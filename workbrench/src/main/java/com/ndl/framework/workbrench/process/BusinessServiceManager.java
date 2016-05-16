package com.ndl.framework.workbrench.process;


/**
 * 业务服务可以调用其它业务服务和原子服务，原子服务由AtomServiceManager
 */
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ndl.framework.workbrench.define.AnnotationBean;
import com.ndl.framework.workbrench.define.AnnotationTypeEnum;
import com.ndl.framework.workbrench.define.BusinessDescripter;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.DaoBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodCallChainInBodyBean;
import com.ndl.framework.workbrench.define.MethodDescripter;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.ServicePackage;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.freemarker.template.ServiceTemplate;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.JAXBUtil;
import com.ndl.framework.workbrench.util.ProductParseTemplateUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

public class BusinessServiceManager {
	private static final Logger logger = LoggerFactory.getLogger(BusinessServiceManager.class);
	
	private static final AnnotationBean defualtCalleeAnnotationBean=new AnnotationBean();
	private static final JAXBUtil jaxbUtilDefined = new JAXBUtil(ServicePackage.class);
	private static final JAXBUtil jaxbUtilTemplate = new JAXBUtil(ServiceTemplate.class);
	private static Map<String, BusinessServiceManager> mapBusinessServiceManager = new java.util.concurrent.ConcurrentHashMap<String, BusinessServiceManager>();

	private final ServiceBean serviceBean;
	
