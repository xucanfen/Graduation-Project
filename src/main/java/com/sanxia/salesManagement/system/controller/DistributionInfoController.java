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

import com.sanxia.salesManagement.system.model.DiscountInfo;
import com.sanxia.salesManagement.system.model.DistributionInfo;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.DistributionInfoService;
import com.sanxia.salesManagement.system.service.UserService;

@Controller
@RequestMapping(value = "/distributionInfoController")
public class DistributionInfoController {
	@Autowired
	private DistributionInfoService distributionInfoService;

	// 显示用户列表
	@RequestMapping(value = "distributionInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<DistributionInfo> distributionInfoList = distributionInfoService.queryAllDistributionInfo();
		model.addAttribute("distributionInfoList", distributionInfoList);

		return "view/distributionInfo/distributionInfoList";
	}

	// 增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/distributionInfo/distributionInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String goods_idStr = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_idStr);

		String goods_name = req.getParameter("goods_name");
		String goods_numberStr = req.getParameter("goods_number");
		int goods_number = Integer.parseInt(goods_numberStr);

		String time = req.getParameter("distribution_time");
		Date distribution_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		String address = req.getParameter("address");
		String distribution_name = req.getParameter("distribution_name");
		String distribution_tel = req.getParameter("distribution_tel");

		DistributionInfo d = new DistributionInfo();
		d.setAddress(address);
		d.setDistributionName(distribution_name);
		d.setDistributionTel(distribution_tel);
		d.setDistributionTime(distribution_time);
		d.setGoodsId(goods_id);
		d.setGoodsName(goods_name);
		d.setGoodsNumber(goods_number);

		int n = distributionInfoService.addDistributionInfoByInfo(d);

		List<DistributionInfo> distributionInfoList = distributionInfoService.queryAllDistributionInfo();
		model.addAttribute("distributionInfoList", distributionInfoList);

		return "view/distributionInfo/distributionInfoList";
	}

	// 修改指定用户信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String distribution_idStr = req.getParameter("distributionId");
		int distribution_id = Integer.parseInt(distribution_idStr);

		DistributionInfo distributionInfo = distributionInfoService.queryDistributionInfoById(distribution_id);

		req.setAttribute("distributionInfo", distributionInfo);
		return "view/distributionInfo/distributionInfoUpdate";

	}

	// 修改用户信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String distribution_idStr = req.getParameter("distribution_id");
		int distribution_id = Integer.parseInt(distribution_idStr);

		String goods_idStr = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_idStr);

		String goods_name = req.getParameter("goods_name");
		String goods_numberStr = req.getParameter("goods_number");
		int goods_number = Integer.parseInt(goods_numberStr);

		String time = req.getParameter("distribution_time");
		Date distribution_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		String address = req.getParameter("address");
		String distribution_name = req.getParameter("distribution_name");
		String distribution_tel = req.getParameter("distribution_tel");

		DistributionInfo d = new DistributionInfo();
		d.setDistributionId(distribution_id);
		d.setAddress(address);
		d.setDistributionName(distribution_name);
		d.setDistributionTel(distribution_tel);
		d.setDistributionTime(distribution_time);
		d.setGoodsId(goods_id);
		d.setGoodsName(goods_name);
		d.setGoodsNumber(goods_number);

		int i = distributionInfoService.updateDistributionInfoByDistribution(d);

		// 重新返回主页
		List<DistributionInfo> distributionInfoList = distributionInfoService.queryAllDistributionInfo();
		model.addAttribute("distributionInfoList", distributionInfoList);

		return "view/distributionInfo/distributionInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deleteDistributionInfo.do")
	public String deleteDistributionInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			Model model, User user, @RequestParam(value = "distributionId", required = false) String distributionIdStr)
			throws ServletException, IOException {

		int distributionId = Integer.parseInt(distributionIdStr);

		int i = distributionInfoService.deleteDistributionInfoById(distributionId);

		// 重新返回主页
		List<DistributionInfo> distributionInfoList = distributionInfoService.queryAllDistributionInfo();
		model.addAttribute("distributionInfoList", distributionInfoList);

		return "view/distributionInfo/distributionInfoList";

	}

	// 搜索指定的配送信息
	@RequestMapping(value = "searchDistribution.do")
	public String searchDistribution(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "distribution_search", required = false) String distribution_search)
			throws ServletException, IOException {
//判断传入的是编号还是名字
		if (distribution_search != "") {
			int id = Integer.parseInt(distribution_search);

			List<DistributionInfo> distributionInfoList = distributionInfoService.selectDistributionById(id);

			model.addAttribute("distributionInfoList", distributionInfoList);

			return "view/distributionInfo/distributionInfoList";

		}

		else {

			// 重新返回主页
			List<DistributionInfo> distributionInfoList = distributionInfoService.queryAllDistributionInfo();
			model.addAttribute("distributionInfoList", distributionInfoList);
			return "view/distributionInfo/distributionInfoList";
		}

	}

}
