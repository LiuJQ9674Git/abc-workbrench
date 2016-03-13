package com.ndl.framework.workbrench.define;
import java.io.Serializable;
/**
 * 类型描述
 */
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
@XmlRootElement(name="TableBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class TableBean implements Cloneable,Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3304280123229775430L;

	@XmlElement
    private String tableName;
    
    @XmlElement
    private String tableComment;

    /**
     * 首字母小写，变量命名
     */
    @XmlElement
    private String tableNameNoDash;
    
    /**
     * 首字母大写，类命名方式
     */
    @XmlElement
    private String tableNameCapitalized;
    
    //主键名称
    @XmlElement
    private String pkName;
    //主键类别
    @XmlElement
    private String pkType;
    
    //主键数据库的字段
    @XmlElement
    private String pkColumn;
    
    //主键列名
    @XmlElement
    private String pkColumnName;
    
    
    @XmlElement
    private String pkColumnNameCapitalized;

    @XmlElement(name="columnBean")
    @XmlElementWrapper(name="columnBeanList")
    private Set<ColumnBean> columnBeanList = new HashSet<ColumnBean>();
    
    @XmlElement(name="transientColumnBean")
    @XmlElementWrapper(name="transientColumnBeanList")
    private Set<ColumnBean> transientColumnBeanList= new HashSet<ColumnBean>(); ;
    
    @XmlElement
    private ColumnBean methodArugment;
    
    /**
     * 首字母小写，变量命名
     */
    @XmlElement
    private String referTableNameNoDash;
    
    /**
     * 首字母大写，类命名方式
     */
    @XmlElement
    private String referTableNameCapitalized;

    @XmlElement
    private boolean hasDateColumn;

    @XmlElement
    private boolean hasBigDecimal;
    
	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 

	
	
    public String getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(String pkColumn) {
		this.pkColumn = pkColumn;
	}

	public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public String getReferTableNameNoDash() {
		return referTableNameNoDash;
	}

	public void setReferTableNameNoDash(String referTableNameNoDash) {
		this.referTableNameNoDash = referTableNameNoDash;
	}

	public String getReferTableNameCapitalized() {
		return referTableNameCapitalized;
	}

	public void setReferTableNameCapitalized(String referTableNameCapitalized) {
		this.referTableNameCapitalized = referTableNameCapitalized;
	}

	public ColumnBean getMethodArugment() {
		return methodArugment;
	}

	public void setMethodArugment(ColumnBean methodArugment) {
		this.methodArugment = methodArugment;
	}

	public TableBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(TableBean.class);
	}
    
    
    public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getPkType() {
		return pkType;
	}

	public void setPkType(String pkType) {
		this.pkType = pkType;
	}

	
	public String getPkColumnName() {
		return pkColumnName;
	}

	public void setPkColumnName(String pkColumnName) {
		this.pkColumnName = pkColumnName;
	}

	
	public String getPkColumnNameCapitalized() {
		return pkColumnNameCapitalized;
	}

	public void setPkColumnNameCapitalized(String pkColumnNameCapitalized) {
		this.pkColumnNameCapitalized = pkColumnNameCapitalized;
	}

	/*
    public void addColumn(ColumnBean columnBean) {
        columnBeanList.add(columnBean);
    }
	*/
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Set<ColumnBean> getColumnBeanList() {
        return columnBeanList;
    }

    public void setColumnBeanList(Set<ColumnBean> columnBeanList) {
        this.columnBeanList = columnBeanList;
    }

    
    
    public Set<ColumnBean> getTransientColumnBeanList() {
		return transientColumnBeanList;
	}

	public void setTransientColumnBeanList(Set<ColumnBean> transientColumnBeanList) {
		this.transientColumnBeanList = transientColumnBeanList;
	}

	public String getTableNameNoDash() {
        return tableNameNoDash;
    }

    public void setTableNameNoDash(String tableNameNoDash) {
        this.tableNameNoDash = tableNameNoDash;
    }

    public String getTableNameCapitalized() {
        return tableNameCapitalized;
    }

    public void setTableNameCapitalized(String tableNameCapitalized) {
        this.tableNameCapitalized = tableNameCapitalized;
    }

    public boolean isHasDateColumn() {
        return hasDateColumn;
    }

    public void setHasDateColumn(boolean hasDateColumn) {
        this.hasDateColumn = hasDateColumn;
    }

    public boolean isHasBigDecimal() {
        return hasBigDecimal;
    }

    public void setHasBigDecimal(boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }

    public Object clone() {  
    	TableBean o = null;  
        try {  
            o = (TableBean) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tableNameCapitalized == null) ? 0 : tableNameCapitalized.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableBean other = (TableBean) obj;
		if (tableNameCapitalized == null) {
			if (other.tableNameCapitalized != null)
				return false;
		} else if (!tableNameCapitalized.equals(other.tableNameCapitalized))
			return false;
		return true;
	}
	
}
