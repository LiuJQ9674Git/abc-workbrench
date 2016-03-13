package com.ndlan.framework.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.MyBatisDao;

/**
 * 基础Service服务接口实现类
 * @author LiuJQ
 */
public abstract class DefaultAtomBaseServiceImpl<D extends MyBatisDao<T>,T extends Identifiable> implements AtomService<D,T> {

	/**
	 * 获取基础数据库操作类
	 * @return
	 */
	public abstract MyBatisDao<T> getBaseDao();

	@Override
	public <V extends T> V queryOne(T query) {
		return getBaseDao().selectOne(query);
	}

	@Override
	public <V extends T> V queryById(String id) {
		return getBaseDao().selectById(id);
	}

	@Override
	public <V extends T> List<V> queryList(T query) {
		return getBaseDao().selectList(query);
	}

	@Override
	public <V extends T> List<V> queryAll() {
		return getBaseDao().selectAll();
	}

	@Override
	public <K, V extends T> Map<K, V> queryMap(T query, String mapKey) {
		return getBaseDao().selectMap(query, mapKey);
	}

	@Override
	public Long queryCount() {
		return getBaseDao().selectCount();
	}

	@Override
	public Long queryCount(T query) {
		return getBaseDao().selectCount(query);
	}

	@Override
	public void insert(T entity) {
		getBaseDao().insert(entity);
	}

	@Override
	public int delete(T query) {
		return getBaseDao().delete(query);
	}

	@Override
	public int deleteById(String id) {
		return getBaseDao().deleteById(id);
	}

	@Override
	public int deleteAll() {
		return getBaseDao().deleteAll();
	}

	@Override
	public int updateById(T entity) {
		return getBaseDao().updateById(entity);
	}

	@Override
	public int updateByIdSelective(T entity) {
		return getBaseDao().updateByIdSelective(entity);
	}

	@Override
	@Transactional
	public void deleteByIdInBatch(List<String> idList) {
		getBaseDao().deleteByIdInBatch(idList);
	}

	@Override
	@Transactional
	public void insertInBatch(List<T> entityList) {
		getBaseDao().insertInBatch(entityList);
	}

	@Override
	@Transactional
	public void updateInBatch(List<T> entityList) {
		getBaseDao().updateInBatch(entityList);
	}

	@Override
	public <V extends T> List<V> queryList(T query, Pageable pageable) {
		return getBaseDao().selectList(query, pageable);
	}

	@Override
	public <V extends T> Page<V> queryPageList(T query, Pageable pageable) {
		return getBaseDao().selectPageList(query, pageable);
	}

	@Override
	public <K, V extends T> Map<K, V> queryMap(T query, String mapKey, Pageable pageable) {
		return getBaseDao().selectMap(query, mapKey, pageable);
	}

}
