package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderPkgBean;
import com.ndlan.g2.b2b.dao.B2bOrderPkgDao;

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
@Component("b2bOrderPkgAtomService")
public class B2bOrderPkgAtomServiceImpl  extends BaseService<B2bOrderPkgDao, 
	B2bOrderPkgBean>      implements B2bOrderPkgAtomService {

    @Resource(name="b2bOrderPkgDao")
    protected B2bOrderPkgDao b2bOrderPkgDao;

    @Override
    public int saveB2bOrderPkgBean(B2bOrderPkgBean b2bOrderPkg) {
        trimStringValue(b2bOrderPkg);
        return b2bOrderPkgDao.insertSelective(b2bOrderPkg);
    }

    @Override
    public int saveAndGetId(B2bOrderPkgBean b2bOrderPkg) {
        trimStringValue(b2bOrderPkg);
        return b2bOrderPkgDao.insertSelectiveAndGetId(b2bOrderPkg);
    }

    @Override
    public int update(B2bOrderPkgBean b2bOrderPkg) {
        trimStringValue(b2bOrderPkg);
        return b2bOrderPkgDao.updateByPrimaryKeySelective(b2bOrderPkg);
    }

    @Override
    public int saveOrUpdateB2bOrderPkgBean(B2bOrderPkgBean b2bOrderPkg) {
        if (null == b2bOrderPkg.getOrderPkgId() ||
		"" == b2bOrderPkg.getOrderPkgId()) {
            return saveB2bOrderPkgBean(b2bOrderPkg);
        } else {
            return update(b2bOrderPkg);
        }
    }

    @Override
    public B2bOrderPkgBean getB2bOrderPkgBean(String orderPkgId) {
        return b2bOrderPkgDao.selectByPrimaryKey(orderPkgId);
    }

    @Override
    public List<B2bOrderPkgBean> getAll() {
        return b2bOrderPkgDao.selectAll();
    }

    @Override
    public void delete(String orderPkgId) {
         b2bOrderPkgDao.deleteByPrimaryKey(orderPkgId);
    }

    public List<B2bOrderPkgBean> queryB2bOrderPkgBean
	(B2bOrderPkgBean b2bOrderPkg, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrderPkg);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderPkgDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderPkgDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderPkgDao.update(sql, args);
    }

    public List<B2bOrderPkgBean> queryB2bOrderPkgBean
	(B2bOrderPkgBean b2bOrderPkg){
	SQLParam sqlParam=getWhereSQL(b2bOrderPkg);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderPkgDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderPkgBean b2bOrderPkg) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String payType = b2bOrderPkg.getPayType();
        if (StringUtils.isNotBlank(payType) ) {
           sqlBuffer.append("payType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getPayType());
        }
      
            
        String customerId = b2bOrderPkg.getCustomerId();
        if (StringUtils.isNotBlank(customerId) ) {
           sqlBuffer.append("customerId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getCustomerId());
        }
      
            
        String orderPkgId = b2bOrderPkg.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getOrderPkgId());
        }
      
            
	  = b2bOrderPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.get());
        } 
      
            
        String customerName = b2bOrderPkg.getCustomerName();
        if (StringUtils.isNotBlank(customerName) ) {
           sqlBuffer.append("customerName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getCustomerName());
        }
      
            
        String orderPkgName = b2bOrderPkg.getOrderPkgName();
        if (StringUtils.isNotBlank(orderPkgName) ) {
           sqlBuffer.append("orderPkgName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getOrderPkgName());
        }
      
            
        String exceptionDesc = b2bOrderPkg.getExceptionDesc();
        if (StringUtils.isNotBlank(exceptionDesc) ) {
           sqlBuffer.append("exceptionDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getExceptionDesc());
        }
      
            
        String supplierId = b2bOrderPkg.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getSupplierId());
        }
      
            
        String strategyDesc = b2bOrderPkg.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) ) {
           sqlBuffer.append("strategyDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getStrategyDesc());
        }
      
            
        String targetClient = b2bOrderPkg.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) ) {
           sqlBuffer.append("targetClient=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getTargetClient());
        }
      
            
        String orderHeadId = b2bOrderPkg.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) ) {
           sqlBuffer.append("orderHeadId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getOrderHeadId());
        }
      
            
        String allDiscount = b2bOrderPkg.getAllDiscount();
        if (StringUtils.isNotBlank(allDiscount) ) {
           sqlBuffer.append("allDiscount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getAllDiscount());
        }
      
            
        String supplierName = b2bOrderPkg.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getSupplierName());
        }
      
            
        String storageStatus = b2bOrderPkg.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) ) {
           sqlBuffer.append("storageStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getStorageStatus());
        }
      
            
	  = b2bOrderPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.get());
        } 
      
            
        String orderPkgCode = b2bOrderPkg.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getOrderPkgCode());
        }
      
            
        String unpaidAmount = b2bOrderPkg.getUnpaidAmount();
        if (StringUtils.isNotBlank(unpaidAmount) ) {
           sqlBuffer.append("unpaidAmount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getUnpaidAmount());
        }
      
            
	  = b2bOrderPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.get());
        } 
      
            
        String remark = b2bOrderPkg.getRemark();
        if (StringUtils.isNotBlank(remark) ) {
           sqlBuffer.append("remark=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getRemark());
        }
      
            
        String restName = b2bOrderPkg.getRestName();
        if (StringUtils.isNotBlank(restName) ) {
           sqlBuffer.append("restName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getRestName());
        }
      
            
        String cartId = b2bOrderPkg.getCartId();
        if (StringUtils.isNotBlank(cartId) ) {
           sqlBuffer.append("cartId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getCartId());
        }
      
            
        String statusDesc = b2bOrderPkg.getStatusDesc();
        if (StringUtils.isNotBlank(statusDesc) ) {
           sqlBuffer.append("statusDesc=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getStatusDesc());
        }
      
            
        String amountPaid = b2bOrderPkg.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) ) {
           sqlBuffer.append("amountPaid=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getAmountPaid());
        }
      
            
        String exceptionSolve = b2bOrderPkg.getExceptionSolve();
        if (StringUtils.isNotBlank(exceptionSolve) ) {
           sqlBuffer.append("exceptionSolve=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getExceptionSolve());
        }
      
            
        String slsPmtnId = b2bOrderPkg.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) ) {
           sqlBuffer.append("slsPmtnId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getSlsPmtnId());
        }
      
            
	  = b2bOrderPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.get());
        } 
      
            
        String allPrivilege = b2bOrderPkg.getAllPrivilege();
        if (StringUtils.isNotBlank(allPrivilege) ) {
           sqlBuffer.append("allPrivilege=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getAllPrivilege());
        }
      
            
        String codelessSum = b2bOrderPkg.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) ) {
           sqlBuffer.append("codelessSum=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getCodelessSum());
        }
      
            
	  = b2bOrderPkg.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.get());
        } 
      
            
        String restId = b2bOrderPkg.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderPkg.getRestId());
        }
      
            
        String amount = b2bOrderPkg.getAmount();
        if (StringUtils.isNotBlank(amount) ) {
           sqlBuffer.append("amount=?");
	    
	     param.add(b2bOrderPkg.getAmount());
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
    public B2bOrderPkgBean trimStringValue(B2bOrderPkgBean b2bOrderPkg) {
        String payType = b2bOrderPkg.getPayType();
        if (StringUtils.isNotBlank(payType) && payType.length() > 36) {
            b2bOrderPkg.setPayType(payType.substring(0, 36));
        }

        String customerId = b2bOrderPkg.getCustomerId();
        if (StringUtils.isNotBlank(customerId) && customerId.length() > 36) {
            b2bOrderPkg.setCustomerId(customerId.substring(0, 36));
        }

        String orderPkgId = b2bOrderPkg.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bOrderPkg.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String customerName = b2bOrderPkg.getCustomerName();
        if (StringUtils.isNotBlank(customerName) && customerName.length() > 255) {
            b2bOrderPkg.setCustomerName(customerName.substring(0, 255));
        }

        String orderPkgName = b2bOrderPkg.getOrderPkgName();
        if (StringUtils.isNotBlank(orderPkgName) && orderPkgName.length() > 64) {
            b2bOrderPkg.setOrderPkgName(orderPkgName.substring(0, 64));
        }

        String exceptionDesc = b2bOrderPkg.getExceptionDesc();
        if (StringUtils.isNotBlank(exceptionDesc) && exceptionDesc.length() > 255) {
            b2bOrderPkg.setExceptionDesc(exceptionDesc.substring(0, 255));
        }

        String supplierId = b2bOrderPkg.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bOrderPkg.setSupplierId(supplierId.substring(0, 36));
        }

        String strategyDesc = b2bOrderPkg.getStrategyDesc();
        if (StringUtils.isNotBlank(strategyDesc) && strategyDesc.length() > 255) {
            b2bOrderPkg.setStrategyDesc(strategyDesc.substring(0, 255));
        }

        String targetClient = b2bOrderPkg.getTargetClient();
        if (StringUtils.isNotBlank(targetClient) && targetClient.length() > 20) {
            b2bOrderPkg.setTargetClient(targetClient.substring(0, 20));
        }

        String orderHeadId = b2bOrderPkg.getOrderHeadId();
        if (StringUtils.isNotBlank(orderHeadId) && orderHeadId.length() > 36) {
            b2bOrderPkg.setOrderHeadId(orderHeadId.substring(0, 36));
        }

        String allDiscount = b2bOrderPkg.getAllDiscount();
        if (StringUtils.isNotBlank(allDiscount) && allDiscount.length() > 100) {
            b2bOrderPkg.setAllDiscount(allDiscount.substring(0, 100));
        }

        String supplierName = b2bOrderPkg.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bOrderPkg.setSupplierName(supplierName.substring(0, 255));
        }

        String storageStatus = b2bOrderPkg.getStorageStatus();
        if (StringUtils.isNotBlank(storageStatus) && storageStatus.length() > 20) {
            b2bOrderPkg.setStorageStatus(storageStatus.substring(0, 20));
        }

        String orderPkgCode = b2bOrderPkg.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bOrderPkg.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String unpaidAmount = b2bOrderPkg.getUnpaidAmount();
        if (StringUtils.isNotBlank(unpaidAmount) && unpaidAmount.length() > 255) {
            b2bOrderPkg.setUnpaidAmount(unpaidAmount.substring(0, 255));
        }

        String remark = b2bOrderPkg.getRemark();
        if (StringUtils.isNotBlank(remark) && remark.length() > 255) {
            b2bOrderPkg.setRemark(remark.substring(0, 255));
        }

        String restName = b2bOrderPkg.getRestName();
        if (StringUtils.isNotBlank(restName) && restName.length() > 255) {
            b2bOrderPkg.setRestName(restName.substring(0, 255));
        }

        String cartId = b2bOrderPkg.getCartId();
        if (StringUtils.isNotBlank(cartId) && cartId.length() > 36) {
            b2bOrderPkg.setCartId(cartId.substring(0, 36));
        }

        String statusDesc = b2bOrderPkg.getStatusDesc();
        if (StringUtils.isNotBlank(statusDesc) && statusDesc.length() > 255) {
            b2bOrderPkg.setStatusDesc(statusDesc.substring(0, 255));
        }

        String amountPaid = b2bOrderPkg.getAmountPaid();
        if (StringUtils.isNotBlank(amountPaid) && amountPaid.length() > 100) {
            b2bOrderPkg.setAmountPaid(amountPaid.substring(0, 100));
        }

        String exceptionSolve = b2bOrderPkg.getExceptionSolve();
        if (StringUtils.isNotBlank(exceptionSolve) && exceptionSolve.length() > 255) {
            b2bOrderPkg.setExceptionSolve(exceptionSolve.substring(0, 255));
        }

        String slsPmtnId = b2bOrderPkg.getSlsPmtnId();
        if (StringUtils.isNotBlank(slsPmtnId) && slsPmtnId.length() > 36) {
            b2bOrderPkg.setSlsPmtnId(slsPmtnId.substring(0, 36));
        }

        String allPrivilege = b2bOrderPkg.getAllPrivilege();
        if (StringUtils.isNotBlank(allPrivilege) && allPrivilege.length() > 100) {
            b2bOrderPkg.setAllPrivilege(allPrivilege.substring(0, 100));
        }

        String codelessSum = b2bOrderPkg.getCodelessSum();
        if (StringUtils.isNotBlank(codelessSum) && codelessSum.length() > 100) {
            b2bOrderPkg.setCodelessSum(codelessSum.substring(0, 100));
        }

        String restId = b2bOrderPkg.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bOrderPkg.setRestId(restId.substring(0, 36));
        }

        String amount = b2bOrderPkg.getAmount();
        if (StringUtils.isNotBlank(amount) && amount.length() > 100) {
            b2bOrderPkg.setAmount(amount.substring(0, 100));
        }

        return b2bOrderPkg;
    }
}
