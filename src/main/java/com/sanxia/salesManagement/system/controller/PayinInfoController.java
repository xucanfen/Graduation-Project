package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.sanxia.salesManagement.system.model.PayinInfo;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.DiscountInfoService;
import com.sanxia.salesManagement.system.service.PayinInfoService;

@Controller
@RequestMapping(value = "/payinInfoController")
public class PayinInfoController {
	@Autowired
	private PayinInfoService payinInfoService;

//显示用户列表
	@RequestMapping(value = "payinInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<PayinInfo> payinInfoList = payinInfoService.queryAllPayinInfo();
		model.addAttribute("payinInfoList", payinInfoList);
		return "view/payinInfo/payinInfoList";
	}

//增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/payinInfo/payinInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String account_numberStr = req.getParameter("account_number");
		int account_number = Integer.parseInt(account_numberStr);

		String origin = req.getParameter("origin");

		String payin_moneyStr = req.getParameter("payin_money");
		BigDecimal payin_money = new BigDecimal(payin_moneyStr);

		String time1 = req.getParameter("time");
		Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);

		PayinInfo p = new PayinInfo();
		p.setAccountNumber(account_number);
		p.setOrigin(origin);
		p.setPayinMoney(payin_money);
		p.setTime(time);

		int n = payinInfoService.addPayinInfoByInfo(p);

		List<PayinInfo> payinInfoList = payinInfoService.queryAllPayinInfo();
		model.addAttribute("payinInfoList", payinInfoList);

		return "view/payinInfo/payinInfoList";
	}

//修改指定用户信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		PayinInfo payinInfo = payinInfoService.queryPayinInfoById(id);

		req.setAttribute("payinInfo", payinInfo);
		return "view/payinInfo/payinInfoUpdate";

	}

	// 修改用户信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String account_numberStr = req.getParameter("account_number");
		int account_number = Integer.parseInt(account_numberStr);

		String origin = req.getParameter("origin");

		String payin_moneyStr = req.getParameter("payin_money");
		BigDecimal payin_money = new BigDecimal(payin_moneyStr);

		String time1 = req.getParameter("time");
		Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);

		PayinInfo p = new PayinInfo();
		p.setId(id);
		p.setAccountNumber(account_number);
		p.setOrigin(origin);
		p.setPayinMoney(payin_money);
		p.setTime(time);

		int i = payinInfoService.updatePayinInfoByInfo(p);

		// 重新返回主页
		List<PayinInfo> payinInfoList = payinInfoService.queryAllPayinInfo();
		model.addAttribute("payinInfoList", payinInfoList);

		return "view/payinInfo/payinInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deletePayinInfo.do")
	public String deletePayinInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {

		int id = Integer.parseInt(idStr);

		int i = payinInfoService.deletePayinInfoById(id);

		// 重新返回主页
		List<PayinInfo> payinInfoList = payinInfoService.queryAllPayinInfo();
		model.addAttribute("payinInfoList", payinInfoList);

		return "view/payinInfo/payinInfoList";
	}

	// 搜索指定的收入信息
	@RequestMapping(value = "searchPayin.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "payin_search", required = false) String payin_search)
			throws ServletException, IOException {
		// 判断传入的是编号还是名字
		if (payin_search != "") {
			int id = Integer.parseInt(payin_search);

			List<PayinInfo> payinInfoList = payinInfoService.selectPayinInfoById(id);
			model.addAttribute("payinInfoList", payinInfoList);

			return "view/payinInfo/payinInfoList";

		}

		else {

			List<PayinInfo> payinInfoList = payinInfoService.queryAllPayinInfo();
			model.addAttribute("payinInfoList", payinInfoList);

			return "view/payinInfo/payinInfoList";
		}

	}

//批量删除

	@RequestMapping(value = "batchDelete.do")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "payinId", required = false) String[] payinId)
			throws ServletException, IOException {

		if (payinId != null) {
			for (String string : payinId) {
				int n = payinInfoService.deletePayinInfoById(Integer.parseInt(string));
			}
		}

		// 重新返回主页
		List<PayinInfo> payinInfoList = payinInfoService.queryAllPayinInfo();
		model.addAttribute("payinInfoList", payinInfoList);

		return "view/payinInfo/payinInfoList";
	}
}
