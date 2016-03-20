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
		String path=url+"${methodDescripter.methodHeaderAnnotation.annoteValue}";
	
		return null;

	}
	</#if>
	
</#list>
}


