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

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;
import com.sanxia.salesManagement.system.service.UserRoleService;
import com.sanxia.salesManagement.system.service.UserService;

@Controller
@RequestMapping(value = "/userController")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private SalesmanInfoService salesmanInfoService;

//显示用户列表
	@RequestMapping(value = "userlist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<User> userList = userService.queryAllUser();
		model.addAttribute("userList", userList);
//		for (User user : userList) {
//			System.out.println(user);
//		}
		// 2.跳转页面
		return "view/user/userList";
	}

//增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/user/userAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String name = req.getParameter("name");
		String login_name = req.getParameter("login_name");
		String password = req.getParameter("password");
//		String time= req.getParameter("create_time");
//		Date create_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		User u = new User();
		u.setLoginName(login_name);
		u.setName(name);
		u.setPassword(password);
		u.setCreateTime(new Date());
		int n = userService.addUserByUser(u);

		// 重新返回主页
		List<User> userList = userService.queryAllUser();
		model.addAttribute("userList", userList);
		// 2.跳转页面
		return "view/user/userList";
	}

//修改指定用户信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String userIdStr = req.getParameter("userId");
		int userId = Integer.parseInt(userIdStr);

		User user = userService.queryUserByUser_id(userId);

		req.setAttribute("user", user);
		return "view/user/userUpdate";

	}

	// 修改用户信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String name = req.getParameter("name");
		String login_name = req.getParameter("login_name");
		String password = req.getParameter("password");
		String user_idstr = req.getParameter("user_id");
		int user_id = Integer.parseInt(user_idstr);
		String time = req.getParameter("create_time");
		Date create_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		User u = new User();
		u.setLoginName(login_name);
		u.setName(name);
		u.setPassword(password);
		u.setUserId(user_id);
		u.setCreateTime(create_time);

		int i = userService.updateUserById(u);

		// 重新返回主页
		List<User> userList = userService.queryAllUser();
		model.addAttribute("userList", userList);
		// 2.跳转页面
		return "view/user/userList";

	}

//删除指定用户信息
	@RequestMapping(value = "deleteUser.do")
	public String deleteUser(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "userId", required = false) String userIdStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

//		String userIdStr= req.getParameter("userId");
		int userId = Integer.parseInt(userIdStr);

		int i = userService.deleteUserByUser_id(userId);

		int n = userRoleService.deleteUserRoleByUser_id(userId);

		// 重新返回主页
		List<User> userList = userService.queryAllUser();
		model.addAttribute("userList", userList);
		// 2.跳转页面
		return "view/user/userList";

	}

//搜索指定的用户信息
	@RequestMapping(value = "searchUser.do")
	public String searchUser(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "user_search", required = false) String user_search)
			throws ServletException, IOException {
//判断传入的是编号还是名字
		if (user_search != "") {
			char first = user_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int user_id = Integer.parseInt(user_search);

				List<User> userList = userService.selectUserByUserId(user_id);

				model.addAttribute("userList", userList);
				return "view/user/userList";
			} else {
				String user_name = "%" + user_search + "%";
				List<User> userList = userService.selectUserByUserName(user_name);

				model.addAttribute("userList", userList);
				return "view/user/userList";
			}
		} else {
			// 重新返回主页
			List<User> userList = userService.queryAllUser();
			model.addAttribute("userList", userList);
			// 2.跳转页面
			return "view/user/userList";
		}

	}

}
