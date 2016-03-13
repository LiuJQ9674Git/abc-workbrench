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

import com.ndl.framework.workbrench.util.SequenceGenerator;

@SuppressWarnings("restriction")
@XmlRootElement(name = "AtomOrRepositoryPackage")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtomOrRepositoryPackage implements Cloneable,Serializable{

	private static final long serialVersionUID = 7392550799344935911L;

	@XmlElement(name = "atomService")
	@XmlElementWrapper(name="atomServices") 
	private Set<ServiceBean> atomServices;
	
	@XmlElement(name = "atomOrRepository")
	private String atomOrRepository;
	
	
	@XmlElement(name = "repository")
	@XmlElementWrapper(name="repositories") 
	private Set<DaoBean> repositories;
	
	@XmlElement(name = "atomServiceTemplate")
	@XmlElementWrapper(name="atomServiceTemplates") 
	private final Set<BusinessDescripter> atomServiceTemplates=new HashSet<BusinessDescripter>();

	@XmlElement(name = "repositoryTemplate")
	@XmlElementWrapper(name="repositoryTemplates") 
	private final Set<BusinessDescripter> repositoryTemplates=new HashSet<BusinessDescripter>();
	
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}
	
    public AtomOrRepositoryPackage() {
     	uuid=SequenceGenerator.obtainSequenceUUID(AtomOrRepositoryPackage.class);
    }

	public Set<ServiceBean> getAtomServices() {
		return atomServices;
	}

	public void setAtomServices(Set<ServiceBean> atomServices) {
		this.atomServices = atomServices;
	}

	public Set<DaoBean> getRepositories() {
		return repositories;
	}

	public void setRepositories(Set<DaoBean> repositories) {
		this.repositories = repositories;
	}

	public String getAtomOrRepository() {
		return atomOrRepository;
	}

	public void setAtomOrRepository(String atomOrRepository) {
		this.atomOrRepository = atomOrRepository;
	}

	public Set<BusinessDescripter> getAtomServiceTemplates() {
		return atomServiceTemplates;
	}

	public void addAtomServiceTemplate(BusinessDescripter  atomServiceTemplate) {
		this.atomServiceTemplates.add(atomServiceTemplate);
	}

	public Set<BusinessDescripter> getRepositoryTemplates() {
		return repositoryTemplates;
	}

	public void addRepositoryTemplate(BusinessDescripter  repositoryTemplate) {
		this.repositoryTemplates.add(repositoryTemplate);
	}

}
