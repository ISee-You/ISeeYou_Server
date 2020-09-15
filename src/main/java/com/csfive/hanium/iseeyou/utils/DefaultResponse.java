package com.csfive.hanium.iseeyou.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DefaultResponse<T> {

    //Response StatusCode
    private int status;

    //Response Message
    private String message;

    //Response TestData
    private T data;

    public DefaultResponse(final int status, final String message)
    {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int status, final String message) {return res(status,message,null);}

    public static<T> DefaultResponse<T> res(final int status, final String message, final T t) {
        return DefaultResponse.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }

    public static final DefaultResponse FAIL_DEFAULT_RES
            = new DefaultResponse(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
}