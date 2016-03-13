package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartDetailBean;
import com.ndlan.g2.b2b.dao.B2bShoppingCartDetailDao;

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
public class B2bShoppingCartDetailAtomServiceTests  {

    @Autowired
    protected B2bShoppingCartDetailAtomServiceImpl b2bShoppingCartDetailAtomService;

    @Test
    public void save(B2bShoppingCartDetailBean b2bShoppingCartDetail) {

        return b2bShoppingCartDetailDao.insertSelective(b2bShoppingCartDetail);
    }

    @Test
    public void saveAndGetId(B2bShoppingCartDetailBean b2bShoppingCartDetail) {

         b2bShoppingCartDetailAtomService.insertSelectiveAndGetId(b2bShoppingCartDetail);
    }

    @Test
    public void update(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        trimStringValue(b2bShoppingCartDetail);
        return b2bShoppingCartDetailAtomService.updateByPrimaryKeySelective(b2bShoppingCartDetail);
    }

    @Test
    public void saveOrUpdate(B2bShoppingCartDetailBean b2bShoppingCartDetail) {
        if (null == b2bShoppingCartDetail.getId() || 0 == b2bShoppingCartDetail.getId()) {
            return save(b2bShoppingCartDetail);
        } else {
            return update(b2bShoppingCartDetail);
        }
    }

    @Test
    public void get(Long id) {
         b2bShoppingCartDetailAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bShoppingCartDetailAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bShoppingCartDetail$AtomService.deleteByPrimaryKey(id);
    }

}
