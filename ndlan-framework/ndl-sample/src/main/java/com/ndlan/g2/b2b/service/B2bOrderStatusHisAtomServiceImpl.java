package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderStatusHisBean;
import com.ndlan.g2.b2b.dao.B2bOrderStatusHisDao;

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
@Component("b2bOrderStatusHisAtomService")
public class B2bOrderStatusHisAtomServiceImpl  extends BaseService<B2bOrderStatusHisDao, 
	B2bOrderStatusHisBean>      implements B2bOrderStatusHisAtomService {

    @Resource(name="b2bOrderStatusHisDao")
    protected B2bOrderStatusHisDao b2bOrderStatusHisDao;

    @Override
    public int saveB2bOrderStatusHisBean(B2bOrderStatusHisBean b2bOrderStatusHis) {
        trimStringValue(b2bOrderStatusHis);
        return b2bOrderStatusHisDao.insertSelective(b2bOrderStatusHis);
    }

    @Override
    public int saveAndGetId(B2bOrderStatusHisBean b2bOrderStatusHis) {
        trimStringValue(b2bOrderStatusHis);
        return b2bOrderStatusHisDao.insertSelectiveAndGetId(b2bOrderStatusHis);
    }

    @Override
    public int update(B2bOrderStatusHisBean b2bOrderStatusHis) {
        trimStringValue(b2bOrderStatusHis);
        return b2bOrderStatusHisDao.updateByPrimaryKeySelective(b2bOrderStatusHis);
    }

    @Override
    public int saveOrUpdateB2bOrderStatusHisBean(B2bOrderStatusHisBean b2bOrderStatusHis) {
        if (null == b2bOrderStatusHis.getOrderStatusHisId() ||
		"" == b2bOrderStatusHis.getOrderStatusHisId()) {
            return saveB2bOrderStatusHisBean(b2bOrderStatusHis);
        } else {
            return update(b2bOrderStatusHis);
        }
    }

    @Override
    public B2bOrderStatusHisBean getB2bOrderStatusHisBean(String orderStatusHisId) {
        return b2bOrderStatusHisDao.selectByPrimaryKey(orderStatusHisId);
    }

    @Override
    public List<B2bOrderStatusHisBean> getAll() {
        return b2bOrderStatusHisDao.selectAll();
    }

    @Override
    public void delete(String orderStatusHisId) {
         b2bOrderStatusHisDao.deleteByPrimaryKey(orderStatusHisId);
    }

    public List<B2bOrderStatusHisBean> queryB2bOrderStatusHisBean
	(B2bOrderStatusHisBean b2bOrderStatusHis, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrderStatusHis);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderStatusHisDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderStatusHisDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderStatusHisDao.update(sql, args);
    }

    public List<B2bOrderStatusHisBean> queryB2bOrderStatusHisBean
	(B2bOrderStatusHisBean b2bOrderStatusHis){
	SQLParam sqlParam=getWhereSQL(b2bOrderStatusHis);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderStatusHisDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderStatusHisBean b2bOrderStatusHis) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	Long orignVersion = b2bOrderStatusHis.getOrignVersion();
	if (orignVersion!=null  ) {
           sqlBuffer.append("orignVersion=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignVersion());
        } 
      
            
	  = b2bOrderStatusHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.get());
        } 
      
            
	Date orignUpdateTime = b2bOrderStatusHis.getOrignUpdateTime();
	if (orignUpdateTime!=null  ) {
           sqlBuffer.append("orignUpdateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignUpdateTime());
        } 
      
            
	  = b2bOrderStatusHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.get());
        } 
      
            
        String orignStatus = b2bOrderStatusHis.getOrignStatus();
        if (StringUtils.isNotBlank(orignStatus) ) {
           sqlBuffer.append("orignStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignStatus());
        }
      
            
	Date orignCreateTime = b2bOrderStatusHis.getOrignCreateTime();
	if (orignCreateTime!=null  ) {
           sqlBuffer.append("orignCreateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignCreateTime());
        } 
      
            
        String orignUpdateBy = b2bOrderStatusHis.getOrignUpdateBy();
        if (StringUtils.isNotBlank(orignUpdateBy) ) {
           sqlBuffer.append("orignUpdateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignUpdateBy());
        }
      
            
	  = b2bOrderStatusHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.get());
        } 
      
            
        String orderPkgCode = b2bOrderStatusHis.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrderPkgCode());
        }
      
            
        String orderPkgId = b2bOrderStatusHis.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrderPkgId());
        }
      
            
        String orignCreateBy = b2bOrderStatusHis.getOrignCreateBy();
        if (StringUtils.isNotBlank(orignCreateBy) ) {
           sqlBuffer.append("orignCreateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignCreateBy());
        }
      
            
	Long orignSynVersion = b2bOrderStatusHis.getOrignSynVersion();
	if (orignSynVersion!=null  ) {
           sqlBuffer.append("orignSynVersion=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrignSynVersion());
        } 
      
            
        String orderStatusHisId = b2bOrderStatusHis.getOrderStatusHisId();
        if (StringUtils.isNotBlank(orderStatusHisId) ) {
           sqlBuffer.append("orderStatusHisId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrderStatusHisId());
        }
      
            
	  = b2bOrderStatusHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.get());
        } 
      
            
        String updateReason = b2bOrderStatusHis.getUpdateReason();
        if (StringUtils.isNotBlank(updateReason) ) {
           sqlBuffer.append("updateReason=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getUpdateReason());
        }
      
            
        String orderHeadId = b2bOrderStatusHis.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) ) {
           sqlBuffer.append("orderHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderStatusHis.getOrderHeadId());
        }
      
            
	  = b2bOrderStatusHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    
	     param.add(b2bOrderStatusHis.get());
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
    public B2bOrderStatusHisBean trimStringValue(B2bOrderStatusHisBean b2bOrderStatusHis) {
        String orignStatus = b2bOrderStatusHis.getOrignStatus();
        if (StringUtils.isNotBlank(orignStatus) && orignStatus.length() > 2) {
            b2bOrderStatusHis.setOrignStatus(orignStatus.substring(0, 2));
        }

        String orignUpdateBy = b2bOrderStatusHis.getOrignUpdateBy();
        if (StringUtils.isNotBlank(orignUpdateBy) && orignUpdateBy.length() > 36) {
            b2bOrderStatusHis.setOrignUpdateBy(orignUpdateBy.substring(0, 36));
        }

        String orderPkgCode = b2bOrderStatusHis.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bOrderStatusHis.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String orderPkgId = b2bOrderStatusHis.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bOrderStatusHis.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String orignCreateBy = b2bOrderStatusHis.getOrignCreateBy();
        if (StringUtils.isNotBlank(orignCreateBy) && orignCreateBy.length() > 36) {
            b2bOrderStatusHis.setOrignCreateBy(orignCreateBy.substring(0, 36));
        }

        String orderStatusHisId = b2bOrderStatusHis.getOrderStatusHisId();
        if (StringUtils.isNotBlank(orderStatusHisId) && orderStatusHisId.length() > 36) {
            b2bOrderStatusHis.setOrderStatusHisId(orderStatusHisId.substring(0, 36));
        }

        String updateReason = b2bOrderStatusHis.getUpdateReason();
        if (StringUtils.isNotBlank(updateReason) && updateReason.length() > 255) {
            b2bOrderStatusHis.setUpdateReason(updateReason.substring(0, 255));
        }

        String orderHeadId = b2bOrderStatusHis.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) && orderHeadId.length() > 36) {
            b2bOrderStatusHis.setOrderHeadId(orderHeadId.substring(0, 36));
        }

        return b2bOrderStatusHis;
    }
}
