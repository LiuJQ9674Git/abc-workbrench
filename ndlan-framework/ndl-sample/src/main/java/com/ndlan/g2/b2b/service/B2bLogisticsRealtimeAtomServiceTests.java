package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bLogisticsRealtimeBean;
import com.ndlan.g2.b2b.dao.B2bLogisticsRealtimeDao;

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
public class B2bLogisticsRealtimeAtomServiceTests  {

    @Autowired
    protected B2bLogisticsRealtimeAtomServiceImpl b2bLogisticsRealtimeAtomService;

    @Test
    public void save(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {

        return b2bLogisticsRealtimeDao.insertSelective(b2bLogisticsRealtime);
    }

    @Test
    public void saveAndGetId(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {

         b2bLogisticsRealtimeAtomService.insertSelectiveAndGetId(b2bLogisticsRealtime);
    }

    @Test
    public void update(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        trimStringValue(b2bLogisticsRealtime);
        return b2bLogisticsRealtimeAtomService.updateByPrimaryKeySelective(b2bLogisticsRealtime);
    }

    @Test
    public void saveOrUpdate(B2bLogisticsRealtimeBean b2bLogisticsRealtime) {
        if (null == b2bLogisticsRealtime.getId() || 0 == b2bLogisticsRealtime.getId()) {
            return save(b2bLogisticsRealtime);
        } else {
            return update(b2bLogisticsRealtime);
        }
    }

    @Test
    public void get(Long id) {
         b2bLogisticsRealtimeAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bLogisticsRealtimeAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bLogisticsRealtime$AtomService.deleteByPrimaryKey(id);
    }

}
