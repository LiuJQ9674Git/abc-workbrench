#import "${IOSPrefix}${tableBean.tableNameCapitalized}.h"

@implementation ${IOSPrefix}${tableBean.tableNameCapitalized}
	
- (instancetype)init{
    
    
    if ((self = [super init])!=nil) {
        //
    }
    return self;
}

- (id)copyWithZone:(NSZone *)zone{
    
    
    return self;
}

-(NSString *)description

{   <#assign czsum=0>
    return [NSString stringWithFormat:@"<#list tableBean.columnBeanList as columnBean><#if ('' != columnBean.columnTypeDescriptionIOS)> ${columnBean.columnNameCapitalized}   ${columnBean.columnTypeDescriptionIOS}</#if></#list> ",
<#list tableBean.columnBeanList as columnBean>
<#if ('' != columnBean.columnTypeDescriptionIOS)>
   self.${columnBean.columnNameNoDash} <#if columnBean_has_next>,</#if>
   </#if>
 </#list>
 ];
}

@end
