package com.bubble.controller;

import com.bubble.domain.ResultData;
import com.bubble.domain.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常信息拦截, 统一格式输出
 *
 * @author wugang
 * date: 2019-04-01 11:36
 **/
@RestControllerAdvice
public class ResponseErrorAdvice {

    @ExceptionHandler(value = ValidationFailedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultData handleValidationFailed(ValidationFailedException e) {
        ResultData result = new ResultData();
        result.setCode(ResultData.Code.FAILED.getCode());
        result.setMsg(e.getMessage());
        result.setData("");
        return result;
    }

}
