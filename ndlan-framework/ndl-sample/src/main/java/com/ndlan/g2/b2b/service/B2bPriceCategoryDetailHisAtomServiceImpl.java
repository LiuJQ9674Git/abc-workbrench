package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailHisBean;
import com.ndlan.g2.b2b.dao.B2bPriceCategoryDetailHisDao;

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
@Component("b2bPriceCategoryDetailHisAtomService")
public class B2bPriceCategoryDetailHisAtomServiceImpl  extends BaseService<B2bPriceCategoryDetailHisDao, 
	B2bPriceCategoryDetailHisBean>      implements B2bPriceCategoryDetailHisAtomService {

    @Resource(name="b2bPriceCategoryDetailHisDao")
    protected B2bPriceCategoryDetailHisDao b2bPriceCategoryDetailHisDao;

    @Override
    public int saveB2bPriceCategoryDetailHisBean(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        trimStringValue(b2bPriceCategoryDetailHis);
        return b2bPriceCategoryDetailHisDao.insertSelective(b2bPriceCategoryDetailHis);
    }

    @Override
    public int saveAndGetId(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        trimStringValue(b2bPriceCategoryDetailHis);
        return b2bPriceCategoryDetailHisDao.insertSelectiveAndGetId(b2bPriceCategoryDetailHis);
    }

    @Override
    public int update(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        trimStringValue(b2bPriceCategoryDetailHis);
        return b2bPriceCategoryDetailHisDao.updateByPrimaryKeySelective(b2bPriceCategoryDetailHis);
    }

    @Override
    public int saveOrUpdateB2bPriceCategoryDetailHisBean(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        if (null == b2bPriceCategoryDetailHis.getPriCtyDtlhisId() ||
		"" == b2bPriceCategoryDetailHis.getPriCtyDtlhisId()) {
            return saveB2bPriceCategoryDetailHisBean(b2bPriceCategoryDetailHis);
        } else {
            return update(b2bPriceCategoryDetailHis);
        }
    }

    @Override
    public B2bPriceCategoryDetailHisBean getB2bPriceCategoryDetailHisBean(String priCtyDtlhisId) {
        return b2bPriceCategoryDetailHisDao.selectByPrimaryKey(priCtyDtlhisId);
    }

    @Override
    public List<B2bPriceCategoryDetailHisBean> getAll() {
        return b2bPriceCategoryDetailHisDao.selectAll();
    }

    @Override
    public void delete(String priCtyDtlhisId) {
         b2bPriceCategoryDetailHisDao.deleteByPrimaryKey(priCtyDtlhisId);
    }

    public List<B2bPriceCategoryDetailHisBean> queryB2bPriceCategoryDetailHisBean
	(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bPriceCategoryDetailHis);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bPriceCategoryDetailHisDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bPriceCategoryDetailHisDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bPriceCategoryDetailHisDao.update(sql, args);
    }

    public List<B2bPriceCategoryDetailHisBean> queryB2bPriceCategoryDetailHisBean
	(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis){
	SQLParam sqlParam=getWhereSQL(b2bPriceCategoryDetailHis);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bPriceCategoryDetailHisDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String price = b2bPriceCategoryDetailHis.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getPrice());
        }
      
            
	Date orignUpdateTime = b2bPriceCategoryDetailHis.getOrignUpdateTime();
	if (orignUpdateTime!=null  ) {
           sqlBuffer.append("orignUpdateTime=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getOrignUpdateTime());
        } 
      
            
	  = b2bPriceCategoryDetailHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.get());
        } 
      
            
	  = b2bPriceCategoryDetailHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.get());
        } 
      
            
        String orignCreateBy = b2bPriceCategoryDetailHis.getOrignCreateBy();
        if (StringUtils.isNotBlank(orignCreateBy) ) {
           sqlBuffer.append("orignCreateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getOrignCreateBy());
        }
      
            
        String priCtyDtlhisId = b2bPriceCategoryDetailHis.getPriCtyDtlhisId();
        if (StringUtils.isNotBlank(priCtyDtlhisId) ) {
           sqlBuffer.append("priCtyDtlhisId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getPriCtyDtlhisId());
        }
      
            
        String orignUpdateBy = b2bPriceCategoryDetailHis.getOrignUpdateBy();
        if (StringUtils.isNotBlank(orignUpdateBy) ) {
           sqlBuffer.append("orignUpdateBy=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getOrignUpdateBy());
        }
      
            
        String priCatLineId = b2bPriceCategoryDetailHis.getPriCatLineId();
        if (StringUtils.isNotBlank(priCatLineId) ) {
           sqlBuffer.append("priCatLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getPriCatLineId());
        }
      
            
	  = b2bPriceCategoryDetailHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.get());
        } 
      
            
        String startPointQyt = b2bPriceCategoryDetailHis.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) ) {
           sqlBuffer.append("startPointQyt=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getStartPointQyt());
        }
      
            
	Long orignSynVersion = b2bPriceCategoryDetailHis.getOrignSynVersion();
	if (orignSynVersion!=null  ) {
           sqlBuffer.append("orignSynVersion=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getOrignSynVersion());
        } 
      
            
	  = b2bPriceCategoryDetailHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.get());
        } 
      
            
        String priCatHeadId = b2bPriceCategoryDetailHis.getPriCatHeadId();
        if (StringUtils.isNotBlank(priCatHeadId) ) {
           sqlBuffer.append("priCatHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getPriCatHeadId());
        }
      
            
        String remarks = b2bPriceCategoryDetailHis.getRemarks();
        if (StringUtils.isNotBlank(remarks) ) {
           sqlBuffer.append("remarks=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getRemarks());
        }
      
            
	Date expiryDate = b2bPriceCategoryDetailHis.getExpiryDate();
	if (expiryDate!=null  ) {
           sqlBuffer.append("expiryDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getExpiryDate());
        } 
      
            
	  = b2bPriceCategoryDetailHis.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.get());
        } 
      
            
	Long orignVersion = b2bPriceCategoryDetailHis.getOrignVersion();
	if (orignVersion!=null  ) {
           sqlBuffer.append("orignVersion=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getOrignVersion());
        } 
      
            
	Date effectiveDate = b2bPriceCategoryDetailHis.getEffectiveDate();
	if (effectiveDate!=null  ) {
           sqlBuffer.append("effectiveDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetailHis.getEffectiveDate());
        } 
      
            
	Date orignCreateTime = b2bPriceCategoryDetailHis.getOrignCreateTime();
	if (orignCreateTime!=null  ) {
           sqlBuffer.append("orignCreateTime=?");
	    
	     param.add(b2bPriceCategoryDetailHis.getOrignCreateTime());
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
    public B2bPriceCategoryDetailHisBean trimStringValue(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        String price = b2bPriceCategoryDetailHis.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bPriceCategoryDetailHis.setPrice(price.substring(0, 100));
        }

        String orignCreateBy = b2bPriceCategoryDetailHis.getOrignCreateBy();
        if (StringUtils.isNotBlank(orignCreateBy) && orignCreateBy.length() > 36) {
            b2bPriceCategoryDetailHis.setOrignCreateBy(orignCreateBy.substring(0, 36));
        }

        String priCtyDtlhisId = b2bPriceCategoryDetailHis.getPriCtyDtlhisId();
        if (StringUtils.isNotBlank(priCtyDtlhisId) && priCtyDtlhisId.length() > 36) {
            b2bPriceCategoryDetailHis.setPriCtyDtlhisId(priCtyDtlhisId.substring(0, 36));
        }

        String orignUpdateBy = b2bPriceCategoryDetailHis.getOrignUpdateBy();
        if (StringUtils.isNotBlank(orignUpdateBy) && orignUpdateBy.length() > 36) {
            b2bPriceCategoryDetailHis.setOrignUpdateBy(orignUpdateBy.substring(0, 36));
        }

        String priCatLineId = b2bPriceCategoryDetailHis.getPriCatLineId();
        if (StringUtils.isNotBlank(priCatLineId) && priCatLineId.length() > 36) {
            b2bPriceCategoryDetailHis.setPriCatLineId(priCatLineId.substring(0, 36));
        }

        String startPointQyt = b2bPriceCategoryDetailHis.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) && startPointQyt.length() > 100) {
            b2bPriceCategoryDetailHis.setStartPointQyt(startPointQyt.substring(0, 100));
        }

        String priCatHeadId = b2bPriceCategoryDetailHis.getPriCatHeadId();
        if (StringUtils.isNotBlank(priCatHeadId) && priCatHeadId.length() > 36) {
            b2bPriceCategoryDetailHis.setPriCatHeadId(priCatHeadId.substring(0, 36));
        }

        String remarks = b2bPriceCategoryDetailHis.getRemarks();
        if (StringUtils.isNotBlank(remarks) && remarks.length() > 255) {
            b2bPriceCategoryDetailHis.setRemarks(remarks.substring(0, 255));
        }

        return b2bPriceCategoryDetailHis;
    }
}
