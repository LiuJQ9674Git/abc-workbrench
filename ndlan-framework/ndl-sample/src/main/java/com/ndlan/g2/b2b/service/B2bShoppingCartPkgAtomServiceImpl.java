package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartPkgBean;
import com.ndlan.g2.b2b.dao.B2bShoppingCartPkgDao;

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
@Component("b2bShoppingCartPkgAtomService")
public class B2bShoppingCartPkgAtomServiceImpl  extends BaseService<B2bShoppingCartPkgDao, 
	B2bShoppingCartPkgBean>      implements B2bShoppingCartPkgAtomService {

    @Resource(name="b2bShoppingCartPkgDao")
    protected B2bShoppingCartPkgDao b2bShoppingCartPkgDao;

    @Override
    public int saveB2bShoppingCartPkgBean(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        trimStringValue(b2bShoppingCartPkg);
        return b2bShoppingCartPkgDao.insertSelective(b2bShoppingCartPkg);
    }

    @Override
    public int saveAndGetId(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        trimStringValue(b2bShoppingCartPkg);
        return b2bShoppingCartPkgDao.insertSelectiveAndGetId(b2bShoppingCartPkg);
    }

    @Override
    public int update(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        trimStringValue(b2bShoppingCartPkg);
        return b2bShoppingCartPkgDao.updateByPrimaryKeySelective(b2bShoppingCartPkg);
    }

    @Override
    public int saveOrUpdateB2bShoppingCartPkgBean(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        if (null == b2bShoppingCartPkg.getCartPkgId() ||
		"" == b2bShoppingCartPkg.getCartPkgId()) {
            return saveB2bShoppingCartPkgBean(b2bShoppingCartPkg);
        } else {
            return update(b2bShoppingCartPkg);
        }
    }

    @Override
    public B2bShoppingCartPkgBean getB2bShoppingCartPkgBean(String cartPkgId) {
        return b2bShoppingCartPkgDao.selectByPrimaryKey(cartPkgId);
    }

    @Override
    public List<B2bShoppingCartPkgBean> getAll() {
        return b2bShoppingCartPkgDao.selectAll();
    }

    @Override
    public void delete(String cartPkgId) {
         b2bShoppingCartPkgDao.deleteByPrimaryKey(cartPkgId);
    }

    public List<B2bShoppingCartPkgBean> queryB2bShoppingCartPkgBean
	(B2bShoppingCartPkgBean b2bShoppingCartPkg, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bShoppingCartPkg);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bShoppingCartPkgDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bShoppingCartPkgDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bShoppingCartPkgDao.update(sql, args);
    }

    public List<B2bShoppingCartPkgBean> queryB2bShoppingCartPkgBean
	(B2bShoppingCartPkgBean b2bShoppingCartPkg){
	SQLParam sqlParam=getWhereSQL(b2bShoppingCartPkg);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bShoppingCartPkgDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String strategyDesc = b2bShoppingCartPkg.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) ) {
           sqlBuffer.append("strategyDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getStrategyDesc());
        }
      
            
        String slsPmtnId = b2bShoppingCartPkg.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) ) {
           sqlBuffer.append("slsPmtnId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getSlsPmtnId());
        }
      
            
        String cartId = b2bShoppingCartPkg.getCartId();
        if (StringUtils.isNotBlank(cartId) ) {
           sqlBuffer.append("cartId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getCartId());
        }
      
            
        String remark = b2bShoppingCartPkg.getRemark();
        if (StringUtils.isNotBlank(remark) ) {
           sqlBuffer.append("remark=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getRemark());
        }
      
            
        String amount = b2bShoppingCartPkg.getAmount();
        if (StringUtils.isNotBlank(amount) ) {
           sqlBuffer.append("amount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getAmount());
        }
      
            
        String derate = b2bShoppingCartPkg.getDerate();
        if (StringUtils.isNotBlank(derate) ) {
           sqlBuffer.append("derate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getDerate());
        }
      
            
        String discount = b2bShoppingCartPkg.getDiscount();
        if (StringUtils.isNotBlank(discount) ) {
           sqlBuffer.append("discount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getDiscount());
        }
      
            
        String restId = b2bShoppingCartPkg.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getRestId());
        }
      
            
        String customerName = b2bShoppingCartPkg.getCustomerName();
        if (StringUtils.isNotBlank(customerName) ) {
           sqlBuffer.append("customerName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getCustomerName());
        }
      
            
	  = b2bShoppingCartPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.get());
        } 
      
            
	  = b2bShoppingCartPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.get());
        } 
      
            
        String cartPkgId = b2bShoppingCartPkg.getCartPkgId();
        if (StringUtils.isNotBlank(cartPkgId) ) {
           sqlBuffer.append("cartPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getCartPkgId());
        }
      
            
        String payType = b2bShoppingCartPkg.getPayType();
        if (StringUtils.isNotBlank(payType) ) {
           sqlBuffer.append("payType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getPayType());
        }
      
            
        String supplierName = b2bShoppingCartPkg.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getSupplierName());
        }
      
            
	  = b2bShoppingCartPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.get());
        } 
      
            
        String customerId = b2bShoppingCartPkg.getCustomerId();
        if (StringUtils.isNotBlank(customerId) ) {
           sqlBuffer.append("customerId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getCustomerId());
        }
      
            
        String restName = b2bShoppingCartPkg.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getRestName());
        }
      
            
        String supplierId = b2bShoppingCartPkg.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getSupplierId());
        }
      
            
	  = b2bShoppingCartPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.get());
        } 
      
            
        String amountPaid = b2bShoppingCartPkg.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) ) {
           sqlBuffer.append("amountPaid=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getAmountPaid());
        }
      
            
        String codelessSum = b2bShoppingCartPkg.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) ) {
           sqlBuffer.append("codelessSum=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getCodelessSum());
        }
      
            
        String targetClient = b2bShoppingCartPkg.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bShoppingCartPkg.getTargetClient());
        }
      
            
	  = b2bShoppingCartPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    
	     param.add(b2bShoppingCartPkg.get());
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
    public B2bShoppingCartPkgBean trimStringValue(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        String strategyDesc = b2bShoppingCartPkg.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) && strategyDesc.length() > 2) {
            b2bShoppingCartPkg.setStrategyDesc(strategyDesc.substring(0, 2));
        }

        String slsPmtnId = b2bShoppingCartPkg.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) && slsPmtnId.length() > 2) {
            b2bShoppingCartPkg.setSlsPmtnId(slsPmtnId.substring(0, 2));
        }

        String cartId = b2bShoppingCartPkg.getCartId();
        if (StringUtils.isNotBlank(cartId) && cartId.length() > 36) {
            b2bShoppingCartPkg.setCartId(cartId.substring(0, 36));
        }

        String remark = b2bShoppingCartPkg.getRemark();
        if (StringUtils.isNotBlank(remark) && remark.length() > 255) {
            b2bShoppingCartPkg.setRemark(remark.substring(0, 255));
        }

        String amount = b2bShoppingCartPkg.getAmount();
        if (StringUtils.isNotBlank(amount) && amount.length() > 100) {
            b2bShoppingCartPkg.setAmount(amount.substring(0, 100));
        }

        String derate = b2bShoppingCartPkg.getDerate();
        if (StringUtils.isNotBlank(derate) && derate.length() > 100) {
            b2bShoppingCartPkg.setDerate(derate.substring(0, 100));
        }

        String discount = b2bShoppingCartPkg.getDiscount();
        if (StringUtils.isNotBlank(discount) && discount.length() > 100) {
            b2bShoppingCartPkg.setDiscount(discount.substring(0, 100));
        }

        String restId = b2bShoppingCartPkg.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bShoppingCartPkg.setRestId(restId.substring(0, 36));
        }

        String customerName = b2bShoppingCartPkg.getCustomerName();
        if (StringUtils.isNotBlank(customerName) && customerName.length() > 255) {
            b2bShoppingCartPkg.setCustomerName(customerName.substring(0, 255));
        }

        String cartPkgId = b2bShoppingCartPkg.getCartPkgId();
        if (StringUtils.isNotBlank(cartPkgId) && cartPkgId.length() > 36) {
            b2bShoppingCartPkg.setCartPkgId(cartPkgId.substring(0, 36));
        }

        String payType = b2bShoppingCartPkg.getPayType();
        if (StringUtils.isNotBlank(payType) && payType.length() > 36) {
            b2bShoppingCartPkg.setPayType(payType.substring(0, 36));
        }

        String supplierName = b2bShoppingCartPkg.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bShoppingCartPkg.setSupplierName(supplierName.substring(0, 255));
        }

        String customerId = b2bShoppingCartPkg.getCustomerId();
        if (StringUtils.isNotBlank(customerId) && customerId.length() > 36) {
            b2bShoppingCartPkg.setCustomerId(customerId.substring(0, 36));
        }

        String restName = b2bShoppingCartPkg.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bShoppingCartPkg.setRestName(restName.substring(0, 255));
        }

        String supplierId = b2bShoppingCartPkg.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bShoppingCartPkg.setSupplierId(supplierId.substring(0, 36));
        }

        String amountPaid = b2bShoppingCartPkg.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) && amountPaid.length() > 100) {
            b2bShoppingCartPkg.setAmountPaid(amountPaid.substring(0, 100));
        }

        String codelessSum = b2bShoppingCartPkg.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) && codelessSum.length() > 100) {
            b2bShoppingCartPkg.setCodelessSum(codelessSum.substring(0, 100));
        }

        String targetClient = b2bShoppingCartPkg.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bShoppingCartPkg.setTargetClient(targetClient.substring(0, 20));
        }

        return b2bShoppingCartPkg;
    }
}
