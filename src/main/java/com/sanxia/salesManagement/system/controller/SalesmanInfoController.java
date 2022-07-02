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

import com.sanxia.salesManagement.system.model.SalesmanInfo;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.model.UserRole;
import com.sanxia.salesManagement.system.model.WorkInfo;
import com.sanxia.salesManagement.system.service.RoleService;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;
import com.sanxia.salesManagement.system.service.UserRoleService;
import com.sanxia.salesManagement.system.service.UserService;
import com.sanxia.salesManagement.system.service.WorkInfoService;

@Controller
@RequestMapping(value = "/salesmanInfoController")
public class SalesmanInfoController {
	@Autowired
	private SalesmanInfoService salesmanInfoService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private WorkInfoService workInfoService;

	// 显示用户列表
	@RequestMapping(value = "salesmanInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
		model.addAttribute("salesmanInfoList", salesmanInfoList);

		return "view/salesmanInfo/salesmanInfoList";
	}

	// 增加信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/salesmanInfo/salesmanInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		int max_id = salesmanInfoService.selectMax_id();
		int salesman_id = max_id + 1;

		String salesman_name = req.getParameter("salesman_name");
		String salesman_sex = req.getParameter("salesman_sex");

		String salesman_ageStr = req.getParameter("salesman_age");
		int salesman_age = Integer.parseInt(salesman_ageStr);

		String salesman_tel = req.getParameter("salesman_tel");

		String time = req.getParameter("join_time");

		Date join_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		SalesmanInfo s = new SalesmanInfo();
		s.setSalesmanId(salesman_id);
		s.setSalesmanName(salesman_name);
		s.setSalesmanAge(salesman_age);
		s.setSalesmanSex(salesman_sex);
		s.setSalesmanTel(salesman_tel);
		s.setJoinTime(join_time);

		int n = salesmanInfoService.addsalesmanInfoBysalesmanInfo(s);
		if(n==1) {
			// 在添加销售员的同时也要添加一条用户信息
			User u = new User();
			String login_name = Integer.toString(salesman_id);
			u.setName(salesman_name);
			u.setLoginName(login_name);// 初始化用户名为工号
			u.setPassword("123456");// 初始化密码123456
			u.setCreateTime(new Date());
			int a = userService.addUserByUser(u);

			// 给添加的用户自动分配权限
			// 先查询销售员的角色id
			String role_name = "销售员";
			int role_id = roleService.queryRole_idByRole_name(role_name);
			// 根据工号查询用户表的用户id
			int user_id = userService.queryUserIdByLoginName(login_name);
			// 再往用户角色表里添加数据
			UserRole ur = new UserRole();
			ur.setRoleId(role_id);
			ur.setUserId(user_id);
			int b = userRoleService.addUserRoleByUserRole(ur);
		}else {
			// 重新返回页面
			List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
			model.addAttribute("salesmanInfoList", salesmanInfoList);

			return "view/salesmanInfo/salesmanInfoList";
		}
	
		// 重新返回页面
		List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
		model.addAttribute("salesmanInfoList", salesmanInfoList);

