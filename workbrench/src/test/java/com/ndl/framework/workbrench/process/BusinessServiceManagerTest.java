package com.ndl.framework.workbrench.process;

import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ndl.framework.workbrench.define.AnnotationBean;
import com.ndl.framework.workbrench.define.AnnotationTypeEnum;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.FieldRativeEnum;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.ServicePackage;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class BusinessServiceManagerTest {
	
	@Test
	public void generateBusinessServiceTemplateToXML(){
		BusinessServiceManager.generateBusinessServiceTemplateToXML("test businessServicePackage");
	}
	
	@Test
	public void generateBusinessServiceMethodToXML() {
		String serviceType = "Order";
		String orderServiceId = "order";
		String descripter = "订单业务的增删改查";
		String businessMethodSignature = "queryBusinessByOrderNameAndPageSize";
		String methodSignatureDescripter = "查询订单头方法";

		AnnotationBean serviceAnnotationBean = new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
		serviceAnnotationBean.setAnnoteValue(orderServiceId+"CustomedBusiness");

		ColumnBean startColumnBean = new ColumnBean();
		startColumnBean.setColumnNameNoDash("orderName");
		startColumnBean.setColumnNameCapitalized("OrderName");
		startColumnBean.setColumnType("String");
		startColumnBean.setNextFieldRative(FieldRativeEnum.And);

		ColumnBean nextColumnBean = new ColumnBean();
		nextColumnBean.setColumnNameNoDash("pageSize");
		nextColumnBean.setColumnNameCapitalized("PageSize");
		nextColumnBean.setColumnType("String");
		nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		startColumnBean.setNextColumnBean(nextColumnBean);

		ColumnBean returnType = new ColumnBean();
		returnType.setColumnType(serviceType);
		returnType.setFieldType(FieldTypeEnum.Page);
		returnType.setReturnTypeCall(MethodRuntimeEnum.CALL_OUTER);
		
		// 调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
		//
		String calleeServiceType = "OrderHeader";
		String calleeServiceId = "orderHeader";
		// @Resource(name=“userDao”)
		AnnotationBean calleeServiceAnnotationBean = new AnnotationBean();
		calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
		calleeServiceAnnotationBean.setAnnoteValue(orderServiceId);

		String calleeServiceMethodSignature = "queryAtomByOrderNameAndPageSize";

		ColumnBean calleeStartColumnBean = new ColumnBean();
		calleeStartColumnBean.setColumnNameNoDash("orderName");
		calleeStartColumnBean.setColumnNameCapitalized("OrderName");
		calleeStartColumnBean.setColumnType("String");
		calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);

		ColumnBean callNextColumnBean = new ColumnBean();
		callNextColumnBean.setColumnNameNoDash("pageSize");
		callNextColumnBean.setColumnNameCapitalized("PageSize");
		callNextColumnBean.setColumnType("String");
		callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		calleeStartColumnBean.setNextColumnBean(callNextColumnBean);

		MethCategoryEnum calleeMethodCategory = RunConfigure.BNSCategory;

		ColumnBean calleeReturnType = new ColumnBean();
		calleeReturnType.setColumnType(calleeServiceType);
		calleeReturnType.setColumnNameNoDash(calleeServiceId);
		calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);
		calleeReturnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
		
		BusinessServiceManager manager = BusinessServiceManager.addServiceMajorMethod(serviceType,
				serviceAnnotationBean, descripter, businessMethodSignature, methodSignatureDescripter, startColumnBean,
				returnType, calleeServiceType, calleeServiceAnnotationBean, calleeServiceMethodSignature,
				calleeStartColumnBean, calleeMethodCategory, calleeReturnType);

		//
		assertNotNull(manager);

		// serviceType = "Product";
		// orderServiceId = "Product";
		descripter = "商品增删改查";
		businessMethodSignature = "queryBusinessByProductCatatoryAndPageSize";
		methodSignatureDescripter = "商品查询";

		serviceAnnotationBean = new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
		serviceAnnotationBean.setAnnoteValue(orderServiceId);

		startColumnBean = new ColumnBean();
		startColumnBean.setColumnNameNoDash("productCatatory");
		startColumnBean.setColumnNameCapitalized("ProductCatatory");
		startColumnBean.setColumnType("String");
		startColumnBean.setNextFieldRative(FieldRativeEnum.And);

		nextColumnBean = new ColumnBean();
		nextColumnBean.setColumnNameNoDash("pageSize");
		nextColumnBean.setColumnNameCapitalized("PageSize");
		nextColumnBean.setColumnType("String");
		nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		startColumnBean.setNextColumnBean(nextColumnBean);

		returnType = new ColumnBean();
		returnType.setColumnType(serviceType);
		returnType.setFieldType(FieldTypeEnum.Page);
		calleeReturnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
		
		// 调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
		//
		calleeServiceType = "ProuctCatory";
		// @Resource(name=“userDao”)
		calleeServiceAnnotationBean = new AnnotationBean();
		calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
		calleeServiceAnnotationBean.setAnnoteValue(orderServiceId );

		calleeServiceMethodSignature = "queryAtomByProuctCatoryAndPageSize";

		calleeStartColumnBean = new ColumnBean();
		// calleeStartColumnBean.setColumnName("ProuctCatoryName");
		calleeStartColumnBean.setColumnNameNoDash("prouctCatoryName");
		calleeStartColumnBean.setColumnNameCapitalized("ProuctCatoryName");
		calleeStartColumnBean.setColumnType("String");
		calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);

		callNextColumnBean = new ColumnBean();
		// callNextColumnBean.setColumnName("PageSize");
		callNextColumnBean.setColumnNameNoDash("pageSize");
		callNextColumnBean.setColumnNameCapitalized("PageSize");
		callNextColumnBean.setColumnType("String");
		callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		calleeStartColumnBean.setNextColumnBean(callNextColumnBean);

		calleeMethodCategory = MethCategoryEnum.ATM;

		calleeReturnType = new ColumnBean();
		calleeReturnType.setColumnType(calleeServiceType);
		calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);
		calleeReturnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
		calleeReturnType.setColumnType(calleeServiceType);
		calleeReturnType.setColumnNameNoDash(calleeServiceId);
		manager = BusinessServiceManager.addServiceMajorMethod(serviceType, serviceAnnotationBean, descripter,
				businessMethodSignature, methodSignatureDescripter, startColumnBean, returnType, calleeServiceType,
				calleeServiceAnnotationBean, calleeServiceMethodSignature, calleeStartColumnBean, calleeMethodCategory,
				calleeReturnType);

		//
		descripter = "商品增删改查";
		businessMethodSignature = "queryBusinessByProductNameAndPageSize";
		methodSignatureDescripter = "商品查询";

		serviceAnnotationBean = new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
		serviceAnnotationBean.setAnnoteValue(orderServiceId );

		startColumnBean = new ColumnBean();
		startColumnBean.setColumnNameNoDash("productDetail");
		startColumnBean.setColumnNameCapitalized("ProductDetail");
		startColumnBean.setColumnType("String");
		startColumnBean.setNextFieldRative(FieldRativeEnum.And);

		nextColumnBean = new ColumnBean();
		nextColumnBean.setColumnNameNoDash("pageSize");
		nextColumnBean.setColumnNameCapitalized("PageSize");
		nextColumnBean.setColumnType("String");
		nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		startColumnBean.setNextColumnBean(nextColumnBean);

		returnType = new ColumnBean();
		returnType.setColumnType(calleeServiceType);
		returnType.setFieldType(FieldTypeEnum.ENTITY);
		returnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
		returnType.setColumnType(calleeServiceType);
		returnType.setColumnNameNoDash(calleeServiceId);
		BusinessServiceManager.addServiceMajorMethod(serviceType, serviceAnnotationBean, descripter,
				businessMethodSignature, methodSignatureDescripter, startColumnBean, returnType);

		// 上面逻辑完成了业务服务中一个业务方法对应一个原子服务或者业务方法的调用
		assertNotNull(manager);
		//
		//
		manager.generateBusinessServiceBeanToXML("testBusinessService");

		BusinessServiceManager bs = BusinessServiceManager.getBusinessServiceManager(serviceType);
		MethodBean methodBean = bs.getMethodBeanBySignature(businessMethodSignature);
		assertNotNull(methodBean);

	}

	@Test
	public void testLoadBusinessServiceFromXML() {
		ServicePackage servicePackage = BusinessServiceManager.loadBusinessServicePackageFromXML();
		assertNotNull(servicePackage);
		String packageName = servicePackage.getBusinessServiceName();
		System.out.println("BusinessService packageName:\t" + packageName);
		Set<ServiceBean> businessServices = servicePackage.getBusinessServices();
		Assert.notNull(businessServices, "BusinessServices must not empty");
		for (ServiceBean businessService : businessServices) {
			Set<MethodBean> businessOwnerMethods = businessService.getServiceMethodList();
			if (null == businessOwnerMethods) {
				continue;// 容错，当业务服务包含业务服务时，没有方法，为匿名服务
			}
			String serviceType = businessService.getSerivceType();
			Assert.notNull(serviceType, "serviceType must not null");
			System.out.println("BusinessService serviceType:\t" + serviceType);

			String serviceId = businessService.getServiceId();
			Assert.notNull(serviceId, "serviceId must not null");
			System.out.println("BusinessService serviceId:\t" + serviceId);

			String businessServiceDescripter = businessService.getServiceDescipter();
			System.out.println("BusinessService businessServiceDescripter:\t" + businessServiceDescripter);

			MethCategoryEnum serviceCategory = businessService.getServiceCategory();
			System.out.println("BusinessService serviceCategory:\t" + serviceCategory);

			Set<AnnotationBean> businessAnnotations = businessService.getAnnotationBeanList();
			Assert.notNull(businessAnnotations, "businessAnnotations must not empty");
			for (AnnotationBean annotationBean : businessAnnotations) {
				AnnotationTypeEnum annnoteKey = annotationBean.getAnnnoteKey();
				Assert.notNull(annnoteKey, "annnoteKey must not null");
				System.out.println("BusinessService annnoteKey:\t" + annnoteKey);

				String annoteValue = annotationBean.getAnnoteValue();
				System.out.println("BusinessService annoteValue:\t" + annoteValue);
			}

			Set<TableBean> businessIncludeBeans = businessService.getIncludeBeanList();
			Assert.notNull(businessIncludeBeans, "businessIncludeBeans must not empty");

			Set<ServiceBean> businessIncludeServices = businessService.getIncludeServiceList();
			Assert.notNull(businessIncludeServices, "businessIncludeServices must not empty");

			Assert.notNull(businessOwnerMethods, "businessOwnerMethods is not empty");

			for (MethodBean methodBean : businessOwnerMethods) {
				String mehodSignature = methodBean.getSignature();
				Assert.notNull(mehodSignature, "BusinessService mehodSignature must not null");
				System.out.println("BusinessService serviceMehodSignatureategory:\t" + mehodSignature);

				String descripter = methodBean.getSignatureDescripter();
				System.out.println("BusinessService serviceMehod descripter:\t" + descripter);

				AnnotationBean annotationBean = methodBean.getAnnotationBean();
				if (null != annotationBean) {
					Assert.notNull(annotationBean, "BusinessService mehod  annotationBean must not null");
					System.out.println("BusinessService annotationBean key:\t" + annotationBean.getAnnnoteKey());
					System.out.println("BusinessService annotationBean value:\t" + annotationBean.getAnnoteValue());
				}
				Set<DomainBean> domainBeans = methodBean.getAssignDomainList();
				Assert.isNull(domainBeans, WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_DOMAIN_NO_REALIZE);
				// 方法参数处理
				TableBean  methodAssign = methodBean.getAssignList();
				Assert.notNull(methodAssign, "BusinessService mehod assigns must not null");
				System.out.println("BusinessService methods assigns:\t" + methodAssign);
				if (methodAssign!=null) {
					String assignEntityName = methodAssign.getTableNameNoDash();
					System.out.println("BusinessService methods assignEntityName:\t" + assignEntityName);

					String assignEnityType = methodAssign.getTableNameCapitalized();
					System.out.println("BusinessService methods assignEnityType:\t" + assignEnityType);

					Set<ColumnBean> mehtodAssignes = methodAssign.getColumnBeanList();
					Assert.notNull(mehtodAssignes, "BusinessService mehod mehtodAssignes must not null");
					if (!CollectionUtils.isEmpty(mehtodAssignes)) {
						for (ColumnBean specialAssign : mehtodAssignes) {
							String specialAssignName = specialAssign.getColumnNameNoDash();
							System.out.println("BusinessService methods specialAssignName:\t" + specialAssignName);

							String specialAssignEntity = specialAssign.getColumnNameCapitalized();
							System.out.println("BusinessService methods specialAssignEntity:\t" + specialAssignEntity);

							String specialAssignType = specialAssign.getColumnType();
							System.out.println("BusinessService methods specialAssignType:\t" + specialAssignType);
							ColumnBean nextSpecialAssign = specialAssign.getNextColumnBean();
							System.out.println("BusinessService methods nextSpecialAssign:\t" + nextSpecialAssign);
							//
							FieldRativeEnum fieldRative = specialAssign.getNextFieldRative();
							System.out.println("BusinessService methods fieldRative:\t" + fieldRative);
							if (fieldRative != RunConfigure.AssignFinishedCategory) {
								// nextSpecialAssign=specialAssign.getNextColumnBean();
								while (null != nextSpecialAssign) {

									String specialAssignNameRative = nextSpecialAssign.getColumnNameNoDash();
									System.out.println("BusinessService methods specialAssignNameRative:\t"
											+ specialAssignNameRative);

									String specialAssignEntitRativey = nextSpecialAssign.getColumnNameCapitalized();
									System.out.println("BusinessService methods specialAssignEntitRativey:\t"
											+ specialAssignEntitRativey);

									String specialAssignTypRativee = nextSpecialAssign.getColumnType();
									System.out.println("BusinessService methods specialAssignTypRativee:\t"
											+ specialAssignTypRativee);
									nextSpecialAssign = nextSpecialAssign.getNextColumnBean();
									System.out.println(
											"BusinessService methods nextSpecialAssign:\t" + nextSpecialAssign);

								}
							}
						}
					}
				}
				// 方法调用服务处理
				ServiceBean calleeMajorService = methodBean.getCalleeMajorService();
				Assert.notNull(calleeMajorService, "BusinessService mehod calleeMajorService must not null");
				System.out.println("BusinessService methods calleeMajorService:\t" + calleeMajorService);

				String calleeMajorServiceType = calleeMajorService.getSerivceType();
				Assert.notNull(calleeMajorServiceType, "BusinessService mehod calleeMajorServiceType must not null");
				System.out.println("BusinessService methods calleeMajorServiceType:\t" + calleeMajorServiceType);

				String calleeMajorServiceId = calleeMajorService.getServiceId();
				Assert.notNull(calleeMajorServiceId, "BusinessService mehod calleeMajorServiceId must not null");
				System.out.println("BusinessService methods calleeMajorServiceId:\t" + calleeMajorServiceId);

				MethodBean calleeMajorRunningMethod = calleeMajorService.getRunningMethod();
				Assert.notNull(calleeMajorRunningMethod,
						"BusinessService mehod calleeMajorRunningMethod must not null");
				System.out.println("BusinessService methods calleeMajorRunningMethod:\t" + calleeMajorRunningMethod);

				String calleeMajorServiceResponseSignature = calleeMajorRunningMethod.getSignature();
				Assert.notNull(calleeMajorServiceResponseSignature,
						"BusinessService mehod calleeMajorServiceResponseSignature must not null");
				System.out.println("BusinessService methods calleeMajorServiceResponseSignature:\t"
						+ calleeMajorServiceResponseSignature);

				MethCategoryEnum calleeMajorMethodCatetory = calleeMajorRunningMethod.getCatetory();
				Assert.notNull(calleeMajorMethodCatetory, "BusinessServic method calleeMajorMethodCatetory must not null");
				System.out.println("BusinessService methods calleeMajorMethodCatetory:\t" + calleeMajorMethodCatetory);

				ColumnBean calleeMajorServiceResponseType = calleeMajorRunningMethod.getResponseType();
				Assert.notNull(calleeMajorServiceResponseType,
						"BusinessService mehod calleeMajorServiceResponseType must not null");
				System.out.println(
						"BusinessService methods calleeMajorServiceResponseType:\t" + calleeMajorServiceResponseType);

				TableBean calleeMajorMethodAssign = calleeMajorRunningMethod.getAssignList();
				Assert.notNull(calleeMajorMethodAssign,
						"BusinessService mehod calleeMajorMethodAssign must not null");
				if(null!= calleeMajorMethodAssign){
					String calleeMajorMethodAssignEntityName=calleeMajorMethodAssign.getTableNameNoDash();
					Assert.notNull(calleeMajorMethodAssignEntityName,
							"BusinessService mehod calleeMajorMethodAssignEntityName must not null");
					System.out.println(
							"BusinessService methods calleeMajorMethodAssignEntityName:\t" + calleeMajorMethodAssignEntityName);

					
					String calleeMajorMethodAssignEntityType=calleeMajorMethodAssign.getTableNameCapitalized();
					Assert.notNull(calleeMajorMethodAssignEntityType,
							"BusinessService mehod calleeMajorMethodAssignEntityType must not null");
					System.out.println(
							"BusinessService methods calleeMajorMethodAssignEntityType:\t" + calleeMajorMethodAssignEntityType);

					
					Set<ColumnBean> calleeMajorMethodAssignFields=calleeMajorMethodAssign.getColumnBeanList();
					Assert.notNull(calleeMajorMethodAssignFields,
							"BusinessService mehod calleeMajorMethodAssignFields must not null");
					for(ColumnBean calleeMajorMethodAssignField :calleeMajorMethodAssignFields){
						String calleeMajorMethodAssignFieldEntityName=calleeMajorMethodAssignField.getColumnNameNoDash();
						Assert.notNull(calleeMajorMethodAssignFieldEntityName,
								"BusinessService mehod calleeMajorMethodAssignFieldEntityName must not null");
						System.out.println(
								"BusinessService methods calleeMajorMethodAssignFieldEntityName:\t" + calleeMajorMethodAssignFieldEntityName);

						String calleeMajorMethodAssignFieldEntityType=calleeMajorMethodAssignField.getColumnNameCapitalized();
						Assert.notNull(calleeMajorMethodAssignEntityType,
								"BusinessService mehod calleeMajorMethodAssignFieldEntityType must not null");
						System.out.println(
								"BusinessService methods calleeMajorMethodAssignFieldEntityType:\t" + calleeMajorMethodAssignFieldEntityType);

						String calleeMajorMethodAssignFieldType=calleeMajorMethodAssignField.getColumnType();
						Assert.notNull(calleeMajorMethodAssignEntityType,
								"BusinessService mehod calleeMajorMethodAssignFieldType must not null");
						System.out.println(
								"BusinessService methods calleeMajorMethodAssignFieldType:\t" + calleeMajorMethodAssignFieldType);

						FieldRativeEnum fieldRative=calleeMajorMethodAssignField.getNextFieldRative();
						if(fieldRative!=RunConfigure.AssignFinishedCategory){
							ColumnBean nextAssignField=calleeMajorMethodAssignField.getNextColumnBean();
							while(null!=nextAssignField){
								String nextAssignFieldEntityName=nextAssignField.getColumnNameNoDash();
								Assert.notNull(calleeMajorMethodAssignEntityType,
										"BusinessService mehod nextAssignFieldEntityName must not null");
								System.out.println(
										"BusinessService methods nextAssignFieldEntityName:\t" + nextAssignFieldEntityName);

								String nextAssignFieldFieldEntityType=nextAssignField.getColumnNameCapitalized();
								Assert.notNull(nextAssignFieldFieldEntityType,
										"BusinessService mehod nextAssignFieldFieldEntityType must not null");
								System.out.println(
										"BusinessService methods nextAssignFieldFieldEntityType:\t" + nextAssignFieldFieldEntityType);

								String nextAssignFieldFieldType=nextAssignField.getColumnType();
								Assert.notNull(calleeMajorMethodAssignEntityType,
										"BusinessService mehod nextAssignFieldFieldType must not null");
								System.out.println(
										"BusinessService methods nextAssignFieldFieldType:\t" + nextAssignFieldFieldType);

								FieldRativeEnum nextFieldRative=nextAssignField.getNextFieldRative();
								Assert.notNull(calleeMajorMethodAssignEntityType,
										"BusinessService mehod nextFieldRative must not null");
								System.out.println(
										"BusinessService methods nextFieldRative:\t" + nextFieldRative);

								nextAssignField=nextAssignField.getNextColumnBean();
							}
						}
					}
				}
				// 方法调用具体服务，需要在头部导入
				Set<ServiceBean> callBusinessServices = methodBean.getCallBusinessServices();

				Set<ServiceBean> callAtomServices = methodBean.getCallAtomServices();
				Set<ServiceBean> callServices = null;
				if (null != callBusinessServices || null != callAtomServices) {
					callServices = new HashSet<ServiceBean>();
					if (!CollectionUtils.isEmpty(callBusinessServices)) {
						callServices.addAll(callBusinessServices);
					}
					if (!CollectionUtils.isEmpty(callAtomServices)) {
						callServices.addAll(callAtomServices);
					}
				}
				Assert.notNull(callServices, "BusinessService mehod callServices must not null");
				System.out.println("BusinessService methods callServices:\t" + callServices);

			}

		}

	}

	
	@Test
	public void testAddServiceMajorMethod() {
		String serviceType = "Order";
		String orderServiceId = "order";
		String descripter = "订单业务的增删改查";
		String businessMethodSignature = "queryBusinessByOrderNameAndPageSize";
		String methodSignatureDescripter = "查询订单头方法";

		AnnotationBean serviceAnnotationBean = new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
		serviceAnnotationBean.setAnnoteValue(orderServiceId);

		ColumnBean startColumnBean = new ColumnBean();
		startColumnBean.setColumnName("OrderName");
		startColumnBean.setColumnType("String");
		startColumnBean.setNextFieldRative(FieldRativeEnum.And);

		ColumnBean nextColumnBean = new ColumnBean();
		nextColumnBean.setColumnName("PageSize");
		nextColumnBean.setColumnType("String");
		nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		startColumnBean.setNextColumnBean(nextColumnBean);

		ColumnBean returnType = new ColumnBean();
		returnType.setColumnType(serviceType);
		returnType.setFieldType(FieldTypeEnum.Page);

		// 调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
		//
		String calleeServiceType = "OrderHeader";
		// @Resource(name=“userDao”)
		AnnotationBean calleeServiceAnnotationBean = new AnnotationBean();
		calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
		calleeServiceAnnotationBean.setAnnoteValue(orderServiceId );

		String calleeServiceMethodSignature = "queryAtomByOrderNameAndPageSize";

		ColumnBean calleeStartColumnBean = new ColumnBean();
		calleeStartColumnBean.setColumnName("OrderName");
		calleeStartColumnBean.setColumnType("String");
		calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);

		ColumnBean callNextColumnBean = new ColumnBean();
		callNextColumnBean.setColumnName("PageSize");
		callNextColumnBean.setColumnType("String");
		callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		calleeStartColumnBean.setNextColumnBean(callNextColumnBean);

		MethCategoryEnum calleeMethodCategory = RunConfigure.BNSCategory;

		ColumnBean calleeReturnType = new ColumnBean();
		calleeReturnType.setColumnType(calleeServiceType);
		calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);

		BusinessServiceManager manager = BusinessServiceManager.addServiceMajorMethod(serviceType,
				serviceAnnotationBean, descripter, businessMethodSignature, methodSignatureDescripter, startColumnBean,
				returnType, calleeServiceType, calleeServiceAnnotationBean, calleeServiceMethodSignature,
				calleeStartColumnBean, calleeMethodCategory, calleeReturnType);

		//
		assertNotNull(manager);

		// serviceType = "Product";
		// orderServiceId = "Product";
		descripter = "商品增删改查";
		businessMethodSignature = "queryBusinessByProductCatatoryAndPageSize";
		methodSignatureDescripter = "商品查询";

		serviceAnnotationBean = new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
		serviceAnnotationBean.setAnnoteValue(orderServiceId );

		startColumnBean = new ColumnBean();
		startColumnBean.setColumnName("ProductCatatory");
		startColumnBean.setColumnType("String");
		startColumnBean.setNextFieldRative(FieldRativeEnum.And);

		nextColumnBean = new ColumnBean();
		nextColumnBean.setColumnName("PageSize");
		nextColumnBean.setColumnType("String");
		nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		startColumnBean.setNextColumnBean(nextColumnBean);

		returnType = new ColumnBean();
		returnType.setColumnType(serviceType);
		returnType.setFieldType(FieldTypeEnum.Page);

		// 调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
		//
		calleeServiceType = "ProuctCatory";
		// @Resource(name=“userDao”)
		calleeServiceAnnotationBean = new AnnotationBean();
		calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
		calleeServiceAnnotationBean.setAnnoteValue(orderServiceId);

		calleeServiceMethodSignature = "queryAtomByProuctCatoryAndPageSize";

		calleeStartColumnBean = new ColumnBean();
		calleeStartColumnBean.setColumnName("ProuctCatoryName");
		calleeStartColumnBean.setColumnType("String");
		calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);

		callNextColumnBean = new ColumnBean();
		callNextColumnBean.setColumnName("PageSize");
		callNextColumnBean.setColumnType("String");
		callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		calleeStartColumnBean.setNextColumnBean(callNextColumnBean);

		calleeMethodCategory = MethCategoryEnum.ATM;

		calleeReturnType = new ColumnBean();
		calleeReturnType.setColumnType(calleeServiceType);
		calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);

		manager = BusinessServiceManager.addServiceMajorMethod(serviceType, serviceAnnotationBean, descripter,
				businessMethodSignature, methodSignatureDescripter, startColumnBean, returnType, calleeServiceType,
				calleeServiceAnnotationBean, calleeServiceMethodSignature, calleeStartColumnBean, calleeMethodCategory,
				calleeReturnType);

		//
		descripter = "商品增删改查";
		businessMethodSignature = "queryBusinessByProductNameAndPageSize";
		methodSignatureDescripter = "商品查询";

		serviceAnnotationBean = new AnnotationBean();
		serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
		serviceAnnotationBean.setAnnoteValue(orderServiceId );

		startColumnBean = new ColumnBean();
		startColumnBean.setColumnName("ProductDetail");
		startColumnBean.setColumnType("String");
		startColumnBean.setNextFieldRative(FieldRativeEnum.And);

		nextColumnBean = new ColumnBean();
		nextColumnBean.setColumnName("PageSize");
		nextColumnBean.setColumnType("String");
		nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

		startColumnBean.setNextColumnBean(nextColumnBean);

		returnType = new ColumnBean();
		returnType.setColumnType(serviceType);
		returnType.setFieldType(FieldTypeEnum.Page);
		BusinessServiceManager.addServiceMajorMethod(serviceType, serviceAnnotationBean, descripter,
				businessMethodSignature, methodSignatureDescripter, startColumnBean, returnType);

		// 上面逻辑完成了业务服务中一个业务方法对应一个原子服务或者业务方法的调用
		assertNotNull(manager);
		//
		BusinessServiceManager bs = BusinessServiceManager.getBusinessServiceManager(serviceType);
		MethodBean methodBean = bs.getMethodBeanBySignature(businessMethodSignature);
		assertNotNull(methodBean);

		//

	}

	@Test
	public void testCreateDefaultBusinessServiceManager() {
		// 订单服务
		try {
			String orderService = "Order";
			String orderServiceId = "order";
			String orderServiceDescipter = "订单业务的增删改查";
			BusinessServiceManager manager = BusinessServiceManager.createDefaultBusinessServiceManager(orderService,
					orderServiceDescipter);
			assertNotNull(manager);
			// 业务方法一般的都有调用原子服务，原子服务实现对数据持久化，或者异步消息的发送
			// 调用服务方法的参数
			TableBean entityBean = new TableBean();
			entityBean.setTableNameNoDash(orderServiceId);
			entityBean.setTableNameCapitalized(orderService);

			// 业务服务的方法调用业务服务
			String orderAtomService = "Product";
			String orderAtomServiceDescipter = "商品业务服务";
			MethodBean controllerMethod = new MethodBean();

			BusinessServiceManager calleeManager = BusinessServiceManager
					.createDefaultBusinessServiceManager(orderAtomService, orderAtomServiceDescipter);

			assertNotNull(calleeManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 业务服务的方法调用原子服务
	}

}
