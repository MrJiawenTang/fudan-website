package com.cloud.cqc.framework.persistence.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 实体类(基于mybatis-plus)
 * 
 * @author joy.zhou
 * @date 2017年5月9日
 */
public class BasicEntity extends BasicSerializableEntity {

	private static final long serialVersionUID = 1L;

	/** 删除标记 */
	private Integer deleted;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	@TableField(fill = FieldFill.INSERT)
	private String createTime;
	/** 更新人 */
	private String lastUpdateBy;
	/** 更新时间 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String lastUpdateTime;

	@JsonIgnore
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@JsonIgnore
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@JsonIgnore
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@JsonIgnore
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
