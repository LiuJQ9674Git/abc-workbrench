package com.ndl.framework.workbrench.define;
/**
 *当时columnKey为TST（transient）时，columnType描述为基本基本类型或者是JavaBean，
 *	如果extra为List，则为ArrayList<JavaBean>，
 *	如果为Map|objKey，则为HashMap< objKey, JavaBean>。
 *	如果extra中包含JsonIgnore则需要在变量上加上相应注解。
 *
 */
public enum FieldTypeEnum {
	PRIMARY,//Java基本类型
	PRI,//基本类型并且为数据库主键
	LIST,//则为ArrayList<JavaBean>
	MAPKEY,//则为HashMap< objKey, JavaBean>
    ENTITY,//实体Bean
    DOMAIN,//域模型
    Page,//实体分页
	NORETURN,//在返回值时表示无返回值，在入参时表示无入参
	NOARGUMENT//无参数
}
