package com.springboot.demo.service;

import com.springboot.demo.bean.UserBean;
import com.springboot.demo.model.RoleEntity;
import com.springboot.demo.model.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity queryUserById(Integer id);

    int insertUser(UserEntity entity);

    int updateUser(UserEntity entity, List<RoleEntity> roleEntities);

    UserBean findByUserName(String username);

}
