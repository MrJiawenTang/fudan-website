package com.cloud.cqc.execption;

import com.cloud.cqc.framework.mvc.http.RestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public RestEntity handleHospitalException(Exception e) {

        log.error("Exception异常信息:{},异常类型：{}", e.getMessage(), e.getClass());

        return new RestEntity(500).appendMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public RestEntity handleIllegalArgumentException(IllegalArgumentException e) {

        log.error("IllegalArgumentException异常信息:{},异常类型：{}", e.getMessage(), e.getClass());

        return new RestEntity(500).appendMessage(e.getLocalizedMessage());

    }

}