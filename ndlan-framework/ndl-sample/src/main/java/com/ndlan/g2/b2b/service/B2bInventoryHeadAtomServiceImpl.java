package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryHeadBean;
import com.ndlan.g2.b2b.dao.B2bInventoryHeadDao;

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
@Component("b2bInventoryHeadAtomService")
public class B2bInventoryHeadAtomServiceImpl  extends BaseService<B2bInventoryHeadDao, 
	B2bInventoryHeadBean>      implements B2bInventoryHeadAtomService {

    @Resource(name="b2bInventoryHeadDao")
    protected B2bInventoryHeadDao b2bInventoryHeadDao;

    @Override
    public int saveB2bInventoryHeadBean(B2bInventoryHeadBean b2bInventoryHead) {
        trimStringValue(b2bInventoryHead);
        return b2bInventoryHeadDao.insertSelective(b2bInventoryHead);
    }

    @Override
    public int saveAndGetId(B2bInventoryHeadBean b2bInventoryHead) {
        trimStringValue(b2bInventoryHead);
        return b2bInventoryHeadDao.insertSelectiveAndGetId(b2bInventoryHead);
    }

    @Override
    public int update(B2bInventoryHeadBean b2bInventoryHead) {
        trimStringValue(b2bInventoryHead);
        return b2bInventoryHeadDao.updateByPrimaryKeySelective(b2bInventoryHead);
    }

    @Override
    public int saveOrUpdateB2bInventoryHeadBean(B2bInventoryHeadBean b2bInventoryHead) {
        if (null == b2bInventoryHead.getInvHeadId() ||
		"" == b2bInventoryHead.getInvHeadId()) {
            return saveB2bInventoryHeadBean(b2bInventoryHead);
        } else {
            return update(b2bInventoryHead);
        }
    }

    @Override
    public B2bInventoryHeadBean getB2bInventoryHeadBean(String invHeadId) {
        return b2bInventoryHeadDao.selectByPrimaryKey(invHeadId);
    }

    @Override
    public List<B2bInventoryHeadBean> getAll() {
        return b2bInventoryHeadDao.selectAll();
    }

    @Override
    public void delete(String invHeadId) {
         b2bInventoryHeadDao.deleteByPrimaryKey(invHeadId);
    }

    public List<B2bInventoryHeadBean> queryB2bInventoryHeadBean
	(B2bInventoryHeadBean b2bInventoryHead, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bInventoryHead);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bInventoryHeadDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bInventoryHeadDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bInventoryHeadDao.update(sql, args);
    }

    public List<B2bInventoryHeadBean> queryB2bInventoryHeadBean
	(B2bInventoryHeadBean b2bInventoryHead){
	SQLParam sqlParam=getWhereSQL(b2bInventoryHead);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bInventoryHeadDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bInventoryHeadBean b2bInventoryHead) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	  = b2bInventoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.get());
        } 
      
            
        String seriesId = b2bInventoryHead.getSeriesId();
        if (StringUtils.isNotBlank(seriesId) ) {
           sqlBuffer.append("seriesId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSeriesId());
        }
      
            
        String barCode = b2bInventoryHead.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getBarCode());
        }
      
            
        String seriesName = b2bInventoryHead.getSeriesName();
        if (StringUtils.isNotBlank(seriesName) ) {
           sqlBuffer.append("seriesName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSeriesName());
        }
      
            
        String baseProNo = b2bInventoryHead.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getBaseProNo());
        }
      
            
        String volume = b2bInventoryHead.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getVolume());
        }
      
            
        String categoryName = b2bInventoryHead.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getCategoryName());
        }
      
            
        String realQty = b2bInventoryHead.getRealQty();
        if (StringUtils.isNotBlank(realQty) ) {
           sqlBuffer.append("realQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getRealQty());
        }
      
            
	  = b2bInventoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.get());
        } 
      
            
        String invHeadId = b2bInventoryHead.getInvHeadId();
        if (StringUtils.isNotBlank(invHeadId) ) {
           sqlBuffer.append("invHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getInvHeadId());
        }
      
            
        String supplierType = b2bInventoryHead.getSupplierType();
        if (StringUtils.isNotBlank(supplierType) ) {
           sqlBuffer.append("supplierType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSupplierType());
        }
      
            
        String availableQty = b2bInventoryHead.getAvailableQty();
        if (StringUtils.isNotBlank(availableQty) ) {
           sqlBuffer.append("availableQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getAvailableQty());
        }
      
            
        String supplierId = b2bInventoryHead.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSupplierId());
        }
      
            
        String applDesc = b2bInventoryHead.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getApplDesc());
        }
      
            
        String capacity = b2bInventoryHead.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getCapacity());
        }
      
            
        String size = b2bInventoryHead.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSize());
        }
      
            
	  = b2bInventoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.get());
        } 
      
            
        String baseProId = b2bInventoryHead.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getBaseProId());
        }
      
            
        String supplierName = b2bInventoryHead.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSupplierName());
        }
      
            
        String proColorNo = b2bInventoryHead.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getProColorNo());
        }
      
            
        String categoryId = b2bInventoryHead.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getCategoryId());
        }
      
            
        String capacityVolume = b2bInventoryHead.getCapacityVolume();
        if (StringUtils.isNotBlank(capacityVolume) ) {
           sqlBuffer.append("capacityVolume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getCapacityVolume());
        }
      
            
        String totalQty = b2bInventoryHead.getTotalQty();
        if (StringUtils.isNotBlank(totalQty) ) {
           sqlBuffer.append("totalQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getTotalQty());
        }
      
            
        String specsId = b2bInventoryHead.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSpecsId());
        }
      
            
        String proDesc = b2bInventoryHead.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getProDesc());
        }
      
            
	  = b2bInventoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.get());
        } 
      
            
        String safetyStock = b2bInventoryHead.getSafetyStock();
        if (StringUtils.isNotBlank(safetyStock) ) {
           sqlBuffer.append("safetyStock=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSafetyStock());
        }
      
            
        String deliveryQty = b2bInventoryHead.getDeliveryQty();
        if (StringUtils.isNotBlank(deliveryQty) ) {
           sqlBuffer.append("deliveryQty=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getDeliveryQty());
        }
      
            
        String restId = b2bInventoryHead.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getRestId());
        }
      
            
        String specsName = b2bInventoryHead.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.getSpecsName());
        }
      
            
	  = b2bInventoryHead.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bInventoryHead.get());
        } 
      
            
        String proName = b2bInventoryHead.getProName();
        if (StringUtils.isNotBlank(proName) ) {
           sqlBuffer.append("proName=?");
	    
	     param.add(b2bInventoryHead.getProName());
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
    public B2bInventoryHeadBean trimStringValue(B2bInventoryHeadBean b2bInventoryHead) {
        String seriesId = b2bInventoryHead.getSeriesId();
        if (StringUtils.isNotBlank(seriesId) && seriesId.length() > 36) {
            b2bInventoryHead.setSeriesId(seriesId.substring(0, 36));
        }

        String barCode = b2bInventoryHead.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bInventoryHead.setBarCode(barCode.substring(0, 36));
        }

        String seriesName = b2bInventoryHead.getSeriesName();
        if (StringUtils.isNotBlank(seriesName) && seriesName.length() > 255) {
            b2bInventoryHead.setSeriesName(seriesName.substring(0, 255));
        }

        String baseProNo = b2bInventoryHead.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bInventoryHead.setBaseProNo(baseProNo.substring(0, 50));
        }

        String volume = b2bInventoryHead.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bInventoryHead.setVolume(volume.substring(0, 10));
        }

        String categoryName = b2bInventoryHead.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bInventoryHead.setCategoryName(categoryName.substring(0, 255));
        }

        String realQty = b2bInventoryHead.getRealQty();
        if (StringUtils.isNotBlank(realQty) && realQty.length() > 100) {
            b2bInventoryHead.setRealQty(realQty.substring(0, 100));
        }

        String invHeadId = b2bInventoryHead.getInvHeadId();
        if (StringUtils.isNotBlank(invHeadId) && invHeadId.length() > 36) {
            b2bInventoryHead.setInvHeadId(invHeadId.substring(0, 36));
        }

        String supplierType = b2bInventoryHead.getSupplierType();
        if (StringUtils.isNotBlank(supplierType) && supplierType.length() > 4) {
            b2bInventoryHead.setSupplierType(supplierType.substring(0, 4));
        }

        String availableQty = b2bInventoryHead.getAvailableQty();
        if (StringUtils.isNotBlank(availableQty) && availableQty.length() > 100) {
            b2bInventoryHead.setAvailableQty(availableQty.substring(0, 100));
        }

        String supplierId = b2bInventoryHead.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bInventoryHead.setSupplierId(supplierId.substring(0, 36));
        }

        String applDesc = b2bInventoryHead.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bInventoryHead.setApplDesc(applDesc.substring(0, 255));
        }

        String capacity = b2bInventoryHead.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 100) {
            b2bInventoryHead.setCapacity(capacity.substring(0, 100));
        }

        String size = b2bInventoryHead.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bInventoryHead.setSize(size.substring(0, 36));
        }

        String baseProId = b2bInventoryHead.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bInventoryHead.setBaseProId(baseProId.substring(0, 36));
        }

        String supplierName = b2bInventoryHead.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bInventoryHead.setSupplierName(supplierName.substring(0, 255));
        }

        String proColorNo = b2bInventoryHead.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bInventoryHead.setProColorNo(proColorNo.substring(0, 36));
        }

        String categoryId = b2bInventoryHead.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bInventoryHead.setCategoryId(categoryId.substring(0, 36));
        }

        String capacityVolume = b2bInventoryHead.getCapacityVolume();
        if (StringUtils.isNotBlank(capacityVolume) && capacityVolume.length() > 10) {
            b2bInventoryHead.setCapacityVolume(capacityVolume.substring(0, 10));
        }

        String totalQty = b2bInventoryHead.getTotalQty();
        if (StringUtils.isNotBlank(totalQty) && totalQty.length() > 100) {
            b2bInventoryHead.setTotalQty(totalQty.substring(0, 100));
        }

        String specsId = b2bInventoryHead.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bInventoryHead.setSpecsId(specsId.substring(0, 36));
        }

        String proDesc = b2bInventoryHead.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bInventoryHead.setProDesc(proDesc.substring(0, 255));
        }

        String safetyStock = b2bInventoryHead.getSafetyStock();
        if (StringUtils.isNotBlank(safetyStock) && safetyStock.length() > 100) {
            b2bInventoryHead.setSafetyStock(safetyStock.substring(0, 100));
        }

        String deliveryQty = b2bInventoryHead.getDeliveryQty();
        if (StringUtils.isNotBlank(deliveryQty) && deliveryQty.length() > 100) {
            b2bInventoryHead.setDeliveryQty(deliveryQty.substring(0, 100));
        }

        String restId = b2bInventoryHead.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bInventoryHead.setRestId(restId.substring(0, 36));
        }

        String specsName = b2bInventoryHead.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bInventoryHead.setSpecsName(specsName.substring(0, 255));
        }

        String proName = b2bInventoryHead.getProName();
        if (StringUtils.isNotBlank(proName) && proName.length() > 255) {
            b2bInventoryHead.setProName(proName.substring(0, 255));
        }

        return b2bInventoryHead;
    }
}
