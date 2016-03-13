package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderItemBean;
import com.ndlan.g2.b2b.dao.B2bOrderItemDao;

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
@Component("b2bOrderItemAtomService")
public class B2bOrderItemAtomServiceImpl  extends BaseService<B2bOrderItemDao, 
	B2bOrderItemBean>      implements B2bOrderItemAtomService {

    @Resource(name="b2bOrderItemDao")
    protected B2bOrderItemDao b2bOrderItemDao;

    @Override
    public int saveB2bOrderItemBean(B2bOrderItemBean b2bOrderItem) {
        trimStringValue(b2bOrderItem);
        return b2bOrderItemDao.insertSelective(b2bOrderItem);
    }

    @Override
    public int saveAndGetId(B2bOrderItemBean b2bOrderItem) {
        trimStringValue(b2bOrderItem);
        return b2bOrderItemDao.insertSelectiveAndGetId(b2bOrderItem);
    }

    @Override
    public int update(B2bOrderItemBean b2bOrderItem) {
        trimStringValue(b2bOrderItem);
        return b2bOrderItemDao.updateByPrimaryKeySelective(b2bOrderItem);
    }

    @Override
    public int saveOrUpdateB2bOrderItemBean(B2bOrderItemBean b2bOrderItem) {
        if (null == b2bOrderItem.getBdId() ||
		"" == b2bOrderItem.getBdId()) {
            return saveB2bOrderItemBean(b2bOrderItem);
        } else {
            return update(b2bOrderItem);
        }
    }

    @Override
    public B2bOrderItemBean getB2bOrderItemBean(String bdId) {
        return b2bOrderItemDao.selectByPrimaryKey(bdId);
    }

    @Override
    public List<B2bOrderItemBean> getAll() {
        return b2bOrderItemDao.selectAll();
    }

    @Override
    public void delete(String bdId) {
         b2bOrderItemDao.deleteByPrimaryKey(bdId);
    }

    public List<B2bOrderItemBean> queryB2bOrderItemBean
	(B2bOrderItemBean b2bOrderItem, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrderItem);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderItemDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderItemDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderItemDao.update(sql, args);
    }

    public List<B2bOrderItemBean> queryB2bOrderItemBean
	(B2bOrderItemBean b2bOrderItem){
	SQLParam sqlParam=getWhereSQL(b2bOrderItem);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderItemDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderItemBean b2bOrderItem) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String price = b2bOrderItem.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getPrice());
        }
      
            
        String bId = b2bOrderItem.getBId();
        if (StringUtils.isNotBlank(bId) ) {
           sqlBuffer.append("bId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getBId());
        }
      
            
        String proDesc = b2bOrderItem.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getProDesc());
        }
      
            
        String rmtDetailId = b2bOrderItem.getRmtDetailId();
        if (StringUtils.isNotBlank(rmtDetailId) ) {
           sqlBuffer.append("rmtDetailId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getRmtDetailId());
        }
      
            
        String quantity = b2bOrderItem.getQuantity();
        if (StringUtils.isNotBlank(quantity) ) {
           sqlBuffer.append("quantity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getQuantity());
        }
      
            
        String volume = b2bOrderItem.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getVolume());
        }
      
            
        String orderPkgCode = b2bOrderItem.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getOrderPkgCode());
        }
      
            
        String numberOfBroken = b2bOrderItem.getNumberOfBroken();
        if (StringUtils.isNotBlank(numberOfBroken) ) {
           sqlBuffer.append("numberOfBroken=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getNumberOfBroken());
        }
      
            
        String supplierId = b2bOrderItem.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSupplierId());
        }
      
            
        String priceAgencyName = b2bOrderItem.getPriceAgencyName();
        if (StringUtils.isNotBlank(priceAgencyName) ) {
           sqlBuffer.append("priceAgencyName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getPriceAgencyName());
        }
      
            
	  = b2bOrderItem.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.get());
        } 
      
            
        String privilege = b2bOrderItem.getPrivilege();
        if (StringUtils.isNotBlank(privilege) ) {
           sqlBuffer.append("privilege=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getPrivilege());
        }
      
            
        String pic = b2bOrderItem.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getPic());
        }
      
            
        String isCodeless = b2bOrderItem.getIsCodeless();
        if (StringUtils.isNotBlank(isCodeless) ) {
           sqlBuffer.append("isCodeless=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getIsCodeless());
        }
      
            
	  = b2bOrderItem.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.get());
        } 
      
            
        String rmtPkgId = b2bOrderItem.getRmtPkgId();
        if (StringUtils.isNotBlank(rmtPkgId) ) {
           sqlBuffer.append("rmtPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getRmtPkgId());
        }
      
            
        String bdNo = b2bOrderItem.getBdNo();
        if (StringUtils.isNotBlank(bdNo) ) {
           sqlBuffer.append("bdNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getBdNo());
        }
      
            
        String startPointQyt = b2bOrderItem.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) ) {
           sqlBuffer.append("startPointQyt=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getStartPointQyt());
        }
      
            
        String amountPaid = b2bOrderItem.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) ) {
           sqlBuffer.append("amountPaid=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getAmountPaid());
        }
      
            
        String capacity = b2bOrderItem.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getCapacity());
        }
      
            
        String proColorNo = b2bOrderItem.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getProColorNo());
        }
      
            
        String restName = b2bOrderItem.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getRestName());
        }
      
            
        String discount = b2bOrderItem.getDiscount();
        if (StringUtils.isNotBlank(discount) ) {
           sqlBuffer.append("discount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getDiscount());
        }
      
            
        String rmtStatus = b2bOrderItem.getRmtStatus();
        if (StringUtils.isNotBlank(rmtStatus) ) {
           sqlBuffer.append("rmtStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getRmtStatus());
        }
      
            
        String baseProId = b2bOrderItem.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getBaseProId());
        }
      
            
        String parentNamePath = b2bOrderItem.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getParentNamePath());
        }
      
            
        String strategyDesc = b2bOrderItem.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) ) {
           sqlBuffer.append("strategyDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getStrategyDesc());
        }
      
            
        String size = b2bOrderItem.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSize());
        }
      
            
        String parentIdPath = b2bOrderItem.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getParentIdPath());
        }
      
            
	  = b2bOrderItem.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.get());
        } 
      
            
        String supplierName = b2bOrderItem.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSupplierName());
        }
      
            
	  = b2bOrderItem.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.get());
        } 
      
            
	  = b2bOrderItem.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.get());
        } 
      
            
        String applDesc = b2bOrderItem.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getApplDesc());
        }
      
            
        String slsPmtnId = b2bOrderItem.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) ) {
           sqlBuffer.append("slsPmtnId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSlsPmtnId());
        }
      
            
        String priceAgencyId = b2bOrderItem.getPriceAgencyId();
        if (StringUtils.isNotBlank(priceAgencyId) ) {
           sqlBuffer.append("priceAgencyId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getPriceAgencyId());
        }
      
            
        String subtotal = b2bOrderItem.getSubtotal();
        if (StringUtils.isNotBlank(subtotal) ) {
           sqlBuffer.append("subtotal=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSubtotal());
        }
      
            
        String proName = b2bOrderItem.getProName();
        if (StringUtils.isNotBlank(proName) ) {
           sqlBuffer.append("proName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getProName());
        }
      
            
        String specsId = b2bOrderItem.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSpecsId());
        }
      
            
        String targetClient = b2bOrderItem.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getTargetClient());
        }
      
            
        String orderPkgId = b2bOrderItem.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getOrderPkgId());
        }
      
            
        String proId = b2bOrderItem.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getProId());
        }
      
            
        String categoryName = b2bOrderItem.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getCategoryName());
        }
      
            
        String cartItemId = b2bOrderItem.getCartItemId();
        if (StringUtils.isNotBlank(cartItemId) ) {
           sqlBuffer.append("cartItemId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getCartItemId());
        }
      
            
        String damageBdId = b2bOrderItem.getDamageBdId();
        if (StringUtils.isNotBlank(damageBdId) ) {
           sqlBuffer.append("damageBdId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getDamageBdId());
        }
      
            
        String specsName = b2bOrderItem.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getSpecsName());
        }
      
            
        String baseProNo = b2bOrderItem.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getBaseProNo());
        }
      
            
        String bdId = b2bOrderItem.getBdId();
        if (StringUtils.isNotBlank(bdId) ) {
           sqlBuffer.append("bdId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getBdId());
        }
      
            
        String categoryId = b2bOrderItem.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getCategoryId());
        }
      
            
        String restId = b2bOrderItem.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getRestId());
        }
      
            
        String storageStatus = b2bOrderItem.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) ) {
           sqlBuffer.append("storageStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getStorageStatus());
        }
      
            
        String priCatLineId = b2bOrderItem.getPriCatLineId();
        if (StringUtils.isNotBlank(priCatLineId) ) {
           sqlBuffer.append("priCatLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderItem.getPriCatLineId());
        }
      
            
        String barCode = b2bOrderItem.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    
	     param.add(b2bOrderItem.getBarCode());
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
    public B2bOrderItemBean trimStringValue(B2bOrderItemBean b2bOrderItem) {
        String price = b2bOrderItem.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bOrderItem.setPrice(price.substring(0, 100));
        }

        String bId = b2bOrderItem.getBId();
        if (StringUtils.isNotBlank(bId) && bId.length() > 36) {
            b2bOrderItem.setBId(bId.substring(0, 36));
        }

        String proDesc = b2bOrderItem.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bOrderItem.setProDesc(proDesc.substring(0, 255));
        }

        String rmtDetailId = b2bOrderItem.getRmtDetailId();
        if (StringUtils.isNotBlank(rmtDetailId) && rmtDetailId.length() > 36) {
            b2bOrderItem.setRmtDetailId(rmtDetailId.substring(0, 36));
        }

        String quantity = b2bOrderItem.getQuantity();
        if (StringUtils.isNotBlank(quantity) && quantity.length() > 100) {
            b2bOrderItem.setQuantity(quantity.substring(0, 100));
        }

        String volume = b2bOrderItem.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bOrderItem.setVolume(volume.substring(0, 10));
        }

        String orderPkgCode = b2bOrderItem.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bOrderItem.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String numberOfBroken = b2bOrderItem.getNumberOfBroken();
        if (StringUtils.isNotBlank(numberOfBroken) && numberOfBroken.length() > 60) {
            b2bOrderItem.setNumberOfBroken(numberOfBroken.substring(0, 60));
        }

        String supplierId = b2bOrderItem.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bOrderItem.setSupplierId(supplierId.substring(0, 36));
        }

        String priceAgencyName = b2bOrderItem.getPriceAgencyName();
        if (StringUtils.isNotBlank(priceAgencyName) && priceAgencyName.length() > 255) {
            b2bOrderItem.setPriceAgencyName(priceAgencyName.substring(0, 255));
        }

        String privilege = b2bOrderItem.getPrivilege();
        if (StringUtils.isNotBlank(privilege) && privilege.length() > 100) {
            b2bOrderItem.setPrivilege(privilege.substring(0, 100));
        }

        String pic = b2bOrderItem.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 100) {
            b2bOrderItem.setPic(pic.substring(0, 100));
        }

        String isCodeless = b2bOrderItem.getIsCodeless();
        if (StringUtils.isNotBlank(isCodeless) && isCodeless.length() > 2) {
            b2bOrderItem.setIsCodeless(isCodeless.substring(0, 2));
        }

        String rmtPkgId = b2bOrderItem.getRmtPkgId();
        if (StringUtils.isNotBlank(rmtPkgId) && rmtPkgId.length() > 36) {
            b2bOrderItem.setRmtPkgId(rmtPkgId.substring(0, 36));
        }

        String bdNo = b2bOrderItem.getBdNo();
        if (StringUtils.isNotBlank(bdNo) && bdNo.length() > 36) {
            b2bOrderItem.setBdNo(bdNo.substring(0, 36));
        }

        String startPointQyt = b2bOrderItem.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) && startPointQyt.length() > 60) {
            b2bOrderItem.setStartPointQyt(startPointQyt.substring(0, 60));
        }

        String amountPaid = b2bOrderItem.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) && amountPaid.length() > 100) {
            b2bOrderItem.setAmountPaid(amountPaid.substring(0, 100));
        }

        String capacity = b2bOrderItem.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 100) {
            b2bOrderItem.setCapacity(capacity.substring(0, 100));
        }

        String proColorNo = b2bOrderItem.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bOrderItem.setProColorNo(proColorNo.substring(0, 36));
        }

        String restName = b2bOrderItem.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bOrderItem.setRestName(restName.substring(0, 255));
        }

        String discount = b2bOrderItem.getDiscount();
        if (StringUtils.isNotBlank(discount) && discount.length() > 100) {
            b2bOrderItem.setDiscount(discount.substring(0, 100));
        }

        String rmtStatus = b2bOrderItem.getRmtStatus();
        if (StringUtils.isNotBlank(rmtStatus) && rmtStatus.length() > 60) {
            b2bOrderItem.setRmtStatus(rmtStatus.substring(0, 60));
        }

        String baseProId = b2bOrderItem.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bOrderItem.setBaseProId(baseProId.substring(0, 36));
        }

        String parentNamePath = b2bOrderItem.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 3600) {
            b2bOrderItem.setParentNamePath(parentNamePath.substring(0, 3600));
        }

        String strategyDesc = b2bOrderItem.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) && strategyDesc.length() > 255) {
            b2bOrderItem.setStrategyDesc(strategyDesc.substring(0, 255));
        }

        String size = b2bOrderItem.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bOrderItem.setSize(size.substring(0, 36));
        }

        String parentIdPath = b2bOrderItem.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 3600) {
            b2bOrderItem.setParentIdPath(parentIdPath.substring(0, 3600));
        }

        String supplierName = b2bOrderItem.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bOrderItem.setSupplierName(supplierName.substring(0, 255));
        }

        String applDesc = b2bOrderItem.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bOrderItem.setApplDesc(applDesc.substring(0, 255));
        }

        String slsPmtnId = b2bOrderItem.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) && slsPmtnId.length() > 20) {
            b2bOrderItem.setSlsPmtnId(slsPmtnId.substring(0, 20));
        }

        String priceAgencyId = b2bOrderItem.getPriceAgencyId();
        if (StringUtils.isNotBlank(priceAgencyId) && priceAgencyId.length() > 36) {
            b2bOrderItem.setPriceAgencyId(priceAgencyId.substring(0, 36));
        }

        String subtotal = b2bOrderItem.getSubtotal();
        if (StringUtils.isNotBlank(subtotal) && subtotal.length() > 100) {
            b2bOrderItem.setSubtotal(subtotal.substring(0, 100));
        }

        String proName = b2bOrderItem.getProName();
        if (StringUtils.isNotBlank(proName) && proName.length() > 255) {
            b2bOrderItem.setProName(proName.substring(0, 255));
        }

        String specsId = b2bOrderItem.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bOrderItem.setSpecsId(specsId.substring(0, 36));
        }

        String targetClient = b2bOrderItem.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bOrderItem.setTargetClient(targetClient.substring(0, 20));
        }

        String orderPkgId = b2bOrderItem.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bOrderItem.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String proId = b2bOrderItem.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bOrderItem.setProId(proId.substring(0, 36));
        }

        String categoryName = b2bOrderItem.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bOrderItem.setCategoryName(categoryName.substring(0, 255));
        }

        String cartItemId = b2bOrderItem.getCartItemId();
        if (StringUtils.isNotBlank(cartItemId) && cartItemId.length() > 36) {
            b2bOrderItem.setCartItemId(cartItemId.substring(0, 36));
        }

        String damageBdId = b2bOrderItem.getDamageBdId();
        if (StringUtils.isNotBlank(damageBdId) && damageBdId.length() > 36) {
            b2bOrderItem.setDamageBdId(damageBdId.substring(0, 36));
        }

        String specsName = b2bOrderItem.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bOrderItem.setSpecsName(specsName.substring(0, 255));
        }

        String baseProNo = b2bOrderItem.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bOrderItem.setBaseProNo(baseProNo.substring(0, 50));
        }

        String bdId = b2bOrderItem.getBdId();
        if (StringUtils.isNotBlank(bdId) && bdId.length() > 36) {
            b2bOrderItem.setBdId(bdId.substring(0, 36));
        }

        String categoryId = b2bOrderItem.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bOrderItem.setCategoryId(categoryId.substring(0, 36));
        }

        String restId = b2bOrderItem.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bOrderItem.setRestId(restId.substring(0, 36));
        }

        String storageStatus = b2bOrderItem.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) && storageStatus.length() > 20) {
            b2bOrderItem.setStorageStatus(storageStatus.substring(0, 20));
        }

        String priCatLineId = b2bOrderItem.getPriCatLineId();
        if (StringUtils.isNotBlank(priCatLineId) && priCatLineId.length() > 36) {
            b2bOrderItem.setPriCatLineId(priCatLineId.substring(0, 36));
        }

        String barCode = b2bOrderItem.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bOrderItem.setBarCode(barCode.substring(0, 36));
        }

        return b2bOrderItem;
    }
}
