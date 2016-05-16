package com.ndl.framework.workbrench.process;


/**
 * 原子服务管理AtomServiceManager，原子服务可以包括
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
import com.ndl.framework.workbrench.define.AtomOrRepositoryPackage;
import com.ndl.framework.workbrench.define.BusinessDescripter;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.DaoBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodCallChainInBodyBean;
import com.ndl.framework.workbrench.define.MethodDescripter;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.freemarker.template.AtomOrRepositorTemplate;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.JAXBUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

public class AtomServiceManager {
	private static final Logger logger = LoggerFactory.getLogger(AtomServiceManager.class);
	private static final AnnotationBean defualtCalleeAnnotationBean=new AnnotationBean();

	private static final RunConfigure runConfigure=RunConfigure.instanceRunConfigure();
	private static final JAXBUtil jaxbUtilDefined = new JAXBUtil(AtomOrRepositoryPackage.class);
	private static final JAXBUtil jaxbUtilTemplate = new JAXBUtil(AtomOrRepositorTemplate.class);
	
	private static Map<String, AtomServiceManager> mapAtomServiceManager = new 
			java.util.concurrent.ConcurrentHashMap<String, AtomServiceManager>();

	private final ServiceBean serviceBean;

	/**
	 *  @Service
	 *	@Transactional
	 *	public class AccountService
	 *
	 *
	 * @param serivceType
	 * @param serviceDescipter
	 */
	private AtomServiceManager(String serivceType,String serviceDescipter,AnnotationBean annotationBean){
		serviceBean=new ServiceBean();
		defualtCalleeAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Autowired);

		//serivceType=serivceType+RunConfigure.ATOM_SERVICE_SUFFIX;
		String serviceId = ClassHelper.namingUsingJavaMethod(serivceType);
		
		serviceBean.setServiceCategory(MethCategoryEnum.ATM);
		setServiceBeanInfo(serviceId,serivceType,serviceDescipter);
		TableBean tableBean = new TableBean();
		// 命名,小写
		tableBean.setTableNameNoDash(serviceId);
		// 类型，大写
		tableBean.setTableNameCapitalized(serivceType);
		addIncludeEntityBean(tableBean);
		//
		AnnotationBean serviceAnnotationBean;
		if(null==annotationBean){
			serviceAnnotationBean=new AnnotationBean();
			serviceAnnotationBean.setAnnoteValue(serviceBean.getServiceId());
		}else{
			serviceAnnotationBean=(AnnotationBean) annotationBean.clone();
		}
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Component);
		addAnnotationBean(serviceAnnotationBean);
		serviceAnnotationBean=new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Transactional);
		addAnnotationBean(serviceAnnotationBean);
		//
		DaoManager daoManager = DaoManager.createDefaultDaoManager(serivceType, serviceDescipter);
		addIncludeDaoBean(daoManager.getDaoBean());
		defualtCalleeAnnotationBean.setAnnoteType(daoManager.getDaoBean().getDaoType());
		defualtCalleeAnnotationBean.setAnnoteValue(daoManager.getDaoBean().getDaoId());
		addInstanceAnnotationBean(defualtCalleeAnnotationBean);
		
	}
	
	/**
	 * 装配原子组件之后，调用此方法写入XML文件。
	 * @param atomPackageName
	 */
	public void generateAtomServiceBeanToXML(String atomPackageName) {
		String atomServicePath=RunConfigure.getConfigPath() + "/atom-service.xml";
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager generateBusinessServiceBeanToXML Begin:");
			logger.info("buinessServicePath:\t"+atomServicePath);
		}
		Set<ServiceBean> list=getBusinessBean();
		if (logger.isInfoEnabled()) {
			logger.info("controll size:\t"+list.size());
		}
		try {
			File file = new File(atomServicePath);
			AtomOrRepositoryPackage servicePackage=new AtomOrRepositoryPackage();
			servicePackage.setAtomOrRepository(atomPackageName);
			servicePackage.setAtomServices(list);
			synchronized(AtomServiceManager.class){
				jaxbUtilDefined.objectToXmlFile(servicePackage, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.lang.RuntimeException("生成业务服务模板，写入配置文件失败", e.getCause());
		}
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager generateControllerBeanToXML Over:");
			logger.info("buinessServicePath:\t"+atomServicePath);
		}
	}
	
	/**
	 * 从XML中读取装配原子组件配置，解析成FreeMarker易识别的BusinessDescripter，之后写入配置文件
	 * @param atomPakageName
	 */
	public static void generateAtomServiceTemplateToXML(String atomPakageName){
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager loadAtomServiceFromXML Begin:");
		}
		//读取模板信息
		AtomOrRepositoryPackage atomOrRepositoryPackage = loadAtomServiceBeanFromXML();
		//解析
		parseAtomServicePackage(atomOrRepositoryPackage);
		writeAtomServiceTempleteToXML(atomOrRepositoryPackage,atomPakageName);
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager loadAtomServiceFromXML Over.");
		}
	}
	
	public static void parseAtomServicePackage(AtomOrRepositoryPackage servicePackage){
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager loadAtomServiceFromXML Begin:");
			logger.info("servicePackage:\t"+servicePackage);
		}
		String packageName = servicePackage.getAtomOrRepository();
		logger.info("AtomService atomPackage:\t" + packageName);
		Set<ServiceBean> businessServices = servicePackage.getAtomServices();
		Assert.notNull(businessServices, "AtomServices must not empty");
	
		for (ServiceBean businessService : businessServices) {
			paraseServiceBean(businessService);
			if(StringUtils.isNotBlank(businessService.getBusinessDescripter().getClassName())){
				servicePackage.addAtomServiceTemplate(businessService.getBusinessDescripter());
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager loadAtomServiceFromXML Over:");
		}
	}
	
	private static void paraseServiceBean(ServiceBean businessService){
		BusinessServiceManager.paraseServiceBean(businessService);
	}
	
	public static void parseCalleeMajorDAO(final BusinessDescripter businessDescripter,
			MethodDescripter methodDescripter,
			final DaoBean calleeMajorDao){
		if (logger.isDebugEnabled()) {
			logger.debug("AtomServiceManager parseCalleeMajorDAO Begin:");
			logger.debug("calleeMajorDao:\t" + calleeMajorDao);
		}
		DaoManager.parseCalleeMajorDAO(businessDescripter,methodDescripter,calleeMajorDao);
		if (logger.isDebugEnabled()) {
			logger.debug("AtomServiceManager parseCalleeMajorDAO Over");
		}
		
	}
	
	private static void writeAtomServiceTempleteToXML(AtomOrRepositoryPackage atomOrRepositoryPackage,String moduleName){
		//AtomOrRepositoryPackage atomOrRepositoryPackage=loadAtomServiceBeanFromXML();
		AtomOrRepositorTemplate moudleAtom=new AtomOrRepositorTemplate();
		moudleAtom.setAtomServiceTemplates(atomOrRepositoryPackage.getAtomServiceTemplates());
		String buinessServicePath=RunConfigure.getConfigPath() + "/atom-service-class-templete.xml";
		try {
			
			File file = new File(buinessServicePath);
			moudleAtom.setModuleName(moduleName);
			synchronized(BusinessServiceManager.class){
				jaxbUtilTemplate.objectToXmlFile(moudleAtom, file);
			}
			atomOrRepositoryPackage.getAtomServiceTemplates().clear();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成业务服务模板，写入配置文件失败", e);
		}
		
	}
	
	public static AtomOrRepositoryPackage loadAtomServiceBeanFromXML() {
		String buinessServicePath=RunConfigure.getConfigPath() + "/atom-service.xml";
		AtomOrRepositoryPackage servicePackage=null;
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadBusinessServiceBeanFromXML Begin:");
			logger.info("controllPath:\t"+buinessServicePath);
		}
		
		try {
			File file = new File(buinessServicePath);
			synchronized(AtomServiceManager.class){
				servicePackage= jaxbUtilDefined.xmlToObject(file,AtomOrRepositoryPackage.class);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成原子服务模板，写入配置文件失败", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadBusinessServiceBeanFromXML Over:");
			logger.info("frontBean:\t"+servicePackage);
		}
		return servicePackage;
	}
	

	
	public static synchronized AtomServiceManager createDefaultAtomServiceManager(String serviceType,
			String descipter,AnnotationBean annotationBean) {
						
		if (logger.isDebugEnabled()) {
			logger.debug("AtomServiceManager createDefaultAtomServiceManager Begin:");
			logger.debug("serviceType:\t" + serviceType);
			logger.debug("descipter:\t" + descipter);
		}
		//服务类型不能为空
		Assert.notNull(serviceType, "serviceType must not be null");
		if (!ClassHelper.isCapitalion(serviceType)) {
			if (logger.isWarnEnabled()) {
				logger.warn("AtomServiceManager createDefaultAtomServiceManager Over: serviceType name error");
				logger.warn("serviceType:\t" + serviceType);
			}
			return null;
		}
		//Assert.notNull(annotationBean, "annotationBean must not be null");
		if (mapAtomServiceManager.containsKey(serviceType)) {
			return (AtomServiceManager) mapAtomServiceManager.get(serviceType);
		}
		AtomServiceManager atomServiceManager = new AtomServiceManager(serviceType, descipter,annotationBean);
		mapAtomServiceManager.put(serviceType, atomServiceManager);
		if (logger.isDebugEnabled()) {
			logger.debug("Over:");
			logger.debug("AtomServiceManager createDefaultAtomServiceManager:\t" + atomServiceManager.serviceBean);
		}
		return atomServiceManager;
	}
	
	public static synchronized AtomServiceManager createDefaultAtomServiceManager(String serviceType,
			String descipter) {
						
		return createDefaultAtomServiceManager(serviceType,descipter,null);
	}
	
	public static AtomServiceManager addAtomMajorMethod(String serviceType,
		    String descripter, String businessMethodSignature,
			String methodSignatureDescripter, ColumnBean startColumnBean,
			ColumnBean returnType,
			String calleeServiceType,AnnotationBean calleeServiceAnnotationBean,
			String calleeServiceMethodSignature, 
			ColumnBean calleeColumnBean,
			MethCategoryEnum calleeMethodCategory,
			ColumnBean calleeReturnType){
		return addAtomMajorMethod( serviceType,null,  descripter,  businessMethodSignature,
				 methodSignatureDescripter,  startColumnBean,returnType,
				 calleeServiceType, calleeServiceAnnotationBean,calleeServiceMethodSignature, 
				 calleeColumnBean,calleeMethodCategory,calleeReturnType);
	}
	
	/**
	 * 
	 * @param serviceType
	 * @param serviceAnnotationBean
	 * @param descripter
	 * @param businessMethodSignature
	 * @param methodSignatureDescripter
	 * @param startColumnBean
	 * @param returnType
	 * @param calleeServiceType
	 * @param calleeServiceAnnotationBean 注解到其它原子服务中的实例变量注解
	 * @param calleeServiceMethodSignature
	 * @param calleeColumnBean
	 * @param calleeMethodCategory
	 * @param calleeReturnType
	 * @return
	 */
	public static AtomServiceManager addAtomMajorMethod(String serviceType,
			AnnotationBean serviceAnnotationBean, String descripter, String businessMethodSignature,
			String methodSignatureDescripter, ColumnBean startColumnBean,
			ColumnBean returnType,
			String calleeServiceType,AnnotationBean calleeServiceAnnotationBean,
			String calleeServiceMethodSignature, 
			ColumnBean calleeColumnBean,
			MethCategoryEnum calleeMethodCategory,//被调用的是JPA
			ColumnBean calleeReturnType) {
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager addAtomMajorMethod Begin:");
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
						"AtomServiceManager addAtomMajorMethod Over: "
						+ "serviceType or businessMethodSignature is null");
			}
			return null;
		}
		//
		boolean isATM=calleeMethodCategory==MethCategoryEnum.ATM?true:false;
		boolean isJPA=calleeMethodCategory==MethCategoryEnum.JPA?true:false;
		boolean isJDBC=calleeMethodCategory==MethCategoryEnum.JDBC?true:false;
		boolean isMBT=calleeMethodCategory==MethCategoryEnum.MBT?true:false;
		if(null==calleeMethodCategory||!(isATM|| isJPA||isJDBC||isMBT)){
			if (logger.isWarnEnabled()) {
				logger.warn(
						"AtomServiceManager addAtomMajorMethod Over: "
						+ "calleeMethodCategory is null or must not ATM or BNS");
			}
			return null;
		}
		//
		AtomServiceManager atomServiceManager;
		if(null!=serviceAnnotationBean){
			atomServiceManager = AtomServiceManager
					.createDefaultAtomServiceManager(serviceType, descripter,serviceAnnotationBean);
		}else{
			atomServiceManager = AtomServiceManager
				.createDefaultAtomServiceManager(serviceType, descripter);
		}
		
		//方法的签名,即当前业务服务的所对应的实体类型
		String serviceId = ClassHelper.namingUsingJavaMethod(serviceType);
		
		TableBean entityBean = new TableBean();
		entityBean.setTableNameNoDash(serviceId);
		entityBean.setTableNameCapitalized(serviceType);
		if (null!=startColumnBean) {
			/*
			Set<ColumnBean> assignBeanList=entityBean.getColumnBeanList();
			if(CollectionUtils.isEmpty(assignBeanList)){
				assignBeanList =new HashSet<ColumnBean>();
			}
			assignBeanList.add(startColumnBean);//控制器调用服务的参数，需要转换为业务服务的变量
			entityBean.setColumnBeanList(assignBeanList);
			*/
			entityBean.setMethodArugment(startColumnBean);
		}
		//业务服务不存在事务，暂时不支持注解
		MethodManager atomMethodManager=MethodManager.createMothod(businessMethodSignature, methodSignatureDescripter,
				entityBean, calleeMethodCategory,returnType);
		
		atomMethodManager.setAssignList(entityBean);
		//当前业务服务所调用的DAO服务
		DaoBean callDAOService=null;
		//当前调用的原子服务
		ServiceBean calleeServiceBean=null;
		boolean isDB=isJPA||isJDBC||isMBT;
		AnnotationBean annotationBean;
		if(null!=calleeServiceAnnotationBean){
			annotationBean=calleeServiceAnnotationBean;
		}else{
			
			annotationBean=defualtCalleeAnnotationBean;
		}
		if(isDB){
			//AnnotationBean daoAnnotationBean=calleeServiceAnnotationBean；
			DaoManager calleeBusinessServiceManager;
			if(null!=calleeServiceAnnotationBean){
				calleeBusinessServiceManager = DaoManager
						.createDefaultDaoManager(calleeServiceType, descripter,annotationBean);
			}else{
				calleeBusinessServiceManager = DaoManager
					.createDefaultDaoManager(calleeServiceType, descripter);
			}
			callDAOService=calleeBusinessServiceManager.getDaoBean();
			annotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
			annotationBean.setAnnoteValue(callDAOService.getDaoId());
			annotationBean.setAnnoteType(callDAOService.getDaoType());
			atomServiceManager.addIncludeDaoBean(callDAOService);
			
			atomMethodManager.addCallDAOService(callDAOService);
		}else if(calleeMethodCategory==MethCategoryEnum.ATM){
			AtomServiceManager callAtomServiceManager;
			if(null!=calleeServiceAnnotationBean){
				callAtomServiceManager=AtomServiceManager.createDefaultAtomServiceManager(calleeServiceType, descripter, calleeServiceAnnotationBean);
			}else{
				callAtomServiceManager=AtomServiceManager.createDefaultAtomServiceManager(calleeServiceType, descripter);
			}
			
			calleeServiceBean=callAtomServiceManager.getServiceBean();
			annotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
			annotationBean.setAnnoteValue(calleeServiceBean.getServiceId());
			annotationBean.setAnnoteType(calleeServiceBean.getSerivceType());
			atomServiceManager.addIncludeService(calleeServiceBean);
			
			atomMethodManager.addCallAtomService(calleeServiceBean);
		}else{
			if (logger.isWarnEnabled()) {
				logger.warn(
						"AtomServiceManager addAtomMajorMethod Over: "
						+ "called ServiceType must JPA/JDBC/MBT or ATM");
			}
			return null;
		}
		atomServiceManager.addInstanceAnnotationBean(annotationBean);
		
		//被调用的方法
		//方法的签名,即当前业务服务的所对应的实体类型
		String calleeServiceId = ClassHelper.namingUsingJavaMethod(calleeServiceType);
				
		TableBean calleeEntityBean = new TableBean();
		calleeEntityBean.setTableNameNoDash(calleeServiceId);
		calleeEntityBean.setTableNameCapitalized(calleeServiceType);
		if (null!=calleeColumnBean) {
			calleeEntityBean.setMethodArugment(calleeColumnBean);
			/*
			Set<ColumnBean> assignBeanList=calleeEntityBean.getColumnBeanList();
			if(CollectionUtils.isEmpty(assignBeanList)){
				assignBeanList =new HashSet<ColumnBean>();
			}
			assignBeanList.add(startColumnBean);//控制器调用服务的参数，需要转换为业务服务的变量
			
			calleeEntityBean.setColumnBeanList(assignBeanList);
			*/
		}
		//调用方法的返回者
		MethodManager calleeMethodManager=MethodManager.createMothod(calleeServiceMethodSignature,methodSignatureDescripter,
				calleeEntityBean,calleeMethodCategory,calleeReturnType);
		//调用第一个方法的方法的返回值需要处理
		if(null!=calleeServiceBean){	
			calleeMethodManager.setCallBusinessServiceMethod(calleeServiceBean, calleeServiceMethodSignature, 
				calleeReturnType, calleeEntityBean,MethodRuntimeEnum.CALL_INTER);
		}
		if(null!=callDAOService){
			calleeMethodManager.setCallBusinessServiceMethod(callDAOService, calleeServiceMethodSignature, 
					calleeReturnType, calleeEntityBean,MethodRuntimeEnum.CALL_INTER);
		}
		calleeMethodManager.setBodyBeanCatetory(MethodRuntimeEnum.CALL_INTER);
		
		//方法体的设置，当next不为null时说明在本方法体内有其它的方法
		atomMethodManager.setBodyBeanCatetory(MethodRuntimeEnum.CALL_OUTER);
		
		
		if(isDB){
			atomMethodManager.setFromDaoBean(callDAOService);
		}else if(calleeMethodCategory==MethCategoryEnum.ATM){
			atomMethodManager.setFromServiceBean(calleeServiceBean);
		}
		
		atomMethodManager.setFromTableBean(entityBean);
		atomMethodManager.setFromColumnBean(startColumnBean);
		atomMethodManager.setFromResultType(returnType);

		atomMethodManager.setToMethod(calleeMethodManager.getMethodBean());
		atomMethodManager.setToTableBean(calleeEntityBean);
		atomMethodManager.setToColumnBean(calleeColumnBean);
		if(isDB){
			atomMethodManager.setToDaoBean(callDAOService);
		}else if(calleeMethodCategory==MethCategoryEnum.ATM){
			atomMethodManager.setToServiceBean(calleeServiceBean);
		}
		atomMethodManager.setToResultType(calleeReturnType);
		if(isDB){
			atomMethodManager.setCallMajorDAOService(callDAOService);
		}else if(calleeMethodCategory==MethCategoryEnum.ATM){
			atomMethodManager.setCalleeMajorService(calleeServiceBean);
		}
		atomMethodManager.setAssignList(calleeEntityBean);
		atomServiceManager.addServiceMethod(atomMethodManager.getMethodBean());
		//设置调用方法链	
		MethodCallChainInBodyBean next=atomMethodManager.copyToBody2FromBody();
		//
		if (logger.isInfoEnabled()) {
			logger.info("AtomServiceManager addAtomMajorMethod Over:");
			logger.info("AtomServiceManager:\t" + atomServiceManager.serviceBean);
		}
		return atomServiceManager;

	}

	protected void setServiceBeanInfo(String serviceId, String serivceType, String serviceDescipter) {
		if (StringUtils.isBlank(serviceId) || StringUtils.isBlank(serivceType)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		//删除空格
		serivceType=StringUtils.trim(serivceType);
		serviceId=StringUtils.trim(serviceId);
		
		serivceType=serivceType+RunConfigure.getAtomServiceSuffix();
		serviceId=serviceId+RunConfigure.getAtomServiceSuffix();

		serviceBean.setServiceId(serviceId );
		serviceBean.setSerivceType(serivceType);
		serviceBean.setServiceDescipter(serviceDescipter);
	}

	protected void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		if (CollectionUtils.isEmpty(annotationBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setAnnotationBeanList(annotationBeanList);
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
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addInstanceAnnotationBean(annotationBean);
	}
	
	public ServiceBean getServiceBean(){
		return this.serviceBean;
	}
	
	protected void setRunningMethod(MethodBean runningMethod){
		if(null==runningMethod){
			throw new ConfigRuntimeException(WorkBrenchConfigProperty.
					PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setRunningMethod(runningMethod);
	}
	public MethodBean getRunningMethod(){
		return serviceBean.getRunningMethod();
	}
	
	protected void addAnnotationBean(AnnotationBean annotationBean) {
		if (null == annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addAnnotationBean(annotationBean);
	}

	protected void setServiceMethodList(Set<MethodBean> serviceMethodList) {
		if (CollectionUtils.isEmpty(serviceMethodList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setServiceMethodList(serviceMethodList);
	}

	protected void addServiceMethod(MethodBean serviceMethod) {
		if (null == serviceMethod) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
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
		if (entityBeanList!=null) {
			serviceBean.addIncludeBean(entityBeanList);
		}
	}

	protected void setIncludeServiceList(Set<ServiceBean> includeServiceList) {
		if (CollectionUtils.isEmpty(includeServiceList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeServiceList(includeServiceList);
	}

	protected void addIncludeService(ServiceBean includeService) {
		if (null == includeService) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
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
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeBeanList(includeBeanList);
	}

	protected void addIncludeEntityBean(TableBean entityBean) {
		if (null == entityBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addIncludeBean(entityBean);
	}

	protected void setIncludeDomainBeanList(Set<DomainBean> includeDomainBeanList) {
		if (CollectionUtils.isEmpty(includeDomainBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeDomainBeanList(includeDomainBeanList);
	}

	protected void addIncludeDomainBean(DomainBean domainBean) {
		if (null == domainBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addIncludeDomainBean(domainBean);
	}

	protected void setIncludeDaoList(Set<DaoBean> includeDaoList) {
		if(CollectionUtils.isEmpty(includeDaoList)){
			throw new ConfigRuntimeException(WorkBrenchConfigProperty.
					PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.setIncludeDaoList(includeDaoList);
	}

	protected void addIncludeDaoBean(DaoBean includeDao) {
		if (null == includeDao) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_ATOMSERVICE_NO_EXIST_FILED);
		}
		serviceBean.addIncludeDaoBean(includeDao);
		//
		Set<TableBean> entityBeanList = includeDao.getIncludeBeanList();
		if (!CollectionUtils.isEmpty(entityBeanList)) {
			serviceBean.addIncludeBean(entityBeanList);
		}
		//
		Set<DomainBean> domainBeanList = includeDao.getIncludeDomainBeanList();
		if (!CollectionUtils.isEmpty(domainBeanList)) {
			serviceBean.addIncludeDomainBean(domainBeanList);
		}
	}

		
	private static Set<ServiceBean> getBusinessBean(){
		Set<ServiceBean> serviceBeanList=new HashSet<ServiceBean>();
		if(mapAtomServiceManager.isEmpty()){
			if (logger.isWarnEnabled()) {
				logger.warn("ControllerManager getControllerBean:");
				logger.warn("mapController must not Empty");
			}
			throw new java.lang.RuntimeException("生成控制器模板时，控制器数量为零");
		}
		//
		Collection<AtomServiceManager> collection=mapAtomServiceManager.values();
	    for(AtomServiceManager atomSerivceManager:collection){
	    	serviceBeanList.add(atomSerivceManager.serviceBean);
	    }
	    return serviceBeanList;
	}
	
	public void buildAtomServiceBean(){
		
	}

}
