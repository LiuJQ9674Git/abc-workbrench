package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartDetailBean;
import com.ndlan.g2.b2b.dao.B2bShoppingCartDetailDao;

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
@Component("b2bShoppingCartDetailAtomService")
public class B2bShoppingCartDetailAtomServiceImpl  extends BaseService<B2bShoppingCartDetailDao, 
	B2bShoppingCartDetailBean>      implements B2bShoppingCartDetailAtomService {

    @Resource(name="b2bShoppingCartDetailDao")
    protected B2bShoppingCartDetailDao b2bShoppingCartDetailDao;

    @Override
    public int saveB2bShoppingCartDetailBean(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        trimStringValue(b2bShoppingCartDetail);
        return b2bShoppingCartDetailDao.insertSelective(b2bShoppingCartDetail);
    }

    @Override
    public int saveAndGetId(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        trimStringValue(b2bShoppingCartDetail);
        return b2bShoppingCartDetailDao.insertSelectiveAndGetId(b2bShoppingCartDetail);
    }

    @Override
    public int update(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        trimStringValue(b2bShoppingCartDetail);
        return b2bShoppingCartDetailDao.updateByPrimaryKeySelective(b2bShoppingCartDetail);
    }

    @Override
    public int saveOrUpdateB2bShoppingCartDetailBean(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        if (null == b2bShoppingCartDetail.getCartItemId() ||
		"" == b2bShoppingCartDetail.getCartItemId()) {
            return saveB2bShoppingCartDetailBean(b2bShoppingCartDetail);
        } else {
            return update(b2bShoppingCartDetail);
        }
    }

    @Override
    public B2bShoppingCartDetailBean getB2bShoppingCartDetailBean(String cartItemId) {
        return b2bShoppingCartDetailDao.selectByPrimaryKey(cartItemId);
    }

    @Override
    public List<B2bShoppingCartDetailBean> getAll() {
        return b2bShoppingCartDetailDao.selectAll();
    }

    @Override
    public void delete(String cartItemId) {
         b2bShoppingCartDetailDao.deleteByPrimaryKey(cartItemId);
    }

    public List<B2bShoppingCartDetailBean> queryB2bShoppingCartDetailBean
	(B2bShoppingCartDetailBean b2bShoppingCartDetail, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bShoppingCartDetail);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bShoppingCartDetailDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bShoppingCartDetailDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bShoppingCartDetailDao.update(sql, args);
    }

    public List<B2bShoppingCartDetailBean> queryB2bShoppingCartDetailBean
	(B2bShoppingCartDetailBean b2bShoppingCartDetail){
	SQLParam sqlParam=getWhereSQL(b2bShoppingCartDetail);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bShoppingCartDetailDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String proDesc = b2bShoppingCartDetail.getProDesc();
        if (StringUtils.isNotBlank(proDesc) ) {
           sqlBuffer.append("proDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getProDesc());
        }
      
            
        String volume = b2bShoppingCartDetail.getVolume();
        if (StringUtils.isNotBlank(volume) ) {
           sqlBuffer.append("volume=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getVolume());
        }
      
            
        String discount = b2bShoppingCartDetail.getDiscount();
        if (StringUtils.isNotBlank(discount) ) {
           sqlBuffer.append("discount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getDiscount());
        }
      
            
        String price = b2bShoppingCartDetail.getPrice();
        if (StringUtils.isNotBlank(price) ) {
           sqlBuffer.append("price=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getPrice());
        }
      
            
        String subtotal = b2bShoppingCartDetail.getSubtotal();
        if (StringUtils.isNotBlank(subtotal) ) {
           sqlBuffer.append("subtotal=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSubtotal());
        }
      
            
        String barCode = b2bShoppingCartDetail.getBarCode();
        if (StringUtils.isNotBlank(barCode) ) {
           sqlBuffer.append("barCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getBarCode());
        }
      
            
        String privilege = b2bShoppingCartDetail.getPrivilege();
        if (StringUtils.isNotBlank(privilege) ) {
           sqlBuffer.append("privilege=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getPrivilege());
        }
      
            
        String restName = b2bShoppingCartDetail.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getRestName());
        }
      
            
        String specsName = b2bShoppingCartDetail.getSpecsName();
        if (StringUtils.isNotBlank(specsName) ) {
           sqlBuffer.append("specsName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSpecsName());
        }
      
            
        String categoryName = b2bShoppingCartDetail.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getCategoryName());
        }
      
            
        String supplierId = b2bShoppingCartDetail.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSupplierId());
        }
      
            
        String size = b2bShoppingCartDetail.getSize();
        if (StringUtils.isNotBlank(size) ) {
           sqlBuffer.append("size=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSize());
        }
      
            
	  = b2bShoppingCartDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.get());
        } 
      
            
        String supplierName = b2bShoppingCartDetail.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSupplierName());
        }
      
            
        String specsId = b2bShoppingCartDetail.getSpecsId();
        if (StringUtils.isNotBlank(specsId) ) {
           sqlBuffer.append("specsId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSpecsId());
        }
      
            
        String cartPkgId = b2bShoppingCartDetail.getCartPkgId();
        if (StringUtils.isNotBlank(cartPkgId) ) {
           sqlBuffer.append("cartPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getCartPkgId());
        }
      
            
        String startPointQyt = b2bShoppingCartDetail.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) ) {
           sqlBuffer.append("startPointQyt=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getStartPointQyt());
        }
      
            
        String categoryId = b2bShoppingCartDetail.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getCategoryId());
        }
      
            
        String proColorNo = b2bShoppingCartDetail.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) ) {
           sqlBuffer.append("proColorNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getProColorNo());
        }
      
            
        String slsPmtnId = b2bShoppingCartDetail.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) ) {
           sqlBuffer.append("slsPmtnId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getSlsPmtnId());
        }
      
            
	  = b2bShoppingCartDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.get());
        } 
      
            
        String name = b2bShoppingCartDetail.getName();
        if (StringUtils.isNotBlank(name) ) {
           sqlBuffer.append("name=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getName());
        }
      
            
        String restId = b2bShoppingCartDetail.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getRestId());
        }
      
            
        String strategyDesc = b2bShoppingCartDetail.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) ) {
           sqlBuffer.append("strategyDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getStrategyDesc());
        }
      
            
	  = b2bShoppingCartDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.get());
        } 
      
            
        String applDesc = b2bShoppingCartDetail.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) ) {
           sqlBuffer.append("applDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getApplDesc());
        }
      
            
        String payStatus = b2bShoppingCartDetail.getPayStatus();
        if (StringUtils.isNotBlank(payStatus) ) {
           sqlBuffer.append("payStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getPayStatus());
        }
      
            
        String capacity = b2bShoppingCartDetail.getCapacity();
        if (StringUtils.isNotBlank(capacity) ) {
           sqlBuffer.append("capacity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getCapacity());
        }
      
            
        String pic = b2bShoppingCartDetail.getPic();
        if (StringUtils.isNotBlank(pic) ) {
           sqlBuffer.append("pic=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getPic());
        }
      
            
        String baseProNo = b2bShoppingCartDetail.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) ) {
           sqlBuffer.append("baseProNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getBaseProNo());
        }
      
            
        String targetClient = b2bShoppingCartDetail.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getTargetClient());
        }
      
            
        String cartId = b2bShoppingCartDetail.getCartId();
        if (StringUtils.isNotBlank(cartId) ) {
           sqlBuffer.append("cartId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getCartId());
        }
      
            
        String proId = b2bShoppingCartDetail.getProId();
        if (StringUtils.isNotBlank(proId) ) {
           sqlBuffer.append("proId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getProId());
        }
      
            
        String cartItemId = b2bShoppingCartDetail.getCartItemId();
        if (StringUtils.isNotBlank(cartItemId) ) {
           sqlBuffer.append("cartItemId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getCartItemId());
        }
      
            
        String quantity = b2bShoppingCartDetail.getQuantity();
        if (StringUtils.isNotBlank(quantity) ) {
           sqlBuffer.append("quantity=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getQuantity());
        }
      
            
        String baseProId = b2bShoppingCartDetail.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) ) {
           sqlBuffer.append("baseProId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getBaseProId());
        }
      
            
        String isCodeless = b2bShoppingCartDetail.getIsCodeless();
        if (StringUtils.isNotBlank(isCodeless) ) {
           sqlBuffer.append("isCodeless=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getIsCodeless());
        }
      
            
	  = b2bShoppingCartDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.get());
        } 
      
            
	  = b2bShoppingCartDetail.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.get());
        } 
      
            
        String priceAgencyName = b2bShoppingCartDetail.getPriceAgencyName();
        if (StringUtils.isNotBlank(priceAgencyName) ) {
           sqlBuffer.append("priceAgencyName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartDetail.getPriceAgencyName());
        }
      
            
        String priceAgencyId = b2bShoppingCartDetail.getPriceAgencyId();
        if (StringUtils.isNotBlank(priceAgencyId) ) {
           sqlBuffer.append("priceAgencyId=?");
	    
	     param.add(b2bShoppingCartDetail.getPriceAgencyId());
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
    public B2bShoppingCartDetailBean trimStringValue(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        String proDesc = b2bShoppingCartDetail.getProDesc();
        if (StringUtils.isNotBlank(proDesc) && proDesc.length() > 255) {
            b2bShoppingCartDetail.setProDesc(proDesc.substring(0, 255));
        }

        String volume = b2bShoppingCartDetail.getVolume();
        if (StringUtils.isNotBlank(volume) && volume.length() > 10) {
            b2bShoppingCartDetail.setVolume(volume.substring(0, 10));
        }

        String discount = b2bShoppingCartDetail.getDiscount();
        if (StringUtils.isNotBlank(discount) && discount.length() > 100) {
            b2bShoppingCartDetail.setDiscount(discount.substring(0, 100));
        }

        String price = b2bShoppingCartDetail.getPrice();
        if (StringUtils.isNotBlank(price) && price.length() > 100) {
            b2bShoppingCartDetail.setPrice(price.substring(0, 100));
        }

        String subtotal = b2bShoppingCartDetail.getSubtotal();
        if (StringUtils.isNotBlank(subtotal) && subtotal.length() > 100) {
            b2bShoppingCartDetail.setSubtotal(subtotal.substring(0, 100));
        }

        String barCode = b2bShoppingCartDetail.getBarCode();
        if (StringUtils.isNotBlank(barCode) && barCode.length() > 36) {
            b2bShoppingCartDetail.setBarCode(barCode.substring(0, 36));
        }

        String privilege = b2bShoppingCartDetail.getPrivilege();
        if (StringUtils.isNotBlank(privilege) && privilege.length() > 100) {
            b2bShoppingCartDetail.setPrivilege(privilege.substring(0, 100));
        }

        String restName = b2bShoppingCartDetail.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bShoppingCartDetail.setRestName(restName.substring(0, 255));
        }

        String specsName = b2bShoppingCartDetail.getSpecsName();
        if (StringUtils.isNotBlank(specsName) && specsName.length() > 255) {
            b2bShoppingCartDetail.setSpecsName(specsName.substring(0, 255));
        }

        String categoryName = b2bShoppingCartDetail.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bShoppingCartDetail.setCategoryName(categoryName.substring(0, 255));
        }

        String supplierId = b2bShoppingCartDetail.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bShoppingCartDetail.setSupplierId(supplierId.substring(0, 36));
        }

        String size = b2bShoppingCartDetail.getSize();
        if (StringUtils.isNotBlank(size) && size.length() > 36) {
            b2bShoppingCartDetail.setSize(size.substring(0, 36));
        }

        String supplierName = b2bShoppingCartDetail.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bShoppingCartDetail.setSupplierName(supplierName.substring(0, 255));
        }

        String specsId = b2bShoppingCartDetail.getSpecsId();
        if (StringUtils.isNotBlank(specsId) && specsId.length() > 36) {
            b2bShoppingCartDetail.setSpecsId(specsId.substring(0, 36));
        }

        String cartPkgId = b2bShoppingCartDetail.getCartPkgId();
        if (StringUtils.isNotBlank(cartPkgId) && cartPkgId.length() > 36) {
            b2bShoppingCartDetail.setCartPkgId(cartPkgId.substring(0, 36));
        }

        String startPointQyt = b2bShoppingCartDetail.getStartPointQyt();
        if (StringUtils.isNotBlank(startPointQyt) && startPointQyt.length() > 100) {
            b2bShoppingCartDetail.setStartPointQyt(startPointQyt.substring(0, 100));
        }

        String categoryId = b2bShoppingCartDetail.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bShoppingCartDetail.setCategoryId(categoryId.substring(0, 36));
        }

        String proColorNo = b2bShoppingCartDetail.getProColorNo();
        if (StringUtils.isNotBlank(proColorNo) && proColorNo.length() > 36) {
            b2bShoppingCartDetail.setProColorNo(proColorNo.substring(0, 36));
        }

        String slsPmtnId = b2bShoppingCartDetail.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) && slsPmtnId.length() > 2) {
            b2bShoppingCartDetail.setSlsPmtnId(slsPmtnId.substring(0, 2));
        }

        String name = b2bShoppingCartDetail.getName();
        if (StringUtils.isNotBlank(name) && name.length() > 36) {
            b2bShoppingCartDetail.setName(name.substring(0, 36));
        }

        String restId = b2bShoppingCartDetail.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bShoppingCartDetail.setRestId(restId.substring(0, 36));
        }

        String strategyDesc = b2bShoppingCartDetail.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) && strategyDesc.length() > 2) {
            b2bShoppingCartDetail.setStrategyDesc(strategyDesc.substring(0, 2));
        }

        String applDesc = b2bShoppingCartDetail.getApplDesc();
        if (StringUtils.isNotBlank(applDesc) && applDesc.length() > 255) {
            b2bShoppingCartDetail.setApplDesc(applDesc.substring(0, 255));
        }

        String payStatus = b2bShoppingCartDetail.getPayStatus();
        if (StringUtils.isNotBlank(payStatus) && payStatus.length() > 2) {
            b2bShoppingCartDetail.setPayStatus(payStatus.substring(0, 2));
        }

        String capacity = b2bShoppingCartDetail.getCapacity();
        if (StringUtils.isNotBlank(capacity) && capacity.length() > 100) {
            b2bShoppingCartDetail.setCapacity(capacity.substring(0, 100));
        }

        String pic = b2bShoppingCartDetail.getPic();
        if (StringUtils.isNotBlank(pic) && pic.length() > 255) {
            b2bShoppingCartDetail.setPic(pic.substring(0, 255));
        }

        String baseProNo = b2bShoppingCartDetail.getBaseProNo();
        if (StringUtils.isNotBlank(baseProNo) && baseProNo.length() > 50) {
            b2bShoppingCartDetail.setBaseProNo(baseProNo.substring(0, 50));
        }

        String targetClient = b2bShoppingCartDetail.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bShoppingCartDetail.setTargetClient(targetClient.substring(0, 20));
        }

        String cartId = b2bShoppingCartDetail.getCartId();
        if (StringUtils.isNotBlank(cartId) && cartId.length() > 36) {
            b2bShoppingCartDetail.setCartId(cartId.substring(0, 36));
        }

        String proId = b2bShoppingCartDetail.getProId();
        if (StringUtils.isNotBlank(proId) && proId.length() > 36) {
            b2bShoppingCartDetail.setProId(proId.substring(0, 36));
        }

        String cartItemId = b2bShoppingCartDetail.getCartItemId();
        if (StringUtils.isNotBlank(cartItemId) && cartItemId.length() > 36) {
            b2bShoppingCartDetail.setCartItemId(cartItemId.substring(0, 36));
        }

        String quantity = b2bShoppingCartDetail.getQuantity();
        if (StringUtils.isNotBlank(quantity) && quantity.length() > 100) {
            b2bShoppingCartDetail.setQuantity(quantity.substring(0, 100));
        }

        String baseProId = b2bShoppingCartDetail.getBaseProId();
        if (StringUtils.isNotBlank(baseProId) && baseProId.length() > 36) {
            b2bShoppingCartDetail.setBaseProId(baseProId.substring(0, 36));
        }

        String isCodeless = b2bShoppingCartDetail.getIsCodeless();
        if (StringUtils.isNotBlank(isCodeless) && isCodeless.length() > 2) {
            b2bShoppingCartDetail.setIsCodeless(isCodeless.substring(0, 2));
        }

        String priceAgencyName = b2bShoppingCartDetail.getPriceAgencyName();
        if (StringUtils.isNotBlank(priceAgencyName) && priceAgencyName.length() > 255) {
            b2bShoppingCartDetail.setPriceAgencyName(priceAgencyName.substring(0, 255));
        }

        String priceAgencyId = b2bShoppingCartDetail.getPriceAgencyId();
        if (StringUtils.isNotBlank(priceAgencyId) && priceAgencyId.length() > 36) {
            b2bShoppingCartDetail.setPriceAgencyId(priceAgencyId.substring(0, 36));
        }

        return b2bShoppingCartDetail;
    }
}
