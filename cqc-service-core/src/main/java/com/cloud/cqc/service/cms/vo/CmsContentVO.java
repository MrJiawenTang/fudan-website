package com.cloud.cqc.service.cms.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 文章内容
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public class CmsContentVO extends BasicEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	@NotBlank
	private String title;
	/** 分类编码 */
	private String categoryCode;
	/** 是否转载 */
	private Boolean copied;
	/** 作者 */
	private String author;
	/** 编辑 */
	private String editor;
	/** 简介 */
	private String description;
	/** 标签 */
	private String tags;
	/** 封面 */
	private String cover;
	/** 状态：0、草稿 1、已发布 2、待审核 */
	private Integer status;
	/** 评论数 */
	private Integer comments;
	/** 点击数 */
	private Integer clicks;
	/** 排序 */
	private Float sort;
	/** 发布时间 */
	private String publishTime;
	/** 发布人 */
	private String publishUser;
	/** 内容 */
	@NotBlank
	private String data;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Boolean getCopied() {
		return copied;
	}

	public void setCopied(Boolean copied) {
		this.copied = copied;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
