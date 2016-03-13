package com.ndl.framework.workbrench.define;
/**
 * 哪个变量是临时对象，无需保存在数据中
 */
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
@XmlRootElement(name = "TransientBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransientBean implements Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8295479627309367055L;

	//大写的映射，即实体的类名称
	@XmlElement
	private String tableNameCapitalized;
	
	//对应的表名,主表对应的表名
	@XmlElement
	private String tableName;


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@XmlElement(name = "columnBean")
    @XmlElementWrapper(name="columnBeanList")
	private Set<ColumnBean> columnBeanList = new HashSet<ColumnBean>();
	
	@XmlElement(name = "excludeColumnList")
    @XmlElementWrapper(name="excludeColumnList")
	private Set<ColumnBean> excludeColumnList = new HashSet<ColumnBean>();

	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 


    public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public TransientBean() {
    	uuid=SequenceGenerator.obtainSequenceUUID(TransientBean.class);
	}
    
    
    
	public Set<ColumnBean> getExcludeColumnList() {
		return excludeColumnList;
	}

	//某个表中标不包含的字段
	public void setExcludeColumnList(Set<ColumnBean> excludeColumnList) {
		this.excludeColumnList = excludeColumnList;
	}
	
	public String getTableNameCapitalized() {
		return tableNameCapitalized;
	}

	public void setTableNameCapitalized(String tableNameCapitalized) {
		this.tableNameCapitalized = tableNameCapitalized;
	}

	public Set<ColumnBean> getColumnBeanList() {
		return columnBeanList;
	}

	public void setColumnBeanList(Set<ColumnBean> columnBeanList) {
		this.columnBeanList = columnBeanList;
	}

	public void addColumnBean(ColumnBean columnBean){
		if(!this.columnBeanList.contains(columnBean)){
			this.columnBeanList.add(columnBean);
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
