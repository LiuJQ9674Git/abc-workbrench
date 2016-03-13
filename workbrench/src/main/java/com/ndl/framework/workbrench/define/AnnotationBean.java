package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 注解信息描述
 * Controller方法的注解，主要描述RequestMapping
 * Service方法注解，主要用于原子服务的事务描述
 * DAO的注解，对于JPA主要用于Query语句的描述
 *
 */

@SuppressWarnings("restriction")
@XmlRootElement(name = "AnnotationBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnnotationBean implements Cloneable,Serializable{
	
	private static final long serialVersionUID = -9020736670567123478L;

	/**
	 * 注解关键字
	 */
	@XmlElement
	private AnnotationTypeEnum annnoteKey;
	
	/**
	 * 注解值
	 */
	@XmlElement
	private String annoteValue;
	
	/**
	 * 当是实例变量时，实例变量的类型，它应当和TableBean的值一致。
	 */
	@XmlElement
	private String annoteType;


	public AnnotationTypeEnum getAnnnoteKey() {
		return annnoteKey;
	}

	
	public void setAnnnoteKey(AnnotationTypeEnum annnoteKey) {
		this.annnoteKey = annnoteKey;
	}

	public String getAnnoteValue() {
		return annoteValue;
	}

	
	public void setAnnoteValue(String annoteValue) {
		this.annoteValue = annoteValue;
	}

	public Object clone() {
		AnnotationBean o = null;
		try {
			o = (AnnotationBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getAnnoteType() {
		return annoteType;
	}

	public void setAnnoteType(String annoteType) {
		this.annoteType = annoteType;
	}

}
