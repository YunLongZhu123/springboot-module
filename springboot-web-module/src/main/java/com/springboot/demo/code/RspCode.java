package com.springboot.demo.code;

public enum RspCode {

    PARAM_CAN_NOT_BE_NULL("必填参数不能为空","000000001") ;

    private String msg;
    private String code;

    RspCode(String msg, String code) {
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
