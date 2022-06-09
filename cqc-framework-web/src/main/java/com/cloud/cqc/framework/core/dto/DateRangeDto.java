package com.cloud.cqc.framework.core.dto;

import java.io.Serializable;

/**
 * 时间范围
 *
 * @author joy.zhou
 * @date 2016年4月6日
 * @version 1.0
 *
 */
public class DateRangeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SEP = "~";

	/** 开始时间 */
	private String start;
	/** 结束时间 */
	private String end;

	public DateRangeDto() {
	}

	public DateRangeDto(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return start + SEP + end;
	}

}
