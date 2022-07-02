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
import com.sanxia.salesManagement.system.model.SupplierGoods;
import com.sanxia.salesManagement.system.model.SupplierInfo;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
import com.sanxia.salesManagement.system.service.SupplierGoodsService;
import com.sanxia.salesManagement.system.service.SupplierInfoService;

@Controller
@RequestMapping(value = "/supplierGoodsController")
public class SupplierGoodsController {
	@Autowired
	private SupplierGoodsService supplierGoodsService;
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Autowired
	private SupplierInfoService supplierInfoService;

	// 显示供应商品列表
	@RequestMapping(value = "supplierGoodsInfoList.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService.queryAllSupplierGoodsInfo(); // 查询所有的商品信息数据
		model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList); // 数据返回前端

		// 2.跳转页面
		return "view/supplierGoods/supplierGoodsInfoList";
	}

	// 增加供应信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/supplierGoods/supplierGoodsInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String supplierid_str = req.getParameter("supplier_id");
		int supplierid = Integer.parseInt(supplierid_str);

		String goodsid_str = req.getParameter("goods_id");
		int goodsid = Integer.parseInt(goodsid_str);

		String name = goodsInfoService.selectGoodsNameById(goodsid);
		if (name != null) {

			String price_str = req.getParameter("supply_price");
			BigDecimal price = new BigDecimal(price_str);

			String number_str = req.getParameter("number");
			int number = Integer.parseInt(number_str);
			String type = goodsInfoService.selectGoodsTypeById(goodsid);
			String time = req.getParameter("supply_time");
			Date supply_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

			SupplierGoods s = new SupplierGoods();
			s.setSupplierId(supplierid);
			s.setGoodsId(goodsid);
			s.setGoodsType(type);
			s.setGoodsName(name);
			s.setSupplyTime(supply_time);
			s.setSupplyPrice(price);
			s.setNumber(number);
			int n = supplierGoodsService.addSupplierGoodsBySupplierGoods(s);
			if (n == 1) {
				// 修改商品的库存与总量
				// 获取商品的原来库存与总量
				GoodsInfo g = goodsInfoService.queryGoodsByGoods_id(goodsid);
				int all_num = g.getTotalNumber();
				int remain_num = g.getRemainingNumber();
				// 添加供货数量
				int new_all = all_num + number;
				int new_remain = remain_num + number;
				// 修改
				GoodsInfo goods = new GoodsInfo();
				goods.setGoodsId(goodsid);
				goods.setRemainingNumber(new_remain);
				goods.setTotalNumber(new_all);
				int a = goodsInfoService.updateNumByGoods(goods);
			}

		} else {
			List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService.queryAllSupplierGoodsInfo();// 再一次查询所有信息
			model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList); // 数据返回前端

			// 2.跳转页面
			return "view/supplierGoods/supplierGoodsInfoList";
		}
		// System.out.println(g);
		// 重新返回主页
		List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService.queryAllSupplierGoodsInfo();// 再一次查询所有信息
		model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList); // 数据返回前端

		// 2.跳转页面
		return "view/supplierGoods/supplierGoodsInfoList";
	}

	// 删除指定供应单信息
	@RequestMapping(value = "deleteSupplierInfo.do")
	public String deleteSupplierInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			SupplierGoods supplierGoods, @RequestParam(value = "id", required = false) String supplierGoodsIdStr)
			throws ServletException, IOException {

		int id = Integer.parseInt(supplierGoodsIdStr);

		int i = supplierGoodsService.deleteSupplierInfoById(id);

		// 重新返回主页
		List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService.queryAllSupplierGoodsInfo();
		model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList);
		// 2.跳转页面
		return "view/supplierGoods/supplierGoodsInfoList";

	}

	// 修改指定供应单信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		SupplierGoods supplierGoods = supplierGoodsService.querySupplierGoodsInfoBy_id(id);

		// 数据回显
		req.setAttribute("supplierGoods", supplierGoods);
		return "view/supplierGoods/supplierGoodsInfoUpdate";

	}

	// 修改供应单信息
	@RequestMapping(value = "Update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String supplierid_str = req.getParameter("supplier_id");
		int supplierid = Integer.parseInt(supplierid_str);

		
			String price_str = req.getParameter("supply_price");
			BigDecimal price = new BigDecimal(price_str);

			String number_str = req.getParameter("number");
			int number = Integer.parseInt(number_str);
			
			String time = req.getParameter("supply_time");
			Date supply_time = new SimpleDateFormat("yyyy-MM-dd").parse(time);

			SupplierGoods s = new SupplierGoods();
			s.setSupplierId(supplierid);
			s.setSupplyTime(supply_time);
			s.setSupplyPrice(price);
			s.setNumber(number);
			s.setId(id);

			int n = supplierGoodsService.UpdateSupplierGoodsIndoBy_id(s);
 
		// 重新返回主页
		List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService.queryAllSupplierGoodsInfo();
		model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList);
		// 2.跳转页面
		return "view/supplierGoods/supplierGoodsInfoList";

	}

	// 搜索指定的供应信息
	@RequestMapping(value = "searchSupplierGoods.do")
	public String searchSupplierGoods(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			Model model, SupplierGoods supplierGoods,
			@RequestParam(value = "supplierGoods_search", required = false) String supplierGoods_search)
			throws ServletException, IOException {
		// 判断传入的是供应商ID还是商品名
		// 输入ID是精确查询 输入名字是模糊查询
		if (supplierGoods_search != "") {
			char first = supplierGoods_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9' || first == '0') {
				int supplier_id = Integer.parseInt(supplierGoods_search);

				List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService
						.selectSupplierGoodsBySupplierId(supplier_id);

				model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList);
				return "view/supplierGoods/supplierGoodsInfoList";
			} else {
				String goods_name = "%" + supplierGoods_search + "%";
				List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService
						.selectSupplierGoodsByGoodsName(goods_name);

				model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList);
				return "view/supplierGoods/supplierGoodsInfoList";
			}
		} else {
			// 重新返回主页
			List<SupplierGoods> supplierGoodsInfoList = supplierGoodsService.queryAllSupplierGoodsInfo();
			model.addAttribute("supplierGoodsInfoList", supplierGoodsInfoList);
			return "view/supplierGoods/supplierGoodsInfoList";

		}

	}

	// 查看供应商信息
	@RequestMapping(value = "watchSupplierInfo.do")
	public String watchSupplierInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);
		// 查询对应的供应商id
		int supplier_id = supplierGoodsService.selectSupplierIdById(id);

		SupplierInfo supplierInfo = supplierInfoService.querySupplierInfoBy_id(supplier_id);
		req.setAttribute("supplierInfo", supplierInfo);
		return "view/supplierGoods/supplierInfo";

	}

}
