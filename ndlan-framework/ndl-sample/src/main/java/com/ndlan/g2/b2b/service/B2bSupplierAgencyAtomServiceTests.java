package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bSupplierAgencyBean;
import com.ndlan.g2.b2b.dao.B2bSupplierAgencyDao;

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
public class B2bSupplierAgencyAtomServiceTests  {

    @Autowired
    protected B2bSupplierAgencyAtomServiceImpl b2bSupplierAgencyAtomService;

    @Test
    public void save(B2bSupplierAgencyBean b2bSupplierAgency) {

        return b2bSupplierAgencyDao.insertSelective(b2bSupplierAgency);
    }

    @Test
    public void saveAndGetId(B2bSupplierAgencyBean b2bSupplierAgency) {

         b2bSupplierAgencyAtomService.insertSelectiveAndGetId(b2bSupplierAgency);
    }

    @Test
    public void update(B2bSupplierAgencyBean b2bSupplierAgency) {
        trimStringValue(b2bSupplierAgency);
        return b2bSupplierAgencyAtomService.updateByPrimaryKeySelective(b2bSupplierAgency);
    }

    @Test
    public void saveOrUpdate(B2bSupplierAgencyBean b2bSupplierAgency) {
        if (null == b2bSupplierAgency.getId() || 0 == b2bSupplierAgency.getId()) {
            return save(b2bSupplierAgency);
        } else {
            return update(b2bSupplierAgency);
        }
    }

    @Test
    public void get(Long id) {
         b2bSupplierAgencyAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bSupplierAgencyAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bSupplierAgency$AtomService.deleteByPrimaryKey(id);
    }

}
