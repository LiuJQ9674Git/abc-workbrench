package com.ndl.framework.workbrench.process;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

import org.springframework.util.Assert;

import com.ndl.framework.workbrench.define.AnnotationBean;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.ControllerBean;
import com.ndl.framework.workbrench.define.DomainBean;
import com.ndl.framework.workbrench.define.FieldRativeEnum;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.FrontPackage;
import com.ndl.framework.workbrench.define.MethCategoryEnum;
import com.ndl.framework.workbrench.define.MethodBean;
import com.ndl.framework.workbrench.define.MethodCallChainInBodyBean;
import com.ndl.framework.workbrench.define.MethodRuntimeEnum;
import com.ndl.framework.workbrench.define.ServiceBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

public class ControllerManagerTest {
	/**
	 * 方法签名
	 */
	
	@Test
	public void testGenerateControllerBean() {
		// 基本信息
		// Controller签名
		String controllerId = "Login";
		String descipter = "index login addLogin createLogin deleteLogin updateLogin";
		// 缺省的创建Controller
		// 其中的
		ControllerManager manager = ControllerManager.createDefaultControllerHearder(controllerId, descipter);
		assertNotNull(manager);

	}

	@Test
	public void generateFrontTemplateToXML(){
		ControllerManager.generateFrontTemplateToXML("ieream");
	}
	
