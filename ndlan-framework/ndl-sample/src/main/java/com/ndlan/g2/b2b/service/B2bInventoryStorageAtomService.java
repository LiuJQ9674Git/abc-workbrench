package com.ndlan.g2.b2b.service;

import com.ndlan.g2.b2b.model.B2bInventoryStorageBean;

import java.util.List;

public interface B2bInventoryStorageAtomService {
    /**
     * 保存数据对象
     * @param b2bInventoryStorageBean 数据对象
     * @return 成功操作记录数
     */
    public int saveB2bInventoryStorageBean(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * 保存数据对象，并获取主键id到b2bInventoryStorage中
     * @param b2bInventoryStorage 数据对象
     * @return 成功操作记录数
     */
    public int saveAndGetId(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * 更新数据对象
     * @param b2bInventoryStorage 数据对象
     * @return 成功操作记录数
     */
    public int update(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * 保存或更新对象，判断逻辑根据主键id，为null或0，则为保存，否则为更新
     * @param b2bInventoryStorage 数据对象
     * @return 成功操作记录数
     */
    public int saveOrUpdateB2bInventoryStorageBean(B2bInventoryStorageBean b2bInventoryStorage);

    /**
     * 获取一个数据对象
     * @param id 主键
     * @return 数据对象，无数据时返回null
     */
    public B2bInventoryStorageBean getB2bInventoryStorageBean(String invStorageId);
    /**
     * 获取所有数据对象
     * @return 所有数据对象，无数据时返回空记录集
     */
    public List<B2bInventoryStorageBean> getAll();

    /**
    * 根据主键id删除对象
    * @return 成功操作记录数
    */
    public void delete(String invStorageId);

    /**
     * 将对象中的字符串类型根据数据库长度进行剪切，防止因数据过长导致的数据保存失败
     * @return 剪切后的数据对象
     */
    public B2bInventoryStorageBean trimStringValue(B2bInventoryStorageBean b2bInventoryStorage);

    public List<B2bInventoryStorageBean> queryB2bInventoryStorageBean
	(B2bInventoryStorageBean bean, Long startPos, Long num);

    public List<B2bInventoryStorageBean> queryB2bInventoryStorageBean
	(B2bInventoryStorageBean bean);

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
