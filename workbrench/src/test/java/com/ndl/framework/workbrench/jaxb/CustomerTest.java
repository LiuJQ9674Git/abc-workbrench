package com.ndl.framework.workbrench.jaxb;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import org.junit.Test;

import com.ndl.framework.workbrench.freemarker.template.EntityConfigTemplate;
import com.ndl.framework.workbrench.util.JAXBUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class CustomerTest {

	@Test
	public void testObject2Xml() {
		Customer customer = new Customer();
		customer.setId(100);
		customer.setNames(new String[] { "name-a", "name-b", "name-c" });
		customer.setAge(29);
		try {
			File file = new File("d:\\file.xml");
			JAXBUtil util = new JAXBUtil(Customer.class);
			util.objectToXmlFile(customer, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXml2Object() {
			try {
			File file = new File("d:\\file.xml");
			JAXBUtil util = new JAXBUtil( Customer.class);
			
			Customer customer=util.xmlToObject(file,Customer.class);
			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
