package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductClientBean;
import com.ndlan.g2.b2b.dao.B2bProductClientDao;

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
@Component("b2bProductClientAtomService")
public class B2bProductClientAtomServiceImpl  extends BaseService<B2bProductClientDao, 
	B2bProductClientBean>      implements B2bProductClientAtomService {

    @Resource(name="b2bProductClientDao")
    protected B2bProductClientDao b2bProductClientDao;

    @Override
    public int saveB2bProductClientBean(B2bProductClientBean b2bProductClient) {
        trimStringValue(b2bProductClient);
        return b2bProductClientDao.insertSelective(b2bProductClient);
    }

    @Override
    public int saveAndGetId(B2bProductClientBean b2bProductClient) {
        trimStringValue(b2bProductClient);
        return b2bProductClientDao.insertSelectiveAndGetId(b2bProductClient);
    }

    @Override
    public int update(B2bProductClientBean b2bProductClient) {
        trimStringValue(b2bProductClient);
        return b2bProductClientDao.updateByPrimaryKeySelective(b2bProductClient);
    }

    @Override
    public int saveOrUpdateB2bProductClientBean(B2bProductClientBean b2bProductClient) {
        if (null == b2bProductClient.getProId() ||
		"" == b2bProductClient.getProId()) {
            return saveB2bProductClientBean(b2bProductClient);
        } else {
            return update(b2bProductClient);
        }
    }

    @Override
    public B2bProductClientBean getB2bProductClientBean(String proId) {
        return b2bProductClientDao.selectByPrimaryKey(proId);
    }

    @Override
    public List<B2bProductClientBean> getAll() {
        return b2bProductClientDao.selectAll();
    }

    @Override
    public void delete(String proId) {
         b2bProductClientDao.deleteByPrimaryKey(proId);
    }

    public List<B2bProductClientBean> queryB2bProductClientBean
	(B2bProductClientBean b2bProductClient, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bProductClient);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bProductClientDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bProductClientDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bProductClientDao.update(sql, args);
    }

    public List<B2bProductClientBean> queryB2bProductClientBean
	(B2bProductClientBean b2bProductClient){
	SQLParam sqlParam=getWhereSQL(b2bProductClient);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bProductClientDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bProductClientBean b2bProductClient) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	  = b2bProductClient.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.get());
        } 
      
            
        String size = b2bProductClient.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getSize());
        }
      
            
        String spuulierName = b2bProductClient.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) ) {
           sqlBuffer.append("spuulierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getSpuulierName());
        }
      
            
	  = b2bProductClient.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.get());
        } 
      
            
        String supplierId = b2bProductClient.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getSupplierId());
        }
      
            
        String name = b2bProductClient.getName();
        if (StringUtils.isNotBlank(name) ) {
           sqlBuffer.append("name=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getName());
        }
      
            
	  = b2bProductClient.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.get());
        } 
      
            
        String capacity = b2bProductClient.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getCapacity());
        }
      
            
        String proDesc = b2bProductClient.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getProDesc());
        }
      
            
        String supplierType = b2bProductClient.getSupplierType();
        if (StringUtils.isNotBlank(supplierType) ) {
           sqlBuffer.append("supplierType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getSupplierType());
        }
      
            
        String applDesc = b2bProductClient.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getApplDesc());
        }
      
            
        String goodsAttr4 = b2bProductClient.getGoodsAttr4();
        if (StringUtils.isNotBlank(goodsAttr4) ) {
           sqlBuffer.append("goodsAttr4=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getGoodsAttr4());
        }
      
            
        String pic = b2bProductClient.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getPic());
        }
      
            
        String volume = b2bProductClient.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getVolume());
        }
      
            
	  = b2bProductClient.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.get());
        } 
      
            
        String restId = b2bProductClient.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getRestId());
        }
      
            
        String price = b2bProductClient.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getPrice());
        }
      
            
        String parentIdPath = b2bProductClient.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getParentIdPath());
        }
      
            
        String proColorNo = b2bProductClient.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getProColorNo());
        }
      
            
        String remarks = b2bProductClient.getRemarks();
        if (StringUtils.isNotBlank(remarks) ) {
           sqlBuffer.append("remarks=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getRemarks());
        }
      
            
	  = b2bProductClient.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.get());
        } 
      
            
        String specsName = b2bProductClient.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getSpecsName());
        }
      
            
        String specsId = b2bProductClient.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getSpecsId());
        }
      
            
        String baseProNo = b2bProductClient.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getBaseProNo());
        }
      
            
        String categoryId = b2bProductClient.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getCategoryId());
        }
      
            
        String goodsAttr3 = b2bProductClient.getGoodsAttr3();
        if (StringUtils.isNotBlank(goodsAttr3) ) {
           sqlBuffer.append("goodsAttr3=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getGoodsAttr3());
        }
      
            
        String goodsAttr2 = b2bProductClient.getGoodsAttr2();
        if (StringUtils.isNotBlank(goodsAttr2) ) {
           sqlBuffer.append("goodsAttr2=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getGoodsAttr2());
        }
      
            
        String quantity = b2bProductClient.getQuantity();
        if (StringUtils.isNotBlank(quantity) ) {
           sqlBuffer.append("quantity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getQuantity());
        }
      
            
        String barCode = b2bProductClient.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getBarCode());
        }
      
            
        String goodsAttr1 = b2bProductClient.getGoodsAttr1();
        if (StringUtils.isNotBlank(goodsAttr1) ) {
           sqlBuffer.append("goodsAttr1=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getGoodsAttr1());
        }
      
            
        String targetClient = b2bProductClient.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getTargetClient());
        }
      
            
        String primeCost = b2bProductClient.getPrimeCost();
        if (StringUtils.isNotBlank(primeCost) ) {
           sqlBuffer.append("primeCost=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getPrimeCost());
        }
      
            
        String categoryName = b2bProductClient.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getCategoryName());
        }
      
            
        String baseStatus = b2bProductClient.getBaseStatus();
        if (StringUtils.isNotBlank(baseStatus) ) {
           sqlBuffer.append("baseStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getBaseStatus());
        }
      
            
        String goodsAttr5 = b2bProductClient.getGoodsAttr5();
        if (StringUtils.isNotBlank(goodsAttr5) ) {
           sqlBuffer.append("goodsAttr5=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getGoodsAttr5());
        }
      
            
        String baseProId = b2bProductClient.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getBaseProId());
        }
      
            
        String parentNamePath = b2bProductClient.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getParentNamePath());
        }
      
            
        String proId = b2bProductClient.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProductClient.getProId());
        }
      
            
        String picAll = b2bProductClient.getPicAll();
        if (StringUtils.isNotBlank(picAll) ) {
           sqlBuffer.append("picAll=?");
	    
	     param.add(b2bProductClient.getPicAll());
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
    public B2bProductClientBean trimStringValue(B2bProductClientBean b2bProductClient) {
        String size = b2bProductClient.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bProductClient.setSize(size.substring(0, 36));
        }

        String spuulierName = b2bProductClient.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) && spuulierName.length() > 255) {
            b2bProductClient.setSpuulierName(spuulierName.substring(0, 255));
        }

        String supplierId = b2bProductClient.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bProductClient.setSupplierId(supplierId.substring(0, 36));
        }

        String name = b2bProductClient.getName();
        if (StringUtils.isNotBlank(name) && name.length() > 255) {
            b2bProductClient.setName(name.substring(0, 255));
        }

        String capacity = b2bProductClient.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 11) {
            b2bProductClient.setCapacity(capacity.substring(0, 11));
        }

        String proDesc = b2bProductClient.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bProductClient.setProDesc(proDesc.substring(0, 255));
        }

        String supplierType = b2bProductClient.getSupplierType();
        if (StringUtils.isNotBlank(supplierType) && supplierType.length() > 4) {
            b2bProductClient.setSupplierType(supplierType.substring(0, 4));
        }

        String applDesc = b2bProductClient.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bProductClient.setApplDesc(applDesc.substring(0, 255));
        }

        String goodsAttr4 = b2bProductClient.getGoodsAttr4();
        if (StringUtils.isNotBlank(goodsAttr4) && goodsAttr4.length() > 36) {
            b2bProductClient.setGoodsAttr4(goodsAttr4.substring(0, 36));
        }

        String pic = b2bProductClient.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 100) {
            b2bProductClient.setPic(pic.substring(0, 100));
        }

        String volume = b2bProductClient.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 36) {
            b2bProductClient.setVolume(volume.substring(0, 36));
        }

        String restId = b2bProductClient.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bProductClient.setRestId(restId.substring(0, 36));
        }

        String price = b2bProductClient.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bProductClient.setPrice(price.substring(0, 100));
        }

        String parentIdPath = b2bProductClient.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 255) {
            b2bProductClient.setParentIdPath(parentIdPath.substring(0, 255));
        }

        String proColorNo = b2bProductClient.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bProductClient.setProColorNo(proColorNo.substring(0, 36));
        }

        String remarks = b2bProductClient.getRemarks();
        if (StringUtils.isNotBlank(remarks) && remarks.length() > 255) {
            b2bProductClient.setRemarks(remarks.substring(0, 255));
        }

        String specsName = b2bProductClient.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bProductClient.setSpecsName(specsName.substring(0, 255));
        }

        String specsId = b2bProductClient.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bProductClient.setSpecsId(specsId.substring(0, 36));
        }

        String baseProNo = b2bProductClient.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 36) {
            b2bProductClient.setBaseProNo(baseProNo.substring(0, 36));
        }

        String categoryId = b2bProductClient.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bProductClient.setCategoryId(categoryId.substring(0, 36));
        }

        String goodsAttr3 = b2bProductClient.getGoodsAttr3();
        if (StringUtils.isNotBlank(goodsAttr3) && goodsAttr3.length() > 36) {
            b2bProductClient.setGoodsAttr3(goodsAttr3.substring(0, 36));
        }

        String goodsAttr2 = b2bProductClient.getGoodsAttr2();
        if (StringUtils.isNotBlank(goodsAttr2) && goodsAttr2.length() > 36) {
            b2bProductClient.setGoodsAttr2(goodsAttr2.substring(0, 36));
        }

        String quantity = b2bProductClient.getQuantity();
        if (StringUtils.isNotBlank(quantity) && quantity.length() > 100) {
            b2bProductClient.setQuantity(quantity.substring(0, 100));
        }

        String barCode = b2bProductClient.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bProductClient.setBarCode(barCode.substring(0, 36));
        }

        String goodsAttr1 = b2bProductClient.getGoodsAttr1();
        if (StringUtils.isNotBlank(goodsAttr1) && goodsAttr1.length() > 36) {
            b2bProductClient.setGoodsAttr1(goodsAttr1.substring(0, 36));
        }

        String targetClient = b2bProductClient.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bProductClient.setTargetClient(targetClient.substring(0, 20));
        }

        String primeCost = b2bProductClient.getPrimeCost();
        if (StringUtils.isNotBlank(primeCost) && primeCost.length() > 100) {
            b2bProductClient.setPrimeCost(primeCost.substring(0, 100));
        }

        String categoryName = b2bProductClient.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bProductClient.setCategoryName(categoryName.substring(0, 255));
        }

        String baseStatus = b2bProductClient.getBaseStatus();
        if (StringUtils.isNotBlank(baseStatus) && baseStatus.length() > 20) {
            b2bProductClient.setBaseStatus(baseStatus.substring(0, 20));
        }

        String goodsAttr5 = b2bProductClient.getGoodsAttr5();
        if (StringUtils.isNotBlank(goodsAttr5) && goodsAttr5.length() > 36) {
            b2bProductClient.setGoodsAttr5(goodsAttr5.substring(0, 36));
        }

        String baseProId = b2bProductClient.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bProductClient.setBaseProId(baseProId.substring(0, 36));
        }

        String parentNamePath = b2bProductClient.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bProductClient.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String proId = b2bProductClient.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bProductClient.setProId(proId.substring(0, 36));
        }

        String picAll = b2bProductClient.getPicAll();
        if (StringUtils.isNotBlank(picAll) && picAll.length() > 100) {
            b2bProductClient.setPicAll(picAll.substring(0, 100));
        }

        return b2bProductClient;
    }
}
