package com.cloud.cqc.framework.mvc.model;

import java.util.List;

import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

/**
 * 批量操作
 * 
 * @author Joy.zhou
 *
 */
public class BatchEntity extends SerializableSerializer {

	private static final long serialVersionUID = 1L;

	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
