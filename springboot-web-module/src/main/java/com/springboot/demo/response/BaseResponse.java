package com.springboot.demo.response;

import com.springboot.demo.constant.ResponseEnum;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {


    private String msg;

    private String code;

    private T t;

    public BaseResponse(ResponseEnum responseEnum) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
    }

    public BaseResponse(ResponseEnum responseEnum, T t) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.t = t;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
