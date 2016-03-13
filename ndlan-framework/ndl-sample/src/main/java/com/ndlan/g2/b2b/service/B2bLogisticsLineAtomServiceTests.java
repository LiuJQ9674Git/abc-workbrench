package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsLineBean;
import com.ndlan.g2.b2b.dao.B2bLogisticsLineDao;

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
public class B2bLogisticsLineAtomServiceTests  {

    @Autowired
    protected B2bLogisticsLineAtomServiceImpl b2bLogisticsLineAtomService;

    @Test
    public void save(B2bLogisticsLineBean b2bLogisticsLine) {

        return b2bLogisticsLineDao.insertSelective(b2bLogisticsLine);
    }

    @Test
    public void saveAndGetId(B2bLogisticsLineBean b2bLogisticsLine) {

         b2bLogisticsLineAtomService.insertSelectiveAndGetId(b2bLogisticsLine);
    }

    @Test
    public void update(B2bLogisticsLineBean b2bLogisticsLine) {
        trimStringValue(b2bLogisticsLine);
        return b2bLogisticsLineAtomService.updateByPrimaryKeySelective(b2bLogisticsLine);
    }

    @Test
    public void saveOrUpdate(B2bLogisticsLineBean b2bLogisticsLine) {
        if (null == b2bLogisticsLine.getId() || 0 == b2bLogisticsLine.getId()) {
            return save(b2bLogisticsLine);
        } else {
            return update(b2bLogisticsLine);
        }
    }

    @Test
    public void get(Long id) {
         b2bLogisticsLineAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bLogisticsLineAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bLogisticsLine$AtomService.deleteByPrimaryKey(id);
    }

}
