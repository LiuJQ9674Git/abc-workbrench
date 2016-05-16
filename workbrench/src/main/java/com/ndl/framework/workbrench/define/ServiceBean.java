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
@XmlRootElement(name="ServiceBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceBean //extends BusinessDescripter 
	implements Cloneable,Serializable{
	
	/**
	 * 服务在标识，在服务的实现中是注解的名称和其它类中定义变量时的名称
	 */
    @XmlElement
	private String serviceId;
	/**
	 * 服务的全路径类名，实体类+Service+Impl
	 */
    @XmlElement
	private String serivceType;
	/**
	 * 服务的描述信息，则package之后的注释
	 */
    @XmlElement
	private String serviceDescipter;
	
    @XmlElement
    private MethCategoryEnum serviceCategory;
	/**
	 * 	@RunWith(SpringJUnit4ClassRunner.class)
	 *	@Transactional
	 *	@ContextConfiguration(classes = CachingConfiguration.class)
	 *
	 *  @Configuration
	 *	@EnableCaching
	 *  @EnableAutoConfiguration
	 *  
	 *  @ComponentScan
	 * 
	 *	@ImportResource("classpath:/appdao.xml")
	 */
    @XmlElement(name="annotationBean")
	@XmlElementWrapper(name="annotationBeanList")
	private Set<AnnotationBean> annotationBeanList; 
    
    @XmlElement(name="instanceAnnotationBean")
  	@XmlElementWrapper(name="instanceAnnotationBeanList")
  	private Set<AnnotationBean> instanceAnnotationBeanList; 
	/**
	 * 服务所规定的方法
	 */
    @XmlElement(name="serviceMethod")
    @XmlElementWrapper(name="serviceMethodList")
	private Set<MethodBean> serviceMethodList;
	
    @XmlElement(name="runningMethod")
   	private MethodBean runningMethod;
    
	/**
	 * 服务所包含的服务,import部分
	 */
    @XmlElement(name="includeService")
    @XmlElementWrapper(name="includeServiceList")
	private Set<ServiceBean> includeServiceList;
	
	/**
	 * 服务所包含的实体Bean，用于import
	 */
    @XmlElement(name="includeBean")
    @XmlElementWrapper(name="includeBeanList")
	private Set<TableBean> includeBeanList;
	
	/**
	 * 服务所包含的DAO，用于import
	 */
    @XmlElement(name="includeDao")
    @XmlElementWrapper(name="includeDaoList")
	private Set<DaoBean> includeDaoList;
	
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

	public ServiceBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(ServiceBean.class);
    	businessDescripter=new BusinessDescripter();
    }

    public BusinessDescripter getBusinessDescripter() {
        return businessDescripter; 
    }
    
	public String getServiceId() {
		return serviceId;
	}

  
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getSerivceType() {
		return serivceType;
	}

	public void setSerivceType(String serivceType) {
		this.serivceType = serivceType;
	}

	public String getServiceDescipter() {
		return serviceDescipter;
	}

    
	public void setServiceDescipter(String serviceDescipter) {
		this.serviceDescipter = serviceDescipter;
	}

	public Set<AnnotationBean> getAnnotationBeanList() {
		return annotationBeanList;
	}

	public void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		this.annotationBeanList = annotationBeanList;
	}

	public void addAnnotationBean(AnnotationBean annotationBean){
		if(this.annotationBeanList==null){
			this.annotationBeanList=new HashSet<AnnotationBean>();
		}
		if(!this.annotationBeanList.contains(annotationBean)){
			this.annotationBeanList.add(annotationBean);
		}
	}
	
	public void addAnnotationBean(Set<AnnotationBean> annotationBeanList){
		if(this.annotationBeanList==null){
			this.annotationBeanList=new HashSet<AnnotationBean>();
		}
		if(!this.annotationBeanList.containsAll(annotationBeanList)){
			this.annotationBeanList.addAll(annotationBeanList);
		}
	}
	
	public Set<MethodBean> getServiceMethodList() {
		return serviceMethodList;
	}

	public void setServiceMethodList(Set<MethodBean> serviceMethodList) {
		this.serviceMethodList = serviceMethodList;
	}

	public void addSerivceMethod(MethodBean methodBean){
		if(this.serviceMethodList==null){
			this.serviceMethodList=new HashSet<MethodBean>();
		}
		if(!this.serviceMethodList.contains(methodBean)){
			this.serviceMethodList.add(methodBean);
		}
	}
	
	public void addSerivceMethod(Set<MethodBean> serviceMethodList){
		if(this.serviceMethodList==null){
			this.serviceMethodList=new HashSet<MethodBean>();
		}
		if(!this.serviceMethodList.containsAll(serviceMethodList)){
			this.serviceMethodList.addAll(serviceMethodList);
		}
	}
	
	public Set<ServiceBean> getIncludeServiceList() {
		return includeServiceList;
	}

	public void setIncludeServiceList(Set<ServiceBean> includeServiceList) {
		this.includeServiceList = includeServiceList;
	}
	
	public void addIncludeService(ServiceBean serviceBean){
		if(this.includeServiceList==null){
			this.includeServiceList=new HashSet<ServiceBean>();
		}
		if(!this.includeServiceList.contains(serviceBean)){
			this.includeServiceList.add(serviceBean);
		}
	}
	
	public void addIncludeService(Set<ServiceBean> includeServiceList){
		if(this.includeServiceList==null){
			this.includeServiceList=new HashSet<ServiceBean>();
		}
		if(!this.includeServiceList.containsAll(includeServiceList)){
			this.includeServiceList.addAll(includeServiceList);
		}
	}
	
	public Set<TableBean> getIncludeBeanList() {
		return includeBeanList;
	}

	public void setIncludeBeanList(Set<TableBean> includeBeanList) {
		this.includeBeanList = includeBeanList;
	}

	public void addIncludeBean(TableBean tableBean){
		if(this.includeBeanList==null){
			this.includeBeanList=new HashSet<TableBean>();
		}
		if(!this.includeBeanList.contains(tableBean)){
			this.includeBeanList.add(tableBean);
		}
		
	}
	
	public void addIncludeBean(Set<TableBean> includeBeanList){
		if(this.includeBeanList==null){
			this.includeBeanList=new HashSet<TableBean>();
		}
		if(!this.includeBeanList.containsAll(includeBeanList)){
			this.includeBeanList.addAll(includeBeanList);
		}
		
	}
	
	public Set<DaoBean> getIncludeDaoList() {
		return includeDaoList;
	}

	public void setIncludeDaoList(Set<DaoBean> includeDaoList) {
		this.includeDaoList = includeDaoList;
	}

	public void addIncludeDaoBean(DaoBean daoBean){
		if(this.includeDaoList==null){
			this.includeDaoList=new HashSet<DaoBean>();
		}
		if(!this.includeDaoList.contains(daoBean)){
			this.includeDaoList.add(daoBean);
		}
	}
	
	public void addIncludeDaoBean(Set<DaoBean> includeDaoList){
		if(this.includeDaoList==null){
			this.includeDaoList=new HashSet<DaoBean>();
		}
		if(!this.includeDaoList.containsAll(includeDaoList)){
			this.includeDaoList.addAll(includeDaoList);
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
	
	public MethCategoryEnum getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(MethCategoryEnum serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	
	public MethodBean getRunningMethod() {
		return runningMethod;
	}

	public void setRunningMethod(MethodBean runningMethod) {
		this.runningMethod = runningMethod;
	}

	public Object clone() {  
		ServiceBean o = null;  
        try {  
            o = (ServiceBean) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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
