package com.ndl.framework.workbrench.jaxb.circularref;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;  
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlElementWrapper;  
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient; 
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "EmployeeContainer")
public class EmployeeContainer {

    Employee employee; 
    
    // The @XmlIDREF entities in  Employee are not available as in XML transcoding 
    // only the reference gets saved and not the data.
    // So we save this data separately as below so that it gets passed from the 
    // server to the client or vice versa. 
    Department department;
    Company company; 

    /** 
     * @return the employee
     */
    public Employee getEmployee() {
        return this.employee; 
    }

    /**
     * @param employee - the Employee to be set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        department = employee.getDepartment();
        	company = employee.getCompany();
    }

    public EmployeeContainer() {
        employee = null; 
        department = null;
        	company = null; 
    }

    /**
     * @param employee - the Employee to be set
     */
    public EmployeeContainer(Employee employee) {
        setEmployee(employee); 
    }
}

