package com.cloud.cqc.service.cms.vo;

import com.cloud.cqc.framework.persistence.entity.BasicEntity;

/**
 * <p>
 * 幻灯片
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public class CmsSlideVO extends BasicEntity {

    private static final long serialVersionUID = 1L;

    /** 名称 */
	private String name;
    /** 图片地址 */
	private String img;
    /** 标题 */
	private String title;
    /** 链接 */
	private String link;
    /** 排序 */
	private Float sort;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}
}
