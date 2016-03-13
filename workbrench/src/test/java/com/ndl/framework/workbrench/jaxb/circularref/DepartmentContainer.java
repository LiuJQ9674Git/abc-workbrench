package com.ndl.framework.workbrench.jaxb.circularref;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlElementWrapper;  
import javax.xml.bind.annotation.XmlRootElement; 
import javax.xml.bind.annotation.XmlTransient; 
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "DepartmentContainer")
public class DepartmentContainer {

    Department department; 

    // The @XmlIDREF entities in Department are not available as in XML transcoding only 
    // the reference gets saved and not the data.
    // So we save this data separately as below so that it gets passed from the 
    // server to the client or vice versa. 
    Company company; 

    /** @param department
     *            the Department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
        this.company = department.getCompany();
    }

    public DepartmentContainer() {
        department = null; 
        company = null; 
    }

    /**
     * @param department
     */
    public DepartmentContainer(Department department) {
        setDepartment(department); 
    }

    /**
     *  @return the department
     */
    public Department getDepartment() {
        return this.department; 
    }
}

