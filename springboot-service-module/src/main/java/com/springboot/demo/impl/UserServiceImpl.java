package com.springboot.demo.impl;

import com.springboot.demo.bean.UserBean;
import com.springboot.demo.dao.RoleEntityMapper;
import com.springboot.demo.dao.UserEntityMapper;
import com.springboot.demo.dao.UserRoleEntityMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.model.RoleEntity;
import com.springboot.demo.model.UserEntity;
import com.springboot.demo.model.UserRoleEntity;
import com.springboot.demo.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private UserRoleEntityMapper userRoleEntityMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public UserEntity queryUserById(Integer id) {

        return userMapper.queryUserById(id);
    }

    @Override
    public int insertUser(UserEntity entity) {

        return userEntityMapper.insertSelective(entity);
    }

    @Override
    public int updateUser(UserEntity entity, List<RoleEntity> roleEntities) {
        int num = userEntityMapper.updateByPrimaryKeySelective(entity);
        if(num > 0 && !CollectionUtils.isEmpty(roleEntities)) {
            List<Long> longs = roleEntities.stream().map(roleEntity -> roleEntity.getId()).collect(Collectors.toList());
            for (Long aLong : longs) {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUserId(entity.getId());
                userRoleEntity.setRoleId(aLong);
                userRoleEntity.setStatus((byte)1);
                userRoleEntityMapper.insertSelective(userRoleEntity);
            }
        }
        return num;
    }

    @Override
    public UserBean findByUserName(String username) {

        UserEntity entity = userEntityMapper.findByUserName(username);
        UserBean userBean = mapperFacade.map(entity, UserBean.class);
        return userBean;
    }
}
