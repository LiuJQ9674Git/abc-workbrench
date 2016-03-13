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

import com.ndl.framework.workbrench.exception.ConfigRuntimeException;
import com.ndl.framework.workbrench.util.SequenceGenerator;
import com.ndl.framework.workbrench.util.WorkBrenchConfigProperty;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("restriction")
@XmlRootElement(name="EntityConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomainBean implements Cloneable,Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4710193355844257383L;

	@XmlElement
    private String domainType;
    
    @XmlElement
    private String domainComment;

    /**
     * 首字母小写，变量命名
     */
    @XmlElement
    private String domainNameNoDash;
    
    /**
     * 首字母大写，类命名方式
     */
    @XmlElement
    private String tableNameCapitalized;
    
    @XmlElement(name="tableBean")
    @XmlElementWrapper(name="tableBeanList")
    private Set<TableBean> tableBeanList = new HashSet<TableBean>();
    
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public DomainBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(DomainBean.class);
    	throw new ConfigRuntimeException(
				WorkBrenchConfigProperty.PROCESS_CONFIGURE_EXCETPION_NO_REALIZE);
    }
    
    public Set<TableBean> getTableBeanList() {
        return tableBeanList;
    }

    public void setTableBeanList(Set<TableBean> tableBeanList) {
        this.tableBeanList = tableBeanList;
    }

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public String getDomainComment() {
		return domainComment;
	}

	public void setDomainComment(String domainComment) {
		this.domainComment = domainComment;
	}

	public String getDomainNameNoDash() {
		return domainNameNoDash;
	}

	public void setDomainNameNoDash(String domainNameNoDash) {
		this.domainNameNoDash = domainNameNoDash;
	}

	public String getTableNameCapitalized() {
		return tableNameCapitalized;
	}

	public void setTableNameCapitalized(String tableNameCapitalized) {
		this.tableNameCapitalized = tableNameCapitalized;
	}

	public Object clone() {
		DomainBean o = null;
		try {
			o = (DomainBean) super.clone();
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
