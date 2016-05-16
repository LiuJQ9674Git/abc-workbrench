package com.ndl.framework.workbrench.jaxb.customer;

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
	File file = new File(RunConfigure.getConfigPath()+"/fileHashSet.xml");
	
	@Test
	public void testHashSetWrapObject2Xml() {
		Customer<BookSet> customer = new Customer<BookSet>();  
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
          
        //customer.setT(bookSet);  
        BookSet bookSetWrap= new BookSet();  
        bookSetWrap.addBook(book);  
        bookSetWrap.addBook(book2);  
          
        customer.setT(bookSetWrap);  
		try {
			JAXBUtil util = new JAXBUtil( Customer.class,BookSet.class);
			util.objectToXmlFile(customer, file);

			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHashSetWrapXml2Object() {
			try {

			JAXBUtil util = new JAXBUtil(Customer.class,BookSet.class);
			
			Customer customer=util.xmlToObject(file,  Customer.class);
			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testHashSetObject2Xml() {
		Customer<HashSet<Book>> customer = new Customer<HashSet<Book>>();  
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
          
        customer.setT(bookSet);  
		try {
			JAXBUtil util = new JAXBUtil(Customer.class,HashSet.class);
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
			
			Customer customer=util.xmlToObject(file, Customer.class);
			assertNotNull(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
