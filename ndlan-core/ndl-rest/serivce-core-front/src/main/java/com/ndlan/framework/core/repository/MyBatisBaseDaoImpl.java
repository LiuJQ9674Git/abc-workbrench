package com.ndlan.framework.core.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ndlan.canyin.base.repository.BaseDao;
import com.ndlan.framework.core.exception.RepositoryException;

public class MyBatisBaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> 
{

	// PagingAndSortingRepository start
	/**
	 * Returns all entities sorted by the given options.
	 * 
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	public Iterable<T> findAll(Sort sort) {
		throw new RepositoryException("PagingAndSortingRepository No Releazied");
	}

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction
	 * provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	public Page<T> findAll(Pageable pageable) {//public <V extends T> List<V> selectAll();
		throw new RepositoryException("PagingAndSortingRepository No Releazied");
	}
	// PagingAndSortingRepository over

	// CrudRepository start
	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	public <S extends T> S save(S entity) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Saves all given entities.
	 * 
	 * @param entities
	 * @return the saved entities
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	public T findOne(ID id) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false}
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	public boolean exists(ID id) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	public Iterable<T> findAll() {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	public Iterable<T> findAll(Iterable<ID> ids) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	public long count() {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	public void delete(ID id) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	public void delete(T entity) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException
	 *             in case the given {@link Iterable} is {@literal null}.
	 */
	public void delete(Iterable<? extends T> entities) {
		throw new RepositoryException("CrudRepository No Releazied");
	}

	/**
	 * Deletes all entities managed by the repository.
	 */
	public void deleteAll() {
		throw new RepositoryException("CrudRepository No Releazied");
	}
	// CrudRepository over

	// JpaSpecificationExecutor start

	/**
	 * Returns a single entity matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public T findOne(Specification<T> spec) {
		throw new RepositoryException("JpaSpecificationExecutor No Releazied");
	}

	/**
	 * Returns all entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public List<T> findAll(Specification<T> spec) {
		throw new RepositoryException("JpaSpecificationExecutor No Releazied");
	}

	/**
	 * Returns a {@link Page} of entities matching the given
	 * {@link Specification}.
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		throw new RepositoryException("JpaSpecificationExecutor No Releazied");
	}

	/**
	 * Returns all entities matching the given {@link Specification} and
	 * {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Specification<T> spec, Sort sort) {
		throw new RepositoryException("JpaSpecificationExecutor No Releazied");
	}

	/**
	 * Returns the number of instances that the given {@link Specification} will
	 * return.
	 * 
	 * @param spec
	 *            the {@link Specification} to count instances for
	 * @return the number of instances
	 */
	public long count(Specification<T> spec) {
		throw new RepositoryException("JpaSpecificationExecutor No Releazied");
	}
	// JpaSpecificationExecutor over

	// BaseDao接口javax.persistence.EntityManager

	public EntityManager getEntityManager(){
		throw new RepositoryException("BaseDao No Releazied");
	}
	
	//以下方法实现
	public void batchInsert(List<T> paramList){
		throw new RepositoryException("BaseDao No Releazied");
	}

	public void batchUpdate(List<T> paramList){
		throw new RepositoryException("BaseDao No Releazied");
	}

	public void batchDelete(List<T> paramList){
		throw new RepositoryException("BaseDao No Releazied");
	}

	public void batchDelete(String[] paramArrayOfString){
		throw new RepositoryException("BaseDao No Releazied");
	}

	public void saveWithId(T paramT){
		throw new RepositoryException("BaseDao No Releazied");
	}

}
