package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderRmtDetailBean;
import com.ndlan.g2.b2b.dao.B2bOrderRmtDetailDao;

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
public class B2bOrderRmtDetailAtomServiceTests  {

    @Autowired
    protected B2bOrderRmtDetailAtomServiceImpl b2bOrderRmtDetailAtomService;

    @Test
    public void save(B2bOrderRmtDetailBean b2bOrderRmtDetail) {

        return b2bOrderRmtDetailDao.insertSelective(b2bOrderRmtDetail);
    }

    @Test
    public void saveAndGetId(B2bOrderRmtDetailBean b2bOrderRmtDetail) {

         b2bOrderRmtDetailAtomService.insertSelectiveAndGetId(b2bOrderRmtDetail);
    }

    @Test
    public void update(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        trimStringValue(b2bOrderRmtDetail);
        return b2bOrderRmtDetailAtomService.updateByPrimaryKeySelective(b2bOrderRmtDetail);
    }

    @Test
    public void saveOrUpdate(B2bOrderRmtDetailBean b2bOrderRmtDetail) {
        if (null == b2bOrderRmtDetail.getId() || 0 == b2bOrderRmtDetail.getId()) {
            return save(b2bOrderRmtDetail);
        } else {
            return update(b2bOrderRmtDetail);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderRmtDetailAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderRmtDetailAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrderRmtDetail$AtomService.deleteByPrimaryKey(id);
    }

}
