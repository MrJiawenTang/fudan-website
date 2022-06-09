package com.cloud.cqc.framework.mvc.convert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.cloud.cqc.framework.core.dto.DateRangeDto;

/**
 * 
 * 日期范围 转换
 *
 * @author joy.zhou
 * @date 2016年2月2日
 * @version 1.0
 *
 */
public class DataRangeConvertor implements Converter<String, DateRangeDto> {

	@Override
	public DateRangeDto convert(String source) {

		if (StringUtils.isBlank(source)) {
			return null;
		}

		String[] str = source.split(DateRangeDto.SEP);

		if (str.length < 2) {
			return new DateRangeDto(str[0], "");
		}

		return new DateRangeDto(str[0], str[1]);
	}

}
