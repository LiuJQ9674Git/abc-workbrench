package com.ndl.framework.workbrench.process;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ndl.framework.workbrench.define.AnnotationBean;
import com.ndl.framework.workbrench.define.AnnotationTypeEnum;
import com.ndl.framework.workbrench.define.AtomOrRepositoryPackage;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.DaoBean;
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

public class AtomServiceManagerTest {
	
	@Test
	public void generateAtomServiceTemplateToXML(){
		AtomServiceManager.generateAtomServiceTemplateToXML("test atom service package");
	}
	
	@Test
	public void generateAtomServiceMethodToXML() {
		// 订单原子服务
		try {
			String serviceType = "Order";
			String orderServiceId = "order";
			String descripter = "订单业务的增删改查";
			String businessMethodSignature="queryBusinessOrderByOrderNameAndPageSize";
			String methodSignatureDescripter="查询订单头方法";
			
			AnnotationBean serviceAnnotationBean=new AnnotationBean();
			serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Repository);
			serviceAnnotationBean.setAnnoteValue(orderServiceId);

			ColumnBean startColumnBean = new ColumnBean();
			startColumnBean.setColumnNameNoDash("orderName");
			startColumnBean.setColumnNameCapitalized("OrderName");
			startColumnBean.setColumnType("String");
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);

			ColumnBean productColumnBean = new ColumnBean();
			productColumnBean.setColumnType("String");
			productColumnBean.setColumnNameNoDash("productId");
			productColumnBean.setColumnNameCapitalized("ProductId");
			productColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			
			ColumnBean nextColumnBean = new ColumnBean();
			nextColumnBean.setColumnType("String");
			nextColumnBean.setColumnNameNoDash("pageSize");
			nextColumnBean.setColumnNameCapitalized("PageSize");
			nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			startColumnBean.setNextColumnBean(productColumnBean);
			productColumnBean.setNextColumnBean(nextColumnBean);
			
			ColumnBean returnType=new ColumnBean();
			returnType.setColumnType(serviceType);
			returnType.setFieldType(FieldTypeEnum.Page);
			returnType.setReturnTypeCall(MethodRuntimeEnum.CALL_OUTER);
			
			//调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
			//
			String calleeServiceType="OrderHeader";
			//@Resource(name=“userDao”)
			AnnotationBean calleeServiceAnnotationBean=new AnnotationBean();
			calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
			calleeServiceAnnotationBean.setAnnoteValue(orderServiceId);
			
			String calleeServiceMethodSignature="queryAtomByOrderNameAndPageSize";
			