	/**
	 * 定义时（装配）组件写入XML
	 * @param bnsPakageName
	 */
	public void generateBusinessServiceBeanToXML(String bnsPakageName) {
		String buinessServicePath=RunConfigure.getConfigPath() + "/bussiness-service.xml";
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager generateBusinessServiceBeanToXML Begin:");
			logger.info("buinessServicePath:\t"+buinessServicePath);
		}
		Set<ServiceBean> list=getBusinessBean();
		if (logger.isInfoEnabled()) {
			logger.info("controll size:\t"+list.size());
		}
		try {
			File file = new File(buinessServicePath);
			//util.objectToXmlFile(serviceBean,  file);
			ServicePackage servicePackage=new ServicePackage();
			servicePackage.setBusinessServiceName(bnsPakageName);
			servicePackage.setBusinessServices(list);
			synchronized(BusinessServiceManager.class){
				jaxbUtilDefined.objectToXmlFile(servicePackage, file);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成业务服务模板，写入配置文件失败", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager generateControllerBeanToXML Over:");
			logger.info("buinessServicePath:\t"+buinessServicePath);
		}
	}

	/**
	 * 装载配置组件配置XML，解析ServicePackage为易识别的BusinessDescripter，写入FreeMarker的模板文件
	 * @param bnsPakageName
	 */
	public static void generateBusinessServiceTemplateToXML(String bnsPakageName){
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadAtomServiceFromXML Begin:");
		}
		ServicePackage servicePackage =loadBusinessServicePackageFromXML();
		paraseServicePackage(servicePackage);
		writeBusinessServicePackageTempleteToXML(servicePackage,bnsPakageName);
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadAtomServiceFromXML Over.");
		}
	}
	
	
	
	public static void paraseServicePackage(ServicePackage servicePackage){
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager paraseServicePackage Begin:");
		}
		String packageName = servicePackage.getBusinessServiceName();
		logger.debug("BusinessService packageName:\t" + packageName);
		Set<ServiceBean> businessServices = servicePackage.getBusinessServices();
		Assert.notNull(businessServices, "BusinessServices must not empty");
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager loadAtomServiceFromXML Begin:");
		}
	
		for (ServiceBean businessService : businessServices) {
			paraseServiceBean(businessService);
			if(StringUtils.isNotBlank(businessService.getBusinessDescripter().getClassName())){
				servicePackage.addBusinessServiceTemplate(businessService.getBusinessDescripter());
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager paraseServicePackage Over.");
		}
	}
	
	public static void paraseServiceBean(final ServiceBean businessService){
		if (logger.isDebugEnabled()) {
			logger.debug("ServiceManager paraseServiceBean Begin:");
			logger.debug("businessService:\t" + businessService);
		}
		Set<MethodBean> businessOwnerMethods = businessService.getServiceMethodList();
		if (null == businessOwnerMethods) {
			logger.debug("businessOwnerMethods is null, it is refer,not real class");
			return;// 容错，当原子服务包含原子服务时，没有方法，为匿名服务
		}
		
		//解析方法类型
		String serviceType = businessService.getSerivceType();
		Assert.notNull(serviceType, "serviceType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService serviceType:\t" + serviceType);
		}
		//解析类名
		ProductParseTemplateUtil.parseClassName(businessService.getBusinessDescripter(), serviceType);
		
		//解析方法注解
		String serviceId = businessService.getServiceId();
		Assert.notNull(serviceId, "serviceId must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService serviceId:\t" + serviceId);
		}
		ProductParseTemplateUtil.parseHeaderAnnotation(businessService.getServiceCategory(), 
				businessService.getBusinessDescripter(), serviceId);
		
		//解析方法注释
		String businessServiceDescripter = businessService.getServiceDescipter();
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService businessServiceDescripter:\t" + businessServiceDescripter);
		}
		ProductParseTemplateUtil.parseClassComment(businessService.getBusinessDescripter(), businessServiceDescripter);
		
		
		MethCategoryEnum serviceCategory = businessService.getServiceCategory();
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService serviceCategory:\t" + serviceCategory);
		}
		ProductParseTemplateUtil.parseComponentCategory(businessService.getBusinessDescripter(), serviceCategory);
		
		//解析服务头部注解
		Set<AnnotationBean> businessAnnotations = businessService.getAnnotationBeanList();
		Assert.notNull(businessAnnotations, "business Annotations must not empty");
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService businessAnnotations:\t" + businessAnnotations);
		}
		ProductParseTemplateUtil.parseHeaderAnnotation(serviceCategory, businessService.getBusinessDescripter(), businessAnnotations);
		
		//解析服务头部注解
		Set<AnnotationBean> instanceCalleeAnnotations = businessService.getInstanceAnnotationBeanList();
		Assert.notNull(instanceCalleeAnnotations, "business Annotations must not empty");
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService businessAnnotations:\t" + businessAnnotations);
		}
		ProductParseTemplateUtil.parseInstanceCalleeAnnotation(businessService.getBusinessDescripter(), instanceCalleeAnnotations);
		
		//解析服务Import的实体类
		Set<TableBean> businessIncludeBeans = businessService.getIncludeBeanList();
		Assert.notNull(businessIncludeBeans, "businessIncludeBeans must not empty");
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService businessIncludeBeans:\t" + businessIncludeBeans);
		}
		ProductParseTemplateUtil.parseClassTableBeanImport(businessService.getBusinessDescripter(), businessIncludeBeans);
		
		//解析服务Import的服务类
		Set<ServiceBean> businessIncludeServices = businessService.getIncludeServiceList();
		//引入的服务有可能是空，此时说明它仅仅支持原生的配置，如原子服务一般的引入DAO
		//Assert.notNull(businessIncludeServices, "businessIncludeServices must not empty");
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessService businessIncludeServices:\t" + businessIncludeServices);
		}
		ProductParseTemplateUtil.parseClassServiceImport(businessService.getBusinessDescripter(), businessIncludeServices);
		
		//解析具体方法
		Assert.notNull(businessOwnerMethods, "businessOwnerMethods is not empty");
		for (MethodBean methodBean : businessOwnerMethods) {
			MethodManager.parseMethod(methodBean,
					businessService.getBusinessDescripter().getClassName(),serviceCategory);
			
			MethodDescripter methodDescripter=(MethodDescripter) methodBean.getMethodDescripter().clone();
			BusinessDescripter businessDescripter=businessService.getBusinessDescripter();
			businessDescripter.addMethodDescripter(methodDescripter);
			// 方法内部，也就是方法体的处理，方法体不是方法签名的描述。
			ServiceBean calleeMajorService = methodBean.getCalleeMajorService();
			if(null!=calleeMajorService){
				//doCalleeMajorService(calleeMajorService);
				parseCalleeMajorService(businessDescripter,methodDescripter,calleeMajorService);
			}else{
				DaoBean calleeMajorDao=methodBean.getCallMajorDAOService();
				Assert.notNull(calleeMajorDao, "DaoBean mehod calleeMajorDao must not null");
				if (logger.isDebugEnabled()) {
					logger.debug(
						"methods calleeMajorDao:\t" + calleeMajorDao);
				}
				AtomServiceManager.parseCalleeMajorDAO(businessDescripter,methodDescripter,calleeMajorDao);
			}
			// 方法调用具体服务，需要在头部导入
			Set<ServiceBean> callBusinessServices = methodBean.getCallBusinessServices();

			Set<ServiceBean> callAtomServices = methodBean.getCallAtomServices();
			Set<DaoBean> callDaoServices = methodBean.getCallDAOServices();
			Set<ServiceBean> callServices = null;
			if (null != callBusinessServices || null != callAtomServices) {
				callServices = new HashSet<ServiceBean>();
				if (!CollectionUtils.isEmpty(callBusinessServices)) {
					callServices.addAll(callBusinessServices);
				}
				if (!CollectionUtils.isEmpty(callAtomServices)) {
					callServices.addAll(callAtomServices);
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("BusinessService methods callServices:\t" + callServices);
				logger.debug("BusinessService methods callDaoServices:\t" + callDaoServices);
			}

		}
		if (logger.isDebugEnabled()) {
			logger.debug("ServiceManager paraseServiceBean Over");
		}
	}
	
	public static void parseCalleeMajorService(final BusinessDescripter businessDescripter,
			MethodDescripter methodDescripter,ServiceBean calleeMajorService){
		if (logger.isDebugEnabled()) {
			logger.debug("ServiceManager parseCalleeMajorService Begin:");
			logger.debug("calleeMajorService:\t" + calleeMajorService);
		}
		Assert.notNull(calleeMajorService, "parseCalleeMajorService calleeMajorService must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseCalleeMajorService calleeMajorService:\t" + calleeMajorService);
		}
		String calleeMajorServiceType = calleeMajorService.getSerivceType();
		Assert.notNull(calleeMajorServiceType, "BusinessService mehod calleeMajorServiceType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseCalleeMajorService calleeMajorServiceType:\t" + calleeMajorServiceType);
		}
		String calleeMajorServiceId = calleeMajorService.getServiceId();
		Assert.notNull(calleeMajorServiceId, "BusinessService mehod calleeMajorServiceId must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseCalleeMajorService calleeMajorServiceId:\t" + calleeMajorServiceId);
		}
		//方法体内调用的方法
		MethodBean calleeMajorRunningMethod = calleeMajorService.getRunningMethod();

	 	MethodManager.parseMethodBody(methodDescripter,calleeMajorServiceId,calleeMajorRunningMethod);
		if (logger.isDebugEnabled()) {
			logger.debug("ServiceManager parseCalleeMajorService Over.");
		}
	}
	
	/**
	 * 从XML中装载定义时（装配）组件
	 * @return
	 */
	public static ServicePackage loadBusinessServicePackageFromXML() {
		String buinessServicePath=RunConfigure.getConfigPath() + "/bussiness-service.xml";
		ServicePackage servicePackage=null;
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadBusinessServiceBeanFromXML Begin:");
			logger.info("controllPath:\t"+buinessServicePath);
		}
		
		try {
			File file = new File(buinessServicePath);
			synchronized(BusinessServiceManager.class){
				servicePackage= jaxbUtilDefined.xmlToObject(file,ServicePackage.class);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("装载业务服务模板，写入配置文件失败", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadBusinessServiceBeanFromXML Over:");
			logger.info("frontBean:\t"+servicePackage);
		}
		return servicePackage;
	}
	
	private static void writeBusinessServicePackageTempleteToXML(ServicePackage servicePackage,String bnsPakageName){
		String buinessServicePath=RunConfigure.getConfigPath() + "/bussiness-service-class-templete.xml";
		try {
			ServiceTemplate serviceTemplete=new ServiceTemplate();
			File file = new File(buinessServicePath);
			serviceTemplete.setServiceTemplates(servicePackage.getBusinessServiceTemplates());
			synchronized(BusinessServiceManager.class){
				jaxbUtilTemplate.objectToXmlFile(serviceTemplete, file);
			}
			serviceTemplete.getServiceTemplates().clear();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成业务服务模板，写入配置文件失败", e);
		}
	}
	//////////////////////////////////////////////////////////////////////////
	
	public static synchronized BusinessServiceManager createDefaultBusinessServiceManager(String serviceType,
			String descipter,AnnotationBean annotationBean){
		if (logger.isDebugEnabled()) {
			logger.debug("BusinessServiceManager createDefaultBusinessServiceManager Begin:");
			logger.debug("serviceType:\t" + serviceType);
			logger.debug("descipter:\t" + descipter);
		}
		// 服务类型不能为空
		Assert.notNull(serviceType, "serviceType must not be null");
		// 服务类型不能为空
		//Assert.notNull(annotationBean, "annotationBean must not be null");
		if (!ClassHelper.isCapitalion(serviceType)) {
			if (logger.isWarnEnabled()) {
				logger.warn("BusinessServiceManager createDefaultBusinessServiceManager Over: serviceType name error");
				logger.warn("serviceType:\t" + serviceType);
			}
			return null;
		}

		if (mapBusinessServiceManager.containsKey(serviceType)) {
			return (BusinessServiceManager) mapBusinessServiceManager.get(serviceType);
		}
		BusinessServiceManager businessServiceManager = new BusinessServiceManager(serviceType, descipter);
		
		mapBusinessServiceManager.put(serviceType, businessServiceManager);
		if (logger.isDebugEnabled()) {
			logger.debug("Over:");
			logger.debug("BusinessServiceManager createDefaultBusinessServiceManager:\t"
					+ businessServiceManager.serviceBean);
		}
		return businessServiceManager;
	}
	
	public static BusinessServiceManager createDefaultBusinessServiceManager(String serviceType,
			String descipter) {
		return createDefaultBusinessServiceManager(serviceType, descipter, null);
	}



	/**
	 * 此签名内部仅仅调用一个方法。
	 * @Component("cityBusinessService")
	 *	public class CityServiceImpl implements CityService {
	 *
	 *		private final CityAtomService cityAtomService;
	 *	
	 *		private final HotelService hotelService;
	 *	
	 *		@Autowired
	 *		public CityServiceImpl(CityAtomService cityAtomService,
	 *				HotelService hotelAtomService) {
	 *			this.cityAtomService = cityAtomService;
	 *			this.hotelService = hotelService;
	 *		}
	 *      
	 *      @Override
	 *		public City getCity(String name, String country) {
	 *			Assert.notNull(name, "Name must not be null");
	 *			Assert.notNull(country, "Country must not be null");
	 *			return this.cityAtomService.findByNameAndCountryAllIgnoringCase(name, country);
	 *		}
	 *
	 *		@Override
	 *		public Page<HotelSummary> getHotels(City city, Pageable pageable) {
	 *			Assert.notNull(city, "City must not be null");
	 *			return this.hotelService.findByCity(city, pageable);
	 *		}
	 *	}
	 * 
	 *  上述示例是业务方法需要处理的一般逻辑
	 *  业务服务命名如下：
	 * 	业务服务可以使用此方法构建业务服务具体方法调用链内部逻辑的实现
	 * 	业务服务接口为实体类型加BusinessService
	 * 	业务服务实现为实体类型加BusinessServiceImpl
	 *  如果此业务服务接口多个业务服务实现时，引入它的控制器或者其它服务采用采用下面规约：
	 *  采用下面方案完成多个实现的注入
	 *		@Resource(name=“userDao”)
	 *		private UserDao  userDao;//用于字段上
	 *		支持@Autowired按名称装配
	 *		@Autowired  @Qualifier("userDao")
	 *		private PersonDao  personDao;
     *
	 * @param serviceType 服务类型 实体类型，必须大写
	 * @param serviceAnnotationBean 注解，业务方法注解，如原子服务需要注解事务属性等
	 * @param descripter 业务服务注释信息
	 * @param methodAnnotationBean 方法签名的注解，默认为一个注解，即原子服务的事务注解，DAO的Query注解
	 * @param businessMethodSignature 业务方法签名
	 * @param methodSignatureDescripter 业务方法说明
	 * @param startColumnBean 业务方法签名
	 * @param returnType 当前业务方法的返回值类型
	 * @param calleeServiceType 业务服务方法体内调用实例的类型，可以是多个，此时需要定义为当前业务服务的实例变量，并且声明为Autowired
	 * @param calleeServiceAnnotationBean 被调用服务的签名的注解，用于当前业务服务实例变量注解
	 * @param calleeServiceMethodSignature 业务服务方法体内调用实例变量服务的某个方法的签名
	 * @param calleeColumnBean 业务服务方法体内调用实例的类型为基础所需传入的参数
	 * @param calleeAnnotationBean 如果被调用的服务（原子服务、业务服务）有多个实现，此参数描述其具体采用的Service
	 * @param calleeMethodCategory 调用的第一个方法是业务服务，还是原子服务 
	 * @param calleeReturnType 调用方法的返回值
	 * @param calleeReturnFieldEnum 调用服务返回类型
	 * @return
	 */
	public static BusinessServiceManager addServiceMajorMethod(String serviceType,
			AnnotationBean serviceAnnotationBean, String descripter, String businessMethodSignature,
			String methodSignatureDescripter, ColumnBean startColumnBean,
			ColumnBean returnType,
			String calleeServiceType,AnnotationBean calleeServiceAnnotationBean,
			String calleeServiceMethodSignature, 
			ColumnBean calleeColumnBean,
			MethCategoryEnum calleeMethodCategory,
			ColumnBean calleeReturnType) {
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager addServiceMajorMethod Begin:");
			logger.info("serviceType:\t" + serviceType);
			logger.info("serviceAnnotationBean:\t" + serviceAnnotationBean);
			logger.info("descripter:\t" + descripter);
			logger.info("businessMethodSignature:\t" + businessMethodSignature);
			logger.info("methodSignatureDescripter:\t" + methodSignatureDescripter);
			logger.info("startColumnBean:\t" + startColumnBean);

			logger.info("calleeServiceType:\t" + calleeServiceType);
			logger.info("calleeServiceAnnotationBean:\t" + calleeServiceAnnotationBean);
			logger.info("calleeServiceMethodSignature:\t" + calleeServiceMethodSignature);
			logger.info("calleeColumnBean:\t" + calleeColumnBean);
			logger.info("calleeMethodCategory:\t" + calleeMethodCategory);
			logger.info("calleeReturnType:\t" + calleeReturnType);
		}
		if (!ClassHelper.isCapitalion(serviceType) || StringUtils.isBlank(businessMethodSignature)
				|| StringUtils.isBlank(calleeServiceMethodSignature)) {
			if (logger.isWarnEnabled()) {
				logger.warn(
						"BusinessServiceManager addServiceMajorMethod Over: "
						+ "serviceType or businessMethodSignature is null");
			}
			return null;
		}
		//
		boolean isATM=calleeMethodCategory==MethCategoryEnum.ATM?true:false;
		boolean isBNS=calleeMethodCategory==MethCategoryEnum.BNS?true:false;
		if(null==calleeMethodCategory||!(isATM|| isBNS)){
			if (logger.isWarnEnabled()) {
				logger.warn(
						"BusinessServiceManager addServiceMajorMethod Over: "
						+ "calleeMethodCategory is null or must not ATM or BNS");
			}
			return null;
		}
		
		//如果有类注解，则注解为
		/*
		 	@Service("cityService")
			class CityServiceImpl implements CityService
		 */
		BusinessServiceManager businessServiceManager;
		if(null!=serviceAnnotationBean){
			businessServiceManager = BusinessServiceManager
					.createDefaultBusinessServiceManager(serviceType, descripter,serviceAnnotationBean);
		}else{
			businessServiceManager = BusinessServiceManager
				.createDefaultBusinessServiceManager(serviceType, descripter);
		}
		
		AnnotationBean annotationBean;
		if(null!=calleeServiceAnnotationBean){
			annotationBean=calleeServiceAnnotationBean;
		}else{
			
			annotationBean=defualtCalleeAnnotationBean;
		}

		//方法的签名,即当前业务服务的所对应的实体类型
		String serviceId = ClassHelper.namingUsingJavaMethod(serviceType);
		serviceId=StringUtils.trim(serviceId);
		Assert.notNull(serviceId,"serviceId must not null");
		TableBean entityBean = new TableBean();
		entityBean.setTableNameNoDash(serviceId);
		entityBean.setTableNameCapitalized(serviceType);
		if (null!=startColumnBean) {
			entityBean.setMethodArugment(startColumnBean);
			/*
			Set<ColumnBean> assignBeanList=entityBean.getColumnBeanList();
			if(CollectionUtils.isEmpty(assignBeanList)){
				assignBeanList =new HashSet<ColumnBean>();
			}
			assignBeanList.add(startColumnBean);//控制器调用服务的参数，需要转换为业务服务的变量
			entityBean.setColumnBeanList(assignBeanList);
			*/
		}
		//业务服务不存在事务，暂时不支持注解
		MethodManager businessMethodManager=MethodManager.createMothod(businessMethodSignature, methodSignatureDescripter,
				entityBean, MethCategoryEnum.BNS,returnType);
		
		businessMethodManager.setAssignList(entityBean);
		//当前业务服务所调用的业务服务
		ServiceBean calleeServiceBean;
	
		if(calleeMethodCategory==MethCategoryEnum.BNS){
			BusinessServiceManager calleeBusinessServiceManager;
			if(null!=calleeServiceType){
				calleeBusinessServiceManager = BusinessServiceManager
						.createDefaultBusinessServiceManager(calleeServiceType, descripter,annotationBean);
			}else{
				calleeBusinessServiceManager = BusinessServiceManager
					.createDefaultBusinessServiceManager(serviceType, descripter,annotationBean);
			}
			calleeServiceBean=calleeBusinessServiceManager.getServiceBean();

			businessMethodManager.addCallBusinessService(calleeServiceBean);
		}else if(calleeMethodCategory==MethCategoryEnum.ATM){
			AtomServiceManager atomServiceManager;
			if(null==calleeServiceType){
				atomServiceManager=AtomServiceManager.createDefaultAtomServiceManager(serviceType, descripter, annotationBean);
			}else{
				atomServiceManager=AtomServiceManager.createDefaultAtomServiceManager(calleeServiceType, descripter,annotationBean);
			}
			calleeServiceBean=atomServiceManager.getServiceBean();
			businessMethodManager.addCallAtomService(calleeServiceBean);
		}else{
			if (logger.isWarnEnabled()) {
				logger.warn(
						"BusinessServiceManager addServiceMajorMethod Over: "
						+ "called ServiceType must BNS or ATM");
			}
			return null;
		}
		annotationBean.setAnnoteType(calleeServiceBean.getSerivceType());
		annotationBean.setAnnoteValue(calleeServiceBean.getServiceId());
		businessServiceManager.addInstanceAnnotationBean(annotationBean);
		//被调用的方法
		//方法的签名,即当前业务服务的所对应的实体类型
		String calleeServiceId = ClassHelper.namingUsingJavaMethod(calleeServiceType);
		calleeServiceId=StringUtils.trim(calleeServiceId);
		Assert.notNull(serviceId,"calleeServiceId must not null");		
		TableBean calleeEntityBean = new TableBean();
		calleeEntityBean.setTableNameNoDash(calleeServiceId);
		calleeEntityBean.setTableNameCapitalized(calleeServiceType);
		if (null!=calleeColumnBean) {
			calleeEntityBean.setMethodArugment(calleeColumnBean);
			Set<ColumnBean> assignBeanList=calleeEntityBean.getColumnBeanList();
			if(CollectionUtils.isEmpty(assignBeanList)){
				assignBeanList =new HashSet<ColumnBean>();
			}
			assignBeanList.add(startColumnBean);//控制器调用服务的参数，需要转换为业务服务的变量
			calleeEntityBean.setColumnBeanList(assignBeanList);
		}
		//调用方法的返回者
		MethodManager calleeMethodManager=MethodManager.createMothod(calleeServiceMethodSignature,methodSignatureDescripter,
				calleeEntityBean,calleeMethodCategory,calleeReturnType);
		//调用第一个方法的方法的返回值需要处理
			
		calleeMethodManager.setCallBusinessServiceMethod(calleeServiceBean, calleeServiceMethodSignature, 
				calleeReturnType, calleeEntityBean,MethodRuntimeEnum.CALL_INTER);
		calleeMethodManager.setBodyBeanCatetory(MethodRuntimeEnum.CALL_INTER);
		
		//方法体的设置，当next不为null时说明在本方法体内有其它的方法
		businessMethodManager.setBodyBeanCatetory(MethodRuntimeEnum.CALL_OUTER);
		businessMethodManager.setFromServiceBean(businessServiceManager.getServiceBean());
		businessMethodManager.setFromTableBean(entityBean);
		businessMethodManager.setFromColumnBean(startColumnBean);
		businessMethodManager.setFromResultType(returnType);

		businessMethodManager.setToMethod(calleeMethodManager.getMethodBean());
		businessMethodManager.setToTableBean(calleeEntityBean);
		businessMethodManager.setToColumnBean(calleeColumnBean);
		businessMethodManager.setToServiceBean(calleeServiceBean);
		businessMethodManager.setToResultType(calleeReturnType);
		
		
		businessMethodManager.setCalleeMajorService(calleeServiceBean);

		businessMethodManager.setAssignList(calleeEntityBean);
		
		businessServiceManager.addServiceMethod(businessMethodManager.getMethodBean());
		//设置调用方法链	
		MethodCallChainInBodyBean next=businessMethodManager.copyToBody2FromBody();
		//
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager addServiceMajorMethod Over:");
			logger.info("BusinessServiceManager:\t" + businessServiceManager.serviceBean);
		}
		return businessServiceManager;

	}

	/**
	 * 业务方法同调用的主方法（第一个方法）的签名完全相同，只是Service不同，它是原子服务
	 * 它调用的服务为ATM类型服务
	 * @param serviceType
	 * @param serviceAnnotationBean
	 * @param descripter
	 * @param businessMethodSignature
	 * @param methodSignatureDescripter
	 * @param startColumnBean
	 * @param returnType
	 * @return
	 */
	public static BusinessServiceManager addServiceMajorMethod(String serviceType,
			AnnotationBean serviceAnnotationBean, String descripter, String businessMethodSignature,
			String methodSignatureDescripter, ColumnBean startColumnBean,
			ColumnBean returnType
			){
		String calleeServiceType=serviceType;
		AnnotationBean calleeServiceAnnotationBean=serviceAnnotationBean;
		String calleeServiceMethodSignature=businessMethodSignature; 
		ColumnBean calleeColumnBean=startColumnBean;
		ColumnBean calleeReturnType=returnType;
		return  addServiceMajorMethod( serviceType,
				 serviceAnnotationBean,  descripter,  businessMethodSignature,
				 methodSignatureDescripter,  startColumnBean,
				 returnType,
				 calleeServiceType, calleeServiceAnnotationBean,
				 calleeServiceMethodSignature, 
				 calleeColumnBean,
				 MethCategoryEnum.ATM,
				 calleeReturnType) ;
	}
	
	public static BusinessServiceManager addServiceSecondaryMethod(String serviceType,
			AnnotationBean serviceAnnotationBean, String descripter, String businessMethodSignature,
			String methodSignatureDescripter, ColumnBean startColumnBean,
			ColumnBean returnType,
			String calleeServiceType,AnnotationBean calleeServiceAnnotationBean,
			String calleeServiceMethodSignature, 
			ColumnBean calleeColumnBean,AnnotationBean calleeAnnotationBean,
			MethCategoryEnum calleeMethodCategory,
			ColumnBean calleeReturnType,
			FieldTypeEnum calleeReturnFieldEnum){
		
		return null;
		
	}
	
	protected void setBusinessServiceInfo(String serviceId, String serivceType, String serviceDescipter) {
		if (StringUtils.isBlank(serviceId) || StringUtils.isBlank(serivceType)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		//删除空格
		serviceId=StringUtils.trim(serviceId);
		serivceType=StringUtils.trim(serivceType);
		
		serviceId=serviceId + RunConfigure.getBusinessServiceSuffix();
		serivceType=serivceType+RunConfigure.getBusinessServiceSuffix();
		
		serviceBean.setServiceId(serviceId);
		serviceBean.setSerivceType(serivceType);
		serviceBean.setServiceDescipter(serviceDescipter);
	}

	public ServiceBean getServiceBean() {
		return this.serviceBean;
	}

	
	public void setRunningMethod(MethodBean runningMethod) {
		if (null == runningMethod) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setRunningMethod(runningMethod);
	}

	public MethodBean getRunningMethod() {
		return serviceBean.getRunningMethod();
	}

	public static BusinessServiceManager getBusinessServiceManager(String serviceType){
		if (mapBusinessServiceManager.size()<=0) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		return mapBusinessServiceManager.get(serviceType);
	}
	
	public MethodBean getMethodBeanBySignature(String signature){
		Set<MethodBean> listMethodBean=serviceBean.getServiceMethodList();
		if (CollectionUtils.isEmpty(listMethodBean)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		//查找业务方法的签名
		for(MethodBean methodBean : listMethodBean){
			String methodName=methodBean.getSignature();
			if(methodName.equals(signature)){
				return methodBean;
			}
		}
		return null;
	}
	
	protected void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		if (CollectionUtils.isEmpty(annotationBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setAnnotationBeanList(annotationBeanList);
	}

	protected void addAnnotationBean(AnnotationBean annotationBean) {
		if (null == annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addAnnotationBean(annotationBean);
	}

	public Set<AnnotationBean> getInstanceAnnotationBeanList() {
		return serviceBean.getInstanceAnnotationBeanList();
	}

	protected void setInstanceAnnotationBeanList(Set<AnnotationBean> instanceAnnotationBeanList) {
		serviceBean.setInstanceAnnotationBeanList(instanceAnnotationBeanList);
	}
	
	protected void addInstanceAnnotationBean(AnnotationBean annotationBean) {
		if (null==annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addInstanceAnnotationBean(annotationBean);
	}
	
	protected void setServiceMethodList(Set<MethodBean> serviceMethodList) {
		if (CollectionUtils.isEmpty(serviceMethodList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setServiceMethodList(serviceMethodList);
	}

	protected void addServiceMethod(MethodBean serviceMethod) {
		if (null == serviceMethod) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addSerivceMethod(serviceMethod);
		//
		Set<ServiceBean> businessServiceBeanList = serviceMethod.getCallBusinessServices();
		if (!CollectionUtils.isEmpty(businessServiceBeanList)) {
			serviceBean.addIncludeService(businessServiceBeanList);
		}
		Set<DomainBean> domainBeanList = serviceMethod.getAssignDomainList();
		if (!CollectionUtils.isEmpty(domainBeanList)) {
			serviceBean.addIncludeDomainBean(domainBeanList);
		}
		TableBean entityBeanList = serviceMethod.getAssignList();
		if (null!=entityBeanList) {
			serviceBean.addIncludeBean(entityBeanList);
		}
	}

	protected void setIncludeServiceList(Set<ServiceBean> includeServiceList) {
		if (CollectionUtils.isEmpty(includeServiceList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeServiceList(includeServiceList);
	}

	protected void addIncludeServiceList(ServiceBean includeService) {
		if (null == includeService) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addIncludeService(includeService);
		//
		Set<TableBean> entityBeanList = includeService.getIncludeBeanList();
		if (!CollectionUtils.isEmpty(entityBeanList)) {
			serviceBean.addIncludeBean(entityBeanList);
		}
		//
		Set<DomainBean> domainBeanList = includeService.getIncludeDomainBeanList();
		if (!CollectionUtils.isEmpty(domainBeanList)) {
			serviceBean.addIncludeDomainBean(domainBeanList);
		}

	}

	protected void setIncludeBeanList(Set<TableBean> includeBeanList) {
		if (CollectionUtils.isEmpty(includeBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeBeanList(includeBeanList);
	}

	protected void addIncludeEntityBean(TableBean entityBean) {
		if (null == entityBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addIncludeBean(entityBean);
	}

	protected void setIncludeDomainBeanList(Set<DomainBean> includeDomainBeanList) {
		if (CollectionUtils.isEmpty(includeDomainBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeDomainBeanList(includeDomainBeanList);
	}

	protected void addIncludeDomainBean(DomainBean domainBean) {
		if (null == domainBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_BUSINESSSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addIncludeDomainBean(domainBean);
	}

	private static Set<ServiceBean> getBusinessBean(){
		Set<ServiceBean> serviceBeanList=new HashSet<ServiceBean>();
		if(mapBusinessServiceManager.isEmpty()){
			if (logger.isWarnEnabled()) {
				logger.warn("ControllerManager getControllerBean:");
				logger.warn("mapController must not Empty");
			}
			throw new java.lang.RuntimeException("生成控制器模板时，控制器数量为零");
		}
		//
		Collection<BusinessServiceManager> collection=mapBusinessServiceManager.values();
	    for(BusinessServiceManager businessSerivceManager:collection){
	    	serviceBeanList.add(businessSerivceManager.serviceBean);
	    }
	    return serviceBeanList;
	}
	
	private BusinessServiceManager(String serivceType, String serviceDescipter){
		this(serivceType, serviceDescipter, null);
	}
	
	private BusinessServiceManager(String serivceType, String serviceDescipter,AnnotationBean annotationBean) {
		serviceBean = new ServiceBean();
		defualtCalleeAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Autowired);

		//serivceType=serivceType+RunConfigure.BUSINESS_SERVICE_SUFFIX;
		String serviceId = ClassHelper.namingUsingJavaMethod(serivceType);

		serviceBean.setServiceCategory(MethCategoryEnum.BNS);
		setBusinessServiceInfo(serviceId, serivceType, serviceDescipter);
		AnnotationBean serviceAnnotationBean;
		if(annotationBean!=null){
			serviceAnnotationBean=(AnnotationBean) annotationBean.clone();
			if(StringUtils.isBlank(annotationBean.getAnnoteValue())){
				serviceAnnotationBean.setAnnoteValue(serviceBean.getServiceId());
			}
			if(StringUtils.isBlank(annotationBean.getAnnoteType())){
				serviceAnnotationBean.setAnnoteType(serviceBean.getSerivceType());
			}
			serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
			addAnnotationBean(serviceAnnotationBean);
		}else{
			serviceAnnotationBean=new AnnotationBean();
			serviceAnnotationBean.setAnnoteValue(serviceBean.getServiceId());
			//serviceAnnotationBean.setAnnoteType(serviceBean.getSerivceType());
			serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
			addAnnotationBean(serviceAnnotationBean);
			
		}
		TableBean tableBean = new TableBean();
		// 命名,小写
		tableBean.setTableNameNoDash(serviceId);
		// 类型，大写
		tableBean.setTableNameCapitalized(serivceType);
		addIncludeEntityBean(tableBean);
		// 默认增加对原子服务的实例化
		AtomServiceManager atomServiceManager = AtomServiceManager.createDefaultAtomServiceManager(serivceType,
				serviceDescipter);
		
		addIncludeServiceList(atomServiceManager.getServiceBean());
		defualtCalleeAnnotationBean.setAnnoteValue(atomServiceManager.getServiceBean().getServiceId());
		defualtCalleeAnnotationBean.setAnnoteType(atomServiceManager.getServiceBean().getSerivceType());
		addInstanceAnnotationBean(defualtCalleeAnnotationBean);

	}
}
