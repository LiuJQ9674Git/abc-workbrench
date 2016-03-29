package com.ndl.framework.workbrench.process;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.FieldRativeEnum;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.process.APIModelForFrontPointManager.RestfulApiPathInfo;

public class APIModelForFrontPointManagerTest {

	APIModelManager modelManager=APIModelForFrontPointManager.getInstance();
	@Test
	public void generateAllModelForFrontSimpleFileFormXML(){
		modelManager.generateAllModelForFrontSimpleFileFormXML();
	}

	@Test
	public void generateModelDamainFromRawData(){
		//GET method
		Map<String,RestfulApiPathInfo> pathUrl=new HashMap<String,RestfulApiPathInfo>();
		RestfulApiPathInfo restfulApiPathInfo=null;
		/*
		//5.guides(霞客)
		
			restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/guides.php?action=getlist", 
				".php", "GetList");
		pathUrl.put("http://www.ieream.com/mapi/1/guides.php?action=getlist", restfulApiPathInfo);
		
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/guides.php?action=getitem&id=102", 
				".php", "GetItem");
		pathUrl.put("http://www.ieream.com/mapi/1/guides.php?action=getitem&id=102", restfulApiPathInfo);

		//http://www.ieream.com/mapi/1/guides.php?action=getrellist
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/guides.php?action=getrellist", 
				".php", "GetRellist");
		pathUrl.put("http://www.ieream.com/mapi/1/guides.php?action=getrellist", restfulApiPathInfo);

		
		//6.hotels(临居)
		//http://www.ieream.com/mapi/1/hotels.php?action=search&filter=locations:330000;tags:9;key
		
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/hotels.php?action=search&filter=locations:330000;tags:9;key", 
				".php", "Search");
		                              pathUrl.put("http://www.ieream.com/mapi/1/hotels.php?action=search&filter=locations:330000;tags:9;key",
		                            		  restfulApiPathInfo);
		                              
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/hotels.php?action=getitem&id=1", 
					".php", "GetItem");
									  pathUrl.put("http://www.ieream.com/mapi/1/hotels.php?action=getitem&id=1", restfulApiPathInfo);
			
									  //http://www.ieream.com/mapi/1/hotels.php?action=getrellist
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/hotels.php?action=getrellist", 
				".php", "GetRellist");
		                              pathUrl.put("http://www.ieream.com/mapi/1/hotels.php?action=getrellist",
		                            		  restfulApiPathInfo);
		*/                           		  
		//2.locations(区域)
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/locations.php?action=getlist&loc_type=ream&data_type=exact", 
				".php", "GetList");
		pathUrl.put("http://www.ieream.com/mapi/1/locations.php?action=getlist&loc_type=ream&data_type=exact", restfulApiPathInfo);
		
