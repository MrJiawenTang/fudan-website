package com.cloud.cqc.service.cms.constant;

import com.cloud.cqc.framework.core.constant.DefaultEnum;

/**
 * 内容状态
 * 
 * @author joy.zhou
 * @date 2017年12月18日
 * @version 1.0
 */
public enum CmsContentStatus implements DefaultEnum {

	/** 私额 */
	DEFAULT(0, "草稿"),
	/** 已发布 */
	PUBLISHED(1, "已发布"),
	/** 待审核 */
	WAIT_REVIEW(2, "待审核");
	int value;
	String label;

	CmsContentStatus(int value, String label) {
		this.value = value;
		this.label = label;
	}
}
