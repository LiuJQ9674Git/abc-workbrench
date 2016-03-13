package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProCategoryBean;
import com.ndlan.g2.b2b.dao.B2bProCategoryDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.ndlan.canyin.main.service.BaseService;

@Transactional
@Component("b2bProCategoryAtomService")
public class B2bProCategoryAtomServiceImpl  extends BaseService<B2bProCategoryDao, 
	B2bProCategoryBean>      implements B2bProCategoryAtomService {

    @Resource(name="b2bProCategoryDao")
    protected B2bProCategoryDao b2bProCategoryDao;

    @Override
    public int saveB2bProCategoryBean(B2bProCategoryBean b2bProCategory) {
        trimStringValue(b2bProCategory);
        return b2bProCategoryDao.insertSelective(b2bProCategory);
    }

    @Override
    public int saveAndGetId(B2bProCategoryBean b2bProCategory) {
        trimStringValue(b2bProCategory);
        return b2bProCategoryDao.insertSelectiveAndGetId(b2bProCategory);
    }

    @Override
    public int update(B2bProCategoryBean b2bProCategory) {
        trimStringValue(b2bProCategory);
        return b2bProCategoryDao.updateByPrimaryKeySelective(b2bProCategory);
    }

    @Override
    public int saveOrUpdateB2bProCategoryBean(B2bProCategoryBean b2bProCategory) {
        if (null == b2bProCategory.getCategoryId() ||
		"" == b2bProCategory.getCategoryId()) {
            return saveB2bProCategoryBean(b2bProCategory);
        } else {
            return update(b2bProCategory);
        }
    }

    @Override
    public B2bProCategoryBean getB2bProCategoryBean(String categoryId) {
        return b2bProCategoryDao.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<B2bProCategoryBean> getAll() {
        return b2bProCategoryDao.selectAll();
    }

    @Override
    public void delete(String categoryId) {
         b2bProCategoryDao.deleteByPrimaryKey(categoryId);
    }

    public List<B2bProCategoryBean> queryB2bProCategoryBean
	(B2bProCategoryBean b2bProCategory, Long startPos, Long num){
	SQLParam sqlParam=getWhereSQL(b2bProCategory);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	return b2bProCategoryDao.selectByWhereSql( whereSql,  params,  startPos,  num);
    }

    public int deleteByWhereSql(String whereSql, Object[] params){
	return b2bProCategoryDao.deleteByWhereSql(whereSql, params);
    }
     
    @Override
    public int update(String sql, Object... args) {
        return b2bProCategoryDao.update(sql, args);
    }

    public List<B2bProCategoryBean> queryB2bProCategoryBean
	(B2bProCategoryBean b2bProCategory){
	SQLParam sqlParam=getWhereSQL(b2bProCategory);
	String whereSql=sqlParam.where ;
	Object [] params=sqlParam.params;
	
	return b2bProCategoryDao.selectByWhereSql(whereSql, params);
    }

     public SQLParam getWhereSQL(B2bProCategoryBean b2bProCategory) {
	StringBuffer sqlBuffer=new StringBuffer();
	List<Object> param=new ArrayList<Object>();
	SQLParam sqlParam=new SQLParam();
        String restId = b2bProCategory.getRestId();
        if (StringUtils.isNotBlank(restId) ) {
           sqlBuffer.append("restId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getRestId());
        }
      
            
	  = b2bProCategory.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.get());
        } 
      
            
        String categoryGrade = b2bProCategory.getCategoryGrade();
        if (StringUtils.isNotBlank(categoryGrade) ) {
           sqlBuffer.append("categoryGrade=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getCategoryGrade());
        }
      
            
        String categoryToneIos = b2bProCategory.getCategoryToneIos();
        if (StringUtils.isNotBlank(categoryToneIos) ) {
           sqlBuffer.append("categoryToneIos=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getCategoryToneIos());
        }
      
            
	  = b2bProCategory.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.get());
        } 
      
            
        String categoryId = b2bProCategory.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) ) {
           sqlBuffer.append("categoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getCategoryId());
        }
      
            
        String parentCategoryName = b2bProCategory.getParentCategoryName();
        if (StringUtils.isNotBlank(parentCategoryName) ) {
           sqlBuffer.append("parentCategoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getParentCategoryName());
        }
      
            
        String categoryStatus = b2bProCategory.getCategoryStatus();
        if (StringUtils.isNotBlank(categoryStatus) ) {
           sqlBuffer.append("categoryStatus=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getCategoryStatus());
        }
      
            
        String parentCategoryId = b2bProCategory.getParentCategoryId();
        if (StringUtils.isNotBlank(parentCategoryId) ) {
           sqlBuffer.append("parentCategoryId=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getParentCategoryId());
        }
      
            
        String categoryName = b2bProCategory.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) ) {
           sqlBuffer.append("categoryName=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getCategoryName());
        }
      
            
        String parentIdPath = b2bProCategory.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) ) {
           sqlBuffer.append("parentIdPath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getParentIdPath());
        }
      
            
	  = b2bProCategory.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.get());
        } 
      
            
	  = b2bProCategory.get();
	if (!=null  ) {
           sqlBuffer.append("=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.get());
        } 
      
            
        String parentNamePath = b2bProCategory.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) ) {
           sqlBuffer.append("parentNamePath=?");
	    sqlBuffer.append(" and ");
	     param.add(b2bProCategory.getParentNamePath());
        }
      
            
        String categoryToneAndroid = b2bProCategory.getCategoryToneAndroid();
        if (StringUtils.isNotBlank(categoryToneAndroid) ) {
           sqlBuffer.append("categoryToneAndroid=?");
	    
	     param.add(b2bProCategory.getCategoryToneAndroid());
        }
      
            
	
	String sql=sqlBuffer.toString();
	sql=StringUtils.trim(sql);//sql.trim()
	if(sql.endsWith("and")){
		sql=sql.substring(0, sql.lastIndexOf("and"));
	}
	sqlParam.where=sql;
	sqlParam.params=param.toArray(new Object[0]);
	return sqlParam;
     }

    private class  SQLParam {
	String where;
	Object[] params;
    }
    @Override
    public B2bProCategoryBean trimStringValue(B2bProCategoryBean b2bProCategory) {
        String restId = b2bProCategory.getRestId();
        if (StringUtils.isNotBlank(restId) && restId.length() > 36) {
            b2bProCategory.setRestId(restId.substring(0, 36));
        }

        String categoryGrade = b2bProCategory.getCategoryGrade();
        if (StringUtils.isNotBlank(categoryGrade) && categoryGrade.length() > 20) {
            b2bProCategory.setCategoryGrade(categoryGrade.substring(0, 20));
        }

        String categoryToneIos = b2bProCategory.getCategoryToneIos();
        if (StringUtils.isNotBlank(categoryToneIos) && categoryToneIos.length() > 36) {
            b2bProCategory.setCategoryToneIos(categoryToneIos.substring(0, 36));
        }

        String categoryId = b2bProCategory.getCategoryId();
        if (StringUtils.isNotBlank(categoryId) && categoryId.length() > 36) {
            b2bProCategory.setCategoryId(categoryId.substring(0, 36));
        }

        String parentCategoryName = b2bProCategory.getParentCategoryName();
        if (StringUtils.isNotBlank(parentCategoryName) && parentCategoryName.length() > 255) {
            b2bProCategory.setParentCategoryName(parentCategoryName.substring(0, 255));
        }

        String categoryStatus = b2bProCategory.getCategoryStatus();
        if (StringUtils.isNotBlank(categoryStatus) && categoryStatus.length() > 32) {
            b2bProCategory.setCategoryStatus(categoryStatus.substring(0, 32));
        }

        String parentCategoryId = b2bProCategory.getParentCategoryId();
        if (StringUtils.isNotBlank(parentCategoryId) && parentCategoryId.length() > 36) {
            b2bProCategory.setParentCategoryId(parentCategoryId.substring(0, 36));
        }

        String categoryName = b2bProCategory.getCategoryName();
        if (StringUtils.isNotBlank(categoryName) && categoryName.length() > 255) {
            b2bProCategory.setCategoryName(categoryName.substring(0, 255));
        }

        String parentIdPath = b2bProCategory.getParentIdPath();
        if (StringUtils.isNotBlank(parentIdPath) && parentIdPath.length() > 255) {
            b2bProCategory.setParentIdPath(parentIdPath.substring(0, 255));
        }

        String parentNamePath = b2bProCategory.getParentNamePath();
        if (StringUtils.isNotBlank(parentNamePath) && parentNamePath.length() > 255) {
            b2bProCategory.setParentNamePath(parentNamePath.substring(0, 255));
        }

        String categoryToneAndroid = b2bProCategory.getCategoryToneAndroid();
        if (StringUtils.isNotBlank(categoryToneAndroid) && categoryToneAndroid.length() > 36) {
            b2bProCategory.setCategoryToneAndroid(categoryToneAndroid.substring(0, 36));
        }

        return b2bProCategory;
    }
}
