package com.csfive.hanium.iseeyou.utils;

import lombok.Getter;

@Getter
public class ErrorException extends Exception{
    private int ERR_CODE;

    ErrorException(String msg, int errcode){
        super(msg);
        ERR_CODE = errcode;
    }
    public ErrorException(String msg){
        this(msg, StatusCode.BAD_REQUEST);
    }
}