			ColumnBean calleeStartColumnBean=new ColumnBean();
			calleeStartColumnBean.setColumnType("String");
			calleeStartColumnBean.setColumnNameNoDash("orderName");
			calleeStartColumnBean.setColumnNameCapitalized("OrderName");
			//基本类型直接拷贝
			calleeStartColumnBean.setReferNameNoDash("orderName");
			calleeStartColumnBean.setReferNameCapitalized("OrderName");
			calleeStartColumnBean.setReferType("String");
		
			
			calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);
			calleeStartColumnBean.setFieldType(FieldTypeEnum.ENTITY);//被调用的方法的第一个参数来说明
			
			
			productColumnBean = new ColumnBean();
			productColumnBean.setColumnType("String");
			productColumnBean.setColumnNameNoDash("productId");
			productColumnBean.setColumnNameCapitalized("ProductId");
			productColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			productColumnBean.setColumnType("String");
			productColumnBean.setReferNameNoDash("productId");
			productColumnBean.setReferNameCapitalized("ProductId");
			
			ColumnBean callNextColumnBean = new ColumnBean();
			callNextColumnBean.setColumnNameNoDash("pageSize");
			callNextColumnBean.setColumnNameCapitalized("PageSize");
			callNextColumnBean.setColumnType("String");
			callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			calleeStartColumnBean.setNextColumnBean(productColumnBean);	
			productColumnBean.setNextColumnBean(callNextColumnBean);//callNextColumnBean
			MethCategoryEnum calleeMethodCategory=RunConfigure.JPACategory;
			
			
			//方法体内的返回值及其类型
			ColumnBean calleeReturnType=new ColumnBean();
			calleeReturnType.setColumnType(calleeServiceType);
			calleeReturnType.setColumnNameCapitalized(calleeServiceType);
			calleeReturnType.setColumnNameNoDash("orderHeader");
			calleeReturnType.setColumnType(calleeServiceType);
			calleeReturnType.setExtra("String");
			calleeReturnType.setFieldType(FieldTypeEnum.MAPKEY);
			calleeReturnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
			
			AtomServiceManager manager =AtomServiceManager.addAtomMajorMethod( serviceType,
					 descripter,  businessMethodSignature,
					 methodSignatureDescripter,  startColumnBean,
					 returnType,
					 calleeServiceType, calleeServiceAnnotationBean,
					 calleeServiceMethodSignature, 
					 calleeStartColumnBean,
					 calleeMethodCategory,
					 calleeReturnType);
			//
			assertNotNull(manager);
			//调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
			//
			calleeServiceType="OrderItem";
			//@Resource(name=“userDao”)
			calleeServiceAnnotationBean=new AnnotationBean();
			calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
			calleeServiceAnnotationBean.setAnnoteValue(orderServiceId);
			
			businessMethodSignature="queryBusinessOrderItemByOrderNameAndPageSize";
			calleeServiceMethodSignature="queryAtomByOrderNameAndPageSize";
			
			calleeStartColumnBean=new ColumnBean();
			calleeStartColumnBean.setColumnType("String");
			calleeStartColumnBean.setColumnNameNoDash("orderName");
			calleeStartColumnBean.setColumnNameCapitalized("OrderName");
			calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);

			callNextColumnBean = new ColumnBean();
			callNextColumnBean.setColumnNameNoDash("pageSize");
			callNextColumnBean.setColumnNameCapitalized("PageSize");
			callNextColumnBean.setColumnType("String");
			callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			calleeStartColumnBean.setNextColumnBean(callNextColumnBean);	
			
			calleeMethodCategory=RunConfigure.ATOMCategory;
			
			calleeReturnType=new ColumnBean();
			calleeReturnType.setColumnNameCapitalized(calleeServiceType);
			calleeReturnType.setColumnNameNoDash("orderItem");
			calleeReturnType.setColumnType(calleeServiceType);
			//calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);
			calleeReturnType.setReturnTypeCall(MethodRuntimeEnum.CALL_INTER);
			calleeReturnType.setFieldType(FieldTypeEnum.LIST);
			
			manager =AtomServiceManager.addAtomMajorMethod( serviceType,
					 descripter,  businessMethodSignature,
					 methodSignatureDescripter,  startColumnBean,
					 returnType,
					 calleeServiceType, calleeServiceAnnotationBean,
					 calleeServiceMethodSignature, 
					 calleeStartColumnBean,
					 calleeMethodCategory,
					 calleeReturnType);
			//
			assertNotNull(manager);
			manager.generateAtomServiceBeanToXML("testAtomService");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateDefaultAtomServiceManager() {
		// 订单原子服务
		try {
			String serviceType = "Order";
			String orderServiceId = "order";
			String descripter = "订单业务的增删改查";
			String businessMethodSignature="queryBusinessByOrderNameAndPageSize";
			String methodSignatureDescripter="查询订单头方法";
			
			AnnotationBean serviceAnnotationBean=new AnnotationBean();
			serviceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Service);
			serviceAnnotationBean.setAnnoteValue(orderServiceId+RunConfigure.BUSINESS_SERVICE_SUFFIX);

			ColumnBean startColumnBean = new ColumnBean();
			startColumnBean.setColumnName("OrderName");
			startColumnBean.setColumnType("String");
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);

			ColumnBean nextColumnBean = new ColumnBean();
			nextColumnBean.setColumnName("PageSize");
			nextColumnBean.setColumnType("String");
			nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			startColumnBean.setNextColumnBean(nextColumnBean);
			
			
			ColumnBean returnType=new ColumnBean();
			returnType.setColumnType(serviceType);
			returnType.setFieldType(FieldTypeEnum.Page);
			
			
			//调用的第一个服务，调用的第一个服务，或者称为主服务，它是在业务方法需要引入的实例变量。
			//
			String calleeServiceType="OrderHeader";
			//@Resource(name=“userDao”)
			AnnotationBean calleeServiceAnnotationBean=new AnnotationBean();
			calleeServiceAnnotationBean.setAnnnoteKey(AnnotationTypeEnum.Qualifier);
			calleeServiceAnnotationBean.setAnnoteValue(orderServiceId);
			
			String calleeServiceMethodSignature="queryAtomByOrderNameAndPageSize";
			
			ColumnBean calleeStartColumnBean=new ColumnBean();
			calleeStartColumnBean.setColumnName("OrderName");
			calleeStartColumnBean.setColumnType("String");
			calleeStartColumnBean.setNextFieldRative(FieldRativeEnum.And);

			ColumnBean callNextColumnBean = new ColumnBean();
			callNextColumnBean.setColumnName("PageSize");
			callNextColumnBean.setColumnType("String");
			callNextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			calleeStartColumnBean.setNextColumnBean(callNextColumnBean);	
			
			MethCategoryEnum calleeMethodCategory=RunConfigure.JPACategory;
			
			ColumnBean calleeReturnType=new ColumnBean();
			calleeReturnType.setColumnType(calleeServiceType);
			calleeReturnType.setFieldType(FieldTypeEnum.ENTITY);
			
			AtomServiceManager manager =AtomServiceManager.addAtomMajorMethod( serviceType,
					serviceAnnotationBean,  descripter,  businessMethodSignature,
					 methodSignatureDescripter,  startColumnBean,
					 returnType,
					 calleeServiceType, calleeServiceAnnotationBean,
					 calleeServiceMethodSignature, 
					 calleeStartColumnBean,
					 calleeMethodCategory,
					 calleeReturnType);
			//
			assertNotNull(manager);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
