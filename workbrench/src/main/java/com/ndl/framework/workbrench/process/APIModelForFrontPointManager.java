package com.ndl.framework.workbrench.process;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.define.TransientBean;
import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

/**
 * 解决数据来自JSON，也就是第三方提供的接口，需要处理的逻辑是JSON转对象，和Http/Https的调用问题
 * @author admin
 *
 */
public class APIModelForFrontPointManager extends APIModelManager {
	private static final Logger logger = LoggerFactory.getLogger(APIModelForFrontPointManager.class);
	
	Set<TableBean> tableBeanSets=new CopyOnWriteArraySet<TableBean>();;
	private static class APIModelForFrontPointHolder {
	      private final static APIModelManager INSTANCE = 
	    		  new APIModelForFrontPointManager();
	}

	public static APIModelManager getInstance() {
	      return APIModelForFrontPointHolder.INSTANCE;
	}
	

	protected void generateModelDamainFromRawData(String urlPath){
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager generateModelDamain Begin.");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("APIModelForFrontPointManager generateModelDamain Over.");
		}
	}
	
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
