package com.ndlan.framework.core.web.restful;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ndlan.framework.core.api.BusinessController;
import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.web.domain.ControllerPath;
import com.ndlan.framework.core.web.domain.ErrorDescripter;
import com.ndlan.framework.core.web.domain.Result;
import com.ndlan.framework.core.web.domain.Result.Status;

public abstract class BaseBusinessControllerImpl<T extends Identifiable, Q extends T>
	implements BusinessController<T, Q> 
{

	private Logger log = LoggerFactory.getLogger(BaseBusinessControllerImpl.class);
	private final static ErrorDescripter errorDescripter=new ErrorDescripter();
	/** 
	 * @fields path 页面路径信息
	 */
	protected ControllerPath path = new ControllerPath(this.getClass());

	/**
	 * 获取基础的服务
	 * @return BaseService
	 */
	protected abstract BusinessService<T> getBaseService();
	  
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Result selectAll() {
		if(log.isInfoEnabled()){
			log.info("selectList begin:\n");
			log.info("argument:\t");
		}
		
		List<T> list= null;
		try{
			list=getBaseService().queryAll();
		}catch(Exception e){
			log.error("添加对象失败:" + path.getEntityName(), e);
			return new Result(Status.ADDERROR, errorDescripter.getControllerAddOneErrorInfo());

		}
		return new Result(Status.OK, list);
	}
	
	
	//@Override
	@ResponseBody
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, //RequestMethod.DELETE
	produces = MediaType.APPLICATION_JSON_VALUE)
	public Result deleteList(@RequestBody String[] ids) {
		if(log.isInfoEnabled()){
			log.info("deleteList begin:\n");
			log.info("argument:\t"+ids);
		}
		if (ArrayUtils.isEmpty(ids)) {
			log.warn("未设置批量删除对象的ID号！对象：{}", path.getEntityName());
			return new Result(Status.DELETECHECK, errorDescripter.getControllerDeleteListArgumentInfo());
		}
		try {
			getBaseService().deleteByIdInBatch(Arrays.asList(ids));
		} catch (Exception e) {
			log.error("批量删除对象失败！对象:" + path.getEntityName(), e);
			return new Result(Status.DELETEERROR, errorDescripter.getControllerDeleteListErrorInfo());
		}
		if(log.isInfoEnabled()){
			log.info("deleteList over:\n");
			log.info("return:\t"+ids.length);
		}
		return new Result(Status.OK, ids.length);
	}
	
	

	//@Override
	@ResponseBody
	@RequestMapping(value = "/deleteOne/{defualtId}", method = RequestMethod.GET, //RequestMethod.DELETE
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Result deleteOne(@PathVariable("defualtId") String defualtId) {
		if(log.isInfoEnabled()){
			log.info("deleteOne begin:\n");
			log.info("argument:\t"+defualtId);
		}
		if (StringUtils.isBlank(defualtId)) {
			log.warn("要删除的ID号为null或空字符串！对象：{}", path.getEntityName());
			return new Result(Status.DELETECHECK, errorDescripter.getControllerDeleteOneArgumentInfo());
		}
		
		int count = getBaseService().deleteById(defualtId);
		if (count == 0)
			return new Result(Status.DELETEERROR, errorDescripter.getControllerDeleteOneErrorInfo());
		log.debug("成功删除{}个对象，id:{},对象:{}", count, defualtId, path.getEntityName());
		if(log.isInfoEnabled()){
			log.info("deleteOne over:\n");
			log.info("return:\t"+count);
		}
		return new Result(Status.OK, count);
	}

	//@Override
	@ResponseBody
	@RequestMapping(value = "/addOne", method = RequestMethod.POST)
	public Result addOne(@RequestBody T entity) {
		if(log.isInfoEnabled()){
			log.info("deleteOne begin:\n");
			log.info("argument:\t"+entity);
		}
		if (entity==null) {
			log.warn("添加对象为null或空字符串！对象：", path.getEntityName());
			return new Result(Status.ADDCHECK, errorDescripter.getControllerDeleteOneArgumentInfo());
		}
		try{
			getBaseService().insert(entity);
		}catch(Exception e){
			log.error("添加对象失败:" + path.getEntityName(), e);
			return new Result(Status.ADDERROR, errorDescripter.getControllerAddOneErrorInfo());

		}
		return new Result(Status.OK, entity);
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/selectList", method = RequestMethod.POST)
	public Result selectList(@RequestBody Q query) {
		if(log.isInfoEnabled()){
			log.info("selectList begin:\n");
			log.info("argument:\t"+query);
		}
		if (query==null) {
			log.warn("查询为null或空字符串！对象：", path.getEntityName());
			return new Result(Status.ADDCHECK, errorDescripter.getControllerDeleteOneArgumentInfo());
		}
		List<T> list= null;
		try{
			list=getBaseService().queryList(query);
		}catch(Exception e){
			log.error("添加对象失败:" + path.getEntityName(), e);
			return new Result(Status.ADDERROR, errorDescripter.getControllerAddOneErrorInfo());

		}
		if(CollectionUtils.isEmpty(list)){
			return new Result(Status.NOENTITY, new ArrayList<T>());
		}
		return new Result(Status.OK, list);
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/viewOne/{defualtId}", method = RequestMethod.GET)
	public Result viewOne(@PathVariable("defualtId") String defualtId) {
		Object obj = getBaseService().queryById(defualtId);
		if(obj==null){
			return new Result(Status.NOENTITY, null);
		}
		return new Result(Status.OK, obj);
	}

	
	@Override
	@ResponseBody
	@RequestMapping(value = "/editView", method = RequestMethod.POST)
	public Result editOne(@RequestBody T entity) {
		Object obj = getBaseService().updateById(entity);
		return new Result(Status.OK, obj);
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value = "/editSelective", method = RequestMethod.POST)
	public Result editSelective(@RequestBody T entity) {
		Object obj = getBaseService().updateByIdSelective(entity);
		return new Result(Status.OK, obj);
	}

}
