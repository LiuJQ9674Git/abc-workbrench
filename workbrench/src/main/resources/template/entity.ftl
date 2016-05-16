<#setting number_format="#">
package ${packageBase}.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import com.ndlan.canyin.base.entity.BaseEntity;
import java.util.List;
import java.util.Map;

<#if (tableBean.hasDateColumn)>
import java.util.Date;
</#if>

<#if (tableBean.hasBigDecimal)>
import java.math.BigDecimal;
</#if>

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "${tableBean.tableName}" )
public class ${tableBean.tableNameCapitalized}${classSuffix}  extends BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID =-1L;
	
	<#--变量信息 -->
<#list tableBean.columnBeanList as columnBean>
    <#if ('' != columnBean.columnComment)>
    /**
     * ${columnBean.columnComment}
     **/
    </#if>
    <#if columnBean.columnKey == "PRI">
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    </#if>
    <#if ('' != columnBean.columnType)>
    @Column(name = "${columnBean.columnName}"
    </#if>
    <#if columnBean.length != 0&&'' != columnBean.columnType> ,length = ${columnBean.length}</#if>
    <#if columnBean.isNullable == "NO"&&'' != columnBean.columnType> ,nullable = false</#if>
    <#if columnBean.columnKey == "UNI"&&'' != columnBean.columnType> ,unique = true</#if>
    <#if columnBean.columnKey == "PRI"&&'' != columnBean.columnType> ,unique = true</#if>
<#if ('' != columnBean.columnType)>
    )
    private ${columnBean.columnType} ${columnBean.columnNameNoDash};
</#if>
</#list>

<#list tableBean.transientColumnBeanList as columnBean>
 <#if ('' != columnBean.columnType)>
      private ${columnBean.columnType} ${columnBean.columnNameNoDash};
  </#if>
</#list>

   

<#list tableBean.columnBeanList as columnBean>
 <#if ('' != columnBean.columnType)>
    public void set${columnBean.columnNameCapitalized}(${columnBean.columnType} ${columnBean.columnNameNoDash}) {
        this.${columnBean.columnNameNoDash} = ${columnBean.columnNameNoDash};
    }

    public ${columnBean.columnType} get${columnBean.columnNameCapitalized}() {
        return ${columnBean.columnNameNoDash};
    }
</#if>
</#list>

<#list tableBean.transientColumnBeanList as columnBean>
 <#if ('' != columnBean.columnType)>
 public void set${columnBean.columnNameCapitalized}(${columnBean.columnType} ${columnBean.columnNameNoDash}) {
        this.${columnBean.columnNameNoDash} = ${columnBean.columnNameNoDash};
    }

    public ${columnBean.columnType} get${columnBean.columnNameCapitalized}() {
        return ${columnBean.columnNameNoDash};
    }
</#if>
</#list>

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
