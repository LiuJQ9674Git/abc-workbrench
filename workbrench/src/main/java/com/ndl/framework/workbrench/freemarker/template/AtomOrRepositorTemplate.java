package com.ndl.framework.workbrench.freemarker.template;

import java.io.Serializable;
import java.util.HashSet;
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
public class AtomOrRepositorTemplate implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4962468814228125476L;

	@XmlElement
	private String moduleName;
	
	@XmlElement(name = "atomOrRepositorTemplate")
	@XmlElementWrapper(name="atomOrRepositorTemplates") 
	private Set<BusinessDescripter> atomServiceTemplates;
	
	@XmlElement(name = "repositoryTemplate")
	@XmlElementWrapper(name="repositoryTemplates") 
	private Set<BusinessDescripter> repositoryTemplates;
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Set<BusinessDescripter> getAtomServiceTemplates() {
		return atomServiceTemplates;
	}
	
	public void setAtomServiceTemplates(Set<BusinessDescripter> atomServiceTemplates) {
		this.atomServiceTemplates=atomServiceTemplates;
	}
	
	public Set<BusinessDescripter> getRepositoryTemplates() {
		return repositoryTemplates;
	}

	public void setRepositoryTemplates(Set<BusinessDescripter>  repositoryTemplates) {
		this.repositoryTemplates=repositoryTemplates;
	}

}
