package com.ndl.framework.workbrench.define;

import org.springframework.beans.factory.annotation.Autowired;

public enum AnnotationTypeEnum {
	Resource,//J2EE注解，@Resource(name=“userDao”)
	
	Autowired,
	Qualifier,//它应当和Autowired一起使用，@Autowired  @Qualifier("userDao")
	
	Component,//原子服务，事务在原子服务上
	Service,//业务服务
	Controller,//控制器
	
	Transactional,//既是方法又可以是类注解
	Repository, //DataAccessException
	RequestMapping,
	Query,//JPA的查询注解，或者事务注解
	ResponseBody,//方法注解，controller专用
	
	RequestBody,//同ModelAttribute合用
	ModelAttribute,
	PathVariable,
	SessionAttributes,
	
	ComponentScan,
	SpringBootApplication,
	WebAppConfiguration,
	ContextConfiguration,
	EnableAutoConfiguration,

	RunWith,//测试专用
}
