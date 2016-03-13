package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryStorageBean;
import com.ndlan.g2.b2b.dao.B2bInventoryStorageDao;

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
@Component("b2bInventoryStorageAtomService")
public class B2bInventoryStorageAtomServiceImpl  extends BaseService<B2bInventoryStorageDao, 
	B2bInventoryStorageBean>      implements B2bInventoryStorageAtomService {

    @Resource(name="b2bInventoryStorageDao")
    protected B2bInventoryStorageDao b2bInventoryStorageDao;

    @Override
    public int saveB2bInventoryStorageBean(B2bInventoryStorageBean b2bInventoryStorage) {
        trimStringValue(b2bInventoryStorage);
        return b2bInventoryStorageDao.insertSelective(b2bInventoryStorage);
    }

    @Override
    public int saveAndGetId(B2bInventoryStorageBean b2bInventoryStorage) {
        trimStringValue(b2bInventoryStorage);
        return b2bInventoryStorageDao.insertSelectiveAndGetId(b2bInventoryStorage);
    }

    @Override
    public int update(B2bInventoryStorageBean b2bInventoryStorage) {
        trimStringValue(b2bInventoryStorage);
        return b2bInventoryStorageDao.updateByPrimaryKeySelective(b2bInventoryStorage);
    }

    @Override
    public int saveOrUpdateB2bInventoryStorageBean(B2bInventoryStorageBean b2bInventoryStorage) {
        if (null == b2bInventoryStorage.getInvStorageId() ||
		"" == b2bInventoryStorage.getInvStorageId()) {
            return saveB2bInventoryStorageBean(b2bInventoryStorage);
        } else {
            return update(b2bInventoryStorage);
        }
    }

    @Override
    public B2bInventoryStorageBean getB2bInventoryStorageBean(String invStorageId) {
        return b2bInventoryStorageDao.selectByPrimaryKey(invStorageId);
    }

    @Override
    public List<B2bInventoryStorageBean> getAll() {
        return b2bInventoryStorageDao.selectAll();
    }

    @Override
    public void delete(String invStorageId) {
         b2bInventoryStorageDao.deleteByPrimaryKey(invStorageId);
    }

    public List<B2bInventoryStorageBean> queryB2bInventoryStorageBean
	(B2bInventoryStorageBean b2bInventoryStorage, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bInventoryStorage);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bInventoryStorageDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bInventoryStorageDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bInventoryStorageDao.update(sql, args);
    }

    public List<B2bInventoryStorageBean> queryB2bInventoryStorageBean
	(B2bInventoryStorageBean b2bInventoryStorage){
	SQLParam sqlParam=getWhereSQL(b2bInventoryStorage);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bInventoryStorageDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bInventoryStorageBean b2bInventoryStorage) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String invHeadId = b2bInventoryStorage.getInvHeadId();
        if (StringUtils.isNotBlank(invHeadId) ) {
           sqlBuffer.append("invHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getInvHeadId());
        }
      
            
        String storageQty = b2bInventoryStorage.getStorageQty();
        if (StringUtils.isNotBlank(storageQty) ) {
           sqlBuffer.append("storageQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getStorageQty());
        }
      
            
        String supplierId = b2bInventoryStorage.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSupplierId());
        }
      
            
	  = b2bInventoryStorage.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.get());
        } 
      
            
        String seriesId = b2bInventoryStorage.getSeriesId();
        if (StringUtils.isNotBlank(seriesId) ) {
           sqlBuffer.append("seriesId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSeriesId());
        }
      
            
        String orderHeadId = b2bInventoryStorage.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) ) {
           sqlBuffer.append("orderHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getOrderHeadId());
        }
      
            
        String proId = b2bInventoryStorage.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getProId());
        }
      
            
        String orderPkgId = b2bInventoryStorage.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getOrderPkgId());
        }
      
            
        String size = b2bInventoryStorage.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSize());
        }
      
            
        String parentIdPath = b2bInventoryStorage.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getParentIdPath());
        }
      
            
	  = b2bInventoryStorage.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.get());
        } 
      
            
        String categoryName = b2bInventoryStorage.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getCategoryName());
        }
      
            
        String specsName = b2bInventoryStorage.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSpecsName());
        }
      
            
        String orderPkgCode = b2bInventoryStorage.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getOrderPkgCode());
        }
      
            
        String volume = b2bInventoryStorage.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getVolume());
        }
      
            
        String specsId = b2bInventoryStorage.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSpecsId());
        }
      
            
	  = b2bInventoryStorage.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.get());
        } 
      
            
        String orderLineId = b2bInventoryStorage.getOrderLineId();
        if (StringUtils.isNotBlank(orderLineId) ) {
           sqlBuffer.append("orderLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getOrderLineId());
        }
      
            
        String pic = b2bInventoryStorage.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getPic());
        }
      
            
        String seriesName = b2bInventoryStorage.getSeriesName();
        if (StringUtils.isNotBlank(seriesName) ) {
           sqlBuffer.append("seriesName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSeriesName());
        }
      
            
        String remark = b2bInventoryStorage.getRemark();
        if (StringUtils.isNotBlank(remark) ) {
           sqlBuffer.append("remark=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getRemark());
        }
      
            
        String baseProId = b2bInventoryStorage.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getBaseProId());
        }
      
            
        String proDesc = b2bInventoryStorage.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getProDesc());
        }
      
            
        String storageUser = b2bInventoryStorage.getStorageUser();
        if (StringUtils.isNotBlank(storageUser) ) {
           sqlBuffer.append("storageUser=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getStorageUser());
        }
      
            
	  = b2bInventoryStorage.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.get());
        } 
      
            
        String invStorageId = b2bInventoryStorage.getInvStorageId();
        if (StringUtils.isNotBlank(invStorageId) ) {
           sqlBuffer.append("invStorageId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getInvStorageId());
        }
      
            
	Date storageDate = b2bInventoryStorage.getStorageDate();
	if (storageDate!=null  ) {
           sqlBuffer.append("storageDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getStorageDate());
        } 
      
            
        String barCode = b2bInventoryStorage.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getBarCode());
        }
      
            
        String proCode = b2bInventoryStorage.getProCode();
        if (StringUtils.isNotBlank(proCode) ) {
           sqlBuffer.append("proCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getProCode());
        }
      
            
        String source = b2bInventoryStorage.getSource();
        if (StringUtils.isNotBlank(source) ) {
           sqlBuffer.append("source=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSource());
        }
      
            
        String proColorNo = b2bInventoryStorage.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getProColorNo());
        }
      
            
        String baseProNo = b2bInventoryStorage.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getBaseProNo());
        }
      
            
        String proName = b2bInventoryStorage.getProName();
        if (StringUtils.isNotBlank(proName) ) {
           sqlBuffer.append("proName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getProName());
        }
      
            
        String parentNamePath = b2bInventoryStorage.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getParentNamePath());
        }
      
            
        String categoryId = b2bInventoryStorage.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getCategoryId());
        }
      
            
        String supplierName = b2bInventoryStorage.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getSupplierName());
        }
      
            
        String storagePrice = b2bInventoryStorage.getStoragePrice();
        if (StringUtils.isNotBlank(storagePrice) ) {
           sqlBuffer.append("storagePrice=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getStoragePrice());
        }
      
            
        String orderDetailNo = b2bInventoryStorage.getOrderDetailNo();
        if (StringUtils.isNotBlank(orderDetailNo) ) {
           sqlBuffer.append("orderDetailNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryStorage.getOrderDetailNo());
        }
      
            
        String capacity = b2bInventoryStorage.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    
	     param.add(b2bInventoryStorage.getCapacity());
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
    public B2bInventoryStorageBean trimStringValue(B2bInventoryStorageBean b2bInventoryStorage) {
        String invHeadId = b2bInventoryStorage.getInvHeadId();
        if (StringUtils.isNotBlank(invHeadId) && invHeadId.length() > 36) {
            b2bInventoryStorage.setInvHeadId(invHeadId.substring(0, 36));
        }

        String storageQty = b2bInventoryStorage.getStorageQty();
        if (StringUtils.isNotBlank(storageQty) && storageQty.length() > 100) {
            b2bInventoryStorage.setStorageQty(storageQty.substring(0, 100));
        }

        String supplierId = b2bInventoryStorage.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bInventoryStorage.setSupplierId(supplierId.substring(0, 36));
        }

        String seriesId = b2bInventoryStorage.getSeriesId();
        if (StringUtils.isNotBlank(seriesId) && seriesId.length() > 36) {
            b2bInventoryStorage.setSeriesId(seriesId.substring(0, 36));
        }

        String orderHeadId = b2bInventoryStorage.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) && orderHeadId.length() > 36) {
            b2bInventoryStorage.setOrderHeadId(orderHeadId.substring(0, 36));
        }

        String proId = b2bInventoryStorage.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bInventoryStorage.setProId(proId.substring(0, 36));
        }

        String orderPkgId = b2bInventoryStorage.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bInventoryStorage.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String size = b2bInventoryStorage.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bInventoryStorage.setSize(size.substring(0, 36));
        }

        String parentIdPath = b2bInventoryStorage.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 255) {
            b2bInventoryStorage.setParentIdPath(parentIdPath.substring(0, 255));
        }

        String categoryName = b2bInventoryStorage.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bInventoryStorage.setCategoryName(categoryName.substring(0, 255));
        }

        String specsName = b2bInventoryStorage.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bInventoryStorage.setSpecsName(specsName.substring(0, 255));
        }

        String orderPkgCode = b2bInventoryStorage.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bInventoryStorage.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String volume = b2bInventoryStorage.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bInventoryStorage.setVolume(volume.substring(0, 10));
        }

        String specsId = b2bInventoryStorage.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bInventoryStorage.setSpecsId(specsId.substring(0, 36));
        }

        String orderLineId = b2bInventoryStorage.getOrderLineId();
        if (StringUtils.isNotBlank(orderLineId) && orderLineId.length() > 36) {
            b2bInventoryStorage.setOrderLineId(orderLineId.substring(0, 36));
        }

        String pic = b2bInventoryStorage.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 100) {
            b2bInventoryStorage.setPic(pic.substring(0, 100));
        }

        String seriesName = b2bInventoryStorage.getSeriesName();
        if (StringUtils.isNotBlank(seriesName) && seriesName.length() > 255) {
            b2bInventoryStorage.setSeriesName(seriesName.substring(0, 255));
        }

        String remark = b2bInventoryStorage.getRemark();
        if (StringUtils.isNotBlank(remark) && remark.length() > 255) {
            b2bInventoryStorage.setRemark(remark.substring(0, 255));
        }

        String baseProId = b2bInventoryStorage.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bInventoryStorage.setBaseProId(baseProId.substring(0, 36));
        }

        String proDesc = b2bInventoryStorage.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bInventoryStorage.setProDesc(proDesc.substring(0, 255));
        }

        String storageUser = b2bInventoryStorage.getStorageUser();
        if (StringUtils.isNotBlank(storageUser) && storageUser.length() > 255) {
            b2bInventoryStorage.setStorageUser(storageUser.substring(0, 255));
        }

        String invStorageId = b2bInventoryStorage.getInvStorageId();
        if (StringUtils.isNotBlank(invStorageId) && invStorageId.length() > 36) {
            b2bInventoryStorage.setInvStorageId(invStorageId.substring(0, 36));
        }

        String barCode = b2bInventoryStorage.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bInventoryStorage.setBarCode(barCode.substring(0, 36));
        }

        String proCode = b2bInventoryStorage.getProCode();
        if (StringUtils.isNotBlank(proCode) && proCode.length() > 36) {
            b2bInventoryStorage.setProCode(proCode.substring(0, 36));
        }

        String source = b2bInventoryStorage.getSource();
        if (StringUtils.isNotBlank(source) && source.length() > 36) {
            b2bInventoryStorage.setSource(source.substring(0, 36));
        }

        String proColorNo = b2bInventoryStorage.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bInventoryStorage.setProColorNo(proColorNo.substring(0, 36));
        }

        String baseProNo = b2bInventoryStorage.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bInventoryStorage.setBaseProNo(baseProNo.substring(0, 50));
        }

        String proName = b2bInventoryStorage.getProName();
        if (StringUtils.isNotBlank(proName) && proName.length() > 255) {
            b2bInventoryStorage.setProName(proName.substring(0, 255));
        }

        String parentNamePath = b2bInventoryStorage.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bInventoryStorage.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String categoryId = b2bInventoryStorage.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bInventoryStorage.setCategoryId(categoryId.substring(0, 36));
        }

        String supplierName = b2bInventoryStorage.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bInventoryStorage.setSupplierName(supplierName.substring(0, 255));
        }

        String storagePrice = b2bInventoryStorage.getStoragePrice();
        if (StringUtils.isNotBlank(storagePrice) && storagePrice.length() > 100) {
            b2bInventoryStorage.setStoragePrice(storagePrice.substring(0, 100));
        }

        String orderDetailNo = b2bInventoryStorage.getOrderDetailNo();
        if (StringUtils.isNotBlank(orderDetailNo) && orderDetailNo.length() > 36) {
            b2bInventoryStorage.setOrderDetailNo(orderDetailNo.substring(0, 36));
        }

        String capacity = b2bInventoryStorage.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 100) {
            b2bInventoryStorage.setCapacity(capacity.substring(0, 100));
        }

        return b2bInventoryStorage;
    }
}
