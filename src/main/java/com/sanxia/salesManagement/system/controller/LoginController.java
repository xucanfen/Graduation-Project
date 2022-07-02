package com.sanxia.salesManagement.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanxia.salesManagement.system.model.Permission;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.PermissionService;
import com.sanxia.salesManagement.system.service.UserService;

@Controller
// @RequestMapping(value="/loginController")
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest req, HttpServletResponse resp, Model model, User user) {

		String login_name = req.getParameter("login_name");
		String password = req.getParameter("password");
		user.setLoginName(login_name);
		user.setPassword(password);

		User u = userService.queryUserByLogin_nameAndPassword(user);

		if (u != null) {
			// 2、跳转页面
			HttpSession session = req.getSession();
			session.setAttribute("USER", u);
			// 用户id
			int user_id = u.getUserId();
			// 查询用户拥有的菜单的所有信息

			List<Permission> menuList = permissionService.queryMenuByUserId(user_id);
			// 创建集合，保存对应子菜单
			HashMap<Integer, List<Permission>> permissionList = new HashMap<>();
			// 遍历
			for (Permission menu : menuList) {
				// 3、菜单下权限列表
				List<Permission> menuDownPermissionList = permissionService
						.querypermissionByPId_Son(menu.getPermissionId());
				// 4、 维护父节点和子节点关系
				int pId = menu.getPermissionId();
				permissionList.put(pId, menuDownPermissionList);
				req.setAttribute("menuList", menuList);
				req.setAttribute("permissionList", permissionList);
			}
			
			return "index";
			
		} else {
			// 2、跳转页面
			model.addAttribute("message", "用户名或密码错误！");
			return "login";
		}
	}

}
