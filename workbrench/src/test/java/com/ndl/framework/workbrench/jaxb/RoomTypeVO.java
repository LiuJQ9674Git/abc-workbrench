package com.ndl.framework.workbrench.jaxb;

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
  
@SuppressWarnings("restriction")
public class RoomTypeVO {  
      
    ///@XmlElement(name = "typeid")  
    @XmlAttribute(name = "typeid")  
    public int getTypeid() {  
        return typeid;  
    }  
    public void setTypeid(int typeid) {  
        this.typeid = typeid;  
    }  
    @XmlElement(name = "typename")  
    public String getTypename() {  
        return typename;  
    }  
    public void setTypename(String typename) {  
        this.typename = typename;  
    }  
    @XmlElement(name = "price")  
    public String getPrice() {  
        return price;  
    }  
    public void setPrice(String price) {  
        this.price = price;  
    }  
    private int typeid;  
    private String typename;  
    private String price;  
  
}  
