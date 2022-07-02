package com.sanxia.salesManagement.system.service;

import com.sanxia.salesManagement.system.model.Role;
import com.sanxia.salesManagement.system.model.UserRole;

public interface UserRoleService {
 

	int insertUserRoleByUserRole(UserRole userRole);

	Role queryXZRoleByUser_id(int user_id);

	int deleteUserRoleByUser_id(int id);

	int addUserRoleByUserRole(UserRole ur);

 

	 

}
