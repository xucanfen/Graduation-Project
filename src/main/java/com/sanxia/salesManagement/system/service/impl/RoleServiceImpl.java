package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.RoleMapper;
import com.sanxia.salesManagement.system.model.Role;
import com.sanxia.salesManagement.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> queryAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.queryAllRole();
	}

	@Override
	public int addRoleByRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.addRoleByRole(role);
	}

	@Override
	public Role queryRoleByRole_id(int roleId) {
		// TODO Auto-generated method stub
		return roleMapper.queryRoleByRole_id(roleId);
	}

	@Override
	public int updateRoleById(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.updateRoleById(role);
	}

	@Override
	public int deleteRoleByRole_id(int role_id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteRoleByRole_id(role_id);
	}

	@Override
	public Role queryXZRoleByUser_id(int id) {
		// TODO Auto-generated method stub
		return roleMapper.queryXZRoleByUser_id(id);
	}

	@Override
	public int queryRole_idByRole_name(String role_name) {
		// TODO Auto-generated method stub
		return roleMapper.queryRole_idByRole_name(role_name);
	}

	@Override
	public List<Role> selectRoleByRoleId(int role_id) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleByRoleId(role_id);
	}

	@Override
	public List<Role> selectRoleByName(String role_name) {
		// TODO Auto-generated method stub
		return  roleMapper.selectRoleByName(role_name);
	}



}
