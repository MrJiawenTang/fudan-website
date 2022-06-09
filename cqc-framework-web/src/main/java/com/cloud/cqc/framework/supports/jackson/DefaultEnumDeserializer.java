package com.cloud.cqc.framework.supports.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.cloud.cqc.framework.core.constant.DefaultEnum;

/**
 * 
 * DefaultEnum 解析
 * 
 * @see com.cloud.cqc.framework.core.constant.DefaultEnum
 * 
 * @author joy.zhou
 * @date 2017年6月5日
 */
public class DefaultEnumDeserializer<T extends DefaultEnum> extends JsonDeserializer<T> {

	private Class<T> cls;

	public DefaultEnumDeserializer(Class<T> cls) {
		this.cls = cls;
	}

	@Override
	public T deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		int status = parser.getIntValue();

		for (T e : cls.getEnumConstants()) {
			if (status == e.getValue()) {
				return e;
			}
		}

		return null;
	}

}
