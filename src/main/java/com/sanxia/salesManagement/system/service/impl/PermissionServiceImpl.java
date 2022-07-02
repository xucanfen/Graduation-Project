package com.sanxia.salesManagement.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.PermissionMapper;
import com.sanxia.salesManagement.system.dao.RoleMapper;
import com.sanxia.salesManagement.system.model.Permission;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private  PermissionMapper  permissionMapper;
	

	@Override
	public List<Permission> queryAllPermission() {
		// TODO Auto-generated method stub
		return permissionMapper.queryAllPermission();
	}

	@Override
	public int addPermissionByPermission(Permission p) {
		// TODO Auto-generated method stub
		return permissionMapper.addPermissionByPermission(p);
	}

	
	
//查询权限菜单
	 
	@Override
	public List<Permission> queryMenuByUserId(int user_id) {
		// TODO Auto-generated method stub
		return permissionMapper.queryMenuByUserId(user_id);
	}

	@Override
	public List<Permission> querypermissionByPId_Son(int permissionId) {
		// TODO Auto-generated method stub
		return permissionMapper.querypermissionByPId_Son(permissionId);
	}
/////////////
	@Override
	public Permission queryPermissionById(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.queryPermissionById(id) ;
	}

	@Override
	public int updatePermissionByPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.updatePermissionByPermission(permission);
	}

	@Override
	public int deletePermissionById(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.deletePermissionById(id);
	}

	@Override
	public List<Permission> query_XZ_CByrole_id(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.query_XZ_CByrole_id(id);
	}

	@Override
	public List<Permission> selectPermissionByName(String name) {
		// TODO Auto-generated method stub
		return permissionMapper.selectPermissionByName(name);
	}

	@Override
	public List<Permission> queryAllPermissionByPID(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return permissionMapper.queryAllPermissionByPID(map);
	}

	 
}
