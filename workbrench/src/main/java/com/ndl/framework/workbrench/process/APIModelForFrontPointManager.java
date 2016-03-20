package com.ndl.framework.workbrench.process;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.noggit.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.FieldTypeEnum;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.define.TransientBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.freemarker.template.FrontTemplete;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.ProductParseTemplateUtil;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

import freemarker.template.utility.StringUtil;

/**
 * 解决数据来自JSON，也就是第三方提供的接口，需要处理的逻辑是JSON转对象，和Http/Https的调用问题
 * @author admin
 *
 */
public class APIModelForFrontPointManager extends APIModelManager {
	private static final Logger logger = LoggerFactory.getLogger(APIModelForFrontPointManager.class);
	
	MediaType mediaType = MediaType.APPLICATION_JSON;
	private final ObjectMapper mapper ;
	{
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.WRAP_EXCEPTIONS, false);

	}
	private static final RestTemplate restTemplate = new RestTemplate();

	{
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	private static final Set<TransientBean> transientBeanSets=new CopyOnWriteArraySet<TransientBean>();
	private static final Map<String,TransientBean> transientBeanMap=new ConcurrentHashMap<String,TransientBean>();

	private static final Set<TableBean> tableBeanSets=new CopyOnWriteArraySet<TableBean>();
	private static final Map<String,TableBean> tableBeanMap=new ConcurrentHashMap<String,TableBean>();
	
	private static class APIModelForFrontPointHolder {
	      private final static APIModelManager INSTANCE = 
	    		  new APIModelForFrontPointManager();
	}

	public static APIModelManager getInstance() {
	      return APIModelForFrontPointHolder.INSTANCE;
	}
	

	/**
	 * 生成代码
	 */
	public void generateAllModelForFrontSimpleFileFormXML(){
		super.loadModelDataFromXML();
		generateIOSModel();
		generateModlePojo();
		
	}
	
	public void generateAndroidRestfulForFrontSimpleFileFormXML(String moduleName){
		ControllerManager.generateFrontTemplateToXML(moduleName);
		generateAndroidRestfulForFrontByTemplate();
	}
	
	protected void generateModelDamainFromRawData(Map<String,String> urlPathEndChar){
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager generateModelDamainFromRawData Begin.");
			logger.info("urlPathEndChar:\t"+urlPathEndChar);
		}
		Assert.notNull(urlPathEndChar, "urlPathEndChar must not be null");
		String defualtEndChar="Root";
		if(MapUtils.isNotEmpty(urlPathEndChar)){
			Set<String> urlPathSet=urlPathEndChar.keySet();
			for(String urlPath:urlPathSet){
				String endChar=urlPathEndChar.getOrDefault(urlPath, defualtEndChar);
				generateModelDamainFromRawData(urlPath,endChar);
			}
			this.writeEntityConfigToFile();
		}
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager generateModelDamainFromRawData Over.");
		}
	}
	
	/**
	 * 根据url生成模型
	 */
	private void generateModelDamainFromRawData(String urlPath,String endChar){
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager generateModelDamainFromRawData Begin.");
			logger.debug("urlPath:\t"+urlPath);
			logger.debug("endChar:\t"+endChar);
		}
		//读入urlPath内容
		readModelDomainFromRawData(urlPath,endChar);
		//
		modifiedDataTransientBean();
		modifiedDataTableBean();
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager generateModelDamainFromRawData Over.");
		}
	
	}
	
	protected void writeEntityConfigToFile(){
		if(CollectionUtils.isNotEmpty(tableBeanSets)){
			super.setTableBeanList(tableBeanSets);
			super.writeEntityConfigToFile();
		}
	}
	
	//解析JNSON方法
	private void readModelDomainFromRawData(String urlPath,String endChar){
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager generateModelDamain Begin.");
			logger.info("urlPath:\t"+urlPath);
			logger.info("endChar:\t"+endChar);
		}
		if(StringUtils.isEmpty(urlPath)){
			logger.warn("urlPath must not null");
			return;
		}
		//使用RestfulTemplate读取数据
		try{
			String response = restTemplate.getForObject(urlPath, String.class);
			if(logger.isDebugEnabled()){
				logger.info("response:\t"+response);
			}
			//开始解析数据
			JSONObject parser=new JSONObject(response); 
			String[] strs=urlPath.split("/");
			String usrShot=strs[strs.length-1];
			String tableName=usrShot.substring(0,usrShot.indexOf(endChar));
			TableBean tableBean=createTableBean(tableName);
			tableBean.setTableComment(urlPath);

			Iterator<String> fieldList=parser.keys();
			while(fieldList.hasNext()){
				String parserKey=fieldList.next();
				Object object=parser.get(parserKey);
				if(logger.isDebugEnabled()){
					logger.info("JSONArray key:\t"+parserKey);
					logger.info("JSONArray object:\t"+object);
				}
				if(object.getClass()==JSONArray.class){
					if(logger.isInfoEnabled()){//是数组有可能是对象有可能不是
						logger.info("JSONArray");
					}
					//新建.是数组的情况翻译为List《Data>
					ColumnBean columnBean =createColumnBean(parserKey
						,object,tableBean);
					//columnBean.setFieldType(FieldTypeEnum.LIST);
					tableBean.addColumn(columnBean);
					//区别根节点下的节点
					//TableBean parserTableBean=createTableBean(parserKey );
					TableBean parserTableBean= createTableBean(parserKey+StringUtil.capitalize(tableName),tableName);
					parseJSONArray(parserKey,object, parserTableBean);
				}else{
					if(logger.isDebugEnabled()){
						logger.info("JSONObject");
					}
					//
					ColumnBean columnBean =createColumnBean(parserKey,object,tableBean);
					tableBean.addColumn(columnBean);
					parseJSONObject(parserKey,object,tableBean);
					
				}
			}
			if(logger.isDebugEnabled()){
				logger.info("parser:\t"+parser);
			}
			
		}catch(Exception e){
			logger.error("读取网络原始数据出现异常", e);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager generateModelDamain Over.");
		}
	}
	
	//修改类名之后写入文件
	private void modifiedDataTableBean(){
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager modifiedDataTableBean Begin.");
		}
		Assert.notNull(tableBeanSets, "transientBeanSets must not be null");
		if(CollectionUtils.isEmpty(tableBeanSets)){
			logger.warn("transientBeanSets must not be null");
			return ;
		}
		/*
	    for(TableBean tableBean:tableBeanSets){
	    	if(StringUtils.isNotEmpty(tableBean.getParentTableNameCapitalized())){
	    		tableBean.setTableName(tableBean.getTableName()+tableBean.getParentTableNameCapitalized());
	    		tableBean.setTableNameCapitalized(tableBean.getTableNameCapitalized()
	    				+tableBean.getParentTableNameCapitalized());
	    		tableBean.setTableNameNoDash(tableBean.getTableNameNoDash()+
	    				tableBean.getParentTableNameCapitalized());
	    	}
	    }
	    */
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager modifiedDataTableBean Over.");
		}
	}
	private void modifiedDataTransientBean(){
		//transientBeanSets
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager modifiedDataTableBean Begin.");
		}
		Assert.notNull(transientBeanSets, "transientBeanSets must not be null");
		if(CollectionUtils.isEmpty(transientBeanSets)){
			logger.warn("transientBeanSets must not be null");
			return ;
		}
		
		super.setConfigTransientBeanList(transientBeanSets);
		super.generateTableColumnConfigToXML();
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager modifiedDataTableBean Over.");
		}
	}
	///////////////////////JSON START//////////////////////////////////////
	private void parseJSONArray(String key,Object object,TableBean tableBean){
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONArray Begin.");
			logger.debug("object:\t"+object);
		}
		
		if(object.getClass()==JSONArray.class){
			//JSONArray
			JSONArray objectArray=(JSONArray) object;
			if(logger.isInfoEnabled()){
				logger.debug("objectArray.length:\t"+objectArray.length());
			}
			for(int i=0;i<objectArray.length();i++){
				JSONObject parser=objectArray.getJSONObject(i);
				Iterator<String> fieldList=parser.keys();
				if(logger.isDebugEnabled()){
					logger.debug("parser:\t"+parser);
				}
				while(fieldList.hasNext()){
					String parserKey=fieldList.next();
					Object parserObject=parser.get(parserKey);
					if(logger.isDebugEnabled()){
						logger.debug("key:\t"+parserKey);
						logger.debug("parserObject:\t"+parserObject);
					}
					
					if(parserObject.getClass()==JSONArray.class){
						if(logger.isDebugEnabled()){//是数组有可能是对象有可能不是
							logger.debug("JSONArray:\t");
						}
						//新建
						JSONArray parserObjectArray=(JSONArray) parserObject;
						if(parserObjectArray.length()>0){//
							//
							ColumnBean columnBean=this.createColumnBean(parserKey, parserObjectArray, tableBean);
							tableBean.addColumn(columnBean);
							//union_guide_id_list修改
							TableBean parserTableBean=createTableBean(parserKey+tableBean.getTableNameCapitalized(),
									tableBean.getTableNameNoDash() 
										);
							parseJSONArray(parserKey,parserObject, parserTableBean);
						}else{
							if(logger.isDebugEnabled()){//是数组有可能是对象有可能不是
								logger.debug("parseJSONArray is emplety");
							}
							//TableBean parserTableBean=createTableBean(parserKey,tableBean.getTableNameNoDash()
							//		+tableBean.getParentTableNameCapitalized() );
							//parseJSONArray(parserKey,parserObject, parserTableBean);
						}
					}else{
						if(logger.isDebugEnabled()){
							logger.debug("JSONObject simple data type,value above\n");
							
						}//
						parseJSONObject(parserKey,parserObject,tableBean);
						
					}
				}
			}
			//parseJSONArray(object,tableBean);
		}else{
			parseJSONObject(key,object,tableBean);
		}
		if(logger.isInfoEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONArray Over.");
		}
	}
	
	private ColumnBean createColumnBean(String key,Object object,TableBean tableBean){
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager createColumnBean Begin.");
			logger.debug("key:\t"+key);
			logger.debug("object:\t"+object);
		}

		boolean isPrimitive=ClassHelper.isPrimitiveOrWrapper(object);
		String type=ClassHelper.getType(object);
		type=ClassHelper.getType(object);
		ColumnBean[] columnBeanArray=tableBean.getColumnBeanList().toArray(new ColumnBean[0]);
		for(ColumnBean columnBean:columnBeanArray){
			if(columnBean.getColumnNameNoDash().equals(key)&&isPrimitive){
				return columnBean;
			}
		}
		boolean isString=ClassHelper.isString(object);
			
		if(isString){
			boolean isDate=ClassHelper.isDate((String)object,"\\d{4}-\\d{2}-\\d{2}");
			type="String";
			if(isDate){
				//columnBean.setColumnKey("Date");
				type="Date";
			}
			tableBean.setHasDateColumn(true);
			
		}
		ColumnBean columnBean=null;
		
		if(!isPrimitive&&!isString){
			if(logger.isDebugEnabled()){
				logger.debug("not primitive or string or date,info followed:\n\t");
				logger.debug("key:\t"+key);
				logger.debug("object:\t"+object);
			
			}
			String className=key+tableBean.getTableNameCapitalized();
			
			if(StringUtils.isNoneEmpty(tableBean.getParentTableNameCapitalized())){
				//className=className+tableBean.getParentTableNameCapitalized();
			}
			String transientBeanName=tableBean.getTableNameCapitalized();
			if(StringUtils.isNoneEmpty(tableBean.getParentTableNameCapitalized())){
				//transientBeanName=transientBeanName+tableBean.getParentTableNameCapitalized();
			}
			TransientBean transientBean=createTransientBean(transientBeanName);
			String objectType=StringUtils.capitalize(className);//+tableBean.getTableNameCapitalized();
	
			if (object.getClass()==JSONArray.class){
				columnBean=createColumnBean(className,objectType,FieldTypeEnum.LIST);
			}else{
				columnBean=createColumnBean(className,objectType,FieldTypeEnum.ENTITY);
			}
			columnBean.setColumnKey("TST");
			addTransientBean(transientBean,columnBean);
		}else{
			if(isString){
				columnBean=	createColumnBean(key,type,(String)object);
			}else{
				columnBean=	createColumnBean(key,type,key);
			}
			columnBean.setFieldType(FieldTypeEnum.PRIMARY);
		}
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager createColumnBean end.");
		}
		return columnBean;
	}
	
	private void parseJSONObject(String key,Object object,TableBean tableBean){
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONObject Begin.");
			logger.debug("key:\t"+key);
			logger.debug("object:\t"+object);
			logger.debug("tableBean:\t"+tableBean.getTableNameCapitalized()+
					"\tParent:\t"+ tableBean.getParentTableNameCapitalized());
		}

		ColumnBean columnBean =this.createColumnBean(key, object,tableBean);
		boolean isString=ClassHelper.isString(object);
		boolean isPrimitive=ClassHelper.isPrimitiveOrWrapper(object);
		if(!isPrimitive&&!isString){
			if(logger.isDebugEnabled()){
				logger.debug("not primitive or string or date,info followed:\n\t");
				logger.debug("key:\t"+key);
				logger.debug("object:\t"+object);
				//TableBean parserTableBean=createTableBean(key,tableBean.getTableNameNoDash() );
				TableBean parserTableBean=createTableBean(columnBean.getColumnNameNoDash(),
						tableBean.getTableNameNoDash() );
				//parserTableBean.addColumn(columnBean);
				//解析对象
				if(object.getClass()==JSONObject.class){
					JSONObject parserObject=(JSONObject)object;
					Iterator<String> fieldList=parserObject.keys();
					while(fieldList.hasNext()){
						//检查是否有key
						String parseKey=fieldList.next();
						Object parserContreteObject=parserObject.get(parseKey);
						
						if(parserContreteObject.getClass()==JSONArray.class){
							//
							//如果是对象，则需要创建Column和Column对象的Table,province:[]
							//"data": {
					        //"province": [
					        //             {
							TableBean childParserTableBean=createTableBean(parseKey+parserTableBean.getTableNameCapitalized(),
									parserTableBean.getTableNameCapitalized());
							//如果为对象
							ColumnBean childColumnBean =this.createColumnBean(parseKey, parserContreteObject,parserTableBean);
							parserTableBean.addColumn(childColumnBean);
							parseJSONArray(parseKey,parserContreteObject,childParserTableBean);
						}else if(parserContreteObject.getClass()==JSONObject.class){
							parseJSONObject(parseKey,parserContreteObject,parserTableBean);
						}else{
							boolean isParseString=ClassHelper.isString(parserContreteObject);
							boolean isParsePrimitive=ClassHelper.isPrimitiveOrWrapper(parserContreteObject);
							logger.warn("APIModelForFrontPointManager parseJSONObject,value is:");
							logger.warn("object :\t"+object.getClass());
							if(isParseString||isParsePrimitive){
								parseJSONObject(parseKey,parserContreteObject,parserTableBean);
							}
						}
					}
				}else if(object.getClass()==JSONArray.class){
					logger.warn("APIModelForFrontPointManager parseJSONObject JSONArray:");
					logger.warn("object :\t"+object.getClass());
					
				}else{
					logger.warn("APIModelForFrontPointManager parseJSONObject,value is:");
					logger.warn("object :\t"+object.getClass());
				}
			}
		}
		TableBean table=createTableBean(tableBean.getTableNameNoDash(),"###");
		table.addColumn(columnBean);
		
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONObject end.");
		}
	}
	
	private TableBean createTableBean(String key,String parentKey){
		String tableName=key;
//		if(!StringUtils.isEmpty(parentKey)){
//			 tableName=key+StringUtils.capitalize(parentKey);
//		}
		if(tableBeanMap.containsKey(tableName)){
			return tableBeanMap.get(tableName);
		}else{
			TableBean tableBean=new TableBean();
			
			tableBean.setTableName(tableName);
			tableBean.setTableNameNoDash(tableName);
			tableBean.setTableNameCapitalized(StringUtils.capitalize(tableName));
			//
			if(!StringUtils.isEmpty(parentKey)){
				tableBean.setParentTableNameCapitalized(StringUtils.capitalize(parentKey));
				tableBean.setParentTableNameNoDash(parentKey);
			}
			
			tableBeanMap.put(tableName, tableBean);
			tableBeanSets.add(tableBean);
			return tableBean;
		}
	}
	
	private void addTransientBean(TransientBean transientBean,ColumnBean columnBean ){
		
		transientBean.addColumnBean(columnBean);
	}
	
	
	
	private TableBean createTableBean(String key){
		String tableName=key;
		if(tableBeanMap.containsKey(tableName)){
			return tableBeanMap.get(tableName);
		}else{
			TableBean tableBean=new TableBean();
			
			tableBean.setTableName(tableName);
			tableBean.setTableNameNoDash(tableName);
			tableBean.setTableNameCapitalized(StringUtils.capitalize(tableName));
			tableBeanMap.put(tableName, tableBean);
			tableBeanSets.add(tableBean);
			return tableBean;
		}
	}
	
	private TransientBean createTransientBean(String key ){
		if(transientBeanMap.containsKey(key)){
			return transientBeanMap.get(key);
		}else{
			TransientBean tableBean=new TransientBean();
			tableBean.setTableName(key);
			tableBean.setTableNameCapitalized(StringUtils.capitalize(key));
			transientBeanMap.put(key, tableBean);
			transientBeanSets.add(tableBean);
			return tableBean;
		}
	}
	
	private ColumnBean createColumnBean(String columnNameNoDash,
			String columnKey,FieldTypeEnum fieldType){
		
		ColumnBean columnBean=new ColumnBean();
		columnBean.setColumnType(columnKey);
		columnBean.setColumnNameNoDash(columnNameNoDash);
		columnBean.setColumnComment(columnKey);
		columnBean.setColumnNameCapitalized(StringUtils.capitalize(columnNameNoDash));
		columnBean.setFieldType(fieldType);
		ProductParseTemplateUtil.parseTypeToIOS(columnBean);
		return columnBean;
	}
	
	private ColumnBean createColumnBean(String columnNameNoDash,String columnKey,String comment){
		
		ColumnBean columnBean=new ColumnBean();
		columnBean.setColumnType(columnKey);
		columnBean.setColumnNameNoDash(columnNameNoDash);
		if(comment.length()>20){
			columnBean.setColumnComment(comment.substring(0, 20));
		}else{
			columnBean.setColumnComment(comment);
		}
	
		columnBean.setColumnNameCapitalized(StringUtils.capitalize(columnNameNoDash));
		//columnBean.setColumnKey("COMM");
		ProductParseTemplateUtil.parseTypeToIOS(columnBean);
		return columnBean;
	}
	
	////////////////////////////////////////////////
	protected void generateAndroidRestfulForFrontByTemplate(){
		FrontTemplete frontTemplete=ControllerManager.loadFrontTempleteFromXML();
		Assert.notNull(frontTemplete, "frontTemplete must not be null");
		super.generate.generateAndroidRestfulForFrontByTemplate(frontTemplete);
	}
}
