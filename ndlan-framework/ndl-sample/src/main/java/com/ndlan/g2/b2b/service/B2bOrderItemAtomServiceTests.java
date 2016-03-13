package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderItemBean;
import com.ndlan.g2.b2b.dao.B2bOrderItemDao;

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
public class B2bOrderItemAtomServiceTests  {

    @Autowired
    protected B2bOrderItemAtomServiceImpl b2bOrderItemAtomService;

    @Test
    public void save(B2bOrderItemBean b2bOrderItem) {

        return b2bOrderItemDao.insertSelective(b2bOrderItem);
    }

    @Test
    public void saveAndGetId(B2bOrderItemBean b2bOrderItem) {

         b2bOrderItemAtomService.insertSelectiveAndGetId(b2bOrderItem);
    }

    @Test
    public void update(B2bOrderItemBean b2bOrderItem) {
        trimStringValue(b2bOrderItem);
        return b2bOrderItemAtomService.updateByPrimaryKeySelective(b2bOrderItem);
    }

    @Test
    public void saveOrUpdate(B2bOrderItemBean b2bOrderItem) {
        if (null == b2bOrderItem.getId() || 0 == b2bOrderItem.getId()) {
            return save(b2bOrderItem);
        } else {
            return update(b2bOrderItem);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderItemAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderItemAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrderItem$AtomService.deleteByPrimaryKey(id);
    }

}
