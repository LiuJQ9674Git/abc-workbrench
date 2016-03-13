package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderDeliveryBean;
import com.ndlan.g2.b2b.dao.B2bOrderDeliveryDao;

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
public class B2bOrderDeliveryAtomServiceTests  {

    @Autowired
    protected B2bOrderDeliveryAtomServiceImpl b2bOrderDeliveryAtomService;

    @Test
    public void save(B2bOrderDeliveryBean b2bOrderDelivery) {

        return b2bOrderDeliveryDao.insertSelective(b2bOrderDelivery);
    }

    @Test
    public void saveAndGetId(B2bOrderDeliveryBean b2bOrderDelivery) {

         b2bOrderDeliveryAtomService.insertSelectiveAndGetId(b2bOrderDelivery);
    }

    @Test
    public void update(B2bOrderDeliveryBean b2bOrderDelivery) {
        trimStringValue(b2bOrderDelivery);
        return b2bOrderDeliveryAtomService.updateByPrimaryKeySelective(b2bOrderDelivery);
    }

    @Test
    public void saveOrUpdate(B2bOrderDeliveryBean b2bOrderDelivery) {
        if (null == b2bOrderDelivery.getId() || 0 == b2bOrderDelivery.getId()) {
            return save(b2bOrderDelivery);
        } else {
            return update(b2bOrderDelivery);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderDeliveryAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderDeliveryAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrderDelivery$AtomService.deleteByPrimaryKey(id);
    }

}
