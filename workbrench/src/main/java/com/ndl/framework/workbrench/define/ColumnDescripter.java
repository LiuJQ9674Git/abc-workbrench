package com.ndl.framework.workbrench.define;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("restriction")
@XmlRootElement(name = "ColumnBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class ColumnDescripter  implements Cloneable,Serializable{

	private static final long serialVersionUID = 1252537586665592862L;

		// 字段名称
		@XmlElement
		private String columnName=null;
		// 属性名称
		@XmlElement(name="fieldName")
		private String columnNameNoDash=null;
		// 符合Java命名规范的变量名称，类型首字母大写
		@XmlElement(name="fieldNameSetter")
		private String columnNameCapitalized=null;
		
		// Java规定的基本类型
		@XmlElement(name="javaType")
		private String columnType=null;
		
		@XmlElement(name="iosType")
		private String columnTypeIOS=null;
		
		@XmlElement(name="iosProperty")
		private String columnTypePropertyIOS=null;
		
		@XmlElement(name="iosFieldDescription")
		private String columnTypeDescriptionIOS;
		
		@XmlElement(name="jsType")
		private String columnTypeJS=null;
		
		// getters
		@XmlElement
		private String columnTypeRsGetter=null;
		
		// 说明信息
		@XmlElement(name="fieldComment")
		private String columnComment=null;
		//
		@XmlElement
		private String ordinalPosition=null;
		// 是否为空,YES可以为空，NO不可为空
		@XmlElement
		private String isNullable=null;
		
		// 缺省值
		@XmlElement(name="defaultValue")
		private String columnDefault=null;

		//作为参数时，下一个参数关系符描述
		@XmlElement
		private FieldRativeEnum nextFieldRative=FieldRativeEnum.Finished;
		
		//当作为方法体时，由返回方法获取具体方法的返回值类型及其调用类型
		@XmlElement
		private MethodRuntimeEnum returnTypeCall;

		/**
		 * PRI，数据库主键，如果是TST（transient），则说明非数据库存储字段。columnKey
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
		 */
		@XmlElement
		private String extra=null;

		
		/**
		 * 参考FieldTypeEnum,默认为基本类型
		 * 	PRIMARY,//Java基本类型
		 *	PRI,//基本类型并且为数据库主键
		 *	LIST,//则为ArrayList<JavaBean>
		 *	MAPKEY,//则为HashMap< objKey, JavaBean>
	     *	ENTITY,//实体Bean
	     *	DOMAIN,//域模型
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

		public static ColumnDescripter convertByColumnBean(ColumnBean columnBean){
			ColumnDescripter columnDescripter=new ColumnDescripter();
			columnDescripter.setColumnComment(columnBean.getColumnComment());
			columnDescripter.setColumnDefault(columnBean.getColumnDefault());
			columnDescripter.setColumnKey(columnBean.getColumnKey());
			columnDescripter.setColumnName(columnBean.getColumnName());
			columnDescripter.setColumnNameCapitalized(columnBean.getColumnNameCapitalized());
			columnDescripter.setColumnNameNoDash(columnBean.getColumnNameNoDash());
			columnDescripter.setColumnType(columnBean.getColumnType());
			columnDescripter.setColumnTypeDescriptionIOS(columnBean.getColumnTypeDescriptionIOS());
			columnDescripter.setColumnTypeIOS(columnBean.getColumnTypeIOS());
			columnDescripter.setColumnTypeJS(columnBean.getColumnTypeJS());
			columnDescripter.setColumnTypePropertyIOS(columnBean.getColumnTypeRsGetter());
			columnDescripter.setColumnTypeRsGetter(columnBean.getColumnTypeRsGetter());
			columnDescripter.setExtra(columnBean.getExtra());
			columnDescripter.setFieldType(columnBean.getFieldType());
			columnDescripter.setIsNullable(columnBean.getIsNullable());
			columnDescripter.setLength(columnBean.getLength());
			columnDescripter.setNextFieldRative(columnBean.getNextFieldRative());
			columnDescripter.setOrdinalPosition(columnBean.getOrdinalPosition());
			columnDescripter.setReturnTypeCall(columnBean.getReturnTypeCall());
			return columnDescripter;
		}
		
		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnNameNoDash() {
			return columnNameNoDash;
		}

		public void setColumnNameNoDash(String columnNameNoDash) {
			this.columnNameNoDash = columnNameNoDash;
		}

		public String getColumnNameCapitalized() {
			return columnNameCapitalized;
		}

		public void setColumnNameCapitalized(String columnNameCapitalized) {
			this.columnNameCapitalized = columnNameCapitalized;
		}

		public String getColumnType() {
			return columnType;
		}

		public void setColumnType(String columnType) {
			this.columnType = columnType;
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

		public String getColumnTypeRsGetter() {
			return columnTypeRsGetter;
		}

		public void setColumnTypeRsGetter(String columnTypeRsGetter) {
			this.columnTypeRsGetter = columnTypeRsGetter;
		}

		public String getColumnComment() {
			return columnComment;
		}

		public void setColumnComment(String columnComment) {
			this.columnComment = columnComment;
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

		public FieldRativeEnum getNextFieldRative() {
			return nextFieldRative;
		}

		public void setNextFieldRative(FieldRativeEnum nextFieldRative) {
			this.nextFieldRative = nextFieldRative;
		}

		public MethodRuntimeEnum getReturnTypeCall() {
			return returnTypeCall;
		}

		public void setReturnTypeCall(MethodRuntimeEnum returnTypeCall) {
			this.returnTypeCall = returnTypeCall;
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

		public FieldTypeEnum getFieldType() {
			return fieldType;
		}

		public void setFieldType(FieldTypeEnum fieldType) {
			this.fieldType = fieldType;
		}

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Object clone() {
			ColumnDescripter o = null;
			try {
				o = (ColumnDescripter) super.clone();
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
