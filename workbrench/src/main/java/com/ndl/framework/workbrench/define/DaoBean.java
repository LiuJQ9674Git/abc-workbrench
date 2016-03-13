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
@XmlRootElement(name = "DaoBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class DaoBean //extends BusinessDescripter 
	implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5046319321058254700L;

	/**
	 * DAO在标识，在服务的实现中是注解的名称和其它类中定义变量时的名称
	 */
	@XmlElement
	private String daoId;

	/**
	 * DAO的全路径类名，实体类+DAO+Impl
	 */
	@XmlElement
	private String daoType;

	/**
	 * DAO的描述信息，则package之后的注释
	 */
	@XmlElement
	private String daoDescipter;

	@XmlElement
	private MethCategoryEnum daoCategory = MethCategoryEnum.JPA;

	@XmlElement(name = "runningMethod")
	private MethodBean runningMethod;

	/**
	 * @Transactional
	 */
	@XmlElement(name = "annotationBean")
	@XmlElementWrapper(name="annotationBeanList")
	private Set<AnnotationBean> annotationBeanList;

	 @XmlElement(name="instanceAnnotationBean")
	 @XmlElementWrapper(name="instanceAnnotationBeanList")
	 private Set<AnnotationBean> instanceAnnotationBeanList; 
	 
	/**
	 * 服务所规定的方法
	 */
	@XmlElement(name = "daoMethod")
	@XmlElementWrapper(name="daoMethodList")
	private Set<MethodBean> daoMethodList;

	/**
	 * 服务所包含的实体Bean，用于import
	 */
	@XmlElement(name = "includeBean")
	@XmlElementWrapper(name="includeBeanList")
	private Set<TableBean> includeBeanList;

	/**
	 * 服务所包含的域Bean，用于import
	 */
	@XmlElement(name = "includeDomain")
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
    
    public DaoBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(DaoBean.class);
    	businessDescripter=new BusinessDescripter();
    }
    
    public BusinessDescripter getBusinessDescripter() {
        return businessDescripter; 
    }
    
	public String getDaoId() {
		return daoId;
	}

	public void setDaoId(String daoId) {
		this.daoId = daoId;
	}

	public String getDaoType() {
		return daoType;
	}

	public void setDaoType(String daoType) {
		this.daoType = daoType;
	}

	public String getDaoDescipter() {
		return daoDescipter;
	}

	public void setDaoDescipter(String daoDescipter) {
		this.daoDescipter = daoDescipter;
	}

	public MethCategoryEnum getDaoCategory() {
		return daoCategory;
	}

	public void setDaoCategory(MethCategoryEnum daoCategory) {
		this.daoCategory = daoCategory;
	}

	public Set<AnnotationBean> getAnnotationBeanList() {
		return annotationBeanList;
	}

	public void setAnnotationBeanList(Set<AnnotationBean> annotationBeanList) {
		this.annotationBeanList = annotationBeanList;
	}

	public void addAnnotationBean(AnnotationBean annotationBean) {
		if (this.annotationBeanList == null) {
			this.annotationBeanList = new HashSet<AnnotationBean>();
		}
		if (!this.annotationBeanList.contains(annotationBean)) {
			this.annotationBeanList.add(annotationBean);
		}
	}

	public void addAnnotationBean(List<AnnotationBean> annotationBeanList) {
		if (this.annotationBeanList == null) {
			this.annotationBeanList = new HashSet<AnnotationBean>();
		}
		if (!this.annotationBeanList.containsAll(annotationBeanList)) {
			this.annotationBeanList.addAll(annotationBeanList);
		}
	}

	public Set<MethodBean> getDaoMethodList() {
		return daoMethodList;
	}

	public void seDaoMethodList(Set<MethodBean> daoMethodList) {
		this.daoMethodList = daoMethodList;
	}

	public void addDaoMethod(MethodBean methodBean) {
		if (this.daoMethodList == null) {
			this.daoMethodList = new HashSet<MethodBean>();
		}
		if (!this.daoMethodList.contains(methodBean)) {
			this.daoMethodList.add(methodBean);
		}
	}

	public void addDaoMethod(List<MethodBean> daoMethodList) {
		if (this.daoMethodList == null) {
			this.daoMethodList = new HashSet<MethodBean>();
		}
		if (!this.daoMethodList.containsAll(daoMethodList)) {
			this.daoMethodList.addAll(daoMethodList);
		}
	}

	public Set<TableBean> getIncludeBeanList() {
		return includeBeanList;
	}

	public void setIncludeBeanList(Set<TableBean> includeBeanList) {
		this.includeBeanList = includeBeanList;
	}

	public void addIncludeEntityBean(TableBean tableBean) {
		if (this.includeBeanList == null) {
			this.includeBeanList = new HashSet<TableBean>();
		}
		if (!this.includeBeanList.contains(tableBean)) {
			this.includeBeanList.add(tableBean);
		}
	}

	public void addIncludeEntityBean(Set<TableBean> includeBeanList) {
		if (this.includeBeanList == null) {
			this.includeBeanList = new HashSet<TableBean>();
		}
		if (!this.includeBeanList.containsAll(includeBeanList)) {
			this.includeBeanList.addAll(includeBeanList);
		}
	}

	public Set<DomainBean> getIncludeDomainBeanList() {
		return includeDomainBeanList;
	}

	public void setIncludeDomainBeanList(Set<DomainBean> includeDomainBeanList) {
		this.includeDomainBeanList = includeDomainBeanList;
	}

	public void addIncludeDomainBean(DomainBean domainBean) {
		if (this.includeDomainBeanList == null) {
			this.includeDomainBeanList = new HashSet<DomainBean>();
		}
		if (!this.includeDomainBeanList.contains(domainBean)) {
			this.includeDomainBeanList.add(domainBean);
		}
	}

	public void addIncludeDomainBean(Set<DomainBean> includeDomainBeanList) {
		if (this.includeDomainBeanList == null) {
			this.includeDomainBeanList = new HashSet<DomainBean>();
		}
		if (!this.includeDomainBeanList.containsAll(includeDomainBeanList)) {
			this.includeDomainBeanList.addAll(includeDomainBeanList);
		}
	}

	public MethodBean getRunningMethod() {
		return runningMethod;
	}

	public void setRunningMethod(MethodBean runningMethod) {
		this.runningMethod = runningMethod;
	}

	public void setDaoMethodList(Set<MethodBean> daoMethodList) {
		this.daoMethodList = daoMethodList;
	}

	public Object clone() {
		DaoBean o = null;
		try {
			o = (DaoBean) super.clone();
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
