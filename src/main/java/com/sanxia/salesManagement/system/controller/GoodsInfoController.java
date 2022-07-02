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
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
import com.sanxia.salesManagement.system.service.UserService;

@Controller
@RequestMapping(value = "/goodsInfoController")
public class GoodsInfoController {
	@Autowired
	private GoodsInfoService goodsInfoService;

//显示用户列表
	@RequestMapping(value = "goodsInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<GoodsInfo> goodsInfoList = goodsInfoService.queryAllGoodsInfo(); // 查询所有的商品信息数据
		model.addAttribute("goodsInfoList", goodsInfoList); // 数据返回前端

		// 2.跳转页面
		return "view/goodsInfo/goodsInfoList";
	}

	// 增加商品信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/goodsInfo/goodsInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String name = req.getParameter("goods_name");
		String type = req.getParameter("goods_type");
		String price_str = req.getParameter("goods_price");
		BigDecimal price = new BigDecimal(price_str);
		 
		int number = 0;
//添加时，余量等于总量		
		int remaining = number;
//		Date create_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

		GoodsInfo g = new GoodsInfo();
		g.setGoodsName(name);
		g.setGoodsType(type);
		g.setGoodsPrice(price);
		g.setTotalNumber(number);
		g.setRemainingNumber(remaining);
		int n = goodsInfoService.addGoodsByGoods(g);
		// System.out.println(n); 打印0添加失败 打印1添加成功
		// System.out.println(g);
		// 重新返回主页
		List<GoodsInfo> goodsInfoList = goodsInfoService.queryAllGoodsInfo();
		model.addAttribute("goodsInfoList", goodsInfoList);
		// 2.跳转页面
		return "view/goodsInfo/goodsInfoList";
	}

	// 删除指定商品信息
	@RequestMapping(value = "deleteGoodsInfo.do")
	public String deleteGoodsInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo, @RequestParam(value = "goodsId", required = false) String goodsIdStr)
			throws ServletException, IOException {
		System.out.println("1111");
		System.out.println(goodsIdStr);

//			String userIdStr= req.getParameter("userId");
		int goods_id = Integer.parseInt(goodsIdStr);

		int i = goodsInfoService.deleteGoodsByGoods_id(goods_id);

		// 重新返回主页
		List<GoodsInfo> goodsInfoList = goodsInfoService.queryAllGoodsInfo();
		model.addAttribute("goodsInfoList", goodsInfoList);
		// 2.跳转页面
		return "view/goodsInfo/goodsInfoList";

	}

	// 搜索指定的商品信息
	@RequestMapping(value = "searchGoodsInfo.do")
	public String searchUser(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo, @RequestParam(value = "goodsInfo_search", required = false) String goodsInfo_search)
			throws ServletException, IOException {
		// 判断传入的是编号还是名字
		if (goodsInfo_search != "") {
			char first = goodsInfo_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9') {
				int goods_id = Integer.parseInt(goodsInfo_search);

				List<GoodsInfo> goodsInfoList = goodsInfoService.selectGoodsByGoodsId(goods_id);

				model.addAttribute("goodsInfoList", goodsInfoList);
				return "view/goodsInfo/goodsInfoList";
			} else {
				String goods_name = "%" + goodsInfo_search + "%";
				List<GoodsInfo> goodsInfoList = goodsInfoService.selectGoodsByGoodsName(goods_name);

				model.addAttribute("goodsInfoList", goodsInfoList);
				return "view/goodsInfo/goodsInfoList";
			}
		} else {
			// 重新返回主页
			List<GoodsInfo> goodsInfoList = goodsInfoService.queryAllGoodsInfo();
			model.addAttribute("goodsInfoList", goodsInfoList);
			// 2.跳转页面
			return "view/goodsInfo/goodsInfoList";

		}

	}

	// 修改指定商品信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String goodsIdStr = req.getParameter("goodsId");
		int goodsId = Integer.parseInt(goodsIdStr);

		GoodsInfo goodsInfo = goodsInfoService.queryGoodsByGoods_id(goodsId);

		req.setAttribute("goodsInfo", goodsInfo);
		return "view/goodsInfo/goodsInfoUpdate";

	}

	// 修改商品信息
	@RequestMapping(value = "goodsupdate.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String id_str = req.getParameter("goods_id");
		int id = Integer.parseInt(id_str);
		String name = req.getParameter("goods_name");
		String type = req.getParameter("goods_type");
		String price_str = req.getParameter("goods_price");
		BigDecimal price = new BigDecimal(price_str);
		

		GoodsInfo g = new GoodsInfo();
		g.setGoodsName(name);
		g.setGoodsType(type);
		g.setGoodsPrice(price);
		

		g.setGoodsId(id);

		int i = goodsInfoService.updateGoodsInfoById(g);

		// 重新返回主页
		List<GoodsInfo> goodsInfoList = goodsInfoService.queryAllGoodsInfo();
		model.addAttribute("goodsInfoList", goodsInfoList);
		// 2.跳转页面
		return "view/goodsInfo/goodsInfoList";

	}

}