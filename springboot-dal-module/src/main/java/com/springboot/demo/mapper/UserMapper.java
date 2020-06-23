package com.springboot.demo.mapper;

import com.springboot.demo.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    UserEntity queryUserById(Integer id);
}
