package com.springboot.demo.dao;

import com.springboot.demo.model.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int insert(PermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int insertSelective(PermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    PermissionEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PermissionEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PermissionEntity record);
}