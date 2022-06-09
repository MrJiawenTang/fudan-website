package com.cloud.cqc.framework.mvc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.cloud.cqc.framework.mvc.http.RestEntity;

/**
 * 
 * <p>
 * 基类controller
 * </p>
 * 失败请抛出异常
 * 
 * @author joy.zhou
 * @date 2017年5月9日
 */
public class BaseRestController {

	/**
	 * 返回成功消息
	 * 
	 * @return
	 */
	protected <T> RestEntity<T> resultOk() {
		return new RestEntity<T>(HttpStatus.OK.value());
	}

	/**
	 * 返回成功结果
	 * 
	 * @param body
	 * @return
	 */
	protected <T> RestEntity<T> resultOk(T body) {
		return new RestEntity<T>(HttpStatus.OK.value(), StringUtils.EMPTY, body);
	}

	/**
	 * 返回成功结果
	 * 
	 * @param body
	 * @return
	 */
	protected <T> RestEntity<Map<String, Collection<T>>> resultOk(Collection<T> body) {
		Map<String, Collection<T>> result = new HashMap<>();
		result.put("list", body);
		return new RestEntity<Map<String, Collection<T>>>(HttpStatus.OK.value(), StringUtils.EMPTY, result);
	}

}
