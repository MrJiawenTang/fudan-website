package com.cloud.cqc.service.cms.vo;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 文章内容扩展
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public class CmsContentAttributeVO extends BasicEntity {

    private static final long serialVersionUID = 1L;

    /** 内容ID */
	private Long contentId;
    /** 内容来源 */
	private String source;
    /** 来源地址 */
	private String sourceUrl;
    /** 内容 */
	private String data;
    /** 字数 */
	private Integer wordCount;


	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}
}
