package com.ndl.framework.workbrench.freemarker;

/**
 * setFitlerTable 设置过滤数据
 * setIsEntityFormDB(true)
 * loadModelData
 * 
 */
import freemarker.template.Template;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.define.TransientBean;
import com.ndl.framework.workbrench.freemarker.template.EntityConfigTemplate;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.FreemarkerUtil;
import com.ndl.framework.workbrench.util.JAXBUtil;
import com.ndl.framework.workbrench.util.ProductParseTemplateUtil;

import com.ndl.framework.workbrench.util.FileUtil;
import com.ndl.framework.workbrench.util.IOUtil;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Generate {

	private final static Logger logger = LoggerFactory.getLogger(Generate.class);
	private final static RunConfigure runConfigure = RunConfigure.instanceRunConfigure();
	private final static JAXBUtil jaxbUtilEntity = new JAXBUtil(EntityConfigTemplate.class);
	private final static JdbcTemplate jdbcTemplate = (JdbcTemplate) new ClassPathXmlApplicationContext(
			"applicationContext.xml").getBean("jdbcTemplate");

	// private final static String module_entity =RunConfigure.packageEntity;
	private final static String module_model = RunConfigure.packageModel;

	private final static String module_model_query = RunConfigure.packageModelQuery;

	private final static String module_service = RunConfigure.packageService;
	private final static String module_service_impl = RunConfigure.packageServiceImpl;

	private final static String module_dao = RunConfigure.packageDao;
	private final static String module_dao_impl = RunConfigure.packageDaoImpl;

	private static String PACKAGE_BASE = runConfigure.getModulePakage();

	private final static String CLASS_PREFIX = runConfigure.getClassPrefix();

	private final static String JDBC_TEMPLATE_NAME = "jdbcTemplate";

	private final static String SCHEMA_NAME = runConfigure.getSchemaName();
	
	private static boolean generateSimpleAndroidRestfulConfig=true;

	// 过滤表,只有这些表生成
	private String tablesFilter;
	// 去除不包括的字段
	private String columnsExclude;

	private Map<String, Object> varMap = new java.util.concurrent.ConcurrentHashMap<String, Object>();

	private Set<TableBean> tableBeanList;
	private Set<TransientBean> transientBeanSet;
	private Set<String> columnsExcludeList;
	// 输出目录
	private String outputDirs;

	public static Generate getInstance() {
		return new Generate();
	}

	protected Generate() {
		configDirs();
		configFreeMarK();
	}

	private void configDirs() {
		forceMkdir();
	}

	private void configFreeMarK() {
		varMap.put("schemaName", SCHEMA_NAME);
		varMap.put("packageBase", PACKAGE_BASE);
		varMap.put("classPrefix", CLASS_PREFIX);
		varMap.put("jdbcTemplateName", JDBC_TEMPLATE_NAME);
		varMap.put("jdbcTemplateNameCapitalized", StringUtils.capitalize(JDBC_TEMPLATE_NAME));
	}

	private volatile boolean isEntityFormDB = false;

	public void setIsEntityFormDB(boolean bEntityForm) {
		this.isEntityFormDB = bEntityForm;
	}

	public boolean isEntityFormDB() {
		return isEntityFormDB;
	}

	public void setColumnsExclude(String columnsExclude) {
		this.columnsExclude = columnsExclude;
	}

	public void setColumnsExcludeList(Set<String> columnsExcludeList) {
		this.columnsExcludeList = columnsExcludeList;
	}

	public void setFitlerTable(String filter) {
		this.tablesFilter = filter;
	}

	public void setTransientBeanSet(Set<TransientBean> transientBeanSet) {
		this.transientBeanSet = transientBeanSet;
	}

	/**
	 * 装载实体描述信息
	 * 
	 * @return
	 */
	public synchronized Set<TableBean> loadModelData() {
		// 装载数据
		if (isEntityFormDB) {
			loadTableDataFromDB();
			writeEntityConfigToFile(this.tableBeanList);
		} else {
			loadEntityConfigFromFile();
		}
		Set<TableBean> tableList = java.util.Collections.unmodifiableSet(tableBeanList);
		// 针对表中或者域模型中增加临时对象
		if (CollectionUtils.isNotEmpty(tableList) && CollectionUtils.isNotEmpty(transientBeanSet)) {
			for (TableBean tableBean : tableList) {

				// 过滤模块中都不包含的字段
				Set<ColumnBean> columnBeanList = tableBean.getColumnBeanList();
				if (CollectionUtils.isNotEmpty(columnBeanList)) {
					for (ColumnBean columnBean : columnBeanList) {
						String column = columnBean.getColumnDBType();
						String type = columnBean.getColumnNameNoDash();
						if ((StringUtils.isNoneBlank(column) && this.columnsExcludeList.contains(column))
								|| (StringUtils.isNoneBlank(type) && this.columnsExcludeList.contains(type))) {
							columnBean.setColumnType("");
							columnBean.setColumnDBType("");
							columnBean.setColumnJDBCType("");
							columnBean.setColumnName("");
							columnBean.setColumnNameNoDash("");
							columnBean.setColumnNameCapitalized("");
							columnBean.setColumnTypeDescriptionIOS("");
							columnBean.setColumnTypeIOS(null);
							columnBean.setColumnTypeJS(null);
							columnBean.setColumnTypePropertyIOS(null);
							columnBean.setColumnComment("");
							columnBean.setLength(0);
						}
					}
				}

			}
		}
		// this.
		return tableList;
	}

	private void loadTransientColumnBean(TableBean tableBean, String suffix) {

		Set<ColumnBean> transientColumnBeanList = new HashSet<ColumnBean>();
		// 增加非Entity或者Bean的对象
		if (CollectionUtils.isEmpty(transientBeanSet)) {
			return;
		}
		for (TransientBean transientBean : transientBeanSet) {
			if (tableBean.getTableName().equalsIgnoreCase(transientBean.getTableName()) || // 以表名方式判断是否相等
					tableBean.getTableNameCapitalized().equalsIgnoreCase(transientBean.getTableNameCapitalized())) {
				// 包括临时变量的
				Set<ColumnBean> transientColumnBeanSet = transientBean.getColumnBeanList();
				for (ColumnBean specialAssign : transientColumnBeanSet) {
					String columnType = specialAssign.getColumnType();
					/*
					 * LIST,//则为ArrayList<JavaBean> MAPKEY,//则为HashMap< objKey,
					 * JavaBean> ENTITY,//实体Bean DOMAIN,//域模型 Page,//实体分页
					 */
					if (specialAssign.getFieldType() == FieldTypeEnum.LIST
							|| specialAssign.getFieldType() == FieldTypeEnum.DOMAIN
							|| specialAssign.getFieldType() == FieldTypeEnum.ENTITY
							|| specialAssign.getFieldType() == FieldTypeEnum.MAPKEY) {
						columnType = columnType + suffix;
						specialAssign.setColumnType(columnType);
					}
					columnType = ProductParseTemplateUtil.parseInstanceValiable(specialAssign);

					ColumnBean transientColumnBean = (ColumnBean) specialAssign.clone();
					String columnNameCapitalized = specialAssign.getColumnNameCapitalized();
					String str = ClassHelper.namingUsingJavaClass(columnNameCapitalized);
					transientColumnBean.setColumnNameCapitalized(str);
					transientColumnBean.setColumnType(columnType);
					ProductParseTemplateUtil.parseTypeToIOS(transientColumnBean);
					transientColumnBeanList.add(transientColumnBean);
				}
			}
		}
		if (CollectionUtils.isNotEmpty(transientColumnBeanList)) {
			tableBean.setTransientColumnBeanList(transientColumnBeanList);
		}
	}

	/**
	 * 从数据库读取表数据形成Entity信息
	 */
	private void loadTableDataFromDB() {
		if (tableBeanList == null) {
			synchronized (this) {
				if (tableBeanList == null) {
					// 获取表数据
					tableBeanList = getTables();
					for (TableBean tableBean : tableBeanList) {
						tableBean.setColumnBeanList(getColumns(tableBean.getTableName(), tableBean));
					}
				}
			}
		}
	}

	/**
	 * 把模型配置信息写入XML文件
	 */
	public void writeEntityConfigToFile(final Set<TableBean> tableBeanList) {
		Set<TableBean> list=tableBeanList;
		EntityConfigTemplate entityConfig = new EntityConfigTemplate();
		entityConfig.setTableBeanList(list);
		try {
			File file = new File(RunConfigure.getConfigPath() + "/entity-config.xml");
			jaxbUtilEntity.objectToXmlFile(entityConfig, file);
		} catch (Exception e) {
			throw new java.lang.RuntimeException("生成实体模板，写入配置文件失败", e.getCause());
		}
	}

	/**
	 * 把模型配置信息写入XML文件
	 */
	private void loadEntityConfigFromFile() {
		if (tableBeanList == null) {
			synchronized (this) {
				if (tableBeanList == null) {
					// 获取表数据
					try {
						File file = new File(RunConfigure.getConfigPath() + "/entity-config.xml");
						EntityConfigTemplate entityConfig = jaxbUtilEntity.xmlToObject(file,
								EntityConfigTemplate.class);
						tableBeanList = entityConfig.getTableBeanList();
					} catch (Exception e) {
						// e.printStackTrace();
						throw new java.lang.RuntimeException("生成实体模板，写入配置文件失败", e);
					}
				}
			}
		}

	}

	/////////////////////////////////////////////////////
	public synchronized void generateIOSModel() {
		setFreemarkerModel("IOSPrefix", runConfigure.getIOSPrefix());
		forceMkdirIosOutputPath();
		for (TableBean tableBean : tableBeanList) {
			generateIOSModelH(tableBean);
			generateIOSModelM(tableBean);
		}
	}

	private void generateIOSModelH(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		loadTransientColumnBean(tableBean, runConfigure.getIOSPrefix());
		setFreemarkerModel("tableBean", tableBean);
		Template entityTemplate = FreemarkerUtil.getTemplate("template/modelIOSH.ftl");
		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + runConfigure.getIOSPrefix() + tableBean.getTableNameCapitalized() + ".h",
				entityTemplate, varMap);
	}

	private void generateIOSModelM(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		loadTransientColumnBean(tableBean, runConfigure.getIOSPrefix());
		setFreemarkerModel("tableBean", tableBean);
		Template entityTemplate = FreemarkerUtil.getTemplate("template/modelIOSM.ftl");
		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + runConfigure.getIOSPrefix() + tableBean.getTableNameCapitalized() + ".m",
				entityTemplate, varMap);
	}

	public synchronized void generateService() {
		for (TableBean tableBean : tableBeanList) {
			setFreemarkerModel("tableBean", tableBean);
			generateService(tableBean);
			generateServiceImpl(tableBean);
		}
	}

	private void generateService(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		forceMkdirServiceOutputPath();
		// atom的接口应当在业务服务实现中
		// com.ndlan.cwwarm.atomservice
		// com.ndlan.cwwarm.atomserviceimpl
		// com.ndlan.cwwarm.compservice
		// com.ndlan.cwwarm.compserviceimpl
		// com.ndlan.cwwarm.repository
		// com.ndlan.cwwarm.repositoryimpl

		// com.ndlan.cwwarm.controller
		// com.ndlan.cwwarm.rest
		Template serviceTemplate = FreemarkerUtil.getTemplate("template/atomService.ftl");
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("aomtServiceSuffix", RunConfigure.SERVICE_SUFFIX);
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);

		FreemarkerUtil.outputProcessResult(outputDirs + "/" + module_service + "/" + tableBean.getTableNameCapitalized()
				+ CLASS_PREFIX + RunConfigure.SERVICE_SUFFIX + ".java", serviceTemplate, varMap);
	}

	private void generateServiceImpl(TableBean tableBean) {
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirServiceOutputPath();
		setFreemarkerModel("classSuffix", RunConfigure.SERVICE_IMPL_SUFFIX);
		setFreemarkerModel("serviceIntefaceSuffix", RunConfigure.SERVICE_SUFFIX);
		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		setFreemarkerModel("tableBean", tableBean);
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/serviceimpl.ftl");
		FreemarkerUtil
				.outputProcessResult(
						outputDirs + "/" + module_service_impl + "/" + tableBean.getTableNameCapitalized()
								+ CLASS_PREFIX + RunConfigure.SERVICE_IMPL_SUFFIX + ".java",
						serviceimplTemplate, varMap);
	}

	// 当有了MyBatis之后，生成的简易结构
	public synchronized void generateSimpleService() {
		for (TableBean tableBean : tableBeanList) {
			setFreemarkerModel("tableBean", tableBean);
			generateSimpleService(tableBean);
			generateSimpleServiceImpl(tableBean);
		}
	}

	// 简单接口定义
	private void generateSimpleService(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirServiceOutputPath();
		Template serviceTemplate = FreemarkerUtil.getTemplate("template/atomService.ftl");

		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());// Bean
		setFreemarkerModel("pojoSuffix", runConfigure.getClassPrefix());// 和classPrefix一致
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());// Query
		setFreemarkerModel("packageModel", runConfigure.packageModel);// Query

		setFreemarkerModel("atomServiceSuffix", RunConfigure.SERVICE_SUFFIX);// 原子服务
		setFreemarkerModel("packageAtomService", RunConfigure.packageService);// 原子服务

		setFreemarkerModel("daoSuffix", RunConfigure.DAO_SUFFIX);// 原子服务
		setFreemarkerModel("packageDao", RunConfigure.packageDao);// 原子服务

		setFreemarkerModel("tableBean", tableBean);//

		FreemarkerUtil.outputProcessResult(outputDirs + "/" + RunConfigure.packageService + "/"
				+ tableBean.getTableNameCapitalized() + CLASS_PREFIX + RunConfigure.SERVICE_SUFFIX + ".java",
				serviceTemplate, varMap);
	}

	private void generateSimpleServiceImpl(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/atomServiceimpl.ftl");
		forceMkdirServiceOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);

		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("packageDao", RunConfigure.packageDao);// dao

		setFreemarkerModel("atomServiceSuffix", RunConfigure.SERVICE_SUFFIX);
		setFreemarkerModel("packageAtomService", RunConfigure.packageService);// 原子服务
		// atom serivce impl
		setFreemarkerModel("packageAtomServiceImpl", RunConfigure.packageServiceImpl);// 原子服务
		setFreemarkerModel("atomServiceImplSuffix", RunConfigure.SERVICE_IMPL_SUFFIX);
		setFreemarkerModel("tableBean", tableBean);

		FreemarkerUtil
				.outputProcessResult(
						outputDirs + "/" + RunConfigure.packageServiceImpl + "/" + tableBean.getTableNameCapitalized()
								+ CLASS_PREFIX + RunConfigure.SERVICE_IMPL_SUFFIX + ".java",
						serviceimplTemplate, varMap);
	}

	// 当有了MyBatis之后，生成的简易结构
	public synchronized void generateSimpleBusinessService() {
		for (TableBean tableBean : tableBeanList) {
			setFreemarkerModel("tableBean", tableBean);
			generateSimpleBusinessService(tableBean);
			generateSimpleBusinessServiceImpl(tableBean);
		}
	}

	// 简单接口定义
	private void generateSimpleBusinessService(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirAPIServiceOutputPath();
		Template serviceTemplate = FreemarkerUtil.getTemplate("template/businessService.ftl");

		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());// Bean
		setFreemarkerModel("pojoSuffix", runConfigure.getClassPrefix());// 和classPrefix一致
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());// Query
		setFreemarkerModel("packageModel", runConfigure.packageModel);// Query

		
		
		setFreemarkerModel("businessServiceSuffix", RunConfigure.BUSINESS_SERVICE_SUFFIX);// 业务服务
		setFreemarkerModel("packageBusinessService", RunConfigure.packageBusinessService);// 业务服务
		setFreemarkerModel("tableBean", tableBean);//

		FreemarkerUtil.outputProcessResult(outputDirs + "/" + RunConfigure.packageBusinessService + "/"
				+ tableBean.getTableNameCapitalized() + CLASS_PREFIX + RunConfigure.BUSINESS_SERVICE_SUFFIX + ".java",
				serviceTemplate, varMap);
	}

	private void generateSimpleBusinessServiceImpl(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		//forceMkdirAPIServiceOutputPath();
		forceMkdirAPIImplServiceOutputPath();
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/businessServiceimpl.ftl");
		//forceMkdirAPIImplServiceOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		setFreemarkerModel("packageModelQuery", runConfigure.packageModelQuery);
		
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		setFreemarkerModel("packageModel", RunConfigure.packageModel);
		
		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("packageDao", RunConfigure.packageDao);// dao

		setFreemarkerModel("businessServiceSuffix", RunConfigure.BUSINESS_SERVICE_SUFFIX);
		setFreemarkerModel("packageBusinessService", RunConfigure.packageBusinessService);// 原子服务
		setFreemarkerModel("atomServiceSuffix", RunConfigure.SERVICE_SUFFIX);// 原子服务
		setFreemarkerModel("packageBusinessServiceImpl", RunConfigure.packageBusinessImpl);// 业务服务实现
		setFreemarkerModel("businessServiceImplSuffix", RunConfigure.BUSINESS_SERVICE_IMPL_SUFFIX);
		setFreemarkerModel("tableBean", tableBean);

		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + RunConfigure.packageBusinessImpl + "/" + tableBean.getTableNameCapitalized()
						+ CLASS_PREFIX + RunConfigure.BUSINESS_SERVICE_IMPL_SUFFIX + ".java",
				serviceimplTemplate, varMap);
	}

	// 当有了MyBatis之后，生成的简易结构
	public synchronized void generateSimpleDAO() {
		for (TableBean tableBean : tableBeanList) {
			setFreemarkerModel("tableBean", tableBean);
			generateSimpleDAO(tableBean);
			generateSimpleDAOImpl(tableBean);
		}
	}

	// 简单接口定义
	private void generateSimpleDAO(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirDaoOutputPath();
		Template serviceTemplate = FreemarkerUtil.getTemplate("template/abcDao.ftl");

		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());// Bean
		setFreemarkerModel("pojoSuffix", runConfigure.getClassPrefix());// 和classPrefix一致
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());// Query
		setFreemarkerModel("packageModel", runConfigure.packageModel);// Query

		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);// 原子服务
		setFreemarkerModel("packageDao", RunConfigure.packageDao);// 原子服务
		setFreemarkerModel("tableBean", tableBean);//

		FreemarkerUtil.outputProcessResult(outputDirs + "/" + RunConfigure.packageDao + "/"
				+ tableBean.getTableNameCapitalized() + CLASS_PREFIX + RunConfigure.DAO_SUFFIX + ".java",
				serviceTemplate, varMap);
	}

	private void generateSimpleDAOImpl(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/abcDaoImpl.ftl");
		forceMkdirDaoOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);

		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("packageDao", RunConfigure.packageDao);// dao

		setFreemarkerModel("daoImplSuffix", RunConfigure.DAO_IMPL_SUFFIX);
		setFreemarkerModel("packageDaoImpl", RunConfigure.packageDaoImpl);// 原子服务
		
		setFreemarkerModel("tableBean", tableBean);

		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + RunConfigure.packageDaoImpl + "/" + tableBean.getTableNameCapitalized()
						+ CLASS_PREFIX + RunConfigure.DAO_IMPL_SUFFIX + ".java",
				serviceimplTemplate, varMap);
	}
	
	public synchronized void generateSimpleAndroidRestfulJunit() {
		for (TableBean tableBean : tableBeanList) {
			generateSimpleAndroidRestfulJunit(tableBean);
		}
	}

	private void generateSimpleAndroidRestfulJunit(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/abcAndroidRestfulTest.ftl");
		forceMkdirAndroidTestOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);

		setFreemarkerModel("packageModelQuery", runConfigure.packageModelQuery);// Query
		
		
		setFreemarkerModel("parseControllerPath", 
				parseControllerPath(tableBean.getTableNameCapitalized()));
		

		setFreemarkerModel("androidRestfulSuffix", RunConfigure.ANDROID_RESTFUL_SUFFIX);
		// rest controller
		setFreemarkerModel("packageAndroid", RunConfigure.packageAndroid);// android
		
		//setFreemarkerModel("restSuffix", RunConfigure.REST_SUFFIX);
		
		setFreemarkerModel("tableBean", tableBean);

		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + RunConfigure.packageAndroid + "/" + tableBean.getTableNameCapitalized()
						+ CLASS_PREFIX + RunConfigure.ANDROID_RESTFUL_SUFFIX + "Test.java",
				serviceimplTemplate, varMap);
	}
	
	public synchronized void generateSimpleMyBatis() {
		for (TableBean tableBean : tableBeanList) {
			generateSimpleMyBatis(tableBean);
		}
		String pathDirectory=runConfigure.getMyBatisXMLOutPath()+"/"+runConfigure.getMyBatisConfigPath(); ;
		deleteStringInDirectory(pathDirectory,"\\");
	}

	private void generateSimpleMyBatis(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		//PACKAGE_BASE=runConfigure.getModulePakage();
		//outputDirs=runConfigure.getMyBatisXMLOutPath();
		varMap.put("packageBase", runConfigure.getModulePakage());
		Template serviceimplTemplate=null ;//= FreemarkerUtil.getTemplate("template/abcMapper.ftl");
		try{
			serviceimplTemplate = FreemarkerUtil.getTemplate("template/abcMapper.ftl");
		}catch(Exception e){
			logger.error("tableBean:\t",e);
		}
		forceMkdirMyBatisXMLOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		setFreemarkerModel("packageModel", RunConfigure.packageModel);
		setFreemarkerModel("packageModelQuery", runConfigure.packageModelQuery);// Query
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		
		setFreemarkerModel("daoXMLSuffix", RunConfigure.DAO_XML_SUFFIX);
		setFreemarkerModel("packageDaoXML", runConfigure.getMyBatisConfigPath());// android
		
		setFreemarkerModel("tableBean", tableBean);
		String pathDirectory=outputDirs + "/" + runConfigure.getMyBatisConfigPath()  ;
		String path=pathDirectory+"/" + tableBean.getTableNameCapitalized()
		+ CLASS_PREFIX + RunConfigure.DAO_XML_SUFFIX + ".xml";
		FreemarkerUtil.outputProcessResult( path
				,
				serviceimplTemplate, varMap);
		//生成完的Mapper文件修修改
		
	}
	
	
	private void deleteStringInDirectory(String pathDirectory,String deleteContent){
		File directory=new File(pathDirectory);
		if(!directory.isDirectory()){
			//D:\DevT\SpringHelper\ndlan-projects\ndl-warm-project\cwwarm\service-module\src\main\resources\mapper\cwwarm
			logger.warn("修改目录不存在,pathDirectory:\t"+pathDirectory);
			return;
		}
		Collection<File> fileCollection=FileUtil.listFiles(directory, new String[]{"xml"}, false);
		try {
			if(CollectionUtils.isEmpty(fileCollection)){
				logger.warn("修改目录中不存在文件,pathDirectory:\t"+pathDirectory);
				return;
			}
			for(File file:fileCollection){
				String content=FileUtil.readFileToString(file);
				if(logger.isDebugEnabled()){
					logger.debug("readContent from file:\t"+content);
				}
				while(content.contains("\\")){
					content=content.replace("\\", "");
				}
				if(logger.isDebugEnabled()){
					logger.debug("deleteContent:\t"+content);
				}
				//写文件
				FileUtil.writeStringToFile(file, content);
				
			}
		} catch (IOException e) {
			logger.error("读入文件时产生错误", e);
		}
		
		
	}
	public synchronized void generateSimpleAndroidRestful() {
		//生成AndroidRestful之前按照模块引入地址/和项目的相对路径
		generateSimpleAndroidRestfulConfig();
		for (TableBean tableBean : tableBeanList) {
			generateSimpleAndroidRestful(tableBean);
		}
	}
	
	
	private boolean generateSimpleAndroidRestfulConfig() {

		if(!generateSimpleAndroidRestfulConfig){
			return false;
		}
		generateSimpleAndroidRestfulConfig=false;
		
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/abcAndroidRestfulConfig.ftl");
		forceMkdirAndroidOutputPath();
		
		setFreemarkerModel("serviceContext",runConfigure.getServiceContext());
	
		setFreemarkerModel("androidRestfulSuffix", RunConfigure.ANDROID_RESTFUL_SUFFIX);
		// rest controller
		setFreemarkerModel("packageAndroid", RunConfigure.packageAndroid);// android
		
		setFreemarkerModel("restSuffix", RunConfigure.REST_SUFFIX);
	

		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + RunConfigure.packageAndroid + "/" + "BaseAndroidRestfulConfig.java",
				serviceimplTemplate, varMap);
		return false;
	}
	
	
	private void generateSimpleAndroidRestful(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/abcAndroidRestful.ftl");
		forceMkdirAndroidOutputPath();
		
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		
		setFreemarkerModel("serviceContext",runConfigure.getServiceContext());
		setFreemarkerModel("packageModelQuery", runConfigure.packageModelQuery);// Query
		setFreemarkerModel("packageModel", runConfigure.packageModel);// Query
		
		
		setFreemarkerModel("parseControllerPath", 
				parseControllerPath(tableBean.getTableNameCapitalized()));
		

		setFreemarkerModel("androidRestfulSuffix", RunConfigure.ANDROID_RESTFUL_SUFFIX);
		// rest controller
		setFreemarkerModel("packageAndroid", RunConfigure.packageAndroid);// android
		
		setFreemarkerModel("restSuffix", RunConfigure.REST_SUFFIX);
		
		setFreemarkerModel("tableBean", tableBean);

		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + RunConfigure.packageAndroid + "/" + tableBean.getTableNameCapitalized()
						+ CLASS_PREFIX + RunConfigure.ANDROID_RESTFUL_SUFFIX + ".java",
				serviceimplTemplate, varMap);
	}
	
	public synchronized void generateSimpleController() {
		for (TableBean tableBean : tableBeanList) {
			generateSimpleController(tableBean);
		}
	}

	private void generateSimpleController(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		setFreemarkerModel("defaultBeanPackage",runConfigure.getModulePakage() );
		varMap.put("packageBase", runConfigure.getControllerPakage());
		PACKAGE_BASE=runConfigure.getControllerPakage();
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/abcController.ftl");
		//接口提供者
		//forceMkdirAPIServiceOutputPath();
		forceMkdirAPIImplServiceOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);

		setFreemarkerModel("packageModelQuery", runConfigure.packageModelQuery);// Query
		
		
		setFreemarkerModel("parseControllerPath", 
				parseControllerPath(tableBean.getTableNameCapitalized()));
		

		setFreemarkerModel("businessServiceSuffix", RunConfigure.BUSINESS_SERVICE_SUFFIX);
		setFreemarkerModel("packageBusinessService", RunConfigure.packageBusinessService);// 业务服务
		
		setFreemarkerModel("businessServiceSuffix", RunConfigure.BUSINESS_SERVICE_SUFFIX);
		setFreemarkerModel("packageBusinessService", RunConfigure.packageBusinessService);//
		// rest controller
		setFreemarkerModel("packageRest", RunConfigure.packageRest);// Controller
		setFreemarkerModel("restSuffix", RunConfigure.REST_SUFFIX);
		
		setFreemarkerModel("tableBean", tableBean);

		FreemarkerUtil.outputProcessResult(
				outputDirs + "/" + RunConfigure.packageRest + "/" + tableBean.getTableNameCapitalized()
						+ CLASS_PREFIX + RunConfigure.REST_SUFFIX + ".java",
				serviceimplTemplate, varMap);
	}

	private String parseControllerPath(String tableNameCapitalized){
		String[] words=getWords(tableNameCapitalized);
		return getBasePath(words);
	}
	private String getBasePath(String[] words) {
		StringBuffer sb = new StringBuffer();
		for (String word : words) {
			sb.append(word).append("/");
		}
		return sb.toString();
	}
	/**
	 * 以字符串中的大写字母为标示拆分字符串,如果字符串为null或空则返回null
	 * @param str
	 * @return String[] 拆分后的字符串，已转换为全小写
	 */
	private String[] getWords(String str) {
		if (StringUtils.isEmpty(str)) return null;
		String[] words = str.split("(?<!^)(?=[A-Z])");
		for (int i = 0; i < words.length; i++) {
			words[i] = StringUtils.lowerCase(words[i]);
		}
		return words;
	}
	
	public synchronized void generateServiceJunit() {
		for (TableBean tableBean : tableBeanList) {
			generateServiceJunit(tableBean);
		}
	}

	private void generateServiceJunit(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		forceMkdirServiceOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("serviceSuffix", RunConfigure.SERVICE_IMPL_SUFFIX);
		setFreemarkerModel("serviceIntefaceSuffix", RunConfigure.SERVICE_SUFFIX);
		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		Template serviceimplTemplate = FreemarkerUtil.getTemplate("template/servicetest.ftl");
		FreemarkerUtil
				.outputProcessResult(
						outputDirs + "/" + module_service_impl + "/" + tableBean.getTableNameCapitalized()
								+ CLASS_PREFIX + RunConfigure.SERVICE_SUFFIX + "Tests.java",
						serviceimplTemplate, varMap);
	}

	public synchronized void genernateJDBCTemplete() {
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		for (TableBean tableBean : tableBeanList) {
			genernateJDBCTemplete(tableBean);
			genernateJDBCTempleteImpl(tableBean);
		}
	}

	public synchronized void genernateJDBCTempleteImpl() {

		for (TableBean tableBean : tableBeanList) {
			genernateJDBCTempleteImpl(tableBean);
		}
	}

	private void genernateJDBCTemplete(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirDaoOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		Template daoTemplate = FreemarkerUtil.getTemplate("template/dao.ftl");
		FreemarkerUtil.outputProcessResult(outputDirs + "/" + module_dao + "/" + tableBean.getTableNameCapitalized()
				+ CLASS_PREFIX + RunConfigure.DAO_SUFFIX + ".java", daoTemplate, varMap);
	}

	private void genernateJDBCTempleteImpl(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirDaoOutputPath();
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", RunConfigure.DAO_IMPL_SUFFIX);
		setFreemarkerModel("daoIntefaceSuffix", RunConfigure.DAO_SUFFIX);
		setFreemarkerModel("pojoSuffix", RunConfigure.POJO_SUFFIX);
		Template daoimplTemplate = FreemarkerUtil.getTemplate("template/daoimpl.ftl");
		FreemarkerUtil.outputProcessResult(outputDirs + "/" + module_dao_impl + "/"
				+ tableBean.getTableNameCapitalized() + CLASS_PREFIX + RunConfigure.DAO_IMPL_SUFFIX + ".java",
				daoimplTemplate, varMap);
	}

	public synchronized void generateEntity() {
		for (TableBean tableBean : tableBeanList) {
			generateEntity(tableBean);
		}
	}

	private void generateEntity(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		forceMkdirEntityOutputPath();
		loadTransientColumnBean(tableBean, RunConfigure.Entity_SUFFIX);
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", RunConfigure.Entity_SUFFIX);
		Template entityTemplate = FreemarkerUtil.getTemplate("template/entity.ftl");
		FreemarkerUtil.outputProcessResult(outputDirs + "/" + RunConfigure.packageEntity + "/"
				+ tableBean.getTableNameCapitalized() + RunConfigure.Entity_SUFFIX + ".java", entityTemplate, varMap);

	}

	public synchronized void generateModlePojo() {
		for (TableBean tableBean : tableBeanList) {
			generateModlePojo(tableBean);
			generateModleQuery(tableBean);// 暂时策略，为了模糊查询
		}
	}

	private void generateModlePojo(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		loadTransientColumnBean(tableBean, runConfigure.getClassPrefix());
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		forceMkdirDomainOutputPath();
		Template modelTemplate = FreemarkerUtil.getTemplate("template/model.ftl");
		FreemarkerUtil.outputProcessResult(outputDirs + "/" + RunConfigure.packageModel + "/"
				+ tableBean.getTableNameCapitalized() + runConfigure.getClassPrefix() + ".java", modelTemplate, varMap);

	}

	/**
	 * 临时解决模糊查询方案
	 * 
	 * @param tableBean
	 */
	private void generateModleQuery(TableBean tableBean) {
		if(logger.isInfoEnabled()){
			logger.info("tableBean:\t"+tableBean);
		}
		PACKAGE_BASE=runConfigure.getModulePakage();
		varMap.put("packageBase", runConfigure.getModulePakage());
		setFreemarkerModel("tableBean", tableBean);
		setFreemarkerModel("classSuffix", runConfigure.getClassPrefix());
		setFreemarkerModel("classQuerySuffix", runConfigure.getClassQuerySuffix());
		forceMkdirDomainOutputPath();
		Template modelTemplate = FreemarkerUtil.getTemplate("template/modelQuery.ftl");
		FreemarkerUtil.outputProcessResult(outputDirs + "/" + RunConfigure.packageModelQuery + "/"
				+ tableBean.getTableNameCapitalized() + runConfigure.getClassQuerySuffix() + ".java", modelTemplate,
				varMap);

	}
	///////////////////////////////////////////////////////////

	public void putDataForFreeMarker(String key, Object bean) {
		varMap.put(key, bean);
	}

	public void setFreemarkerModel(String beanKey, Object beanObject) {
		varMap.put(beanKey, beanObject);
	}

	/////////////////////////////////////////////
	public Set<TableBean> getTables() {
		String queryTableSQL = "select * from tables where table_schema = ?";
		if (StringUtils.isNotBlank(tablesFilter)) {
			queryTableSQL = "select * from tables where table_schema = ? and table_name in (" + tablesFilter + ")";
		}
		return loadTables(queryTableSQL, new Object[] { SCHEMA_NAME });
	}

	public Set<ColumnBean> getColumns(String tableName, final TableBean tableBean) {
		String queryColumnsSQL = "select * from COLUMNS where table_schema = ? and table_name = ? ";
		if (StringUtils.isNoneBlank(columnsExclude)) {
			queryColumnsSQL = "select * from COLUMNS where table_schema = ? and table_name = ? and COLUMN_NAME NOT IN ("
					+ columnsExclude + ")";
		}
		return loadColumns(tableName, tableBean, queryColumnsSQL, new Object[] { SCHEMA_NAME, tableName });
	}

	public Set<TableBean> loadTables(String queryTableSQL, Object[] queryCondifition) {
		List<TableBean> tableBeanList = jdbcTemplate.query(queryTableSQL, queryCondifition, new RowMapper<TableBean>() {
			public TableBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				TableBean bean = new TableBean();
				String tableName = rs.getString("table_name");
				String tableNameTrimed = tableName;
				if (tableName.startsWith("t_")) {
					tableNameTrimed = tableName.substring(2);
				}
				bean.setTableName(tableName);
				bean.setTableNameNoDash(delDash(tableNameTrimed));
				bean.setTableNameCapitalized(StringUtils.capitalize(bean.getTableNameNoDash()));
				bean.setTableComment(rs.getString("table_comment"));
				return bean;
			}
		});

		// 获取表信息
		Set<TableBean> set = new HashSet<TableBean>(tableBeanList);
		return set;
	}

	public static Set<ColumnBean> loadColumns(String tableName, final TableBean tableBean, String queryColumnsSQL,
			Object[] queryCondifition) {

		List<ColumnBean> list = jdbcTemplate.query(queryColumnsSQL, queryCondifition, new RowMapper<ColumnBean>() {
			public ColumnBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				ColumnBean bean = new ColumnBean();
				// 对取出的列做驼峰式处理
				String columnName = rs.getString("COLUMN_NAME");
				// 字段名称
				bean.setColumnName(columnName);
				// 实体对象属性名称
				bean.setColumnNameNoDash(delDash(columnName));
				bean.setColumnNameCapitalized(StringUtils.capitalize(bean.getColumnNameNoDash()));
				// 长度
				String charLength = rs.getString("CHARACTER_MAXIMUM_LENGTH");
				if (StringUtils.isNotBlank(charLength)) {// 长度
					bean.setLength(Long.parseLong(charLength));
				}
				// 备注信息
				bean.setColumnComment(rs.getString("COLUMN_COMMENT"));
				//
				bean.setOrdinalPosition(rs.getString("ORDINAL_POSITION"));
				bean.setIsNullable(rs.getString("IS_NULLABLE"));
				bean.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
				bean.setColumnType(rs.getString("COLUMN_TYPE"));
				bean.setColumnKey(rs.getString("COLUMN_KEY"));
				bean.setExtra(rs.getString("EXTRA"));
				// 类型，DB类型同Java类型的转换
				String columnType = rs.getString("column_type").toLowerCase();
				convertType(tableBean, bean, columnType);
				return bean;
			}
		});
		Set<ColumnBean> set = new HashSet<ColumnBean>(list);
		return set;
	}

	private static void convertType(final TableBean tableBean, final ColumnBean bean, final String columnType) {
		bean.setColumnJDBCType(columnType);
		bean.setColumnDBType(columnType);
		ProductParseTemplateUtil.parseType(bean, columnType);
		if (("timestamp").equals(columnType) || ("datetime").equals(columnType) || ("date").equals(columnType)) {
			tableBean.setHasDateColumn(true);
		} else if (columnType.startsWith("decimal")) {
			tableBean.setHasBigDecimal(true);
		}
		String key = bean.getColumnKey();
		if (StringUtils.isNotBlank(key) && key.equals("PRI")) {
			tableBean.setPkType(bean.getColumnType());
			tableBean.setPkName(bean.getColumnNameNoDash());
			tableBean.setPkColumnName(bean.getColumnNameNoDash());
			tableBean.setPkColumn(bean.getColumnName());//
			tableBean.setPkColumnNameCapitalized(bean.getColumnNameCapitalized());
		}
	}

	public static String delDash(String str) {
		return ClassHelper.namingUsingJavaMethod(str);
	}

	private void forceMkdir() {
		forceMkdir(runConfigure.getOutputPath());
	}

	private void forceMkdir(String path) {
		try {
			File outputPath = new File(path);
			if (!outputPath.isDirectory()) {
				FileUtils.forceMkdir(outputPath);
			}
			outputDirs = path + "/" + StringUtils.replace(PACKAGE_BASE, ".", "/");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void forceMkdirEntityOutputPath() {
		forceMkdir(runConfigure.getEntityOutputPath());
	}

	private void forceMkdirDomainOutputPath() {
		forceMkdir(runConfigure.getDomainOutputPath());
	}

	private void forceMkdirServiceOutputPath() {
		forceMkdir(runConfigure.getServiceOutputPath());
	}
	
	private void forceMkdirAPIImplServiceOutputPath() {
		forceMkdir(runConfigure.getAPIImplOutputPath());
	}
	
	private void forceMkdirAPIServiceOutputPath() {
		forceMkdir(runConfigure.getAPIOutputPath());
	}
	
	private void forceMkdirMyBatisXMLOutputPath() {
		//forceMkdir(runConfigure.getAndroidOutPath());
		try {
			String path = runConfigure.getMyBatisXMLOutPath();
			File outputPath = new File(path);
			if (!outputPath.isDirectory()) {
				FileUtils.forceMkdir(outputPath);
			}
			outputDirs = path;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	private void forceMkdirAndroidOutputPath() {
		forceMkdir(runConfigure.getAndroidOutPath());
	}
	
	private void forceMkdirAndroidTestOutputPath() {
		forceMkdir(runConfigure.getAndroidTestOutputPath());
	}
	private void forceMkdirDaoOutputPath() {
		forceMkdir(runConfigure.getDaoOutputPath());
	}

	private void forceMkdirIosOutputPath() {
		try {
			String path = runConfigure.getIOSOutputPath();
			File outputPath = new File(path);
			if (!outputPath.isDirectory()) {
				FileUtils.forceMkdir(outputPath);
			}
			outputDirs = path;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
