package com.sanxia.salesManagement.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.RolePermissonMapper;
import com.sanxia.salesManagement.system.model.RolePermisson;
import com.sanxia.salesManagement.system.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{

	@Autowired
	private RolePermissonMapper rolePermissionMapper;
	
	@Override
	public int deleteRolePermissionByRole_id(int id) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.deleteRolePermissionByRole_id(id);
	}

	@Override
	public int insertRolePermissionByRolePermission(RolePermisson rolePermission) {
		// TODO Auto-generated method stub
		return rolePermissionMapper.insertRolePermissionByRolePermission(rolePermission);
	}

}
