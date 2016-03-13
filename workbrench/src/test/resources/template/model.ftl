package ${packageBase}.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
<#if (tableBean.hasDateColumn)>
import java.util.Date;
</#if>
<#if (tableBean.hasBigDecimal)>
import java.math.BigDecimal;
</#if>

public class ${tableBean.tableNameCapitalized}${classSuffix} implements Identifiable{

	private static final long serialVersionUID =-1;
	
<#list tableBean.columnBeanList as columnBean>
    <#if ('' != columnBean.columnComment)>
    /**
     * ${columnBean.columnComment}
     **/
    </#if>
    <#if ('' != columnBean.columnType)>
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
<#list tableBean.columnBeanList as columnBean>
	<#if columnBean.columnKey == "PRI">
	@Override
	public ${columnBean.columnType} getDefaultId() {
		// TODO Auto-generated method stub
	     return ${columnBean.columnNameNoDash};
        }

	@Override
	public void setDefaultId(${columnBean.columnType} ${columnBean.columnNameNoDash}) {
	     this.${columnBean.columnNameNoDash}=${columnBean.columnNameNoDash};
       }
</#if>
</#list>
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
