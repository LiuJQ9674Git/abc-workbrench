package com.ndl.framework.workbrench.util;

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
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBodyFragment;
import com.ndl.framework.workbrench.define.MethodDescripter;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.RunConfigure;


public class ProductParseTemplateUtil {
	private static final Logger logger = LoggerFactory.getLogger(ProductParseTemplateUtil.class);
	
	
	
	//////////////////以下为解析方法MethodBean///////////////////////////////
	
	public static void parseMethodAnnotation(MethodDescripter methodDescripter,
			AnnotationBean methodHeaderAnnotation){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodAnnotation Begin:");
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("methodHeaderAnnotation:\t" + methodHeaderAnnotation);
		}
		methodDescripter.setMethodHeaderAnnotation(methodHeaderAnnotation);
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodAnnotation Over");
		}
	}
	
	public static void parseMethodClassName(MethodDescripter methodDescripter,String signatureEntirety){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodClassName Begin:");
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("signatureEntirety:\t" + signatureEntirety);
		}
		if (StringUtils.isBlank(signatureEntirety)) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		methodDescripter.setSignatureEntirety(signatureEntirety);
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodClassName Over");
		}
	}
	
	public static void parseMethodDescripter(MethodDescripter methodDescripter,String descripter,String className,MethCategoryEnum serviceCategory){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodDescripter Begin:");
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("descripter:\t" + descripter);
		}
		if (StringUtils.isBlank(descripter)) {
			logger.warn("businessDescripter and descripter must not null,");
			return ;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("/**\n");
		sb.append("* className:"+className);
		sb.append("* serviceCategory:"+serviceCategory);
		sb.append(descripter);
		sb.append("\n**/");
		methodDescripter.setComment(sb.toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodDescripter Over");
		}
	}
	
	/**
	 * 解析方法体内的对象调用的方法调用
	 * @param methodBodyFragment
	 * @param specialAssign
	 */
	public static void parseMethodAssignment(MethodBodyFragment methodBodyFragment,ColumnBean specialAssign){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodAssignment Begin:");
			logger.debug("methodBodyFragment:\t" + methodBodyFragment);
			logger.debug("specialAssign:\t" + specialAssign);
		}
		StringBuffer sb=new StringBuffer();
		sb.append(methodBodyFragment.getCalleeMethodName());
		if(specialAssign==null){
			return;
		}
		methodBodyFragment.addCalleeArgument(specialAssign);
    	sb.append(" "+specialAssign.getColumnNameNoDash());
    	methodBodyFragment.setCalleeMethodName(sb.toString());
    	if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodAssignment Over");
		}
	}
	
	public static void parseCelleeMethodResponseType(MethodDescripter methodDescripter,ColumnBean reposeType){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodResponseType Begin:");
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("reposeType:\t" + reposeType);
		}
		StringBuffer sb=new StringBuffer();
		//sb.append("public ");
		sb.append(parseMethodResponseType(reposeType));
 			
		sb.append(methodDescripter.getSignatureEntirety());
		methodDescripter.setSignatureEntirety(sb.toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodResponseType Over");
		}
	}
	
	public static void parseMethodResponseType(MethodDescripter methodDescripter,ColumnBean reposeType){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodResponseType Begin:");
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("reposeType:\t" + reposeType);
		}
		StringBuffer sb=new StringBuffer();
		sb.append("public ");
		sb.append(parseMethodResponseType(reposeType));
 			
		sb.append(methodDescripter.getSignatureEntirety());
		methodDescripter.setSignatureEntirety(sb.toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodResponseType Over");
		}
	}
	

	public static String parseMethodResponseType(ColumnBean reposeType) {
		StringBuffer sb=new StringBuffer();
		FieldTypeEnum fieldType=reposeType.getFieldType();
		if(fieldType==FieldTypeEnum.NOARGUMENT){
    		sb.append("  ");
    	}else if(fieldType==FieldTypeEnum.NORETURN){
    		sb.append(" void ");
    	}else{
			String responseEntityType = reposeType.getColumnType();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod responseEntityType:\t" + responseEntityType);
			}

			if (!ClassHelper.isCapitalion(responseEntityType)) {
				Assert.notNull(responseEntityType,
						"ProductManager parseMethodResponseType " + "responseEntityType must not null");
			}

			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod fieldType:\t" + fieldType);
			}
			if (fieldType == FieldTypeEnum.Page || fieldType == FieldTypeEnum.LIST) {
				sb.append(" List<");
				sb.append(responseEntityType);
				sb.append("> ");
			} else if (fieldType == FieldTypeEnum.MAPKEY) {
				String extra = reposeType.getExtra();
				Assert.notNull(extra, "ProductManager parseMethodResponseType " + "extra must not null");
				if (logger.isDebugEnabled()) {
					logger.debug("parseMethod extra:\t" + extra);
				}
				sb.append(" Map<");
				sb.append(extra);
				sb.append(",");
				sb.append(responseEntityType);
				sb.append(">");
			} else if (fieldType == FieldTypeEnum.ENTITY || fieldType == FieldTypeEnum.DOMAIN) {
				sb.append(" " + responseEntityType + " ");
			} else if (fieldType == FieldTypeEnum.PRIMARY) {
				sb.append(" " + responseEntityType + " ");
			} else {
				throw new ConfigRuntimeException(WorkBrenchConfigProperty.PROCESS_PRODUCT_EXCETPION_METHOD_NO_ARGUMENT);
			}
    	}
    	return sb.toString();
	}
	/**
	 * 
	 * 参考FieldTypeEnum,默认为基本类型
	 * 	PRIMARY,//Java基本类型
	 *	PRI,//基本类型并且为数据库主键
	 *	LIST,//则为ArrayList<JavaBean>
	 *	MAPKEY,//则为HashMap< objKey, JavaBean>
     *	ENTITY,//实体Bean
     *	DOMAIN,//域模型
     *	Page,//实体分页
	 *	NORETURN//在返回值时表示无返回值，在入参时表示无入参
	 *
	 * 当时fieldType为TST（transient）时，columnType描述为基本基本类型或者是JavaBean，
	 * 如果为Map|objKey，则为HashMap< objKey, JavaBean>。一般应当为主键
	 * 如果extra中包含JsonIgnore则需要在变量上加上相应注解。
	 * 
	 * extra:
	 *  参考FieldTypeEnum
	 * 
	 * 
	 * @param methodDescripter
	 * @param assignmentColumnBean
	 */
	public static void parseMethodAssignment(MethodDescripter methodDescripter,ColumnBean specialAssign){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodAssignment Begin:");
			logger.debug("methodDescripter:\t" + methodDescripter);
			logger.debug("specialAssign:\t" + specialAssign);
		}
		StringBuffer sb=new StringBuffer();
		sb.append(methodDescripter.getSignatureEntirety());
		if(specialAssign==null){
			return;
		}
		methodDescripter.addArgumentValid(specialAssign);
    	sb.append(parseMethodAssignmentOrInstanceValiable(specialAssign));
		methodDescripter.setSignatureEntirety(sb.toString());
    	if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseMethodAssignment Over");
		}
	}
	
	public static String parseMethodAssignmentOrInstanceValiable(ColumnBean specialAssign){
		StringBuffer sb=new StringBuffer();
	 	    	FieldTypeEnum fieldType=specialAssign.getFieldType();
	 	String valiable=parseMethodValiable(specialAssign);
	 	if(StringUtils.isBlank(valiable)){
	 		return valiable;
	 	}
	 	sb.append(valiable);
    	sb.append(specialAssign.getColumnNameNoDash());
    	return sb.toString();
	}
	
	public static String parseInstanceValiable(ColumnBean columnBean){
		String valiable=parseMethodValiable(columnBean);
		if(StringUtils.isBlank(valiable)){
			throw new ConfigRuntimeException(WorkBrenchConfigProperty.PROCESS_PRODUCT_EXCETPION_METHOD_NO_ARGUMENT);

	 	}
		return StringUtils.trim(valiable);
	}
	
	private static String parseMethodValiable(ColumnBean specialAssign){
		StringBuffer sb=new StringBuffer();
	 	    	FieldTypeEnum fieldType=specialAssign.getFieldType();
    	if(fieldType==FieldTypeEnum.NOARGUMENT){
    		sb.append("  ");
    	}else{
 			String specialAssignName = specialAssign.getColumnNameNoDash();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethodAssignment methods specialAssignName:\t" + specialAssignName);
			}

			String specialAssignEntity = specialAssign.getColumnNameCapitalized();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethodAssignment methods specialAssignEntity:\t" + specialAssignEntity);
			}

			String specialAssignType = specialAssign.getColumnType();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethodAssignment methods specialAssignType:\t" + specialAssignType);
			}
			
			//参数为基本类型
			if(fieldType==FieldTypeEnum.PRIMARY||fieldType==FieldTypeEnum.PRI){
				sb.append("  ");
				sb.append(specialAssign.getColumnType());
				sb.append("  ");
				//sb.append(specialAssign.getColumnNameNoDash());
			}else if (fieldType == FieldTypeEnum.MAPKEY){
				String extra = specialAssign.getExtra();
				Assert.notNull(extra, "ProductManager parseMethodResponseType " + "extra must not null");
				if (logger.isDebugEnabled()) {
					logger.debug("parseMethod extra:\t" + extra);
				}
				sb.append(" Map< ");
				sb.append(extra);
				sb.append(" ,  ");
				sb.append(specialAssign.getColumnType());
				sb.append(" > ");
				//sb.append(specialAssign.getColumnNameNoDash());
				
			}else if (fieldType == FieldTypeEnum.LIST){
				sb.append(" List< ");
				sb.append(specialAssign.getColumnType());
				sb.append(" > ");//Pageable paramPageable
				//sb.append(specialAssign.getColumnNameNoDash());
			}else if(fieldType == FieldTypeEnum.Page){
				sb.append(" Pageable ");
				//sb.append(specialAssign.getColumnNameNoDash());
				
			} else if (fieldType == FieldTypeEnum.ENTITY || fieldType == FieldTypeEnum.DOMAIN){
				sb.append(specialAssign.getColumnType());
				sb.append(" ");
				//sb.append(specialAssign.getColumnNameNoDash());
			}else{
				throw new ConfigRuntimeException(WorkBrenchConfigProperty.PROCESS_PRODUCT_EXCETPION_METHOD_NO_ARGUMENT);
			}
	
    	}
    	return sb.toString();
	}
	public static void parseClassComment(BusinessDescripter businessDescripter,String classComment){
		businessDescripter.setComment(classComment);
	}
	
	public static void parseComponentCategory(BusinessDescripter businessDescripter,
			MethCategoryEnum componentCategory){
		businessDescripter.setComponentCategory(componentCategory);
	}

	public static void parseClassServiceImport(BusinessDescripter businessDescripter, Set<ServiceBean> serviceBeans){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
			logger.debug("className:\t" + serviceBeans);
		}
		
		if (null==businessDescripter||CollectionUtils.isEmpty(serviceBeans)) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		for(ServiceBean serviceBean:serviceBeans){
			parseClassServiceImport(businessDescripter, serviceBean);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
	}
	
	public static void parseClassServiceImport(BusinessDescripter businessDescripter, ServiceBean serviceBean){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseClassServiceImport Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
			logger.debug("className:\t" + serviceBean);
		}
		
		if (null==businessDescripter||null==serviceBean) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		if(serviceBean.getSerivceType()==businessDescripter.getClassName()){
			return;
		}
		businessDescripter.addIncludeClass(serviceBean.getSerivceType());
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseClassServiceImport Over");
		}
	}
	public static void parseClassTableBeanImport(BusinessDescripter businessDescripter, Set<TableBean> includeClasses){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
			logger.debug("className:\t" + includeClasses);
		}
		
		if (null==businessDescripter||CollectionUtils.isEmpty(includeClasses)) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		for(TableBean entity:includeClasses){
			if(entity.getTableNameCapitalized()==businessDescripter.getClassName()){
				continue;
			}
			businessDescripter.addIncludeClass(entity.getTableNameCapitalized());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
	}
	
	public static void parseClassName(BusinessDescripter businessDescripter,String className){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
			logger.debug("className:\t" + className);
		}
		
		if (null==businessDescripter||null==className) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		businessDescripter.setClassName(className);
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
	}
	
	/**
	 * 
	 * @param methCategoryEnum
	 * @param businessDescripter
	 * @param classAnnotation
	 */
	public static void parseHeaderAnnotation(MethCategoryEnum methCategoryEnum,BusinessDescripter businessDescripter,String classAnnotation){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
			logger.debug("classAnnotation:\t" + classAnnotation);
		}
		
		if (null==businessDescripter||null==classAnnotation) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		if(isControllerByCategory(methCategoryEnum)){
			businessDescripter.addHeaderAnnotation(parseAnnotation(AnnotationTypeEnum.Controller.name(),classAnnotation));
		}else if(isDaoByCategory(methCategoryEnum)){
			businessDescripter.addHeaderAnnotation(parseAnnotation(AnnotationTypeEnum.Repository.name(),classAnnotation));
		}else if(isAtomServiceByCategory(methCategoryEnum)){
			businessDescripter.addHeaderAnnotation(parseAnnotation(AnnotationTypeEnum.Resource.name(),classAnnotation));
		}else if(isBusinessServiceByCategory(methCategoryEnum)) {
			businessDescripter.addHeaderAnnotation(parseAnnotation(AnnotationTypeEnum.Service.name(),classAnnotation));
		}else {
			//RADIS,MONGODB,ELASTICSEARCH
			isCachOrSearchOrNoSQLByCategory(methCategoryEnum);
		}
			
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
		
	}
	
	public static void  parseInstanceCalleeAnnotation(BusinessDescripter businessDescripter,
	Set<AnnotationBean> annotationBeanSet){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseInstanceCalleeAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
		}
		
		//类头注解，对于Controller，则需要加上RequestMapping
		if (null==businessDescripter||CollectionUtils.isEmpty(annotationBeanSet)) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		//当是Controller是增加@RequestMapping("/"),其它组件按照加入的头配置增加，如果是DAO方式
		for(AnnotationBean annotation:annotationBeanSet){
			AnnotationTypeEnum annotationType=annotation.getAnnnoteKey();
			Assert.notNull(annotationType,"ProductManager annotationType must not null");
			String annotationValue=annotation.getAnnoteValue();
			if(StringUtils.isBlank(annotationValue)){
				businessDescripter.addInstanceAnnotation(parseAnnotation(annotationType.name()));
			}else{
				businessDescripter.addInstanceAnnotation(parseAnnotation(annotationType.name(),annotationValue));
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
	}

	public static void parseHeaderAnnotation(MethCategoryEnum methCategoryEnum,BusinessDescripter businessDescripter,
			Set<AnnotationBean> annotationBeanSet){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
		}
		
		//类头注解，对于Controller，则需要加上RequestMapping
		if (null==businessDescripter||CollectionUtils.isEmpty(annotationBeanSet)) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		//当是Controller是增加@RequestMapping("/"),其它组件按照加入的头配置增加，如果是DAO方式
		for(AnnotationBean annotation:annotationBeanSet){
			parseHeaderAnnotation( methCategoryEnum, businessDescripter,
					annotation);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
	}
	
	public static void parseHeaderAnnotation(MethCategoryEnum methCategoryEnum,BusinessDescripter businessDescripter,
			AnnotationBean annotationBean){
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Begin:");
			logger.debug("businessDescripter:\t" + businessDescripter);
		}
		
		if (null==businessDescripter||null==annotationBean) {
			logger.warn("businessDescripter and snnotationBeanSet must not null,");
			return ;
		}
		AnnotationTypeEnum annotationType=annotationBean.getAnnnoteKey();
		Assert.notNull(annotationType,"ProductManager annotationType must not null");
		String annotationValue=annotationBean.getAnnoteValue();
		if(StringUtils.isBlank(annotationValue)){
			businessDescripter.addHeaderAnnotation(parseAnnotation(annotationType.name()));
		}else{
			businessDescripter.addHeaderAnnotation(parseAnnotation(annotationType.name(),annotationValue));
		}
		if (logger.isDebugEnabled()) {
			logger.debug("ProductManager parseAnnotation Over");
		}
	}
	
	private static boolean isControllerByCategory(MethCategoryEnum methCategoryEnum){
		boolean isController=false;
		if(methCategoryEnum==RunConfigure.GETPATHCategory||methCategoryEnum==RunConfigure.GETCategory
				||methCategoryEnum==RunConfigure.POSTCategory||methCategoryEnum==RunConfigure.UPDATECategory||
				methCategoryEnum==RunConfigure.DELETECategory||methCategoryEnum==RunConfigure.PUTCategory){
			isController= true;
		}
		return isController;
	}
	
	private static boolean isAtomServiceByCategory(MethCategoryEnum methCategoryEnum){
		boolean isAtomService=false;
		if(methCategoryEnum==RunConfigure.ATOMCategory){
			isAtomService=true;
		}
		return isAtomService;
	}
	
	private static boolean isBusinessServiceByCategory(MethCategoryEnum methCategoryEnum){
		boolean isBusiness=false;
		if(methCategoryEnum==RunConfigure.BNSCategory){
			isBusiness=true;
		}
		return isBusiness;
	}
	
	private static boolean isDaoByCategory(MethCategoryEnum methCategoryEnum){
		boolean isDao=false;
		if(methCategoryEnum==RunConfigure.JDBCCategory||methCategoryEnum==RunConfigure.JPACategory
				||methCategoryEnum==RunConfigure.MYBATISCategory){
			isDao= true;
		}
		return isDao;
	}
	
	private static boolean isCachOrSearchOrNoSQLByCategory(MethCategoryEnum methCategoryEnum){
		throw new ConfigRuntimeException(WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_NO_REALIZE);
	}
	
	private static String parseAnnotation(String annotation){
		return "@"+ annotation;
	}
	
	/**
	 * 形成带名称注解
	 * @Bean("bookId")
	 * @Service("bookId")
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private static String parseAnnotation(String annotation,String value){
		return "@"+ annotation+"(\""+value+"\")";
	}
	
	public static void parseTypeToIOS( final ColumnBean bean){
		if(bean.getFieldType()==FieldTypeEnum.LIST&&bean.getIsMutable()){
			bean.setColumnTypeIOS("NSArray");
			bean.setColumnTypePropertyIOS("property(nonatomic,copy)NSArray *");
			bean.setColumnTypeDescriptionIOS("%@");
			
		}else if(bean.getFieldType()==FieldTypeEnum.LIST&&!bean.getIsMutable()){
			bean.setColumnTypeIOS("NSMutableArray");
			bean.setColumnTypePropertyIOS("property(nonatomic,strong)NSMutableArray *");
			bean.setColumnTypeDescriptionIOS("%@");
		}else if(bean.getFieldType()==FieldTypeEnum.MAPKEY&&bean.getIsMutable()){
			bean.setColumnTypeIOS("NSArray");
			bean.setColumnTypePropertyIOS("property(nonatomic,copy)NSDictonary *");
			bean.setColumnTypeDescriptionIOS("%@");
		}else if(bean.getFieldType()==FieldTypeEnum.LIST&&!bean.getIsMutable()){
			bean.setColumnTypeIOS("NSMutableDictonary");
			bean.setColumnTypePropertyIOS("property(nonatomic,strong)NSMutableDictonary *");
			bean.setColumnTypeDescriptionIOS("%@");
		}else if(bean.getFieldType()==FieldTypeEnum.ENTITY||bean.getFieldType()==FieldTypeEnum.DOMAIN
				&&bean.getIsMutable()){
			bean.setColumnTypeIOS(bean.getColumnType());
			bean.setColumnTypePropertyIOS("property(nonatomic,copy) "+bean.getColumnType()+" *");
			        
			bean.setColumnTypeDescriptionIOS("%@");
		}else if(bean.getFieldType()==FieldTypeEnum.ENTITY||bean.getFieldType()==FieldTypeEnum.DOMAIN
				&&!bean.getIsMutable()){
			bean.setColumnTypeIOS(bean.getColumnType());
			bean.setColumnTypePropertyIOS("property(nonatomic,strong) "+bean.getColumnType()+" *");
			bean.setColumnTypeDescriptionIOS("%@");
		}else{
			parseType( bean,  bean.getColumnType());
		}
		
	}
	public static void parseType( final ColumnBean bean, final String columnType){
		if (columnType.startsWith("varchar") || columnType.startsWith("char") || ("text").equals(columnType)
				|| ("longtext").equals(columnType) || columnType.startsWith("enum")
				||bean.getColumnType().equalsIgnoreCase("String")) {
			bean.setColumnType("String");
			bean.setColumnDBType("VARCHAR");
			bean.setColumnTypeRsGetter("getString");
			bean.setColumnTypeIOS("NSString");
			bean.setColumnTypePropertyIOS("property(nonatomic,copy)NSString *");
			bean.setColumnTypeDescriptionIOS("%@");
		} else if (columnType.startsWith("tinyint") || columnType.startsWith("smallint")
				|| columnType.startsWith("mediumint")||bean.getColumnType().equalsIgnoreCase("Integer")) {
			bean.setColumnType("Integer");
			bean.setColumnDBType("INTEGER");
			bean.setColumnTypeIOS("NSInteger");
			bean.setColumnTypePropertyIOS("property(nonatomic,assign)NSInteger ");
			bean.setColumnTypeDescriptionIOS("%d");
			bean.setColumnTypeRsGetter("getInt");
		} else if (columnType.startsWith("int") || columnType.startsWith("bigint")
				||bean.getColumnType().equalsIgnoreCase("Long")) {
			bean.setColumnType("Long");
			bean.setColumnDBType("BIGINT");
			bean.setColumnTypeIOS("NSInteger");
			bean.setColumnTypePropertyIOS("property(nonatomic,assign)NSInteger ");
			bean.setColumnTypeDescriptionIOS("%d");
			bean.setColumnTypeRsGetter("getLong");
		} else if (("timestamp").equals(columnType) || ("datetime").equals(columnType) 
				|| ("date").equals(columnType)||bean.getColumnType().equalsIgnoreCase("Date")) {
			bean.setColumnType("Date");
			bean.setColumnDBType("DATE");
			bean.setColumnTypeIOS("NSDate");
			bean.setColumnTypePropertyIOS("property(nonatomic,copy)NSDate *");
			bean.setColumnTypeDescriptionIOS("%@");
			bean.setColumnTypeRsGetter("getDate");
			//tableBean.setHasDateColumn(true);
		} else if (columnType.startsWith("float")||bean.getColumnType().equalsIgnoreCase("Float")) {
			bean.setColumnType("Float");
			bean.setColumnTypeIOS("float");
			bean.setColumnDBType("FLOAT");
			bean.setColumnTypePropertyIOS("property(nonatomic,assign)float ");
			bean.setColumnTypeDescriptionIOS("%f");
			bean.setColumnTypeRsGetter("getFloat");
		} else if (columnType.startsWith("double")||bean.getColumnType().equalsIgnoreCase("Double")) {
			bean.setColumnType("Double");
			bean.setColumnDBType("DOUBLE");
			bean.setColumnTypeIOS("double");
			bean.setColumnTypeDescriptionIOS("%E");
			bean.setColumnTypePropertyIOS("property(nonatomic,assign)double ");
			bean.setColumnTypeRsGetter("getDouble");
		} else if (columnType.startsWith("decimal")||bean.getColumnType().equalsIgnoreCase("BigDecimal")) {
			bean.setColumnType("BigDecimal");
			bean.setColumnDBType("decimal");
			bean.setColumnTypeRsGetter("getBigDecimal");
			bean.setColumnTypeIOS("NSNumber");
			bean.setColumnTypeDescriptionIOS("%d");
			bean.setColumnTypePropertyIOS("property(nonatomic,copy)NSNumber *");
			//tableBean.setHasBigDecimal(true);
		} else {
			if(logger.isWarnEnabled()){
				logger.warn("Column Name:" + bean.getColumnName() + "\tUnsupported type: [" + columnType + "]!");
			}
			bean.setColumnDBType("VARCHAR");
			bean.setColumnType("String");
			bean.setColumnTypeRsGetter("getString");
			bean.setColumnTypeIOS("NSString");
			bean.setColumnTypePropertyIOS("property(nonatomic,copy)NSString *");
			bean.setColumnTypeDescriptionIOS("%@");
		}
	}

}
