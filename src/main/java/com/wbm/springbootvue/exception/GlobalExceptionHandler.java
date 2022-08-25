package com.wbm.springbootvue.exception;

import com.wbm.springbootvue.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException serviceException){
        return Result.error(serviceException.getCode(),serviceException.getMessage());
    }
}
