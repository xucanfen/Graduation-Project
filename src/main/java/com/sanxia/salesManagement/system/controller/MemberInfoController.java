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

import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.MemberInfo;
import com.sanxia.salesManagement.system.model.SupplierGoods;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.service.MemberInfoService;

@Controller
@RequestMapping(value = "/memberInfoController")
public class MemberInfoController {
	@Autowired
	private MemberInfoService memberInfoService;

	// 显示会员信息列表
	@RequestMapping(value = "memberInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<MemberInfo> memberInfoList = memberInfoService.queryAllMemberInfo(); // 查询所有的商品信息数据
		model.addAttribute("memberInfoList", memberInfoList); // 数据返回前端

		// 2.跳转页面
		return "view/memberInfo/memberInfoList";
	}

	// 增加会员信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/memberInfo/memberInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String name = req.getParameter("vip_name");
		String tel = req.getParameter("vip_tel");
		String address = req.getParameter("vip_address");

		String birthday = req.getParameter("vip_birthday");
		Date vip_birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);

		Date join_time = new Date();

		MemberInfo m = new MemberInfo();
		m.setJoinTime(join_time);
		m.setVipAddress(address);
		m.setVipBirthday(vip_birthday);
		m.setVipName(name);
		m.setVipTel(tel);

		int n = memberInfoService.addMemberByMember(m);
		// 重新返回主页
		List<MemberInfo> memberInfoList = memberInfoService.queryAllMemberInfo();
		model.addAttribute("memberInfoList", memberInfoList);
		return "view/memberInfo/memberInfoList";
	}


	// 搜索指定的会员信息
	@RequestMapping(value = "searchInfo.do")
	public String searchInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo) throws ServletException, IOException {

		String Info_search = req.getParameter("Info_search");
		// 判断传入的是会员ID还是会员名
		// 输入ID是精确查询 输入名字是模糊查询

		if (Info_search != "") {
			// 判断传入的是编号还是名字
			char first = Info_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9') {
				int member_id = Integer.parseInt(Info_search);

				List<MemberInfo> memberInfoList = memberInfoService.selectMemberInfoByMemberId(member_id);
				model.addAttribute("memberInfoList", memberInfoList);
				return "view/memberInfo/memberInfoList";
			} else {
				String member_name = "%" + Info_search + "%";
				List<MemberInfo> memberInfoList = memberInfoService.selectMemberInfoByMemberName(member_name);
				model.addAttribute("memberInfoList", memberInfoList);
				return "view/memberInfo/memberInfoList";
			}
		} else {
			// 重新返回主页
			List<MemberInfo> memberInfoList = memberInfoService.queryAllMemberInfo();
			model.addAttribute("memberInfoList", memberInfoList);
			return "view/memberInfo/memberInfoList";

		}

	}

}
