package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bShoppingCartPkgBean;
import com.ndlan.g2.b2b.dao.B2bShoppingCartPkgDao;

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
public class B2bShoppingCartPkgAtomServiceTests  {

    @Autowired
    protected B2bShoppingCartPkgAtomServiceImpl b2bShoppingCartPkgAtomService;

    @Test
    public void save(B2bShoppingCartPkgBean b2bShoppingCartPkg) {

        return b2bShoppingCartPkgDao.insertSelective(b2bShoppingCartPkg);
    }

    @Test
    public void saveAndGetId(B2bShoppingCartPkgBean b2bShoppingCartPkg) {

         b2bShoppingCartPkgAtomService.insertSelectiveAndGetId(b2bShoppingCartPkg);
    }

    @Test
    public void update(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        trimStringValue(b2bShoppingCartPkg);
        return b2bShoppingCartPkgAtomService.updateByPrimaryKeySelective(b2bShoppingCartPkg);
    }

    @Test
    public void saveOrUpdate(B2bShoppingCartPkgBean b2bShoppingCartPkg) {
        if (null == b2bShoppingCartPkg.getId() || 0 == b2bShoppingCartPkg.getId()) {
            return save(b2bShoppingCartPkg);
        } else {
            return update(b2bShoppingCartPkg);
        }
    }

    @Test
    public void get(Long id) {
         b2bShoppingCartPkgAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bShoppingCartPkgAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bShoppingCartPkg$AtomService.deleteByPrimaryKey(id);
    }

}
