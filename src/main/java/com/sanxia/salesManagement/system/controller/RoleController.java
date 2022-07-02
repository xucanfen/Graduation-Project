package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.sanxia.salesManagement.system.model.Role;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.model.UserRole;
import com.sanxia.salesManagement.system.service.RoleService;
import com.sanxia.salesManagement.system.service.UserRoleService;
import com.sanxia.salesManagement.system.service.UserService;

@Controller
@RequestMapping(value = "/roleController")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;

//显示角色列表
	@RequestMapping(value = "rolelist.do")
	public String list(HttpServletRequest req, HttpServletResponse resp, Role role, Model model)
			throws ServletException, IOException {
		List<Role> roleList = roleService.queryAllRole();
		model.addAttribute("roleList", roleList);
		// for (User user2 : userList) {
		// System.out.println(user2);
		// }
		return "view/role/roleList";

	}

//增加角色信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/role/roleAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String role_name = req.getParameter("role_name");
		String role_state = req.getParameter("role_state");

		Role role = new Role();
		role.setRoleName(role_name);
		role.setRoleState(role_state);
		int n = roleService.addRoleByRole(role);

		// 重新返回主页
		List<Role> roleList = roleService.queryAllRole();
		model.addAttribute("roleList", roleList);
		// 2.跳转页面
		return "view/role/roleList";
	}

//修改指定角色信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {

		String roleIdStr = req.getParameter("roleId");
		int roleId = Integer.parseInt(roleIdStr);

		Role role = roleService.queryRoleByRole_id(roleId);

		req.setAttribute("role", role);
		return "view/role/roleUpdate";

	}

	// 修改用户信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String role_name = req.getParameter("role_name");
		String role_state = req.getParameter("role_state");
		String roleIdStr = req.getParameter("role_id");
		int role_id = Integer.parseInt(roleIdStr);

		Role role = new Role();
		role.setRoleName(role_name);
		role.setRoleState(role_state);
		role.setRoleId(role_id);
		int n = roleService.updateRoleById(role);

		// 重新返回主页
		List<Role> roleList = roleService.queryAllRole();
		model.addAttribute("roleList", roleList);
		// 2.跳转页面
		return "view/role/roleList";

	}

//删除指定用户信息
	@RequestMapping(value = "deleteRole.do")
	public String delete(HttpServletRequest req, HttpServletResponse resp, User user, Model model,
			@RequestParam("roleId") String roleId) throws ServletException, IOException {
		// System.out.println(roleId);
		int role_id = Integer.parseInt(roleId);
		int i = roleService.deleteRoleByRole_id(role_id);

		List<Role> roleList = roleService.queryAllRole();
		model.addAttribute("roleList", roleList);

		return "view/role/roleList";

	}

//分配角色
	@RequestMapping("FPRoleUI.do")
	public String FPRoleUI(HttpServletRequest req, HttpServletResponse resp, @RequestParam("userId") String userId,
			Model model) throws ServletException, IOException {
		// 用户ID
		String user_idStr = req.getParameter("userId");
		int user_id = Integer.parseInt(user_idStr);

		User u = userService.queryUserByUser_id(user_id);
		req.setAttribute("user", u);

		List<Role> roleList = roleService.queryAllRole();
		req.setAttribute("roleList", roleList);

		Role roleXZ = roleService.queryXZRoleByUser_id(user_id);
		req.setAttribute("roleXZ", roleXZ);

		// 跳转页面
		return "view/role/FPRole";
	}

	@RequestMapping("FPRole.do")
	public String FPRole(HttpServletRequest req, HttpServletResponse resp, Model model)
			throws ServletException, IOException {
		// 用户ID
		// System.err.println(userId);
		String userId = req.getParameter("userId");
		int id = Integer.parseInt(userId);

		String roleId = req.getParameter("roleId");
		int id2 = Integer.parseInt(roleId);

		int m = userRoleService.deleteUserRoleByUser_id(id);

		UserRole userRole = new UserRole();
		userRole.setUserId(id);
		userRole.setRoleId(id2);

		int n = userRoleService.insertUserRoleByUserRole(userRole);

		// 查询角色
		User user = userService.queryUserByUser_id(id);
		List<Role> roleList = roleService.queryAllRole();
		req.setAttribute("user", user);
		req.setAttribute("roleList", roleList);

		// 选中角色数据
		Role roleXZ = roleService.queryXZRoleByUser_id(id);
		model.addAttribute("roleXZ", roleXZ);

		// 跳转页面
		return "view/role/FPRole";
	}

	// 搜索指定的角色 信息
	@RequestMapping(value = "searchRole.do")
	public String searchUser(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "role_search", required = false) String role_search)
			throws ServletException, IOException {
		// 判断传入的是编号还是名字
		if (role_search != "") {
			char first = role_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int role_id = Integer.parseInt(role_search);

				List<Role> roleList = roleService.selectRoleByRoleId(role_id);

				model.addAttribute("roleList", roleList);
				return "view/role/roleList";
			} else {
				String role_name = "%" + role_search + "%";
				List<Role> roleList = roleService.selectRoleByName(role_name);

				model.addAttribute("roleList", roleList);
				return "view/role/roleList";
			}
		} else {
			// 重新返回主页
			List<Role> roleList = roleService.queryAllRole();
			model.addAttribute("roleList", roleList);

			return "view/role/roleList";
		}

	}

}
