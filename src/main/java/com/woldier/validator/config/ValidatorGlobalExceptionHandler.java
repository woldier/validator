package com.woldier.validator.config;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice(annotations = {RestController.class, Controller.class}) //切片  在加了RestController或者Controller注解的controller层组件上加入前置
@ResponseBody //表明返回json数据
public class ValidatorGlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationExceptionHandler(ConstraintViolationException e ){ //这里可在参数处获得注入的所有资源
        String msg = "";
        Set<ConstraintViolation<?>> violations =
                e.getConstraintViolations();
        ConstraintViolation<?> next = violations.iterator().next();
        msg = next.getMessage();
        return msg;
    }

    @ExceptionHandler(BindException.class)
    public String BindExceptionHandler(BindException e ){
        return e.getBindingResult().getFieldError().getDefaultMessage();
    }



}