		return "view/salesmanInfo/salesmanInfoList";
	}

	// 修改指定信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String salesmanIdStr = req.getParameter("salesmanId");
		int salesmanId = Integer.parseInt(salesmanIdStr);

		SalesmanInfo salesmanInfo = salesmanInfoService.querySalesmanInfoBySalesmanId(salesmanId);

		req.setAttribute("salesmanInfo", salesmanInfo);
		return "view/salesmanInfo/salesmanInfoUpdate";

	}

	// 修改信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String salesman_name = req.getParameter("salesman_name");
		String salesman_sex = req.getParameter("salesman_sex");
		String salesman_ageStr = req.getParameter("salesman_age");
		int salesman_age = Integer.parseInt(salesman_ageStr);

		String salesman_idStr = req.getParameter("salesman_id");
		int salesman_id = Integer.parseInt(salesman_idStr);

		String salesman_tel = req.getParameter("salesman_tel");

		String time = req.getParameter("join_time");
		Date join_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		SalesmanInfo s = new SalesmanInfo();
		s.setJoinTime(join_time);
		s.setSalesmanAge(salesman_age);
		s.setSalesmanId(salesman_id);
		s.setSalesmanName(salesman_name);
		s.setSalesmanSex(salesman_sex);
		s.setSalesmanTel(salesman_tel);
		
		// 修改用户
		User u = new User();
		String login_name = Integer.toString(salesman_id);
		// 根据工号查询用户表的用户id
		int user_id = userService.queryUserIdByLoginName(login_name);
		u.setName(salesman_name);
		u.setUserId(user_id);

		int i = salesmanInfoService.updateSalesmanInfoById(s);
		
		if(i==1) {
			
			int n = userService.updateUserNameById(u);
		}else {
			// 重新返回主页
			List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
			model.addAttribute("salesmanInfoList", salesmanInfoList);

			return "view/salesmanInfo/salesmanInfoList";
		}
		// 重新返回主页
		List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
		model.addAttribute("salesmanInfoList", salesmanInfoList);

		return "view/salesmanInfo/salesmanInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deleteSalesmanInfo.do")
	public String deleteSalesmanInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SalesmanInfo salesmanInfo, @RequestParam(value = "salesmanId", required = false) String salesmanIdStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

		int salesmanId = Integer.parseInt(salesmanIdStr);

		int i = salesmanInfoService.deleteSalesmanInfoById(salesmanId);

		String login_name = salesmanIdStr;
		int user_id = userService.queryUserIdByLoginName(login_name);
		if(i==1) {
			// 同时也要删除用户表信息
			int n = userService.deleteUserByLoginName(login_name);

			// 删除用户角色的中间表
			int m = userRoleService.deleteUserRoleByUser_id(user_id);
		}else {
			// 重新返回主页
			List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
			model.addAttribute("salesmanInfoList", salesmanInfoList);

			return "view/salesmanInfo/salesmanInfoList";
		}

		// 重新返回主页
		List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
		model.addAttribute("salesmanInfoList", salesmanInfoList);

		return "view/salesmanInfo/salesmanInfoList";

	}

//查看考勤记录
	@RequestMapping(value = "selectWorkInfo.do")
	public String selectWorkInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SalesmanInfo salesmanInfo, @RequestParam(value = "salesmanId", required = false) String salesmanIdStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

		int salesmanId = Integer.parseInt(salesmanIdStr);

		// 3.调用业务
		List<WorkInfo> workInfoList = workInfoService.selectWorkInfoById(salesmanId);
		model.addAttribute("workInfoList", workInfoList);

		session.setAttribute("salesmanId", salesmanId);

		return "view/salesmanInfo/workInfoIDList";
	}

//查询考勤记录
	@RequestMapping(value = "timeSearch.do")
	public String timeSearch(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SalesmanInfo salesmanInfo) throws ServletException, IOException, ParseException {

		int salesmanId = (int) session.getAttribute("salesmanId");

		String timeStr = req.getParameter("time_search");
		Date time = new SimpleDateFormat("yyyy-MM-dd").parse(timeStr);

		WorkInfo workInfo = new WorkInfo();
		workInfo.setSalesmanId(salesmanId);
		workInfo.setTime(time);
		// 3.调用业务
		List<WorkInfo> workInfoList = workInfoService.selectWorkInfoByIdAndTime(workInfo);
		model.addAttribute("workInfoList", workInfoList);

		return "view/salesmanInfo/workInfoIDList";
	}

//搜索指定的员工信息
	@RequestMapping(value = "searchSalesmanInfo.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "salesmanInfo_search", required = false) String salesmanInfo_search)
			throws ServletException, IOException {
		// 判断传入的是编号还是名字
		if (salesmanInfo_search != "") {
			char first = salesmanInfo_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int salesman_id = Integer.parseInt(salesmanInfo_search);

				List<SalesmanInfo> salesmanInfoList = salesmanInfoService.querySalesmanInfoById(salesman_id);
				model.addAttribute("salesmanInfoList", salesmanInfoList);

				return "view/salesmanInfo/salesmanInfoList";
			} else {
				String salesman_name = "%" + salesmanInfo_search + "%";
				List<SalesmanInfo> salesmanInfoList = salesmanInfoService.querySalesmanInfoByName(salesman_name);
				model.addAttribute("salesmanInfoList", salesmanInfoList);

				return "view/salesmanInfo/salesmanInfoList";

			}
		} else {

			// 重新返回主页
			List<SalesmanInfo> salesmanInfoList = salesmanInfoService.queryAllSalesmanInfo();
			model.addAttribute("salesmanInfoList", salesmanInfoList);

			return "view/salesmanInfo/salesmanInfoList";

		}

	}

}
