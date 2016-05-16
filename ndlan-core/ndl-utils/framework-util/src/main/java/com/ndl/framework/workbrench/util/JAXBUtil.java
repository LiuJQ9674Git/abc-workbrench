package com.ndl.framework.workbrench.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;

@SuppressWarnings("restriction")
public class JAXBUtil {

	private final JAXBContext context;
	
	@SuppressWarnings("unused")
	private JAXBUtil(){
		context=null;
	}
	
	public JAXBUtil(Class<?>... types) {
		try {
			context = JAXBContext.newInstance(types);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public String objectToXml(Object obj) throws Exception {
		// context = JAXBContext.newInstance(types);
		// 根据上下文获取marshaller对象
		Marshaller marshaller = context.createMarshaller();
		// 设置编码字符集
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		// 格式化XML输出，有分行和缩进
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 打印到控制台
		marshaller.marshal(obj, System.out);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(obj, baos);
		String xmlObj = new String(baos.toByteArray());
		return xmlObj.replace(" standalone=\"yes\"", "");
	}

	public void objectToXmlFile(Object obj, File file) throws Exception {
		// context = JAXBContext.newInstance(types);
		// 根据上下文获取marshaller对象
		Marshaller marshaller = context.createMarshaller();
		// 设置编码字符集
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		// 格式化XML输出，有分行和缩进
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 打印到控制台
		marshaller.marshal(obj, System.out);
		marshaller.marshal(obj, file);
	}

	@SuppressWarnings("unchecked")
	public <T> T xmlToObject(File file, Class<T> beanClass) throws Exception {
		T bean = beanClass.newInstance();
		// context = JAXBContext.newInstance(beanClass);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		bean = (T) unmarshaller.unmarshal(file);

		return bean;
	}

	/**
	 * Xml->Java Object.
	 */
	@SuppressWarnings("unchecked")
	public <T> T xmlToObject(String xml, Class<T>... types) {
		try {

			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 封装Root Element 是 Collection的情况.
	 */
	public static class CollectionWrapper {
		@SuppressWarnings("unchecked")
		@XmlAnyElement
		protected Collection collection;
	}
}
