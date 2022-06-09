package com.cloud.cqc.api.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.cqc.framework.mvc.BaseRestController;
import com.cloud.cqc.service.cms.constant.CmsContentStatus;
import com.cloud.cqc.service.cms.searchable.CmsCategorySearchable;
import com.cloud.cqc.service.cms.searchable.CmsContentSearchable;
import com.cloud.cqc.service.cms.service.ICmsCategoryService;
import com.cloud.cqc.service.cms.service.ICmsContentService;
import com.cloud.cqc.service.cms.vo.CmsCategoryVO;

/**
 * 
 * @author joy.zhou
 * @date 2017年12月18日
 * @version 1.0
 */
@RestController
@RequestMapping("/api/category")
public class PublicCmsCategoryController extends BaseRestController {
	@Autowired
	private ICmsCategoryService cmsCategoryService;
	@Autowired
	private ICmsContentService cmsContentService;

	/**
	 * 获取分类信息
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public Object getCategory(@PathVariable String code) {

		CmsCategoryVO entity = cmsCategoryService.selectByCode(code);

		return resultOk(entity);
	}

	/**
	 * 获取分类数据
	 * 
	 * @param size
	 *            分类数量
	 * @param dataSize
	 *            内容数量
	 * @return
	 */
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public Object getCategories(@RequestBody CmsCategorySearchable searchable, Integer dataSize) {

		if (dataSize == null) {
			dataSize = 7;
		}
		if (searchable == null) {
			searchable = new CmsCategorySearchable();
		}
		// 默认参数
		searchable.setDisabled(false);

		// 分类信息
		List<CmsCategoryVO> categoryList = cmsCategoryService.selectList(searchable);

		if (categoryList != null && categoryList.size() > 0) {
			// 数据列表
			for (CmsCategoryVO category : categoryList) {
				CmsContentSearchable contentSearchable = new CmsContentSearchable();
				contentSearchable.setStatus(CmsContentStatus.PUBLISHED.getValue());
				contentSearchable.setCategoryCode(category.getCode());
				category.setDataList(
						cmsContentService.selectPage(new Page<>(0, dataSize), contentSearchable).getRecords());
			}

		}

		return resultOk(categoryList);
	}
}
