package com.sanxia.salesManagement.system.dao;

import com.sanxia.salesManagement.system.model.Role;
import com.sanxia.salesManagement.system.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    //////////////
 

	int insertUserRoleByUserRole(UserRole userRole);

	Role queryXZRoleByUser_id(int user_id);

	int deleteUserRoleByUser_id(int id);

	int addUserRoleByUserRole(UserRole ur);

 
    

 
}