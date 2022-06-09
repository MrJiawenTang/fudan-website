package com.cloud.cqc.client.cms.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.common.controller.CRUDController;
import com.cloud.cqc.framework.mvc.http.RestEntity;
import com.cloud.cqc.service.cms.entity.CmsContent;
import com.cloud.cqc.service.cms.searchable.CmsContentSearchable;
import com.cloud.cqc.service.cms.service.ICmsContentAttributeService;
import com.cloud.cqc.service.cms.service.ICmsContentService;
import com.cloud.cqc.service.cms.vo.CmsContentAttributeVO;
import com.cloud.cqc.service.cms.vo.CmsContentVO;
import com.hankcs.hanlp.HanLP;

/**
 * <p>
 * CMS 内容管理
 * </p>
 * 
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@RestController
@RequestMapping("/cms/content")
public class CmsContentController
		extends CRUDController<CmsContent, CmsContentVO, CmsContentSearchable, ICmsContentService> {

	@Autowired
	private ICmsContentAttributeService cmsContentAttributeService;

	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public RestEntity<?> save(@Valid @RequestBody CmsContentVO vo) {
		fillVO(vo, false);
		baseService.insert(vo);
		CmsContentAttributeVO cmsContentAttribute = new CmsContentAttributeVO();
		cmsContentAttribute.setContentId(vo.getId());
		cmsContentAttribute.setData(vo.getData());
		cmsContentAttributeService.insert(cmsContentAttribute);
		return resultOk(vo);
	}

	/**
	 * 修改
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RestEntity<?> update(@PathVariable Long id, @RequestBody CmsContentVO vo) {
		fillVO(vo, true);
		vo.setId(id);
		baseService.update(vo);
		CmsContentAttributeVO cmsContentAttribute = cmsContentAttributeService.selectByContentId(id);
		if (cmsContentAttribute != null) {
			CmsContentAttributeVO updateEntity = new CmsContentAttributeVO();
			updateEntity.setId(cmsContentAttribute.getId());
			updateEntity.setData(vo.getData());
			cmsContentAttributeService.update(updateEntity);
		}
		return resultOk();
	}

	private void fillVO(CmsContentVO vo, boolean isUpdate) {

		// 生成文章摘要
		vo.setDescription(getDesc(vo.getData()));

		// 生成默认封面(html中的第一张图片作为封面)
		if (StringUtils.isBlank(vo.getCover())) {
			Elements images = Jsoup.parse(vo.getData()).getElementsByTag("img");
			if (images.size() > 0) {
				vo.setCover(images.get(0).attr("src"));
			}
		}

		// 生成默认发布人
		if (vo.getStatus() != null && vo.getStatus() == 1) {
			vo.setPublishUser(StringUtils.isNotBlank(getCurrentUser().getRealname()) ? getCurrentUser().getRealname()
					: getCurrentUser().getUsername());
		}
	}

	private String getDesc(String data) {

		List<String> sentenceList = HanLP.extractSummary(Jsoup.clean(data, Whitelist.none()), 3);

		return StringUtils.join(sentenceList, ",");
	}

}
