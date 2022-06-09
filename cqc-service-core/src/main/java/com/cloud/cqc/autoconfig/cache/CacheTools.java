package com.cloud.cqc.autoconfig.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;

/**
 * 缓存工具类
 *
 * @author joy.zhou
 * @date 2016年7月18日
 * @version 1.0
 *
 */
public class CacheTools {

	protected static final Logger logger = LoggerFactory.getLogger(CacheTools.class);

	private final CacheManager cacheManager;

	public CacheTools(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * 清空缓存
	 * 
	 * @param cacheName
	 *            缓存名称
	 */
	public void clear(String cacheName) {

		Cache cache = cacheManager.getCache(cacheName);

		if (cache == null) {
			return;
		}

		cache.clear();
	}

	/**
	 * 清空缓存
	 * 
	 * @param cacheNames
	 *            缓存名称
	 */
	public void clear(String[] cacheNames) {

		for (String cacheName : cacheNames) {

			this.clear(cacheName);
		}
	}

	/**
	 * 删除缓存
	 * 
	 * @param cacheNames
	 *            缓存名称
	 * @param keyPattern
	 *            缓存key(支持模糊匹配)
	 */
	public int deleteByPattern(String[] cacheNames, String keyPattern) {

		int count = 0;

		for (String cacheName : cacheNames) {

			count += this.deleteByPattern(cacheName, keyPattern);
		}

		return count;

	}

	/**
	 * 删除缓存
	 * 
	 * @param cacheName
	 *            缓存名称
	 * @param keyPattern
	 *            缓存key(支持模糊匹配)
	 */
	public int deleteByPattern(String cacheName, String keyPattern) {

		int count = 0;

		Cache cache = cacheManager.getCache(cacheName);

		if (cache == null) {
			return count;
		}

		if (cache instanceof EhCacheCache) {

			Ehcache ehcache = ((EhCacheCache) cache).getNativeCache();

			Attribute<String> name = ehcache.getSearchAttribute("key");

			Query query = ehcache.createQuery();

			query.includeKeys().addCriteria(name.ilike(keyPattern));

			List<Result> results = query.execute().all();

			if (results != null && !results.isEmpty()) {
				for (Result result : results) {
					logger.debug(String.format("删除缓存：%s", result.getKey()));
					cache.evict(result.getKey());
				}
			} else {
				logger.debug("未找到缓存");
			}
		}
		return count;
	}

}
