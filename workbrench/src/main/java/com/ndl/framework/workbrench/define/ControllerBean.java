package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("restriction")
@XmlRootElement(name = "ControllerBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class ControllerBean //extends BusinessDescripter 
	implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5092131891907574512L;

	/**
	 * 服务在标识，在服务的实现中是注解的名称和其它类中定义变量时的名称
	 */
	@XmlElement
	private String controllerId;
	
	@XmlElement
	private String uri;
	
	@XmlElement
	private String controllerType;
	
	/**
	 * 服务的描述信息，则package之后的注释
	 */
	@XmlElement
	private String controllerDescipter;
	
	@XmlElement
	private String extendedClass="BaseManageController";
	
    @XmlElement
    private MethCategoryEnum controllerCategory;
	/**
	 * @RestController
	 * @RequestMapping("/shoppingcart")
	 */
	@XmlElement(name="annotation")
	@XmlElementWrapper(name="annotationBeanList")
	private Set<AnnotationBean> annotationBeanList; 
	
	 @XmlElement(name="instanceAnnotationBean")
	 @XmlElementWrapper(name="instanceAnnotationBeanList")
	 private Set<AnnotationBean> instanceAnnotationBeanList; 
	 
	/**
	 * 服务所规定的方法
	 */
	@XmlElement(name="controllerMethod")
	@XmlElementWrapper(name="controllerMethodList")
	private Set<MethodBean> controllerMethodList;
	
	/**
	 * 服务所包含的服务，此时应当是业务服务，所包含的业务，默认添加到控制器的实例变量中，
	 * 而且一个Controller一个业务对象。
	 */
	@XmlElement(name="includeService")
	private ServiceBean includeService;
	
	/**
	 * 服务所包含的实体Bean，用于import
	 */
	@XmlElement(name="includeBean")
	@XmlElementWrapper(name="includeBeanList")
	private Set<TableBean> includeBeanList;
	
	/**
	 * 服务所包含的域Bean，用于import
	 */
	@XmlElement(name="includeDomain")
	@XmlElementWrapper(name="includeDomainBeanList")
	private Set<DomainBean> includeDomainBeanList;

	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 

	@XmlTransient
	private final BusinessDescripter businessDescripter;
	
    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public ControllerBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(ControllerBean.class);
      	businessDescripter=new BusinessDescripter();
    }
    
    public BusinessDescripter getBusinessDescripter() {
        return businessDescripter; 
    }
    
    
	public MethCategoryEnum getControllerCategory() {
		return controllerCategory;
	}

	public void setControllerCategory(MethCategoryEnum controllerCategory) {
		this.controllerCategory = controllerCategory;
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public String getControllerType() {
		return controllerType;
	}

	public void setControllerType(String controllerType) {
		this.controllerType = controllerType;
	}

	public String getControllerDescipter() {
		return controllerDescipter;
	}

	public void setControllerDescipter(String controllerDescipter) {
		this.controllerDescipter = controllerDescipter;
	}

	public String getExtendedClass() {
		return extendedClass;
	}

	public void setExtendedClass(String extendedClass) {
		this.extendedClass = extendedClass;
	}
	
	public Set<AnnotationBean> getAnnotationBeanList() {
		return annotationBeanList;
	}

	public void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		this.annotationBeanList = annotationBeanList;
	}

	public void addAnnotationBean(Set<AnnotationBean> annotationBeanList){
		if(this.annotationBeanList==null){
			this.annotationBeanList=new HashSet<AnnotationBean>();
		}
		if(!this.annotationBeanList.containsAll(annotationBeanList)){
			this.annotationBeanList.addAll(annotationBeanList);
		}
	}
	public void addAnnotationBean(AnnotationBean annotationBean){
		if(this.annotationBeanList==null){
			this.annotationBeanList=new HashSet<AnnotationBean>();
		}
		if(!this.annotationBeanList.contains(annotationBean)){
			this.annotationBeanList.add(annotationBean);
		}
	}
	
	public Set<MethodBean> getControllerMethodList() {
		return controllerMethodList;
	}

	public void setControllerMethodList(Set<MethodBean> controllerMethodList) {
		this.controllerMethodList = controllerMethodList;
	}
	
	public void addControllerMethodBean(Set<MethodBean> controllerMethodList){
		if(this.controllerMethodList==null){
			this.controllerMethodList=new HashSet<MethodBean>();
		}
		if(!this.controllerMethodList.containsAll(controllerMethodList)){
			this.controllerMethodList.addAll(controllerMethodList);
		}
	}
	
	public void addControllerMethodBean(MethodBean methodBean){
		if(this.controllerMethodList==null){
			this.controllerMethodList=new HashSet<MethodBean>();
		}
		if(!this.controllerMethodList.contains(methodBean)){
			this.controllerMethodList.add(methodBean);
		}
	}
	public Set<TableBean> getIncludeBeanList() {
		return includeBeanList;
	}

	public void setIncludeBeanList(Set<TableBean> includeBeanList) {
		this.includeBeanList = includeBeanList;
	}

	public void addInculdeEntityBean(TableBean tableBean){
		if(this.includeBeanList==null){
			this.includeBeanList=new HashSet<TableBean>();
		}
		if(!this.includeBeanList.contains(tableBean)){
			this.includeBeanList.add(tableBean);
		}
	}
	
	public void addInculdeEntityBean(Set<TableBean> includeBeanList){
		if(this.includeBeanList==null){
			this.includeBeanList=new HashSet<TableBean>();
		}
		if(!this.includeBeanList.containsAll(includeBeanList)){
			this.includeBeanList.addAll(includeBeanList);
		}
	}
	
	public Set<DomainBean> getIncludeDomainBeanList() {
		return includeDomainBeanList;
	}

	public void setIncludeDomainBeanList(Set<DomainBean> includeDomainBeanList) {
		this.includeDomainBeanList = includeDomainBeanList;
	}

	public void addIncludeDomainBean(DomainBean domainBean){
		if(this.includeDomainBeanList==null){
			this.includeDomainBeanList=new HashSet<DomainBean>();
		}
		if(!this.includeDomainBeanList.contains(domainBean)){
			this.includeDomainBeanList.add(domainBean);
		}
	}
	
	public void addIncludeDomainBean(Set<DomainBean> includeDomainBeanList){
		if(this.includeDomainBeanList==null){
			this.includeDomainBeanList=new HashSet<DomainBean>();
		}
		if(!this.includeDomainBeanList.containsAll(includeDomainBeanList)){
			this.includeDomainBeanList.addAll(includeDomainBeanList);
		}
	}

	public ServiceBean getIncludeService() {
		return includeService;
	}

	public void setIncludeService(ServiceBean includeService) {
		this.includeService = includeService;
	}
	
	public Object clone() {
		ControllerBean o = null;
		try {
			o = (ControllerBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Set<AnnotationBean> getInstanceAnnotationBeanList() {
		return instanceAnnotationBeanList;
	}

	public void setInstanceAnnotationBeanList(Set<AnnotationBean> instanceAnnotationBeanList) {
		this.instanceAnnotationBeanList = instanceAnnotationBeanList;
	}
	
	public void addInstanceAnnotationBean(AnnotationBean annotationBean) {
		if (this.instanceAnnotationBeanList == null) {
			this.instanceAnnotationBeanList = new HashSet<AnnotationBean>();
		}
		if (!this.instanceAnnotationBeanList.contains(annotationBean)) {
			this.instanceAnnotationBeanList.add(annotationBean);
		}
	}

}
