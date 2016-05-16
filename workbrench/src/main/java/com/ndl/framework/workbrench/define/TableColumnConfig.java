package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ndl.framework.workbrench.util.SequenceGenerator;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("restriction")
@XmlRootElement(name = "TableColumnConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class TableColumnConfig implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6798442956463320742L;

	@XmlElement(name = "excludeBean")
    @XmlElementWrapper(name="excludeBeanList")
	private Set<TransientBean> excludeBeanList = new HashSet<TransientBean>();

	@XmlElement(name = "transientBean")
    @XmlElementWrapper(name="transientBeanList")
	private Set<TransientBean> transientBeanList = new HashSet<TransientBean>();

	@XmlElement(name = "filterBean")
    @XmlElementWrapper(name="filterBeanList")
	Set<TransientBean> filterBeanList=new CopyOnWriteArraySet<TransientBean>();
	
	@XmlElement(name = "excludeColomn")
    @XmlElementWrapper(name="excludeColomnFramework")
	Set<String> excludeColomnFramework=new CopyOnWriteArraySet<String>();

	@XmlAttribute
	@XmlID // should be unique across all entities.
	private String uuid;

	public TableColumnConfig() {
    	uuid=SequenceGenerator.obtainSequenceUUID(TableColumnConfig.class);
	}

	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	
	public Set<String> getExcludeColomnFramework() {
		return excludeColomnFramework;
	}

	public void setExcludeColomnFramework(Set<String> excludeColomnFramework) {
		this.excludeColomnFramework=excludeColomnFramework;
	}


	public Set<TransientBean> getFilterBeanList() {
		return filterBeanList;
	}

	public void setFilterBeanList(Set<TransientBean> filterBeanList) {
		this.filterBeanList = filterBeanList;
	}

	public Set<TransientBean> getExcludeBeanList() {
		return excludeBeanList;
	}

	public void setExcludeBeanList(Set<TransientBean> excludeBeanList) {
		this.excludeBeanList = excludeBeanList;
	}

	public void addExcludeBean(TransientBean transientBean) {
		if (this.excludeBeanList == null) {
			this.excludeBeanList = new HashSet<TransientBean>();
		}
		this.excludeBeanList.add(transientBean);
	}

	public Set<TransientBean> getTransientBeanList() {
		return transientBeanList;
	}

	public void setTransientBeanList(Set<TransientBean> transientBeanList) {
		this.transientBeanList = transientBeanList;
	}

	public void addTransientBean(TransientBean transientBean) {
		if (this.transientBeanList == null) {
			this.transientBeanList = new HashSet<TransientBean>();
		}
		this.transientBeanList.add(transientBean);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
