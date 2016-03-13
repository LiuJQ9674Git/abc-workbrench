package com.ndl.framework.workbrench.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import com.ndl.framework.workbrench.define.ControllerBean;
import com.ndl.framework.workbrench.define.DaoBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.FrontPackage;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodCallChainInBodyBean;
import com.ndl.framework.workbrench.define.MethodDescripter;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.define.TableColumnConfig;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.Generate;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.freemarker.template.AtomOrRepositorTemplate;
import com.ndl.framework.workbrench.freemarker.template.FrontTemplete;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.JAXBUtil;
import com.ndl.framework.workbrench.util.ProductParseTemplateUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

public class ControllerManager {

	private static final Logger logger = LoggerFactory.getLogger(ControllerManager.class);
	//private static final RunConfigure runConfigure = new RunConfigure();
	private static final JAXBUtil jaxbUtilDefined = new JAXBUtil(FrontPackage.class);
	private static final JAXBUtil jaxbUtilTemplate = new JAXBUtil(FrontTemplete.class);

	private final ControllerBean controllerBean;
	private static final AnnotationBean defualtCalleeAnnotationBean=new AnnotationBean();
	private static Map<String, ControllerManager> mapController = new java.util.concurrent.ConcurrentHashMap<String, ControllerManager>();

	public void generateControllerBeanToXML(String frontModuleName) {
		String controllPath=RunConfigure.getConfigPath() + "/controller-config.xml";
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager generateControllerBeanToXML Begin:");
			logger.info("controllPath:\t"+controllPath);
		}
		Set<ControllerBean> list=getControllerBean();
		if (logger.isInfoEnabled()) {
			logger.info("controll size:\t"+list.size());
		}

		try {
			//JAXBUtil util = new JAXBUtil(FrontPackage.class);
			FrontPackage frontBean=new FrontPackage();
			frontBean.setFrontName(frontModuleName);
			frontBean.setControlleres(list);
			File file = new File(controllPath);
			synchronized(ControllerManager.class){
				jaxbUtilDefined.objectToXmlFile(frontBean, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new java.lang.RuntimeException("生成控制器模板，写入配置文件失败:\n"+ e.getMessage());
		}
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager generateControllerBeanToXML Over:");
			logger.info("controllPath:\t"+controllPath);
		}
	}

	
	public static FrontPackage loadFrontPackageFromXML() {
		String controllPath=RunConfigure.getConfigPath() + "/controller-config.xml";
		FrontPackage frontBean=null;
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager generateControllerBeanToXML Begin:");
			logger.info("controllPath:\t"+controllPath);
		}
		
