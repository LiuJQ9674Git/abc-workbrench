package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderStatusHisBean;
import com.ndlan.g2.b2b.dao.B2bOrderStatusHisDao;

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
public class B2bOrderStatusHisAtomServiceTests  {

    @Autowired
    protected B2bOrderStatusHisAtomServiceImpl b2bOrderStatusHisAtomService;

    @Test
    public void save(B2bOrderStatusHisBean b2bOrderStatusHis) {

        return b2bOrderStatusHisDao.insertSelective(b2bOrderStatusHis);
    }

    @Test
    public void saveAndGetId(B2bOrderStatusHisBean b2bOrderStatusHis) {

         b2bOrderStatusHisAtomService.insertSelectiveAndGetId(b2bOrderStatusHis);
    }

    @Test
    public void update(B2bOrderStatusHisBean b2bOrderStatusHis) {
        trimStringValue(b2bOrderStatusHis);
        return b2bOrderStatusHisAtomService.updateByPrimaryKeySelective(b2bOrderStatusHis);
    }

    @Test
    public void saveOrUpdate(B2bOrderStatusHisBean b2bOrderStatusHis) {
        if (null == b2bOrderStatusHis.getId() || 0 == b2bOrderStatusHis.getId()) {
            return save(b2bOrderStatusHis);
        } else {
            return update(b2bOrderStatusHis);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderStatusHisAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderStatusHisAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrderStatusHis$AtomService.deleteByPrimaryKey(id);
    }

}
