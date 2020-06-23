package com.springboot.demo.service;

import com.springboot.demo.model.PermissionEntity;
import com.springboot.demo.model.RoleEntity;

import java.util.List;

public interface RoleMangeService {

    //新建角色
    int insertRole(RoleEntity entity);

    //修改角色
    int updateRole(RoleEntity entity, List<PermissionEntity> permissionEntities);

    //获取角色信息
    List<RoleEntity> findRoleByUserName(String username);
}
