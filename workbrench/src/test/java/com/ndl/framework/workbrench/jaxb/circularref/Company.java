package com.ndl.framework.workbrench.jaxb.circularref;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlElementWrapper;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient; 
import javax.xml.bind.annotation.XmlID;

@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Company implements Serializable {
    private static final long serialVersionUID = 0;

    @XmlElement
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @Id
   
    protected Integer id;

    @XmlElement
    @XmlID//should be unique across all entities.
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
    
}

