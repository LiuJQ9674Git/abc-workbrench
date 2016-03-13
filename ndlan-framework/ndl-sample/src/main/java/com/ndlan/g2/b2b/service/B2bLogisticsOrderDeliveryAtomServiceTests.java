package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsOrderDeliveryBean;
import com.ndlan.g2.b2b.dao.B2bLogisticsOrderDeliveryDao;

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
public class B2bLogisticsOrderDeliveryAtomServiceTests  {

    @Autowired
    protected B2bLogisticsOrderDeliveryAtomServiceImpl b2bLogisticsOrderDeliveryAtomService;

    @Test
    public void save(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {

        return b2bLogisticsOrderDeliveryDao.insertSelective(b2bLogisticsOrderDelivery);
    }

    @Test
    public void saveAndGetId(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {

         b2bLogisticsOrderDeliveryAtomService.insertSelectiveAndGetId(b2bLogisticsOrderDelivery);
    }

    @Test
    public void update(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        trimStringValue(b2bLogisticsOrderDelivery);
        return b2bLogisticsOrderDeliveryAtomService.updateByPrimaryKeySelective(b2bLogisticsOrderDelivery);
    }

    @Test
    public void saveOrUpdate(B2bLogisticsOrderDeliveryBean b2bLogisticsOrderDelivery) {
        if (null == b2bLogisticsOrderDelivery.getId() || 0 == b2bLogisticsOrderDelivery.getId()) {
            return save(b2bLogisticsOrderDelivery);
        } else {
            return update(b2bLogisticsOrderDelivery);
        }
    }

    @Test
    public void get(Long id) {
         b2bLogisticsOrderDeliveryAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bLogisticsOrderDeliveryAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bLogisticsOrderDelivery$AtomService.deleteByPrimaryKey(id);
    }

}
