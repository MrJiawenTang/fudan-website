package com.cloud.cqc.framework.mvc.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.cloud.cqc.framework.core.constant.DefaultEnum;

/**
 * 
 * DefaultEnumConvertor 转换
 *
 * @author joy.zhou
 * @date 2016年2月2日
 * @version 1.0
 *
 */
public class DefaultEnumConvertor implements ConverterFactory<Integer, DefaultEnum> {

	@Override
	public <T extends DefaultEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
		return new IntegerToEnum<T>(targetType);
	}

	private class IntegerToEnum<T extends DefaultEnum> implements Converter<Integer, T> {

		private final Class<T> type;

		public IntegerToEnum(Class<T> type) {
			this.type = type;
		}

		public T convert(Integer source) {
			if (source == null) {
				return null;
			}
			for (T e : type.getEnumConstants()) {
				if (source == e.getValue()) {
					return e;
				}
			}
			return null;
		}
	}

}
