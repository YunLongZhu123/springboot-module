package com.springboot.demo.utils;

import com.springboot.demo.exception.MyException;
import com.springboot.demo.code.RspCode;
import com.springboot.demo.dto.PermissionDto;

public class CheckParamUtils {

    private CheckParamUtils(){}

    public static void checkPermissionParam(PermissionDto dto) {

        if(null == dto) {
            throw new MyException(RspCode.PARAM_CAN_NOT_BE_NULL);
        }
    }
}
