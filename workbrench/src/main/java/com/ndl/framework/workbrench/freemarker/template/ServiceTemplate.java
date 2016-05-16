package com.ndl.framework.workbrench.freemarker.template;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.ndl.framework.workbrench.define.BusinessDescripter;

@SuppressWarnings("restriction")
@XmlRootElement(name = "serviceTemplate")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceTemplate implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1084662478848634186L;

	@XmlElement
	private String moduleName;
	
	@XmlElement(name = "serviceTemplate")
	@XmlElementWrapper(name="serviceTemplates") 
	private Set<BusinessDescripter> serviceTemplates;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Set<BusinessDescripter> getServiceTemplates() {
		return serviceTemplates;
	}

	public void setServiceTemplates(Set<BusinessDescripter> serviceTemplates) {
		this.serviceTemplates = serviceTemplates;
	}

	@Override
	public String toString() {
		return "ServiceTemplate [moduleName=" + moduleName + "]";
	}
}
