package ${packageBase}.${packageDaoImpl};

import org.springframework.stereotype.Repository;

import com.ndlan.framework.core.repository.DefualtBaseDaoImpl;

import ${packageBase}.${packageModel}.${tableBean.tableNameCapitalized}${classSuffix};
import ${packageBase}.${packageDao}.${tableBean.tableNameCapitalized}${classSuffix}${daoIntefaceSuffix};
/**
 * 字典数据库操作类接口实现类
 * @author LiuJQ
 */
@Repository("${tableBean.tableNameNoDash}${classSuffix}${daoIntefaceSuffix}")
public class ${tableBean.tableNameCapitalized}${classSuffix}${daoImplSuffix} extends 
	DefualtBaseDaoImpl<${tableBean.tableNameCapitalized}${classSuffix}> implements ${tableBean.tableNameCapitalized}${classSuffix}${daoIntefaceSuffix} {

}
