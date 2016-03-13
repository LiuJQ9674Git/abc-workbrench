package com.ndl.framework.workbrench.jaxb.objects;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashSet;

import org.junit.Test;

import com.ndl.framework.workbrench.freemarker.RunConfigure;
import com.ndl.framework.workbrench.util.JAXBUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class CustomerTest {
	File file = new File(RunConfigure.getConfigPath() + "/fileObjects.xml");

	@Test
	public void testHashSetWrapXml2Object() {
		try {

			JAXBUtil util = new JAXBUtil(Customer.class);

			Customer customer = util.xmlToObject(file, Customer.class);
			HashSet<Book> bookSet=customer.getBookSet();
			for(Book book:bookSet){
				System.out.println("book id:\t"+book.getId());
				System.out.println("book name:\t"+book.getName());
			}
			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHashSetObject2Xml() {
		Customer customer = new Customer();
		customer.setId(100);
		customer.setName("suo");
		customer.setAge(29);

		Book book = new Book();
		book.setId("1");
		book.setName("哈里波特");
		book.setPrice(100);

		Book book2 = new Book();
		book2.setId("2");
		book2.setName("苹果");
		book2.setPrice(50);

		HashSet<Book> bookSet = new HashSet<Book>();
		bookSet.add(book);
		bookSet.add(book2);

		customer.setBookSet(bookSet);
		try {
			// JAXBUtil util = new JAXBUtil(Customer.class,HashSet.class);
			JAXBUtil util = new JAXBUtil(Customer.class);
			util.objectToXmlFile(customer, file);

			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testXml2Object() {
		try {

			JAXBUtil util = new JAXBUtil();

			Customer customer = util.xmlToObject(file, Customer.class);
			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
