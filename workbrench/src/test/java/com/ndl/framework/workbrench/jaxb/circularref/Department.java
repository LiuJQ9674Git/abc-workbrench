package com.ndl.framework.workbrench.jaxb.circularref;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlElementWrapper;  
import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlTransient; 
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@SuppressWarnings("restriction")
//@XmlAccessorType(value = XmlAccessType.PROPERTY) 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Department implements Serializable {
    private static final long serialVersionUID = 0; 

    @XmlElement

    @Column(name = "ID", unique = true, nullable = false, updatable = false) 
    @Id
    protected Integer id; 
    
    @XmlID	// should be unique across all entities.
    @XmlElement
    private String name;
    
    //@XmlTransient
    @XmlElement(name="company") 
    @ManyToOne(optional = true, cascade = CascadeType.ALL) 
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
    @XmlIDREF
    Company company; 


    //@XmlTransient
    @XmlElementWrapper(name="employees")  
    @XmlElement(name="employee") 
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL) 
    Set<Employee> employees;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Set<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}


	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", company=" + company + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	} 
       
}
