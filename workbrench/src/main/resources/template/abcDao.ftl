package ${packageBase}.${packageDao};

import com.ndlan.framework.core.api.MyBatisDao;

import ${packageBase}.${packageModel}.${tableBean.tableNameCapitalized}${classSuffix};

/**
 * 字典数据库操作类接口
 * @author LiuJQ
 */
public interface ${tableBean.tableNameCapitalized}${classSuffix}${daoIntefaceSuffix}
	extends MyBatisDao<${tableBean.tableNameCapitalized}${classSuffix}> {

}