		/* 
		//4.reams(游目)
		
		restfulApiPathInfo=new RestfulApiPathInfo("//http://www.ieream.com/mapi/1/reams.php?action=search&filter=locations:330000;tags:19;keywords", 
				".php", "Search");
		pathUrl.put("//http://www.ieream.com/mapi/1/reams.php?action=search&filter=locations:330000;tags:19;keywords", restfulApiPathInfo);
		
		//http://www.ieream.com/mapi/1/reams.php?action=getitem&id=6
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/reams.php?action=getitem&id=6", 
				".php", "GetItem");
		pathUrl.put("http://www.ieream.com/mapi/1/reams.php?action=getitem&id=6", restfulApiPathInfo);
		
		//http://www.ieream.com/mapi/1/reams.php?action=getlist
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/reams.php?action=getlist", 
				".php", "GetList");
		pathUrl.put("http://www.ieream.com/mapi/1/reams.php?action=getlist", restfulApiPathInfo);
		
		//http://www.ieream.com/mapi/1/reams.php?action=getrellist&id=6
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/reams.php?action=getrellist&id=6", 
					".php", "GetRelllist");
		pathUrl.put("http://www.ieream.com/mapi/1/reams.php?action=getrellist&id=6", restfulApiPathInfo);
		
		//3.tags(标签)
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/tags.php?action=getlist&psize=30", 
				".php", "GetList");
		pathUrl.put("http://www.ieream.com/mapi/1/tags.php?action=getlist&psize=30", restfulApiPathInfo);
		
		//1.users(用户)
		restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/users.php?action=getitem&uid=102", 
				".php", "GetItem");
		pathUrl.put("http://www.ieream.com/mapi/1/users.php?action=getitem&uid=102", restfulApiPathInfo);
		
		*/
		//http://www.ieream.com/mapi/1/helpers.php?action=getupdateinfo&version_type=android&version
		//restfulApiPathInfo=new RestfulApiPathInfo("http://www.ieream.com/mapi/1/helpers.php?action=getupdateinfo&version_type=android&version", 
		//		".php", "Getupdateinfo");
		//pathUrl.put("http://www.ieream.com/mapi/1/users.php?action=getitem&uid=102", restfulApiPathInfo);
		modelManager.generateModelDamainFromRawData(pathUrl);
	}
	
	@Test
	public void generateAndroidRestfulForFrontSimpleFileFormXML(){
		((APIModelForFrontPointManager) modelManager).generateAndroidRestfulForFrontSimpleFileFormXML("ieream");
	}

	@Test
	public void generateLocationsBeanControllerMethodToXML() {
		try {
			// 基本信息
			// Controller签名
			//http://www.ieream.com/mapi/1/locations.php?action=getlist&loc_type=ream&data_type=exact
			String controllerType = "LocationsGetListBean";
			String descipter = "获 获取位置列表";
			//action=getitem&id=:id
			String controllerUri = "http://www.ieream.com/mapi/1/";
			///guides.php?action=
			String methodUri="locations.php?action=getlist";
			String controllerMethodSignature = "getlist";
			String toServiceMethodSignatureDescripter = "获 获取位置列表";
			
			String toServiceMethodSignature = "getlist";
			
			ColumnBean startColumnBean = new ColumnBean();
			startColumnBean.setColumnNameNoDash("p");
			startColumnBean.setColumnNameCapitalized("P");
			startColumnBean.setColumnType("Integer");
			//startColumnBean.setColumnDefault("1");
			//
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);
			

			ColumnBean nextColumnBean = new ColumnBean();
			nextColumnBean.setColumnNameNoDash("psize");
			nextColumnBean.setColumnNameCapitalized("Psize");
			nextColumnBean.setColumnType("Integer");
			//nextColumnBean.setColumnDefault("10");
			nextColumnBean.setNextFieldRative(FieldRativeEnum.And);

			startColumnBean.setNextColumnBean(nextColumnBean);

			ColumnBean loc_typeColumnBean = new ColumnBean();
			loc_typeColumnBean.setColumnNameNoDash("loc_type");
			loc_typeColumnBean.setColumnNameCapitalized("Loc_type");
			loc_typeColumnBean.setColumnType("String");
			loc_typeColumnBean.setColumnDefault("ream");
			loc_typeColumnBean.setNextFieldRative(FieldRativeEnum.And);
			//
			nextColumnBean.setNextColumnBean(loc_typeColumnBean);
			//
			
			ColumnBean loc_orderColumnBean = new ColumnBean();
			loc_orderColumnBean.setColumnNameNoDash("loc_order");
			loc_orderColumnBean.setColumnNameCapitalized("loc_order");
			loc_orderColumnBean.setColumnType("String");
			loc_orderColumnBean.setColumnDefault("asc");
			loc_orderColumnBean.setNextFieldRative(FieldRativeEnum.And);
			//
			loc_typeColumnBean.setNextColumnBean(loc_orderColumnBean);
			
			ColumnBean data_typeColumnBean = new ColumnBean();
			data_typeColumnBean.setColumnNameNoDash("data_type");
			data_typeColumnBean.setColumnNameCapitalized("Data_type");
			data_typeColumnBean.setColumnType("String");
			data_typeColumnBean.setColumnDefault("exact");
			data_typeColumnBean.setNextFieldRative(FieldRativeEnum.Finished);
			loc_orderColumnBean.setNextColumnBean(data_typeColumnBean);
			
			ControllerManager manager;
			FieldTypeEnum returnFieldEnum=FieldTypeEnum.ENTITY;
			ColumnBean returnColumnBeanType=new ColumnBean();
			returnColumnBeanType.setColumnName("LocationsGetListBean");
			returnColumnBeanType.setColumnNameCapitalized("LocationsGetListBean");
			returnColumnBeanType.setColumnNameNoDash("locationsGetListBean");
			returnColumnBeanType.setColumnType("LocationsGetListBean");
			returnColumnBeanType.setFieldType(FieldTypeEnum.ENTITY);
			
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					startColumnBean,methodUri,returnFieldEnum,returnColumnBeanType);

			
			assertNotNull(manager);
			
			//生成XML
			manager.generateControllerBeanToXML("ieream");
			
			assertNotNull(manager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void generateUsersGetupdateinfoBeanControllerMethodToXML() {
		try {
			// 基本信息
			// Controller签名
			String controllerType = "UsersGetupdateinfoBean";
			String descipter = "霞客";
			//action=getitem&id=:id
			String controllerUri = "http://www.ieream.com/mapi/1/";
			///guides.php?action=
			String methodUri="helpers.php?action=getupdateinfo";
			String controllerMethodSignature = "getupdateinfo";
			String toServiceMethodSignatureDescripter = "获取APP版本更新信息";
			
			String toServiceMethodSignature = "getupdateinfo";
			
			ColumnBean startColumnBean = new ColumnBean();
			startColumnBean.setColumnNameNoDash("version_type");
			startColumnBean.setColumnNameCapitalized("Version_type");
			startColumnBean.setColumnType("String");
			//
			startColumnBean.setColumnDefault("android");
			startColumnBean.setNextFieldRative(FieldRativeEnum.And);
			

			ColumnBean nextColumnBean = new ColumnBean();
			nextColumnBean.setColumnNameNoDash("version_id");
			nextColumnBean.setColumnNameCapitalized("version_id");
			nextColumnBean.setColumnType("Integer");
			
			nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			startColumnBean.setNextColumnBean(nextColumnBean);

			ControllerManager manager;
			FieldTypeEnum returnFieldEnum=FieldTypeEnum.ENTITY;
			ColumnBean returnColumnBeanType=new ColumnBean();
			returnColumnBeanType.setColumnName("UsersGetupdateinfoBean");
			returnColumnBeanType.setColumnNameCapitalized("UsersGetupdateinfoBean");
			returnColumnBeanType.setColumnNameNoDash("usersGetupdateinfoBean");
			returnColumnBeanType.setColumnType("UsersGetupdateinfoBean");
			returnColumnBeanType.setFieldType(FieldTypeEnum.ENTITY);
			
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					startColumnBean,methodUri,returnFieldEnum,returnColumnBeanType);

			
			assertNotNull(manager);
			
			//生成XML
			manager.generateControllerBeanToXML("ieream");
			
			assertNotNull(manager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void generateControllerMethodGuidesToXML() {
		try {
			// 基本信息
			// Controller签名
			String controllerType = "GuidesBean";
			String descipter = "霞客";
			//action=getitem&id=:id
			String controllerUri = "http://www.ieream.com/mapi/1/";
			///guides.php?action=
			String methodUri="guides.php?action=getItemById";
			String controllerMethodSignature = "getItemById";
			String toServiceMethodSignatureDescripter = "获取霞客信";
			
			String toServiceMethodSignature = "getItemById";
			
			ColumnBean startColumnBean = new ColumnBean();
			startColumnBean.setColumnNameNoDash("id");
			startColumnBean.setColumnNameCapitalized("Id");
			startColumnBean.setColumnType("Integer");
			startColumnBean.setNextFieldRative(FieldRativeEnum.Finished);
			

			ColumnBean nextColumnBean = new ColumnBean();
			nextColumnBean.setColumnNameNoDash("id");
			nextColumnBean.setColumnNameCapitalized("Id");
			nextColumnBean.setColumnType("Integer");
			
			//nextColumnBean.setNextFieldRative(FieldRativeEnum.Finished);

			startColumnBean.setNextColumnBean(nextColumnBean);

			ControllerManager manager;
			FieldTypeEnum returnFieldEnum=FieldTypeEnum.ENTITY;
			ColumnBean returnColumnBeanType=new ColumnBean();
			returnColumnBeanType.setColumnName("guidesGetItemBean");
			returnColumnBeanType.setColumnNameCapitalized("GuidesGetItemBean");
			returnColumnBeanType.setColumnNameNoDash("guidesGetItemBean");
			returnColumnBeanType.setColumnType("GuidesGetItemBean");
			returnColumnBeanType.setFieldType(FieldTypeEnum.ENTITY);
			
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					startColumnBean,methodUri,returnFieldEnum,returnColumnBeanType);

			controllerMethodSignature = "getList";
			toServiceMethodSignature = "getList";
			methodUri= "guides.php?action=getList";
			toServiceMethodSignatureDescripter = "获取霞客列表";
			ColumnBean getListColumnBean = new ColumnBean();
			getListColumnBean.setColumnName("p");
			getListColumnBean.setColumnType("Integer");//默认值为1
			getListColumnBean.setColumnDefault("1");
			getListColumnBean.setColumnNameNoDash("p");
			getListColumnBean.setColumnNameCapitalized("p");
			getListColumnBean.setNextFieldRative(FieldRativeEnum.And);

			ColumnBean psizeColumnBean = new ColumnBean();
			psizeColumnBean.setColumnName("psize");
			psizeColumnBean.setColumnType("Integer");
			psizeColumnBean.setColumnDefault("10");
			psizeColumnBean.setColumnNameNoDash("psize");
			psizeColumnBean.setColumnNameCapitalized("Psize");
			psizeColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			getListColumnBean.setNextColumnBean(psizeColumnBean);
			
			
			//data_type String  列表数据类型
			ColumnBean data_typeColumnBean = new ColumnBean();
			data_typeColumnBean.setColumnName("data_type");
			data_typeColumnBean.setColumnType("String");
			
			data_typeColumnBean.setColumnNameNoDash("data_type");
			data_typeColumnBean.setColumnNameCapitalized("Data_type");
			data_typeColumnBean.setNextFieldRative(FieldRativeEnum.And); 
			
			psizeColumnBean.setNextColumnBean(data_typeColumnBean);
			
			//data_order String  列表数据排序方向
			
			ColumnBean data_orderColumnBean = new ColumnBean();
			data_orderColumnBean.setColumnName("data_order");
			data_orderColumnBean.setColumnType("String");
			
			data_orderColumnBean.setColumnNameNoDash("data_order");
			data_orderColumnBean.setColumnNameCapitalized("Data_order");
			data_orderColumnBean.setNextFieldRative(FieldRativeEnum.Finished); 
			
			//data_orderColumnBean
			psizeColumnBean.setNextColumnBean(data_orderColumnBean);
			
			
			ColumnBean returnTypeGetList=new ColumnBean();
			returnTypeGetList.setColumnName("guidesGetRellistBean");
			returnTypeGetList.setColumnNameCapitalized("GuidesGetRellistBean");
			returnTypeGetList.setColumnNameNoDash("guidesGetRellistBean");
			returnTypeGetList.setColumnType("GuidesGetRellistBean");
			returnTypeGetList.setFieldType(FieldTypeEnum.ENTITY);
			returnTypeGetList.setColumnType("GuidesGetRellistBean");
			FieldTypeEnum returnGetListEnum=FieldTypeEnum.ENTITY;
			
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					getListColumnBean,methodUri,returnGetListEnum,returnTypeGetList);
			
			//获取霞客相关列表信息(合伙霞客列表)
			controllerMethodSignature = "getRelList";
			//getrellist两个参数
			methodUri=  "guides.php?action=getRelList";
			controllerMethodSignature = "getRelList";
			toServiceMethodSignature = "getRelList";
			toServiceMethodSignatureDescripter = "获取霞客相关列表信息(合伙霞客列表)";
			ColumnBean getRelListColumnBean = new ColumnBean();
			getRelListColumnBean.setColumnName("uid");
			getRelListColumnBean.setColumnType("Integer");//默认值为1
			getRelListColumnBean.setColumnComment("相关的霞客ID");
			getRelListColumnBean.setColumnDefault("1");
			getRelListColumnBean.setColumnNameNoDash("uid");
			getRelListColumnBean.setColumnNameCapitalized("Uid");
			getRelListColumnBean.setNextFieldRative(FieldRativeEnum.And);

			ColumnBean getRelListpsizeColumnBean = new ColumnBean();
			getRelListpsizeColumnBean.setColumnName("p");
			getRelListpsizeColumnBean.setColumnType("Integer");
			getRelListpsizeColumnBean.setColumnDefault("1");
			getRelListpsizeColumnBean.setColumnNameNoDash("p");
			getRelListpsizeColumnBean.setColumnNameCapitalized("P");
			getRelListpsizeColumnBean.setNextFieldRative(FieldRativeEnum.And);
			
			getRelListColumnBean.setNextColumnBean(getRelListpsizeColumnBean);
			
			ColumnBean dataTypeColumnBean = new ColumnBean();
			dataTypeColumnBean.setColumnName("psize");
			dataTypeColumnBean.setColumnType("Integer");
			dataTypeColumnBean.setColumnDefault("10");
			dataTypeColumnBean.setColumnNameNoDash("psize");
			dataTypeColumnBean.setColumnNameCapitalized("Psize");
			dataTypeColumnBean.setNextFieldRative(FieldRativeEnum.Finished);
			
			

			getRelListpsizeColumnBean.setNextColumnBean(dataTypeColumnBean);
		
			//startColumnBean.setNextColumnBean(psizeColumnBean);
			FieldTypeEnum returnGetRelListFieldEnum=FieldTypeEnum.ENTITY;
			ColumnBean returnTypeGetRelList=new ColumnBean();
			returnTypeGetRelList.setColumnName("guidesGetRellistBean");
			returnTypeGetRelList.setColumnNameCapitalized("GuidesGetRellistBean");
			returnTypeGetRelList.setColumnNameNoDash("guidesGetRellistBean");
			returnTypeGetRelList.setColumnType("GuidesGetRellistBean");
			returnTypeGetRelList.setFieldType(FieldTypeEnum.ENTITY);
			returnTypeGetRelList.setColumnType("GuidesGetRellistBean");
			
			
			manager = ControllerManager.addControllerMethodGetModelAttribute(controllerType, controllerUri, descipter,
					controllerMethodSignature, toServiceMethodSignature, toServiceMethodSignatureDescripter,
					getRelListColumnBean,methodUri,returnGetRelListFieldEnum,returnTypeGetRelList);
			
			assertNotNull(manager);
			
			//生成XML
			manager.generateControllerBeanToXML("ieream");
			
			assertNotNull(manager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
