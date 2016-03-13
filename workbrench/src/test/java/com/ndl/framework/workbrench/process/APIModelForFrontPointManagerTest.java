package com.ndl.framework.workbrench.process;

import org.junit.Test;

public class APIModelForFrontPointManagerTest {

	APIModelManager modelManager=APIModelForFrontPointManager.getInstance();
	

	@Test
	public void generateModelDamain(){
		//Android API
		//Guides - 获取霞客列表信息
		//http://www.ieream.com/mapi/1/mapi/1/guides.php?action=getlist
		String path="http://www.ieream.com/mapi/1/guides.php?action=getlist";
		//GET method
		modelManager.generateModelDamainFromRawData(path);
	}
}
