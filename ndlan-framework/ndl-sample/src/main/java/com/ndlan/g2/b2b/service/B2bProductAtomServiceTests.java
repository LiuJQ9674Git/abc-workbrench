package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductBean;
import com.ndlan.g2.b2b.dao.B2bProductDao;

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
public class B2bProductAtomServiceTests  {

    @Autowired
    protected B2bProductAtomServiceImpl b2bProductAtomService;

    @Test
    public void save(B2bProductBean b2bProduct) {

        return b2bProductDao.insertSelective(b2bProduct);
    }

    @Test
    public void saveAndGetId(B2bProductBean b2bProduct) {

         b2bProductAtomService.insertSelectiveAndGetId(b2bProduct);
    }

    @Test
    public void update(B2bProductBean b2bProduct) {
        trimStringValue(b2bProduct);
        return b2bProductAtomService.updateByPrimaryKeySelective(b2bProduct);
    }

    @Test
    public void saveOrUpdate(B2bProductBean b2bProduct) {
        if (null == b2bProduct.getId() || 0 == b2bProduct.getId()) {
            return save(b2bProduct);
        } else {
            return update(b2bProduct);
        }
    }

    @Test
    public void get(Long id) {
         b2bProductAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bProductAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bProduct$AtomService.deleteByPrimaryKey(id);
    }

}
