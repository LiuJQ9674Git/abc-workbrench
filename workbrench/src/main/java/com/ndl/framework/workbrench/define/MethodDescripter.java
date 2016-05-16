package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "MethodDescripter")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodDescripter implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5620921171884165856L;

	@XmlElement
	private AnnotationBean methodReturnAnnotation;

	/**
	 * 当作为入参时，参数的注解 如：MyBatis的param，Spring-MVC的ModelAttribute、PathVariable等
	 */
	@XmlElement
	private AnnotationBean methodHeaderAnnotation;
	
	// 方法注释
	@XmlElement
	private String comment;

	@XmlElement
	private String signatureEntirety;

	// 有序Set,方法内对于入参验证使用
	@XmlElement(name = "argumentDescripter")
	@XmlElementWrapper(name = "argumentDescripterList")
	private List<ColumnDescripter> calleeArgumentList = new ArrayList<ColumnDescripter>();

	//多个时预留
	@XmlElement(name = "calleeMethod")
	@XmlElementWrapper(name = "bodyMethodMap")
	private Map<Integer, ColumnBean> bodyMap = new LinkedHashMap<Integer, ColumnBean>();

	//方法体内调用的
	@XmlElement(name = "bodyFragment")
	@XmlElementWrapper(name = "bodyFragmentSlice")
	private Map<Integer, MethodBodyFragment> bodyragment = new LinkedHashMap<Integer, MethodBodyFragment>();

	public MethodDescripter() {

	}

	
	public AnnotationBean getMethodReturnAnnotation() {
		return methodReturnAnnotation;
	}


	public void setMethodReturnAnnotation(AnnotationBean methodReturnAnnotation) {
		this.methodReturnAnnotation = methodReturnAnnotation;
	}


	public AnnotationBean getMethodHeaderAnnotation() {
		return methodHeaderAnnotation;
	}


	public void setMethodHeaderAnnotation(AnnotationBean methodHeaderAnnotation) {
		this.methodHeaderAnnotation = methodHeaderAnnotation;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Map<Integer, MethodBodyFragment> getBodyragment() {
		return bodyragment;
	}

	public void setBodyragment(Map<Integer, MethodBodyFragment> bodyragment) {
		this.bodyragment = bodyragment;
	}

	public void addBodyragment(Integer key, MethodBodyFragment value) {
		this.bodyragment.put(key, value);
	}
	
	public String getSignatureEntirety() {
		return signatureEntirety;
	}

	public void setSignatureEntirety(String signatureEntirety) {
		this.signatureEntirety = signatureEntirety;
	}


	public Map<Integer, ColumnBean> getBodyMap() {
		return bodyMap;
	}

	public void setBodyMap(Map<Integer, ColumnBean> bodyMap) {
		this.bodyMap = bodyMap;
	}

	public void addBody(Integer key, ColumnBean value) {
		this.bodyMap.put(key, value);
	}

	

	public List<ColumnDescripter> getCalleeArgumentList() {
		return calleeArgumentList;
	}

	public void setCalleeArgumentList(List<ColumnDescripter> calleeArgumentList) {
		this.calleeArgumentList = calleeArgumentList;
	}

	public void addArgumentValid(ColumnBean calleeArgumen) {
		//calleeArgumen
		ColumnDescripter columnDescripter=ColumnDescripter.convertByColumnBean(calleeArgumen);
		this.calleeArgumentList.add(columnDescripter) ;
	}
	
	public Object clone() {
		MethodDescripter o = null;
		try {
			o = (MethodDescripter) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

}
