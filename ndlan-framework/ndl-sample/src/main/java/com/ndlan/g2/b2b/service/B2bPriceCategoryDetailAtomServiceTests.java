package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailBean;
import com.ndlan.g2.b2b.dao.B2bPriceCategoryDetailDao;

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
public class B2bPriceCategoryDetailAtomServiceTests  {

    @Autowired
    protected B2bPriceCategoryDetailAtomServiceImpl b2bPriceCategoryDetailAtomService;

    @Test
    public void save(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {

        return b2bPriceCategoryDetailDao.insertSelective(b2bPriceCategoryDetail);
    }

    @Test
    public void saveAndGetId(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {

         b2bPriceCategoryDetailAtomService.insertSelectiveAndGetId(b2bPriceCategoryDetail);
    }

    @Test
    public void update(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        trimStringValue(b2bPriceCategoryDetail);
        return b2bPriceCategoryDetailAtomService.updateByPrimaryKeySelective(b2bPriceCategoryDetail);
    }

    @Test
    public void saveOrUpdate(B2bPriceCategoryDetailBean b2bPriceCategoryDetail) {
        if (null == b2bPriceCategoryDetail.getId() || 0 == b2bPriceCategoryDetail.getId()) {
            return save(b2bPriceCategoryDetail);
        } else {
            return update(b2bPriceCategoryDetail);
        }
    }

    @Test
    public void get(Long id) {
         b2bPriceCategoryDetailAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bPriceCategoryDetailAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bPriceCategoryDetail$AtomService.deleteByPrimaryKey(id);
    }

}
