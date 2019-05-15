package com.github.supertrampai.fantasticfunicular.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(basePackages = { "com.github.supertrampai.fantasticfunicular.domain" })
@EnableTransactionManagement
public class BeansConfig {

	/*@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}*/

	@Bean
	public HttpMessageConverters httpMessageConverters() {
		FastJsonHttpMessageConverter fjhmc = new FastJsonHttpMessageConverter();
		//fjhmc.setCharset(FastJsonHttpMessageConverter.);
		List<MediaType> types = new ArrayList<MediaType>();
		types.add(MediaType.APPLICATION_JSON_UTF8);
		fjhmc.setSupportedMediaTypes(types);
		fjhmc.setFeatures(SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteMapNullValue,
				SerializerFeature.QuoteFieldNames, SerializerFeature.PrettyFormat,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty);
		return new HttpMessageConverters(fjhmc);
	}
}
