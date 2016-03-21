package com.ndl.framework.workbrench.process;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ndl.framework.workbrench.define.AnnotationBean;
import com.ndl.framework.workbrench.define.AnnotationTypeEnum;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.DaoBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.FieldRativeEnum;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodBodyFragment;
import com.ndl.framework.workbrench.define.MethodCallChainInBodyBean;
import com.ndl.framework.workbrench.define.MethodDescripter;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.util.ProductParseTemplateUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

public class MethodManager {
	private static final Logger logger = LoggerFactory.getLogger(MethodManager.class);
	private final MethodBean methodBean;

	private final MethodCallChainInBodyBean bodyBean;;
	private static final java.util.concurrent.atomic.AtomicInteger 
		methodBodyFragmentSeq=new java.util.concurrent.atomic.AtomicInteger();
	
	private static final java.util.concurrent.atomic.AtomicInteger 
	currentFragmentSeq=new java.util.concurrent.atomic.AtomicInteger();
	
	
	public MethodManager() {
		methodBean = new MethodBean();
		bodyBean = new MethodCallChainInBodyBean();
		methodBean.setBodyBean(bodyBean);
	}


	private MethodManager(String signature, String uri, String signatureDescripter, MethCategoryEnum methodCategory,
			TableBean entityBean) {
		this(signature, uri, signatureDescripter, methodCategory, entityBean, FieldTypeEnum.ENTITY,MethodRuntimeEnum.CALL_OUTER);

	}

	// 下面方法为Controller构造方法提供的构造函数
	private MethodManager(String signature, String uri, String signatureDescripter, MethCategoryEnum methodCategory,
			TableBean entityBean, FieldTypeEnum fieldTypeEnum,MethodRuntimeEnum methodRuntimeEnum) {
		methodBean = new MethodBean();
		bodyBean = new MethodCallChainInBodyBean();
		methodBean.setBodyBean(bodyBean);
		// 返回值类型fieldTypeEnum
		ColumnBean returnType = new ColumnBean();
		returnType.setFieldType(fieldTypeEnum);
		/**
		 * PRIMARY,//Java基本类型
		 * PRI,//基本类型并且为数据库主键
		 * LIST,//则为ArrayList<JavaBean>
		 * MAPKEY,//则为HashMap< objKey, JavaBean>
    	 * ENTITY,//实体Bean
    	 * DOMAIN,//域模型
    	 * Page,//实体分页
		 * NORETURN,//在返回值时表示无返回值，在入参时表示无入参
		 * NOARGUMENT//无参数
		 */
		if(fieldTypeEnum==FieldTypeEnum.PRIMARY||fieldTypeEnum==FieldTypeEnum.PRI){
			returnType.setColumnType("Object");//Java基本类型，默认为String
		}else if(fieldTypeEnum==FieldTypeEnum.ENTITY||fieldTypeEnum==FieldTypeEnum.DOMAIN){
			returnType.setColumnType(entityBean.getTableNameCapitalized());//Java基本类型，默认为String
		}else if(fieldTypeEnum==FieldTypeEnum.MAPKEY){
			returnType.setColumnType("Map");//Java基本类型，默认为String
		}else if(fieldTypeEnum==FieldTypeEnum.LIST||fieldTypeEnum==FieldTypeEnum.Page){
			returnType.setColumnType("List");//Java基本类型，默认为String
		}else if(fieldTypeEnum==FieldTypeEnum.NORETURN){
			returnType.setColumnType("void");//Java基本类型，默认为String
		}

		//returnType.setColumnType(runConfigure.getControllerMethodReturnType());
		setMethodBeanInfo(signature, signatureDescripter, returnType, methodCategory,methodRuntimeEnum);
		setAssignList(entityBean);
		AnnotationBean annotationBean = new AnnotationBean();
		if(methodCategory==MethCategoryEnum.JPA||methodCategory==MethCategoryEnum.JDBC
				||methodCategory==MethCategoryEnum.MBT){
			annotationBean.setAnnnoteKey(AnnotationTypeEnum.Query);
		}else if(methodCategory==MethCategoryEnum.ATM){
			annotationBean.setAnnnoteKey(AnnotationTypeEnum.Transactional);
		}else if(methodCategory==MethCategoryEnum.GET||methodCategory==MethCategoryEnum.GETPATH
				||methodCategory==MethCategoryEnum.POST){
			annotationBean.setAnnnoteKey(AnnotationTypeEnum.RequestMapping);
		}
		if (StringUtils.isBlank(uri)) {
			annotationBean.setAnnoteValue(signature);
		} else {
			annotationBean.setAnnoteValue(uri);
		}
		setAnnotationBean(annotationBean);

	}
	private MethodManager(String signature, String signatureDescripter, MethCategoryEnum methodCategory,
			TableBean entityBean,ColumnBean returnType) {
		this(signature, signature, signatureDescripter, methodCategory, entityBean, FieldTypeEnum.ENTITY,MethodRuntimeEnum.CALL_OUTER);
		setResultColumnBean(returnType);
	}
	/**
	 * 
	 * @param signature
	 *            签名
	 * @param signatureDescripter
	 *            签名描述
	 * @param entityBean
	 *            需要转义的实体类型
	 * @return
	 */
	public static MethodManager createControllerMethodPost(String signature, String uri, String signatureDescripter,
			TableBean entityBean) {
		return createMothod(signature, uri, signatureDescripter, entityBean, MethCategoryEnum.POST);
	}

