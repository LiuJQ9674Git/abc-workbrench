package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailBean;
import com.ndlan.g2.b2b.dao.B2bPriceCategoryDetailDao;

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
@Component("b2bPriceCategoryDetailAtomService")
public class B2bPriceCategoryDetailAtomServiceImpl  extends BaseService<B2bPriceCategoryDetailDao, 
	B2bPriceCategoryDetailBean>      implements B2bPriceCategoryDetailAtomService {

    @Resource(name="b2bPriceCategoryDetailDao")
    protected B2bPriceCategoryDetailDao b2bPriceCategoryDetailDao;

    @Override
    public int saveB2bPriceCategoryDetailBean(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        trimStringValue(b2bPriceCategoryDetail);
        return b2bPriceCategoryDetailDao.insertSelective(b2bPriceCategoryDetail);
    }

    @Override
    public int saveAndGetId(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        trimStringValue(b2bPriceCategoryDetail);
        return b2bPriceCategoryDetailDao.insertSelectiveAndGetId(b2bPriceCategoryDetail);
    }

    @Override
    public int update(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        trimStringValue(b2bPriceCategoryDetail);
        return b2bPriceCategoryDetailDao.updateByPrimaryKeySelective(b2bPriceCategoryDetail);
    }

    @Override
    public int saveOrUpdateB2bPriceCategoryDetailBean(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        if (null == b2bPriceCategoryDetail.getPriCatLineId() ||
		"" == b2bPriceCategoryDetail.getPriCatLineId()) {
            return saveB2bPriceCategoryDetailBean(b2bPriceCategoryDetail);
        } else {
            return update(b2bPriceCategoryDetail);
        }
    }

    @Override
    public B2bPriceCategoryDetailBean getB2bPriceCategoryDetailBean(String priCatLineId) {
        return b2bPriceCategoryDetailDao.selectByPrimaryKey(priCatLineId);
    }

    @Override
    public List<B2bPriceCategoryDetailBean> getAll() {
        return b2bPriceCategoryDetailDao.selectAll();
    }

    @Override
    public void delete(String priCatLineId) {
         b2bPriceCategoryDetailDao.deleteByPrimaryKey(priCatLineId);
    }

    public List<B2bPriceCategoryDetailBean> queryB2bPriceCategoryDetailBean
	(B2bPriceCategoryDetailBean b2bPriceCategoryDetail, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bPriceCategoryDetail);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bPriceCategoryDetailDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bPriceCategoryDetailDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bPriceCategoryDetailDao.update(sql, args);
    }

    public List<B2bPriceCategoryDetailBean> queryB2bPriceCategoryDetailBean
	(B2bPriceCategoryDetailBean b2bPriceCategoryDetail){
	SQLParam sqlParam=getWhereSQL(b2bPriceCategoryDetail);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bPriceCategoryDetailDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String startPointQyt = b2bPriceCategoryDetail.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) ) {
           sqlBuffer.append("startPointQyt=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getStartPointQyt());
        }
      
            
        String priCatLineId = b2bPriceCategoryDetail.getPriCatLineId();
        if (StringUtils.isNotBlank(priCatLineId) ) {
           sqlBuffer.append("priCatLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getPriCatLineId());
        }
      
            
	Date effectiveDate = b2bPriceCategoryDetail.getEffectiveDate();
	if (effectiveDate!=null  ) {
           sqlBuffer.append("effectiveDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getEffectiveDate());
        } 
      
            
        String baseProId = b2bPriceCategoryDetail.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getBaseProId());
        }
      
            
        String parentNamePath = b2bPriceCategoryDetail.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getParentNamePath());
        }
      
            
        String priCatHeadId = b2bPriceCategoryDetail.getPriCatHeadId();
        if (StringUtils.isNotBlank(priCatHeadId) ) {
           sqlBuffer.append("priCatHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getPriCatHeadId());
        }
      
            
        String agencyId = b2bPriceCategoryDetail.getAgencyId();
        if (StringUtils.isNotBlank(agencyId) ) {
           sqlBuffer.append("agencyId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getAgencyId());
        }
      
            
	Date expiryDate = b2bPriceCategoryDetail.getExpiryDate();
	if (expiryDate!=null  ) {
           sqlBuffer.append("expiryDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getExpiryDate());
        } 
      
            
        String specsId = b2bPriceCategoryDetail.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getSpecsId());
        }
      
            
        String parentIdPath = b2bPriceCategoryDetail.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getParentIdPath());
        }
      
            
        String categoryId = b2bPriceCategoryDetail.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getCategoryId());
        }
      
            
        String name = b2bPriceCategoryDetail.getName();
        if (StringUtils.isNotBlank(name) ) {
           sqlBuffer.append("name=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getName());
        }
      
            
        String promotionPrice = b2bPriceCategoryDetail.getPromotionPrice();
        if (StringUtils.isNotBlank(promotionPrice) ) {
           sqlBuffer.append("promotionPrice=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getPromotionPrice());
        }
      
            
        String proColorNo = b2bPriceCategoryDetail.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getProColorNo());
        }
      
            
        String categoryName = b2bPriceCategoryDetail.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getCategoryName());
        }
      
            
        String proId = b2bPriceCategoryDetail.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getProId());
        }
      
            
        String baseProNo = b2bPriceCategoryDetail.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getBaseProNo());
        }
      
            
	  = b2bPriceCategoryDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.get());
        } 
      
            
        String specsName = b2bPriceCategoryDetail.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getSpecsName());
        }
      
            
        String price = b2bPriceCategoryDetail.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getPrice());
        }
      
            
	  = b2bPriceCategoryDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.get());
        } 
      
            
	  = b2bPriceCategoryDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.get());
        } 
      
            
        String spuulierName = b2bPriceCategoryDetail.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) ) {
           sqlBuffer.append("spuulierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getSpuulierName());
        }
      
            
        String agencyName = b2bPriceCategoryDetail.getAgencyName();
        if (StringUtils.isNotBlank(agencyName) ) {
           sqlBuffer.append("agencyName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getAgencyName());
        }
      
            
        String volume = b2bPriceCategoryDetail.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getVolume());
        }
      
            
        String remarks = b2bPriceCategoryDetail.getRemarks();
        if (StringUtils.isNotBlank(remarks) ) {
           sqlBuffer.append("remarks=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getRemarks());
        }
      
            
        String supplierId = b2bPriceCategoryDetail.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.getSupplierId());
        }
      
            
	  = b2bPriceCategoryDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryDetail.get());
        } 
      
            
	  = b2bPriceCategoryDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    
	     param.add(b2bPriceCategoryDetail.get());
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
    public B2bPriceCategoryDetailBean trimStringValue(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        String startPointQyt = b2bPriceCategoryDetail.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) && startPointQyt.length() > 100) {
            b2bPriceCategoryDetail.setStartPointQyt(startPointQyt.substring(0, 100));
        }

        String priCatLineId = b2bPriceCategoryDetail.getPriCatLineId();
        if (StringUtils.isNotBlank(priCatLineId) && priCatLineId.length() > 36) {
            b2bPriceCategoryDetail.setPriCatLineId(priCatLineId.substring(0, 36));
        }

        String baseProId = b2bPriceCategoryDetail.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bPriceCategoryDetail.setBaseProId(baseProId.substring(0, 36));
        }

        String parentNamePath = b2bPriceCategoryDetail.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bPriceCategoryDetail.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String priCatHeadId = b2bPriceCategoryDetail.getPriCatHeadId();
        if (StringUtils.isNotBlank(priCatHeadId) && priCatHeadId.length() > 36) {
            b2bPriceCategoryDetail.setPriCatHeadId(priCatHeadId.substring(0, 36));
        }

        String agencyId = b2bPriceCategoryDetail.getAgencyId();
        if (StringUtils.isNotBlank(agencyId) && agencyId.length() > 36) {
            b2bPriceCategoryDetail.setAgencyId(agencyId.substring(0, 36));
        }

        String specsId = b2bPriceCategoryDetail.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bPriceCategoryDetail.setSpecsId(specsId.substring(0, 36));
        }

        String parentIdPath = b2bPriceCategoryDetail.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 255) {
            b2bPriceCategoryDetail.setParentIdPath(parentIdPath.substring(0, 255));
        }

        String categoryId = b2bPriceCategoryDetail.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bPriceCategoryDetail.setCategoryId(categoryId.substring(0, 36));
        }

        String name = b2bPriceCategoryDetail.getName();
        if (StringUtils.isNotBlank(name) && name.length() > 255) {
            b2bPriceCategoryDetail.setName(name.substring(0, 255));
        }

        String promotionPrice = b2bPriceCategoryDetail.getPromotionPrice();
        if (StringUtils.isNotBlank(promotionPrice) && promotionPrice.length() > 100) {
            b2bPriceCategoryDetail.setPromotionPrice(promotionPrice.substring(0, 100));
        }

        String proColorNo = b2bPriceCategoryDetail.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bPriceCategoryDetail.setProColorNo(proColorNo.substring(0, 36));
        }

        String categoryName = b2bPriceCategoryDetail.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bPriceCategoryDetail.setCategoryName(categoryName.substring(0, 255));
        }

        String proId = b2bPriceCategoryDetail.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bPriceCategoryDetail.setProId(proId.substring(0, 36));
        }

        String baseProNo = b2bPriceCategoryDetail.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bPriceCategoryDetail.setBaseProNo(baseProNo.substring(0, 50));
        }

        String specsName = b2bPriceCategoryDetail.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bPriceCategoryDetail.setSpecsName(specsName.substring(0, 255));
        }

        String price = b2bPriceCategoryDetail.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bPriceCategoryDetail.setPrice(price.substring(0, 100));
        }

        String spuulierName = b2bPriceCategoryDetail.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) && spuulierName.length() > 255) {
            b2bPriceCategoryDetail.setSpuulierName(spuulierName.substring(0, 255));
        }

        String agencyName = b2bPriceCategoryDetail.getAgencyName();
        if (StringUtils.isNotBlank(agencyName) && agencyName.length() > 255) {
            b2bPriceCategoryDetail.setAgencyName(agencyName.substring(0, 255));
        }

        String volume = b2bPriceCategoryDetail.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bPriceCategoryDetail.setVolume(volume.substring(0, 10));
        }

        String remarks = b2bPriceCategoryDetail.getRemarks();
        if (StringUtils.isNotBlank(remarks) && remarks.length() > 255) {
            b2bPriceCategoryDetail.setRemarks(remarks.substring(0, 255));
        }

        String supplierId = b2bPriceCategoryDetail.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bPriceCategoryDetail.setSupplierId(supplierId.substring(0, 36));
        }

        return b2bPriceCategoryDetail;
    }
}
