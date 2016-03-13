package com.ndl.framework.workbrench.define;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * 控制器包描述
 *
 */

@SuppressWarnings("restriction")
@XmlRootElement(name = "FrontPackage")
@XmlAccessorType(XmlAccessType.FIELD)
public class FrontPackage implements Cloneable,Serializable{
	
	private static final long serialVersionUID = -8752398040258029005L;
	
	@XmlElement(name = "FrontName")
	private String frontName;
	
	
	@XmlElement(name = "controller")
	@XmlElementWrapper(name="controlleres") 
	private Set<ControllerBean> controlleres;

	@XmlElement(name = "controllerTemplate")
	@XmlElementWrapper(name="controllerTemplates") 
	private final Set<BusinessDescripter> controllerTemplates=new HashSet<BusinessDescripter>();
	
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public FrontPackage() {
    	uuid=SequenceGenerator.obtainSequenceUUID(FrontPackage.class);
    }
    
	public Set<ControllerBean> getControlleres() {
		return controlleres;
	}

	public void setControlleres(Set<ControllerBean> controlleres) {
		this.controlleres = controlleres;
	}
	
	public String getFrontName() {
		return frontName;
	}

	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}

	public Set<BusinessDescripter> getControllerTemplates() {
		return controllerTemplates;
	}

	public void addControllerTemplate(BusinessDescripter controllerTemplate) {
		this.controllerTemplates.add(controllerTemplate);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	} 
	
}
