package ${packageBase}.query;

import ${packageBase}.model.${tableBean.tableNameCapitalized}${classSuffix};
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ${tableBean.tableNameCapitalized}${classQuerySuffix} extends ${tableBean.tableNameCapitalized}${classSuffix}{

	private static final long serialVersionUID = 4587505733381942426L;

	<#list tableBean.columnBeanList as columnBean>
	 <#if columnBean.columnName?index_of("name")!=-1>
	 /**
	 * ${columnBean.columnComment}
	 **/
     	 private ${columnBean.columnType} ${columnBean.columnNameNoDash}Like;
	 </#if>
	</#list>

	<#list tableBean.columnBeanList as columnBean>
	 <#if columnBean.columnName?index_of("name")!=-1>
         public void set${columnBean.columnNameCapitalized}Like(${columnBean.columnType} ${columnBean.columnNameNoDash}Like) {
	    this.${columnBean.columnNameNoDash}Like = ${columnBean.columnNameNoDash}Like;
	 }

	 public ${columnBean.columnType} get${columnBean.columnNameCapitalized}Like() {
            return ${columnBean.columnNameNoDash}Like;
         }
	</#if>
	</#list>
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
