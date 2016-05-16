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

/**
 * 方法签名描述 MethodBean提供关于类或接口上单独某个方法（以及如何访问该方法）的信息。
 */

@SuppressWarnings("restriction")
@XmlRootElement(name = "MethodBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodBean implements Cloneable,Serializable {

	@XmlTransient
	private final MethodDescripter methodDescripter;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7430227656365809913L;

	@XmlAttribute
	@XmlID // should be unique across all entities.
	private String uuid;

	/**
	 * 方法名称
	 */
	@XmlElement
	private String signature;
	/**
	 * 方法注释信息
	 */
	@XmlElement
	private String signatureDescripter;
	/**
	 * 方法返回类型
	 */
	@XmlElement
	private ColumnBean responseType;

	@XmlElement
	private MethCategoryEnum catetory;

	//在其他类方法中的运行时关系
	@XmlElement
	private MethodRuntimeEnum methodRuntimeTypeInBody;
	
	@XmlElement
	//@XmlIDREF
	private MethodCallChainInBodyBean bodyBean;

	@XmlElement
	private AnnotationBean returnAnnotation;

	/**
	 * 当作为入参时，参数的注解 如：MyBatis的param，Spring-MVC的ModelAttribute、PathVariable等
	 */
	@XmlElement
	private AnnotationBean annotationBean;

	@XmlElement
	ServiceBean calleeMajorService;

	@XmlElement
	DaoBean callMajorDAOService;
	/**
	 * 参数列表，此处取出TableBean的tableNameCapitalized和tableNameNoDash Person
	 * person,用于多个类作为参数,如果List的数量大于1则表示以实体类或者模型做为参数。
	 */
	@XmlElement(name = "assign")
	private TableBean assignList = null; // 类型和变量名称

	@XmlElement(name = "assignDomain")
	@XmlElementWrapper(name="assignDomainList")
	private Set<DomainBean> assignDomainList = null;

	// 方法内使用的Service、DAO和实体
	/**
	 * 控制器只有业务服务，业务服务可以有业务服务
	 */

	@XmlElement(name = "callBusinessService")
	@XmlElementWrapper(name="callBusinessServiceList")
	private Set<ServiceBean> callBusinessServices = null;

	/**
	 * 调用原子服务列表,如果是控制器调用的方法，则此项为null
	 */

	@XmlElement(name = "callAtomService")
	@XmlElementWrapper(name="callAtomServicesList")
	private Set<ServiceBean> callAtomServices = null;

	/**
	 * 如果是原子服务只能调用DAO
	 */
	@XmlElement(name = "callDAOService")
	@XmlElementWrapper(name="callDAOServiceList")
	private Set<DaoBean> callDAOServices = null;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	public MethodBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(MethodBean.class);
    	methodDescripter=new MethodDescripter();
	}

	
	public MethodRuntimeEnum getMethodRuntimeTypeInBody() {
		return methodRuntimeTypeInBody;
	}

	public void setMethodRuntimeTypeInBody(MethodRuntimeEnum methodRuntimeTypeInBody) {
		this.methodRuntimeTypeInBody = methodRuntimeTypeInBody;
	}

	public MethodDescripter getMethodDescripter() {
		return methodDescripter;
	}

	public MethodCallChainInBodyBean getBodyBean() {
		return bodyBean;
	}

	public void setBodyBean(MethodCallChainInBodyBean bodyBean) {
		this.bodyBean = bodyBean;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignatureDescripter() {
		return signatureDescripter;
	}

	public void setSignatureDescripter(String signatureDescripter) {
		this.signatureDescripter = signatureDescripter;
	}

	public ColumnBean getResponseType() {
		return responseType;
	}

	public void setResponseType(ColumnBean responseType) {
		this.responseType = responseType;
	}

	public TableBean getAssignList() {
		return assignList;
	}

	public void setAssignList(TableBean assignList) {
		this.assignList = assignList;
	}

	
	public MethCategoryEnum getCatetory() {
		return catetory;
	}

	public void setCatetory(MethCategoryEnum catetory) {
		this.catetory = catetory;
	}

	public Set<ServiceBean> getCallBusinessServices() {
		return callBusinessServices;
	}

	public void setCallBusinessServices(Set<ServiceBean> callBusinessServices) {
		this.callBusinessServices = callBusinessServices;
	}

	public void addCallBusinessService(ServiceBean serviceBean) {
		if (callBusinessServices == null) {
			callBusinessServices = new HashSet<ServiceBean>();
		}
		if (!callBusinessServices.contains(serviceBean)) {
			this.callBusinessServices.add(serviceBean);
		}
	}

	public void addCallBusinessService(Set<ServiceBean> callBusinessServiceList) {
		if (this.callBusinessServices == null) {
			this.callBusinessServices = new HashSet<ServiceBean>();
		}
		if (!this.callBusinessServices.containsAll(callBusinessServiceList)) {
			this.callBusinessServices.addAll(callBusinessServiceList);
		}
	}

	public Set<ServiceBean> getCallAtomServices() {
		return callAtomServices;
	}

	public void setCallAtomServices(Set<ServiceBean> callAtomServices) {
		this.callAtomServices = callAtomServices;
	}

	public void addCallAtomService(ServiceBean serviceBean) {
		if (callAtomServices == null) {
			callAtomServices = new HashSet<ServiceBean>();
		}
		if (!callAtomServices.contains(callAtomServices)) {
			this.callAtomServices.add(serviceBean);
		}
	}

	public void addCallAtomService(Set<ServiceBean> callAtomServiceList) {
		if (this.callAtomServices == null) {
			this.callAtomServices = new HashSet<ServiceBean>();
		}
		if (!this.callAtomServices.containsAll(callAtomServiceList)) {
			this.callAtomServices.addAll(callAtomServiceList);
		}
	}

	public Set<DaoBean> getCallDAOServices() {
		return callDAOServices;
	}

	public void setCallDAOServices(Set<DaoBean> callDAOServices) {
		this.callDAOServices = callDAOServices;
	}

	public void addCallDAOService(DaoBean callDAOService) {
		if (this.callDAOServices == null) {
			this.callDAOServices = new HashSet<DaoBean>();
		}
		if (!callDAOServices.contains(callDAOService)) {
			this.callDAOServices.add(callDAOService);
		}
	}

	public void addCallDAOService(Set<DaoBean> callDAOServiceList) {
		if (this.callDAOServices == null) {
			this.callDAOServices = new HashSet<DaoBean>();
		}
		if (!this.callDAOServices.containsAll(callDAOServiceList)) {
			this.callDAOServices.addAll(callDAOServiceList);
		}
	}

	public Set<DomainBean> getAssignDomainList() {
		return assignDomainList;
	}

	public void setAssignDomainList(Set<DomainBean> assignDomainList) {
		this.assignDomainList = assignDomainList;
	}

	public void addAssignDomain(DomainBean domainBean) {
		if (this.assignDomainList == null) {
			this.assignDomainList = new HashSet<DomainBean>();
		}
		if (!assignDomainList.contains(domainBean)) {
			this.assignDomainList.add(domainBean);
		}
	}

	public void addAssignDomain(Set<DomainBean> assignDomainList) {
		if (this.assignDomainList == null) {
			this.assignDomainList = new HashSet<DomainBean>();
		}
		if (!this.assignDomainList.containsAll(assignDomainList)) {
			this.assignDomainList.addAll(assignDomainList);
		}
	}

	public AnnotationBean getReturnAnnotation() {
		return returnAnnotation;
	}

	public void setReturnAnnotation(AnnotationBean returnAnnotation) {
		this.returnAnnotation = returnAnnotation;
	}

	public AnnotationBean getAnnotationBean() {
		return annotationBean;
	}

	public void setAnnotationBean(AnnotationBean annotationBean) {
		this.annotationBean = annotationBean;
	}

	public ServiceBean getCalleeMajorService() {
		return calleeMajorService;
	}

	public void setCalleeMajorService(ServiceBean calleeMajorService) {
		this.calleeMajorService = calleeMajorService;
	}

	public DaoBean getCallMajorDAOService() {
		return callMajorDAOService;
	}

	public void setCallMajorDAOService(DaoBean callMajorDAOService) {
		this.callMajorDAOService = callMajorDAOService;
	}

	public Object clone() {
		MethodBean o = null;
		try {
			o = (MethodBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
