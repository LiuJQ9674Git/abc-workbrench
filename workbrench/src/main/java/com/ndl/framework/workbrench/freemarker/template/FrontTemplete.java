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
@XmlRootElement(name = "moduleAtomTemplate")
@XmlAccessorType(XmlAccessType.FIELD)
public class FrontTemplete implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 954926967305384368L;

	@XmlElement
	private String moduleName;
	
	@XmlElement(name = "frontTemplate")
	@XmlElementWrapper(name="frontTemplates") 
	private Set<BusinessDescripter> frontTemplates;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	

	@Override
	public String toString() {
		return "FrontTemplete [moduleName=" + moduleName + "]";
	}

	public Set<BusinessDescripter> getFrontTemplates() {
		return frontTemplates;
	}

	public void setFrontTemplates(Set<BusinessDescripter> frontTemplates) {
		this.frontTemplates = frontTemplates;
	}
	
	public void addFrontTemplate(BusinessDescripter frontTemplate) {
		this.frontTemplates.add(frontTemplate);
	}
	
}
