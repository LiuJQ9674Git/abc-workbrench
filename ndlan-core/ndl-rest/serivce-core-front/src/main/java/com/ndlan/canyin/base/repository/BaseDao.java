package com.ndlan.canyin.base.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract interface BaseDao<T, ID extends Serializable> extends 
PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T>
{
  public abstract EntityManager getEntityManager();

  public abstract void batchInsert(List<T> paramList);

  public abstract void batchUpdate(List<T> paramList);

  public abstract void batchDelete(List<T> paramList);

  public abstract void batchDelete(String[] paramArrayOfString);

  public abstract void saveWithId(T paramT);
  
}
