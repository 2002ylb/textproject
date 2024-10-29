package com.example.dongruan.control;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Result {
    private Object data;
    private Integer code;
    private String msg;



    public Result(Integer code, String msg) {
        this.code=code;
        this.msg=msg;
    }
}
