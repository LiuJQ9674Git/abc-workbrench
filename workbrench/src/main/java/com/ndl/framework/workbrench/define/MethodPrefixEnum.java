package com.ndl.framework.workbrench.define;

/**
 * 		controller	api		service				repository
	查询	list		list	find/query/get	find/query/get/select
	获取	fetch		fetch	find/query/get	find/query/get/select
	更新	edit		update	update				update
	删除	delete		delete	delete				delete
	增加	add			add		save/insert			save/insert
 *
 */
public enum MethodPrefixEnum {
	
	//Controller
	list,
	fetch,
	edit,
	delete,
	add,
	//api business service
	
	update,

	//atom service
	find,
	query,
	get,
	select,
	save,
	insert
	
	
}
