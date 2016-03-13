package com.ndlan.framework.core.web.controller;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.api.BusinessService;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.web.domain.ControllerPath;
import com.ndlan.framework.core.web.domain.Result;
import com.ndlan.framework.core.web.domain.Result.Status;

/**
 * 基础控制器接口实现类
 * @author LiuJQ
 */
public abstract class BaseControllerImpl<T extends Identifiable, Q extends T> 
implements BaseController<T, Q> 
{
	private Logger log = LoggerFactory.getLogger(BaseControllerImpl.class);
	/**
	 * @fields path 页面路径信息
	 */
	protected ControllerPath path = new ControllerPath(this.getClass());

	/**
	 * 获取基础的服务
	 * @return BusinessService
	 */
	protected abstract BusinessService<T> getBaseService();

	@Override
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Result deleteList(String[] ids) {
		if (ArrayUtils.isEmpty(ids)) {
			log.error("未设置批量删除对象的ID号！对象：{}", path.getEntityName());
			return new Result(Status.ERROR, "没有传入要删除的ID号数组！");
		}
		try {
			getBaseService().deleteByIdInBatch(Arrays.asList(ids));
		} catch (Exception e) {
			log.error("批量删除对象失败！对象:" + path.getEntityName(), e);
			return new Result(Status.ERROR, "批量删除失败！");
		}
		return new Result(Status.OK, ids.length);
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Result deleteOne(@PathVariable("id") String id) {
		if (StringUtils.isBlank(id)) {
			log.error("要删除的ID号为null或空字符串！对象：{}", path.getEntityName());
			return new Result(Status.ERROR, "没有传入要删除的ID号！");
		}
		int count = getBaseService().deleteById(id);
		if (count == 0)
			return new Result(Status.ERROR, "要删除的记录不存在！");
		log.debug("成功删除{}个对象，id:{},对象:{}", count, id, path.getEntityName());
		return new Result(Status.OK, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addOne(T entity) {
		getBaseService().insert(entity);
		return new ModelAndView(path.getRedirectListPath());
	}

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addView() {
		return new ModelAndView(path.getAddViewPath());
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView selectList(Q query, @PageableDefault Pageable pageable) {
		Page<T> page = getBaseService().queryPageList(query, pageable);
		ModelAndView mav = new ModelAndView(path.getListViewPath(), "page", page);
		mav.addObject("query", query);
		return mav;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewOne(@PathVariable("id") String id) {
		Object obj = getBaseService().queryById(id);
		return new ModelAndView(path.getOneViewPath(), path.getEntityName(), obj);
	}

	@Override
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Result editOne(T entity) {
		getBaseService().updateById(entity);
		return new Result(Status.OK, entity);
	}

	
	@Override
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editView(@PathVariable("id") String id) {
		Object obj = getBaseService().queryById(id);
		return new ModelAndView(path.getEditViewPath(), path.getEntityName(), obj);
	}
	
	

}
