package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bSupplierCardAccountBean;
import com.ndlan.g2.b2b.dao.B2bSupplierCardAccountDao;

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
public class B2bSupplierCardAccountAtomServiceTests  {

    @Autowired
    protected B2bSupplierCardAccountAtomServiceImpl b2bSupplierCardAccountAtomService;

    @Test
    public void save(B2bSupplierCardAccountBean b2bSupplierCardAccount) {

        return b2bSupplierCardAccountDao.insertSelective(b2bSupplierCardAccount);
    }

    @Test
    public void saveAndGetId(B2bSupplierCardAccountBean b2bSupplierCardAccount) {

         b2bSupplierCardAccountAtomService.insertSelectiveAndGetId(b2bSupplierCardAccount);
    }

    @Test
    public void update(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        trimStringValue(b2bSupplierCardAccount);
        return b2bSupplierCardAccountAtomService.updateByPrimaryKeySelective(b2bSupplierCardAccount);
    }

    @Test
    public void saveOrUpdate(B2bSupplierCardAccountBean b2bSupplierCardAccount) {
        if (null == b2bSupplierCardAccount.getId() || 0 == b2bSupplierCardAccount.getId()) {
            return save(b2bSupplierCardAccount);
        } else {
            return update(b2bSupplierCardAccount);
        }
    }

    @Test
    public void get(Long id) {
         b2bSupplierCardAccountAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bSupplierCardAccountAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bSupplierCardAccount$AtomService.deleteByPrimaryKey(id);
    }

}
