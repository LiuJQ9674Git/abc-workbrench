package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.util.SequenceGenerator;

@SuppressWarnings("restriction")
@XmlRootElement(name = "ServicePackage")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServicePackage implements Cloneable,Serializable{

	private static final long serialVersionUID = -6314908505366894817L;

	@XmlElement(name = "businessServiceName")
	private String businessServiceName;
	
	
	@XmlElement(name = "businessService")
	@XmlElementWrapper(name="businessServices") 
	private Set<ServiceBean> businessServices;

	@XmlElement(name = "businessServiceTemplate")
	@XmlElementWrapper(name="businessServiceTemplates") 
	private final Set<BusinessDescripter> businessServiceTemplates=new HashSet<BusinessDescripter>();
	
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public ServicePackage() {
    	uuid=SequenceGenerator.obtainSequenceUUID(ServicePackage.class);
    }

	public Set<ServiceBean> getBusinessServices() {
		return businessServices;
	}

	public void setBusinessServices(Set<ServiceBean> businessServices) {
		this.businessServices = businessServices;
	}

	public String getBusinessServiceName() {
		return businessServiceName;
	}

	public void setBusinessServiceName(String businessServiceName) {
		this.businessServiceName = businessServiceName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Set<BusinessDescripter> getBusinessServiceTemplates() {
		return businessServiceTemplates;
	}

	public void addBusinessServiceTemplate(BusinessDescripter  businessServiceTemplate) {
		this.businessServiceTemplates.add(businessServiceTemplate);
	}

}
