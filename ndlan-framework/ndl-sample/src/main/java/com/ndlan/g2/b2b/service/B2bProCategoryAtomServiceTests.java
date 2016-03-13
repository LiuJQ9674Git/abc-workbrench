package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProCategoryBean;
import com.ndlan.g2.b2b.dao.B2bProCategoryDao;

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
public class B2bProCategoryAtomServiceTests  {

    @Autowired
    protected B2bProCategoryAtomServiceImpl b2bProCategoryAtomService;

    @Test
    public void save(B2bProCategoryBean b2bProCategory) {

        return b2bProCategoryDao.insertSelective(b2bProCategory);
    }

    @Test
    public void saveAndGetId(B2bProCategoryBean b2bProCategory) {

         b2bProCategoryAtomService.insertSelectiveAndGetId(b2bProCategory);
    }

    @Test
    public void update(B2bProCategoryBean b2bProCategory) {
        trimStringValue(b2bProCategory);
        return b2bProCategoryAtomService.updateByPrimaryKeySelective(b2bProCategory);
    }

    @Test
    public void saveOrUpdate(B2bProCategoryBean b2bProCategory) {
        if (null == b2bProCategory.getId() || 0 == b2bProCategory.getId()) {
            return save(b2bProCategory);
        } else {
            return update(b2bProCategory);
        }
    }

    @Test
    public void get(Long id) {
         b2bProCategoryAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bProCategoryAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bProCategory$AtomService.deleteByPrimaryKey(id);
    }

}
