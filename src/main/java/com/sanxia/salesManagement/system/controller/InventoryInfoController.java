package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.InventoryInfo;
import com.sanxia.salesManagement.system.model.StatisticsInfo;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
import com.sanxia.salesManagement.system.service.InventoryInfoService;
import com.sanxia.salesManagement.system.service.SupplierGoodsService;
import com.sanxia.salesManagement.system.service.TradeFinishService;

@Controller
@RequestMapping(value = "/inventoryInfoController")
public class InventoryInfoController {
	@Autowired
	private InventoryInfoService inventoryInfoService;
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Autowired
	private SupplierGoodsService supplierGoodsService;
	@Autowired
	private TradeFinishService tradeFinishService;

	// 显示用户列表
	@RequestMapping(value = "inventoryInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		List<InventoryInfo> inventoryInfoList = inventoryInfoService.queryAllInventoryInfo(); // 查询所有的商品信息数据
		model.addAttribute("inventoryInfoList", inventoryInfoList); // 数据返回前端
		return "view/inventoryInfo/inventoryInfoList";

	}

	// 盘点商品信息
	@RequestMapping(value = "inventorySearch.do")
	public String inventorySearch(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String goods_idStr = req.getParameter("goods_id");
		// 获取月份
		String year_monthStr = req.getParameter("year_month");

		if (goods_idStr != "" && year_monthStr != "") {

			int goods_id = Integer.parseInt(goods_idStr);
			// 得到商品的名字
			String goods_name = goodsInfoService.selectGoodsNameById(goods_id);
			if (goods_name != null) {

				// 得到开始时间
				String start_timeStr = year_monthStr + "-" + "01";
				Date start_time = new SimpleDateFormat("yyyy-MM-dd").parse(start_timeStr);

				// 得到结束时间
				String end_timeStr = year_monthStr + "-" + "31";
				Date end_time = new SimpleDateFormat("yyyy-MM-dd").parse(end_timeStr);

				// 得到盘点时间
				String takeTime = year_monthStr;
				Date take_time = new SimpleDateFormat("yyyy-MM").parse(takeTime);

				// 查询供应单价和数量
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("goodsIdTotal", goods_id);
				map.put("startTimeTotal", start_time);
				map.put("endTimeTotal", end_time);
				// 价格
				BigDecimal purchase_price = supplierGoodsService.selectPriceByIdAndTime(map);

				// 数量
				int number = supplierGoodsService.selectNumberByIdAndTime(map);
				BigDecimal nm = new BigDecimal(number);

				// 得到进货总金额
				BigDecimal purchase_total_price = purchase_price.multiply(nm);

				// 查询剩余数量
				int remaining_number = goodsInfoService.selectRemainingById(goods_id);

				// 查询售卖数量
				int sale_number = tradeFinishService.selectSaleNumByIdAndTime(map);

				// 查询售卖总金额
				BigDecimal sale_total_price = tradeFinishService.selectSalePriceByIdAndTime(map);

				// 计算盈利
				BigDecimal profit = sale_total_price.subtract(purchase_total_price);

				InventoryInfo i = new InventoryInfo();
				i.setGoodsId(goods_id);
				i.setGoodsName(goods_name);
				i.setProfit(profit);
				i.setPurchasePrice(purchase_price);
				i.setPurchaseTotalPrice(purchase_total_price);
				i.setRemainingNumber(remaining_number);
				i.setSaleNumber(sale_number);
				i.setSaleTotalPrice(sale_total_price);
				i.setTakeTime(take_time);

				int n = inventoryInfoService.insertInventoryInfo(i);

				List<InventoryInfo> inventoryInfoList = inventoryInfoService.queryAllInventoryInfo(); // 查询所有的商品信息数据
				model.addAttribute("inventoryInfoList", inventoryInfoList); // 数据返回前端
				return "view/inventoryInfo/inventoryInfoList";


			} else {

				List<InventoryInfo> inventoryInfoList = inventoryInfoService.queryAllInventoryInfo(); // 查询所有的商品信息数据
				model.addAttribute("inventoryInfoList", inventoryInfoList); // 数据返回前端
				return "view/inventoryInfo/inventoryInfoList";

			}

		} else {

			List<InventoryInfo> inventoryInfoList = inventoryInfoService.queryAllInventoryInfo(); // 查询所有的商品信息数据
			model.addAttribute("inventoryInfoList", inventoryInfoList); // 数据返回前端
			return "view/inventoryInfo/inventoryInfoList";

		}

	}
	
	//查询商品库存
		@RequestMapping(value = "searchInfo.do")
		public String searchInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
				GoodsInfo goodsInfo) throws ServletException, IOException, ParseException {

			String goods_idStr = req.getParameter("goods_id2");
			
			String year_timeStr = req.getParameter("year_time");

			if (goods_idStr != "" && year_timeStr!="") {
				int goods_id = Integer.parseInt(goods_idStr);
				
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
				map.put("goodsIdSearch", goods_id);
				map.put("startTimeSearch", start_time);
				map.put("endTimeSearch", end_time);
				
				List<InventoryInfo> inventoryInfoList = inventoryInfoService.queryInventoryInfoByYear(map); // 查询所有的商品信息数据
				model.addAttribute("inventoryInfoList", inventoryInfoList); // 数据返回前端
				return "view/inventoryInfo/inventoryInfoList";
				
			} else {
				// 重新返回主页
				List<InventoryInfo> inventoryInfoList = inventoryInfoService.queryAllInventoryInfo(); // 查询所有的商品信息数据
				model.addAttribute("inventoryInfoList", inventoryInfoList); // 数据返回前端
				return "view/inventoryInfo/inventoryInfoList";

			}
		 
		}

}
