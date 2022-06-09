package com.cloud.cqc.framework.core.constant;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 通用枚举
 * 
 * @author joy.zhou
 * @date 2017年5月15日
 */
public interface DefaultEnum {

	String DEFAULT_VALUE_NAME = "value";

	String DEFAULT_LABEL_NAME = "label";

	@JsonValue
	default Integer getValue() {
		Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME);
		if (field == null)
			return null;
		try {
			field.setAccessible(true);
			return Integer.parseInt(field.get(this).toString());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	default String getLabel() {
		Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_LABEL_NAME);
		if (field == null)
			return null;
		try {
			field.setAccessible(true);
			return field.get(this).toString();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	static <T extends Enum<T>> T valueOfEnum(Class<T> enumClass, Integer value) {
		if (value == null)
			throw new IllegalArgumentException(enumClass.getName() + " value should not be null");
		if (enumClass.isAssignableFrom(DefaultEnum.class))
			throw new IllegalArgumentException("illegal DefaultEnum type");
		T[] enums = enumClass.getEnumConstants();
		for (T t : enums) {
			DefaultEnum displayedEnum = (DefaultEnum) t;
			if (displayedEnum.getValue().equals(value))
				return t;
		}
		throw new IllegalArgumentException("cannot parse integer: " + value + " to " + enumClass.getName());
	}

	static <T extends Enum<T>> T labelOfEnum(Class<T> enumClass, String label) {
		if (label == null)
			throw new IllegalArgumentException(enumClass.getName() + " value should not be null");
		if (enumClass.isAssignableFrom(DefaultEnum.class))
			throw new IllegalArgumentException("illegal DefaultEnum type");
		T[] enums = enumClass.getEnumConstants();
		for (T t : enums) {
			DefaultEnum displayedEnum = (DefaultEnum) t;
			if (displayedEnum.getLabel().equals(label))
				return t;
		}
		throw new IllegalArgumentException("cannot parse string: " + label + " to " + enumClass.getName());
	}
}
