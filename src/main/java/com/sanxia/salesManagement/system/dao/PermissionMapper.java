package com.sanxia.salesManagement.system.dao;

import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.Permission;
import com.sanxia.salesManagement.system.model.User;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
//显示权限列表
	List<Permission> queryAllPermission();

	int addPermissionByPermission(Permission p);
///分配权限
	List<Permission> queryMenuByUserId(int user_id);

	List<Permission> querypermissionByPId_Son(int permissionId);
//////////////////
	Permission queryPermissionById(int id);

	int updatePermissionByPermission(Permission permission);

	int deletePermissionById(int id);
///////////////////////////
	List<Permission> query_XZ_CByrole_id(int id);

	List<Permission> selectPermissionByName(String name);

	List<Permission> queryAllPermissionByPID(HashMap<String, Object> map);

	
}