package com.ndlan.framework.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.MyBatisDao;

public abstract class DefaultBesinessServiceImpl<T extends Identifiable>
		implements BusinessService<T> {

	/**
	 * 获取基础数据库操作类
	 * 
	 * @return
	 */
	public abstract AtomService getAtomService();

	@SuppressWarnings("unchecked")
	@Override
	public <V extends T> V queryOne(T query) {
		return (V) getAtomService().queryOne(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V extends T> V queryById(String id) {
		return (V) getAtomService().queryById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V extends T> List<V> queryList(T query) {
		return getAtomService().queryList(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V extends T> List<V> queryAll() {
		return getAtomService().queryAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V extends T> Map<K, V> queryMap(T query, String mapKey) {
		return getAtomService().queryMap(query, mapKey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long queryCount() {
		return getAtomService().queryCount();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long queryCount(T query) {
		return getAtomService().queryCount(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T entity) {
		getAtomService().insert(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int delete(T query) {
		return getAtomService().delete(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteById(String id) {
		return getAtomService().deleteById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteAll() {
		return getAtomService().deleteAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateById(T entity) {
		return getAtomService().updateById(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateByIdSelective(T entity) {
		return getAtomService().updateByIdSelective(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteByIdInBatch(List<String> idList) {
		getAtomService().deleteByIdInBatch(idList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void insertInBatch(List<T> entityList) {
		getAtomService().insertInBatch(entityList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateInBatch(List<T> entityList) {
		getAtomService().updateInBatch(entityList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V extends T> List<V> queryList(T query, Pageable pageable) {
		return getAtomService().queryList(query, pageable);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <V extends T> Page<V> queryPageList(T query, Pageable pageable) {
		return getAtomService().queryPageList(query, pageable);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <K, V extends T> Map<K, V> queryMap(T query, String mapKey, Pageable pageable) {
		return getAtomService().queryMap(query, mapKey, pageable);
	}
}
