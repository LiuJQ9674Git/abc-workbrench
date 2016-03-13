package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bSupplierCardAccountBean;
import com.ndlan.g2.b2b.dao.B2bSupplierCardAccountDao;

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
@Component("b2bSupplierCardAccountAtomService")
public class B2bSupplierCardAccountAtomServiceImpl  extends BaseService<B2bSupplierCardAccountDao, 
	B2bSupplierCardAccountBean>      implements B2bSupplierCardAccountAtomService {

    @Resource(name="b2bSupplierCardAccountDao")
    protected B2bSupplierCardAccountDao b2bSupplierCardAccountDao;

    @Override
    public int saveB2bSupplierCardAccountBean(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        trimStringValue(b2bSupplierCardAccount);
        return b2bSupplierCardAccountDao.insertSelective(b2bSupplierCardAccount);
    }

    @Override
    public int saveAndGetId(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        trimStringValue(b2bSupplierCardAccount);
        return b2bSupplierCardAccountDao.insertSelectiveAndGetId(b2bSupplierCardAccount);
    }

    @Override
    public int update(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        trimStringValue(b2bSupplierCardAccount);
        return b2bSupplierCardAccountDao.updateByPrimaryKeySelective(b2bSupplierCardAccount);
    }

    @Override
    public int saveOrUpdateB2bSupplierCardAccountBean(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        if (null == b2bSupplierCardAccount.getSupCardAcntId() ||
		"" == b2bSupplierCardAccount.getSupCardAcntId()) {
            return saveB2bSupplierCardAccountBean(b2bSupplierCardAccount);
        } else {
            return update(b2bSupplierCardAccount);
        }
    }

    @Override
    public B2bSupplierCardAccountBean getB2bSupplierCardAccountBean(String supCardAcntId) {
        return b2bSupplierCardAccountDao.selectByPrimaryKey(supCardAcntId);
    }

    @Override
    public List<B2bSupplierCardAccountBean> getAll() {
        return b2bSupplierCardAccountDao.selectAll();
    }

    @Override
    public void delete(String supCardAcntId) {
         b2bSupplierCardAccountDao.deleteByPrimaryKey(supCardAcntId);
    }

    public List<B2bSupplierCardAccountBean> queryB2bSupplierCardAccountBean
	(B2bSupplierCardAccountBean b2bSupplierCardAccount, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bSupplierCardAccount);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bSupplierCardAccountDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bSupplierCardAccountDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bSupplierCardAccountDao.update(sql, args);
    }

    public List<B2bSupplierCardAccountBean> queryB2bSupplierCardAccountBean
	(B2bSupplierCardAccountBean b2bSupplierCardAccount){
	SQLParam sqlParam=getWhereSQL(b2bSupplierCardAccount);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bSupplierCardAccountDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String remake = b2bSupplierCardAccount.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getRemake());
        }
      
            
        String catNo = b2bSupplierCardAccount.getCatNo();
        if (StringUtils.isNotBlank(catNo) ) {
           sqlBuffer.append("catNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getCatNo());
        }
      
            
	  = b2bSupplierCardAccount.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.get());
        } 
      
            
        String isDefault = b2bSupplierCardAccount.getIsDefault();
        if (StringUtils.isNotBlank(isDefault) ) {
           sqlBuffer.append("isDefault=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getIsDefault());
        }
      
            
	  = b2bSupplierCardAccount.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.get());
        } 
      
            
        String phoneNo = b2bSupplierCardAccount.getPhoneNo();
        if (StringUtils.isNotBlank(phoneNo) ) {
           sqlBuffer.append("phoneNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getPhoneNo());
        }
      
            
        String cardholderId = b2bSupplierCardAccount.getCardholderId();
        if (StringUtils.isNotBlank(cardholderId) ) {
           sqlBuffer.append("cardholderId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getCardholderId());
        }
      
            
	  = b2bSupplierCardAccount.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.get());
        } 
      
            
        String cardinfoSummay = b2bSupplierCardAccount.getCardinfoSummay();
        if (StringUtils.isNotBlank(cardinfoSummay) ) {
           sqlBuffer.append("cardinfoSummay=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getCardinfoSummay());
        }
      
            
        String branch = b2bSupplierCardAccount.getBranch();
        if (StringUtils.isNotBlank(branch) ) {
           sqlBuffer.append("branch=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getBranch());
        }
      
            
	  = b2bSupplierCardAccount.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.get());
        } 
      
            
        String subbranch = b2bSupplierCardAccount.getSubbranch();
        if (StringUtils.isNotBlank(subbranch) ) {
           sqlBuffer.append("subbranch=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getSubbranch());
        }
      
            
        String supCardAcntId = b2bSupplierCardAccount.getSupCardAcntId();
        if (StringUtils.isNotBlank(supCardAcntId) ) {
           sqlBuffer.append("supCardAcntId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getSupCardAcntId());
        }
      
            
        String bank = b2bSupplierCardAccount.getBank();
        if (StringUtils.isNotBlank(bank) ) {
           sqlBuffer.append("bank=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getBank());
        }
      
            
        String supplierId = b2bSupplierCardAccount.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getSupplierId());
        }
      
            
        String cardNo = b2bSupplierCardAccount.getCardNo();
        if (StringUtils.isNotBlank(cardNo) ) {
           sqlBuffer.append("cardNo=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getCardNo());
        }
      
            
        String cardholderName = b2bSupplierCardAccount.getCardholderName();
        if (StringUtils.isNotBlank(cardholderName) ) {
           sqlBuffer.append("cardholderName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getCardholderName());
        }
      
            
        String supplierName = b2bSupplierCardAccount.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierCardAccount.getSupplierName());
        }
      
            
	  = b2bSupplierCardAccount.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    
	     param.add(b2bSupplierCardAccount.get());
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
    public B2bSupplierCardAccountBean trimStringValue(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        String remake = b2bSupplierCardAccount.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bSupplierCardAccount.setRemake(remake.substring(0, 255));
        }

        String catNo = b2bSupplierCardAccount.getCatNo();
        if (StringUtils.isNotBlank(catNo) && catNo.length() > 36) {
            b2bSupplierCardAccount.setCatNo(catNo.substring(0, 36));
        }

        String isDefault = b2bSupplierCardAccount.getIsDefault();
        if (StringUtils.isNotBlank(isDefault) && isDefault.length() > 2) {
            b2bSupplierCardAccount.setIsDefault(isDefault.substring(0, 2));
        }

        String phoneNo = b2bSupplierCardAccount.getPhoneNo();
        if (StringUtils.isNotBlank(phoneNo) && phoneNo.length() > 20) {
            b2bSupplierCardAccount.setPhoneNo(phoneNo.substring(0, 20));
        }

        String cardholderId = b2bSupplierCardAccount.getCardholderId();
        if (StringUtils.isNotBlank(cardholderId) && cardholderId.length() > 255) {
            b2bSupplierCardAccount.setCardholderId(cardholderId.substring(0, 255));
        }

        String cardinfoSummay = b2bSupplierCardAccount.getCardinfoSummay();
        if (StringUtils.isNotBlank(cardinfoSummay) && cardinfoSummay.length() > 64) {
            b2bSupplierCardAccount.setCardinfoSummay(cardinfoSummay.substring(0, 64));
        }

        String branch = b2bSupplierCardAccount.getBranch();
        if (StringUtils.isNotBlank(branch) && branch.length() > 255) {
            b2bSupplierCardAccount.setBranch(branch.substring(0, 255));
        }

        String subbranch = b2bSupplierCardAccount.getSubbranch();
        if (StringUtils.isNotBlank(subbranch) && subbranch.length() > 255) {
            b2bSupplierCardAccount.setSubbranch(subbranch.substring(0, 255));
        }

        String supCardAcntId = b2bSupplierCardAccount.getSupCardAcntId();
        if (StringUtils.isNotBlank(supCardAcntId) && supCardAcntId.length() > 36) {
            b2bSupplierCardAccount.setSupCardAcntId(supCardAcntId.substring(0, 36));
        }

        String bank = b2bSupplierCardAccount.getBank();
        if (StringUtils.isNotBlank(bank) && bank.length() > 255) {
            b2bSupplierCardAccount.setBank(bank.substring(0, 255));
        }

        String supplierId = b2bSupplierCardAccount.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bSupplierCardAccount.setSupplierId(supplierId.substring(0, 36));
        }

        String cardNo = b2bSupplierCardAccount.getCardNo();
        if (StringUtils.isNotBlank(cardNo) && cardNo.length() > 36) {
            b2bSupplierCardAccount.setCardNo(cardNo.substring(0, 36));
        }

        String cardholderName = b2bSupplierCardAccount.getCardholderName();
        if (StringUtils.isNotBlank(cardholderName) && cardholderName.length() > 36) {
            b2bSupplierCardAccount.setCardholderName(cardholderName.substring(0, 36));
        }

        String supplierName = b2bSupplierCardAccount.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 36) {
            b2bSupplierCardAccount.setSupplierName(supplierName.substring(0, 36));
        }

        return b2bSupplierCardAccount;
    }
}
