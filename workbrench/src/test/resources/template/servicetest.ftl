package ${packageBase}.service;

import ${packageBase}.model.${tableBean.tableNameCapitalized}${pojoSuffix};
import ${packageBase}.dao.${tableBean.tableNameCapitalized}${classPrefix}${daoIntefaceSuffix};

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
public class ${tableBean.tableNameCapitalized}${classPrefix}${serviceIntefaceSuffix}Tests  {

    @Autowired
    protected ${tableBean.tableNameCapitalized}${classPrefix}${serviceSuffix} ${tableBean.tableNameNoDash}${classPrefix}${serviceIntefaceSuffix};

    @Test
    public void save(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash}) {

        return ${tableBean.tableNameNoDash}${classPrefix}${daoIntefaceSuffix}.insertSelective(${tableBean.tableNameNoDash});
    }

    @Test
    public void saveAndGetId(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash}) {

         ${tableBean.tableNameNoDash}${classPrefix}${serviceIntefaceSuffix}.insertSelectiveAndGetId(${tableBean.tableNameNoDash});
    }

    @Test
    public void update(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash}) {
        trimStringValue(${tableBean.tableNameNoDash});
        return ${tableBean.tableNameNoDash}${classPrefix}${serviceIntefaceSuffix}.updateByPrimaryKeySelective(${tableBean.tableNameNoDash});
    }

    @Test
    public void saveOrUpdate(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash}) {
        if (null == ${tableBean.tableNameNoDash}.getId() || 0 == ${tableBean.tableNameNoDash}.getId()) {
            return save(${tableBean.tableNameNoDash});
        } else {
            return update(${tableBean.tableNameNoDash});
        }
    }

    @Test
    public void get(Long id) {
         ${tableBean.tableNameNoDash}${classPrefix}${serviceIntefaceSuffix}.selectByPrimaryKey(id);
    }

    @Test
    public void getAll() {
         ${tableBean.tableNameNoDash}${classPrefix}${serviceIntefaceSuffix}.selectAll();
    }

    @Test
    public void delete(Long id) {
         ${tableBean.tableNameNoDash}${classPrefix}$${serviceIntefaceSuffix}.deleteByPrimaryKey(id);
    }

}
