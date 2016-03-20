package com.ndl.framework.workbrench.freemarker;

import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ndl.framework.workbrench.define.FieldRativeEnum;
import com.ndl.framework.workbrench.define.MethCategoryEnum;

public class RunConfigure {

	private static Properties configProperties = (Properties) new ClassPathXmlApplicationContext(
			"applicationContext.xml").getBean("configProperties");

	public String getServiceContext() {
		return configProperties.getProperty("workbrench.config.process.serviceContext");
	}
	
	public String getModuleName() {
		return configProperties.getProperty("workbrench.config.process.moduleName");
	}
	
	public String getConfigurePackge() {
		return configProperties.getProperty("workbrench.config.template.path");
	}

	public String getSchemaName() {
		return configProperties.getProperty("workbrench.config.template.schema");
	}
	
	public String getDefaultOutputPath() {
		return configProperties.getProperty("workbrench.config.template.outputPath");
	}
	
	
	public String getModulePakage() {
		return configProperties.getProperty("workbrench.config.template.modulePackage");
	}
	
	//workbrench.config.template.controllerPackage=com.ndlanx.cwwarm
	public String getControllerPakage() {
		return configProperties.getProperty("workbrench.config.template.controllerPackage");
	}
	
	public String getAndroidOutPath() {
		return configProperties.getProperty("workbrench.config.template.androidOutputPath");
	}
	
	public String getMyBatisXMLOutPath() {
		return configProperties.getProperty("workbrench.config.template.mybatisXmlOutputPath");
	}
	
	public String getClassPrefix() {
		return configProperties.getProperty("workbrench.config.process.classPrefix");
	}

	
	public String getClassQuerySuffix() {
		return configProperties.getProperty("workbrench.config.process.classQuerySuffix");
	}

	public String getEntitySuffix() {
		return configProperties.getProperty("workbrench.config.process.entitySuffix");
	}

	public String getIOSPrefix() {
		return configProperties.getProperty("workbrench.config.process.iosPrefix");
	}

	/////////////////////////////////////////////
	public String getOutputPath() {
		return configProperties.getProperty("workbrench.config.template.outputPath");
	}

	public String getApiOutputPath() {
		return configProperties.getProperty("workbrench.config.template.apiOutputPath");
	}

	public String getEntityOutputPath() {
		return configProperties.getProperty("workbrench.config.template.entityOutputPath");
	}

	public String getDomainOutputPath() {
		return configProperties.getProperty("workbrench.config.template.domainOutputPath");
	}
	
	public String getServiceOutputPath() {
		return configProperties.getProperty("workbrench.config.template.serviceOutputPath");
	}

	public String getAPIOutputPath() {
		return configProperties.getProperty("workbrench.config.template.apiOutputPath");
	}
	
	public String getAPIImplOutputPath() {
		return configProperties.getProperty("workbrench.config.template.apiImplOutputPath");
	}
	
	public String getAndroidTestOutputPath() {
		return configProperties.getProperty("workbrench.config.template.androidTestOutputPath");
	}
	public String getDaoOutputPath() {
		return configProperties.getProperty("workbrench.config.template.daoOutputPath");
	}

	public String getIOSOutputPath() {
		return configProperties.getProperty("workbrench.config.template.iosOutputPath");
	}
	////////////////////////////////////////////////////////
	
	public static String getBuinessservcietemplate() {
		return buinessServcieTemplate;
	}

	public static String getPackageentity() {
		return packageEntity;
	}

	public static String getPackagemodel() {
		return packageModel;
	}

	public static String getPackageservice() {
		return packageService;
	}

	public static String getPackageserviceimpl() {
		return packageServiceImpl;
	}

	public static String getPackagedao() {
		return packageDao;
	}

	public static String getPackagedaoimpl() {
		return packageDaoImpl;
	}

	public static String getControllerMethodReturnType() {
		return controllerMethodReturnType;
	}

	public static String getControlleMethodReturnAnnotation() {
		return controllerMethodReturnAnnotation;
	}

	public static String getControlleMehodAssginAnnotation() {
		return controllerMethodAssginAnnotation;
	}

