package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.SaleInfo;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.service.TradeFinishService;

@Controller
@RequestMapping(value = "/tradeFinishController")
public class TradeFinishController {
	@Autowired
	private TradeFinishService tradeFinishService;

	// 显示完成订单信息
	@RequestMapping(value = "tradeFinishlist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<TradeFinish> tradeFinishlist = tradeFinishService.queryAllTradeFinish(); // 查询所有的交易完成信息数据
		model.addAttribute("tradeFinishlist", tradeFinishlist); // 数据返回前端

		// 2.跳转页面
		return "view/tradeFinish/tradeFinishList";
	}

	// 搜索指定的商品信息
	@RequestMapping(value = "searchInfo.do")
	public String searchInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo) throws ServletException, IOException {

		String goods_search = req.getParameter("goods_search");

		if (goods_search != "") {
			// 判断传入的是编号还是名字
			char first = goods_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9') {
				int sale_id = Integer.parseInt(goods_search);

				List<TradeFinish> tradeFinishlist = tradeFinishService.queryAllTradeFinishBySaleId(sale_id); // 查询所有的交易完成信息数据
				model.addAttribute("tradeFinishlist", tradeFinishlist); // 数据返回前端
				return "view/tradeFinish/tradeFinishList";
			} else {
				String goods_name = "%" + goods_search + "%";
				List<TradeFinish> tradeFinishlist = tradeFinishService.queryAllTradeFinishByName(goods_name); // 查询所有的交易完成信息数据
				model.addAttribute("tradeFinishlist", tradeFinishlist); // 数据返回前端
				return "view/tradeFinish/tradeFinishList";
			}
		} else {
			// 重新返回主页

			List<TradeFinish> tradeFinishlist = tradeFinishService.queryAllTradeFinish(); // 查询所有的交易完成信息数据
			model.addAttribute("tradeFinishlist", tradeFinishlist); // 数据返回前端
			return "view/tradeFinish/tradeFinishList";

		}

	}
}
