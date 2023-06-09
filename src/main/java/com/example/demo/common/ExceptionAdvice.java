package com.example.demo.common;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Object exceptionAdvice(Exception e){
        return AjaxResult.fail(-1,e.getMessage());
    }
}
