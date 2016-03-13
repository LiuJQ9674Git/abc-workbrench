package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderBean;
import com.ndlan.g2.b2b.dao.B2bOrderDao;

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
public class B2bOrderAtomServiceTests  {

    @Autowired
    protected B2bOrderAtomServiceImpl b2bOrderAtomService;

    @Test
    public void save(B2bOrderBean b2bOrder) {

        return b2bOrderDao.insertSelective(b2bOrder);
    }

    @Test
    public void saveAndGetId(B2bOrderBean b2bOrder) {

         b2bOrderAtomService.insertSelectiveAndGetId(b2bOrder);
    }

    @Test
    public void update(B2bOrderBean b2bOrder) {
        trimStringValue(b2bOrder);
        return b2bOrderAtomService.updateByPrimaryKeySelective(b2bOrder);
    }

    @Test
    public void saveOrUpdate(B2bOrderBean b2bOrder) {
        if (null == b2bOrder.getId() || 0 == b2bOrder.getId()) {
            return save(b2bOrder);
        } else {
            return update(b2bOrder);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrder$AtomService.deleteByPrimaryKey(id);
    }

}
