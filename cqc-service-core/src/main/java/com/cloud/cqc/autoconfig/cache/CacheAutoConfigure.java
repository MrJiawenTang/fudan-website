package com.cloud.cqc.autoconfig.cache;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloud.cqc.framework.persistence.entity.BasicSerializableEntity;

/**
 * 缓存自动配置
 * 
 * @author joy.zhou
 * @date 2017年10月26日
 * @version 1.0
 */
@Configuration
@EnableCaching
@ConditionalOnClass({ CacheManager.class })
public class CacheAutoConfigure {

	@Configuration
	@AutoConfigureAfter(CacheAutoConfiguration.class)
	protected static class EhCacheConfigure extends CachingConfigurerSupport {

		/**
		 * 自定义缓存key生成策略
		 * 
		 * class : method : _params1_params2_...
		 * 
		 */
		@Bean
		@Override
		public KeyGenerator keyGenerator() {
			return (target, method, params) -> {
				String methodName = method.getName();
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getSimpleName());
				sb.append(":");
				sb.append(methodName);
				sb.append(":");
				if (params.length > 0) {
					for (Object param : params) {
						sb.append("_");
						if (param instanceof BasicSerializableEntity) {
							sb.append(((BasicSerializableEntity) param).getId());
						} else {
							sb.append(ToStringBuilder.reflectionToString(param, ToStringStyle.SHORT_PREFIX_STYLE));

						}
					}
				}
				return sb.toString();
			};
		}

		@Bean
		public CacheTools cacheTools(CacheManager cacheManager) {
			return new CacheTools(cacheManager);
		}

		@Bean
		public EvictCacheAspect evictCacheAspect() {
			return new EvictCacheAspect();
		}
	}

}
