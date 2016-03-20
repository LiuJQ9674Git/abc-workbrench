package com.ndl.framework.workbrench.process;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.define.TransientBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.define.TableColumnConfig;
import com.ndl.framework.workbrench.freemarker.TemplateCommand;
import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.util.JAXBUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class APIModelManager {
	private static final Logger logger = LoggerFactory.getLogger(APIModelManager.class);
	
	protected final TemplateCommand generate =TemplateCommand.getInstance();
	
	JAXBUtil jaxbUtilConfig = new JAXBUtil(TableColumnConfig.class);
	//不需要持久化的数据，但是在展现层次上需要，例如IOS需要模型实例变量，或者实体中包含其它实体，例如订单头，包含订单详细项
	Set<TransientBean> transientBeanList=new CopyOnWriteArraySet<TransientBean>();
	//数据库表中有的但是不用映射为实体变量的
	Set<TransientBean> excludeBeanList=new CopyOnWriteArraySet<TransientBean>();

	Set<TransientBean> filterBeanList=new CopyOnWriteArraySet<TransientBean>();
	
	Map<String ,TransientBean> filterTableMap=new java.util.concurrent.ConcurrentHashMap<String ,TransientBean>();
	
	Map<String ,String> filterTableColoumnMap=new java.util.concurrent.ConcurrentHashMap<String ,String>();
	
	Set<String> excludeColoumnFramework=new CopyOnWriteArraySet<String>();
	
	private Set<TableBean> tableBeanList;
	
	private static final String DEFAULT_FILE_NAME="/filter-table-config.xml";
	
	private String filterTableConfigFileName=DEFAULT_FILE_NAME;
	private static class APIModelManagerHolder {
	      private final static APIModelManager INSTANCE = new APIModelManager();
	}

	public static APIModelManager getInstance() {
	      return APIModelManagerHolder.INSTANCE;
	}
	
	////////////////////2生成模型代码所需数据///////////////
	
	protected void loadModelDataFromXML(){
		setIsEntityFormDB(false);
		parseDataConfigure();
	}
	
	protected void loadModelDataFromDB(){
		setIsEntityFormDB(true);
		parseDataConfigure();
	}
	
	///////////////////////////生成代码////////////////////
	public void generateSimpleMyBaticFileFormDB(){
		loadModelDataFromDB();

		generateSimpleMyBatis();

	}
	
	public void generateAllModelForFrontSimpleFileFormXML(){
		throw new ConfigRuntimeException(
				WorkBrenchConfigProperty.PROCESS_APIMODEL_EXCETPION__NO_METHOD);
	}
	
	public void generateAllModelAndMyBatisMapperSimpleFileFormDB(){
		loadModelDataFromDB();
		generateIOSModel();
		generateJPAEntityModel();
		generateModlePojo();
		//generateSimpleBusinessService();
		//generateSimpleService();
		//generateSimpleDAO();
		generateSimpleMyBatis();
		//generateSimpleAndroidRestful();
		//generateSimpleController();
		//generateSimpleAndroidRestfulJunit();
	}
	
	public void generateAllSimpleFileFormDB(){
		loadModelDataFromDB();
		generateIOSModel();
		generateJPAEntityModel();
		generateModlePojo();
		generateSimpleBusinessService();
		generateSimpleService();
		generateSimpleDAO();
		generateSimpleMyBatis();
		generateSimpleAndroidRestful();
		generateSimpleController();
		generateSimpleAndroidRestfulJunit();
	}
	
	////////////////////////////////////////////////////
	public void generateAllFileFromDB(){
		generateExcludeEntityFileFromDB();
		generateJPAEnityFileFromDB();
	}
	
	public void generateAllFileFromXML(){
		generateExcludeEntityFileFromXML();
		generateJPAEnityFileFromXML();
	}

	public void generateExcludeEntityFileFromDB(){
		loadModelDataFromDB();
		generateIOSModel();
		//generateJPAEntityModel();
		generateModlePojo();
		generateDAOForJDBC();
		generateServiceForJDBC();
		//generateServiceJunit();
	}
	
	/**
	 *	不包括实体Bean的代码生成
	 */
	public void generateExcludeEntityFileFromXML(){
		loadModelDataFromXML();
		generateIOSModel();
		//generateJPAEntityModel();
		generateModlePojo();
		generateDAOForJDBC();
		generateServiceForJDBC();
		//generateServiceJunit();
	}
	
	public void generateIOSAndBeanFileFromDB(){
		loadModelDataFromDB();
		generateIOSModel();
		generateModlePojo();
	}
	
	public void generateIOSAndBeanFileFromXML(){
		loadModelDataFromXML();
		generateIOSModel();
		generateModlePojo();
	}
	
	public void generateIOSFileFromXML(){
		loadModelDataFromXML();
		generateIOSModel();
	}
	
	public void generateIOSFileFromDB(){
		loadModelDataFromDB();
		generateIOSModel();
	}
	
	public void generateJPAEnityFileFromXML(){
		this.filterTableConfigFileName="/filter-entity-config.xml";
		loadModelDataFromXML();
		generateJPAEntityModel();
	}
	
	public void generateJPAEnityFileFromDB(){
		this.filterTableConfigFileName="/filter-entity-config.xml";
		loadModelDataFromDB();
		generateJPAEntityModel();
	}
	
	public void generateModlePojoFileFromXML(){
		loadModelDataFromXML();
		generateModlePojo();
	}
	
	public void generateModlePojoFileFromDB(){
		loadModelDataFromDB();
		generateIOSFileFromDB();
		generateModlePojo();
	}
	
	public void generateDAOForJDBCFromXML(){
		loadModelDataFromXML();
		generateDAOForJDBC();
	}
	
	public void generateDAOForJDBCFromDB(){
		loadModelDataFromDB();
		generateDAOForJDBC();
	}
	
	public void generateServiceForJDBCFromXML(){
		loadModelDataFromXML();
		generateServiceForJDBC();
	}
	
	public void generateServiceForJDBCFromDB(){
		loadModelDataFromDB();
		generateServiceForJDBC();
	}
	
	public void generateServiceJunitFromXML(){
		loadModelDataFromXML();
		generateServiceJunit();
	}
	
	public void generateServiceJunitFromDB(){
		loadModelDataFromDB();
		generateServiceJunit();
	}
	
	////////////////////////////////////////////////////////
	public void addTransientBean(TransientBean transientBean){
		transientBeanList.add(transientBean);
	}
	
	public void setConfigTransientBeanList(Set<TransientBean> transientBeanList){
		this.transientBeanList=transientBeanList;
	}
	
	
	public Set<TableBean> getTableBeanList() {
		return tableBeanList;
	}

	public void setTableBeanList(Set<TableBean> tableBeanList) {
		Assert.notNull(tableBeanList, "tableBeanList must not null");
		this.tableBeanList = tableBeanList;
	}

	/**
	 * 添加项目中统一不包括的字段
	 * @param columnName
	 */
	public void addExcludeColumnFramework(String columnName){
		excludeColoumnFramework.add(columnName);
	}
	/**
	 * 添加仅仅过滤的表，此时TransientBean的tableName为必填项
	 * 如果要仅仅包含某个表的些字段时，ColumnBean中的columnName不能为空
	 * @param transientBean
	 */
	public void addFilterTable(String tableName){
		Assert.notNull(tableName, "tableName must not be null");
		//删除空格
		String localTableName=StringUtils.trim(tableName);
		if(filterTableMap.containsKey(localTableName)){
			return;
		}
		TransientBean transientBean=new TransientBean();
		transientBean.setTableName(localTableName);
		filterBeanList.add(transientBean);
		filterTableMap.put(tableName, transientBean);
	}
	
	/**
	 * 添加仅仅过滤的表，此时TransientBean的tableName为必填项
	 * 如果要仅仅包含某个表的些字段时，ColumnBean中的columnName不能为空
	 * @param transientBean
	 */
	public void addFilterTable(String tableName,String columnName){
		Assert.notNull(tableName, "tableName must not be null");
		Assert.notNull(columnName, "columnName must not be null");
		//删除空格
		String localTableName=StringUtils.trim(tableName);
		String localColumnName=StringUtils.trim(columnName);
		String tableColumn=localTableName+"-"+localColumnName;
		if(filterTableColoumnMap.containsKey(tableColumn)){
			return;
		}
		TransientBean transientBean;
		ColumnBean columnBean=new ColumnBean();
		columnBean.setColumnName(localColumnName);
		if(filterTableMap.containsKey(localTableName)){
			transientBean=filterTableMap.get(localTableName);
			transientBean.addColumnBean(columnBean);
		}else{
			transientBean=new TransientBean();
			transientBean.addColumnBean(columnBean);
			transientBean.setTableName(tableName);
			filterBeanList.add(transientBean);
		}
		
	}
	
	////////////////////////////////////////////////////////////
	protected void parseDataConfigure(){
		String filterTables=parseFilterTalbesCommaFormFromXML();
		String excludeColumn=parseExcludeColumnFrameworkCommaFormFromXML();
		setExcludeColumnFramework(excludeColumn);
		setFilterTables(filterTables);
		Set<TransientBean> transientBeanSet=parseEntityTransientTalbeBeanFromXML();
		setTransientBeanSet(transientBeanSet);
		Set<String> localCoumn =loadTableColumnConfigFromXML().getExcludeColomnFramework();
		
		setColumnsExcludeList(localCoumn);
		
		loadModelData();
		
		//return tables;
	}
	
	/**
	 * 从缓存中读取项目中统一不包括的字段，并形成SQL的格式
	 * @return
	 */
	public String parseExcludeColumnFrameworkCommaFormFromCache(){
		return parseExcludeColumnFrameworkCommaForm(this.excludeColoumnFramework);
	}
	
	/**
	 * 从XML中读取中项目中统一不包括的字段，并形成SQL格式
	 * @return
	 */
	public String parseExcludeColumnFrameworkCommaFormFromXML(){
		Set<String> localCoumn =loadTableColumnConfigFromXML().getExcludeColomnFramework();
		return parseExcludeColumnFrameworkCommaForm(localCoumn);
	}
	
	private String parseExcludeColumnFrameworkCommaForm(Set<String> excludeColoumnFramework){
		Set<String> localCoumn=java.util.Collections.unmodifiableSet(excludeColoumnFramework);
		StringBuffer tableBuffer=new StringBuffer();
		Assert.notNull(localCoumn, "localCoumn must not be null");
		String[] columns=localCoumn.toArray(new String[0]);
		for(int i=0;i<columns.length;i++){
			tableBuffer.append("'");
			tableBuffer.append(columns[i]);
			tableBuffer.append("'");
			if(i<columns.length-1){
				tableBuffer.append(",");
			}
		}
		return tableBuffer.toString();
	}
	
	public String parseFilterTalbesCommaFormFromCache(){
		return parseFilterTalbesCommaForm(this.filterBeanList);
	}
	
	/**
	 * 读取数据库中不包含的字段，但是域模型中需要包含的实例属性
	 * @return
	 */
	public Set<TransientBean> parseEntityTransientTalbeBeanFromXML(){
		Set<TransientBean> sets=loadTableColumnConfigFromXML().getTransientBeanList();
		return sets;
	}
	
	public String parseFilterTalbesCommaFormFromXML(){
		Set<TransientBean> filterTalbes=loadTableColumnConfigFromXML().getFilterBeanList();
		return parseFilterTalbesCommaForm(filterTalbes);
	}
	
	/**
	 * 解析过滤表为逗号分隔的Form形式
	 * @return
	 */
	private String parseFilterTalbesCommaForm(Set<TransientBean> filterTalbes){
		
		StringBuffer tableBuffer=new StringBuffer();
		Assert.notNull(filterTalbes, "filterTalbes must not be null");
		TransientBean[] tables=filterTalbes.toArray(new TransientBean[0]);
		for(int i=0;i<tables.length;i++){
			TransientBean transientBean= tables[i];
			tableBuffer.append("'");
			tableBuffer.append(transientBean.getTableName());
			tableBuffer.append("'");
			if(i<tables.length-1){
				tableBuffer.append(",");
			}
		}
		return tableBuffer.toString();
	}
	
	//////////////////////////////////////////////////////
	
	/**
	 * 产生仅仅包括的表数据写入XML配置文件,如果表中没有配置列，则为全选，如果列出了列信息，则仅仅生成相应列的实体，
	 * 即ColumnBean List的作用
	 */
	public void generateTableColumnConfigToXML(){
		if (logger.isInfoEnabled()) {
			logger.info("APIModelManager generateFilterTablesToXML Begin");
		}
	
		TableColumnConfig tableColumnConfig=new TableColumnConfig();
		if(CollectionUtils.isNotEmpty(filterBeanList)){
			tableColumnConfig.setFilterBeanList(filterBeanList);
		}
		if(CollectionUtils.isNotEmpty(excludeBeanList)){
			tableColumnConfig.setExcludeBeanList(excludeBeanList);
		}
		if(CollectionUtils.isNotEmpty(transientBeanList)){
			tableColumnConfig.setTransientBeanList(transientBeanList);
		}
		if(CollectionUtils.isNotEmpty(excludeColoumnFramework)){
			tableColumnConfig.setExcludeColomnFramework(excludeColoumnFramework);
		}
		try {
			
			File file = new File(RunConfigure.getConfigPath() + this.filterTableConfigFileName);
			jaxbUtilConfig.objectToXmlFile(tableColumnConfig, file);
		} catch (Exception e) {
			throw new java.lang.RuntimeException("生成实体模板，写入配置文件失败", e.getCause());
		}
		if (logger.isInfoEnabled()) {
			logger.info("APIModelManager generateFilterTablesToXML Over:");
			logger.info("transientOrExcludeColumnConfig:\t"+tableColumnConfig);
		}
	}
	
	public TableColumnConfig loadTableColumnConfigFromXML(){
		if (logger.isInfoEnabled()) {
			logger.info("APIModelManager loadFilterTablesToXML Begin");
		}
		TableColumnConfig tableColumnConfig;
		try {
			
			File file = new File(RunConfigure.getConfigPath() + this.filterTableConfigFileName);
			tableColumnConfig=jaxbUtilConfig.xmlToObject(file,TableColumnConfig.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new java.lang.RuntimeException("从配置文件中获取文件失败", e.getCause());
		}
		if (logger.isInfoEnabled()) {
			logger.info("APIModelManager loadFilterTablesToXML Over:");
			logger.info("TableColumnConfig:\t"+tableColumnConfig);
		}
		return tableColumnConfig ;
	}
	
	////////////////////////////////////////////////////
	protected void setFilterTables(String filter){
		if(StringUtils.isNoneBlank(filter)){
			generate.setFitlerTable(filter);
		}
	}
	protected void setColumnsExcludeList(Set<String> filter){
		generate.setColumnsExcludeList(filter);
	}
	protected void setIsEntityFormDB(boolean bEntityForm){
		generate.setIsEntityFormDB(bEntityForm);
	}
	
	protected void setExcludeColumnFramework(String columnsExclude){
		if(StringUtils.isNoneBlank(columnsExclude)){
			generate.setColumnsExclude(columnsExclude);
		}
	}
	
	protected void setTransientBeanSet(Set<TransientBean> transientBeanSet){
		if(CollectionUtils.isNotEmpty(transientBeanSet)){
			generate.setTransientBeanSet(transientBeanSet);
		}
	}
	
	/**
	 * 生成域模型数据，默认是以数据库方式实现，此方法无需实现
	 * @param urlPathEndChar,Key url,value:结尾标识符，如.php
	 */
	protected void generateModelDamainFromRawData(Map<String,String> urlPathEndChar){
		throw new ConfigRuntimeException(
				WorkBrenchConfigProperty.PROCESS_APIMODEL_EXCETPION__NO_METHOD);
	}

	/**
	 * 装载实体信息
	 * @return
	 */
	protected void loadModelData(){
		if(logger.isInfoEnabled()){
			logger.info("APIModelManager loadModelData Begin.");
		}
		generate.loadModelData();
		if(logger.isInfoEnabled()){
			logger.info("APIModelManager loadModelData Over.");
		}
	}
	
		
	protected void writeEntityConfigToFile(){
		this.generate.writeEntityConfigToFile(tableBeanList);
	}
	
	protected void generateIOSModel(){
		generate.generateIOSModel();
	}
	
	protected void generateJPAEntityModel(){
		generate.generateEntity();
	}
	
	protected void generateModlePojo(){
		generate.generateModlePojo();
	}
	
	protected void generateDAOForJDBC(){
		generate.genernateJDBCTemplete();
	}
	
	protected void generateServiceForJDBC(){
		generate.generateService();
	}
	
	protected void generateServiceJunit(){
		generate.generateServiceJunit();
	}
	
	
	protected void generateSimpleBusinessService(){
		this.generate.generateSimpleBusinessService();
	}
	
	protected void generateSimpleService(){
		this.generate.generateSimpleService();
	}
	protected void generateSimpleDAO(){
		this.generate.generateSimpleDAO();
	}
	
	protected void generateSimpleMyBatis(){
		this.generate.generateSimpleMyBatis();
	}
	
	protected void generateSimpleAndroidRestful(){
		this.generate.generateSimpleAndroidRestful();
	}
	
	protected void generateSimpleController(){
		this.generate.generateSimpleController();
	}
	
	protected void generateSimpleAndroidRestfulJunit(){
		generate.generateSimpleAndroidRestfulJunit();
	}
}
