package com.sanxia.salesManagement.system.service;

import com.sanxia.salesManagement.system.model.RolePermisson;

public interface RolePermissionService {

	int deleteRolePermissionByRole_id(int id);

	int insertRolePermissionByRolePermission(RolePermisson rolePermission);

}
