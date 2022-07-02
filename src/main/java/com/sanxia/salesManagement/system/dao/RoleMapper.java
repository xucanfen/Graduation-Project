package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

 //查询所有的用户列表
	List<Role> queryAllRole();
//添加用户信息
	int addRoleByRole(Role role);

	Role queryRoleByRole_id(int roleId);

	int updateRoleById(Role role);

	int deleteRoleByRole_id(int role_id);

	Role queryXZRoleByUser_id(int id);

	int queryRole_idByRole_name(String role_name);

	List<Role> selectRoleByRoleId(int role_id);

	List<Role> selectRoleByName(String role_name);
}