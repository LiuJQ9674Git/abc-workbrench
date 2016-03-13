package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryHeadBean;
import com.ndlan.g2.b2b.dao.B2bPriceCategoryHeadDao;

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
@Component("b2bPriceCategoryHeadAtomService")
public class B2bPriceCategoryHeadAtomServiceImpl  extends BaseService<B2bPriceCategoryHeadDao, 
	B2bPriceCategoryHeadBean>      implements B2bPriceCategoryHeadAtomService {

    @Resource(name="b2bPriceCategoryHeadDao")
    protected B2bPriceCategoryHeadDao b2bPriceCategoryHeadDao;

    @Override
    public int saveB2bPriceCategoryHeadBean(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        trimStringValue(b2bPriceCategoryHead);
        return b2bPriceCategoryHeadDao.insertSelective(b2bPriceCategoryHead);
    }

    @Override
    public int saveAndGetId(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        trimStringValue(b2bPriceCategoryHead);
        return b2bPriceCategoryHeadDao.insertSelectiveAndGetId(b2bPriceCategoryHead);
    }

    @Override
    public int update(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        trimStringValue(b2bPriceCategoryHead);
        return b2bPriceCategoryHeadDao.updateByPrimaryKeySelective(b2bPriceCategoryHead);
    }

    @Override
    public int saveOrUpdateB2bPriceCategoryHeadBean(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        if (null == b2bPriceCategoryHead.getPriCatHeadId() ||
		"" == b2bPriceCategoryHead.getPriCatHeadId()) {
            return saveB2bPriceCategoryHeadBean(b2bPriceCategoryHead);
        } else {
            return update(b2bPriceCategoryHead);
        }
    }

    @Override
    public B2bPriceCategoryHeadBean getB2bPriceCategoryHeadBean(String priCatHeadId) {
        return b2bPriceCategoryHeadDao.selectByPrimaryKey(priCatHeadId);
    }

    @Override
    public List<B2bPriceCategoryHeadBean> getAll() {
        return b2bPriceCategoryHeadDao.selectAll();
    }

    @Override
    public void delete(String priCatHeadId) {
         b2bPriceCategoryHeadDao.deleteByPrimaryKey(priCatHeadId);
    }

    public List<B2bPriceCategoryHeadBean> queryB2bPriceCategoryHeadBean
	(B2bPriceCategoryHeadBean b2bPriceCategoryHead, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bPriceCategoryHead);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bPriceCategoryHeadDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bPriceCategoryHeadDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bPriceCategoryHeadDao.update(sql, args);
    }

    public List<B2bPriceCategoryHeadBean> queryB2bPriceCategoryHeadBean
	(B2bPriceCategoryHeadBean b2bPriceCategoryHead){
	SQLParam sqlParam=getWhereSQL(b2bPriceCategoryHead);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bPriceCategoryHeadDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String barCode = b2bPriceCategoryHead.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getBarCode());
        }
      
            
        String pic = b2bPriceCategoryHead.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getPic());
        }
      
            
        String proId = b2bPriceCategoryHead.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getProId());
        }
      
            
        String categoryName = b2bPriceCategoryHead.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getCategoryName());
        }
      
            
        String agencyId = b2bPriceCategoryHead.getAgencyId();
        if (StringUtils.isNotBlank(agencyId) ) {
           sqlBuffer.append("agencyId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getAgencyId());
        }
      
            
        String name = b2bPriceCategoryHead.getName();
        if (StringUtils.isNotBlank(name) ) {
           sqlBuffer.append("name=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getName());
        }
      
            
        String targetClient = b2bPriceCategoryHead.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getTargetClient());
        }
      
            
        String baseProNo = b2bPriceCategoryHead.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getBaseProNo());
        }
      
            
        String proDesc = b2bPriceCategoryHead.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getProDesc());
        }
      
            
        String capacity = b2bPriceCategoryHead.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getCapacity());
        }
      
            
	  = b2bPriceCategoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.get());
        } 
      
            
        String specsName = b2bPriceCategoryHead.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getSpecsName());
        }
      
            
        String spuulierName = b2bPriceCategoryHead.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) ) {
           sqlBuffer.append("spuulierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getSpuulierName());
        }
      
            
	  = b2bPriceCategoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.get());
        } 
      
            
        String agencyName = b2bPriceCategoryHead.getAgencyName();
        if (StringUtils.isNotBlank(agencyName) ) {
           sqlBuffer.append("agencyName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getAgencyName());
        }
      
            
        String parentIdPath = b2bPriceCategoryHead.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getParentIdPath());
        }
      
            
        String proColorNo = b2bPriceCategoryHead.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getProColorNo());
        }
      
            
        String parentNamePath = b2bPriceCategoryHead.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getParentNamePath());
        }
      
            
        String remarks = b2bPriceCategoryHead.getRemarks();
        if (StringUtils.isNotBlank(remarks) ) {
           sqlBuffer.append("remarks=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getRemarks());
        }
      
            
        String supplierId = b2bPriceCategoryHead.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getSupplierId());
        }
      
            
        String priCatHeadId = b2bPriceCategoryHead.getPriCatHeadId();
        if (StringUtils.isNotBlank(priCatHeadId) ) {
           sqlBuffer.append("priCatHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getPriCatHeadId());
        }
      
            
        String specsId = b2bPriceCategoryHead.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getSpecsId());
        }
      
            
        String baseProId = b2bPriceCategoryHead.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getBaseProId());
        }
      
            
        String categoryId = b2bPriceCategoryHead.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getCategoryId());
        }
      
            
	  = b2bPriceCategoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.get());
        } 
      
            
	  = b2bPriceCategoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.get());
        } 
      
            
        String applDesc = b2bPriceCategoryHead.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getApplDesc());
        }
      
            
        String price = b2bPriceCategoryHead.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getPrice());
        }
      
            
	  = b2bPriceCategoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.get());
        } 
      
            
        String volume = b2bPriceCategoryHead.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bPriceCategoryHead.getVolume());
        }
      
            
        String size = b2bPriceCategoryHead.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    
	     param.add(b2bPriceCategoryHead.getSize());
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
    public B2bPriceCategoryHeadBean trimStringValue(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        String barCode = b2bPriceCategoryHead.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bPriceCategoryHead.setBarCode(barCode.substring(0, 36));
        }

        String pic = b2bPriceCategoryHead.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 100) {
            b2bPriceCategoryHead.setPic(pic.substring(0, 100));
        }

        String proId = b2bPriceCategoryHead.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bPriceCategoryHead.setProId(proId.substring(0, 36));
        }

        String categoryName = b2bPriceCategoryHead.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bPriceCategoryHead.setCategoryName(categoryName.substring(0, 255));
        }

        String agencyId = b2bPriceCategoryHead.getAgencyId();
        if (StringUtils.isNotBlank(agencyId) && agencyId.length() > 36) {
            b2bPriceCategoryHead.setAgencyId(agencyId.substring(0, 36));
        }

        String name = b2bPriceCategoryHead.getName();
        if (StringUtils.isNotBlank(name) && name.length() > 255) {
            b2bPriceCategoryHead.setName(name.substring(0, 255));
        }

        String targetClient = b2bPriceCategoryHead.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bPriceCategoryHead.setTargetClient(targetClient.substring(0, 20));
        }

        String baseProNo = b2bPriceCategoryHead.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bPriceCategoryHead.setBaseProNo(baseProNo.substring(0, 50));
        }

        String proDesc = b2bPriceCategoryHead.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bPriceCategoryHead.setProDesc(proDesc.substring(0, 255));
        }

        String capacity = b2bPriceCategoryHead.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 100) {
            b2bPriceCategoryHead.setCapacity(capacity.substring(0, 100));
        }

        String specsName = b2bPriceCategoryHead.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bPriceCategoryHead.setSpecsName(specsName.substring(0, 255));
        }

        String spuulierName = b2bPriceCategoryHead.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) && spuulierName.length() > 255) {
            b2bPriceCategoryHead.setSpuulierName(spuulierName.substring(0, 255));
        }

        String agencyName = b2bPriceCategoryHead.getAgencyName();
        if (StringUtils.isNotBlank(agencyName) && agencyName.length() > 255) {
            b2bPriceCategoryHead.setAgencyName(agencyName.substring(0, 255));
        }

        String parentIdPath = b2bPriceCategoryHead.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 255) {
            b2bPriceCategoryHead.setParentIdPath(parentIdPath.substring(0, 255));
        }

        String proColorNo = b2bPriceCategoryHead.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bPriceCategoryHead.setProColorNo(proColorNo.substring(0, 36));
        }

        String parentNamePath = b2bPriceCategoryHead.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bPriceCategoryHead.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String remarks = b2bPriceCategoryHead.getRemarks();
        if (StringUtils.isNotBlank(remarks) && remarks.length() > 255) {
            b2bPriceCategoryHead.setRemarks(remarks.substring(0, 255));
        }

        String supplierId = b2bPriceCategoryHead.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bPriceCategoryHead.setSupplierId(supplierId.substring(0, 36));
        }

        String priCatHeadId = b2bPriceCategoryHead.getPriCatHeadId();
        if (StringUtils.isNotBlank(priCatHeadId) && priCatHeadId.length() > 36) {
            b2bPriceCategoryHead.setPriCatHeadId(priCatHeadId.substring(0, 36));
        }

        String specsId = b2bPriceCategoryHead.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bPriceCategoryHead.setSpecsId(specsId.substring(0, 36));
        }

        String baseProId = b2bPriceCategoryHead.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bPriceCategoryHead.setBaseProId(baseProId.substring(0, 36));
        }

        String categoryId = b2bPriceCategoryHead.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bPriceCategoryHead.setCategoryId(categoryId.substring(0, 36));
        }

        String applDesc = b2bPriceCategoryHead.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bPriceCategoryHead.setApplDesc(applDesc.substring(0, 255));
        }

        String price = b2bPriceCategoryHead.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bPriceCategoryHead.setPrice(price.substring(0, 100));
        }

        String volume = b2bPriceCategoryHead.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bPriceCategoryHead.setVolume(volume.substring(0, 10));
        }

        String size = b2bPriceCategoryHead.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bPriceCategoryHead.setSize(size.substring(0, 36));
        }

        return b2bPriceCategoryHead;
    }
}
