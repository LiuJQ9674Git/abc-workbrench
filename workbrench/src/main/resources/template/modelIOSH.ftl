#import <Foundation/Foundation.h>


@interface ${IOSPrefix}${tableBean.tableNameCapitalized} : NSObject
	
	
<#list tableBean.columnBeanList as columnBean>
    <#if ('' != columnBean.columnComment &&columnBean.columnKey=='DEFUALT')>
    /**
     * ${columnBean.columnComment}
     **/
    </#if>
    <#if ('' != columnBean.columnType &&columnBean.columnKey=='DEFUALT')>
    @${columnBean.columnTypePropertyIOS} ${columnBean.columnNameNoDash} ;
    </#if>
</#list>
 
 <#list tableBean.transientColumnBeanList as columnBean>
    <#if ('' != columnBean.columnComment)>
    /**
     * ${columnBean.columnComment}
     **/
    </#if>
    <#if ('' != columnBean.columnType)>
    @${columnBean.columnTypePropertyIOS} ${columnBean.columnNameNoDash} ;
    </#if>
</#list>
@end