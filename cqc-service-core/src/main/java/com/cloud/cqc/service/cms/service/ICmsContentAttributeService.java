package com.cloud.cqc.service.cms.service;

import com.cloud.cqc.framework.persistence.service.IBaseService;
import com.cloud.cqc.service.cms.entity.CmsContentAttribute;
import com.cloud.cqc.service.cms.vo.CmsContentAttributeVO;

/**
 * <p>
 * 文章内容扩展 服务类
 * </p>
 *
 * @author Joy.zhou
 * @since 2017-12-06
 */
public interface ICmsContentAttributeService extends IBaseService<CmsContentAttribute, CmsContentAttributeVO> {

	/**
	 * 根据内容ID查询
	 * 
	 * @param id
	 * @return
	 */
	CmsContentAttributeVO selectByContentId(Long id);
}
