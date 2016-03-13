package com.ndlan.framework.core.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ndlan.framework.core.web.domain.Result;

public interface BusinessController <T extends Identifiable, Q extends T> {


	public Result selectAll();
	
	public Result deleteList( String[] ids) ;
	

	public Result deleteOne( String defualtId);

	
	public Result addOne( T entity);

	
	public Result selectList( Q query);


	public Result viewOne(String defualtId);

	
	public Result editOne(T entity) ;
	
	public Result editSelective(T entity) ;

	
}
