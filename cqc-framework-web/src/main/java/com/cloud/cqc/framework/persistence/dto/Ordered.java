package com.cloud.cqc.framework.persistence.dto;

/**
 * 排序参数
 * 
 * @author Joy.zhou
 *
 */
public class Ordered {
	/** 顺序 */
	public static final String ASC = "asc";
	/** 倒序 */
	public static final String DESC = "desc";
	/** 属性名 */
	private String fieldName;
	/** asc desc */
	private String sortType = ASC;

	public Ordered() {
	}

	public Ordered(String fieldName) {
		this.fieldName = fieldName;
	}

	public Ordered(String fieldName, String sortType) {
		this.fieldName = fieldName;
		this.sortType = sortType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
