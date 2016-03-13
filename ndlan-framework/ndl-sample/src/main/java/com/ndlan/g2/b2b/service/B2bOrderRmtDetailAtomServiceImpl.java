package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderRmtDetailBean;
import com.ndlan.g2.b2b.dao.B2bOrderRmtDetailDao;

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
@Component("b2bOrderRmtDetailAtomService")
public class B2bOrderRmtDetailAtomServiceImpl  extends BaseService<B2bOrderRmtDetailDao, 
	B2bOrderRmtDetailBean>      implements B2bOrderRmtDetailAtomService {

    @Resource(name="b2bOrderRmtDetailDao")
    protected B2bOrderRmtDetailDao b2bOrderRmtDetailDao;

    @Override
    public int saveB2bOrderRmtDetailBean(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        trimStringValue(b2bOrderRmtDetail);
        return b2bOrderRmtDetailDao.insertSelective(b2bOrderRmtDetail);
    }

    @Override
    public int saveAndGetId(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        trimStringValue(b2bOrderRmtDetail);
        return b2bOrderRmtDetailDao.insertSelectiveAndGetId(b2bOrderRmtDetail);
    }

    @Override
    public int update(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        trimStringValue(b2bOrderRmtDetail);
        return b2bOrderRmtDetailDao.updateByPrimaryKeySelective(b2bOrderRmtDetail);
    }

    @Override
    public int saveOrUpdateB2bOrderRmtDetailBean(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        if (null == b2bOrderRmtDetail.getOrderRmtDetailId() ||
		"" == b2bOrderRmtDetail.getOrderRmtDetailId()) {
            return saveB2bOrderRmtDetailBean(b2bOrderRmtDetail);
        } else {
            return update(b2bOrderRmtDetail);
        }
    }

    @Override
    public B2bOrderRmtDetailBean getB2bOrderRmtDetailBean(String orderRmtDetailId) {
        return b2bOrderRmtDetailDao.selectByPrimaryKey(orderRmtDetailId);
    }

    @Override
    public List<B2bOrderRmtDetailBean> getAll() {
        return b2bOrderRmtDetailDao.selectAll();
    }

    @Override
    public void delete(String orderRmtDetailId) {
         b2bOrderRmtDetailDao.deleteByPrimaryKey(orderRmtDetailId);
    }

    public List<B2bOrderRmtDetailBean> queryB2bOrderRmtDetailBean
	(B2bOrderRmtDetailBean b2bOrderRmtDetail, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrderRmtDetail);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderRmtDetailDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderRmtDetailDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderRmtDetailDao.update(sql, args);
    }

    public List<B2bOrderRmtDetailBean> queryB2bOrderRmtDetailBean
	(B2bOrderRmtDetailBean b2bOrderRmtDetail){
	SQLParam sqlParam=getWhereSQL(b2bOrderRmtDetail);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderRmtDetailDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	  = b2bOrderRmtDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.get());
        } 
      
            
        String categoryName = b2bOrderRmtDetail.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getCategoryName());
        }
      
            
        String quantity = b2bOrderRmtDetail.getQuantity();
        if (StringUtils.isNotBlank(quantity) ) {
           sqlBuffer.append("quantity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getQuantity());
        }
      
            
        String proColorNo = b2bOrderRmtDetail.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getProColorNo());
        }
      
            
        String remake = b2bOrderRmtDetail.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getRemake());
        }
      
            
        String volume = b2bOrderRmtDetail.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getVolume());
        }
      
            
        String parentNamePath = b2bOrderRmtDetail.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getParentNamePath());
        }
      
            
        String customerId = b2bOrderRmtDetail.getCustomerId();
        if (StringUtils.isNotBlank(customerId) ) {
           sqlBuffer.append("customerId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getCustomerId());
        }
      
            
        String proDesc = b2bOrderRmtDetail.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getProDesc());
        }
      
            
        String applDesc = b2bOrderRmtDetail.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getApplDesc());
        }
      
            
        String restName = b2bOrderRmtDetail.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getRestName());
        }
      
            
        String customerName = b2bOrderRmtDetail.getCustomerName();
        if (StringUtils.isNotBlank(customerName) ) {
           sqlBuffer.append("customerName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getCustomerName());
        }
      
            
        String orderRmtDetailCode = b2bOrderRmtDetail.getOrderRmtDetailCode();
        if (StringUtils.isNotBlank(orderRmtDetailCode) ) {
           sqlBuffer.append("orderRmtDetailCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderRmtDetailCode());
        }
      
            
        String orderDetailId = b2bOrderRmtDetail.getOrderDetailId();
        if (StringUtils.isNotBlank(orderDetailId) ) {
           sqlBuffer.append("orderDetailId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderDetailId());
        }
      
            
	  = b2bOrderRmtDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.get());
        } 
      
            
        String orderDetailNo = b2bOrderRmtDetail.getOrderDetailNo();
        if (StringUtils.isNotBlank(orderDetailNo) ) {
           sqlBuffer.append("orderDetailNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderDetailNo());
        }
      
            
        String pic = b2bOrderRmtDetail.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getPic());
        }
      
            
        String orderRmtDetailId = b2bOrderRmtDetail.getOrderRmtDetailId();
        if (StringUtils.isNotBlank(orderRmtDetailId) ) {
           sqlBuffer.append("orderRmtDetailId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderRmtDetailId());
        }
      
            
        String baseProId = b2bOrderRmtDetail.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getBaseProId());
        }
      
            
        String proCode = b2bOrderRmtDetail.getProCode();
        if (StringUtils.isNotBlank(proCode) ) {
           sqlBuffer.append("proCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getProCode());
        }
      
            
        String supplierName = b2bOrderRmtDetail.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSupplierName());
        }
      
            
        String baseProNo = b2bOrderRmtDetail.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getBaseProNo());
        }
      
            
        String supplierId = b2bOrderRmtDetail.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSupplierId());
        }
      
            
        String specsId = b2bOrderRmtDetail.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSpecsId());
        }
      
            
        String seriesName = b2bOrderRmtDetail.getSeriesName();
        if (StringUtils.isNotBlank(seriesName) ) {
           sqlBuffer.append("seriesName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSeriesName());
        }
      
            
        String parentIdPath = b2bOrderRmtDetail.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getParentIdPath());
        }
      
            
        String orderPkgCode = b2bOrderRmtDetail.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderPkgCode());
        }
      
            
        String orignStatus = b2bOrderRmtDetail.getOrignStatus();
        if (StringUtils.isNotBlank(orignStatus) ) {
           sqlBuffer.append("orignStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrignStatus());
        }
      
            
        String capacity = b2bOrderRmtDetail.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getCapacity());
        }
      
            
	  = b2bOrderRmtDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.get());
        } 
      
            
        String price = b2bOrderRmtDetail.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getPrice());
        }
      
            
        String proName = b2bOrderRmtDetail.getProName();
        if (StringUtils.isNotBlank(proName) ) {
           sqlBuffer.append("proName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getProName());
        }
      
            
        String restId = b2bOrderRmtDetail.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getRestId());
        }
      
            
        String orderRmtPkgId = b2bOrderRmtDetail.getOrderRmtPkgId();
        if (StringUtils.isNotBlank(orderRmtPkgId) ) {
           sqlBuffer.append("orderRmtPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderRmtPkgId());
        }
      
            
	  = b2bOrderRmtDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.get());
        } 
      
            
	  = b2bOrderRmtDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.get());
        } 
      
            
        String orderRmtHeadId = b2bOrderRmtDetail.getOrderRmtHeadId();
        if (StringUtils.isNotBlank(orderRmtHeadId) ) {
           sqlBuffer.append("orderRmtHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderRmtHeadId());
        }
      
            
        String seriesId = b2bOrderRmtDetail.getSeriesId();
        if (StringUtils.isNotBlank(seriesId) ) {
           sqlBuffer.append("seriesId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSeriesId());
        }
      
            
        String size = b2bOrderRmtDetail.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSize());
        }
      
            
        String damageSpec = b2bOrderRmtDetail.getDamageSpec();
        if (StringUtils.isNotBlank(damageSpec) ) {
           sqlBuffer.append("damageSpec=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getDamageSpec());
        }
      
            
        String orderPkgId = b2bOrderRmtDetail.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderPkgId());
        }
      
            
        String barCode = b2bOrderRmtDetail.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getBarCode());
        }
      
            
        String specsName = b2bOrderRmtDetail.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getSpecsName());
        }
      
            
        String orderHeadId = b2bOrderRmtDetail.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) ) {
           sqlBuffer.append("orderHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getOrderHeadId());
        }
      
            
        String storageStatus = b2bOrderRmtDetail.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) ) {
           sqlBuffer.append("storageStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderRmtDetail.getStorageStatus());
        }
      
            
        String categoryId = b2bOrderRmtDetail.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    
	     param.add(b2bOrderRmtDetail.getCategoryId());
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
    public B2bOrderRmtDetailBean trimStringValue(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        String categoryName = b2bOrderRmtDetail.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bOrderRmtDetail.setCategoryName(categoryName.substring(0, 255));
        }

        String quantity = b2bOrderRmtDetail.getQuantity();
        if (StringUtils.isNotBlank(quantity) && quantity.length() > 100) {
            b2bOrderRmtDetail.setQuantity(quantity.substring(0, 100));
        }

        String proColorNo = b2bOrderRmtDetail.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bOrderRmtDetail.setProColorNo(proColorNo.substring(0, 36));
        }

        String remake = b2bOrderRmtDetail.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bOrderRmtDetail.setRemake(remake.substring(0, 255));
        }

        String volume = b2bOrderRmtDetail.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bOrderRmtDetail.setVolume(volume.substring(0, 10));
        }

        String parentNamePath = b2bOrderRmtDetail.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bOrderRmtDetail.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String customerId = b2bOrderRmtDetail.getCustomerId();
        if (StringUtils.isNotBlank(customerId) && customerId.length() > 36) {
            b2bOrderRmtDetail.setCustomerId(customerId.substring(0, 36));
        }

        String proDesc = b2bOrderRmtDetail.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bOrderRmtDetail.setProDesc(proDesc.substring(0, 255));
        }

        String applDesc = b2bOrderRmtDetail.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bOrderRmtDetail.setApplDesc(applDesc.substring(0, 255));
        }

        String restName = b2bOrderRmtDetail.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bOrderRmtDetail.setRestName(restName.substring(0, 255));
        }

        String customerName = b2bOrderRmtDetail.getCustomerName();
        if (StringUtils.isNotBlank(customerName) && customerName.length() > 255) {
            b2bOrderRmtDetail.setCustomerName(customerName.substring(0, 255));
        }

        String orderRmtDetailCode = b2bOrderRmtDetail.getOrderRmtDetailCode();
        if (StringUtils.isNotBlank(orderRmtDetailCode) && orderRmtDetailCode.length() > 36) {
            b2bOrderRmtDetail.setOrderRmtDetailCode(orderRmtDetailCode.substring(0, 36));
        }

        String orderDetailId = b2bOrderRmtDetail.getOrderDetailId();
        if (StringUtils.isNotBlank(orderDetailId) && orderDetailId.length() > 36) {
            b2bOrderRmtDetail.setOrderDetailId(orderDetailId.substring(0, 36));
        }

        String orderDetailNo = b2bOrderRmtDetail.getOrderDetailNo();
        if (StringUtils.isNotBlank(orderDetailNo) && orderDetailNo.length() > 36) {
            b2bOrderRmtDetail.setOrderDetailNo(orderDetailNo.substring(0, 36));
        }

        String pic = b2bOrderRmtDetail.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 100) {
            b2bOrderRmtDetail.setPic(pic.substring(0, 100));
        }

        String orderRmtDetailId = b2bOrderRmtDetail.getOrderRmtDetailId();
        if (StringUtils.isNotBlank(orderRmtDetailId) && orderRmtDetailId.length() > 36) {
            b2bOrderRmtDetail.setOrderRmtDetailId(orderRmtDetailId.substring(0, 36));
        }

        String baseProId = b2bOrderRmtDetail.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bOrderRmtDetail.setBaseProId(baseProId.substring(0, 36));
        }

        String proCode = b2bOrderRmtDetail.getProCode();
        if (StringUtils.isNotBlank(proCode) && proCode.length() > 36) {
            b2bOrderRmtDetail.setProCode(proCode.substring(0, 36));
        }

        String supplierName = b2bOrderRmtDetail.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bOrderRmtDetail.setSupplierName(supplierName.substring(0, 255));
        }

        String baseProNo = b2bOrderRmtDetail.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bOrderRmtDetail.setBaseProNo(baseProNo.substring(0, 50));
        }

        String supplierId = b2bOrderRmtDetail.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bOrderRmtDetail.setSupplierId(supplierId.substring(0, 36));
        }

        String specsId = b2bOrderRmtDetail.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bOrderRmtDetail.setSpecsId(specsId.substring(0, 36));
        }

        String seriesName = b2bOrderRmtDetail.getSeriesName();
        if (StringUtils.isNotBlank(seriesName) && seriesName.length() > 255) {
            b2bOrderRmtDetail.setSeriesName(seriesName.substring(0, 255));
        }

        String parentIdPath = b2bOrderRmtDetail.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 36) {
            b2bOrderRmtDetail.setParentIdPath(parentIdPath.substring(0, 36));
        }

        String orderPkgCode = b2bOrderRmtDetail.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bOrderRmtDetail.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String orignStatus = b2bOrderRmtDetail.getOrignStatus();
        if (StringUtils.isNotBlank(orignStatus) && orignStatus.length() > 2) {
            b2bOrderRmtDetail.setOrignStatus(orignStatus.substring(0, 2));
        }

        String capacity = b2bOrderRmtDetail.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 100) {
            b2bOrderRmtDetail.setCapacity(capacity.substring(0, 100));
        }

        String price = b2bOrderRmtDetail.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bOrderRmtDetail.setPrice(price.substring(0, 100));
        }

        String proName = b2bOrderRmtDetail.getProName();
        if (StringUtils.isNotBlank(proName) && proName.length() > 255) {
            b2bOrderRmtDetail.setProName(proName.substring(0, 255));
        }

        String restId = b2bOrderRmtDetail.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bOrderRmtDetail.setRestId(restId.substring(0, 36));
        }

        String orderRmtPkgId = b2bOrderRmtDetail.getOrderRmtPkgId();
        if (StringUtils.isNotBlank(orderRmtPkgId) && orderRmtPkgId.length() > 36) {
            b2bOrderRmtDetail.setOrderRmtPkgId(orderRmtPkgId.substring(0, 36));
        }

        String orderRmtHeadId = b2bOrderRmtDetail.getOrderRmtHeadId();
        if (StringUtils.isNotBlank(orderRmtHeadId) && orderRmtHeadId.length() > 36) {
            b2bOrderRmtDetail.setOrderRmtHeadId(orderRmtHeadId.substring(0, 36));
        }

        String seriesId = b2bOrderRmtDetail.getSeriesId();
        if (StringUtils.isNotBlank(seriesId) && seriesId.length() > 36) {
            b2bOrderRmtDetail.setSeriesId(seriesId.substring(0, 36));
        }

        String size = b2bOrderRmtDetail.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bOrderRmtDetail.setSize(size.substring(0, 36));
        }

        String damageSpec = b2bOrderRmtDetail.getDamageSpec();
        if (StringUtils.isNotBlank(damageSpec) && damageSpec.length() > 255) {
            b2bOrderRmtDetail.setDamageSpec(damageSpec.substring(0, 255));
        }

        String orderPkgId = b2bOrderRmtDetail.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bOrderRmtDetail.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String barCode = b2bOrderRmtDetail.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bOrderRmtDetail.setBarCode(barCode.substring(0, 36));
        }

        String specsName = b2bOrderRmtDetail.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bOrderRmtDetail.setSpecsName(specsName.substring(0, 255));
        }

        String orderHeadId = b2bOrderRmtDetail.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) && orderHeadId.length() > 36) {
            b2bOrderRmtDetail.setOrderHeadId(orderHeadId.substring(0, 36));
        }

        String storageStatus = b2bOrderRmtDetail.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) && storageStatus.length() > 20) {
            b2bOrderRmtDetail.setStorageStatus(storageStatus.substring(0, 20));
        }

        String categoryId = b2bOrderRmtDetail.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bOrderRmtDetail.setCategoryId(categoryId.substring(0, 36));
        }

        return b2bOrderRmtDetail;
    }
}
