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

@SuppressWarnings("restriction")
@XmlRootElement(name = "ColumnBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class ColumnBean implements Cloneable,Serializable{
	
	// 字段名称
	@XmlElement
	private String columnName="";
	// 属性名称
	@XmlElement
	private String columnNameNoDash="";
	// 符合Java命名规范的变量名称，类型首字母大写
	@XmlElement
	private String columnNameCapitalized="";
	// Java规定的基本类型
	@XmlElement
	private String columnType="";
	@XmlElement//数据库中的字段名称，
	private String columnDBType;
	@XmlElement//数据库中的字段名称，
	private String columnJDBCType;
	@XmlElement
	private String columnTypeIOS="";
	@XmlElement
	private String columnTypePropertyIOS="";
	@XmlElement
	private String columnTypeDescriptionIOS;
	@XmlElement
	private String columnTypeJS="";
	// getters
	@XmlElement
	private String columnTypeRsGetter="";
	//在传参时，对应的引用变量
	// 属性名称
	@XmlElement
	private String referNameNoDash="";
	// 符合Java命名规范的变量名称，类型首字母大写，在传参时，对应的引用实体
	@XmlElement
	private String referNameCapitalized="";
	// Java规定的基本类型，对应的引用实体
	@XmlElement
	private String referType="";
	
	@XmlElement
	private String referExtra = null;

	@XmlElement
	private FieldTypeEnum referRieldType = FieldTypeEnum.PRIMARY;

	// 字段长度，将在计算属性类型时赋值
	@XmlElement
	private long referLength;
		
	// 说明信息
	@XmlElement
	private String columnComment="";
	//
	@XmlElement
	private String ordinalPosition="";
	// 是否为空,YES可以为空，NO不可为空
	@XmlElement
	private String isNullable="";
	
	// 缺省值
	@XmlElement
	private String columnDefault="";
	
	// 变量的可变性，如果使用IOS，默认采用copy，默认遵守相关语言的规定，
	//在平时使用NSArray，NSDictionary以及NSString的过程中，经常会默认写了strong 属性，或者按照规范使用Copy，
	//在Apple的规范中，是提倡我们使用copy的属性。
	@XmlElement
	private Boolean isMutable=true;

	//作为参数时，下一个参数关系符描述
	@XmlElement
	private FieldRativeEnum nextFieldRative=FieldRativeEnum.Finished;
	
	@XmlElement//当作为方法体时，由返回方法获取具体方法的返回值类型及其调用类型
	private MethodRuntimeEnum returnTypeCall;

	@XmlElement
	private ColumnBean nextColumnBean=null;
	
	/**
	 * PRI，数据库主键，如果是TST（transient），则说明非数据库存储字段。columnKey
	 * 如果是持久化字段，此列以数据库读取的标识为主
	 */
	@XmlElement
	private String columnKey=null;

	// auto_increment
	/**
	 * 参考FieldTypeEnum
	 * 当时columnKey为TST（transient）时，columnType描述为基本基本类型或者是JavaBean，
	 * 如果extra为List，则为ArrayList<JavaBean>，
	 * 如果为Map|objKey，则为HashMap< objKey, JavaBean>。
	 * 如果extra中包含JsonIgnore则需要在变量上加上相应注解。
	 * 目前只有是Map时，配置objKey
	 */
	@XmlElement
	private String extra=null;

	
	public String getReferExtra() {
		return referExtra;
	}

	public void setReferExtra(String referExtra) {
		this.referExtra = referExtra;
	}

	public FieldTypeEnum getReferRieldType() {
		return referRieldType;
	}

	public void setReferRieldType(FieldTypeEnum referRieldType) {
		this.referRieldType = referRieldType;
	}

	public long getReferLength() {
		return referLength;
	}

	public void setReferLength(long referLength) {
		this.referLength = referLength;
	}

	/**
	 * 参考FieldTypeEnum,默认为基本类型
	 * 	PRIMARY,//Java基本类型
	 *	PRI,//基本类型并且为数据库主键
	 *	LIST,//则为ArrayList<JavaBean>
	 *	MAPKEY,//则为HashMap< objKey, JavaBean>
     *	ENTITY,//实体Bean
     *	DOMAIN,//域模型，即transient
     *	Page,//实体分页
	 *	NORETURN//在返回值时表示无返回值，在入参时表示无入参
	 *
	 * 当时columnKey为TST（transient）时，columnType描述为基本基本类型或者是JavaBean，
	 * 如果为Map|objKey，则为HashMap< objKey, JavaBean>。一般应当为主键
	 * 如果extra中包含JsonIgnore则需要在变量上加上相应注解。
	 */
	@XmlElement
	private FieldTypeEnum fieldType=FieldTypeEnum.PRIMARY;
	
	//字段长度，将在计算属性类型时赋值
	@XmlElement
	private long length;

	@XmlAttribute
    @XmlID	    		// should be unique across all entities.
    private String uuid; 

	
  

	public Boolean getIsMutable() {
		return isMutable;
	}

	public void setIsMutable(Boolean isMutable) {
		this.isMutable = isMutable;
	}

	public String getColumnDBType() {
		return columnDBType;
	}

	public void setColumnDBType(String columnDBType) {
		this.columnDBType = columnDBType;
	}

	public String getColumnJDBCType() {
		return columnJDBCType;
	}

	public void setColumnJDBCType(String columnJDBCType) {
		this.columnJDBCType = columnJDBCType;
	}

	public String getReferNameNoDash() {
		return referNameNoDash;
	}

	public void setReferNameNoDash(String referNameNoDash) {
		this.referNameNoDash = referNameNoDash;
	}

	public String getReferNameCapitalized() {
		return referNameCapitalized;
	}

	public void setReferNameCapitalized(String referNameCapitalized) {
		this.referNameCapitalized = referNameCapitalized;
	}

	public String getReferType() {
		return referType;
	}

	public void setReferType(String referType) {
		this.referType = referType;
	}

	public MethodRuntimeEnum getReturnTypeCall() {
		return returnTypeCall;
	}

	public void setReturnTypeCall(MethodRuntimeEnum returnTypeCall) {
		this.returnTypeCall = returnTypeCall;
	}

	public String getUuid() {
        return uuid; 
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }
    
    public ColumnBean() {
     	uuid=SequenceGenerator.obtainSequenceUUID(ColumnBean.class);
    }
    
        
	public String getColumnTypeIOS() {
		return columnTypeIOS;
	}

	public void setColumnTypeIOS(String columnTypeIOS) {
		this.columnTypeIOS = columnTypeIOS;
	}

	public String getColumnTypePropertyIOS() {
		return columnTypePropertyIOS;
	}

	public void setColumnTypePropertyIOS(String columnTypePropertyIOS) {
		this.columnTypePropertyIOS = columnTypePropertyIOS;
	}

	
	
	public String getColumnTypeDescriptionIOS() {
		return columnTypeDescriptionIOS;
	}

	public void setColumnTypeDescriptionIOS(String columnTypeDescriptionIOS) {
		this.columnTypeDescriptionIOS = columnTypeDescriptionIOS;
	}

	public String getColumnTypeJS() {
		return columnTypeJS;
	}

	public void setColumnTypeJS(String columnTypeJS) {
		this.columnTypeJS = columnTypeJS;
	}

	public String getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getColumnNameCapitalized() {
		return columnNameCapitalized;
	}

	public String getColumnNameNoDash() {
		return columnNameNoDash;
	}

	public String getColumnType() {
		return columnType;
	}

	public String getColumnTypeRsGetter() {
		return columnTypeRsGetter;
	}

	public long getLength() {
		return length;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setColumnNameCapitalized(String columnNameCapitalized) {
		this.columnNameCapitalized = columnNameCapitalized;
	}

	public void setColumnNameNoDash(String columnNameNoDash) {
		this.columnNameNoDash = columnNameNoDash;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public void setColumnTypeRsGetter(String columnTypeRsGetter) {
		this.columnTypeRsGetter = columnTypeRsGetter;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;
	}

	public FieldRativeEnum getNextFieldRative() {
		return nextFieldRative;
	}

	public void setNextFieldRative(FieldRativeEnum nextFieldRative) {
		this.nextFieldRative = nextFieldRative;
	}

	public ColumnBean getNextColumnBean() {
		return nextColumnBean;
	}

	public void setNextColumnBean(ColumnBean nextColumnBean) {
		this.nextColumnBean = nextColumnBean;
	}

	public Object clone() {
		ColumnBean o = null;
		try {
			o = (ColumnBean) super.clone();
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
