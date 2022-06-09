package com.cloud.cqc.client.ueditor.configure;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * JSONP 支持
 * @author joy.zhou
 * @date 2017年12月15日
 * @version 1.0
 */
@ControllerAdvice(basePackages = "com.cloud.cqc.client.ueditor.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	public JsonpAdvice() {

		super("callback", "jsonp");
	}
}
