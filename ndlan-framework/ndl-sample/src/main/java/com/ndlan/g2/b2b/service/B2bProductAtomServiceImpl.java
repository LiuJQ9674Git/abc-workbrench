package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductBean;
import com.ndlan.g2.b2b.dao.B2bProductDao;

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
@Component("b2bProductAtomService")
public class B2bProductAtomServiceImpl  extends BaseService<B2bProductDao, 
	B2bProductBean>      implements B2bProductAtomService {

    @Resource(name="b2bProductDao")
    protected B2bProductDao b2bProductDao;

    @Override
    public int saveB2bProductBean(B2bProductBean b2bProduct) {
        trimStringValue(b2bProduct);
        return b2bProductDao.insertSelective(b2bProduct);
    }

    @Override
    public int saveAndGetId(B2bProductBean b2bProduct) {
        trimStringValue(b2bProduct);
        return b2bProductDao.insertSelectiveAndGetId(b2bProduct);
    }

    @Override
    public int update(B2bProductBean b2bProduct) {
        trimStringValue(b2bProduct);
        return b2bProductDao.updateByPrimaryKeySelective(b2bProduct);
    }

    @Override
    public int saveOrUpdateB2bProductBean(B2bProductBean b2bProduct) {
        if (null == b2bProduct.getProId() ||
		"" == b2bProduct.getProId()) {
            return saveB2bProductBean(b2bProduct);
        } else {
            return update(b2bProduct);
        }
    }

    @Override
    public B2bProductBean getB2bProductBean(String proId) {
        return b2bProductDao.selectByPrimaryKey(proId);
    }

    @Override
    public List<B2bProductBean> getAll() {
        return b2bProductDao.selectAll();
    }

    @Override
    public void delete(String proId) {
         b2bProductDao.deleteByPrimaryKey(proId);
    }

    public List<B2bProductBean> queryB2bProductBean
	(B2bProductBean b2bProduct, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bProduct);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bProductDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bProductDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bProductDao.update(sql, args);
    }

    public List<B2bProductBean> queryB2bProductBean
	(B2bProductBean b2bProduct){
	SQLParam sqlParam=getWhereSQL(b2bProduct);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bProductDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bProductBean b2bProduct) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String size = b2bProduct.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getSize());
        }
      
            
	  = b2bProduct.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.get());
        } 
      
            
        String supplierType = b2bProduct.getSupplierType();
        if (StringUtils.isNotBlank(supplierType) ) {
           sqlBuffer.append("supplierType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getSupplierType());
        }
      
            
        String baseProNo = b2bProduct.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getBaseProNo());
        }
      
            
        String volume = b2bProduct.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getVolume());
        }
      
            
        String proId = b2bProduct.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getProId());
        }
      
            
        String picAll = b2bProduct.getPicAll();
        if (StringUtils.isNotBlank(picAll) ) {
           sqlBuffer.append("picAll=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getPicAll());
        }
      
            
        String name = b2bProduct.getName();
        if (StringUtils.isNotBlank(name) ) {
           sqlBuffer.append("name=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getName());
        }
      
            
        String spuulierName = b2bProduct.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) ) {
           sqlBuffer.append("spuulierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getSpuulierName());
        }
      
            
        String supplierId = b2bProduct.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getSupplierId());
        }
      
            
        String primeCost = b2bProduct.getPrimeCost();
        if (StringUtils.isNotBlank(primeCost) ) {
           sqlBuffer.append("primeCost=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getPrimeCost());
        }
      
            
        String proDesc = b2bProduct.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getProDesc());
        }
      
            
        String applDesc = b2bProduct.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getApplDesc());
        }
      
            
        String goodsAttr3 = b2bProduct.getGoodsAttr3();
        if (StringUtils.isNotBlank(goodsAttr3) ) {
           sqlBuffer.append("goodsAttr3=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getGoodsAttr3());
        }
      
            
        String categoryId = b2bProduct.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getCategoryId());
        }
      
            
        String parentIdPath = b2bProduct.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getParentIdPath());
        }
      
            
        String pic = b2bProduct.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getPic());
        }
      
            
	  = b2bProduct.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.get());
        } 
      
            
        String categoryName = b2bProduct.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getCategoryName());
        }
      
            
        String price = b2bProduct.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getPrice());
        }
      
            
        String quantity = b2bProduct.getQuantity();
        if (StringUtils.isNotBlank(quantity) ) {
           sqlBuffer.append("quantity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getQuantity());
        }
      
            
        String goodsAttr2 = b2bProduct.getGoodsAttr2();
        if (StringUtils.isNotBlank(goodsAttr2) ) {
           sqlBuffer.append("goodsAttr2=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getGoodsAttr2());
        }
      
            
        String capacity = b2bProduct.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getCapacity());
        }
      
            
        String goodsAttr4 = b2bProduct.getGoodsAttr4();
        if (StringUtils.isNotBlank(goodsAttr4) ) {
           sqlBuffer.append("goodsAttr4=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getGoodsAttr4());
        }
      
            
        String parentNamePath = b2bProduct.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getParentNamePath());
        }
      
            
        String baseStatus = b2bProduct.getBaseStatus();
        if (StringUtils.isNotBlank(baseStatus) ) {
           sqlBuffer.append("baseStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getBaseStatus());
        }
      
            
        String baseProId = b2bProduct.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getBaseProId());
        }
      
            
        String goodsAttr1 = b2bProduct.getGoodsAttr1();
        if (StringUtils.isNotBlank(goodsAttr1) ) {
           sqlBuffer.append("goodsAttr1=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getGoodsAttr1());
        }
      
            
        String barCode = b2bProduct.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getBarCode());
        }
      
            
        String specsId = b2bProduct.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getSpecsId());
        }
      
            
	  = b2bProduct.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.get());
        } 
      
            
        String remarks = b2bProduct.getRemarks();
        if (StringUtils.isNotBlank(remarks) ) {
           sqlBuffer.append("remarks=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getRemarks());
        }
      
            
        String restId = b2bProduct.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getRestId());
        }
      
            
        String targetClient = b2bProduct.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getTargetClient());
        }
      
            
        String goodsAttr5 = b2bProduct.getGoodsAttr5();
        if (StringUtils.isNotBlank(goodsAttr5) ) {
           sqlBuffer.append("goodsAttr5=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getGoodsAttr5());
        }
      
            
        String proColorNo = b2bProduct.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.getProColorNo());
        }
      
            
	  = b2bProduct.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.get());
        } 
      
            
	  = b2bProduct.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProduct.get());
        } 
      
            
        String specsName = b2bProduct.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    
	     param.add(b2bProduct.getSpecsName());
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
    public B2bProductBean trimStringValue(B2bProductBean b2bProduct) {
        String size = b2bProduct.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bProduct.setSize(size.substring(0, 36));
        }

        String supplierType = b2bProduct.getSupplierType();
        if (StringUtils.isNotBlank(supplierType) && supplierType.length() > 4) {
            b2bProduct.setSupplierType(supplierType.substring(0, 4));
        }

        String baseProNo = b2bProduct.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 36) {
            b2bProduct.setBaseProNo(baseProNo.substring(0, 36));
        }

        String volume = b2bProduct.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 36) {
            b2bProduct.setVolume(volume.substring(0, 36));
        }

        String proId = b2bProduct.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bProduct.setProId(proId.substring(0, 36));
        }

        String picAll = b2bProduct.getPicAll();
        if (StringUtils.isNotBlank(picAll) && picAll.length() > 100) {
            b2bProduct.setPicAll(picAll.substring(0, 100));
        }

        String name = b2bProduct.getName();
        if (StringUtils.isNotBlank(name) && name.length() > 255) {
            b2bProduct.setName(name.substring(0, 255));
        }

        String spuulierName = b2bProduct.getSpuulierName();
        if (StringUtils.isNotBlank(spuulierName) && spuulierName.length() > 255) {
            b2bProduct.setSpuulierName(spuulierName.substring(0, 255));
        }

        String supplierId = b2bProduct.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bProduct.setSupplierId(supplierId.substring(0, 36));
        }

        String primeCost = b2bProduct.getPrimeCost();
        if (StringUtils.isNotBlank(primeCost) && primeCost.length() > 100) {
            b2bProduct.setPrimeCost(primeCost.substring(0, 100));
        }

        String proDesc = b2bProduct.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bProduct.setProDesc(proDesc.substring(0, 255));
        }

        String applDesc = b2bProduct.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bProduct.setApplDesc(applDesc.substring(0, 255));
        }

        String goodsAttr3 = b2bProduct.getGoodsAttr3();
        if (StringUtils.isNotBlank(goodsAttr3) && goodsAttr3.length() > 36) {
            b2bProduct.setGoodsAttr3(goodsAttr3.substring(0, 36));
        }

        String categoryId = b2bProduct.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bProduct.setCategoryId(categoryId.substring(0, 36));
        }

        String parentIdPath = b2bProduct.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 255) {
            b2bProduct.setParentIdPath(parentIdPath.substring(0, 255));
        }

        String pic = b2bProduct.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 100) {
            b2bProduct.setPic(pic.substring(0, 100));
        }

        String categoryName = b2bProduct.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bProduct.setCategoryName(categoryName.substring(0, 255));
        }

        String price = b2bProduct.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bProduct.setPrice(price.substring(0, 100));
        }

        String quantity = b2bProduct.getQuantity();
        if (StringUtils.isNotBlank(quantity) && quantity.length() > 100) {
            b2bProduct.setQuantity(quantity.substring(0, 100));
        }

        String goodsAttr2 = b2bProduct.getGoodsAttr2();
        if (StringUtils.isNotBlank(goodsAttr2) && goodsAttr2.length() > 36) {
            b2bProduct.setGoodsAttr2(goodsAttr2.substring(0, 36));
        }

        String capacity = b2bProduct.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 11) {
            b2bProduct.setCapacity(capacity.substring(0, 11));
        }

        String goodsAttr4 = b2bProduct.getGoodsAttr4();
        if (StringUtils.isNotBlank(goodsAttr4) && goodsAttr4.length() > 36) {
            b2bProduct.setGoodsAttr4(goodsAttr4.substring(0, 36));
        }

        String parentNamePath = b2bProduct.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bProduct.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String baseStatus = b2bProduct.getBaseStatus();
        if (StringUtils.isNotBlank(baseStatus) && baseStatus.length() > 20) {
            b2bProduct.setBaseStatus(baseStatus.substring(0, 20));
        }

        String baseProId = b2bProduct.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bProduct.setBaseProId(baseProId.substring(0, 36));
        }

        String goodsAttr1 = b2bProduct.getGoodsAttr1();
        if (StringUtils.isNotBlank(goodsAttr1) && goodsAttr1.length() > 36) {
            b2bProduct.setGoodsAttr1(goodsAttr1.substring(0, 36));
        }

        String barCode = b2bProduct.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bProduct.setBarCode(barCode.substring(0, 36));
        }

        String specsId = b2bProduct.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bProduct.setSpecsId(specsId.substring(0, 36));
        }

        String remarks = b2bProduct.getRemarks();
        if (StringUtils.isNotBlank(remarks) && remarks.length() > 255) {
            b2bProduct.setRemarks(remarks.substring(0, 255));
        }

        String restId = b2bProduct.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bProduct.setRestId(restId.substring(0, 36));
        }

        String targetClient = b2bProduct.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bProduct.setTargetClient(targetClient.substring(0, 20));
        }

        String goodsAttr5 = b2bProduct.getGoodsAttr5();
        if (StringUtils.isNotBlank(goodsAttr5) && goodsAttr5.length() > 36) {
            b2bProduct.setGoodsAttr5(goodsAttr5.substring(0, 36));
        }

        String proColorNo = b2bProduct.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bProduct.setProColorNo(proColorNo.substring(0, 36));
        }

        String specsName = b2bProduct.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bProduct.setSpecsName(specsName.substring(0, 255));
        }

        return b2bProduct;
    }
}
