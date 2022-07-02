package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.sanxia.salesManagement.system.model.ChangeInfo;
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.StatisticsInfo;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.service.OffInfoService;
import com.sanxia.salesManagement.system.service.OverInfoService;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;
import com.sanxia.salesManagement.system.service.StatisticsInfoService;
import com.sanxia.salesManagement.system.service.WorkInfoService;

@Controller
@RequestMapping(value = "/statisticsInfoController")
public class StatisticsInfoController {
	@Autowired
	private StatisticsInfoService statisticsInfoService;
	@Autowired
	private SalesmanInfoService salesmanInfoService;
	@Autowired
	private WorkInfoService workInfoService;
	@Autowired
	private OverInfoService overInfoService;
	@Autowired
	private OffInfoService offInfoService;

//显示用户列表
	@RequestMapping(value = "statisticsInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		
		List<StatisticsInfo> statisticsInfoList = statisticsInfoService.queryAllStatisticsInfo(); // 查询所有的商品信息数据
		model.addAttribute("statisticsInfoList", statisticsInfoList); // 数据返回前端

		return "view/statisticsInfo/statisticsInfoList";

	}

//统计月考勤信息
	@RequestMapping(value = "statisticsWork.do")
	public String statistics(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		// 获取员工id
		String salesman_idStr = req.getParameter("salesman_id");
		// 获取时间
		String year_monthStr = req.getParameter("year_month");
		// 查询条件不能为空
		if (salesman_idStr != "" && year_monthStr != "") {

			int salesman_id = Integer.parseInt(salesman_idStr);
			// 销售员姓名
			String salesman_name = salesmanInfoService.selectNameById(salesman_id);

			if (salesman_name != null) {
				// 得到开始时间
				String start_timeStr = year_monthStr + "-" + "01";
				Date start_time = new SimpleDateFormat("yyyy-MM-dd").parse(start_timeStr);

				// 得到结束时间
				String end_timeStr = year_monthStr + "-" + "31";
				Date end_time = new SimpleDateFormat("yyyy-MM-dd").parse(end_timeStr);

				// 得到考勤时间
				String statisticTime = year_monthStr;
				Date statistic_time = new SimpleDateFormat("yyyy-MM").parse(statisticTime);

				// 查询在这个时间范围内指定工号的出勤天数
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("salesmanIdTotal", salesman_id);
				map.put("startTimeTotal", start_time);
				map.put("endTimeTotal", end_time);

				int total_workInt = workInfoService.queryTotalByIdAndTime(map);

				// 查询迟到次数
				int total_late = workInfoService.queryLateByIdAndTime(map);
				System.out.println(total_late);

				// 查询早退次数
				int total_early = workInfoService.queryEarlyByIdAndTime(map);
				System.out.println(total_early);
				// 迟到或者早退要扣去0.25的工作时间
				double total_work_db = total_workInt - (total_late + total_early) * 0.25;

				BigDecimal total_work = new BigDecimal(total_work_db);

				// 查询在这个时间范围内指定工号的加班天数
				BigDecimal total_Over = overInfoService.queryOverByIdAndTime(map);
				BigDecimal total_Over1 = new BigDecimal("0");
				total_Over = (total_Over == null) ? total_Over1 : total_Over;
				// 查询在这个时间范围内指定工号的请假天数
				BigDecimal total_Off = offInfoService.queryOffByIdAndTime(map);
				BigDecimal total_Off1 = new BigDecimal("0");
				total_Off = (total_Off == null) ? total_Off1 : total_Off;

				// 得到出勤率
				float attendance_ft = (float) (total_work_db * 100 / 21.75);
				DecimalFormat df = new DecimalFormat("0.00");
				String attendance = df.format(attendance_ft) + "%";

				// 插入数据
				StatisticsInfo s = new StatisticsInfo();
				s.setAttendance(attendance);
				s.setSalesmanId(salesman_id);
				s.setSalesmanName(salesman_name);
				s.setStatisticTime(statistic_time);
				s.setTotalOff(total_Off);
				s.setTotalOver(total_Over);
				s.setTotalWork(total_work);

				int i = statisticsInfoService.insertStatisticsInfo(s);
				List<StatisticsInfo> statisticsInfoList = statisticsInfoService.queryAllStatisticsInfo(); // 查询所有的商品信息数据
				model.addAttribute("statisticsInfoList", statisticsInfoList); // 数据返回前端

				return "view/statisticsInfo/statisticsInfoList";

			} else {
				List<StatisticsInfo> statisticsInfoList = statisticsInfoService.queryAllStatisticsInfo(); // 查询所有的商品信息数据
				model.addAttribute("statisticsInfoList", statisticsInfoList); // 数据返回前端

				return "view/statisticsInfo/statisticsInfoList";
			}

		} else {
			List<StatisticsInfo> statisticsInfoList = statisticsInfoService.queryAllStatisticsInfo(); // 查询所有的商品信息数据
			model.addAttribute("statisticsInfoList", statisticsInfoList); // 数据返回前端

			return "view/statisticsInfo/statisticsInfoList";
		}
	}
	
//查询一年内的考勤记录
	@RequestMapping(value = "searchInfo.do")
	public String searchInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo) throws ServletException, IOException, ParseException {

		String salesman_idStr = req.getParameter("salesman_id2");
		
		String year_timeStr = req.getParameter("year_time");

		if (salesman_idStr != "" && year_timeStr!="") {
			int salesman_id = Integer.parseInt(salesman_idStr);
			//截取时间字符串
			String startStr = year_timeStr.substring(0, 7);
			String endStr = year_timeStr.substring(10);
			
			// 得到开始时间
			String start_timeStr = startStr  + "-" + "01";
			Date start_time = new SimpleDateFormat("yyyy-MM-dd").parse(start_timeStr);

			// 得到结束时间
			String end_timeStr = endStr + "-" + "31";
			Date end_time = new SimpleDateFormat("yyyy-MM-dd").parse(end_timeStr);
			//查询
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("salesmanIdSearch", salesman_id);
			map.put("startTimeSearch", start_time);
			map.put("endTimeSearch", end_time);
			List<StatisticsInfo> statisticsInfoList = statisticsInfoService.queryStatisticsInfoByYear(map);
			model.addAttribute("statisticsInfoList", statisticsInfoList); // 数据返回前端

			return "view/statisticsInfo/statisticsInfoList";
			
		} else {
			// 重新返回主页

			List<StatisticsInfo> statisticsInfoList = statisticsInfoService.queryAllStatisticsInfo(); // 查询所有的商品信息数据
			model.addAttribute("statisticsInfoList", statisticsInfoList); // 数据返回前端

			return "view/statisticsInfo/statisticsInfoList";

		}
	 
	}

}
