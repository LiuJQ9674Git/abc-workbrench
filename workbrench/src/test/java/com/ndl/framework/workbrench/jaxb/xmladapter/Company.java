package com.ndl.framework.workbrench.jaxb.xmladapter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlElementWrapper;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient; 
import javax.xml.bind.annotation.adapters.XmlAdapter;

@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Company  extends XmlAdapter<Company, Company> 
implements Serializable, Cloneable
 {
    private static final long serialVersionUID = 0;

    @XmlElement
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @Id
    protected Integer id;

    @XmlElement
    private String name;
    
    //@XmlTransient
    @XmlElementWrapper(name="employees")  
    @XmlElement(name="employee") 
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL) 
    Set<Employee> employees; 
  
    //@XmlTransient
    @XmlElementWrapper(name="departments")  
    @XmlElement(name="department") 
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL) 
    Set<Department> departments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	// List and Map for XmlAdapter implementation. 
    // This is used to reference the same entity if it already exists in the 
    // XML and hence avoid cyclic references in marshalling and get back the 
    // data in unmarshalling. 
    @XmlTransient		// Not required to store in the XML. 
    private List<Company> entityList = new ArrayList<Company>();
    @XmlTransient		// Not required to store in the XML. 
    private Map<String, Company> entityMap = new HashMap<String, Company>();

    // Entity reference for XmlAdapter implementation. 
    @XmlAttribute
    @Transient		// Not required to store reference in the DB. 
    private String reference; 

	@Override
	public Company unmarshal(Company arg0) throws Exception {
		 Company entity = entityMap.get(arg0.reference); 
	        if (null == entity) {
	            entity = (Company) arg0.clone();
	            entityMap.put(Integer.toString(entity.getId()), entity); 
	        }
	        return entity; 
	    }

	@Override
	public Company marshal(Company arg0) throws Exception {
		 if (arg0 != null) {
	            // Check if the entity has been marshalled before. 
	            // If so return only the reference. 
	            if (entityList.contains(arg0)) {
	                Company adaptedEntity = new Company();
	                adaptedEntity.reference = Integer.toString(arg0.getId());
	                return adaptedEntity; 
	            } else {
	                Company adaptedEntity = (Company) arg0.clone();
	                entityList.add(arg0); 
	                return adaptedEntity; 
	            }
	        } else {
	            return null; 
	        }

	} 
    
}


