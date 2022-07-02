package com.sanxia.salesManagement.system.service;

import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.Permission;
import com.sanxia.salesManagement.system.model.User;

public interface PermissionService {

	List<Permission> queryAllPermission();

	int addPermissionByPermission(Permission p);

	List<Permission> queryMenuByUserId(int user_id);
////////////////
	List<Permission> querypermissionByPId_Son(int permissionId);

	Permission queryPermissionById(int id);

	int updatePermissionByPermission(Permission permission);

	int deletePermissionById(int id);

	List<Permission> query_XZ_CByrole_id(int id);

	List<Permission> selectPermissionByName(String name);

	List<Permission> queryAllPermissionByPID(HashMap<String, Object> map);

}
