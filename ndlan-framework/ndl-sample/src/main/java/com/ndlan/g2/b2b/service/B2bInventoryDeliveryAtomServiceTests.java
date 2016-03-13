package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryDeliveryBean;
import com.ndlan.g2.b2b.dao.B2bInventoryDeliveryDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml"})
public class B2bInventoryDeliveryAtomServiceTests  {

    @Autowired
    protected B2bInventoryDeliveryAtomServiceImpl b2bInventoryDeliveryAtomService;

    @Test
    public void save(B2bInventoryDeliveryBean b2bInventoryDelivery) {

        return b2bInventoryDeliveryDao.insertSelective(b2bInventoryDelivery);
    }

    @Test
    public void saveAndGetId(B2bInventoryDeliveryBean b2bInventoryDelivery) {

         b2bInventoryDeliveryAtomService.insertSelectiveAndGetId(b2bInventoryDelivery);
    }

    @Test
    public void update(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        trimStringValue(b2bInventoryDelivery);
        return b2bInventoryDeliveryAtomService.updateByPrimaryKeySelective(b2bInventoryDelivery);
    }

    @Test
    public void saveOrUpdate(B2bInventoryDeliveryBean b2bInventoryDelivery) {
        if (null == b2bInventoryDelivery.getId() || 0 == b2bInventoryDelivery.getId()) {
            return save(b2bInventoryDelivery);
        } else {
            return update(b2bInventoryDelivery);
        }
    }

    @Test
    public void get(Long id) {
         b2bInventoryDeliveryAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bInventoryDeliveryAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bInventoryDelivery$AtomService.deleteByPrimaryKey(id);
    }

}
