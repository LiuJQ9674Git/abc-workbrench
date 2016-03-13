package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryHeadBean;

import java.util.List;

public interface B2bInventoryHeadAtomService {
    /**
     * 保存数据对象
     * @param b2bInventoryHeadBean 数据对象
     * @return 成功操作记录数
     */
    public int saveB2bInventoryHeadBean(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * 保存数据对象，并获取主键id到b2bInventoryHead中
     * @param b2bInventoryHead 数据对象
     * @return 成功操作记录数
     */
    public int saveAndGetId(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * 更新数据对象
     * @param b2bInventoryHead 数据对象
     * @return 成功操作记录数
     */
    public int update(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * 保存或更新对象，判断逻辑根据主键id，为null或0，则为保存，否则为更新
     * @param b2bInventoryHead 数据对象
     * @return 成功操作记录数
     */
    public int saveOrUpdateB2bInventoryHeadBean(B2bInventoryHeadBean b2bInventoryHead);

    /**
     * 获取一个数据对象
     * @param id 主键
     * @return 数据对象，无数据时返回null
     */
    public B2bInventoryHeadBean getB2bInventoryHeadBean(String invHeadId);
    /**
     * 获取所有数据对象
     * @return 所有数据对象，无数据时返回空记录集
     */
    public List<B2bInventoryHeadBean> getAll();

    /**
    * 根据主键id删除对象
    * @return 成功操作记录数
    */
    public void delete(String invHeadId);

    /**
     * 将对象中的字符串类型根据数据库长度进行剪切，防止因数据过长导致的数据保存失败
     * @return 剪切后的数据对象
     */
    public B2bInventoryHeadBean trimStringValue(B2bInventoryHeadBean b2bInventoryHead);

    public List<B2bInventoryHeadBean> queryB2bInventoryHeadBean
	(B2bInventoryHeadBean bean, Long startPos, Long num);

    public List<B2bInventoryHeadBean> queryB2bInventoryHeadBean
	(B2bInventoryHeadBean bean);

    /**
     * 根据sql语句进行删除
     * @param whereSql where关键字后面的删除条件，例如：id = ?
     * @param params 条件中的参数，个数需要与"?"个数保持一致
     * @return 成功删除的记录数
     */
    public int deleteByWhereSql(String whereSql, Object[] params);

     /**
    * 更新数据
    * @param sql 更新语句
    * @param args 参数
    * @return 成功更新的记录数
    */
    public int update(String sql, Object... args);

}
