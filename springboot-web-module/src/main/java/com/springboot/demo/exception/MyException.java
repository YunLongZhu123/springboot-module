package com.springboot.demo.exception;

import com.springboot.demo.code.RspCode;

public class MyException extends RuntimeException{

    private String msg;
    private String code;
    private RspCode rspCode;

    public MyException(String msg,String code) {
        this.msg = msg;
        this.code = code;
    }

    public MyException(RspCode rspCode) {
        super(rspCode.getMsg());
        this.msg = rspCode.getMsg();
        this.code = rspCode.getCode();
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
}
