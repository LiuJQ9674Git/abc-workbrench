package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bDeliveryAddressBean;
import com.ndlan.g2.b2b.dao.B2bDeliveryAddressDao;

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
public class B2bDeliveryAddressAtomServiceTests  {

    @Autowired
    protected B2bDeliveryAddressAtomServiceImpl b2bDeliveryAddressAtomService;

    @Test
    public void save(B2bDeliveryAddressBean b2bDeliveryAddress) {

        return b2bDeliveryAddressDao.insertSelective(b2bDeliveryAddress);
    }

    @Test
    public void saveAndGetId(B2bDeliveryAddressBean b2bDeliveryAddress) {

         b2bDeliveryAddressAtomService.insertSelectiveAndGetId(b2bDeliveryAddress);
    }

    @Test
    public void update(B2bDeliveryAddressBean b2bDeliveryAddress) {
        trimStringValue(b2bDeliveryAddress);
        return b2bDeliveryAddressAtomService.updateByPrimaryKeySelective(b2bDeliveryAddress);
    }

    @Test
    public void saveOrUpdate(B2bDeliveryAddressBean b2bDeliveryAddress) {
        if (null == b2bDeliveryAddress.getId() || 0 == b2bDeliveryAddress.getId()) {
            return save(b2bDeliveryAddress);
        } else {
            return update(b2bDeliveryAddress);
        }
    }

    @Test
    public void get(Long id) {
         b2bDeliveryAddressAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bDeliveryAddressAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bDeliveryAddress$AtomService.deleteByPrimaryKey(id);
    }

}
