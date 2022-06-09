package com.cloud.cqc.client.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.cloud.cqc.framework.supports.mybatis.interceptor.DefaultMetaObjectHandler;

/**
 * 
 * Mybatis Plus
 * 
 * @author joy.zhou
 * @date 2017年6月14日
 * 
 */
@Configuration
@ConditionalOnClass(MybatisConfiguration.class)
public class MybatisPlusConfigurtion {

	@Bean
	@ConditionalOnMissingBean(PaginationInterceptor.class)
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	@ConditionalOnMissingBean(DefaultMetaObjectHandler.class)
	public DefaultMetaObjectHandler metaObjectHandler() {
		return new DefaultMetaObjectHandler();
	}

}