		try {
		
			File file = new File(controllPath);
			synchronized(ControllerManager.class){
				frontBean= jaxbUtilDefined.xmlToObject(file,FrontPackage.class);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成控制器模板，写入配置文件失败", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager loadControllerBeanFromXML Over:");
			logger.info("frontBean:\t"+frontBean);
		}
		return  frontBean;
	}
	
	public static synchronized ControllerManager createDefaultControllerHearder(String controllerType,
			String descipter) {

		if (logger.isDebugEnabled()) {
			logger.debug("Begin:");
			logger.debug("controllerId:\t" + controllerType);
			logger.debug("descipter:\t" + descipter);
		}
		if (!ClassHelper.isCapitalion(controllerType)) {
			if (logger.isWarnEnabled()) {
				logger.warn("Over: controllerType name error");
				logger.warn("controllerId:\t" + controllerType);
			}
			return null;
		}
		if (mapController.containsKey(controllerType)) {
			return (ControllerManager) mapController.get(controllerType);
		}
		ControllerManager controllerManager = new ControllerManager(controllerType, descipter);
		mapController.put(controllerType, controllerManager);
		if (logger.isDebugEnabled()) {
			logger.debug("Over:");
			logger.debug("ControllerManager:\t" + controllerManager.controllerBean);
		}
		return controllerManager;
	}

	/**
	 * 从XML配置中读取控制器组件的配置，解析为Freemarker易理解的模板，生成配置的模板信息
	 */
	public static void generateFrontTemplateToXML(String moduleName){
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager loadControllerServiceFromXML Begin:");
		}
		FrontPackage frontBean=loadFrontPackageFromXML();
		parseFrontPackage(frontBean);
		writeDaoPackageTempleteToXML(frontBean, moduleName);
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager loadControllerServiceFromXML Over.");
		}
		
	}
	
	private static void writeDaoPackageTempleteToXML(FrontPackage frontBean,String moduleName){
		//AtomOrRepositoryPackage atomOrRepositoryPackage=loadAtomServiceBeanFromXML();
		FrontTemplete moudleAtom=new FrontTemplete();
		moudleAtom.setFrontTemplates(frontBean.getControllerTemplates());
		String buinessServicePath=RunConfigure.getConfigPath() + "/controller-config-class-templete.xml";
		try {
			
			File file = new File(buinessServicePath);
			moudleAtom.setModuleName(moduleName);
			synchronized(BusinessServiceManager.class){
				jaxbUtilTemplate.objectToXmlFile(moudleAtom, file);
			}
			frontBean.getControllerTemplates().clear();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成业务服务模板，写入配置文件失败", e);
		}
		
		;
	}
	public static void parseFrontPackage(FrontPackage frontBean){
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager parseFrontPackage Begin:");
			logger.info("frontBean:\t"+frontBean);
		}
		Set<ControllerBean> controllerSet=frontBean.getControlleres();
		Assert.notNull(controllerSet,"ControllerBean controllerSet must not empty");
		ControllerBean[] controllerArray=controllerSet.toArray(new ControllerBean[0]);
		for(ControllerBean controllerBean:controllerArray){//解析具体的Cotroller
			parseControlerBean(controllerBean);
			if(StringUtils.isNotBlank(controllerBean.getBusinessDescripter().getClassName())){
				frontBean.addControllerTemplate(controllerBean.getBusinessDescripter());
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager parseFrontPackage Over");
		}
	}
	
	public static void parseControlerBean(ControllerBean controllerBean){
		if(logger.isDebugEnabled()){
			logger.debug("ControllerManager Begin:\t");
			logger.debug("ControllerBean:\t"+controllerBean);
		}
		Assert.notNull(controllerBean,"ControllerBean controllerBean must not null");
		//解析类注释
		String classComment=controllerBean.getControllerDescipter();
		if(!StringUtils.isBlank(classComment)){
			ProductParseTemplateUtil.parseClassComment(controllerBean.getBusinessDescripter(), classComment);
		}
		
		//解析类类型
		String controllerType=controllerBean.getControllerType();
		Assert.notNull(controllerType,"ControllerBean controllerType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("ControllerManager controllerType:\t"+controllerType);
		}
		ProductParseTemplateUtil.parseClassName(controllerBean.getBusinessDescripter(), controllerType);
		
		//解析类注解
		String controllerId=controllerBean.getControllerId();
		Assert.notNull(controllerId,"ControllerBean controllerId must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("ControllerManager controllerId:\t"+controllerId);
		}
		//控制器可以
		ProductParseTemplateUtil.parseHeaderAnnotation(RunConfigure.POSTCategory, controllerBean.getBusinessDescripter(), controllerId);

		//
		String uri=controllerBean.getUri();
		if (logger.isDebugEnabled()) {
			logger.debug("ControllerManager uri:\t"+uri);
		}
		//类头注解，对于Controller，则需要加上RequestMapping
		Set<AnnotationBean> annotationBeanSet=controllerBean.getAnnotationBeanList();
		Assert.notNull(annotationBeanSet,"ControllerBean annotationBeanSet must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("annotationBeanSet:\t"+annotationBeanSet);
		}
		ProductParseTemplateUtil.parseHeaderAnnotation(RunConfigure.POSTCategory, controllerBean.getBusinessDescripter(), annotationBeanSet);
		
		//
		ProductParseTemplateUtil.parseComponentCategory(controllerBean.getBusinessDescripter(), MethCategoryEnum.POST);
		
		Set<DomainBean> domainBeanSet=controllerBean.getIncludeDomainBeanList();
		Assert.isNull(domainBeanSet, WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_DOMAIN_NO_REALIZE);
		
		//包含的实体Bean
		Set<TableBean> tableBeanSet=controllerBean.getIncludeBeanList();
		Assert.notNull(tableBeanSet,"ControllerBean tableBeanSet must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("tableBeanSet:\t"+tableBeanSet);
		}
		ProductParseTemplateUtil.parseClassTableBeanImport(controllerBean.getBusinessDescripter(), tableBeanSet);

		
		ServiceBean serviceBean=controllerBean.getIncludeService();
		Assert.notNull(serviceBean,"ControllerBean serviceBean must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("serviceBean:\t"+serviceBean);
		}
		ProductParseTemplateUtil.parseClassServiceImport(controllerBean.getBusinessDescripter(), serviceBean);
		
		//解析方法，在Controller中，仅仅是一个类的调用
		Set<MethodBean> methodBeanSet=controllerBean.getControllerMethodList();
		MethodBean[] methodBeanArray=methodBeanSet.toArray(new MethodBean[0]);
		for(MethodBean methodBean:methodBeanArray){
			MethodManager.parseMethod(methodBean,controllerBean.getBusinessDescripter().getClassName(),
					controllerBean.getControllerCategory());
			MethodDescripter methodDescripter=(MethodDescripter) methodBean.getMethodDescripter().clone();
			BusinessDescripter businessDescripter=controllerBean.getBusinessDescripter();
			businessDescripter.addMethodDescripter(methodDescripter);
			// 方法内部，也就是方法体的处理，方法体不是方法签名的描述。
			ServiceBean calleeMajorService = methodBean.getCalleeMajorService();
			if (logger.isDebugEnabled()) {
				logger.debug("BusinessService calleeMajorService:\t" + calleeMajorService);
			}
			
		}
		if(logger.isDebugEnabled()){
			logger.debug("ControllerManager Over");
		}
	}
	/**
	 * 1.1.处理请求：
	 * 	RequestMethod.GET+@RequestBody @ModelAttribute
	 * 
	 * @RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	 *	public @ResponseBody RestResponse getShoppingCart(
	 *		@RequestBody @ModelAttribute("shoppingCartDomain") 
	 *	ShoppingCartDomain shoppingCartDomain)
	 *
	 * @param controllerType 这里的类型为实体类型，控制器类型作为默认方法签名为传入参数加上+Controller
	 * @param uri
	 * @param descripter
	 * @param methodSignatureDescripter
	 * @param startColumnBean 方法中需要传入到业务服务方法的参数，
	 * 							此时ModelAttribute为实体类型，即controllerType
	 * 							具体参数为TableBean tableNameNoDash为变量名称controllerType
	 * 							tableNameCapitalized为实体类型，也就是传入的参数controllerType
	 * @return
	 * 
	 * 示例：
	 *	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	 *	public @ResponseBody RestResponse getShoppingCart(
	 *		@RequestBody @ModelAttribute("shoppingCartDomain") 
	 *	ShoppingCartDomain shoppingCartDomain)
	 *
	 */
	public static ControllerManager addControllerMethodGetModelAttribute(String controllerType, String uri,
			String descripter,  String methodSignatureDescripter,
			ColumnBean startColumnBean,FieldTypeEnum returnFieldEnum) {
		String methodSignature=ClassHelper.namingUsingJavaMethod(controllerType);
		return addControllerMethod(controllerType, uri, descripter,methodSignature,methodSignature, methodSignatureDescripter,
				startColumnBean, defualtCalleeAnnotationBean,MethCategoryEnum.GET,returnFieldEnum);
	}
	
	/**
	 * 1.2.处理请求：
	 * 		RequestMethod.GET+@RequestBody @ModelAttribute
	 * 
	 * @param controllerType 这里的类型为实体类型，控制器类型作为默认方法签名为传入参数加上+Controller
	 * @param uri
	 * @param descripter
	 * @param methodSignature 控制器和业务服务使用同一个签名
	 * @param methodSignatureDescripter
	 * @param startColumnBean
	 * @return
	 * 
	 * 	示例：
	 *	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	 *	public @ResponseBody RestResponse getShoppingCart(
	 *		@RequestBody @ModelAttribute("shoppingCartDomain") 
	 *	ShoppingCartDomain shoppingCartDomain)
	 *
	 */
	public static ControllerManager addControllerMethodGetModelAttribute(String controllerType, String uri,
			String descripter, String methodSignature, String methodSignatureDescripter,
			ColumnBean startColumnBean,FieldTypeEnum returnFieldEnum) {
		return addControllerMethod(controllerType, uri, descripter,methodSignature,methodSignature, methodSignatureDescripter,
				startColumnBean, defualtCalleeAnnotationBean,MethCategoryEnum.GET,returnFieldEnum);
	}
	
	/**
	 * 1.3.处理请求：
	 * 	RequestMethod.GET+@RequestBody @ModelAttribute
	 * 
	 * @param controllerType
	 * @param uri
	 * @param descripter
	 * @param methodSignature 控制器签名
	 * @param servicemethodSignature
	 * @param methodSignatureDescripter
	 * @param startColumnBean 参数签名为传入业务服务的方法传入值
	 * @return
	 * 
	 * 	示例：
	 *	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	 *	public @ResponseBody RestResponse getShoppingCart(
	 *		@RequestBody @ModelAttribute("shoppingCartDomain") 
	 *	ShoppingCartDomain shoppingCartDomain)
	 *
	 */
	public static ControllerManager addControllerMethodGetModelAttribute(String controllerType, String uri,
			String descripter, String methodSignature,String servicemethodSignature, String methodSignatureDescripter,
			ColumnBean startColumnBean,FieldTypeEnum returnFieldEnum) {
		return addControllerMethod(controllerType, uri, descripter,methodSignature,servicemethodSignature, methodSignatureDescripter,
				startColumnBean, defualtCalleeAnnotationBean,MethCategoryEnum.GET,returnFieldEnum);
	}
	
	/**
	 * 2.1	RequestMethod.POST+@RequestBody
	 *	@RequestMapping(value = "/addcart", method = RequestMethod.POST)
	 *	public @ResponseBody RestResponse addProductToShoppingCart(
	 *		@RequestBody String cartDomail
     *
	 * @param controllerType 参数中传入的参数类型为实体类型，控制器类签名加Controller
	 * @param uri 对外提供的url签名方式
	 * @param descripter 类功能描述
	 * @param methodSignatureDescripter 方法签名描述
	 * @param startColumnBean controllerType 参数列表，实体类型为转移RequestBody String转移对象。
	 * @return
	 */
	public static ControllerManager addControllerMethodPostRequestBody(String controllerType, String uri,
			String descripter,  String methodSignatureDescripter,
			ColumnBean startColumnBean,FieldTypeEnum returnFieldEnum) {
		String methodSignature=ClassHelper.namingUsingJavaMethod(controllerType);
		return addControllerMethod(controllerType, uri, descripter,methodSignature,methodSignature, methodSignatureDescripter,
				startColumnBean,defualtCalleeAnnotationBean, MethCategoryEnum.POST,returnFieldEnum);
	}
	
	/**
	 * 2.2	RequestMethod.POST+@RequestBody
	 *	@RequestMapping(value = "/addcart", method = RequestMethod.POST)
	 *	public @ResponseBody RestResponse addProductToShoppingCart(
	 *		@RequestBody String cartDomail
	 *
	 * @param controllerType
	 * @param uri
	 * @param descripter
	 * @param methodSignature 控制器方法签名和业务服务签名一致
	 * @param methodSignatureDescripter
	 * @param startColumnBean
	 * @return
	 */
	public static ControllerManager addControllerMethodPostRequestBody(String controllerType, String uri,
			String descripter, String methodSignature, String methodSignatureDescripter,
			ColumnBean startColumnBean,FieldTypeEnum returnFieldEnum) {
		return addControllerMethod(controllerType, uri, descripter,methodSignature,methodSignature, methodSignatureDescripter,
				startColumnBean, defualtCalleeAnnotationBean,MethCategoryEnum.POST,returnFieldEnum);
	}
	
	/**
	 * 
	 * 2.3	RequestMethod.POST+@RequestBody
	 *	@RequestMapping(value = "/addcart", method = RequestMethod.POST)
	 *	public @ResponseBody RestResponse addProductToShoppingCart(
	 *		@RequestBody String cartDomail
	 *
	 * @param controllerType
	 * @param uri
	 * @param descripter
	 * @param methodSignature 控制器方法签名
	 * @param servicemethodSignature 业务服务方法签名
	 * @param methodSignatureDescripter
	 * @param startColumnBean 为
	 * @return
	 */
	public static ControllerManager addControllerMethodPostRequestBody(String controllerType, String uri,
			String descripter, String methodSignature,String servicemethodSignature, String methodSignatureDescripter,
			ColumnBean startColumnBean,FieldTypeEnum returnFieldEnum) {
		return addControllerMethod(controllerType, uri, descripter,methodSignature,servicemethodSignature, methodSignatureDescripter,
				startColumnBean, defualtCalleeAnnotationBean,MethCategoryEnum.POST,returnFieldEnum);
	}
	
	/**
	 * 	3.RequestMethod.GET+ PathVariable
	 *	@RequestMapping(value = "/usercarts/{sessionId}", method = RequestMethod.GET)
	 *	public RestResponse showUserCart(@PathVariable String sessionId)

	 * @param controllerType 实体类型，控制器类签名为实体加Controller
	 * @param uri 对外提供的url签名方式
	 * @param descripter
	 * @param methodSignature 控制器方法签名
	 * @param servicemethodSignature 业务服务方法签名
	 * @param methodSignatureDescripter
	 * @param startColumnBean 参数列表
	 * @return
	 */
	public static ControllerManager addControllerMethodGetPathVariable(String controllerType, String uri,
			String descripter, String methodSignature,String servicemethodSignature, String methodSignatureDescripter,
			ColumnBean startColumnBean ,FieldTypeEnum returnFieldEnum) {
		return addControllerMethod(controllerType, uri, descripter,methodSignature,servicemethodSignature, methodSignatureDescripter,
				startColumnBean, defualtCalleeAnnotationBean,MethCategoryEnum.GETPATH,returnFieldEnum);
	}

	/**
	 * 1.RequestMethod.GET+@RequestBody @ModelAttribute
	 * @RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	 * public @ResponseBody RestResponse getShoppingCart(
	 * @RequestBody @ModelAttribute("shoppingCartDomain") ShoppingCartDomain
	 *              shoppingCartDomain)
	 *
	 *  2.RequestMethod.POST+@RequestBody
	 *	@RequestMapping(value = "/addcart", method = RequestMethod.POST)
	 *	public @ResponseBody RestResponse addProductToShoppingCart(
	 *		@RequestBody String cartDomail
	 *
	 *	3.支持签名RequestMethod.GET+ PathVariable
	 *	@RequestMapping(value = "/usercarts/{sessionId}", method = RequestMethod.GET)
	 *	public RestResponse showUserCart(@PathVariable String sessionId)
	 *
	 * @param controllerType
	 * @param descripter
	 * @param methodSignature
	 * @param methodSignatureDescripter
	 * @return
	 */
	private static ControllerManager addControllerMethod(String controllerType, String uri, String descripter,
			String methodSignature,String servicemethodSignature, String methodSignatureDescripter,
			ColumnBean startColumnBean,AnnotationBean calleeServiceAnnotationBean,MethCategoryEnum methodCategory,FieldTypeEnum returnFieldEnum) {

		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager addControllerMethod Begin:");
			logger.info("controllerId:\t" + controllerType);
			logger.info("descipter:\t" + descripter);
			logger.info("methodSignature:\t" + methodSignature);
			logger.info("servicemethodSignature:\t" + servicemethodSignature);
			logger.info("methodCategory:\t" + methodCategory);
			logger.info("startColumnBean:\t" + startColumnBean);
			logger.info("methodSignatureDescripter:\t" + methodSignatureDescripter);
		}
		if (!ClassHelper.isCapitalion(controllerType) || 
				StringUtils.isBlank(servicemethodSignature)||StringUtils.isBlank(methodSignature)) {
			if (logger.isWarnEnabled()) {
				logger.warn("ControllerManager addControllerMethod Over: controllerType or toServiceMethodSignature is null");
			}
			return null;
		}

		ControllerManager manager = ControllerManager.createDefaultControllerHearder(controllerType, descripter);
		
		String serviceId = ClassHelper.namingUsingJavaMethod(controllerType);
		// Controller方法
		// 调用服务方法的参数
		TableBean entityBean = new TableBean();
		entityBean.setTableNameNoDash(serviceId);
		entityBean.setTableNameCapitalized(controllerType);
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
		// 调用业务服务的方法
		ServiceBean toServiceBean = (ServiceBean) manager.getIncludeService().clone();
		MethodManager methodManager = null;
		if (methodCategory == MethCategoryEnum.GET) {
			methodManager = MethodManager.createControllerMethodGetModelAttribute(methodSignature, uri,
					methodSignatureDescripter, entityBean);
		} else if (methodCategory == MethCategoryEnum.POST) {
			methodManager = MethodManager.createControllerMethodPost(methodSignature, uri, methodSignatureDescripter,
					entityBean);
		} else if (methodCategory == MethCategoryEnum.GETPATH) {
			methodManager=MethodManager.createControllerMothodGetPathVariable(methodSignature,uri,methodSignatureDescripter,
			 entityBean);
		} else {
			if (logger.isWarnEnabled()) {
				logger.info("ControllerManager addControllerMethod Over: methodManager MethCategoryEnum is error is");
			}
			return null;

		}
		// 调用服务方法的返回类型,此处定义为TableBean更为合适
		ColumnBean responseType = new ColumnBean();
		responseType.setColumnType(controllerType);
		responseType.setFieldType(returnFieldEnum);
		methodManager.setAssignList(entityBean);
		methodManager.setCallBusinessServiceMethod(toServiceBean, servicemethodSignature, responseType, entityBean,MethodRuntimeEnum.CALL_OUTER);
		methodManager.addCallBusinessService(toServiceBean);
		
		methodManager.setBodyBeanCatetory(MethodRuntimeEnum.CALL_OUTER);
		methodManager.setCalleeMajorService(toServiceBean);
		manager.addControllerMethod(methodManager.getMethodBean());
		
		if (logger.isInfoEnabled()) {
			logger.info("ControllerManager addControllerMethod Over:");
			logger.info("manager.controllerBean:\t" + manager.controllerBean);

		}
		return manager;
	}

	public void setUri(String uri){
		controllerBean.setUri(uri);
	}
	/**
	 * 
	 * @param controllerId
	 *            控制器标示，如果不为空，则标记@RequestMapping("/uri")
	 * @param controllerDescipter
	 *            控制器说明
	 */
	protected void setControllerBaseInfo(String controllerId, String controllerType, String controllerDescipter) {
		if (StringUtils.isBlank(controllerId) || StringUtils.isBlank(controllerDescipter)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		//删除空格
		controllerId=StringUtils.trim(controllerId);
		controllerType=StringUtils.trim(controllerType);
		
		controllerType=controllerType+RunConfigure.getControllerSuffix();
		controllerId=controllerId+RunConfigure.getControllerSuffix();
		
		controllerBean.setControllerCategory(MethCategoryEnum.POST);
		controllerBean.setControllerId(controllerId  );
		controllerBean.setControllerDescipter(controllerDescipter);
		controllerBean.setControllerType(controllerType);
	}

	public MethCategoryEnum getControllerCategory() {
		return controllerBean.getControllerCategory();
	}

	protected void setControllerCategory(MethCategoryEnum controllerCategory) {
		controllerBean.setControllerCategory(controllerCategory);
	}
	
	protected void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		if (CollectionUtils.isEmpty(annotationBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.setAnnotationBeanList(annotationBeanList);
	}

	/**
	 * 
	 * @param requestMapping
	 * @Controller @RequestMapping({"/order"})
	 */
	protected void setBaseAnnotationBean(String requestMapping) {
		AnnotationBean annotationBean = new AnnotationBean();
		//annotationBean.setAnnnoteKey("Controller");
		annotationBean.setAnnnoteKey(AnnotationTypeEnum.Controller);
		
		addAnnotationBean(annotationBean);
		annotationBean = new AnnotationBean();
		//annotationBean.setAnnnoteKey("RequestMapping");
		annotationBean.setAnnnoteKey(AnnotationTypeEnum.RequestMapping);
		annotationBean.setAnnoteValue(requestMapping);
		addAnnotationBean(annotationBean);
	}

	protected void addAnnotationBean(AnnotationBean annotationBean) {
		if (null == annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.addAnnotationBean(annotationBean);
	}

	public Set<AnnotationBean> getInstanceAnnotationBeanList() {
		return controllerBean.getInstanceAnnotationBeanList();
	}

	protected void setInstanceAnnotationBeanList(Set<AnnotationBean> instanceAnnotationBeanList) {
		if (null==instanceAnnotationBeanList) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.setInstanceAnnotationBeanList(instanceAnnotationBeanList);
	}
	
	protected void addInstanceAnnotationBean(AnnotationBean annotationBean) {
		if (null==annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.addInstanceAnnotationBean(annotationBean);
	}
	
	protected void setControllerMethodList(Set<MethodBean> controllerMethodList) {
		if (CollectionUtils.isEmpty(controllerMethodList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.setControllerMethodList(controllerMethodList);
	}

	protected void addControllerMethod(MethodBean controllerMethod) {
		if (null == controllerMethod) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.addControllerMethodBean(controllerMethod);

		Set<DomainBean> domainBeanList = controllerMethod.getAssignDomainList();
		if (!CollectionUtils.isEmpty(domainBeanList)) {
			controllerBean.addIncludeDomainBean(domainBeanList);
		}
		TableBean entityBeanList = controllerMethod.getAssignList();
		if (null!=entityBeanList) {
			controllerBean.addInculdeEntityBean(entityBeanList);
		}

	}

	public ServiceBean getIncludeService() {
		return controllerBean.getIncludeService();
	}

	protected void setIncludeService(ServiceBean includeService) {
		if (null == includeService) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.setIncludeService(includeService);
	}

	protected void setIncludeBeanList(Set<TableBean> includeBeanList) {
		if (CollectionUtils.isEmpty(includeBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.setIncludeBeanList(includeBeanList);
	}

	protected void addIncludeEntityBean(TableBean includeBean) {
		if (null == includeBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.addInculdeEntityBean(includeBean);
	}

	protected void setIncludeDomainBeanList(Set<DomainBean> includeDomainBeanList) {
		if (CollectionUtils.isEmpty(includeDomainBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.setIncludeDomainBeanList(includeDomainBeanList);
	}

	protected void addIncludeDomainBean(DomainBean domainBean) {
		if (null == domainBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_CONTROLLER_NO_EXIST_FILED);
		}
		controllerBean.addIncludeDomainBean(domainBean);
	}

	private static Set<ControllerBean> getControllerBean(){
		Set<ControllerBean> controllerList=new HashSet<ControllerBean>();
		if(mapController.isEmpty()){
			if (logger.isWarnEnabled()) {
				logger.warn("ControllerManager getControllerBean:");
				logger.warn("mapController must not Empty");
			}
			throw new java.lang.RuntimeException("生成控制器模板时，控制器数量为零");
		}
		//
		Collection<ControllerManager> collection=mapController.values();
	    for(ControllerManager controllerManager:collection){
	    	controllerList.add(controllerManager.controllerBean);
	    }
	    return controllerList;
	}
	
	

	private ControllerManager(String controllerType, String descipter) {
		controllerBean = new ControllerBean();
		defualtCalleeAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Autowired);
		// 类型大写
		String serivceType = controllerType;
		// Controller备注信息
		String controllerId = ClassHelper.namingUsingJavaMethod(controllerType);
		//String serviceId = controllerId;
		
		setUri(controllerId);
		String controllerDescipter = "Controller Type:" + controllerType + "\n " + descipter;
		//controllerType=controllerType+RunConfigure.getControllerSuffix();
		// 小写
		
		setControllerBaseInfo(controllerId, controllerType, controllerDescipter);
		//头部信息
		setBaseAnnotationBean(controllerId);

		String serviceDescipter = "Service Type:" + controllerType + "\n " + descipter;

		TableBean tableBean = new TableBean();
		// 命名,小写
		tableBean.setTableNameNoDash(controllerId);
		// 类型，大写
		tableBean.setTableNameCapitalized(controllerType);
		addIncludeEntityBean(tableBean);
		
		BusinessServiceManager businessServiceManager = BusinessServiceManager.
				createDefaultBusinessServiceManager(serivceType, serviceDescipter);
		
		setIncludeService(businessServiceManager.getServiceBean());
		
		defualtCalleeAnnotationBean.setAnnoteType(businessServiceManager.getServiceBean().getSerivceType());
		defualtCalleeAnnotationBean.setAnnoteValue(businessServiceManager.getServiceBean().getServiceId());
		addInstanceAnnotationBean(defualtCalleeAnnotationBean);		

	}
}
