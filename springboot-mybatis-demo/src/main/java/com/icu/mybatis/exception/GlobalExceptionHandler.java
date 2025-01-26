package com.icu.mybatis.exception;

import com.icu.mybatis.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// @RestControllerAdvice 表示全局异常处理器，拦截所有 @Controller 抛出的异常
// @RestControllerAdvice = @ControllerAdvice + @ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("全局异常处理器，拦截到异常 [Exception]", e);
        return Result.build(null, 500, "对不起,服务器异常,请稍后重试");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("全局异常处理器，拦截到异常 [DuplicateKeyException]", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate Entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.build(null, 500, arr[2] + "已存在");
    }
}