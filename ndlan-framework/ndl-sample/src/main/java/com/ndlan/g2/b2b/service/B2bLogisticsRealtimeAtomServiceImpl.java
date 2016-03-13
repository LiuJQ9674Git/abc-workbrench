package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsRealtimeBean;
import com.ndlan.g2.b2b.dao.B2bLogisticsRealtimeDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.ndlan.canyin.main.service.BaseService;

@Transactional
@Component("b2bLogisticsRealtimeAtomService")
public class B2bLogisticsRealtimeAtomServiceImpl  extends BaseService<B2bLogisticsRealtimeDao, 
	B2bLogisticsRealtimeBean>      implements B2bLogisticsRealtimeAtomService {

    @Resource(name="b2bLogisticsRealtimeDao")
    protected B2bLogisticsRealtimeDao b2bLogisticsRealtimeDao;

    @Override
    public int saveB2bLogisticsRealtimeBean(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        trimStringValue(b2bLogisticsRealtime);
        return b2bLogisticsRealtimeDao.insertSelective(b2bLogisticsRealtime);
    }

    @Override
    public int saveAndGetId(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        trimStringValue(b2bLogisticsRealtime);
        return b2bLogisticsRealtimeDao.insertSelectiveAndGetId(b2bLogisticsRealtime);
    }

    @Override
    public int update(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        trimStringValue(b2bLogisticsRealtime);
        return b2bLogisticsRealtimeDao.updateByPrimaryKeySelective(b2bLogisticsRealtime);
    }

    @Override
    public int saveOrUpdateB2bLogisticsRealtimeBean(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        if (null == b2bLogisticsRealtime.getLogisticsRealtimeId() ||
		"" == b2bLogisticsRealtime.getLogisticsRealtimeId()) {
            return saveB2bLogisticsRealtimeBean(b2bLogisticsRealtime);
        } else {
            return update(b2bLogisticsRealtime);
        }
    }

    @Override
    public B2bLogisticsRealtimeBean getB2bLogisticsRealtimeBean(String logisticsRealtimeId) {
        return b2bLogisticsRealtimeDao.selectByPrimaryKey(logisticsRealtimeId);
    }

    @Override
    public List<B2bLogisticsRealtimeBean> getAll() {
        return b2bLogisticsRealtimeDao.selectAll();
    }

    @Override
    public void delete(String logisticsRealtimeId) {
         b2bLogisticsRealtimeDao.deleteByPrimaryKey(logisticsRealtimeId);
    }

    public List<B2bLogisticsRealtimeBean> queryB2bLogisticsRealtimeBean
	(B2bLogisticsRealtimeBean b2bLogisticsRealtime, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bLogisticsRealtime);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bLogisticsRealtimeDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bLogisticsRealtimeDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bLogisticsRealtimeDao.update(sql, args);
    }

    public List<B2bLogisticsRealtimeBean> queryB2bLogisticsRealtimeBean
	(B2bLogisticsRealtimeBean b2bLogisticsRealtime){
	SQLParam sqlParam=getWhereSQL(b2bLogisticsRealtime);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bLogisticsRealtimeDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String logisticsLineCode = b2bLogisticsRealtime.getLogisticsLineCode();
        if (StringUtils.isNotBlank(logisticsLineCode) ) {
           sqlBuffer.append("logisticsLineCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.getLogisticsLineCode());
        }
      
            
        String currAddress = b2bLogisticsRealtime.getCurrAddress();
        if (StringUtils.isNotBlank(currAddress) ) {
           sqlBuffer.append("currAddress=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.getCurrAddress());
        }
      
            
	  = b2bLogisticsRealtime.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.get());
        } 
      
            
        String logisticsRealtimeId = b2bLogisticsRealtime.getLogisticsRealtimeId();
        if (StringUtils.isNotBlank(logisticsRealtimeId) ) {
           sqlBuffer.append("logisticsRealtimeId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.getLogisticsRealtimeId());
        }
      
            
	  = b2bLogisticsRealtime.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.get());
        } 
      
            
	  = b2bLogisticsRealtime.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.get());
        } 
      
            
	  = b2bLogisticsRealtime.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.get());
        } 
      
            
        String nextAddress = b2bLogisticsRealtime.getNextAddress();
        if (StringUtils.isNotBlank(nextAddress) ) {
           sqlBuffer.append("nextAddress=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.getNextAddress());
        }
      
            
	  = b2bLogisticsRealtime.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.get());
        } 
      
            
        String remake = b2bLogisticsRealtime.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.getRemake());
        }
      
            
        String logisticsLineId = b2bLogisticsRealtime.getLogisticsLineId();
        if (StringUtils.isNotBlank(logisticsLineId) ) {
           sqlBuffer.append("logisticsLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsRealtime.getLogisticsLineId());
        }
      
            
	Date currDate = b2bLogisticsRealtime.getCurrDate();
	if (currDate!=null  ) {
           sqlBuffer.append("currDate=?");
	    
	     param.add(b2bLogisticsRealtime.getCurrDate());
        } 
      
            
	
	String sql=sqlBuffer.toString();
	sql=StringUtils.trim(sql);//sql.trim()
	if(sql.endsWith("and")){
		sql=sql.substring(0, sql.lastIndexOf("and"));
	}
	sqlParam.where=sql;
	sqlParam.params=param.toArray(new Object[0]);
	return sqlParam;
     }

    private class  SQLParam {
	String where;
	Object[] params;
    }
    @Override
    public B2bLogisticsRealtimeBean trimStringValue(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        String logisticsLineCode = b2bLogisticsRealtime.getLogisticsLineCode();
        if (StringUtils.isNotBlank(logisticsLineCode) && logisticsLineCode.length() > 36) {
            b2bLogisticsRealtime.setLogisticsLineCode(logisticsLineCode.substring(0, 36));
        }

        String currAddress = b2bLogisticsRealtime.getCurrAddress();
        if (StringUtils.isNotBlank(currAddress) && currAddress.length() > 255) {
            b2bLogisticsRealtime.setCurrAddress(currAddress.substring(0, 255));
        }

        String logisticsRealtimeId = b2bLogisticsRealtime.getLogisticsRealtimeId();
        if (StringUtils.isNotBlank(logisticsRealtimeId) && logisticsRealtimeId.length() > 36) {
            b2bLogisticsRealtime.setLogisticsRealtimeId(logisticsRealtimeId.substring(0, 36));
        }

        String nextAddress = b2bLogisticsRealtime.getNextAddress();
        if (StringUtils.isNotBlank(nextAddress) && nextAddress.length() > 255) {
            b2bLogisticsRealtime.setNextAddress(nextAddress.substring(0, 255));
        }

        String remake = b2bLogisticsRealtime.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bLogisticsRealtime.setRemake(remake.substring(0, 255));
        }

        String logisticsLineId = b2bLogisticsRealtime.getLogisticsLineId();
        if (StringUtils.isNotBlank(logisticsLineId) && logisticsLineId.length() > 36) {
            b2bLogisticsRealtime.setLogisticsLineId(logisticsLineId.substring(0, 36));
        }

        return b2bLogisticsRealtime;
    }
}
