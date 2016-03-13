package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryHeadBean;
import com.ndlan.g2.b2b.dao.B2bPriceCategoryHeadDao;

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
public class B2bPriceCategoryHeadAtomServiceTests  {

    @Autowired
    protected B2bPriceCategoryHeadAtomServiceImpl b2bPriceCategoryHeadAtomService;

    @Test
    public void save(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {

        return b2bPriceCategoryHeadDao.insertSelective(b2bPriceCategoryHead);
    }

    @Test
    public void saveAndGetId(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {

         b2bPriceCategoryHeadAtomService.insertSelectiveAndGetId(b2bPriceCategoryHead);
    }

    @Test
    public void update(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        trimStringValue(b2bPriceCategoryHead);
        return b2bPriceCategoryHeadAtomService.updateByPrimaryKeySelective(b2bPriceCategoryHead);
    }

    @Test
    public void saveOrUpdate(B2bPriceCategoryHeadBean b2bPriceCategoryHead) {
        if (null == b2bPriceCategoryHead.getId() || 0 == b2bPriceCategoryHead.getId()) {
            return save(b2bPriceCategoryHead);
        } else {
            return update(b2bPriceCategoryHead);
        }
    }

    @Test
    public void get(Long id) {
         b2bPriceCategoryHeadAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bPriceCategoryHeadAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bPriceCategoryHead$AtomService.deleteByPrimaryKey(id);
    }

}
