package com.cloud.cqc.client.configure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cloud.cqc.client.configure.web.CorsProperties;
import com.cloud.cqc.framework.core.constant.Device;
import com.cloud.cqc.framework.core.utils.SpringTools;
import com.cloud.cqc.framework.mvc.convert.DataRangeConvertor;
import com.cloud.cqc.framework.mvc.convert.DefaultEnumConvertor;
import com.cloud.cqc.framework.mvc.error.BasicErrorController;
import com.cloud.cqc.framework.supports.jackson.DefaultEnumDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Configuration
@ConditionalOnWebApplication
public class WebAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public SpringTools springTools() {
		return new SpringTools();
	}

	/**
	 * 
	 * MVC 配置
	 * 
	 * @author joy.zhou
	 * @date 2017年6月14日
	 */
	@Configuration
	@EnableConfigurationProperties(CorsProperties.class)
	static class DefaultWebMvcConfigure extends WebMvcConfigurerAdapter {

		@Autowired
		private CorsProperties corsProperties;

		@Bean
		public Converter<?, ?> dataRangeConvertor() {
			return new DataRangeConvertor();
		}

		@Bean
		public ConverterFactory<?, ?> defaultEnumConvertor() {
			return new DefaultEnumConvertor();
		}

		@Override
		public void addFormatters(FormatterRegistry registry) {
			addConverterFactory(registry, defaultEnumConvertor());
		}

		protected void addConverterFactory(FormatterRegistry registry, ConverterFactory<?, ?>... converterFactories) {

			if (converterFactories.length <= 0) {
				return;
			}
			for (ConverterFactory<?, ?> converterFactory : converterFactories) {
				if (converterFactory != null) {
					registry.addConverterFactory(converterFactory);
				}
			}
		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {

			if (corsProperties.getAllowed()) {

				registry.addMapping(corsProperties.getMapping()).allowedOrigins(corsProperties.getAllowedOrigins())
						.allowCredentials(corsProperties.getAllowCredentials())
						.allowedMethods(corsProperties.getAllowedMethods()).maxAge(corsProperties.getMaxAge());
			}

		}
	}

	/**
	 * 自定义错误 配置
	 * 
	 * @author joy.zhou
	 * @date 2017年6月26日
	 */
	@Configuration
	@AutoConfigureBefore(WebMvcAutoConfiguration.class)
	static class BasicErrorConfigure {

		@Autowired(required = false)
		private List<ErrorViewResolver> errorViewResolvers;

		private final ServerProperties serverProperties;

		public BasicErrorConfigure(ServerProperties serverProperties) {
			this.serverProperties = serverProperties;
		}

		@Bean
		public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
			return new BasicErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
		}

	}

	/**
	 * Jackson 配置
	 * 
	 * @author joy.zhou
	 * @date 2017年6月7日
	 */
	@Configuration
	static class JacksonConfigure implements Jackson2ObjectMapperBuilderCustomizer {

		@Override
		public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
			jacksonObjectMapperBuilder.serializationInclusion(Include.NON_NULL).deserializerByType(Device.class,
					new DefaultEnumDeserializer<>(Device.class));
		}

	}
}
