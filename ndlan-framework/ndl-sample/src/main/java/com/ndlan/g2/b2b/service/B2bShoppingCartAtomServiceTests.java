package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartBean;
import com.ndlan.g2.b2b.dao.B2bShoppingCartDao;

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
public class B2bShoppingCartAtomServiceTests  {

    @Autowired
    protected B2bShoppingCartAtomServiceImpl b2bShoppingCartAtomService;

    @Test
    public void save(B2bShoppingCartBean b2bShoppingCart) {

        return b2bShoppingCartDao.insertSelective(b2bShoppingCart);
    }

    @Test
    public void saveAndGetId(B2bShoppingCartBean b2bShoppingCart) {

         b2bShoppingCartAtomService.insertSelectiveAndGetId(b2bShoppingCart);
    }

    @Test
    public void update(B2bShoppingCartBean b2bShoppingCart) {
        trimStringValue(b2bShoppingCart);
        return b2bShoppingCartAtomService.updateByPrimaryKeySelective(b2bShoppingCart);
    }

    @Test
    public void saveOrUpdate(B2bShoppingCartBean b2bShoppingCart) {
        if (null == b2bShoppingCart.getId() || 0 == b2bShoppingCart.getId()) {
            return save(b2bShoppingCart);
        } else {
            return update(b2bShoppingCart);
        }
    }

    @Test
    public void get(Long id) {
         b2bShoppingCartAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bShoppingCartAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bShoppingCart$AtomService.deleteByPrimaryKey(id);
    }

}
