package com.ndl.framework.workbrench.process;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.noggit.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ndl.framework.workbrench.define.ColumnBean;
import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.define.TransientBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.util.ClassHelper;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

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
	
	private static final Set<TableBean> tableBeanSets=new CopyOnWriteArraySet<TableBean>();
	private static final Map<String,TableBean> tableBeanMap=new ConcurrentHashMap<String,TableBean>();
	
	private static class APIModelForFrontPointHolder {
	      private final static APIModelManager INSTANCE = 
	    		  new APIModelForFrontPointManager();
	}

	public static APIModelManager getInstance() {
	      return APIModelForFrontPointHolder.INSTANCE;
	}
	

	protected void generateModelDamainFromRawData(String urlPath,String endChar){
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
			TableBean tableBean=createTableBean(tableName );
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
					//新建
					ColumnBean columnBean =createColumnBean(parserKey,object,tableBean);
					tableBean.addColumn(columnBean);
					TableBean parserTableBean=createTableBean(parserKey );
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
						if(parserObjectArray.length()>0){
							TableBean parserTableBean=createTableBean(parserKey );
							parseJSONArray(parserKey,parserObject, parserTableBean);
						}else{
							if(logger.isDebugEnabled()){//是数组有可能是对象有可能不是
								logger.debug("parseJSONArray");
							}
							TableBean parserTableBean=createTableBean(parserKey );
							parseJSONArray(parserKey,parserObject, parserTableBean);
						}
					}else{
						if(logger.isDebugEnabled()){
							logger.debug("JSONObject");
						}
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
			logger.debug("APIModelForFrontPointManager parseJSONObject Begin.");
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
		ColumnBean columnBean=createColumnBean(key,type);
		boolean isString=ClassHelper.isString(object);
		if(isString){
			boolean isDate=ClassHelper.isDate((String)object,"\\d{4}-\\d{2}-\\d{2}");
			if(isDate){
				columnBean.setColumnKey("Date");
			}
			
		}
		if(!isPrimitive&&!isString){
			if(logger.isDebugEnabled()){
				logger.debug("not primitive or string or date,info followed:\n\t");
				logger.debug("key:\t"+key);
				logger.debug("object:\t"+object);
				//logger.debug("tableBean:\t"+tableBean.getTableName()+"\t"+ tableBean.getTableNameNoDash());
				String objectType=StringUtils.capitalize(key);
				columnBean.setColumnKey(objectType);
				
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONObject Begin.");
		}
		return columnBean;
	}
	
	private void parseJSONObject(String key,Object object,TableBean tableBean){
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONObject Begin.");
			logger.debug("key:\t"+key);
			logger.debug("object:\t"+object);
			logger.debug("tableBean:\t"+tableBean.getTableName()+"\t"+ tableBean.getTableNameNoDash());
		}

		ColumnBean columnBean =this.createColumnBean(key, object,tableBean);
		boolean isString=ClassHelper.isString(object);
		boolean isPrimitive=ClassHelper.isPrimitiveOrWrapper(object);
		if(!isPrimitive&&!isString){
			if(logger.isDebugEnabled()){
				logger.debug("not primitive or string or date,info followed:\n\t");
				logger.debug("key:\t"+key);
				logger.debug("object:\t"+object);
				TableBean parserTableBean=createTableBean(key );
				//解析对象
				JSONObject parserObject=(JSONObject)object;
				Iterator<String> fieldList=parserObject.keys();
				while(fieldList.hasNext()){
					//检查是否有key
					String parseKey=fieldList.next();
					Object parserContreteObject=parserObject.get(parseKey);
					//parseJSONObject(parseKey,parserContreteObject,parserTableBean);
					parseJSONArray(parseKey,parserContreteObject,parserTableBean);
				}

			}
		}

		TableBean table=createTableBean(tableBean.getTableNameNoDash());
		table.addColumn(columnBean);
		if(logger.isDebugEnabled()){
			logger.debug("APIModelForFrontPointManager parseJSONObject Begin.");
		}
	}
	
	private TableBean createTableBean(String key ){
		if(tableBeanMap.containsKey(key)){
			return tableBeanMap.get(key);
		}else{
			TableBean tableBean=new TableBean();
			tableBean.setTableName(key);
			tableBean.setTableNameNoDash(key);
			tableBean.setTableNameCapitalized(StringUtils.capitalize(key));
			tableBeanMap.put(key, tableBean);
			tableBeanSets.add(tableBean);
			return tableBean;
		}
	}
	private ColumnBean createColumnBean(String columnNameNoDash,String columnKey){
		
		ColumnBean columnBean=new ColumnBean();
		columnBean.setColumnKey(columnKey);
		columnBean.setColumnNameNoDash(columnNameNoDash);
		columnBean.setColumnNameCapitalized(StringUtils.capitalize(columnNameNoDash));
		return columnBean;
	}
	
	///////////////////////JSON OVER///////////////////////////////////////
	protected void loadModelData(){
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager loadModelData Begin.");
		}
		//装载数据
		Set<TableBean> setes=tableBeanSets;
		if(CollectionUtils.isEmpty(setes)){
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_APIMODEL_EXCETPION__NO_DOMAINDATA);
		}
		super.writeEntityConfigToFile(setes);
		super.loadModelData();
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager loadModelData Over.");
		}
		
	}

}
