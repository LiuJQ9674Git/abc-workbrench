package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bDeliveryAddressBean;
import com.ndlan.g2.b2b.dao.B2bDeliveryAddressDao;

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
@Component("b2bDeliveryAddressAtomService")
public class B2bDeliveryAddressAtomServiceImpl  extends BaseService<B2bDeliveryAddressDao, 
	B2bDeliveryAddressBean>      implements B2bDeliveryAddressAtomService {

    @Resource(name="b2bDeliveryAddressDao")
    protected B2bDeliveryAddressDao b2bDeliveryAddressDao;

    @Override
    public int saveB2bDeliveryAddressBean(B2bDeliveryAddressBean b2bDeliveryAddress) {
        trimStringValue(b2bDeliveryAddress);
        return b2bDeliveryAddressDao.insertSelective(b2bDeliveryAddress);
    }

    @Override
    public int saveAndGetId(B2bDeliveryAddressBean b2bDeliveryAddress) {
        trimStringValue(b2bDeliveryAddress);
        return b2bDeliveryAddressDao.insertSelectiveAndGetId(b2bDeliveryAddress);
    }

    @Override
    public int update(B2bDeliveryAddressBean b2bDeliveryAddress) {
        trimStringValue(b2bDeliveryAddress);
        return b2bDeliveryAddressDao.updateByPrimaryKeySelective(b2bDeliveryAddress);
    }

    @Override
    public int saveOrUpdateB2bDeliveryAddressBean(B2bDeliveryAddressBean b2bDeliveryAddress) {
        if (null == b2bDeliveryAddress.getDeliveryAddressId() ||
		"" == b2bDeliveryAddress.getDeliveryAddressId()) {
            return saveB2bDeliveryAddressBean(b2bDeliveryAddress);
        } else {
            return update(b2bDeliveryAddress);
        }
    }

    @Override
    public B2bDeliveryAddressBean getB2bDeliveryAddressBean(String deliveryAddressId) {
        return b2bDeliveryAddressDao.selectByPrimaryKey(deliveryAddressId);
    }

    @Override
    public List<B2bDeliveryAddressBean> getAll() {
        return b2bDeliveryAddressDao.selectAll();
    }

    @Override
    public void delete(String deliveryAddressId) {
         b2bDeliveryAddressDao.deleteByPrimaryKey(deliveryAddressId);
    }

    public List<B2bDeliveryAddressBean> queryB2bDeliveryAddressBean
	(B2bDeliveryAddressBean b2bDeliveryAddress, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bDeliveryAddress);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bDeliveryAddressDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bDeliveryAddressDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bDeliveryAddressDao.update(sql, args);
    }

    public List<B2bDeliveryAddressBean> queryB2bDeliveryAddressBean
	(B2bDeliveryAddressBean b2bDeliveryAddress){
	SQLParam sqlParam=getWhereSQL(b2bDeliveryAddress);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bDeliveryAddressDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bDeliveryAddressBean b2bDeliveryAddress) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String region = b2bDeliveryAddress.getRegion();
        if (StringUtils.isNotBlank(region) ) {
           sqlBuffer.append("region=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getRegion());
        }
      
            
        String receiveName = b2bDeliveryAddress.getReceiveName();
        if (StringUtils.isNotBlank(receiveName) ) {
           sqlBuffer.append("receiveName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getReceiveName());
        }
      
            
        String receivePhone = b2bDeliveryAddress.getReceivePhone();
        if (StringUtils.isNotBlank(receivePhone) ) {
           sqlBuffer.append("receivePhone=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getReceivePhone());
        }
      
            
	  = b2bDeliveryAddress.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.get());
        } 
      
            
        String detailAddress = b2bDeliveryAddress.getDetailAddress();
        if (StringUtils.isNotBlank(detailAddress) ) {
           sqlBuffer.append("detailAddress=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getDetailAddress());
        }
      
            
        String receiveTellcall = b2bDeliveryAddress.getReceiveTellcall();
        if (StringUtils.isNotBlank(receiveTellcall) ) {
           sqlBuffer.append("receiveTellcall=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getReceiveTellcall());
        }
      
            
	  = b2bDeliveryAddress.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.get());
        } 
      
            
	  = b2bDeliveryAddress.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.get());
        } 
      
            
        String supplierId = b2bDeliveryAddress.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getSupplierId());
        }
      
            
        String deliveryAddressId = b2bDeliveryAddress.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) ) {
           sqlBuffer.append("deliveryAddressId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getDeliveryAddressId());
        }
      
            
        String remake = b2bDeliveryAddress.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getRemake());
        }
      
            
        String supplierName = b2bDeliveryAddress.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getSupplierName());
        }
      
            
        String isDefault = b2bDeliveryAddress.getIsDefault();
        if (StringUtils.isNotBlank(isDefault) ) {
           sqlBuffer.append("isDefault=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getIsDefault());
        }
      
            
        String postCode = b2bDeliveryAddress.getPostCode();
        if (StringUtils.isNotBlank(postCode) ) {
           sqlBuffer.append("postCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.getPostCode());
        }
      
            
	  = b2bDeliveryAddress.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bDeliveryAddress.get());
        } 
      
            
	  = b2bDeliveryAddress.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    
	     param.add(b2bDeliveryAddress.get());
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
    public B2bDeliveryAddressBean trimStringValue(B2bDeliveryAddressBean b2bDeliveryAddress) {
        String region = b2bDeliveryAddress.getRegion();
        if (StringUtils.isNotBlank(region) && region.length() > 255) {
            b2bDeliveryAddress.setRegion(region.substring(0, 255));
        }

        String receiveName = b2bDeliveryAddress.getReceiveName();
        if (StringUtils.isNotBlank(receiveName) && receiveName.length() > 36) {
            b2bDeliveryAddress.setReceiveName(receiveName.substring(0, 36));
        }

        String receivePhone = b2bDeliveryAddress.getReceivePhone();
        if (StringUtils.isNotBlank(receivePhone) && receivePhone.length() > 11) {
            b2bDeliveryAddress.setReceivePhone(receivePhone.substring(0, 11));
        }

        String detailAddress = b2bDeliveryAddress.getDetailAddress();
        if (StringUtils.isNotBlank(detailAddress) && detailAddress.length() > 255) {
            b2bDeliveryAddress.setDetailAddress(detailAddress.substring(0, 255));
        }

        String receiveTellcall = b2bDeliveryAddress.getReceiveTellcall();
        if (StringUtils.isNotBlank(receiveTellcall) && receiveTellcall.length() > 12) {
            b2bDeliveryAddress.setReceiveTellcall(receiveTellcall.substring(0, 12));
        }

        String supplierId = b2bDeliveryAddress.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bDeliveryAddress.setSupplierId(supplierId.substring(0, 36));
        }

        String deliveryAddressId = b2bDeliveryAddress.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) && deliveryAddressId.length() > 36) {
            b2bDeliveryAddress.setDeliveryAddressId(deliveryAddressId.substring(0, 36));
        }

        String remake = b2bDeliveryAddress.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bDeliveryAddress.setRemake(remake.substring(0, 255));
        }

        String supplierName = b2bDeliveryAddress.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bDeliveryAddress.setSupplierName(supplierName.substring(0, 255));
        }

        String isDefault = b2bDeliveryAddress.getIsDefault();
        if (StringUtils.isNotBlank(isDefault) && isDefault.length() > 2) {
            b2bDeliveryAddress.setIsDefault(isDefault.substring(0, 2));
        }

        String postCode = b2bDeliveryAddress.getPostCode();
        if (StringUtils.isNotBlank(postCode) && postCode.length() > 36) {
            b2bDeliveryAddress.setPostCode(postCode.substring(0, 36));
        }

        return b2bDeliveryAddress;
    }
}
