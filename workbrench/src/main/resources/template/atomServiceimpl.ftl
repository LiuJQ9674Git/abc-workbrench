package ${packageBase}.${packageAtomServiceImpl};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.MyBatisDao;
import com.ndlan.framework.core.service.DefaultAtomBaseServiceImpl;


import ${packageBase}.${packageDao}.${tableBean.tableNameCapitalized}${classSuffix}${daoIntefaceSuffix};


import ${packageBase}.${packageAtomService}.${tableBean.tableNameCapitalized}${classSuffix}${atomServiceSuffix};
import ${packageBase}.${packageModel}.${tableBean.tableNameCapitalized}${classSuffix};

@Service("${tableBean.tableNameNoDash}${classSuffix}${atomServiceSuffix}")
public class ${tableBean.tableNameCapitalized}${classSuffix}${atomServiceImplSuffix} extends DefaultAtomBaseServiceImpl<${tableBean.tableNameCapitalized}${classSuffix}${daoIntefaceSuffix},
	${tableBean.tableNameCapitalized}${classSuffix}> 
	implements ${tableBean.tableNameCapitalized}${classSuffix}${atomServiceSuffix} {
	
	@Autowired(required=true)
	@Qualifier(value="${tableBean.tableNameNoDash}${classPrefix}${daoIntefaceSuffix}")
	private ${tableBean.tableNameCapitalized}${classSuffix}${daoSuffix} ${tableBean.tableNameNoDash}${classPrefix}${daoIntefaceSuffix};

	@Override
	public MyBatisDao<${tableBean.tableNameCapitalized}${classSuffix}> getBaseDao() {
		return ${tableBean.tableNameNoDash}${classPrefix}${daoIntefaceSuffix};
	}

}
