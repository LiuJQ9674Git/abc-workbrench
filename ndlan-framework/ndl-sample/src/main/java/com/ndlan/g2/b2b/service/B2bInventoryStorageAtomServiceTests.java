package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryStorageBean;
import com.ndlan.g2.b2b.dao.B2bInventoryStorageDao;

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
public class B2bInventoryStorageAtomServiceTests  {

    @Autowired
    protected B2bInventoryStorageAtomServiceImpl b2bInventoryStorageAtomService;

    @Test
    public void save(B2bInventoryStorageBean b2bInventoryStorage) {

        return b2bInventoryStorageDao.insertSelective(b2bInventoryStorage);
    }

    @Test
    public void saveAndGetId(B2bInventoryStorageBean b2bInventoryStorage) {

         b2bInventoryStorageAtomService.insertSelectiveAndGetId(b2bInventoryStorage);
    }

    @Test
    public void update(B2bInventoryStorageBean b2bInventoryStorage) {
        trimStringValue(b2bInventoryStorage);
        return b2bInventoryStorageAtomService.updateByPrimaryKeySelective(b2bInventoryStorage);
    }

    @Test
    public void saveOrUpdate(B2bInventoryStorageBean b2bInventoryStorage) {
        if (null == b2bInventoryStorage.getId() || 0 == b2bInventoryStorage.getId()) {
            return save(b2bInventoryStorage);
        } else {
            return update(b2bInventoryStorage);
        }
    }

    @Test
    public void get(Long id) {
         b2bInventoryStorageAtomService.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         b2bInventoryStorageAtomService.selectAll();
    }

    @Test
    public void delete(Long id) {
         b2bInventoryStorage$AtomService.deleteByPrimaryKey(id);
    }

}
