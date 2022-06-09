package com.cloud.cqc.framework.persistence.entity;

/**
 * 排序实体
 * 
 * @author joy.zhou
 * @date 2017年12月18日
 * @version 1.0
 */
public class BasicSortEntity extends BasicEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 排序
	 */
	private Float sort;

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}

}
