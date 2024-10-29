package com.example.dongruan.control;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdive {
//    @ExceptionHandler(Exception.class)
//    public Result doException(Exception exception){
//        System.out.println("处理异常");
//        System.out.println(exception);
//        System.out.println("------");
//        System.out.println(exception.getMessage());
//        return new Result(404,exception.getMessage());
//
//    }

    @ExceptionHandler(SysException.class)
    public Result doSysException(SysException exception){

        return new Result(exception.getCode(),exception.getMessage());

    }
}
