package com.ndl.framework.workbrench.jaxb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ndl.framework.workbrench.jaxb.JaxbUtil.CollectionWrapper;

public class CollectionTest {

	@Test
	public void testCollection(){
		 //创建java对象  
        
        Hotel hotel=new Hotel();  
        hotel.setId(1);  
        hotel.setName("name1");  
  
        RoomTypeVO t1=new RoomTypeVO();  
        t1.setPrice("20");  
        t1.setTypeid(1);  
        t1.setTypename("typename1");  
          
        RoomTypeVO t2=new RoomTypeVO();  
        t2.setPrice("30");  
        t2.setTypeid(2);  
        t2.setTypename("typename2");  
          
  
        List<RoomTypeVO> RoomTypeVOs=new ArrayList<RoomTypeVO>();  
        RoomTypeVOs.add(t1);  
        RoomTypeVOs.add(t2);  
        hotel.setRoomTypeVOs(RoomTypeVOs);  
  
          
        //将java对象转换为XML字符串  
        JaxbUtil requestBinder = new JaxbUtil(Hotel.class,  
                CollectionWrapper.class);  
        String retXml = requestBinder.toXml(hotel, "utf-8");  
        System.out.println("xml:"+retXml);  
          
        //将xml字符串转换为java对象  
        JaxbUtil resultBinder = new JaxbUtil(Hotel.class,  
                CollectionWrapper.class);  
        Hotel hotelObj = resultBinder.fromXml(retXml);  
          
          
          
        System.out.println("hotelid:"+hotelObj.getId());  
        System.out.println("hotelname:"+hotelObj.getName());  
        for(RoomTypeVO roomTypeVO:hotelObj.getRoomTypeVOs())  
        {  
            System.out.println("Typeid:"+roomTypeVO.getTypeid());  
            System.out.println("Typename:"+roomTypeVO.getTypename());  
        }  
  

	}
}
