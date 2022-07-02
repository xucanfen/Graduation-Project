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

import com.sanxia.salesManagement.system.model.OffInfo;
import com.sanxia.salesManagement.system.model.OverInfo;
import com.sanxia.salesManagement.system.model.SalesmanInfo;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.model.WorkInfo;
import com.sanxia.salesManagement.system.service.OverInfoService;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;
import com.sanxia.salesManagement.system.service.WorkInfoService;

@Controller
@RequestMapping(value = "/overInfoController")
public class OverInfoController {
	@Autowired
	private OverInfoService overInfoService;

	@Autowired
	private SalesmanInfoService salesmanInfoService;

	// 显示用户列表
	@RequestMapping(value = "overInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<OverInfo> overInfolist = overInfoService.queryAllOverInfo();
		model.addAttribute("overInfolist", overInfolist);

		return "view/overInfo/overInfoList";

	}

	// 增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/overInfo/overInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String salesman_idStr = req.getParameter("salesman_id");
		int salesman_id = Integer.parseInt(salesman_idStr);
		// 查询销售员的名字

		String salesman_name = salesmanInfoService.selectNameById(salesman_id);

		String timeStr1 = req.getParameter("start_time");
		Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr1);

		String timeStr2 = req.getParameter("end_time");
		Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr2);

		// 计算加班时间
		long nd = 1000 * 8 * 60 * 60;// 按照上班时间8小时为一天计算
		long diff = end_time.getTime() - start_time.getTime();
		BigDecimal nd1 = new BigDecimal(nd);
		BigDecimal diff1 = new BigDecimal(diff);
		System.out.println(nd1);
		System.out.println(diff1);
		BigDecimal totals_days = diff1.divide(nd1, 2, BigDecimal.ROUND_HALF_DOWN);

		if (salesman_name != null) {
			OverInfo over = new OverInfo();
			over.setEndTime(end_time);
			over.setStartTime(start_time);
			over.setSalesmanId(salesman_id);
			over.setSalesmanName(salesman_name);
			over.setTotalDays(totals_days);

			int n = overInfoService.addOverInfoByOverInfo(over);
		} else {
			String msg = "此销售员不存在";
		}

		List<OverInfo> overInfolist = overInfoService.queryAllOverInfo();
		model.addAttribute("overInfolist", overInfolist);

		return "view/overInfo/overInfoList";
	}

	// 修改指定信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		OverInfo overInfo = overInfoService.queryOverInfoById(id);

		req.setAttribute("overInfo", overInfo);
		return "view/overInfo/overInfoUpdate";

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

		String timeStr1 = req.getParameter("start_time");
		Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr1);

		String timeStr2 = req.getParameter("end_time");
		Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr2);

		// 计算加班时间
		long nd = 1000 * 8 * 60 * 60;// 按照上班时间8小时为一天计算
		long diff = end_time.getTime() - start_time.getTime();
		BigDecimal nd1 = new BigDecimal(nd);
		BigDecimal diff1 = new BigDecimal(diff);
		System.out.println(nd1);
		System.out.println(diff1);
		BigDecimal totals_days = diff1.divide(nd1, 2, BigDecimal.ROUND_HALF_DOWN);

		if (salesman_name != null) {
			OverInfo over = new OverInfo();
			over.setEndTime(end_time);
			over.setStartTime(start_time);
			over.setSalesmanId(salesman_id);
			over.setSalesmanName(salesman_name);
			over.setTotalDays(totals_days);
			over.setId(id);

			int n = overInfoService.updateOverInfoByOverInfo(over);
		} else {
			String msg = "此销售员不存在";
		}

		List<OverInfo> overInfolist = overInfoService.queryAllOverInfo();
		model.addAttribute("overInfolist", overInfolist);

		return "view/overInfo/overInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deleteOverInfo.do")
	public String deleteOverInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SalesmanInfo salesmanInfo, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

		int id = Integer.parseInt(idStr);

		int i = overInfoService.deleteOverInfoById(id);

		// 重新返回主页
		List<OverInfo> overInfolist = overInfoService.queryAllOverInfo();
		model.addAttribute("overInfolist", overInfolist);

		return "view/overInfo/overInfoList";

	}

	// 搜索指定的加班信息
	@RequestMapping(value = "searchOverInfo.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "overInfo_search", required = false) String overInfo_search)
			throws ServletException, IOException {
		// 判断传入的是编号还是名字
		if (overInfo_search != "") {
			char first = overInfo_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int salesman_id = Integer.parseInt(overInfo_search);

				List<OverInfo> overInfolist = overInfoService.selectOverBySalesmansId(salesman_id);

				model.addAttribute("overInfolist", overInfolist);

				return "view/overInfo/overInfoList";
			} else {
				String salesman_name = "%" + overInfo_search + "%";
				List<OverInfo> overInfolist = overInfoService.selectOBySalesmansName(salesman_name);

				model.addAttribute("overInfolist", overInfolist);

				return "view/overInfo/overInfoList";

			}
		} else {

			// 重新返回主页
			List<OverInfo> overInfolist = overInfoService.queryAllOverInfo();
			model.addAttribute("overInfolist", overInfolist);

			return "view/overInfo/overInfoList";

		}

	}

}
