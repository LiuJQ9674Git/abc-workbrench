package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsOrderDeliveryBean;
import com.ndlan.g2.b2b.dao.B2bLogisticsOrderDeliveryDao;

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
@Component("b2bLogisticsOrderDeliveryAtomService")
public class B2bLogisticsOrderDeliveryAtomServiceImpl  extends BaseService<B2bLogisticsOrderDeliveryDao, 
	B2bLogisticsOrderDeliveryBean>      implements B2bLogisticsOrderDeliveryAtomService {

    @Resource(name="b2bLogisticsOrderDeliveryDao")
    protected B2bLogisticsOrderDeliveryDao b2bLogisticsOrderDeliveryDao;

    @Override
    public int saveB2bLogisticsOrderDeliveryBean(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        trimStringValue(b2bLogisticsOrderDelivery);
        return b2bLogisticsOrderDeliveryDao.insertSelective(b2bLogisticsOrderDelivery);
    }

    @Override
    public int saveAndGetId(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        trimStringValue(b2bLogisticsOrderDelivery);
        return b2bLogisticsOrderDeliveryDao.insertSelectiveAndGetId(b2bLogisticsOrderDelivery);
    }

    @Override
    public int update(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        trimStringValue(b2bLogisticsOrderDelivery);
        return b2bLogisticsOrderDeliveryDao.updateByPrimaryKeySelective(b2bLogisticsOrderDelivery);
    }

    @Override
    public int saveOrUpdateB2bLogisticsOrderDeliveryBean(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        if (null == b2bLogisticsOrderDelivery.getLgstOrdDlvId() ||
		"" == b2bLogisticsOrderDelivery.getLgstOrdDlvId()) {
            return saveB2bLogisticsOrderDeliveryBean(b2bLogisticsOrderDelivery);
        } else {
            return update(b2bLogisticsOrderDelivery);
        }
    }

    @Override
    public B2bLogisticsOrderDeliveryBean getB2bLogisticsOrderDeliveryBean(String lgstOrdDlvId) {
        return b2bLogisticsOrderDeliveryDao.selectByPrimaryKey(lgstOrdDlvId);
    }

    @Override
    public List<B2bLogisticsOrderDeliveryBean> getAll() {
        return b2bLogisticsOrderDeliveryDao.selectAll();
    }

    @Override
    public void delete(String lgstOrdDlvId) {
         b2bLogisticsOrderDeliveryDao.deleteByPrimaryKey(lgstOrdDlvId);
    }

    public List<B2bLogisticsOrderDeliveryBean> queryB2bLogisticsOrderDeliveryBean
	(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bLogisticsOrderDelivery);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bLogisticsOrderDeliveryDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bLogisticsOrderDeliveryDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bLogisticsOrderDeliveryDao.update(sql, args);
    }

    public List<B2bLogisticsOrderDeliveryBean> queryB2bLogisticsOrderDeliveryBean
	(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery){
	SQLParam sqlParam=getWhereSQL(b2bLogisticsOrderDelivery);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bLogisticsOrderDeliveryDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String orderPkgCode = b2bLogisticsOrderDelivery.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) ) {
           sqlBuffer.append("orderPkgCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getOrderPkgCode());
        }
      
            
        String customName = b2bLogisticsOrderDelivery.getCustomName();
        if (StringUtils.isNotBlank(customName) ) {
           sqlBuffer.append("customName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getCustomName());
        }
      
            
        String goodsType = b2bLogisticsOrderDelivery.getGoodsType();
        if (StringUtils.isNotBlank(goodsType) ) {
           sqlBuffer.append("goodsType=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getGoodsType());
        }
      
            
        String orderPkgId = b2bLogisticsOrderDelivery.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) ) {
           sqlBuffer.append("orderPkgId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getOrderPkgId());
        }
      
            
        String orderAmount = b2bLogisticsOrderDelivery.getOrderAmount();
        if (StringUtils.isNotBlank(orderAmount) ) {
           sqlBuffer.append("orderAmount=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getOrderAmount());
        }
      
            
        String lgstOrdDlvId = b2bLogisticsOrderDelivery.getLgstOrdDlvId();
        if (StringUtils.isNotBlank(lgstOrdDlvId) ) {
           sqlBuffer.append("lgstOrdDlvId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getLgstOrdDlvId());
        }
      
            
        String deliveryAddressId = b2bLogisticsOrderDelivery.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) ) {
           sqlBuffer.append("deliveryAddressId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getDeliveryAddressId());
        }
      
            
        String customId = b2bLogisticsOrderDelivery.getCustomId();
        if (StringUtils.isNotBlank(customId) ) {
           sqlBuffer.append("customId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getCustomId());
        }
      
            
        String remake = b2bLogisticsOrderDelivery.getRemake();
        if (StringUtils.isNotBlank(remake) ) {
           sqlBuffer.append("remake=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getRemake());
        }
      
            
        String logisticsLineId = b2bLogisticsOrderDelivery.getLogisticsLineId();
        if (StringUtils.isNotBlank(logisticsLineId) ) {
           sqlBuffer.append("logisticsLineId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getLogisticsLineId());
        }
      
            
        String logisticsLineName = b2bLogisticsOrderDelivery.getLogisticsLineName();
        if (StringUtils.isNotBlank(logisticsLineName) ) {
           sqlBuffer.append("logisticsLineName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getLogisticsLineName());
        }
      
            
        String postCode = b2bLogisticsOrderDelivery.getPostCode();
        if (StringUtils.isNotBlank(postCode) ) {
           sqlBuffer.append("postCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getPostCode());
        }
      
            
        String receiveTellcall = b2bLogisticsOrderDelivery.getReceiveTellcall();
        if (StringUtils.isNotBlank(receiveTellcall) ) {
           sqlBuffer.append("receiveTellcall=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getReceiveTellcall());
        }
      
            
        String receivePhone = b2bLogisticsOrderDelivery.getReceivePhone();
        if (StringUtils.isNotBlank(receivePhone) ) {
           sqlBuffer.append("receivePhone=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getReceivePhone());
        }
      
            
	  = b2bLogisticsOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.get());
        } 
      
            
	  = b2bLogisticsOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.get());
        } 
      
            
        String orderDeliveryId = b2bLogisticsOrderDelivery.getOrderDeliveryId();
        if (StringUtils.isNotBlank(orderDeliveryId) ) {
           sqlBuffer.append("orderDeliveryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getOrderDeliveryId());
        }
      
            
	  = b2bLogisticsOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.get());
        } 
      
            
        String supplierId = b2bLogisticsOrderDelivery.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) ) {
           sqlBuffer.append("supplierId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getSupplierId());
        }
      
            
	  = b2bLogisticsOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.get());
        } 
      
            
        String region = b2bLogisticsOrderDelivery.getRegion();
        if (StringUtils.isNotBlank(region) ) {
           sqlBuffer.append("region=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getRegion());
        }
      
            
	  = b2bLogisticsOrderDelivery.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.get());
        } 
      
            
	Date orderDate = b2bLogisticsOrderDelivery.getOrderDate();
	if (orderDate!=null  ) {
           sqlBuffer.append("orderDate=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getOrderDate());
        } 
      
            
        String supplierName = b2bLogisticsOrderDelivery.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) ) {
           sqlBuffer.append("supplierName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getSupplierName());
        }
      
            
        String receiveName = b2bLogisticsOrderDelivery.getReceiveName();
        if (StringUtils.isNotBlank(receiveName) ) {
           sqlBuffer.append("receiveName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getReceiveName());
        }
      
            
        String logisticsHeadCode = b2bLogisticsOrderDelivery.getLogisticsHeadCode();
        if (StringUtils.isNotBlank(logisticsHeadCode) ) {
           sqlBuffer.append("logisticsHeadCode=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bLogisticsOrderDelivery.getLogisticsHeadCode());
        }
      
            
        String detailAddress = b2bLogisticsOrderDelivery.getDetailAddress();
        if (StringUtils.isNotBlank(detailAddress) ) {
           sqlBuffer.append("detailAddress=?");
	    
	     param.add(b2bLogisticsOrderDelivery.getDetailAddress());
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
    public B2bLogisticsOrderDeliveryBean trimStringValue(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        String orderPkgCode = b2bLogisticsOrderDelivery.getOrderPkgCode();
        if (StringUtils.isNotBlank(orderPkgCode) && orderPkgCode.length() > 36) {
            b2bLogisticsOrderDelivery.setOrderPkgCode(orderPkgCode.substring(0, 36));
        }

        String customName = b2bLogisticsOrderDelivery.getCustomName();
        if (StringUtils.isNotBlank(customName) && customName.length() > 255) {
            b2bLogisticsOrderDelivery.setCustomName(customName.substring(0, 255));
        }

        String goodsType = b2bLogisticsOrderDelivery.getGoodsType();
        if (StringUtils.isNotBlank(goodsType) && goodsType.length() > 2) {
            b2bLogisticsOrderDelivery.setGoodsType(goodsType.substring(0, 2));
        }

        String orderPkgId = b2bLogisticsOrderDelivery.getOrderPkgId();
        if (StringUtils.isNotBlank(orderPkgId) && orderPkgId.length() > 36) {
            b2bLogisticsOrderDelivery.setOrderPkgId(orderPkgId.substring(0, 36));
        }

        String orderAmount = b2bLogisticsOrderDelivery.getOrderAmount();
        if (StringUtils.isNotBlank(orderAmount) && orderAmount.length() > 100) {
            b2bLogisticsOrderDelivery.setOrderAmount(orderAmount.substring(0, 100));
        }

        String lgstOrdDlvId = b2bLogisticsOrderDelivery.getLgstOrdDlvId();
        if (StringUtils.isNotBlank(lgstOrdDlvId) && lgstOrdDlvId.length() > 36) {
            b2bLogisticsOrderDelivery.setLgstOrdDlvId(lgstOrdDlvId.substring(0, 36));
        }

        String deliveryAddressId = b2bLogisticsOrderDelivery.getDeliveryAddressId();
        if (StringUtils.isNotBlank(deliveryAddressId) && deliveryAddressId.length() > 36) {
            b2bLogisticsOrderDelivery.setDeliveryAddressId(deliveryAddressId.substring(0, 36));
        }

        String customId = b2bLogisticsOrderDelivery.getCustomId();
        if (StringUtils.isNotBlank(customId) && customId.length() > 36) {
            b2bLogisticsOrderDelivery.setCustomId(customId.substring(0, 36));
        }

        String remake = b2bLogisticsOrderDelivery.getRemake();
        if (StringUtils.isNotBlank(remake) && remake.length() > 255) {
            b2bLogisticsOrderDelivery.setRemake(remake.substring(0, 255));
        }

        String logisticsLineId = b2bLogisticsOrderDelivery.getLogisticsLineId();
        if (StringUtils.isNotBlank(logisticsLineId) && logisticsLineId.length() > 36) {
            b2bLogisticsOrderDelivery.setLogisticsLineId(logisticsLineId.substring(0, 36));
        }

        String logisticsLineName = b2bLogisticsOrderDelivery.getLogisticsLineName();
        if (StringUtils.isNotBlank(logisticsLineName) && logisticsLineName.length() > 255) {
            b2bLogisticsOrderDelivery.setLogisticsLineName(logisticsLineName.substring(0, 255));
        }

        String postCode = b2bLogisticsOrderDelivery.getPostCode();
        if (StringUtils.isNotBlank(postCode) && postCode.length() > 36) {
            b2bLogisticsOrderDelivery.setPostCode(postCode.substring(0, 36));
        }

        String receiveTellcall = b2bLogisticsOrderDelivery.getReceiveTellcall();
        if (StringUtils.isNotBlank(receiveTellcall) && receiveTellcall.length() > 12) {
            b2bLogisticsOrderDelivery.setReceiveTellcall(receiveTellcall.substring(0, 12));
        }

        String receivePhone = b2bLogisticsOrderDelivery.getReceivePhone();
        if (StringUtils.isNotBlank(receivePhone) && receivePhone.length() > 11) {
            b2bLogisticsOrderDelivery.setReceivePhone(receivePhone.substring(0, 11));
        }

        String orderDeliveryId = b2bLogisticsOrderDelivery.getOrderDeliveryId();
        if (StringUtils.isNotBlank(orderDeliveryId) && orderDeliveryId.length() > 36) {
            b2bLogisticsOrderDelivery.setOrderDeliveryId(orderDeliveryId.substring(0, 36));
        }

        String supplierId = b2bLogisticsOrderDelivery.getSupplierId();
        if (StringUtils.isNotBlank(supplierId) && supplierId.length() > 36) {
            b2bLogisticsOrderDelivery.setSupplierId(supplierId.substring(0, 36));
        }

        String region = b2bLogisticsOrderDelivery.getRegion();
        if (StringUtils.isNotBlank(region) && region.length() > 255) {
            b2bLogisticsOrderDelivery.setRegion(region.substring(0, 255));
        }

        String supplierName = b2bLogisticsOrderDelivery.getSupplierName();
        if (StringUtils.isNotBlank(supplierName) && supplierName.length() > 255) {
            b2bLogisticsOrderDelivery.setSupplierName(supplierName.substring(0, 255));
        }

        String receiveName = b2bLogisticsOrderDelivery.getReceiveName();
        if (StringUtils.isNotBlank(receiveName) && receiveName.length() > 36) {
            b2bLogisticsOrderDelivery.setReceiveName(receiveName.substring(0, 36));
        }

        String logisticsHeadCode = b2bLogisticsOrderDelivery.getLogisticsHeadCode();
        if (StringUtils.isNotBlank(logisticsHeadCode) && logisticsHeadCode.length() > 36) {
            b2bLogisticsOrderDelivery.setLogisticsHeadCode(logisticsHeadCode.substring(0, 36));
        }

        String detailAddress = b2bLogisticsOrderDelivery.getDetailAddress();
        if (StringUtils.isNotBlank(detailAddress) && detailAddress.length() > 255) {
            b2bLogisticsOrderDelivery.setDetailAddress(detailAddress.substring(0, 255));
        }

        return b2bLogisticsOrderDelivery;
    }
}
