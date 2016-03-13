package com.ndl.framework.workbrench.process;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.TransientBean;

public class ApiModuleManagerTest  {
	
	APIModelManager modelManager=APIModelManager.getInstance();
	
	@Test
	public void generateAllModelAndMyBatisMapperSimpleFileFormDB(){
		modelManager.generateAllModelAndMyBatisMapperSimpleFileFormDB();
	}
	@Test
	public void generateSimpleAndroidRestfulFileFormDB(){
		modelManager.generateSimpleAndroidRestfulFileFormDB();
	}
	
	@Test 
	public void generateMyBaticSimpleFileFormDB(){
		modelManager.generateSimpleMyBaticFileFormDB();
	}
	
	@Test 
	public void generateAllSimpleFileFormDB(){
		this.modelManager.generateAllSimpleFileFormDB();
	}
	
	////////////////////////////////////////////////////
	@Test
	public void generateIOSAndBeanFileFromDB(){
		modelManager.generateIOSAndBeanFileFromDB();
	}
	@Test
	public void generateExcludeEntityFileFromDB(){
		modelManager.generateExcludeEntityFileFromDB();
	}
	
	@Test
	public void generateJPAEnityFileFromXML(){
		modelManager.generateJPAEnityFileFromXML();
	}
	@Test
	public void generateModlePojoFileFromDB(){
		modelManager.generateModlePojoFileFromDB();
	}
	@Test
	public void generateJPAEnityFileFromDB(){
		modelManager.generateJPAEnityFileFromDB();
	}
	
	@Test 
	public void generateAllFileFromDB(){
		modelManager.generateAllFileFromDB();
	}
	
	@Test 
	public void generateAllFileFromXML(){
		modelManager.generateAllFileFromXML();
	}
	
	@Test
	public void generateServiceJunitFromXML(){
		modelManager.generateServiceJunitFromXML();
	}
	@Test
	public void generateServiceForJDBCFromXML(){
		modelManager.generateServiceForJDBCFromXML();
	}
	
	@Test
	public void generateDAOForJDBCFromXML(){
		modelManager.generateDAOForJDBCFromXML();
	}
	
	@Test
	public void generateModlePojoFileFromXML(){
		modelManager.generateModlePojoFileFromXML();
	}
	
	@Test
	public void generateIOSFileFromXML(){
		modelManager.generateIOSFileFromXML();
	}
	
	@Test
	public void generateIOSFileFromDB(){
		modelManager.generateIOSFileFromDB();
	}
	
	
	@Test 
	public void addFilterColumnFramework(){
		
		String columnName="status";
		
		modelManager.addExcludeColumnFramework(columnName);
		columnName="id";
		modelManager.addExcludeColumnFramework(columnName);
		//如果是Entity，需要设置文件名为filter-entity-config.xml;
		modelManager.generateTableColumnConfigToXML();
		
		String str=modelManager.parseExcludeColumnFrameworkCommaFormFromXML();
		assertNotNull(str);
		
		TransientBean transientBean=new TransientBean();
		transientBean.setTableName("account_t");
		
		modelManager.addFilterTable("account_t");
		modelManager.addFilterTable("ad_info_t");
		modelManager.addFilterTable("apply_order_t");
		
		str=modelManager.parseFilterTalbesCommaFormFromCache();
		assertNotNull(str);
		
		modelManager.generateTableColumnConfigToXML();
		
		str=modelManager.parseFilterTalbesCommaFormFromXML();
		assertNotNull(str);
	}
	@Test 
	public void addFilterColumnFrameworkXML(){
		String columnName="status";
		modelManager.addExcludeColumnFramework(columnName);
		columnName="id";
		modelManager.addExcludeColumnFramework(columnName);
		String str=modelManager.parseExcludeColumnFrameworkCommaFormFromXML();
		assertNotNull(str);
	}
	@Test 
	public void parseFilterTalbesCommaFormFromCache(){
		TransientBean transientBean=new TransientBean();
		transientBean.setTableName("account_t");
		
		modelManager.addFilterTable("account_t");
		modelManager.addFilterTable("ad_info_t");
		modelManager.addFilterTable("apply_order_t");
		String str=modelManager.parseFilterTalbesCommaFormFromCache();
		assertNotNull(str);
	}
	@Test 
	public void parseFilterTalbesCommaFormFromXML(){
		String str=modelManager.parseFilterTalbesCommaFormFromXML();
		assertNotNull(str);
	}
	
	@Test 
	public void generateFilterTablesToXML(){
		//private static String tablesFilter = "'account_t','ad_info_t','apply_order_t'";
		TransientBean transientBean=new TransientBean();
		transientBean.setTableName("account_t");
		
		modelManager.addFilterTable("account_t");
		modelManager.addFilterTable("ad_info_t");
		modelManager.addFilterTable("apply_order_t");
		modelManager.generateTableColumnConfigToXML();
		
	}
	
	
	@Test
	public void loadModelData(){
		modelManager.loadModelData();
	}
	
	//加入不是从数据库引入的字段
	@Test
	public void generateTransientBean() {
		//哪个变量是临时对象，无需保存在数据中
		ColumnBean bean=new ColumnBean();
		bean.setColumnNameCapitalized("ColumnBean");
		bean.setColumnNameNoDash("columnBean");
		bean.setColumnKey("TST");
		bean.setColumnType("ColumnBean");
		bean.setFieldType(FieldTypeEnum.LIST);
		
		
		Set<ColumnBean> columnList=new HashSet<ColumnBean>();
		columnList.add(bean);
		
		TransientBean transientBean=new TransientBean();
		//哪个实体类
		transientBean.setTableName("Ad_Info_T");
		transientBean.setTableNameCapitalized("AdInfoT");
		transientBean.setColumnBeanList(columnList);
		
		
		APIModelManager modelManager=new APIModelManager();
		modelManager.addTransientBean(transientBean);
		modelManager.generateTableColumnConfigToXML();
		
		modelManager.parseEntityTransientTalbeBeanFromXML();
		
	}
		
}
