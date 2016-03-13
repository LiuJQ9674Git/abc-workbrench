package com.ndl.framework.workbrench.jaxb.xmladapter;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.jaxb.customer.BookSet;
import com.ndl.framework.workbrench.jaxb.customer.Customer;
import com.ndl.framework.workbrench.util.JAXBUtil;

public class CircularRefTest {
	File file = new File(RunConfigure.getConfigPath()+"/fileCircularRef.xml");
	
	@Test
	public void testCircularRefToXML(){
		try{
		Employee employee=new Employee();
		employee.setName("张三");
		employee.setId(1);
		
		//
		Department department=new Department();
		department.setId(1);
		department.setName("研发");
		
		Department testDepartment=new Department();
		testDepartment.setId(1);
		testDepartment.setName("研发");
		
		Company company=new Company();
		company.setId(1);
		company.setName("ABC");
		
		Set<Employee> employees =new HashSet<Employee>();
		
		employee.setCompany(company);
		employee.setDepartment(department);
		employees.add(employee);
		
		employee=new Employee();
		employee.setName("李四");
		employee.setId(2);
		employees.add(employee);
		
		
		department.setCompany(company);
		department.setEmployees(employees);

		
		Set<Department> departments =new HashSet<Department>();
		departments.add(department);
		company.setDepartments(departments);
		try {
			JAXBUtil util = new JAXBUtil( Company.class);
			util.objectToXmlFile(company, file);

			assertNotNull(company);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCircularRefFromXML() {
			try {

			JAXBUtil util = new JAXBUtil(Company.class);
			
			Company customer=util.xmlToObject(file,  Company.class);
			Set<Department> sets=customer.getDepartments();
			for(Department dempartment:sets){
				System.out.println("dempartment id\t"+dempartment.id);
				System.out.println("dempartment name\t"+dempartment.getName());
				
				Set<Employee> emplSet=dempartment.getEmployees();
				for(Employee employee:emplSet){
					System.out.println("employee id\t"+employee.id);
					System.out.println("employee name\t"+employee.getName());
				}
				Employee[]  empls=emplSet.toArray(new Employee[0]);
				for(int i=0;i< emplSet.size();i++){
					Employee employee=empls[i];
					System.out.println("employee id\t"+employee.id);
					System.out.println("employee name\t"+employee.getName());
				}
			}
			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

