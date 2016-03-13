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
import com.ndl.framework.workbrench.define.DaoBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodDescripter;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.ServicePackage;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.Generate;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.freemarker.template.AtomOrRepositorTemplate;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.JAXBUtil;
import com.ndl.framework.workbrench.util.ProductParseTemplateUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

public class DaoManager {
	private static final Logger logger = LoggerFactory.getLogger(DaoManager.class);
	//private static final AnnotationBean defualtCalleeAnnotationBean=new AnnotationBean();

	//private static final RunConfigure runConfigure=new RunConfigure();
	private static final JAXBUtil jaxbUtilDefined = new JAXBUtil(AtomOrRepositoryPackage.class);
	private static final JAXBUtil jaxbUtilTemplate = new JAXBUtil(AtomOrRepositorTemplate.class);
	private static Map<String, DaoManager> mapAtomServiceManager = new 
			java.util.concurrent.ConcurrentHashMap<String, DaoManager>();

	private static String DAO_SUFFIX = RunConfigure.getDaoSuffix();
	
	private final DaoBean daoBean;
	
	private DaoManager(String daoType,String daoDescipter,MethCategoryEnum methodCategory){
		daoBean=new DaoBean();
		//defualtCalleeAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Autowired);

		String daoId = ClassHelper.namingUsingJavaMethod(daoType);
		
		//默认是JPA方式
		setDaoCategory(methodCategory);
		setDaoInfo(daoId,daoType,daoDescipter);
		TableBean tableBean = new TableBean();
		// 命名,小写
		tableBean.setTableNameNoDash(daoId);
		// 类型，大写
		tableBean.setTableNameCapitalized(daoType);
		addIncludeEntityBean(tableBean);
	}

	/**
	 * 装载配置组件配置XML，解析ServicePackage为易识别的BusinessDescripter，写入FreeMarker的模板文件
	 * @param bnsPakageName
	 */
	public static void generateDaoTemplateToXML(String bnsPakageName){
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadAtomServiceFromXML Begin:");
		}
		AtomOrRepositoryPackage servicePackage = loadDaoPackageFromXML();
		
		parseRepositoryPackage(servicePackage);
		
