package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bProductBean;

import java.util.List;

public interface B2bProductAtomService {
    /**
     * 保存数据对象
     * @param b2bProductBean 数据对象
     * @return 成功操作记录数
     */
    public int saveB2bProductBean(B2bProductBean b2bProduct);

    /**
     * 保存数据对象，并获取主键id到b2bProduct中
     * @param b2bProduct 数据对象
     * @return 成功操作记录数
     */
    public int saveAndGetId(B2bProductBean b2bProduct);

    /**
     * 更新数据对象
     * @param b2bProduct 数据对象
     * @return 成功操作记录数
     */
    public int update(B2bProductBean b2bProduct);

    /**
     * 保存或更新对象，判断逻辑根据主键id，为null或0，则为保存，否则为更新
     * @param b2bProduct 数据对象
     * @return 成功操作记录数
     */
    public int saveOrUpdateB2bProductBean(B2bProductBean b2bProduct);

    /**
     * 获取一个数据对象
     * @param id 主键
     * @return 数据对象，无数据时返回null
     */
    public B2bProductBean getB2bProductBean(String proId);
    /**
     * 获取所有数据对象
     * @return 所有数据对象，无数据时返回空记录集
     */
    public List<B2bProductBean> getAll();

    /**
    * 根据主键id删除对象
    * @return 成功操作记录数
    */
    public void delete(String proId);

    /**
     * 将对象中的字符串类型根据数据库长度进行剪切，防止因数据过长导致的数据保存失败
     * @return 剪切后的数据对象
     */
    public B2bProductBean trimStringValue(B2bProductBean b2bProduct);

    public List<B2bProductBean> queryB2bProductBean
	(B2bProductBean bean, Long startPos, Long num);

    public List<B2bProductBean> queryB2bProductBean
	(B2bProductBean bean);

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
