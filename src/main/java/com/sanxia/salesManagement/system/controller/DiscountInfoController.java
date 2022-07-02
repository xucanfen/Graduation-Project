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
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.DiscountInfoService;
import com.sanxia.salesManagement.system.service.GoodsInfoService;

@Controller
@RequestMapping(value = "/discountInfoController")
public class DiscountInfoController {
	@Autowired
	private DiscountInfoService discountInfoService;
	@Autowired
	private GoodsInfoService goodsInfoService;

	// 显示用户列表
	@RequestMapping(value = "discountInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
		model.addAttribute("discountInfoList", discountInfoList);

		return "view/discountInfo/discountInfoList";

	}

	// 增加用户信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/discountInfo/discountInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {

		String goods_idStr = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_idStr);
		

		String goods_name =goodsInfoService.selectGoodsNameById(goods_id);
		if(goods_name!=null) {
			String discountStr = req.getParameter("discount");
			BigDecimal discount = new BigDecimal(discountStr);
			
			String time1 = req.getParameter("start_time");
			Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);
			
			String time2 = req.getParameter("end_time");
			Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time2);
			
			DiscountInfo d = new DiscountInfo();
			d.setDiscount(discount);
			d.setEndTime(end_time);
			d.setGoodsId(goods_id);
			d.setGoodsName(goods_name);
			d.setStartTime(start_time);
			
			int n = discountInfoService.addDiscountInfoByInfo(d);
			 
		}else {
			List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
			model.addAttribute("discountInfoList", discountInfoList);
			return "view/discountInfo/discountInfoList";
		}

		List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
		model.addAttribute("discountInfoList", discountInfoList);
		return "view/discountInfo/discountInfoList";
	}

//修改指定用户信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		DiscountInfo discountInfo = discountInfoService.queryDiscountInfoById(id);

		req.setAttribute("discountInfo", discountInfo);
		return "view/discountInfo/discountInfoUpdate";

	}

	// 修改用户信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String goods_idStr = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_idStr);

		String goods_name =goodsInfoService.selectGoodsNameById(goods_id);
		if(goods_name!=null) {
			String discountStr = req.getParameter("discount");
			BigDecimal discount = new BigDecimal(discountStr);

			String time1 = req.getParameter("start_time");
			Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);

			String time2 = req.getParameter("end_time");
			Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time2);

			DiscountInfo d = new DiscountInfo();
			d.setDiscount(discount);
			d.setEndTime(end_time);
			d.setGoodsId(goods_id);
			d.setGoodsName(goods_name);
			d.setStartTime(start_time);
			d.setId(id); 
			
			int i = discountInfoService.updateDistributionInfoByDistribution(d);
		 
		 
		}else {
			// 重新返回主页
			List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
			model.addAttribute("discountInfoList", discountInfoList);
			return "view/discountInfo/discountInfoList";
		}

		// 重新返回主页
		List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
		model.addAttribute("discountInfoList", discountInfoList);

		return "view/discountInfo/discountInfoList";

	}

	// 删除指定用户信息
	@RequestMapping(value = "deleteDiscountInfo.do")
	public String deleteDiscountInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {

		int id = Integer.parseInt(idStr);

		int i = discountInfoService.deleteDiscountInfoById(id);

		// 重新返回主页
		List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
		model.addAttribute("discountInfoList", discountInfoList);

		return "view/discountInfo/discountInfoList";

	}

	// 搜索指定的打折信息
	@RequestMapping(value = "searchDiscount.do")
	public String searchDiscount(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "discount_search", required = false) String discount_search)
			throws ServletException, IOException {
		// 判断传入的是编号还是名字
		if (discount_search != "") {
			char first = discount_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3') {
				int goods_id = Integer.parseInt(discount_search);

				List<DiscountInfo> discountInfoList = discountInfoService.selectDiscountByGoodsId(goods_id);

				model.addAttribute("discountInfoList", discountInfoList);
				return "view/discountInfo/discountInfoList";
			} else {
				String goods_name = "%" + discount_search + "%";
				List<DiscountInfo> discountInfoList = discountInfoService.selectDiscountByGoodsName(goods_name);

				model.addAttribute("discountInfoList", discountInfoList);
				return "view/discountInfo/discountInfoList";

			}
		} else {

			// 重新返回主页
			List<DiscountInfo> discountInfoList = discountInfoService.queryAllDiscountInfo();
			model.addAttribute("discountInfoList", discountInfoList);

			return "view/discountInfo/discountInfoList";
		}

	}

}
