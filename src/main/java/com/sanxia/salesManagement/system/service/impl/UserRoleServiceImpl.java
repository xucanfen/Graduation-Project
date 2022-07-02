package com.sanxia.salesManagement.system.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.UserRoleMapper;
import com.sanxia.salesManagement.system.model.Role;
import com.sanxia.salesManagement.system.model.UserRole;
import com.sanxia.salesManagement.system.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleMapper userRoleMapper;
 
	@Override
	public int insertUserRoleByUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return userRoleMapper.insertUserRoleByUserRole(userRole);
	}

	@Override
	public Role queryXZRoleByUser_id(int user_id) {
		// TODO Auto-generated method stub
		return userRoleMapper.queryXZRoleByUser_id(user_id);
	}

	@Override
	public int deleteUserRoleByUser_id(int id) {
		// TODO Auto-generated method stub
		return userRoleMapper.deleteUserRoleByUser_id(id);
	}

	@Override
	public int addUserRoleByUserRole(UserRole ur) {
		// TODO Auto-generated method stub
		return userRoleMapper.addUserRoleByUserRole(ur);
	}

 
 

}
