package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bOrderRmtPkgBean;
import com.ndlan.g2.b2b.dao.B2bOrderRmtPkgDao;

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
public class B2bOrderRmtPkgAtomServiceTests  {

    @Autowired
    protected B2bOrderRmtPkgAtomServiceImpl b2bOrderRmtPkgAtomService;

    @Test
    public void save(B2bOrderRmtPkgBean b2bOrderRmtPkg) {

        return b2bOrderRmtPkgDao.insertSelective(b2bOrderRmtPkg);
    }

    @Test
    public void saveAndGetId(B2bOrderRmtPkgBean b2bOrderRmtPkg) {

         b2bOrderRmtPkgAtomService.insertSelectiveAndGetId(b2bOrderRmtPkg);
    }

    @Test
    public void update(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        trimStringValue(b2bOrderRmtPkg);
        return b2bOrderRmtPkgAtomService.updateByPrimaryKeySelective(b2bOrderRmtPkg);
    }

    @Test
    public void saveOrUpdate(B2bOrderRmtPkgBean b2bOrderRmtPkg) {
        if (null == b2bOrderRmtPkg.getId() || 0 == b2bOrderRmtPkg.getId()) {
            return save(b2bOrderRmtPkg);
        } else {
            return update(b2bOrderRmtPkg);
        }
    }

    @Test
    public void get(Long id) {
         b2bOrderRmtPkgAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bOrderRmtPkgAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bOrderRmtPkg$AtomService.deleteByPrimaryKey(id);
    }

}
