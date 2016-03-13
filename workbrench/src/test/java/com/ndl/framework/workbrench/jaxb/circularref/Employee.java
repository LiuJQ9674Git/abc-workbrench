package com.ndl.framework.workbrench.jaxb.circularref;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Employee implements Serializable {
    private static final long serialVersionUID = 0; 
   
    @XmlElement
    @Column(name = "ID", unique = true, nullable = false) 
    @Id
    protected Integer id; 

    @XmlElement
    @XmlID // should be unique across all entities.
    private String name;
    
    @XmlElement
    private String address;

    //@XmlTransient
    @XmlElement(name="department") 
    @XmlIDREF
    @ManyToOne(optional = true, cascade =  CascadeType.ALL) 
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    Department department; 

    //@XmlTransient
    @XmlIDREF
    @XmlElement(name="company") 
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


}
