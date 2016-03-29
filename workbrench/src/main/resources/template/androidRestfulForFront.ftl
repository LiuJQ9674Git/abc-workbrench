package ${packageBase}.${packageAndroid};
<#if '' != businessDescripter.comment>
/**
*${businessDescripter.comment}
*/
</#if>
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ndlan.framework.core.web.domain.Result;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ${packageBase}.${packageModel}.*;
import ${packageBase}.${packageModelQuery}.*;

public class ${businessDescripter.className}${androidRestfulSuffix} {

	private static final String url = BaseAndroidRestfulConfig.moduleServicePath;
	MediaType mediaType = MediaType.APPLICATION_JSON;

	private static final ExecutorService executorService = Executors.newCachedThreadPool();
	private final ObjectMapper mapper ;
	{
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.WRAP_EXCEPTIONS, false);

	}
	private static final RestTemplate restTemplate = new RestTemplate();

	{
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

<#list methodDescripterList as methodDescripter>
	<#if '' != methodDescripter.comment>
	${methodDescripter.comment}
	</#if>
	<#if '' != methodDescripter.signatureEntirety>
	${methodDescripter.signatureEntirety}{
		final String path=url+"${methodDescripter.methodHeaderAnnotation.annoteValue}";
		<#list methodDescripter.calleeArgumentList as columnDescripter>
		
		<#if ('' != columnDescripter.columnType &&'String' == columnDescripter.columnType&&'' != columnDescripter.columnDefault)>
			${columnDescripter.columnNameNoDash}="${columnDescripter.columnDefault}";
		 <#elseif ('' != columnDescripter.columnType&&'' != columnDescripter.columnDefault ) >
			${columnDescripter.columnNameNoDash}=${columnDescripter.columnDefault};
		 <#else>
		</#if>
		</#list>
		final StringBuffer query=new StringBuffer();
		query.append("");
		<#list methodDescripter.calleeArgumentList as columnDescripter>
		
		<#if ('' != columnDescripter.columnType &&'String' == columnDescripter.columnType)>
		if(StringUtils.isNotEmpty(${columnDescripter.columnNameNoDash})){
			query.append("&");
			query.append("${columnDescripter.columnNameNoDash}");
			query.append("=");
			query.append(${columnDescripter.columnNameNoDash});
		}
		 <#elseif ('' != columnDescripter.columnType ) >
		if(${columnDescripter.columnNameNoDash}!=null){
			query.append("&");
			query.append("${columnDescripter.columnNameNoDash}");
			query.append("=");
			query.append(${columnDescripter.columnNameNoDash});
		}
		 <#else>
		</#if>
		</#list>
		
		FutureTask<${methodDescripter.methodReturnAnnotation.annoteValue}> future = new FutureTask<${methodDescripter.methodReturnAnnotation.annoteValue}>(new Callable< ${methodDescripter.methodReturnAnnotation.annoteValue}>() {
			public ${methodDescripter.methodReturnAnnotation.annoteValue} call() {
				try{
					String currentPath=path+query.toString();
					ResponseEntity<${methodDescripter.methodReturnAnnotation.annoteValue}> response = 
					restTemplate.getForEntity(currentPath, ${methodDescripter.methodReturnAnnotation.annoteValue}.class);
					
					return response.getBody();
				}catch(Exception e){
					e.printStackTrace();
					return new ${methodDescripter.methodReturnAnnotation.annoteValue}();
				}
			}
		});
		executorService.execute(future);
	        ${methodDescripter.methodReturnAnnotation.annoteValue} result = null;
		try {
			result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;

	}
	</#if>
	
</#list>
}


