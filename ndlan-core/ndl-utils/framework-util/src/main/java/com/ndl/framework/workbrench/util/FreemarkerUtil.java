package com.ndl.framework.workbrench.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.Map;

public class FreemarkerUtil {

	public static final String CHARSET = "UTF-8";

	public static Template getTemplate(String templateName) {
		try {
			Configuration configuration = new Configuration();
			File file = new File(
					Thread.currentThread().getContextClassLoader().getResources("").nextElement().getFile());
			FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(file);
			configuration.setTemplateLoader(fileTemplateLoader);
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setDefaultEncoding(CHARSET);
			return configuration.getTemplate(templateName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void outputProcessResult(String outputFile, Template template, Map<String, Object> varMap) {
		String resultString;
		ByteArrayOutputStream baos = null;
		Writer writer = null;
		try {
			baos = new ByteArrayOutputStream();
			writer = new OutputStreamWriter(baos, CHARSET);
			// 写文件
			template.process(varMap, writer);
			resultString = new String(baos.toByteArray(), CHARSET);
			FileUtils.write(new File(outputFile), resultString);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != baos) {
				try {
					baos.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			if (null != writer) {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
