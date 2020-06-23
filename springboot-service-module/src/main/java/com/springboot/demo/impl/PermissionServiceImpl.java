package com.springboot.demo.impl;

import com.springboot.demo.dao.PermissionEntityMapper;
import com.springboot.demo.model.PermissionEntity;
import com.springboot.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionEntityMapper permissionEntityMapper;

    @Override
    public int addPermission(PermissionEntity permissionEntity) {

        return permissionEntityMapper.insertSelective(permissionEntity);
    }
}
