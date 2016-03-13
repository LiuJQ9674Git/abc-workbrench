package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryHeadBean;
import com.ndlan.g2.b2b.dao.B2bInventoryHeadDao;

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
public class B2bInventoryHeadAtomServiceTests  {

    @Autowired
    protected B2bInventoryHeadAtomServiceImpl b2bInventoryHeadAtomService;

    @Test
    public void save(B2bInventoryHeadBean b2bInventoryHead) {

        return b2bInventoryHeadDao.insertSelective(b2bInventoryHead);
    }

    @Test
    public void saveAndGetId(B2bInventoryHeadBean b2bInventoryHead) {

         b2bInventoryHeadAtomService.insertSelectiveAndGetId(b2bInventoryHead);
    }

    @Test
    public void update(B2bInventoryHeadBean b2bInventoryHead) {
        trimStringValue(b2bInventoryHead);
        return b2bInventoryHeadAtomService.updateByPrimaryKeySelective(b2bInventoryHead);
    }

    @Test
    public void saveOrUpdate(B2bInventoryHeadBean b2bInventoryHead) {
        if (null == b2bInventoryHead.getId() || 0 == b2bInventoryHead.getId()) {
            return save(b2bInventoryHead);
        } else {
            return update(b2bInventoryHead);
        }
    }

    @Test
    public void get(Long id) {
         b2bInventoryHeadAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bInventoryHeadAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bInventoryHead$AtomService.deleteByPrimaryKey(id);
    }

}
