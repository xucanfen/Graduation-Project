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

import com.sanxia.salesManagement.system.model.OverInfo;
import com.sanxia.salesManagement.system.model.PayinInfo;
import com.sanxia.salesManagement.system.model.SalesmanInfo;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.model.WorkInfo;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;
import com.sanxia.salesManagement.system.service.WorkInfoService;

@Controller
@RequestMapping(value = "/workInfoController")
public class WorkInfoController {
	@Autowired
	private WorkInfoService workInfoService;

	@Autowired
	private SalesmanInfoService salesmanInfoService;

	// 显示用户列表
	@RequestMapping(value = "workInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<WorkInfo> workInfoList = workInfoService.queryAllWorkInfo();
		model.addAttribute("workInfoList", workInfoList);

		return "view/workInfo/workInfoList";

	}

	// 增加工作信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/workInfo/workInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String salesman_idStr = req.getParameter("salesman_id");
		int salesman_id = Integer.parseInt(salesman_idStr);

		String salesman_name = salesmanInfoService.selectNameById(salesman_id);

		String timeStr = req.getParameter("time");
		Date time = new SimpleDateFormat("yyyy-MM-dd").parse(timeStr);

		String start_timeStr = req.getParameter("start_time");
		String end_timeStr = req.getParameter("end_time");

		if (start_timeStr != null && end_timeStr != null) {

			Date d = new SimpleDateFormat("HH:mm:ss").parse(start_timeStr);
			Time start_time = new Time(d.getTime());// 实际上班时间

			Date d1 = new SimpleDateFormat("HH:mm:ss").parse(end_timeStr);
			Time end_time = new Time(d1.getTime());// 实际下班时间

			// 设置上班和下班时间的标准
			Date st_d = new SimpleDateFormat("HH:mm:ss").parse("09:00:00");
			Time st = new Time(st_d.getTime());// 标准上班时间

			Date et_d = new SimpleDateFormat("HH:mm:ss").parse("18:00:00");
			Time et = new Time(et_d.getTime());// 标准下班时间
			// 判断上班时间是否超过九点，判定是否迟到
			String late;
			if (start_time.after(st)) {
				late = "是";
			} else {
				late = "否";
			}
			// 判断下班时间是否早于6点，判定是否早退
			String leave_early;
			if (end_time.before(et)) {
				leave_early = "是";
			} else {
				leave_early = "否";
			}

			// 是否缺勤设置
			String absenteeism = "否";
			/////
			WorkInfo w = new WorkInfo();
			w.setAbsenteeism(absenteeism);
			w.setEndTime(end_time);
			w.setStartTime(start_time);
			w.setLate(late);
			w.setLeaveEarly(leave_early);
			w.setSalesmanId(salesman_id);
			w.setSalesmanName(salesman_name);
			w.setTime(time);

			int n = workInfoService.addWorkInfoByWorkInfo(w);

		} else if (start_timeStr == null && end_timeStr == null) {
			Date d = new SimpleDateFormat("HH:mm:ss").parse("00:00:00");
			Time start_time = new Time(d.getTime());// 实际上班时间

			Date d1 = new SimpleDateFormat("HH:mm:ss").parse("00:00:00");
			Time end_time = new Time(d1.getTime());// 实际下班时间

			String late = "否";
			String leave_early = "否";
			String absenteeism = "是";

			WorkInfo w = new WorkInfo();
			w.setEndTime(end_time);
			w.setStartTime(start_time);
			w.setAbsenteeism(absenteeism);
			w.setLate(late);
			w.setLeaveEarly(leave_early);
			w.setSalesmanId(salesman_id);
			w.setSalesmanName(salesman_name);
			w.setTime(time);

			int n = workInfoService.addWorkInfoByWorkInfo(w);
		}

		List<WorkInfo> workInfoList = workInfoService.queryAllWorkInfo();
		model.addAttribute("workInfoList", workInfoList);

