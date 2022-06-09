package com.cloud.cqc.service.cms.service;

import com.cloud.cqc.service.cms.entity.CmsCategory;
import com.cloud.cqc.service.cms.vo.CmsCategoryVO;
import com.cloud.cqc.framework.persistence.service.IBaseService;

/**
 * <p>
 * 文章分类 服务类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public interface ICmsCategoryService extends IBaseService<CmsCategory, CmsCategoryVO> {

	/**
	 * 根据编号查询
	 * 
	 * @param code
	 * @return
	 */
	CmsCategoryVO selectByCode(String code);
}
