package com.ndl.framework.workbrench.define;

/**
 * 如果是Controller则描述每个请求的类型，GET/POST/PUT/DELETE/UPDATE，目前处理前两种，此时默认为POST
 * 如果是业务服务分类，取值为BNS，如果是原子服务，取值为ATM，此时默认为BNS
 * 如果是JPA的DAO，则取值为JPA，如果是JDBC则取值为JDBC，如果是JDBC，如果是MyBatis，则取值为MBT，此时默认为JPA
 */
public enum MethCategoryEnum {
	GET,GETPATH,POST,PUT,DELETE,UPDATE,BNS,ATM,JPA,JDBC,MBT,RADIS,MONGODB,ELASTICSEARCH
}
