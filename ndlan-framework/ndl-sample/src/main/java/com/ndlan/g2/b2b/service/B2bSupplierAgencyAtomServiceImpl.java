package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bSupplierAgencyBean;
import com.ndlan.g2.b2b.dao.B2bSupplierAgencyDao;

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
@Component("b2bSupplierAgencyAtomService")
public class B2bSupplierAgencyAtomServiceImpl  extends BaseService<B2bSupplierAgencyDao, 
	B2bSupplierAgencyBean>      implements B2bSupplierAgencyAtomService {

    @Resource(name="b2bSupplierAgencyDao")
    protected B2bSupplierAgencyDao b2bSupplierAgencyDao;

    @Override
    public int saveB2bSupplierAgencyBean(B2bSupplierAgencyBean b2bSupplierAgency) {
        trimStringValue(b2bSupplierAgency);
        return b2bSupplierAgencyDao.insertSelective(b2bSupplierAgency);
    }

    @Override
    public int saveAndGetId(B2bSupplierAgencyBean b2bSupplierAgency) {
        trimStringValue(b2bSupplierAgency);
        return b2bSupplierAgencyDao.insertSelectiveAndGetId(b2bSupplierAgency);
    }

    @Override
    public int update(B2bSupplierAgencyBean b2bSupplierAgency) {
        trimStringValue(b2bSupplierAgency);
        return b2bSupplierAgencyDao.updateByPrimaryKeySelective(b2bSupplierAgency);
    }

    @Override
    public int saveOrUpdateB2bSupplierAgencyBean(B2bSupplierAgencyBean b2bSupplierAgency) {
        if (null == b2bSupplierAgency.getSupplierAgencyId() ||
		"" == b2bSupplierAgency.getSupplierAgencyId()) {
            return saveB2bSupplierAgencyBean(b2bSupplierAgency);
        } else {
            return update(b2bSupplierAgency);
        }
    }

    @Override
    public B2bSupplierAgencyBean getB2bSupplierAgencyBean(String supplierAgencyId) {
        return b2bSupplierAgencyDao.selectByPrimaryKey(supplierAgencyId);
    }

    @Override
    public List<B2bSupplierAgencyBean> getAll() {
        return b2bSupplierAgencyDao.selectAll();
    }

    @Override
    public void delete(String supplierAgencyId) {
         b2bSupplierAgencyDao.deleteByPrimaryKey(supplierAgencyId);
    }

    public List<B2bSupplierAgencyBean> queryB2bSupplierAgencyBean
	(B2bSupplierAgencyBean b2bSupplierAgency, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bSupplierAgency);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bSupplierAgencyDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bSupplierAgencyDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bSupplierAgencyDao.update(sql, args);
    }

    public List<B2bSupplierAgencyBean> queryB2bSupplierAgencyBean
	(B2bSupplierAgencyBean b2bSupplierAgency){
	SQLParam sqlParam=getWhereSQL(b2bSupplierAgency);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bSupplierAgencyDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bSupplierAgencyBean b2bSupplierAgency) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	  = b2bSupplierAgency.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.get());
        } 
      
            
        String supplierName = b2bSupplierAgency.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getSupplierName());
        }
      
            
	  = b2bSupplierAgency.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.get());
        } 
      
            
        String deliveryAddress = b2bSupplierAgency.getDeliveryAddress();
        if (StringUtils.isNotBlank(deliveryAddress) ) {
           sqlBuffer.append("deliveryAddress=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getDeliveryAddress());
        }
      
            
	  = b2bSupplierAgency.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.get());
        } 
      
            
        String sendAddressId = b2bSupplierAgency.getSendAddressId();
        if (StringUtils.isNotBlank(sendAddressId) ) {
           sqlBuffer.append("sendAddressId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getSendAddressId());
        }
      
            
        String contactUser = b2bSupplierAgency.getContactUser();
        if (StringUtils.isNotBlank(contactUser) ) {
           sqlBuffer.append("contactUser=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getContactUser());
        }
      
            
        String supplierAgencyId = b2bSupplierAgency.getSupplierAgencyId();
        if (StringUtils.isNotBlank(supplierAgencyId) ) {
           sqlBuffer.append("supplierAgencyId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getSupplierAgencyId());
        }
      
            
	  = b2bSupplierAgency.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.get());
        } 
      
            
        String agencyId = b2bSupplierAgency.getAgencyId();
        if (StringUtils.isNotBlank(agencyId) ) {
           sqlBuffer.append("agencyId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getAgencyId());
        }
      
            
        String agencyName = b2bSupplierAgency.getAgencyName();
        if (StringUtils.isNotBlank(agencyName) ) {
           sqlBuffer.append("agencyName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getAgencyName());
        }
      
            
	  = b2bSupplierAgency.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.get());
        } 
      
            
        String remake = b2bSupplierAgency.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getRemake());
        }
      
            
        String contactPhone = b2bSupplierAgency.getContactPhone();
        if (StringUtils.isNotBlank(contactPhone) ) {
           sqlBuffer.append("contactPhone=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bSupplierAgency.getContactPhone());
        }
      
            
        String supplierId = b2bSupplierAgency.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    
	     param.add(b2bSupplierAgency.getSupplierId());
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
    public B2bSupplierAgencyBean trimStringValue(B2bSupplierAgencyBean b2bSupplierAgency) {
        String supplierName = b2bSupplierAgency.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 36) {
            b2bSupplierAgency.setSupplierName(supplierName.substring(0, 36));
        }

        String deliveryAddress = b2bSupplierAgency.getDeliveryAddress();
        if (StringUtils.isNotBlank(deliveryAddress) && deliveryAddress.length() > 255) {
            b2bSupplierAgency.setDeliveryAddress(deliveryAddress.substring(0, 255));
        }

        String sendAddressId = b2bSupplierAgency.getSendAddressId();
        if (StringUtils.isNotBlank(sendAddressId) && sendAddressId.length() > 36) {
            b2bSupplierAgency.setSendAddressId(sendAddressId.substring(0, 36));
        }

        String contactUser = b2bSupplierAgency.getContactUser();
        if (StringUtils.isNotBlank(contactUser) && contactUser.length() > 255) {
            b2bSupplierAgency.setContactUser(contactUser.substring(0, 255));
        }

        String supplierAgencyId = b2bSupplierAgency.getSupplierAgencyId();
        if (StringUtils.isNotBlank(supplierAgencyId) && supplierAgencyId.length() > 36) {
            b2bSupplierAgency.setSupplierAgencyId(supplierAgencyId.substring(0, 36));
        }

        String agencyId = b2bSupplierAgency.getAgencyId();
        if (StringUtils.isNotBlank(agencyId) && agencyId.length() > 36) {
            b2bSupplierAgency.setAgencyId(agencyId.substring(0, 36));
        }

        String agencyName = b2bSupplierAgency.getAgencyName();
        if (StringUtils.isNotBlank(agencyName) && agencyName.length() > 36) {
            b2bSupplierAgency.setAgencyName(agencyName.substring(0, 36));
        }

        String remake = b2bSupplierAgency.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bSupplierAgency.setRemake(remake.substring(0, 255));
        }

        String contactPhone = b2bSupplierAgency.getContactPhone();
        if (StringUtils.isNotBlank(contactPhone) && contactPhone.length() > 36) {
            b2bSupplierAgency.setContactPhone(contactPhone.substring(0, 36));
        }

        String supplierId = b2bSupplierAgency.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bSupplierAgency.setSupplierId(supplierId.substring(0, 36));
        }

        return b2bSupplierAgency;
    }
}
