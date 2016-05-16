package com.ndl.framework.workbrench.jaxb.xmladapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
//@XmlAccessorType(value = XmlAccessType.PROPERTY)  
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Employee extends XmlAdapter<Employee, Employee> 
implements Serializable, Cloneable
 {
    private static final long serialVersionUID = 0; 
   
    @XmlElement
    @Column(name = "ID", unique = true, nullable = false) 
    @Id
    protected Integer id; 

    @XmlElement
    private String name;
    
    @XmlElement
    private String address;

    @XmlTransient
    //@XmlElement(name="department") 
    @ManyToOne(optional = true, cascade =  CascadeType.ALL) 
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    Department department; 

    @XmlTransient
    //@XmlElement(name="company") 
    @ManyToOne(optional = true, cascade =  CascadeType.ALL) 
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
    Company company;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", department=" + department
				+ ", company=" + company + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	// List and Map for XmlAdapter implementation. 
    // This is used to reference the same entity if it already exists in the 
    // XML and hence avoid cyclic references in marshalling and get back the 
    // data in unmarshalling. 
    @XmlTransient		// Not required to store in the XML. 
    private List<Employee> entityList = new ArrayList<Employee>();
    @XmlTransient		// Not required to store in the XML. 
    private Map<String, Employee> entityMap = new HashMap<String, Employee>();

    // Entity reference for XmlAdapter implementation. 
    @XmlAttribute
    @Transient		// Not required to store reference in the DB. 
    private String reference; 


	@Override
	public Employee unmarshal(Employee arg0) throws Exception {
		 Employee entity = entityMap.get(arg0.reference); 
	        if (null == entity) {
	            entity = (Employee) arg0.clone();
	            entityMap.put(Integer.toString(entity.getId()), entity); 
	        }
	        return entity; 

	}


	@Override
	public Employee marshal(Employee arg0) throws Exception {
		 if (arg0 != null) {
	            // Check if the entity has been marshalled before. 
	            // If so return only the reference. 
	            if (entityList.contains(arg0)) {
	                Employee adaptedEntity = new Employee();
	                adaptedEntity.reference = Integer.toString(arg0.getId());
	                return adaptedEntity; 
	            } else {
	                Employee adaptedEntity = (Employee) arg0.clone();
	                entityList.add(arg0); 
	                return adaptedEntity; 
	            }
	        } else {
	            return null; 
	        }

	} 


}

