package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bPriceCategoryDetailHisBean;
import com.ndlan.g2.b2b.dao.B2bPriceCategoryDetailHisDao;

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
public class B2bPriceCategoryDetailHisAtomServiceTests  {

    @Autowired
    protected B2bPriceCategoryDetailHisAtomServiceImpl b2bPriceCategoryDetailHisAtomService;

    @Test
    public void save(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {

        return b2bPriceCategoryDetailHisDao.insertSelective(b2bPriceCategoryDetailHis);
    }

    @Test
    public void saveAndGetId(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {

         b2bPriceCategoryDetailHisAtomService.insertSelectiveAndGetId(b2bPriceCategoryDetailHis);
    }

    @Test
    public void update(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        trimStringValue(b2bPriceCategoryDetailHis);
        return b2bPriceCategoryDetailHisAtomService.updateByPrimaryKeySelective(b2bPriceCategoryDetailHis);
    }

    @Test
    public void saveOrUpdate(B2bPriceCategoryDetailHisBean b2bPriceCategoryDetailHis) {
        if (null == b2bPriceCategoryDetailHis.getId() || 0 == b2bPriceCategoryDetailHis.getId()) {
            return save(b2bPriceCategoryDetailHis);
        } else {
            return update(b2bPriceCategoryDetailHis);
        }
    }

    @Test
    public void get(Long id) {
         b2bPriceCategoryDetailHisAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bPriceCategoryDetailHisAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bPriceCategoryDetailHis$AtomService.deleteByPrimaryKey(id);
    }

}
