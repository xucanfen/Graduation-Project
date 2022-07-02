package com.sanxia.salesManagement.system.dao;

import com.sanxia.salesManagement.system.model.RolePermisson;

public interface RolePermissonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermisson record);

    int insertSelective(RolePermisson record);

    RolePermisson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermisson record);

    int updateByPrimaryKey(RolePermisson record);
///////////////////////////////////
	int deleteRolePermissionByRole_id(int id);

	int insertRolePermissionByRolePermission(RolePermisson rolePermission);
}