package com.ndl.framework.workbrench.define;
/**
 * 解析时需要先解析类名，在解析Import、Annotation
 */
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

@SuppressWarnings("restriction")
@XmlRootElement(name = "BusinessDescripter")
@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessDescripter implements Cloneable,Serializable{

	private static final long serialVersionUID = 3259576170230783492L;
	
	//类注释
	@XmlElement
	private String comment;
	
	//类名
	@XmlElement
	private String className;
	
	//类注解
	@XmlElement
	private String classAnnotation;
	
	//
	@XmlElement
	private MethCategoryEnum componentCategory;
	
	//需要引入的类
	@XmlElement(name = "includeClass")
	@XmlElementWrapper(name="includeClasses")
	private final Set<String> includeClasses=new HashSet<String>();
	
	//需要引入的类
	@XmlElement(name = "headerAnnotation")
	@XmlElementWrapper(name="headerAnnotations")
	private final Set<String> headerAnnotations=new HashSet<String>();
	
	//需要引入的类
	@XmlElement(name = "instanceAnnotation")
	@XmlElementWrapper(name="instanceAnnotations")
	private final Set<String> instanceAnnotations=new HashSet<String>();
	
	//类方法体
	//@XmlElement(name = "methodDescripter")
	@XmlElementWrapper(name="methodDescripterMap")
	private final Map<String, MethodDescripter> methods=new LinkedHashMap<String ,MethodDescripter>();

	//
	public MethCategoryEnum getComponentCategory() {
		return componentCategory;
	}

	public void setComponentCategory(MethCategoryEnum componentCategory) {
		this.componentCategory = componentCategory;
	}

	public Set<String> getIncludeClasses() {
		return includeClasses;
	}
	
	public void addInstanceAnnotation(String instanceAnnotation) {
		
		this.instanceAnnotations.add(instanceAnnotation);
	}

	public void addHeaderAnnotation(String headerAnnotation) {
		
		this.headerAnnotations.add(headerAnnotation);
	}

	public void addIncludeClass(String includeClass) {
		
		this.includeClasses.add(includeClass);
	}
	
	public void addMethodDescripter(MethodDescripter methodDescripter) {
		if(methods.containsKey(methodDescripter.getSignatureEntirety())){
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_BUSINESS_DESCRIPTER_METHOD); 
		}
		this.methods.put(methodDescripter.getSignatureEntirety(), methodDescripter);
	}

	public MethodDescripter createMethodDescripter(String signature) {
		//删除空格
		String methodSignature=StringUtils.trim(signature);
		if (StringUtils.isBlank(methodSignature)) {
			throw new ConfigRuntimeException(
					WorkBrenchConfigProperty.PROCESS_PARSE_EXCETPION_BUSINESS_DESCRIPTER_METHOD);
		}

		MethodDescripter methodDescripter=new MethodDescripter();
		methodDescripter.setSignatureEntirety(methodSignature);
		this.methods.put(signature,methodDescripter);
		return methodDescripter;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Collection<MethodDescripter> getMethods() {
		return methods.values();
	}
	
	public Set<String> getMethodSignature() {
		return methods.keySet();
	}

	public Set<String> getHeaderAnnotations() {
		return headerAnnotations;
	}

	public Set<String> getInstanceAnnotations() {
		return instanceAnnotations;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassAnnotation() {
		return classAnnotation;
	}

	public void setClassAnnotation(String classAnnotation) {
		this.classAnnotation = classAnnotation;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	
}
