package com.ndlan.framework.core.web.domain;

import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ErrorDescripter {

	private static Properties configProperties = (Properties) new ClassPathXmlApplicationContext(
			"spring-config.xml").getBean("configProperties");
	

	public String getControllerDeleteListArgumentInfo(){
		return configProperties.getProperty("controller.deletelist.check.info");
	}
	
	public String getControllerDeleteListErrorInfo(){
		return configProperties.getProperty("controller.deletelist.error.info");
	}
	
	public String getControllerDeleteOneArgumentInfo(){
		return configProperties.getProperty("controller.deleteone.check.info");
	}
	
	public String getControllerDeleteOneErrorInfo(){
		return configProperties.getProperty("controller.deleteone.error.info");
	}
	
	public String getControllerAddOneArgumentInfo(){
		return configProperties.getProperty("controller.addone.check.info");
	}
	
	public String getControllerAddOneErrorInfo(){
		return configProperties.getProperty("controller.addone.error.info");
	}
	
	public String getControllerSelectListArgumentInfo(){
		return configProperties.getProperty("controller.selectlist.check.info");
	}
	
	public String getControllerASelectListErrorInfo(){
		return configProperties.getProperty("controller.selectlist.error.info");
	}
	
	
}
