package com.cloud.cqc.framework.mvc.http;

import org.springframework.util.ObjectUtils;

/**
 * 请求响应对象
 * 
 * @author joy.zhou
 * @date 2017年6月28日
 * @param <T>
 */
public class RestEntity<T> {

	/** 状态码 */
	private int code;
	/** 消息 */
	private String message;
	/** 数据对象 */
	private T data;

	public RestEntity() {
	}

	public RestEntity(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public RestEntity(int code) {
		this(code, null, null);
	}

	public RestEntity(int code, String message) {
		this(code, message, null);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!super.equals(other)) {
			return false;
		}
		RestEntity<?> otherEntity = (RestEntity<?>) other;
		return ObjectUtils.nullSafeEquals(this.code, otherEntity.code);
	}

	@Override
	public int hashCode() {
		return (super.hashCode() * 29 + ObjectUtils.nullSafeHashCode(this.code));
	}

	@Override
	public String toString() {
		return "RestEntity [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
