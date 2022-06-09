package com.cloud.cqc.framework.core.constant;

/**
 * 设备
 * 
 * @author joy.zhou
 * @date 2017年5月12日
 */
public enum Device implements DefaultEnum {

	/** PC端 */
	NORMAL(1, "PC"),
	/** 移动端 */
	MOBILE(2, "移动端"),
	/** 平板端 */
	TABLET(3, "平板端");
	int value;
	String label;

	Device(int value, String label) {
		this.value = value;
		this.label = label;
	}
}
