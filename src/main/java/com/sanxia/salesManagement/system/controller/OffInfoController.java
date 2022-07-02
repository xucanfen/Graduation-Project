package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
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

import com.sanxia.salesManagement.system.model.DiscountInfo;
import com.sanxia.salesManagement.system.model.OffInfo;
import com.sanxia.salesManagement.system.model.OverInfo;
import com.sanxia.salesManagement.system.model.SalesmanInfo;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.model.WorkInfo;
import com.sanxia.salesManagement.system.service.OffInfoService;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;
import com.sanxia.salesManagement.system.service.WorkInfoService;

@Controller
@RequestMapping(value = "/offInfoController")
public class OffInfoController {
	@Autowired
	private OffInfoService offInfoService;

	@Autowired
	private SalesmanInfoService salesmanInfoService;

	// 显示用户列表
	@RequestMapping(value = "offInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<OffInfo> offInfolist = offInfoService.queryAllOffInfo();
		model.addAttribute("offInfolist", offInfolist);

		return "view/offInfo/offInfoList";

	}

	// 增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/offInfo/offInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String salesman_idStr = req.getParameter("salesman_id");
		int salesman_id = Integer.parseInt(salesman_idStr);
		// 查询销售员的名字

		String salesman_name = salesmanInfoService.selectNameById(salesman_id);

		String reason = req.getParameter("reason");

		String timeStr1 = req.getParameter("start_time");
		Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr1);

		String timeStr2 = req.getParameter("end_time");
		Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr2);

		// 计算加班时间
		long nd = 1000 * 24 * 60 * 60;
		long diff = end_time.getTime() - start_time.getTime();
		BigDecimal nd1 = new BigDecimal(nd);
		BigDecimal diff1 = new BigDecimal(diff);
		System.out.println(nd1);
		System.out.println(diff1);
		BigDecimal totals_days = diff1.divide(nd1, 2, BigDecimal.ROUND_HALF_DOWN);

		if (salesman_name != null) {
			OffInfo off = new OffInfo();
			off.setEndTime(end_time);
			off.setStartTime(start_time);
			off.setSalesmanId(salesman_id);
			off.setSalesmanName(salesman_name);
			off.setTotalDays(totals_days);
			off.setReason(reason);

			int n = offInfoService.addOffInfoByOffInfo(off);
		} else {
			String msg = "此销售员不存在";
		}

		List<OffInfo> offInfolist = offInfoService.queryAllOffInfo();
		model.addAttribute("offInfolist", offInfolist);

		return "view/offInfo/offInfoList";
	}

	// 修改指定信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		OffInfo offInfo = offInfoService.queryOffInfoById(id);

		req.setAttribute("offInfo", offInfo);
		return "view/offInfo/offInfoUpdate";

	}

	// 修改信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {

		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String salesman_idStr = req.getParameter("salesman_id");
		int salesman_id = Integer.parseInt(salesman_idStr);
		// 查询销售员的名字

		String salesman_name = salesmanInfoService.selectNameById(salesman_id);

		String reason = req.getParameter("reason");

		String timeStr1 = req.getParameter("start_time");
		Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr1);

		String timeStr2 = req.getParameter("end_time");
		Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr2);

		// 计算加班时间
		long nd = 1000 * 24 * 60 * 60;
		long diff = end_time.getTime() - start_time.getTime();
		BigDecimal nd1 = new BigDecimal(nd);
		BigDecimal diff1 = new BigDecimal(diff);
		System.out.println(nd1);
		System.out.println(diff1);
		BigDecimal totals_days = diff1.divide(nd1, 2, BigDecimal.ROUND_HALF_DOWN);

		if (salesman_name != null) {
			OffInfo off = new OffInfo();
			off.setEndTime(end_time);
			off.setStartTime(start_time);
			off.setSalesmanId(salesman_id);
			off.setSalesmanName(salesman_name);
			off.setTotalDays(totals_days);
			off.setReason(reason);
			off.setId(id);

			int n = offInfoService.updateOffInfoByOffInfo(off);
		} else {
			String msg = "此销售员不存在";
		}

		List<OffInfo> offInfolist = offInfoService.queryAllOffInfo();
		model.addAttribute("offInfolist", offInfolist);

		return "view/offInfo/offInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deleteOffInfo.do")
	public String deleteOffInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SalesmanInfo salesmanInfo, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

		int id = Integer.parseInt(idStr);

		int i = offInfoService.deleteOffInfoById(id);

		// 重新返回主页
		List<OffInfo> offInfolist = offInfoService.queryAllOffInfo();
		model.addAttribute("offInfolist", offInfolist);

		return "view/offInfo/offInfoList";

	}

	// 搜索指定的请假信息
	@RequestMapping(value = "searchOffInfo.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "offInfo_search", required = false) String offInfo_search)
			throws ServletException, IOException {
//判断传入的是编号还是名字
		if (offInfo_search != "") {
			char first = offInfo_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int salesman_id = Integer.parseInt(offInfo_search);

				List<OffInfo> offInfolist = offInfoService.selectOffBySalesmansId(salesman_id);

				model.addAttribute("offInfolist", offInfolist);

				return "view/offInfo/offInfoList";
			} else {
				String salesman_name = "%" + offInfo_search + "%";
				List<OffInfo> offInfolist = offInfoService.selectOffBySalesmansName(salesman_name);

				model.addAttribute("offInfolist", offInfolist);

				return "view/offInfo/offInfoList";

			}
		} else {

			// 重新返回主页
			List<OffInfo> offInfolist = offInfoService.queryAllOffInfo();
			model.addAttribute("offInfolist", offInfolist);

			return "view/offInfo/offInfoList";

		}

	}

}
