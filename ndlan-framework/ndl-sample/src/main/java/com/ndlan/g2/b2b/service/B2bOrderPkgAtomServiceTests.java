package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderPkgBean;
import com.ndlan.g2.b2b.dao.B2bOrderPkgDao;

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
public class B2bOrderPkgAtomServiceTests  {

    @Autowired
    protected B2bOrderPkgAtomServiceImpl b2bOrderPkgAtomService;

    @Test
    public void save(B2bOrderPkgBean b2bOrderPkg) {

        return b2bOrderPkgDao.insertSelective(b2bOrderPkg);
    }

    @Test
    public void saveAndGetId(B2bOrderPkgBean b2bOrderPkg) {

         b2bOrderPkgAtomService.insertSelectiveAndGetId(b2bOrderPkg);
    }

    @Test
    public void update(B2bOrderPkgBean b2bOrderPkg) {
        trimStringValue(b2bOrderPkg);
        return b2bOrderPkgAtomService.updateByPrimaryKeySelective(b2bOrderPkg);
    }

    @Test
    public void saveOrUpdate(B2bOrderPkgBean b2bOrderPkg) {
        if (null == b2bOrderPkg.getId() || 0 == b2bOrderPkg.getId()) {
            return save(b2bOrderPkg);
        } else {
            return update(b2bOrderPkg);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderPkgAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderPkgAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrderPkg$AtomService.deleteByPrimaryKey(id);
    }

}