	public static MethodManager createControllerMethodGetModelAttribute(String signature, String uri,
			String signatureDescripter, TableBean entityBean) {
		return createMothod(signature, uri, signatureDescripter, entityBean, MethCategoryEnum.GET);
	}

	public static MethodManager createControllerMothodGetPathVariable(String signature, String uri,
			String signatureDescripter, TableBean entityBean) {
		return createMothod(signature, uri, signatureDescripter, entityBean, MethCategoryEnum.GETPATH);
	}

	public static MethodManager createMothod(String signature, String annotation, String signatureDescripter,
			TableBean entityBean, MethCategoryEnum methCategoryEnum) {
		if (logger.isDebugEnabled()) {
			logger.debug("Begin:");
			logger.debug("signature:\t" + signature);
			logger.debug("signatureDescripter:\t" + signatureDescripter);
		}
		if (StringUtils.isBlank(signature) || null == entityBean) {
			if (logger.isErrorEnabled()) {
				logger.error("Over: createControllerMothodPost name signature");
				logger.error("Or entityBean is null.");
			}
			return null;
		}
		// 如果是PathVariable时需要对签名增加PathVariable注解
		Assert.notNull(entityBean, "EntityBean must not be null");
		Assert.notNull(entityBean.getMethodArugment(), "Argument must not be null");

		MethodManager methodManager = new MethodManager(signature, annotation, signatureDescripter, methCategoryEnum,
				entityBean);
		if (logger.isDebugEnabled()) {
			logger.debug("Over:");
			logger.debug("methodManager:\t" + methodManager.methodBean);
		}
		return methodManager;
	}

	public static MethodManager createMothod(String signature, String signatureDescripter, TableBean entityBean,
			MethCategoryEnum methCategoryEnum, ColumnBean returnType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Begin:");
			logger.debug("signature:\t" + signature);
			logger.debug("signatureDescripter:\t" + signatureDescripter);
		}
		if (StringUtils.isBlank(signature) || null == entityBean) {
			if (logger.isErrorEnabled()) {
				logger.error("Over: createControllerMothodPost name signature");
				logger.error("Or entityBean is null.");
			}
			return null;
		}
		// 如果是PathVariable时需要对签名增加PathVariable注解
		Assert.notNull(entityBean, "EntityBean must not be null");
		Assert.notNull(entityBean.getMethodArugment(), "Argument must not be null");

