package com.ndl.framework.workbrench.process;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.FieldRativeEnum;
import com.ndl.framework.workbrench.define.FieldTypeEnum;

public class APIModelForFrontPointManagerTest {

	APIModelManager modelManager=APIModelForFrontPointManager.getInstance();
	@Test
	public void generateAllModelForFrontSimpleFileFormXML(){
		modelManager.generateAllModelForFrontSimpleFileFormXML();
	}

	@Test
	public void generateModelDamainFromRawData(){
		//GET method
		Map<String,String> pathUrl=new HashMap<String,String>();
		//5.guides(霞客)
		pathUrl.put("http://www.ieream.com/mapi/1/guides.php?action=getlist", ".php");
		//6.hotels(临居)
		pathUrl.put("http://www.ieream.com/mapi/1/hotels.php?action=search&filter=regions:0;tags:养,学;keywords:", ".php");
		//2.locations(区域)
		pathUrl.put("http://www.ieream.com/mapi/1/locations.php?action=getlist&loc_type=ream&data_type=exact", ".php");
		//4.reams(游目)
		pathUrl.put("http://www.ieream.com/mapi/1/reams.php?action=search&filter=locations:320000;tags:2,3;keywords", ".php");
		//3.tags(标签)
		pathUrl.put("http://www.ieream.com/mapi/1/tags.php?action=getlist&psize=30", ".php");
		//1.users(用户)
		pathUrl.put("http://www.ieream.com/mapi/1/users.php?action=getitem&uid=102", ".php");
		
	
		modelManager.generateModelDamainFromRawData(pathUrl);
	}
	
	@Test
	public void generateAndroidRestfulForFrontSimpleFileFormXML(){
		((APIModelForFrontPointManager) modelManager).generateAndroidRestfulForFrontSimpleFileFormXML("ieream");
	}

	
	@Test
	public void generateControllerMethodToXML() {
		try {
			// 基本信息
			// Controller签名
			String controllerType = "DataGuidesBean";
			String descipter = "霞客";
			//action=getitem&id=:id
			String controllerUri = "http://www.ieream.com/mapi/1/";
			///guides.php?action=
			String methodUri="guides.php?action=getItemById";
			String controllerMethodSignature = "getItemById";
			String toServiceMethodSignatureDescripter = "获取霞客信";
			
			String toServiceMethodSignature = "getItemById";
			
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
					startColumnBean,returnFieldEnum,methodUri);
			
			
			//manager.set
			controllerMethodSignature = "getList";
			toServiceMethodSignature = "getList";
			methodUri= "guides.php?action=getList";
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
					startColumnBean,returnFieldEnum,methodUri);
			
			//获取霞客相关列表信息(合伙霞客列表)
			controllerMethodSignature = "getRelList";
			//
			methodUri=  "guides.php?action=getRelList";
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
					startColumnBean,returnFieldEnum,methodUri);
			
			assertNotNull(manager);
			
			//生成XML
			manager.generateControllerBeanToXML("ieream");
			
			assertNotNull(manager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
