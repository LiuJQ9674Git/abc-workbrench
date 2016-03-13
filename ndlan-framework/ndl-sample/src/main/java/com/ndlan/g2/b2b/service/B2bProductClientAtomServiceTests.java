package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductClientBean;
import com.ndlan.g2.b2b.dao.B2bProductClientDao;

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
public class B2bProductClientAtomServiceTests  {

    @Autowired
    protected B2bProductClientAtomServiceImpl b2bProductClientAtomService;

    @Test
    public void save(B2bProductClientBean b2bProductClient) {

        return b2bProductClientDao.insertSelective(b2bProductClient);
    }

    @Test
    public void saveAndGetId(B2bProductClientBean b2bProductClient) {

         b2bProductClientAtomService.insertSelectiveAndGetId(b2bProductClient);
    }

    @Test
    public void update(B2bProductClientBean b2bProductClient) {
        trimStringValue(b2bProductClient);
        return b2bProductClientAtomService.updateByPrimaryKeySelective(b2bProductClient);
    }

    @Test
    public void saveOrUpdate(B2bProductClientBean b2bProductClient) {
        if (null == b2bProductClient.getId() || 0 == b2bProductClient.getId()) {
            return save(b2bProductClient);
        } else {
            return update(b2bProductClient);
        }
    }

    @Test
    public void get(Long id) {
         b2bProductClientAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bProductClientAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bProductClient$AtomService.deleteByPrimaryKey(id);
    }

}
