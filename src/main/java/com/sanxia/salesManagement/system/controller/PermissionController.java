package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanxia.salesManagement.system.model.PayinInfo;
import com.sanxia.salesManagement.system.model.Permission;
import com.sanxia.salesManagement.system.model.Role;
import com.sanxia.salesManagement.system.model.RolePermisson;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.PermissionService;
import com.sanxia.salesManagement.system.service.RolePermissionService;
import com.sanxia.salesManagement.system.service.RoleService;

@Controller
@RequestMapping(value = "/permissionController")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;

	// 显示权限列表
	@RequestMapping(value = "permissionlist.do")
	public String list(HttpServletRequest req, HttpServletResponse resp, Permission permission, Model model)
			throws ServletException, IOException {
		List<Permission> permissionList = permissionService.queryAllPermission();
		model.addAttribute("permissionList", permissionList);
		// for (User user2 : userList) {
		// System.out.println(user2);
		// }
		return "view/permission/permissionList";

	}

	// 增加权限信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		
		int p_id1=-1;
		int p_id2= 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pId1", p_id1);
		map.put("pId2", p_id2);
		
		List<Permission> permissionList = permissionService.queryAllPermissionByPID(map);
		model.addAttribute("permissionList", permissionList);
		// 跳转到增加的页面
		return "view/permission/permissionAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String permission_name = req.getParameter("permission_name");
		String permission_url = req.getParameter("permission_url");
		String permission_type = req.getParameter("type");

		String p_idstr = req.getParameter("p_id");
		int p_id = Integer.parseInt(p_idstr);

		Permission p = new Permission();
		p.setPermissionName(permission_name);
		p.setPermissionUrl(permission_url);
		p.setpId(p_id);
		p.setType(permission_type);
		int n = permissionService.addPermissionByPermission(p);

		// 重新返回主页
		List<Permission> permissionList = permissionService.queryAllPermission();
		model.addAttribute("permissionList", permissionList);

		return "view/permission/permissionList";
	}

	// 修改权限信息
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam("permissionId") String permissionId, Model model) throws ServletException, IOException {
		int id = Integer.parseInt(permissionId);
		// System.out.println(id);
		Permission permission = permissionService.queryPermissionById(id);
		model.addAttribute("p", permission);

		//显示权限信息
		int p_id1=-1;
		int p_id2= 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pId1", p_id1);
		map.put("pId2", p_id2);
		
		List<Permission> permissionList = permissionService.queryAllPermissionByPID(map);
		model.addAttribute("permissionList", permissionList);

		return "view/permission/permissionUpdate";

	}

	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, Model model, Permission permission)
			throws ServletException, IOException {

		String permission_name = req.getParameter("permission_name");
		String permission_url = req.getParameter("permission_url");
		String permission_type = req.getParameter("type");
		String permission_id = req.getParameter("permission_id");
		int id = Integer.parseInt(permission_id);
//		System.out.println(id);

		String p_idstr = req.getParameter("p_id");
		int p_id = Integer.parseInt(p_idstr);

		permission.setPermissionId(id);
		permission.setpId(p_id);
		permission.setPermissionName(permission_name);
		permission.setPermissionUrl(permission_url);
		permission.setType(permission_type);

		int i = permissionService.updatePermissionByPermission(permission);

		List<Permission> permissionList = permissionService.queryAllPermission();
		model.addAttribute("permissionList", permissionList);

		return "view/permission/permissionList";

	}

	// 删除
	@RequestMapping(value = "deletePermission.do")
	public String delete(HttpServletRequest req, HttpServletResponse resp, Model model,
			@RequestParam("permissionId") String permissionId) throws ServletException, IOException {

		int id = Integer.parseInt(permissionId);
		int i = permissionService.deletePermissionById(id);
		List<Permission> permissionList = permissionService.queryAllPermission();
		model.addAttribute("permissionList", permissionList);

		return "view/permission/permissionList";

	}

	// 分配权限
	@RequestMapping(value = "FPPermissionUI.do")
	public String FPPermissionUI(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam("roleId") String roleId, Model model) throws ServletException, IOException {
		// 用户ID
		int role_id = Integer.parseInt(roleId);
		Role role = roleService.queryRoleByRole_id(role_id);

		// 查询角色
		// b、查询所有菜单
		List<Permission> permissionList = permissionService.queryAllPermission();
		model.addAttribute("role", role);
		model.addAttribute("permissionList", permissionList);

		List<Permission> permissionListXZList = permissionService.query_XZ_CByrole_id(role_id);
		List<Integer> list = new ArrayList<Integer>();
		for (Permission mm : permissionListXZList) {
			list.add(mm.getPermissionId());
		}
		model.addAttribute("ids", list);
		// 跳转页面
		return "view/permission/FPPermission";
	}

	@RequestMapping("FPPermission.do")
	public String FPPermission(HttpServletRequest req, HttpServletResponse resp, String[] permissionId, String roleId,
			Model model) throws ServletException, IOException {
		// 用户ID

		int id = Integer.parseInt(roleId);

		int m = rolePermissionService.deleteRolePermissionByRole_id(id);
		for (String string : permissionId) {
			int id2 = Integer.parseInt(string);
			RolePermisson rolePermission = new RolePermisson();
			rolePermission.setRoleId(id);
			rolePermission.setPermissionId(id2);
			int n = rolePermissionService.insertRolePermissionByRolePermission(rolePermission);
		}
		Role role = roleService.queryRoleByRole_id(id);
		// 查询菜单
		List<Permission> permissionList = permissionService.queryAllPermission();
		model.addAttribute("role", role);
		model.addAttribute("permissionList", permissionList);

		List<Permission> permissionListXZList = permissionService.query_XZ_CByrole_id(id);
		List<Integer> list = new ArrayList<Integer>();
		for (Permission mm : permissionListXZList) {
			list.add(mm.getPermissionId());
		}
		model.addAttribute("ids", list);
		// 跳转页面
		return "view/permission/FPPermission";
	}

	// 搜索指定的权限信息
	@RequestMapping(value = "searchPermission.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "permission_search", required = false) String permission_search)
			throws ServletException, IOException {

		if (permission_search != "") {
			String name = "%" + permission_search + "%";
			;

			List<Permission> permissionList = permissionService.selectPermissionByName(name);
			model.addAttribute("permissionList", permissionList);

			return "view/permission/permissionList";

		}

		else {

			List<Permission> permissionList = permissionService.queryAllPermission();
			model.addAttribute("permissionList", permissionList);

			return "view/permission/permissionList";
		}

	}

}
