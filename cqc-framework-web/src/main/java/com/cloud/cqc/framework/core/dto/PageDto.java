package com.cloud.cqc.framework.core.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页对象
 * 
 * @author joy.zhou
 * @date 2017年6月9日
 * @param <T>
 */
public class PageDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/* 总数 */
	private long total;
	/** 每页记录数 */
	private int size;
	/** 查询数据列表 */
	private List<T> records = Collections.emptyList();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

}
