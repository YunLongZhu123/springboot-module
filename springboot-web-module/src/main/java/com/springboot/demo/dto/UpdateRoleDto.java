package com.springboot.demo.dto;

import java.util.List;

public class UpdateRoleDto extends RoleDto {

    private List<PermissionDto> permissionDtoList;

    public List<PermissionDto> getPermissionDtoList() {
        return permissionDtoList;
    }

    public void setPermissionDtoList(List<PermissionDto> permissionDtoList) {
        this.permissionDtoList = permissionDtoList;
    }
}
