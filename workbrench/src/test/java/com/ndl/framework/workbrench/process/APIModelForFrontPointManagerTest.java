package com.ndl.framework.workbrench.process;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class APIModelForFrontPointManagerTest {

	APIModelManager modelManager=APIModelForFrontPointManager.getInstance();
	@Test
	public void generateAllModelForFrontSimpleFileFormXML(){
		modelManager.generateAllModelForFrontSimpleFileFormXML();
	}

	@Test
	public void generateModelDamainFromRawData(){
		//GET method
		Map<String,String> pathUrl=new HashMap<String,String>();
		//5.guides(霞客)
//		pathUrl.put("http://www.ieream.com/mapi/1/guides.php?action=getlist", ".php");
		//6.hotels(临居)
//		pathUrl.put("http://www.ieream.com/mapi/1/hotels.php?action=search&filter=regions:0;tags:养,学;keywords:", ".php");
		//2.locations(区域)
		pathUrl.put("http://www.ieream.com/mapi/1/locations.php?action=getlist&loc_type=ream&data_type=exact", ".php");
		//4.reams(游目)
//		pathUrl.put("http://www.ieream.com/mapi/1/reams.php?action=search&filter=locations:320000;tags:2,3;keywords", ".php");
		//3.tags(标签)
//		pathUrl.put("http://www.ieream.com/mapi/1/tags.php?action=getlist&psize=30", ".php");
		//1.users(用户)
//		pathUrl.put("http://www.ieream.com/mapi/1/users.php?action=getitem&uid=102", ".php");
		
	
		modelManager.generateModelDamainFromRawData(pathUrl);
	}
}
