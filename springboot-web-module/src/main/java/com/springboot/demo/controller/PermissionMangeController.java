package com.springboot.demo.controller;

import com.springboot.demo.constant.ResponseEnum;
import com.springboot.demo.dto.PermissionDto;
import com.springboot.demo.model.PermissionEntity;
import com.springboot.demo.response.BaseResponse;
import com.springboot.demo.service.PermissionService;
import com.springboot.demo.utils.CheckParamUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限管理
 */
@Controller
public class PermissionMangeController {


    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private PermissionService permissionService;

    @PostMapping("permission")
    @ResponseBody
    public BaseResponse addPermission(@RequestBody PermissionDto permissionDto) {

        CheckParamUtils.checkPermissionParam(permissionDto);
        PermissionEntity entity = mapperFacade.map(permissionDto, PermissionEntity.class);
        int id = permissionService.addPermission(entity);
        return new BaseResponse(ResponseEnum.SUCCESS,id);
    }
}