		MethodManager methodManager = new MethodManager(signature, signatureDescripter, methCategoryEnum, entityBean,
				returnType);
		if (logger.isDebugEnabled()) {
			logger.debug("Over:");
			logger.debug("methodManager:\t" + methodManager);
		}
		return methodManager;
	}

	public static void parseMethod(MethodBean methodBean, String className, MethCategoryEnum serviceCategory) {
		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod Start:");
			logger.debug("methodBean:\t" + methodBean);
		}
		String signature = methodBean.getSignature();
		Assert.notNull(signature, "parseMethod reposeType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethod signature:\t" + signature);
		}
		ProductParseTemplateUtil.parseMethodClassName(methodBean.getMethodDescripter(), signature);

		String descripter = methodBean.getSignatureDescripter();
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethod descripter:\t" + descripter);
		}
		ProductParseTemplateUtil.parseMethodDescripter(methodBean.getMethodDescripter(), descripter, className, serviceCategory);

		MethCategoryEnum methodCategory = methodBean.getCatetory();
		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod methodCategory:\t" + methodCategory);
		}

		ColumnBean reposeType = methodBean.getResponseType();
		ColumnBean resultColumnBean=methodBean.getBodyBean().getResultColumnBean();
		Assert.notNull(reposeType, "parseMethod reposeType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod reposeType :\t" + reposeType);
			logger.debug("parseMethod resultColumnBean :\t" + resultColumnBean);
		}
		if(resultColumnBean==null){
			
			ProductParseTemplateUtil.parseMethodResponseType(methodBean.getMethodDescripter(), reposeType,reposeType);
		}else{
			ProductParseTemplateUtil.parseMethodResponseType(methodBean.getMethodDescripter(), reposeType,resultColumnBean);
		}
		AnnotationBean annotationBean = methodBean.getAnnotationBean();
		if (null != annotationBean) {
			Assert.notNull(annotationBean, "parseMethod  annotationBean must not null");
			logger.debug("parseMethod annotationBean key:\t" + annotationBean.getAnnnoteKey());
			logger.debug("parseMethod annotationBean value:\t" + annotationBean.getAnnoteValue());
		}
		ProductParseTemplateUtil.parseMethodAnnotation(methodBean.getMethodDescripter(), annotationBean);
		
		Set<DomainBean> domainBeans = methodBean.getAssignDomainList();
		Assert.isNull(domainBeans, WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_DOMAIN_NO_REALIZE);

		// 方法参数处理
		TableBean methodAssign = methodBean.getAssignList();
		logger.debug("parseMethod methods assigns:\t" + methodAssign);
		String signatureEntirety = methodBean.getMethodDescripter().getSignatureEntirety();
		methodBean.getMethodDescripter().setSignatureEntirety(signatureEntirety + "(");
		if (methodAssign != null) {
			String assignEntityName = methodAssign.getTableNameNoDash();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod methods assignEntityName:\t" + assignEntityName);
			}
			String assignEnityType = methodAssign.getTableNameCapitalized();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod methods assignEnityType:\t" + assignEnityType);
			}
			ColumnBean specialAssign = methodAssign.getMethodArugment();
			if (specialAssign != null) {
				ColumnBean nextSpecialAssign = specialAssign.getNextColumnBean();
				if (logger.isDebugEnabled()) {
					logger.debug("parseMethod methods nextSpecialAssign:\t" + nextSpecialAssign);
				}
				//
				FieldRativeEnum fieldRative = specialAssign.getNextFieldRative();
				logger.debug("parseMethod methods fieldRative:\t" + fieldRative);
				// 解析完成第一个参数
				ProductParseTemplateUtil.parseMethodAssignment(methodBean.getMethodDescripter(), specialAssign);

				if (fieldRative != RunConfigure.AssignFinishedCategory) {
					// nextSpecialAssign=specialAssign.getNextColumnBean();
					signatureEntirety = methodBean.getMethodDescripter().getSignatureEntirety();
					
					while (null != nextSpecialAssign) {
						methodBean.getMethodDescripter().setSignatureEntirety(signatureEntirety + " , ");
						String specialAssignNameRative = nextSpecialAssign.getColumnNameNoDash();
						if (logger.isDebugEnabled()) {
							logger.debug("parseMethod methods specialAssignNameRative:\t" + specialAssignNameRative);
						}
						String specialAssignEntitRativey = nextSpecialAssign.getColumnNameCapitalized();

						if (logger.isDebugEnabled()) {
							logger.debug(
									"parseMethod methods specialAssignEntitRativey:\t" + specialAssignEntitRativey);
						}

						String specialAssignTypRativee = nextSpecialAssign.getColumnType();
						if (logger.isDebugEnabled()) {
							logger.debug("parseMethod methods specialAssignTypRativee:\t" + specialAssignTypRativee);
						}
						ProductParseTemplateUtil.parseMethodAssignment(methodBean.getMethodDescripter(), nextSpecialAssign);

						nextSpecialAssign = nextSpecialAssign.getNextColumnBean();
						if (logger.isDebugEnabled()) {
							logger.debug("parseMethod methods nextSpecialAssign:\t" + nextSpecialAssign);
						}

					}
				}
			}
		}
		signatureEntirety = methodBean.getMethodDescripter().getSignatureEntirety();
		if (signatureEntirety.endsWith(",")) {
			signatureEntirety = signatureEntirety.substring(0, signatureEntirety.lastIndexOf(","));
		}
		methodBean.getMethodDescripter().setSignatureEntirety(signatureEntirety + ")");

		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod methods SignatureEntirety:\t"
					+ methodBean.getMethodDescripter().getSignatureEntirety());
		}
		// bodyBean
		MethodCallChainInBodyBean methodCallChainInBodyBean = methodBean.getBodyBean();
		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod methods methodCallChainInBodyBean:\t" + methodCallChainInBodyBean);
		}

		if (null != methodCallChainInBodyBean) {

			// Assert.notNull(methodCallChainInBodyBean,"ControllerBean methods
			// methodCallChainInBodyBean must not null");
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod methodCallChainInBodyBean:\t" + methodCallChainInBodyBean);
			}

			MethodBean fromMethodBean = methodCallChainInBodyBean.getFromMethod();
			Assert.notNull(fromMethodBean, "ControllerBean methods fromMethodBean must not null");
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod methodCallChainInBodyBean fromMethodBean:\t" + fromMethodBean);
			}

			ColumnBean fromResultType = methodCallChainInBodyBean.getFromResultType();
			Assert.notNull(fromResultType, "parseMethod methods fromResultType must not null");
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod methodCallChainInBodyBean fromResultType:\t" + fromResultType);
			}

			MethodRuntimeEnum methodCallChainInBodyBeanCatetory = methodCallChainInBodyBean.getCatetory();
			Assert.notNull(methodCallChainInBodyBeanCatetory,
					"parseMethod methods methodCallChainInBodyBeanCatetory must not null");
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod methodCallChainInBodyBean MethodRuntimeEnum:\t"
						+ methodCallChainInBodyBeanCatetory);
			}
			MethodCallChainInBodyBean next = methodCallChainInBodyBean.getNext();
			if (logger.isDebugEnabled()) {
				logger.debug("parseMethod next:\t" + next);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod Over");
		}
	}

	public static void parseMethodBody(MethodDescripter methodDescripter,
			String calleeMajorServiceId,MethodBean calleeMajorRunningMethod) {
		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod Begin:");
			logger.debug("calleeMajorRunningMethod:\t" + calleeMajorRunningMethod);
			logger.debug("calleeMajorServiceId:\t" + calleeMajorServiceId);
		}
		Assert.notNull(calleeMajorServiceId, " parseMethodBody calleeMajorServiceId must not null");
		Assert.notNull(calleeMajorRunningMethod, " parseMethodBody calleeMajorRunningMethod must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody calleeMajorRunningMethod:\t" + calleeMajorRunningMethod);
		}

		String calleeMajorServiceResponseSignature = calleeMajorRunningMethod.getSignature();
		Assert.notNull(calleeMajorServiceResponseSignature, " mehod calleeMajorServiceResponseSignature must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody calleeMajorServiceResponseSignature:\t" + calleeMajorServiceResponseSignature);
		}
		ColumnBean calleeMajorType = calleeMajorRunningMethod.getResponseType();
		Assert.notNull(calleeMajorType, " mehod calleeMajorServiceResponseType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody calleeMajorServiceResponseType:\t" + calleeMajorType);
		}
		
		MethodRuntimeEnum methodRuntimeEnum=calleeMajorType.getReturnTypeCall();
		Assert.notNull(methodRuntimeEnum, " parseMethodBody methodRuntimeEnum must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody methodRuntimeEnum:\t" + methodRuntimeEnum);
		}

		//解析方法体
		/*
		ColumnBean calleeReturnType=new ColumnBean();
		calleeReturnType.setColumnType(calleeServiceType);
		calleeReturnType.setExtra("String");
		calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);
		calleeReturnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
		*/
		
		String localName=calleeMajorType.getColumnNameNoDash();
		Assert.notNull(localName, " parseMethodBody localName must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody localName:\t" + localName);
		}

		//方法体内调用的方法签名
		String callMethodSignatureEntirty=localName+" = "+calleeMajorServiceId+"."+calleeMajorServiceResponseSignature+" ( ";
		
		String callResponseType=ProductParseTemplateUtil.parseMethodResponseType(calleeMajorType);
		Assert.notNull(callResponseType, " parseMethodBody callResponseType must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody callResponseType:\t" + callResponseType);
		}
		
		String localFieldName=callResponseType+" "+localName;
		MethodBodyFragment methodBodyFragment=new MethodBodyFragment();
		methodBodyFragment.setCalleeMethodName(callMethodSignatureEntirty);
		methodBodyFragment.setCatetory(methodRuntimeEnum);
		methodBodyFragment.setReturnType(callResponseType);
		methodBodyFragment.setLocalFieldName(localFieldName);
		//
		currentFragmentSeq.set(methodBodyFragmentSeq.get());
		methodDescripter.addBodyragment(methodBodyFragmentSeq.getAndIncrement(), methodBodyFragment);
		
		//
		MethCategoryEnum calleeMajorMethodCatetory = calleeMajorRunningMethod.getCatetory();
		Assert.notNull(calleeMajorMethodCatetory, " parseMethodBody calleeMajorMethodCatetory must not null");
		if (logger.isDebugEnabled()) {
			logger.debug(" parseMethodBody calleeMajorMethodCatetory:\t" + calleeMajorMethodCatetory);
		}

		TableBean calleeMajorMethodAssign = calleeMajorRunningMethod.getAssignList();
		Assert.notNull(calleeMajorMethodAssign, " parseMethodBody calleeMajorMethodAssign must not null");
		if (null != calleeMajorMethodAssign) {
			ColumnBean calleeMajorMethodAssignField=calleeMajorMethodAssign.getMethodArugment();
			Assert.notNull(calleeMajorMethodAssignField, " parseMethodBody calleeMajorMethodAssignFields must not null");
			if(null!=calleeMajorMethodAssignField) {
				String calleeMajorMethodAssignFieldEntityName = calleeMajorMethodAssignField.getColumnNameNoDash();
				Assert.notNull(calleeMajorMethodAssignFieldEntityName,
						" parseMethodBody calleeMajorMethodAssignFieldEntityName must not null");
				if (logger.isDebugEnabled()) {
					logger.debug(" methods calleeMajorMethodAssignFieldEntityName:\t"
							+ calleeMajorMethodAssignFieldEntityName);
				}
				//解析参数
				ProductParseTemplateUtil.parseMethodAssignment(methodBodyFragment,calleeMajorMethodAssignField);
				FieldRativeEnum fieldRative = calleeMajorMethodAssignField.getNextFieldRative();
				if (fieldRative != RunConfigure.AssignFinishedCategory) {
					methodBodyFragment.setCalleeMethodName(methodBodyFragment.getCalleeMethodName() + " , ");
					ColumnBean nextAssignField = calleeMajorMethodAssignField.getNextColumnBean();
					while (null != nextAssignField) {
						String nextAssignFieldEntityName = nextAssignField.getColumnNameNoDash();
						Assert.notNull(nextAssignFieldEntityName,
								" parseMethodBody nextAssignFieldEntityName must not null");
						if (logger.isDebugEnabled()) {
							logger.debug(" parseMethodBody nextAssignFieldEntityName:\t" + nextAssignFieldEntityName);
						}
						String nextAssignFieldFieldEntityType = nextAssignField.getColumnNameCapitalized();
						Assert.notNull(nextAssignFieldFieldEntityType,
								" parseMethodBody nextAssignFieldFieldEntityType must not null");
						if (logger.isDebugEnabled()) {
							logger.debug(" parseMethodBody nextAssignFieldFieldEntityType:\t" + nextAssignFieldFieldEntityType);
						}
						String nextAssignFieldFieldType = nextAssignField.getColumnType();
						Assert.notNull(nextAssignFieldFieldType,
								" parseMethodBody nextAssignFieldFieldType must not null");
						if (logger.isDebugEnabled()) {
							logger.debug(" methods nextAssignFieldFieldType:\t" + nextAssignFieldFieldType);
						}
						ProductParseTemplateUtil.parseMethodAssignment(methodBodyFragment,nextAssignField);

						FieldRativeEnum nextFieldRative = nextAssignField.getNextFieldRative();
						Assert.notNull(nextFieldRative, " parseMethodBody nextFieldRative must not null");
						if (logger.isDebugEnabled()) {
							logger.debug(" parseMethodBody nextFieldRative:\t" + nextFieldRative);
						}
						if(nextFieldRative==FieldRativeEnum.Finished){
							break;
						}
						nextAssignField = nextAssignField.getNextColumnBean();
					}
				}
			}
		}
		String signatureEntirety = methodBodyFragment.getCalleeMethodName();
		signatureEntirety=signatureEntirety+" );";
		methodBodyFragment.setCalleeMethodName(signatureEntirety);

		if (logger.isDebugEnabled()) {
			logger.debug("parseMethod Over:");
		}
	}

	/**
	 * 由组装Controller时调用业务服务的入口方法，即设置控制器调用的当前方法
	 */
	public void setCallBusinessServiceMethod(ServiceBean toServiceBean, String signature, ColumnBean responseType,
			TableBean assignBean,MethodRuntimeEnum methodRuntimeEnum) {
		signature = StringUtils.trim(signature);
		Assert.notNull(signature, "calleeServiceId must not null");
		// 业务方法处理
		addCallBusinessService(toServiceBean);

		// 调用业务服务的方法
		MethodBean currentMethod = new MethodBean();
		// 调用服务方法的方法签名
		signature = StringUtils.trim(signature);

		currentMethod.setSignature(signature);

		currentMethod.setResponseType(responseType);

		// 调用服务方法的参数
		currentMethod.setAssignList(assignBean);
		currentMethod.setCatetory(toServiceBean.getServiceCategory());
		this.setBodyBeanCatetory(methodRuntimeEnum);
		toServiceBean.setRunningMethod(currentMethod);

	}

	public void setCallBusinessServiceMethod(DaoBean toDAOService, String signature, ColumnBean responseType,
			TableBean assignBean,MethodRuntimeEnum methodRuntimeEnum) {
		signature = StringUtils.trim(signature);
		Assert.notNull(signature, "calleeServiceId must not null");
		// 业务方法处理
		addCallDAOService(toDAOService);

		// 调用业务服务的方法
		MethodBean currentMethod = new MethodBean();
		// 调用服务方法的方法签名
		currentMethod.setSignature(signature);

		currentMethod.setResponseType(responseType);

		// 调用服务方法的参数
		currentMethod.setAssignList(assignBean);
		currentMethod.setCatetory(toDAOService.getDaoCategory());
		this.setBodyBeanCatetory(methodRuntimeEnum);
		toDAOService.setRunningMethod(currentMethod);

	}

	public void setResponseType(ColumnBean responseType) {
		this.methodBean.setResponseType(responseType);
	}
	public ServiceBean getCalleeMajorService() {
		return methodBean.getCalleeMajorService();
	}

	public void setCalleeMajorService(ServiceBean calleeMajorService) {
		methodBean.setCalleeMajorService(calleeMajorService);
	}

	public DaoBean getCallMajorDAOService() {
		return methodBean.getCallMajorDAOService();
	}

	public void setCallMajorDAOService(DaoBean callMajorDAOService) {
		methodBean.setCallMajorDAOService(callMajorDAOService);
	}

	public MethodBean getMethodBean() {
		return methodBean;
	}

	public MethodCallChainInBodyBean copyToBody2ToBody() {
		MethodCallChainInBodyBean nextBody = new MethodCallChainInBodyBean();
		nextBody.setToMethod(this.bodyBean.getToMethod());
		nextBody.setToResultType(this.bodyBean.getToResultType());
		nextBody.setToTableBean(this.bodyBean.getToTableBean());
		nextBody.setToColumnBean(this.bodyBean.getToColumnBean());
		nextBody.setToServiceBean(this.bodyBean.getToServiceBean());
		setNext(nextBody);
		return nextBody;
	}

	public MethodCallChainInBodyBean copyFromBody2FromBody() {
		MethodCallChainInBodyBean nextBody = new MethodCallChainInBodyBean();
		nextBody.setFromMethod(this.bodyBean.getFromMethod());
		nextBody.setFromResultType(this.bodyBean.getFromResultType());
		nextBody.setFromTableBean(this.bodyBean.getFromTableBean());
		nextBody.setFromColumnBean(this.bodyBean.getFromColumnBean());
		nextBody.setFromServiceBean(this.bodyBean.getFromServiceBean());
		setNext(nextBody);
		return nextBody;
	}

	public MethodCallChainInBodyBean copyFromBody2ToBody() {
		MethodCallChainInBodyBean nextBody = new MethodCallChainInBodyBean();
		nextBody.setToMethod(this.bodyBean.getFromMethod());
		nextBody.setToResultType(this.bodyBean.getFromResultType());
		nextBody.setToTableBean(this.bodyBean.getFromTableBean());
		nextBody.setToColumnBean(this.bodyBean.getFromColumnBean());
		nextBody.setToServiceBean(this.bodyBean.getFromServiceBean());
		setNext(nextBody);
		return nextBody;
	}

	public MethodCallChainInBodyBean copyToBody2FromBody() {
		MethodCallChainInBodyBean nextBody = new MethodCallChainInBodyBean();
		nextBody.setFromMethod(this.bodyBean.getToMethod());
		nextBody.setFromResultType(this.bodyBean.getToResultType());
		nextBody.setFromTableBean(this.bodyBean.getToTableBean());
		nextBody.setFromColumnBean(this.bodyBean.getToColumnBean());
		nextBody.setFromServiceBean(this.bodyBean.getToServiceBean());
		setNext(nextBody);
		return nextBody;
	}

	public MethodCallChainInBodyBean getNext() {
		return bodyBean.getNext();
	}

	public void setNext(MethodCallChainInBodyBean next) {
		if (next == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setNext(next);
	}

	protected void setMethodBeanInfo(String signature, String signatureDescripter, ColumnBean responseType,
			MethCategoryEnum catetory,MethodRuntimeEnum methodRuntimeEnum) {
		if (StringUtils.isBlank(signature) ||  null == responseType) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		signature = StringUtils.trim(signature);
		Assert.notNull(signature, "calleeServiceId must not null");
		methodBean.setSignature(signature);
		methodBean.setSignatureDescripter(signatureDescripter);
		methodBean.setResponseType(responseType);
		methodBean.setCatetory(catetory);
		//
		methodBean.setBodyBean(bodyBean);
		bodyBean.setFromMethod(methodBean);
		bodyBean.setFromResultType(responseType);
		setBodyBeanCatetory(methodRuntimeEnum);
	}

	// 方法中被调用的返回值
	protected void setToResultType(ColumnBean resultType) {
		if (resultType == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setToResultType(resultType);
	}

	protected void setAnnotationBean(AnnotationBean annotationBean) {
		if (annotationBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setAnnotationBean(annotationBean);
	}

	protected void setReturnAnnotation(AnnotationBean returnAnnotation) {
		if (returnAnnotation == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setReturnAnnotation(returnAnnotation);
	}

	protected void setFromServiceBean(ServiceBean fromServiceBean) {
		if (fromServiceBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setFromServiceBean(fromServiceBean);
	}

	protected void setFromDaoBean(DaoBean fromDaoBean) {
		if (fromDaoBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setFromDaoBean(fromDaoBean);
	}

	protected void setFromColumnBean(ColumnBean fromColumnBean) {
		if (fromColumnBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setFromColumnBean(fromColumnBean);
	}

	protected ColumnBean getFromResultType() {
		return bodyBean.getFromResultType();
	}

	protected void setFromResultType(ColumnBean fromResultType) {
		if (fromResultType == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setFromResultType(fromResultType);
	}

	public TableBean getFromTableBean() {
		return bodyBean.getFromTableBean();
	}

	public void setFromTableBean(TableBean fromTableBean) {
		if (fromTableBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setFromTableBean(fromTableBean);
	}

	public DaoBean getFromDaoBean() {
		return bodyBean.getFromDaoBean();
	}

	public void setBodyBeanCatetory(MethodRuntimeEnum catetory) {
		if (catetory == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		setMethodRuntimeTypeInBody(catetory);
		bodyBean.setCatetory(catetory);
	}

	protected MethodRuntimeEnum getMethodRuntimeTypeInBody() {
		return methodBean.getMethodRuntimeTypeInBody();
	}

	protected void setMethodRuntimeTypeInBody(MethodRuntimeEnum methodRuntimeTypeInBody) {
		methodBean.setMethodRuntimeTypeInBody(methodRuntimeTypeInBody);
	}
	
	protected void setToMethod(MethodBean toMethod) {
		if (toMethod == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setToMethod(toMethod);
	}

	protected void setToServiceBean(ServiceBean toServiceBean) {
		if (toServiceBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setToServiceBean(toServiceBean);
	}

	protected void setToDaoBean(DaoBean toDaoBean) {
		if (toDaoBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setToDaoBean(toDaoBean);
	}

	public DaoBean getToDaoBean() {
		return bodyBean.getToDaoBean();
	}

	protected void setToTableBean(TableBean toTableBean) {
		if (toTableBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setToTableBean(toTableBean);
	}

	protected void setToColumnBean(ColumnBean toColumnBean) {
		if (toColumnBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setToColumnBean(toColumnBean);
	}

	protected void setResultTableBean(TableBean resultTableBean) {
		if (resultTableBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setResultTableBean(resultTableBean);
	}

	protected void setResultColumnBean(ColumnBean resultColumnBean) {
		if (resultColumnBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		bodyBean.setResultColumnBean(resultColumnBean);
	}
	
	protected ColumnBean getResultColumnBean() {
		return bodyBean.getResultColumnBean();
	}

	protected void setAssignList(TableBean assignList) {
		if (null == assignList) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setAssignList(assignList);
	}

	protected void setBodyBean(MethodCallChainInBodyBean bodyBean) {
		if (bodyBean == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setBodyBean(bodyBean);
	}

	protected void setAssignDomainList(Set<DomainBean> assignDomainList) {
		if (CollectionUtils.isEmpty(assignDomainList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setAssignDomainList(assignDomainList);
	}

	protected void addAssignDomain(DomainBean assignDomain) {
		if (null == assignDomain) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.addAssignDomain(assignDomain);
	}

	protected void setCallBusinessServiceList(Set<ServiceBean> callBusinessServiceList) {
		if (CollectionUtils.isEmpty(callBusinessServiceList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setCallBusinessServices(callBusinessServiceList);
	}

	protected void addCallBusinessService(ServiceBean callBusinessService) {
		if (null == callBusinessService) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.addCallBusinessService(callBusinessService);
	}

	protected void setCallAtomServiceList(Set<ServiceBean> callAtomServiceList) {
		if (CollectionUtils.isEmpty(callAtomServiceList)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setCallAtomServices(callAtomServiceList);
	}

	protected void addCallAtomService(ServiceBean callAtomService) {
		if (null == callAtomService) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.addCallAtomService(callAtomService);
	}

	protected void setCallDAOServices(Set<DaoBean> callDAOServices) {
		if (CollectionUtils.isEmpty(callDAOServices)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		methodBean.setCallDAOServices(callDAOServices);
	}

	protected void addCallDAOService(DaoBean callDAOService) {
		if (null == callDAOService) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}

		methodBean.addCallDAOService(callDAOService);
	}

	public void buildMethodBean() {
		if (StringUtils.isBlank(methodBean.getSignature())) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		// 方法必须有方法体
		if (methodBean.getBodyBean() == null) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
		if (null == methodBean.getAssignList() || CollectionUtils.isEmpty(methodBean.getAssignDomainList())) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_Method_NO_EXIST_FILED);
		}
	}

}
