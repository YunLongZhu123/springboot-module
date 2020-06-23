package com.springboot.demo.service;

import com.springboot.demo.model.PermissionEntity;
import org.springframework.dao.PermissionDeniedDataAccessException;

public interface PermissionService {

    //增加权限
    int addPermission(PermissionEntity permissionEntity);
}
