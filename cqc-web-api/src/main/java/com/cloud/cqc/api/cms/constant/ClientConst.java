package com.cloud.cqc.api.cms.constant;

import com.cloud.cqc.framework.persistence.dto.Ordered;

public interface ClientConst {

	/** 列表默认排序 */
	static final Ordered[] DEFAULT_LIST_ORDER = new Ordered[] { new Ordered("sort", "desc"),
			new Ordered("createTime", "desc") };

}
