package ${packageBase}.service;

import ${packageBase}.model.${tableBean.tableNameCapitalized}${pojoSuffix};

import java.util.List;

public interface ${tableBean.tableNameCapitalized}${classPrefix}${classSuffix} {
    /**
     * 保存数据对象
     * @param ${tableBean.tableNameNoDash}${pojoSuffix} 数据对象
     * @return 成功操作记录数
     */
    public int save${tableBean.tableNameCapitalized}${pojoSuffix}(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash});

    /**
     * 保存数据对象，并获取主键id到${tableBean.tableNameNoDash}中
     * @param ${tableBean.tableNameNoDash} 数据对象
     * @return 成功操作记录数
     */
    public int saveAndGetId(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash});

    /**
     * 更新数据对象
     * @param ${tableBean.tableNameNoDash} 数据对象
     * @return 成功操作记录数
     */
    public int update(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash});

    /**
     * 保存或更新对象，判断逻辑根据主键id，为null或0，则为保存，否则为更新
     * @param ${tableBean.tableNameNoDash} 数据对象
     * @return 成功操作记录数
     */
    public int saveOrUpdate${tableBean.tableNameCapitalized}${pojoSuffix}(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash});

    /**
     * 获取一个数据对象
     * @param id 主键
     * @return 数据对象，无数据时返回null
     */
    public ${tableBean.tableNameCapitalized}${pojoSuffix} get${tableBean.tableNameCapitalized}${pojoSuffix}(${tableBean.pkType} ${tableBean.pkName});
    /**
     * 获取所有数据对象
     * @return 所有数据对象，无数据时返回空记录集
     */
    public List<${tableBean.tableNameCapitalized}${pojoSuffix}> getAll();

    /**
    * 根据主键id删除对象
    * @return 成功操作记录数
    */
    public void delete(${tableBean.pkType} ${tableBean.pkName});

    /**
     * 将对象中的字符串类型根据数据库长度进行剪切，防止因数据过长导致的数据保存失败
     * @return 剪切后的数据对象
     */
    public ${tableBean.tableNameCapitalized}${pojoSuffix} trimStringValue(${tableBean.tableNameCapitalized}${pojoSuffix} ${tableBean.tableNameNoDash});

    public List<${tableBean.tableNameCapitalized}${pojoSuffix}> query${tableBean.tableNameCapitalized}${pojoSuffix}
	(${tableBean.tableNameCapitalized}${pojoSuffix} bean, Long startPos, Long num);

    public List<${tableBean.tableNameCapitalized}${pojoSuffix}> query${tableBean.tableNameCapitalized}${pojoSuffix}
	(${tableBean.tableNameCapitalized}${pojoSuffix} bean);

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
