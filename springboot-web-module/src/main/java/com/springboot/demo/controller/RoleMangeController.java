package com.springboot.demo.controller;

import com.springboot.demo.constant.ResponseEnum;
import com.springboot.demo.dto.RoleDto;
import com.springboot.demo.dto.UpdateRoleDto;
import com.springboot.demo.model.PermissionEntity;
import com.springboot.demo.model.RoleEntity;
import com.springboot.demo.response.BaseResponse;
import com.springboot.demo.service.RoleMangeService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 角色管理
 */
@Controller
public class RoleMangeController {


    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private RoleMangeService roleMangeService;

    @PostMapping("/role")
    @ResponseBody
    public BaseResponse<Integer> addRole(@RequestBody RoleDto dto) {

        RoleEntity entity = mapperFacade.map(dto, RoleEntity.class);
        int id = roleMangeService.insertRole(entity);
        return new BaseResponse(ResponseEnum.SUCCESS,id);
    }

    @PutMapping("/role")
    @ResponseBody
    public BaseResponse updateRole(@RequestBody UpdateRoleDto dto) {

        RoleEntity roleEntity = mapperFacade.map(dto, RoleEntity.class);
        List<PermissionEntity> permissionEntities = mapperFacade.mapAsList(dto.getPermissionDtoList(), PermissionEntity.class);
        roleMangeService.updateRole(roleEntity,permissionEntities);

        return new BaseResponse(ResponseEnum.SUCCESS);
    }
}