		writeRepositoryTempleteToXML(servicePackage,bnsPakageName);
		if (logger.isInfoEnabled()) {
			logger.info("BusinessServiceManager loadAtomServiceFromXML Over.");
		}
	}
	
	public static void parseRepositoryPackage(AtomOrRepositoryPackage servicePackage){
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager parseRepositoryManagerFromXML Begin:");
		}
		
		String packageName = servicePackage.getAtomOrRepository();
		logger.info("DaoManager parseRepositoryPackage:\t" + packageName);
		
		Set<DaoBean> repositorise = servicePackage.getRepositories();
		Assert.notNull(repositorise, "repositorise must not empty");
		for (DaoBean daoBean : repositorise) {
			paraseRepository(daoBean);
			if(StringUtils.isNotBlank(daoBean.getBusinessDescripter().getClassName())){
				servicePackage.addRepositoryTemplate(daoBean.getBusinessDescripter());
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager parseRepositoryManagerFromXML Over:");
		}
	}
	
	public static void paraseRepository(DaoBean daoBean){
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager paraseRepository Begin:");
			logger.debug("daoBean:\t" + daoBean);
		}
		//Dao的类型
		String daoType=daoBean.getDaoType();
		Assert.notNull(daoType, "daoType must not null");
		logger.debug("Dao daoType:\t" + daoType);
		
		ProductParseTemplateUtil.parseClassName(daoBean.getBusinessDescripter(), daoType);
		
		//注解的名称
		String daoId=daoBean.getDaoId();
		Assert.notNull(daoId, "daoId must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("Dao daoId:\t" + daoId);
		}
		ProductParseTemplateUtil.parseHeaderAnnotation(daoBean.getDaoCategory(), daoBean.getBusinessDescripter(), daoId);
		
		String comment=daoBean.getDaoDescipter();
		ProductParseTemplateUtil.parseClassComment(daoBean.getBusinessDescripter(), comment);
		
		//增加实体Bean的Import，此时为includeClasses
		Set<TableBean> includeClasses= daoBean.getIncludeBeanList();
		ProductParseTemplateUtil.parseClassTableBeanImport(daoBean.getBusinessDescripter(), includeClasses);
		//类头注解，对于Controller，则需要加上RequestMapping
		Set<AnnotationBean> annotationBeanSet=daoBean.getAnnotationBeanList();
		Assert.notNull(annotationBeanSet, "annotationBeanSet is null,now");
		ProductParseTemplateUtil.parseHeaderAnnotation(daoBean.getDaoCategory(), daoBean.getBusinessDescripter(), annotationBeanSet);
		
		ProductParseTemplateUtil.parseComponentCategory(daoBean.getBusinessDescripter(), daoBean.getDaoCategory());
		
		//Domain方式暂时不支持
		Set<DomainBean> domainBeans =daoBean.getIncludeDomainBeanList();
		Assert.isNull(domainBeans, WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_DOMAIN_NO_REALIZE);
		
		//解析其引用实例的注解，但是在DAO不引入其它DAO，包括缓存、非关系数据库等，这些功能在原子服务中实现，或是利用第三方组件本身的实现
		Set<MethodBean>  methodBeans= daoBean.getDaoMethodList();
		Assert.notNull(methodBeans, "domainBeanSet is null,now");
		for(MethodBean methodBean:methodBeans){
			MethodManager.parseMethod(methodBean,daoBean.getBusinessDescripter().getClassName(),
					daoBean.getDaoCategory());
			MethodDescripter methodDescripter=(MethodDescripter) methodBean.getMethodDescripter().clone();
			BusinessDescripter businessDescripter=daoBean.getBusinessDescripter();
			businessDescripter.addMethodDescripter(methodDescripter);
			// 方法内部，也就是方法体的处理，方法体不是方法签名的描述。
			 DaoBean calleeMajorDaoService = methodBean.getCallMajorDAOService();
			if (logger.isDebugEnabled()) {
				logger.debug("BusinessService calleeMajorDaoService:\t" + calleeMajorDaoService);
			}
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager paraseRepository Over.");
		}
	}
	
	public void generateDaoBeanToXML(String daoPackageName) {
		String daoPath=RunConfigure.getConfigPath() + "/repository-service.xml";
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager generateDaoBeanToXML Begin:");
			logger.info("daoPath:\t"+daoPath);
		}
		Set<DaoBean> list=getBusinessBean();
		if (logger.isInfoEnabled()) {
			logger.info("controll size:\t"+list.size());
		}
		try {
			File file = new File(daoPath);
			AtomOrRepositoryPackage servicePackage=new AtomOrRepositoryPackage();
			servicePackage.setAtomOrRepository(daoPackageName);
			servicePackage.setRepositories(list);
			synchronized(DaoManager.class){
				jaxbUtilDefined.objectToXmlFile(servicePackage, file);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成DAO模板，写入配置文件失败", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager generateDaoBeanToXML Over:");
			logger.info("daoPath:\t"+daoPath);
		}
	}
	
	public static AtomOrRepositoryPackage loadDaoPackageFromXML() {
		String daoPath=RunConfigure.getConfigPath() + "/repository-service.xml";
		
		AtomOrRepositoryPackage servicePackage=null;
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager loadBusinessServiceBeanFromXML Begin:");
			logger.info("daoPath:\t"+daoPath);
		}
		
		try {
			File file = new File(daoPath);
			synchronized(DaoManager.class){
				servicePackage= jaxbUtilDefined.xmlToObject(file,AtomOrRepositoryPackage.class);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("装载DAO模板，写入配置文件失败", e);
		}
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager loadDaoBeanFromXML Over:");
			logger.info("servicePackage:\t"+servicePackage);
		}
		return servicePackage;
	}
	
	private static void writeRepositoryTempleteToXML(AtomOrRepositoryPackage atomOrRepositoryPackage,String moduleName){
		//AtomOrRepositoryPackage atomOrRepositoryPackage=loadAtomServiceBeanFromXML();
		AtomOrRepositorTemplate moudleAtom=new AtomOrRepositorTemplate();
		Set<BusinessDescripter> set=atomOrRepositoryPackage.getRepositoryTemplates();
		moudleAtom.setRepositoryTemplates(set);
		String buinessServicePath=RunConfigure.getConfigPath() + "/repository-service-class-templete.xml";
		try {
			
			File file = new File(buinessServicePath);
			moudleAtom.setModuleName(moduleName);
			synchronized(BusinessServiceManager.class){
				jaxbUtilTemplate.objectToXmlFile(moudleAtom, file);
			}
			atomOrRepositoryPackage.getRepositoryTemplates().clear();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("生成业务服务模板，写入配置文件失败", e);
		}
		
	}
	////////////////////////////////////////////////////////
	
	private static synchronized DaoManager createDefaultDaoManager(String daoType,
			String daoDescripter,AnnotationBean daoAnnotationBean,MethCategoryEnum methodCategory){
		
		if (logger.isInfoEnabled()) {
			logger.debug("DaoManager createDefaultAtomServiceManager Begin:");
			logger.debug("serviceType:\t" + daoType);
			logger.debug("descipter:\t" + daoDescripter);
		}
		//服务类型不能为空
		Assert.notNull(daoType, "serviceType must not be null");
		if (!ClassHelper.isCapitalion(daoType)) {
			if (logger.isWarnEnabled()) {
				logger.warn("DaoManager createDefaultDaoManager Over: serviceType name error");
				logger.warn("serviceType:\t" + daoType);
			}
			return null;
		}

		if (mapAtomServiceManager.containsKey(daoType)) {
			return (DaoManager) mapAtomServiceManager.get(daoType);
		}
		DaoManager daoManager = new DaoManager(daoType, daoDescripter,methodCategory);
		if(null==daoAnnotationBean){
			AnnotationBean annotationBean=new AnnotationBean();
			annotationBean.setAnnoteValue(daoManager.daoBean.getDaoId());
			annotationBean.setAnnnoteKey(AnnotationTypeEnum.Repository);
			daoManager.addAnnotationBean(annotationBean);
		}else{
			if(StringUtils.isBlank(daoAnnotationBean.getAnnoteValue())){
				daoAnnotationBean.setAnnoteValue(daoManager.daoBean.getDaoId());
			}
			if(StringUtils.isBlank(daoAnnotationBean.getAnnoteType())){
				daoAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Repository);
			}
			daoAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Repository);

			daoManager.addAnnotationBean(daoAnnotationBean);
		}
		mapAtomServiceManager.put(daoType, daoManager);
		if (logger.isDebugEnabled()) {
			logger.debug("Over:");
			logger.debug("DaoManager createDefaultDaoManager:\t" + daoManager.daoBean);
		}
		return daoManager;
	}
	
	public static synchronized DaoManager createDefaultDaoManager(String daoType,
			String daoDescripter,AnnotationBean daoAnnotationBean){
		return createDefaultDaoManager(daoType,daoDescripter,daoAnnotationBean,MethCategoryEnum.JPA);
	}
	
	public static synchronized DaoManager createDefaultDaoManager(String daoType,
			String daoDescripter){
		AnnotationBean daoAnnotationBean=new AnnotationBean();
		daoAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Repository);
		return createDefaultDaoManager(daoType,daoDescripter,daoAnnotationBean,MethCategoryEnum.JPA);
	}
	
	public static synchronized DaoManager createDefaultDaoManager(String daoType,
			String daoDescripter,MethCategoryEnum methodCategory){
		AnnotationBean daoAnnotationBean=new AnnotationBean();
		daoAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Repository);
		return createDefaultDaoManager(daoType,daoDescripter,daoAnnotationBean,methodCategory);
	}
	
	public static DaoManager addDaoMethod(String daoType, String descripter,
			String methodSQLKey, String methodSignature,String methodSignatureDescripter,
			ColumnBean startColumnBean,MethCategoryEnum methodCategory,FieldTypeEnum returnFieldEnum){
		return addDaoMethod( daoType,  descripter,null,
				 methodSQLKey,  methodSignature, methodSignatureDescripter,
				 startColumnBean, methodCategory, returnFieldEnum);
	}
	
	public static DaoManager addDaoMethod(String daoType, String descripter,AnnotationBean daoTypeAnnotation,
			String methodSQLKey, String methodSignature,String methodSignatureDescripter,
			ColumnBean startColumnBean,MethCategoryEnum methodCategory,FieldTypeEnum returnFieldEnum) {

		if (logger.isInfoEnabled()) {
			logger.info("DaoManager addDaoMethod Begin:");
			logger.info("daoType:\t" + daoType);
			logger.info("descipter:\t" + descripter);
			logger.info("methodSignature:\t" + methodSignature);
			logger.info("daoTypeAnnotation:\t" + daoTypeAnnotation);
			logger.info("methodCategory:\t" + methodCategory);
			logger.info("startColumnBean:\t" + startColumnBean);
			logger.info("methodSQLKey:\t" + methodSQLKey);
			logger.info("methodSignatureDescripter:\t" + methodSignatureDescripter);
		}
		if (!ClassHelper.isCapitalion(daoType) ||StringUtils.isBlank(methodSignature)) {
			if (logger.isWarnEnabled()) {
				logger.warn("DaoManager addDaoMethod Over: daoType or methodSignature is null");
			}
			return null;
		}
		if(methodCategory!=MethCategoryEnum.JDBC&&methodCategory!=MethCategoryEnum.JPA&&methodCategory!=MethCategoryEnum.MBT){
			if (logger.isWarnEnabled()) {
				logger.warn("DaoManager addDaoMethod Over: methodCategory must JDBC/JPA/MBT");
			}
			return null;
		}
		DaoManager manager = DaoManager.createDefaultDaoManager(daoType, descripter,daoTypeAnnotation,methodCategory);
		/*
		if(null!=calleeServiceAnnotationBean){
			manager.addInstanceAnnotationBean(calleeServiceAnnotationBean);
		}else{
			manager.addInstanceAnnotationBean(defualtCalleeAnnotationBean);

		}
		*/
		String serviceId = ClassHelper.namingUsingJavaMethod(daoType);
		serviceId=StringUtils.trim(serviceId);
		Assert.notNull(daoType,"daoType must not null");
		// Controller方法
		// 调用服务方法的参数
		TableBean entityBean = new TableBean();
		entityBean.setTableNameNoDash(serviceId);
		entityBean.setTableNameCapitalized(daoType);
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
		DaoBean toServiceBean = (DaoBean) manager.getDaoBean().clone();

		MethodManager methodManager=MethodManager.createMothod(methodSignature, methodSQLKey,methodSignatureDescripter,
				 entityBean,methodCategory);
		// 调用服务方法的返回类型,此处定义为TableBean更为合适
		ColumnBean responseType = new ColumnBean();
		responseType.setColumnType(daoType);
		responseType.setFieldType(returnFieldEnum);
		methodManager.setAssignList(entityBean);
		methodManager.setBodyBeanCatetory(MethodRuntimeEnum.CALL_OUTER);
		manager.addDaoMethod(methodManager.getMethodBean());
		if (logger.isInfoEnabled()) {
			logger.info("DaoManager addControllerMethod Over:");
			logger.info("manager.daoBean:\t" + manager.daoBean);

		}
		return manager;
	}

	public static void parseCalleeMajorDAO(final BusinessDescripter businessDescripter,
			MethodDescripter methodDescripter,
			final DaoBean calleeMajorDao){
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager parseCalleeMajorDAO Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("calleeMajorDao:\t" + calleeMajorDao);
		}
		Assert.notNull(calleeMajorDao, "DaoManager parseCalleeMajorDAO calleeMajorDao must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager parseCalleeMajorDAO calleeMajorDao:\t" + calleeMajorDao);
		}
		String calleeMajorServiceType = calleeMajorDao.getDaoType();
		Assert.notNull(calleeMajorServiceType, "DaoManager parseCalleeMajorDAO calleeMajorServiceType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager parseCalleeMajorDAO calleeMajorServiceType:\t" + calleeMajorServiceType);
		}
		String calleeMajorServiceId = calleeMajorDao.getDaoId();
		Assert.notNull(calleeMajorServiceId, "DaoManager parseCalleeMajorDAO calleeMajorServiceId must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager parseCalleeMajorDAO calleeMajorServiceId:\t" + calleeMajorServiceId);
		}
		//方法体内调用的方法
		MethodBean calleeMajorRunningMethod = calleeMajorDao.getRunningMethod();

	 	MethodManager.parseMethodBody(methodDescripter,calleeMajorServiceId,calleeMajorRunningMethod);
		if (logger.isDebugEnabled()) {
			logger.debug("DaoManager parseCalleeMajorDAO Over");
		}
		
	}
	
	protected void setDaoInfo(String daoId,String daoType,String daoDescipter){
		if(StringUtils.isBlank(daoId)||StringUtils.isBlank(daoType)){
			throw new ConfigRuntimeException(WorkBrenchConfigProperty.
					PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoType=StringUtils.trim(daoType);
		daoId=StringUtils.trim(daoId);
		if(this.getDaoCategory()==null){
			daoType=daoType+DAO_SUFFIX;
			daoId=daoId+DAO_SUFFIX;
		}else{
			daoType=daoType+getDaoCategory().toString()+DAO_SUFFIX;
			daoId=daoId+getDaoCategory().toString()+DAO_SUFFIX;
		}

		daoBean.setDaoId(daoId);

		Assert.notNull(daoType,"daoType must not null");
		//默认JPA方式
		daoBean.setDaoType(daoType);
		daoBean.setDaoDescipter(daoDescipter);
	}
	
	protected void setDaoCategory(MethCategoryEnum daoCategory){
		if(daoCategory==MethCategoryEnum.MBT||
				daoCategory==MethCategoryEnum.JDBC){
			throw new ConfigRuntimeException(WorkBrenchConfigProperty.
					PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.setDaoCategory(daoCategory);
	}
	
	protected MethCategoryEnum getDaoCategory(){
		return daoBean.getDaoCategory();
	}
	
	protected void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		if (CollectionUtils.isEmpty(annotationBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.setAnnotationBeanList(annotationBeanList);
	}

	public Set<AnnotationBean> getInstanceAnnotationBeanList() {
		return daoBean.getInstanceAnnotationBeanList();
	}

	protected void setInstanceAnnotationBeanList(Set<AnnotationBean> instanceAnnotationBeanList) {
		if (null==instanceAnnotationBeanList) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.setInstanceAnnotationBeanList(instanceAnnotationBeanList);
	}
	
	protected void addInstanceAnnotationBean(AnnotationBean annotationBean) {
		if (null==annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.addInstanceAnnotationBean(annotationBean);
	}

	
	public DaoBean getDaoBean(){
		return this.daoBean;
	}
	
	protected void setRunningMethod(MethodBean runningMethod){
		if(null==runningMethod){
			throw new ConfigRuntimeException(WorkBrenchConfigProperty.
					PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.setRunningMethod(runningMethod);
	}
	
	public MethodBean getRunningMethod(){
		return daoBean.getRunningMethod();
	}
	
	protected void addAnnotationBean(AnnotationBean annotationBean) {
		if (null == annotationBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.addAnnotationBean(annotationBean);
	}

	protected void seDaoMethodList(Set<MethodBean> daoMethodList) {
		if (CollectionUtils.isEmpty(daoMethodList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.seDaoMethodList(daoMethodList);
	}

	protected void addDaoMethod(MethodBean daoMethod) {
		if (null == daoMethod) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.addDaoMethod(daoMethod);
		//
		Set<DomainBean> domainBeanList = daoMethod.getAssignDomainList();
		if (!CollectionUtils.isEmpty(domainBeanList)) {
			daoBean.addIncludeDomainBean(domainBeanList);
		}
		TableBean entityBeanList = daoMethod.getAssignList();
		if (null!=entityBeanList) {
			daoBean.addIncludeEntityBean(entityBeanList);
		}
	}
	
	protected void setIncludeBeanList(Set<TableBean> includeBeanList) {
		if (CollectionUtils.isEmpty(includeBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.setIncludeBeanList(includeBeanList);
	}

	protected void addIncludeEntityBean(TableBean tableBean){
		if (null == tableBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.addIncludeEntityBean(tableBean);
	}
	
	protected void setIncludeDomainBeanList(Set<DomainBean> includeDomainBeanList) {
		if (CollectionUtils.isEmpty(includeDomainBeanList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.setIncludeDomainBeanList(includeDomainBeanList);
	}

	protected void addIncludeDomainBean(DomainBean domainBean){
		if (null == domainBean) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_DAO_NO_EXIST_FILED);
		}
		daoBean.addIncludeDomainBean(domainBean);
	}
	
	private static Set<DaoBean> getBusinessBean(){
		Set<DaoBean> serviceBeanList=new HashSet<DaoBean>();
		if(mapAtomServiceManager.isEmpty()){
			if (logger.isWarnEnabled()) {
				logger.warn("DaoManager getControllerBean:");
				logger.warn("serviceBeanList must not Empty");
			}
			throw new java.lang.RuntimeException("生成控制器模板时，控制器数量为零");
		}
		//
		Collection<DaoManager> collection=mapAtomServiceManager.values();
	    for(DaoManager daoManager:collection){
	    	serviceBeanList.add(daoManager.daoBean);
	    }
	    return serviceBeanList;
	}
	

	public void buildDaoBean(){
		
	}
}
