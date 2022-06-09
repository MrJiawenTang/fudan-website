package com.cloud.cqc.autoconfig.cache;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 
 * 清除缓存通用设置,清除当前类方法的所有缓存
 * 
 * @author joy.zhou
 * @date 2016年6月23日
 * @version 1.0
 *
 */
@Aspect
public class EvictCacheAspect {

	@Autowired
	private CacheTools cacheTools;

	@After("execution(* com.cloud.cqc..service..*+.insert*(..)) or execution(* com.cloud.cqc..service..*+.update*(..)) or execution(* com.cloud.cqc..service..*+.delete*(..))")
	public void after(JoinPoint point) throws Throwable {

		Class<?> cls = point.getTarget().getClass();

		CacheConfig annotation = AnnotationUtils.getAnnotation(cls.getSuperclass(), CacheConfig.class);

		if (annotation == null) {
			return;
		}

		String keyPattern = cls.getSimpleName() + "*";

		cacheTools.deleteByPattern(annotation.cacheNames(), keyPattern);

	}
}
