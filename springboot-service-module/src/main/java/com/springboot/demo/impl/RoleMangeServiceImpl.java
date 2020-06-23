package com.springboot.demo.impl;

import com.springboot.demo.dao.RoleEntityMapper;
import com.springboot.demo.dao.RolePermissionEntityMapper;
import com.springboot.demo.model.PermissionEntity;
import com.springboot.demo.model.RoleEntity;
import com.springboot.demo.model.RolePermissionEntity;
import com.springboot.demo.service.RoleMangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMangeServiceImpl implements RoleMangeService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Autowired
    private RolePermissionEntityMapper rolePermissionEntityMapper;

    @Override
    public int insertRole(RoleEntity entity) {

        return roleEntityMapper.insertSelective(entity);
    }

    @Override
    public int updateRole(RoleEntity entity, List<PermissionEntity> permissionEntities) {
        int num = 0;
        if(null != entity) {
            num =roleEntityMapper.updateByPrimaryKeySelective(entity);
        }

        if(num > 0 && !CollectionUtils.isEmpty(permissionEntities)) {
            List<Long> collect = permissionEntities.stream().map(permissionEntity -> permissionEntity.getId()).collect(Collectors.toList());
            for (Long aLong : collect) {
                RolePermissionEntity entity1 = new RolePermissionEntity();
                entity1.setRoleId(entity.getId());
                entity1.setPermissionId(aLong);
                entity1.setStatus((byte)1);
                rolePermissionEntityMapper.insertSelective(entity1);
            }

        }
        return num;
    }

    @Override
    public List<RoleEntity> findRoleByUserName(String username) {
        return null;
    }
}
