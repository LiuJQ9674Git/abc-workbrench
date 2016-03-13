package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModuleBean implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3418109709494300160L;
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public ModuleBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(ModuleBean.class);
	}
}