	public static final MethCategoryEnum JPACategory = MethCategoryEnum.JPA;
	public static final MethCategoryEnum JDBCCategory = MethCategoryEnum.JDBC;
	public static final MethCategoryEnum MYBATISCategory = MethCategoryEnum.MBT;

	public static final MethCategoryEnum BNSCategory = MethCategoryEnum.BNS;
	public static final MethCategoryEnum ATOMCategory = MethCategoryEnum.ATM;

	public static final MethCategoryEnum GETCategory = MethCategoryEnum.GET;
	public static final MethCategoryEnum GETPATHCategory = MethCategoryEnum.GETPATH;
	public static final MethCategoryEnum POSTCategory = MethCategoryEnum.POST;
	public static final MethCategoryEnum PUTCategory = MethCategoryEnum.PUT;
	public static final MethCategoryEnum DELETECategory = MethCategoryEnum.DELETE;
	public static final MethCategoryEnum UPDATECategory = MethCategoryEnum.UPDATE;

	public static final FieldRativeEnum AssignFinishedCategory = FieldRativeEnum.Finished;

	public static final String DAO_SUFFIX = "Dao";
	public static final String DAO_IMPL_SUFFIX = "DaoImpl";
	public static final String DAO_XML_SUFFIX = "Mapper";
	public static final String SERVICE_SUFFIX = "AtomService";
	public static final String SERVICE_IMPL_SUFFIX = "AtomServiceImpl";
	public static final String Entity_SUFFIX = "";
	public static final String POJO_SUFFIX = "Bean";

	public static final String ATOM_SERVICE_SUFFIX = SERVICE_SUFFIX;
	public static final String ATOM_SERVICE_IMPL_SUFFIX = SERVICE_IMPL_SUFFIX;

	public static final String BUSINESS_SERVICE_SUFFIX = "BusinessService";
	public static final String BUSINESS_SERVICE_IMPL_SUFFIX = "BusinessServiceImpl";

	public static final String CONTROLLER_SUFFIX = "Controller";
	public static final String REST_SUFFIX="BusinessControllerImpl";
	
	public static final String ANDROID_RESTFUL_SUFFIX="BustinessRestful";
	// com.ndlan.cwwarm.service
	// com.ndlan.cwwarm.serviceimpl
	// com.ndlan.cwwarm.component
	// com.ndlan.cwwarm.componentImpl
	// com.ndlan.cwwarm.repository
	// com.ndlan.cwwarm.repositoryimpl

	// com.ndlan.cwwarm.controller
	// com.ndlan.cwwarm.rest
	// 默认包结构
	public static final String packageEntity = "entity";
	public static final String packageModel = "model";
	public static final String packageModelQuery = "query";
	//原子服务
	public static final String packageService = "service";
	public static final String packageServiceImpl = "serviceimpl";
	
	public static final String packageRest= "rest";
	public static final String packageAndroid= "rest";
	//业务服务
	public static final String packageBusinessService = "component";
	public static final String packageBusinessImpl = "componentimpl";
	//dao
	public static final String packageDao = "dao";
	public static final String packageDaoImpl = "daoimpl";
	//public static final String packageDaoXML = "cwwarm";
	
	// Controller Default
	public static final String controllerMethodReturnType = "IosReturnJson";
	public static final String controllerMethodReturnAnnotation = "ResponseBody";
	public static final String controllerMethodAssginAnnotation = "RequestBody";

	public static final String buinessServcieTemplate = "template/service.ftl";

	public static RunConfigure instanceRunConfigure() {
		RunConfigure runConfigure = new RunConfigure();
		return runConfigure;
	}
	
	public String getMyBatisConfigPath() {
		return configProperties.getProperty("workbrench.config.process.myBatis");
	}
	
	public static String getConfigPath() {
		return instanceRunConfigure().getConfigurePackge();
	}

	public static String getControllerSuffix() {
		return POJO_SUFFIX + CONTROLLER_SUFFIX;
	}

	public static String getBusinessServiceSuffix() {
		return POJO_SUFFIX + BUSINESS_SERVICE_SUFFIX;
	}

	public static String getAtomServiceSuffix() {
		return POJO_SUFFIX + ATOM_SERVICE_SUFFIX;
	}

	public static String getDaoSuffix() {
		return POJO_SUFFIX + DAO_SUFFIX;
	}
}
