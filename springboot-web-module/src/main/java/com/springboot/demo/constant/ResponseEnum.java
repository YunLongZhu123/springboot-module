package com.springboot.demo.constant;

public enum ResponseEnum {
    SUCCESS("成功","1000");

    private String msg;
    private String code;

    ResponseEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
