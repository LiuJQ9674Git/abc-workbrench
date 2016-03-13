package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderDeliveryBean;
import com.ndlan.g2.b2b.dao.B2bOrderDeliveryDao;

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
@Component("b2bOrderDeliveryAtomService")
public class B2bOrderDeliveryAtomServiceImpl  extends BaseService<B2bOrderDeliveryDao, 
	B2bOrderDeliveryBean>      implements B2bOrderDeliveryAtomService {

    @Resource(name="b2bOrderDeliveryDao")
    protected B2bOrderDeliveryDao b2bOrderDeliveryDao;

    @Override
    public int saveB2bOrderDeliveryBean(B2bOrderDeliveryBean b2bOrderDelivery) {
        trimStringValue(b2bOrderDelivery);
        return b2bOrderDeliveryDao.insertSelective(b2bOrderDelivery);
    }

    @Override
    public int saveAndGetId(B2bOrderDeliveryBean b2bOrderDelivery) {
        trimStringValue(b2bOrderDelivery);
        return b2bOrderDeliveryDao.insertSelectiveAndGetId(b2bOrderDelivery);
    }

    @Override
    public int update(B2bOrderDeliveryBean b2bOrderDelivery) {
        trimStringValue(b2bOrderDelivery);
        return b2bOrderDeliveryDao.updateByPrimaryKeySelective(b2bOrderDelivery);
    }

    @Override
    public int saveOrUpdateB2bOrderDeliveryBean(B2bOrderDeliveryBean b2bOrderDelivery) {
        if (null == b2bOrderDelivery.getOrderDeliveryId() ||
		"" == b2bOrderDelivery.getOrderDeliveryId()) {
            return saveB2bOrderDeliveryBean(b2bOrderDelivery);
        } else {
            return update(b2bOrderDelivery);
        }
    }

    @Override
    public B2bOrderDeliveryBean getB2bOrderDeliveryBean(String orderDeliveryId) {
        return b2bOrderDeliveryDao.selectByPrimaryKey(orderDeliveryId);
    }

    @Override
    public List<B2bOrderDeliveryBean> getAll() {
        return b2bOrderDeliveryDao.selectAll();
    }

    @Override
    public void delete(String orderDeliveryId) {
         b2bOrderDeliveryDao.deleteByPrimaryKey(orderDeliveryId);
    }

    public List<B2bOrderDeliveryBean> queryB2bOrderDeliveryBean
	(B2bOrderDeliveryBean b2bOrderDelivery, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bOrderDelivery);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bOrderDeliveryDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bOrderDeliveryDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bOrderDeliveryDao.update(sql, args);
    }

    public List<B2bOrderDeliveryBean> queryB2bOrderDeliveryBean
	(B2bOrderDeliveryBean b2bOrderDelivery){
	SQLParam sqlParam=getWhereSQL(b2bOrderDelivery);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bOrderDeliveryDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bOrderDeliveryBean b2bOrderDelivery) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
	  = b2bOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.get());
        } 
      
            
        String deliveryAddressId = b2bOrderDelivery.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) ) {
           sqlBuffer.append("deliveryAddressId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getDeliveryAddressId());
        }
      
            
        String region = b2bOrderDelivery.getRegion();
        if (StringUtils.isNotBlank(region) ) {
           sqlBuffer.append("region=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getRegion());
        }
      
            
        String receiveTellcall = b2bOrderDelivery.getReceiveTellcall();
        if (StringUtils.isNotBlank(receiveTellcall) ) {
           sqlBuffer.append("receiveTellcall=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getReceiveTellcall());
        }
      
            
        String supplierName = b2bOrderDelivery.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getSupplierName());
        }
      
            
        String receiveName = b2bOrderDelivery.getReceiveName();
        if (StringUtils.isNotBlank(receiveName) ) {
           sqlBuffer.append("receiveName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getReceiveName());
        }
      
            
	  = b2bOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.get());
        } 
      
            
	  = b2bOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.get());
        } 
      
            
        String orderDeliveryId = b2bOrderDelivery.getOrderDeliveryId();
        if (StringUtils.isNotBlank(orderDeliveryId) ) {
           sqlBuffer.append("orderDeliveryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getOrderDeliveryId());
        }
      
            
        String customId = b2bOrderDelivery.getCustomId();
        if (StringUtils.isNotBlank(customId) ) {
           sqlBuffer.append("customId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getCustomId());
        }
      
            
        String detailAddress = b2bOrderDelivery.getDetailAddress();
        if (StringUtils.isNotBlank(detailAddress) ) {
           sqlBuffer.append("detailAddress=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getDetailAddress());
        }
      
            
        String supplierId = b2bOrderDelivery.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getSupplierId());
        }
      
            
        String remake = b2bOrderDelivery.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getRemake());
        }
      
            
        String receivePhone = b2bOrderDelivery.getReceivePhone();
        if (StringUtils.isNotBlank(receivePhone) ) {
           sqlBuffer.append("receivePhone=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getReceivePhone());
        }
      
            
        String customName = b2bOrderDelivery.getCustomName();
        if (StringUtils.isNotBlank(customName) ) {
           sqlBuffer.append("customName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getCustomName());
        }
      
            
        String orderPkgId = b2bOrderDelivery.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getOrderPkgId());
        }
      
            
        String orderPkgCode = b2bOrderDelivery.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getOrderPkgCode());
        }
      
            
	  = b2bOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.get());
        } 
      
            
        String postCode = b2bOrderDelivery.getPostCode();
        if (StringUtils.isNotBlank(postCode) ) {
           sqlBuffer.append("postCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bOrderDelivery.getPostCode());
        }
      
            
	  = b2bOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    
	     param.add(b2bOrderDelivery.get());
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
    public B2bOrderDeliveryBean trimStringValue(B2bOrderDeliveryBean b2bOrderDelivery) {
        String deliveryAddressId = b2bOrderDelivery.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) && deliveryAddressId.length() > 36) {
            b2bOrderDelivery.setDeliveryAddressId(deliveryAddressId.substring(0, 36));
        }

        String region = b2bOrderDelivery.getRegion();
        if (StringUtils.isNotBlank(region) && region.length() > 255) {
            b2bOrderDelivery.setRegion(region.substring(0, 255));
        }

        String receiveTellcall = b2bOrderDelivery.getReceiveTellcall();
        if (StringUtils.isNotBlank(receiveTellcall) && receiveTellcall.length() > 12) {
            b2bOrderDelivery.setReceiveTellcall(receiveTellcall.substring(0, 12));
        }

        String supplierName = b2bOrderDelivery.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bOrderDelivery.setSupplierName(supplierName.substring(0, 255));
        }

        String receiveName = b2bOrderDelivery.getReceiveName();
        if (StringUtils.isNotBlank(receiveName) && receiveName.length() > 36) {
            b2bOrderDelivery.setReceiveName(receiveName.substring(0, 36));
        }

        String orderDeliveryId = b2bOrderDelivery.getOrderDeliveryId();
        if (StringUtils.isNotBlank(orderDeliveryId) && orderDeliveryId.length() > 36) {
            b2bOrderDelivery.setOrderDeliveryId(orderDeliveryId.substring(0, 36));
        }

        String customId = b2bOrderDelivery.getCustomId();
        if (StringUtils.isNotBlank(customId) && customId.length() > 36) {
            b2bOrderDelivery.setCustomId(customId.substring(0, 36));
        }

        String detailAddress = b2bOrderDelivery.getDetailAddress();
        if (StringUtils.isNotBlank(detailAddress) && detailAddress.length() > 255) {
            b2bOrderDelivery.setDetailAddress(detailAddress.substring(0, 255));
        }

        String supplierId = b2bOrderDelivery.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bOrderDelivery.setSupplierId(supplierId.substring(0, 36));
        }

        String remake = b2bOrderDelivery.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bOrderDelivery.setRemake(remake.substring(0, 255));
        }

        String receivePhone = b2bOrderDelivery.getReceivePhone();
        if (StringUtils.isNotBlank(receivePhone) && receivePhone.length() > 11) {
            b2bOrderDelivery.setReceivePhone(receivePhone.substring(0, 11));
        }

        String customName = b2bOrderDelivery.getCustomName();
        if (StringUtils.isNotBlank(customName) && customName.length() > 255) {
            b2bOrderDelivery.setCustomName(customName.substring(0, 255));
        }

        String orderPkgId = b2bOrderDelivery.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bOrderDelivery.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String orderPkgCode = b2bOrderDelivery.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bOrderDelivery.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String postCode = b2bOrderDelivery.getPostCode();
        if (StringUtils.isNotBlank(postCode) && postCode.length() > 36) {
            b2bOrderDelivery.setPostCode(postCode.substring(0, 36));
        }

        return b2bOrderDelivery;
    }
}
