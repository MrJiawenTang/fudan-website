package com.cloud.cqc.service.cms.vo;

import java.util.List;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 文章分类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public class CmsCategoryVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 父分类ID */
	private Long parentId;
	/** 类型(1:普通分类,2:a标签,3:_blank标签) */
	private Integer type;
	/** 跳转地址 */
	private String jumpUrl;
	/** 模板路径 */
	private String templateName;
	/** 排序 */
	private Float sort;
	/** 是否禁用 */
	private Boolean disabled;
	/** 分类内容列表 */
	private List<CmsContentVO> dataList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public List<CmsContentVO> getDataList() {
		return dataList;
	}

	public void setDataList(List<CmsContentVO> dataList) {
		this.dataList = dataList;
	}

}
