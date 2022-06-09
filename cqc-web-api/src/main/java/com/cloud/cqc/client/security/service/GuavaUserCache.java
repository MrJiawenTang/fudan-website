package com.cloud.cqc.client.security.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.google.common.cache.Cache;

public class GuavaUserCache implements UserCache, InitializingBean {

	// ~ Instance fields
	// ================================================================================================

	private Cache<String, Object> cache;

	// ~ Methods
	// ========================================================================================================

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "cache mandatory");
	}

	public Cache<String, Object> getCache() {
		return cache;
	}

	public UserDetails getUserFromCache(String username) {
		Object element = cache.getIfPresent(username);

		if (element == null) {
			return null;
		} else {
			return (UserDetails) element;
		}
	}

	public void putUserInCache(UserDetails user) {
		cache.put(user.getUsername(), user);

	}

	public void removeUserFromCache(UserDetails user) {
		this.removeUserFromCache(user.getUsername());
	}

	public void removeUserFromCache(String username) {
		cache.invalidate(username);
	}

	public void setCache(Cache<String, Object> cache) {
		this.cache = cache;
	}
}
