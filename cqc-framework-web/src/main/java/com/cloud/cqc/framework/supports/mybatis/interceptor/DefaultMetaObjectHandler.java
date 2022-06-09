package com.cloud.cqc.framework.supports.mybatis.interceptor;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.cloud.cqc.framework.core.utils.DateUtils;

public class DefaultMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		
		String current = LocalDateTime.now().format(DateUtils.DATE_TIME_FORMAT);
		this.setFiledNotNull("createTime", current, metaObject);
		this.setFiledNotNull("lastUpdateTime", current, metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("lastUpdateTime", LocalDateTime.now().format(DateUtils.DATE_TIME_FORMAT), metaObject);
	}

	private void setFiledNotNull(String fieldName, Object fieldVal, MetaObject metaObject) {
		if (getFieldValByName(fieldName, metaObject) == null) {
			this.setFieldValByName(fieldName, fieldVal, metaObject);
		}
	}

}