		return "view/workInfo/workInfoList";
	}

	// 修改指定信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		WorkInfo workInfo = workInfoService.queryWorkInfoById(id);

		req.setAttribute("workInfo", workInfo);
		return "view/workInfo/workInfoUpdate";

	}

	// 修改信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {

		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String salesman_idStr = req.getParameter("salesman_id");
		int salesman_id = Integer.parseInt(salesman_idStr);

		String salesman_name = salesmanInfoService.selectNameById(salesman_id);

		String timeStr = req.getParameter("time");
		Date time = new SimpleDateFormat("yyyy-MM-dd").parse(timeStr);

		String start_timeStr = req.getParameter("start_time");
		Date d = new SimpleDateFormat("HH:mm:ss").parse(start_timeStr);
		Time start_time = new Time(d.getTime());// 实际上班时间

		String end_timeStr = req.getParameter("end_time");
		Date d1 = new SimpleDateFormat("HH:mm:ss").parse(end_timeStr);
		Time end_time = new Time(d1.getTime());// 实际下班时间

		Date st_d = new SimpleDateFormat("HH:mm:ss").parse("09:00:00");
		Time st = new Time(st_d.getTime());// 标准上班时间

		Date et_d = new SimpleDateFormat("HH:mm:ss").parse("18:00:00");
		Time et = new Time(et_d.getTime());// 标准下班时间
		// 判断上班时间是否超过九点，判定是否迟到
		String late;
		if (start_time.after(st)) {
			late = "是";
		} else {
			late = "否";
		}
		// 判断下班时间是否早于6点，判定是否早退
		String leave_early;
		if (end_time.before(et)) {
			leave_early = "是";
		} else {
			leave_early = "否";
		}
		String absenteeism = "否";
		/////
		WorkInfo w = new WorkInfo();
		w.setAbsenteeism(absenteeism);
		w.setEndTime(end_time);
		w.setStartTime(start_time);
		w.setLate(late);
		w.setLeaveEarly(leave_early);
		w.setSalesmanId(salesman_id);
		w.setSalesmanName(salesman_name);
		w.setTime(time);
		w.setId(id);

		int n = workInfoService.updateWorkInfoByWorkInfo(w);

		// 重新返回主页
		List<WorkInfo> workInfoList = workInfoService.queryAllWorkInfo();
		model.addAttribute("workInfoList", workInfoList);

		return "view/workInfo/workInfoList";
	}

	// 删除指定用户信息
	@RequestMapping(value = "deleteWorkInfo.do")
	public String deleteWorkInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SalesmanInfo salesmanInfo, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

		int id = Integer.parseInt(idStr);

		int i = workInfoService.deleteWorkInfoById(id);

		// 重新返回主页
		List<WorkInfo> workInfoList = workInfoService.queryAllWorkInfo();
		model.addAttribute("workInfoList", workInfoList);

		return "view/workInfo/workInfoList";

	}

//搜索指定的考勤信息
	@RequestMapping(value = "searchWorkInfo.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "workInfo_search", required = false) String workInfo_search)
			throws ServletException, IOException {
//判断传入的是编号还是名字
		if (workInfo_search != "") {
			char first = workInfo_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int salesman_id = Integer.parseInt(workInfo_search);

				List<WorkInfo> workInfoList = workInfoService.selectWorkInfoById(salesman_id);
				model.addAttribute("workInfoList", workInfoList);

				return "view/workInfo/workInfoList";
			} else {
				String salesman_name = "%" + workInfo_search + "%";
				List<WorkInfo> workInfoList = workInfoService.selectWorkInfoByName(salesman_name);
				model.addAttribute("workInfoList", workInfoList);

				return "view/workInfo/workInfoList";

			}
		} else {

			// 重新返回主页
			List<WorkInfo> workInfoList = workInfoService.queryAllWorkInfo();
			model.addAttribute("workInfoList", workInfoList);

			return "view/workInfo/workInfoList";

		}

	}

}
