package com.ndl.framework.workbrench.freemarker.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ndl.framework.workbrench.define.TableBean;
import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("restriction")
@XmlRootElement(name="EntityConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityConfigTemplate implements Cloneable,Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 309116031173662936L;

	@XmlElement(name="tableBean")
    @XmlElementWrapper(name="tableBeanList")
    private Set<TableBean> tableBeanList = new HashSet<TableBean>();
    
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public EntityConfigTemplate() {
    	uuid=SequenceGenerator.obtainSequenceUUID(EntityConfigTemplate.class);
    }
    
    public Set<TableBean> getTableBeanList() {
        return tableBeanList;
    }

    public void setTableBeanList(Set<TableBean> tableBeanList) {
        this.tableBeanList = tableBeanList;
    }

	@Override
	public String toString() {
		return "EntityConfig [tableBeanList=" + tableBeanList + "]";
	}
    
    
}
