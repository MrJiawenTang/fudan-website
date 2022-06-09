package com.cloud.cqc.framework.persistence.dto;

import java.io.Serializable;

import com.cloud.cqc.framework.core.dto.DateRangeDto;

/**
 * 查询参数
 * 
 * @author joy.zhou
 * @date 2017年6月14日
 * @param <T>
 */
public class Searchable implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 关键字 */
	private String key;
	/** 创建时间范围 */
	private DateRangeDto createDate;
	/** 排序 */
	private Ordered[] ordereds;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public DateRangeDto getCreateDate() {
		return createDate;
	}

	public void setCreateDate(DateRangeDto createDate) {
		this.createDate = createDate;
	}

	public Ordered[] getOrdereds() {
		return ordereds;
	}

	public void setOrdereds(Ordered[] ordereds) {
		this.ordereds = ordereds;
	}

}
