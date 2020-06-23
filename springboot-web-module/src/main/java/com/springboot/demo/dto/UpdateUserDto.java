package com.springboot.demo.dto;

import java.util.List;

public class UpdateUserDto extends UserDto {

    private List<RoleDto> roleDtos;

    public List<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(List<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }
}
