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

import com.sanxia.salesManagement.system.model.PayinInfo;
import com.sanxia.salesManagement.system.model.PayoutInfo;
import com.sanxia.salesManagement.system.model.User;

import com.sanxia.salesManagement.system.service.PayinInfoService;
import com.sanxia.salesManagement.system.service.PayoutInfoService;

@Controller
@RequestMapping(value = "/payoutInfoController")
public class PayoutInfoController {
	@Autowired
	private PayoutInfoService payoutInfoService;

//显示用户列表
	@RequestMapping(value = "payoutInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<PayoutInfo> payoutInfoList = payoutInfoService.queryAllPayoutInfo();
		model.addAttribute("payoutInfoList", payoutInfoList);
		return "view/payoutInfo/payoutInfoList";
	}

//增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 跳转到增加的页面
		return "view/payoutInfo/payoutInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String account_numberStr = req.getParameter("account_number");
		int account_number = Integer.parseInt(account_numberStr);

		String use_ways = req.getParameter("use_ways");

		String payout_moneyStr = req.getParameter("payout_money");
		BigDecimal payout_money = new BigDecimal(payout_moneyStr);

		String time1 = req.getParameter("time");
		Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);

		PayoutInfo p = new PayoutInfo();
		p.setAccountNumber(account_number);
		p.setUseWays(use_ways);
		p.setPayoutMoney(payout_money);
		p.setTime(time);

		int n = payoutInfoService.addPayoutInfoByInfo(p);

		List<PayoutInfo> payoutInfoList = payoutInfoService.queryAllPayoutInfo();
		model.addAttribute("payoutInfoList", payoutInfoList);

		return "view/payoutInfo/payoutInfoList";
	}

//修改指定用户信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		PayoutInfo payoutInfo = payoutInfoService.queryPayoutInfoById(id);

		req.setAttribute("payoutInfo", payoutInfo);
		return "view/payoutInfo/payoutInfoUpdate";

	}

	// 修改用户信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String account_numberStr = req.getParameter("account_number");
		int account_number = Integer.parseInt(account_numberStr);

		String use_ways = req.getParameter("use_ways");

		String payout_moneyStr = req.getParameter("payout_money");
		BigDecimal payout_money = new BigDecimal(payout_moneyStr);

		String time1 = req.getParameter("time");
		Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);

		PayoutInfo p = new PayoutInfo();
		p.setAccountNumber(account_number);
		p.setUseWays(use_ways);
		p.setPayoutMoney(payout_money);
		p.setTime(time);
		p.setId(id);

		int i = payoutInfoService.updatePayoutInfoByInfo(p);

		// 重新返回主页
		List<PayoutInfo> payoutInfoList = payoutInfoService.queryAllPayoutInfo();
		model.addAttribute("payoutInfoList", payoutInfoList);

		return "view/payoutInfo/payoutInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deletePayoutInfo.do")
	public String deletePayoutInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {

		int id = Integer.parseInt(idStr);

		int i = payoutInfoService.deletePayoutInfoById(id);

		// 重新返回主页
		List<PayoutInfo> payoutInfoList = payoutInfoService.queryAllPayoutInfo();
		model.addAttribute("payoutInfoList", payoutInfoList);

		return "view/payoutInfo/payoutInfoList";
	}

//搜索指定的支出信息
	@RequestMapping(value = "searchPayout.do")
	public String search(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model, User user,
			@RequestParam(value = "payout_search", required = false) String payout_search)
			throws ServletException, IOException {
//判断传入的是编号还是名字
		if (payout_search != "") {
			int id = Integer.parseInt(payout_search);

			// 重新返回主页
			List<PayoutInfo> payoutInfoList = payoutInfoService.selectPayoutInfoById(id);
			model.addAttribute("payoutInfoList", payoutInfoList);

			return "view/payoutInfo/payoutInfoList";

		}

		else {

			// 重新返回主页
			List<PayoutInfo> payoutInfoList = payoutInfoService.queryAllPayoutInfo();
			model.addAttribute("payoutInfoList", payoutInfoList);

			return "view/payoutInfo/payoutInfoList";
		}

	}

}
