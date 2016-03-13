package ${packageBase}.${packageBusinessServiceImpl};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ndlan.framework.core.api.AtomService;
import com.ndlan.framework.core.service.DefaultBesinessServiceImpl;



import ${packageBase}.${packageModel}.${tableBean.tableNameCapitalized}${classSuffix};
import ${packageBase}.${packageModelQuery}.${tableBean.tableNameCapitalized}${classQuerySuffix};

import ${packageBase}.${packageBusinessService}.${tableBean.tableNameCapitalized}${classSuffix}${businessServiceSuffix};



@Service("${tableBean.tableNameNoDash}${classSuffix}${businessServiceSuffix}")
public class ${tableBean.tableNameCapitalized}${classSuffix}${businessServiceImplSuffix}
	extends DefaultBesinessServiceImpl<${tableBean.tableNameCapitalized}${classSuffix}>  
	implements ${tableBean.tableNameCapitalized}${classSuffix}${businessServiceSuffix}{

	@Autowired(required=true)
	@Qualifier(value="${tableBean.tableNameNoDash}${classSuffix}${atomServiceSuffix}")
	private AtomService atomService;

	@Override
	public AtomService getAtomService() {
		// TODO Auto-generated method stub
		return atomService;
	}

}
