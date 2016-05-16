package com.ndlan.framework.core.web.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.web.domain.Result;

/**
 * 基础控制器接口
 * @author LiuJQ
 */
public interface BaseController<T extends Identifiable, Q extends T> {
	/**
	 * 根据ID列表删除对象，如果idList 为空或者空列表则直接返回{@link Result},状态为OK
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary/delete</tt></th>
	 * 			<th>post</th>
	 *     		<th><tt>{@link Result}的json对象</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param idList 要删除对象的ID列表
	 * @return ModelAndView
	 */
	public Result deleteList(String[] ids);

	/**
	 * 删除一条记录
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary/${id}</tt></th>
	 * 			<th>delete</th>
	 *     		<th><tt>{@link Result}的json对象</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param id 不能为null，则跳转到错误页面
	 * @return ModelAndView
	 */
	public Result deleteOne(String id);

	/**
	 * 添加一条实体，实体不能为null
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary</tt></th>
	 * 			<th>post</th>
	 *     		<th><tt>redirect:/sys/dictionary/</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param entity 要添加的实体
	 * @return ModelAndView
	 */
	public ModelAndView addOne(T entity);

	/**
	 * 跳转到添加页面为insertXXX页面<br>示例Bean对象:SysDictionay->生成路径：/sys/dictionary
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary/add</tt></th>
	 * 			<th>get</th>
	 *     		<th><tt>/sys/dictionary/addDictionary.ftl</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @return ModelAndView
	 */
	public ModelAndView addView();

	/**
	 * 查询对象列表，返回页面 listXXX页面
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary</tt></th>
	 * 			<th>get</th>
	 *     		<th><tt>/sys/dictionary/listDictionary.ftl</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param query 查询对象
	 * @param pageable 分页参数与排序参数
	 * @return  ModelAndView
	 */
	public ModelAndView selectList(Q query, Pageable pageable);

	/**
	 * 根据ID查询一个对象，返回页面为viewXXX页面
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary/${id}</tt></th>
	 * 			<th>get</th>
	 *     		<th><tt>/sys/dictionary/viewDictionary.ftl</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param id 不能为null，则跳转到错误页面
	 * @return ModelAndView
	 */
	public ModelAndView viewOne(String id);

	/**
	 * 更新一个实体，实体不能为null
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary/</tt></th>
	 * 			<th>put</th>
	 *     		<th><tt>{@link Result}已更新的实体对象json字符串</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param entity 要更新的实体
	 * @return Result
	 */
	public Result editOne(T entity);

	/**
	 * 跳转到更新页面为editXXX页面
	 * <blockquote>
	 * 	<table cellpadding=1 border=1  cellspacing=0 summary="Capturing group numberings">
	 * 		<tr>
	 *     		<th><tt>路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 * 			<th>访问方式&nbsp;&nbsp;&nbsp;&nbsp;</th>
	 *     		<th><tt>返回路径&nbsp;&nbsp;&nbsp;&nbsp;</tt></th>
	 *      </tr>
	 *      <tr>
	 *     		<th><tt>/sys/dictionary/edit/${id}</tt></th>
	 * 			<th>get</th>
	 *     		<th><tt>/sys/dictionary/editDictionary.ftl</tt></th>
	 *      </tr>
	 * </table>
	 * </blockquote>
	 * @param id 不能为null，则跳转到错误页面
	 * @return ModelAndView
	 */
	public ModelAndView editView(String id);
	
}
