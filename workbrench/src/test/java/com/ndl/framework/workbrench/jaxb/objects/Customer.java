package com.ndl.framework.workbrench.jaxb.objects;

import java.util.HashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Customer {
	@XmlElement
	private String name;  
	@XmlElement
	private    int age;  
	@XmlElement
	private    int id;  
	
    @XmlElementWrapper(name="bookSet")  
    @XmlElement(name="book") 
	private HashSet<Book> bookSet = new HashSet<Book>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashSet<Book> getBookSet() {
		return bookSet;
	}
	public void setBookSet(HashSet<Book> bookSet) {
		this.bookSet = bookSet;
	}   
}
