package com.springboot.demo.controller;

import com.springboot.demo.constant.ResponseEnum;
import com.springboot.demo.dto.UpdateUserDto;
import com.springboot.demo.dto.UserDto;
import com.springboot.demo.model.RoleEntity;
import com.springboot.demo.model.UserEntity;
import com.springboot.demo.response.BaseResponse;
import com.springboot.demo.service.UserService;
import com.springboot.demo.utils.PasswordUtils;
import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MapperFacade mapperFacade;


    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

//    @GetMapping("/user/{id}")
//    public QueryUserByIdResponse queryUserById(@PathVariable("id") Integer id) {
//
//        UserEntity userEntity = userService.queryUserById(id);
//        UserBean userBean = mapperFacade.map(userEntity, UserBean.class);
//        QueryUserByIdResponse response = new QueryUserByIdResponse();
//        response.setUserBean(userBean);
//        return response;
//    }

    @PostMapping("/user")
    @ResponseBody
    public BaseResponse addUser(@RequestBody UserDto dto) {

        UserEntity entity = mapperFacade.map(dto, UserEntity.class);
        entity.setPasswordSalt(PasswordUtils.genSalt());
        entity.setPassword(PasswordUtils.encryptPassword(entity.getPassword(),entity.getPasswordSalt()));

        userService.insertUser(entity);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ResponseBody
    @PutMapping("/user")
    public BaseResponse updateUser(@RequestBody UpdateUserDto updateUserDto) {
        UserEntity userEntity = mapperFacade.map(updateUserDto, UserEntity.class);
        List<RoleEntity> roleEntities = mapperFacade.mapAsList(updateUserDto.getRoleDtos(), RoleEntity.class);
        userService.updateUser(userEntity,roleEntities);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