	@Test
	public void generateControllerMethodToXML() {
		try {
			// 基本信息
			// Controller签名
			String controllerType = "Guides";
			String descipter = "/guides.php";
			//action=getitem&id=:id
			String controllerUri = "";
			String controllerMethodSignature = "getItemById";
			String toServiceMethodSignatureDescripter = "获取霞客信";
			
			String toServiceMethodSignature = "queryLoginByUserNameAndPassword";
			
			ColumnBean startColumnBean = new ColumnBean();
			startColumnBean.setColumnNameNoDash("action");
			startColumnBean.setColumnNameCapitalized("Action");
			startColumnBean.setColumnType("String");
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);
			startColumnBean.setColumnDefault("getitem");

			ColumnBean nextColumnBean = new ColumnBean();
			nextColumnBean.setColumnNameNoDash("id");
			nextColumnBean.setColumnNameCapitalized("Id");
			nextColumnBean.setColumnType("Integer");
			
			nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			startColumnBean.setNextColumnBean(nextColumnBean);

			ControllerManager manager;
			FieldTypeEnum returnFieldEnum=FieldTypeEnum.ENTITY;
			
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					startColumnBean,returnFieldEnum);
			
			
			//manager.set
			controllerMethodSignature = "getList";
			toServiceMethodSignature = "getList";
			toServiceMethodSignatureDescripter = "获取霞客列表";
			startColumnBean = new ColumnBean();
			startColumnBean.setColumnName("p");
			startColumnBean.setColumnType("Integer");//默认值为1
			startColumnBean.setColumnDefault("1");
			startColumnBean.setColumnNameNoDash("p");
			startColumnBean.setColumnNameCapitalized("p");
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);

			ColumnBean psizeColumnBean = new ColumnBean();
			psizeColumnBean.setColumnName("psize");
			psizeColumnBean.setColumnType("Integer");
			psizeColumnBean.setColumnDefault("10");
			psizeColumnBean.setColumnNameNoDash("psize");
			psizeColumnBean.setColumnNameCapitalized("Psize");
			psizeColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			ColumnBean dataTypeColumnBean = new ColumnBean();
			dataTypeColumnBean.setColumnName("dataType");
			dataTypeColumnBean.setColumnType("String");
			dataTypeColumnBean.setColumnNameNoDash("dataType");
			dataTypeColumnBean.setColumnNameCapitalized("DataType");
			dataTypeColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			
			ColumnBean dataOrderColumnBean = new ColumnBean();
			dataOrderColumnBean.setColumnName("dataOrder");
			dataOrderColumnBean.setColumnType("String");
			dataOrderColumnBean.setColumnNameNoDash("dataOrder");
			dataOrderColumnBean.setColumnNameCapitalized("DataOrder");
			dataOrderColumnBean.setNextFieldRative(FieldRativeEnum.Finished);
			
			dataTypeColumnBean.setNextColumnBean(dataOrderColumnBean);
			psizeColumnBean.setNextColumnBean(dataTypeColumnBean);
			startColumnBean.setNextColumnBean(psizeColumnBean);

		
			returnFieldEnum=FieldTypeEnum.LIST;
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					startColumnBean,returnFieldEnum);
			
			//获取霞客相关列表信息(合伙霞客列表)
			controllerMethodSignature = "getRelList";
			//
			controllerMethodSignature = "getRelList";
			toServiceMethodSignature = "getRelList";
			toServiceMethodSignatureDescripter = "获取霞客相关列表信息(合伙霞客列表)";
			startColumnBean = new ColumnBean();
			startColumnBean.setColumnName("uid");
			startColumnBean.setColumnType("Integer");//默认值为1
			startColumnBean.setColumnComment("相关的霞客ID");
			startColumnBean.setColumnDefault("1");
			startColumnBean.setColumnNameNoDash("uid");
			startColumnBean.setColumnNameCapitalized("Uid");
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);

			psizeColumnBean = new ColumnBean();
			psizeColumnBean.setColumnName("p");
			psizeColumnBean.setColumnType("Integer");
			psizeColumnBean.setColumnDefault("1");
			psizeColumnBean.setColumnNameNoDash("p");
			psizeColumnBean.setColumnNameCapitalized("P");
			psizeColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			dataTypeColumnBean = new ColumnBean();
			dataTypeColumnBean.setColumnName("psize");
			dataTypeColumnBean.setColumnType("Integer");
			dataTypeColumnBean.setColumnDefault("10");
			dataTypeColumnBean.setColumnNameNoDash("dataType");
			dataTypeColumnBean.setColumnNameCapitalized("DataType");
			dataTypeColumnBean.setNextFieldRative(FieldRativeEnum.Finished);
			
		
			psizeColumnBean.setNextColumnBean(dataTypeColumnBean);
		
			startColumnBean.setNextColumnBean(psizeColumnBean);
			returnFieldEnum=FieldTypeEnum.LIST;
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					startColumnBean,returnFieldEnum);
			
			assertNotNull(manager);
			
			//生成XML
			manager.generateControllerBeanToXML("ieream");
			
			assertNotNull(manager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void loadFrontBeanFromXML(){
		
		FrontPackage frontBean=ControllerManager.loadFrontPackageFromXML();
		assertNotNull(frontBean);
		Set<ControllerBean> controllerSet=frontBean.getControlleres();
		Assert.notNull(controllerSet,"ControllerBean controllerSet must not empty");
		//ControllerBean[] controllerArray=controllerSet.toArray(new ControllerBean[0]);
		for(ControllerBean controllerBean:controllerSet){
			Set<AnnotationBean> annotationBeanSet=controllerBean.getAnnotationBeanList();
			Set<MethodBean> methodBeanSet=controllerBean.getControllerMethodList();
			Set<TableBean> tableBeanSet=controllerBean.getIncludeBeanList();
			Set<DomainBean> domainBeanSet=controllerBean.getIncludeDomainBeanList();
			Assert.isNull(domainBeanSet, WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_DOMAIN_NO_REALIZE);
			ServiceBean serviceBean=controllerBean.getIncludeService();
			//assertNull(domainBeanSet);
			Assert.notNull(controllerBean,"ControllerBean controllerBean must not null");
			Assert.notNull(serviceBean,"ControllerBean serviceBean must not null");
			System.out.println("ControllerBean:\t"+controllerBean);
			System.out.println("serviceBean:\t"+serviceBean);
			AnnotationBean[] annotationBeanArray=annotationBeanSet.toArray(new AnnotationBean[0]);
			for(AnnotationBean annotationBean:annotationBeanArray){
				System.out.println("annotationBean:\t"+annotationBean);
			}
			//解析方法
			MethodBean[] methodBeanArray=methodBeanSet.toArray(new MethodBean[0]);
			for(MethodBean methodBean:methodBeanArray){
				String methodSignature=methodBean.getSignature();
				Assert.notNull(methodSignature,"ControllerBean methods methodSignature must not null");
				System.out.println("MethodBean:\t"+methodSignature);
				
				ColumnBean responseType=methodBean.getResponseType();
				Assert.notNull(responseType,"ControllerBean methods responseType must not null");
				System.out.println("responseType:\t"+responseType);
				
				MethCategoryEnum catetory=methodBean.getCatetory();
					
				System.out.println("catetory:\t"+catetory);
				
				AnnotationBean methodAonnotation=methodBean.getAnnotationBean();
				Assert.notNull(methodAonnotation,"ControllerBean methods methodAonnotation must not null");
				System.out.println("method methodAonnotation:\t"+methodAonnotation);
				
				ServiceBean calleeMajorService=methodBean.getCalleeMajorService();
				Assert.notNull(calleeMajorService,"ControllerBean methods calleeMajorService must not null");
				System.out.println("method calleeMajorService:\t"+calleeMajorService);
				
				MethodBean runningMethod=calleeMajorService.getRunningMethod();
				Assert.notNull(runningMethod,"ControllerBean methods runningMethod must not null");
				System.out.println("method runningMethod:\t"+runningMethod);
				
				//bodyBean
				MethodCallChainInBodyBean methodCallChainInBodyBean=methodBean.getBodyBean();
				if(null!=methodCallChainInBodyBean){
					//Assert.notNull(methodCallChainInBodyBean,"ControllerBean methods methodCallChainInBodyBean must not null");
					System.out.println("methodCallChainInBodyBean:\t"+methodCallChainInBodyBean);
					
					MethodBean fromMethodBean=methodCallChainInBodyBean.getFromMethod();
					Assert.notNull(fromMethodBean,"ControllerBean methods fromMethodBean must not null");
					System.out.println("methodCallChainInBodyBean fromMethodBean:\t"+fromMethodBean);
					
					ColumnBean fromResultType=methodCallChainInBodyBean.getFromResultType();
					Assert.notNull(fromResultType,"ControllerBean methods fromResultType must not null");
					System.out.println("methodCallChainInBodyBean fromResultType:\t"+fromResultType);
					
					MethodRuntimeEnum methodCallChainInBodyBeanCatetory=methodCallChainInBodyBean.getCatetory();
					Assert.notNull(methodCallChainInBodyBeanCatetory,"ControllerBean methods methodCallChainInBodyBeanCatetory must not null");
					System.out.println("methodCallChainInBodyBean MethodRuntimeEnum:\t"+methodCallChainInBodyBeanCatetory);
						
					MethodCallChainInBodyBean next=methodCallChainInBodyBean.getNext();
					System.out.println("next:\t"+next);
				}
			}
			TableBean[] tableBeanArray=tableBeanSet.toArray(new TableBean[0]);
			for(TableBean tableBean:tableBeanArray){
				System.out.println("TableBean:\t"+tableBean);
			}
			
			assertNotNull(tableBeanArray);
		}
	}
	

	@Test
	public void testGenerateControllerMethod() {
		// 基本信息
		// Controller签名
		String controllerId = "Login";
		String descipter = "index login addLogin createLogin deleteLogin updateLogin";
		// 缺省的创建Controller
		// 头部信息
		ControllerManager manager = ControllerManager.createDefaultControllerHearder(controllerId, descipter);
		assertNotNull(manager);
		// Controller方法
		MethodBean controllerMethod = new MethodBean();
		ServiceBean toServiceBean = manager.getIncludeService();
		controllerMethod.setSignature("login");

		// 业务方法处理
		controllerMethod.addCallBusinessService(toServiceBean);

		// 调用业务服务的方法
		MethodBean currentMethod = new MethodBean();
		// 调用服务方法的方法签名
		currentMethod.setSignature("findLogin");
		// 调用服务方法的返回类型
		ColumnBean responseType = new ColumnBean();
		responseType.setColumnType("Login");
		responseType.setFieldType(FieldTypeEnum.ENTITY);

		currentMethod.setResponseType(responseType);
		// 调用服务方法的参数
		TableBean assignBean = new TableBean();
		assignBean.setTableNameNoDash("login");
		assignBean.setTableNameCapitalized("Login");
		currentMethod.setAssignList(assignBean);
		toServiceBean.setRunningMethod(currentMethod);

		manager.addControllerMethod(controllerMethod);

		assertNotNull(manager);

		// Controller方法
		controllerMethod = new MethodBean();
		toServiceBean = manager.getIncludeService();
		controllerMethod.setSignature("createLogin");

		// 业务方法处理
		controllerMethod.addCallBusinessService(toServiceBean);

		// 调用业务服务的方法
		currentMethod = new MethodBean();
		// 调用服务方法的方法签名
		currentMethod.setSignature("addLogin");
		// 调用服务方法的返回类型
		responseType = new ColumnBean();
		responseType.setColumnType("Login");
		responseType.setFieldType(FieldTypeEnum.ENTITY);

		currentMethod.setResponseType(responseType);
		// 调用服务方法的参数
		assignBean = new TableBean();
		assignBean.setTableNameNoDash("login");
		assignBean.setTableNameCapitalized("Login");
		currentMethod.setAssignList(assignBean);
		toServiceBean.setRunningMethod(currentMethod);

		manager.addControllerMethod(controllerMethod);

		assertNotNull(manager);

	}

}
