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
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.SaleInfo;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.model.User;
import com.sanxia.salesManagement.system.service.DiscountInfoService;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
import com.sanxia.salesManagement.system.service.MemberInfoService;
import com.sanxia.salesManagement.system.service.SaleInfoService;
import com.sanxia.salesManagement.system.service.TradeFinishService;

@Controller
@RequestMapping(value = "/saleInfoController")
public class SaleInfoController {
	@Autowired
	private SaleInfoService saleInfoService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Autowired
	private DiscountInfoService discountInfoService;
	@Autowired
	private TradeFinishService tradeFinishService;

//显示订单信息列表
	@RequestMapping(value = "saleInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo(); // 查询所有的商品信息数据
		model.addAttribute("saleInfoList", saleInfoList); // 数据返回前端

		// 2.跳转页面
		return "view/saleInfo/saleInfoList";
	}

	// 删除指定订单信息
	@RequestMapping(value = "deletesaleInfo.do")
	public String deleteGoodsInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo, @RequestParam(value = "saleId", required = false) String saleIdStr)
			throws ServletException, IOException {

		int sale_id = Integer.parseInt(saleIdStr);

		int i = saleInfoService.deleteSaleInfoBySale_id(sale_id);

		// 重新返回主页
		List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
		model.addAttribute("saleInfoList", saleInfoList);
		// 2.跳转页面
		return "view/saleInfo/saleInfoList";

	}

	// 增加销售订单信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/saleInfo/saleInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String goods_id_str = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_id_str);

		String vip_id_str = req.getParameter("vip_id");
		int vip_id = Integer.parseInt(vip_id_str);

		// 查询商品名和会员名字
		String vip_name = memberInfoService.queryMemberNameByMemberId(vip_id);

		String goods_name = goodsInfoService.selectGoodsNameById(goods_id);

		if (vip_name != null && goods_name != null) {
			String sale_number_str = req.getParameter("sale_number");
			int sale_number = Integer.parseInt(sale_number_str);
			// 判断销售数量是否小于库存量
			// 查询商品的库存量
			int remain = goodsInfoService.selectRemainingById(goods_id);
			if (remain >= sale_number) {
				BigDecimal sn = new BigDecimal(sale_number);
				// 查询商品的原来单价

				BigDecimal unit_price = goodsInfoService.selectPriceByGoodsId(goods_id);

				// 计算折扣价格
				// 查询商品是否在打折期限内
				Date time = new Date();// 获取当前时间作为销售时间
				// 查询打折信息的起始时间
				DiscountInfo d = discountInfoService.selectDiscountInfoByGoodsId(goods_id);

				BigDecimal discount_price;

				if (d != null) {

					Date startTime = d.getStartTime();
					Date endTime = d.getEndTime();
					BigDecimal discount = d.getDiscount();
					if (time.after(startTime) && time.before(endTime)) {
						discount_price = unit_price.multiply(discount);
					} else {

						discount_price = unit_price;
					}
				} else {
					discount_price = unit_price;
				}

				BigDecimal consume_money = discount_price.multiply(sn);

				String trade_status = "待确认";
				String deli_type = req.getParameter("deli_type");

				SaleInfo s = new SaleInfo();
				s.setGoodsId(goods_id);
				s.setVipId(vip_id);
				s.setVipName(vip_name);

				s.setGoodsName(goods_name);
				s.setSaleNumber(sale_number);

				s.setUnitPrice(unit_price);
				s.setDiscountPrice(discount_price);
				s.setConsumeMoney(consume_money);
				s.setTime(time);
				s.setTradeStatus(trade_status);
				s.setDeliType(deli_type);
				int n = saleInfoService.addSaleInfoBySaleInfo(s);

				// 重新返回主页
				List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
				model.addAttribute("saleInfoList", saleInfoList);
				// 2.跳转页面
				return "view/saleInfo/saleInfoList";

			} else {
				List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
				model.addAttribute("saleInfoList", saleInfoList);
				// 2.跳转页面
				return "view/saleInfo/saleInfoList";
			}

		} else {

			// 重新返回主页
			List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
			model.addAttribute("saleInfoList", saleInfoList);
			// 2.跳转页面
			return "view/saleInfo/saleInfoList";
		}

	}

	// 修改指定信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String saleIdStr = req.getParameter("saleId");
		int saleId = Integer.parseInt(saleIdStr);

		SaleInfo saleInfo = saleInfoService.querySaleBySale_id(saleId);

		req.setAttribute("saleInfo", saleInfo);
		return "view/saleInfo/saleInfoUpdate";

	}

	// 修改信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String saleIdStr = req.getParameter("sale_id");
		int saleId = Integer.parseInt(saleIdStr);

		String goods_id_str = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_id_str);

		String vip_id_str = req.getParameter("vip_id");
		int vip_id = Integer.parseInt(vip_id_str);

		// 查询商品名和会员名字
		String vip_name = memberInfoService.queryMemberNameByMemberId(vip_id);

		String goods_name = goodsInfoService.selectGoodsNameById(goods_id);

		if (vip_name != null && goods_name != null) {
			String sale_number_str = req.getParameter("sale_number");
			int sale_number = Integer.parseInt(sale_number_str);
			BigDecimal sn = new BigDecimal(sale_number);
			// 查询商品的原来单价

			BigDecimal unit_price = goodsInfoService.selectPriceByGoodsId(goods_id);

			// 计算折扣价格
			// 查询商品是否在打折期限内
			Date time = new Date();// 获取当前时间作为销售时间
			// 查询打折信息的起始时间
			DiscountInfo d = discountInfoService.selectDiscountInfoByGoodsId(goods_id);

			BigDecimal discount_price;

			if (d != null) {

				Date startTime = d.getStartTime();
				Date endTime = d.getEndTime();
				BigDecimal discount = d.getDiscount();
				if (time.after(startTime) && time.before(endTime)) {
					discount_price = unit_price.multiply(discount);
				} else {

					discount_price = unit_price;
				}
			} else {
				discount_price = unit_price;
			}

			BigDecimal consume_money = discount_price.multiply(sn);

			String deli_type = req.getParameter("deli_type");

			SaleInfo s = new SaleInfo();
			s.setSaleId(saleId);
			s.setGoodsId(goods_id);
			s.setVipId(vip_id);
			s.setVipName(vip_name);

			s.setGoodsName(goods_name);
			s.setSaleNumber(sale_number);

			s.setUnitPrice(unit_price);
			s.setDiscountPrice(discount_price);
			s.setConsumeMoney(consume_money);
			s.setTime(time);

			s.setDeliType(deli_type);
			int n = saleInfoService.updateSaleInfoBySaleInfo(s);

			// 重新返回主页
			List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
			model.addAttribute("saleInfoList", saleInfoList);
			// 2.跳转页面
			return "view/saleInfo/saleInfoList";

		} else {

			// 重新返回主页
			List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
			model.addAttribute("saleInfoList", saleInfoList);
			// 2.跳转页面
			return "view/saleInfo/saleInfoList";
		}

	}

	// 删除指定信息
	@RequestMapping(value = "deleteSaleInfo.do")
	public String deleteSaleInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			User user, @RequestParam(value = "saleId", required = false) String saleIdStr)
			throws ServletException, IOException {
		// System.out.println(userIdStr);

		int sale_id = Integer.parseInt(saleIdStr);

		int n = saleInfoService.deleteSaleInfoBySaleId(sale_id);

		// 同时删除交易完成表的信息
		if (n == 1) {
			int m = tradeFinishService.deleteTradeFinishBySaleId(sale_id);
		}

		// 重新返回主页
		List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
		model.addAttribute("saleInfoList", saleInfoList);
		// 2.跳转页面
		return "view/saleInfo/saleInfoList";

	}
//确认订单

	@RequestMapping(value = "updateStatus.do")
	public String updateStatus(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String saleIdStr = req.getParameter("saleId");
		int saleId = Integer.parseInt(saleIdStr);
		//查询状态，根据saleID
		String status=saleInfoService.selectStatusBySaleId(saleId);
		if(status.equals("待确认")) {
			SaleInfo s = new SaleInfo();
			s.setSaleId(saleId);
			s.setTradeStatus("已确认");
			int n = saleInfoService.updateSaleStatusBySaleInfo(s);
			if (n == 1) {
				// 添加数据到交易完成表
				SaleInfo sale = saleInfoService.querySaleBySale_id(saleId);
				TradeFinish tf = new TradeFinish();
				tf.setConsumeMoney(sale.getConsumeMoney());
				tf.setDeliType(sale.getDeliType());
				tf.setDiscountPrice(sale.getDiscountPrice());
				tf.setFinishType("已完成");
				tf.setGoodsId(sale.getGoodsId());
				tf.setGoodsName(sale.getGoodsName());
				tf.setSaleId(sale.getSaleId());
				tf.setSaleNumber(sale.getSaleNumber());
				tf.setTime(sale.getTime());
				tf.setUnitPrice(sale.getUnitPrice());
				tf.setVipId(sale.getVipId());
				tf.setVipName(sale.getVipName());

				int m = tradeFinishService.addTradeFinishBySaleInfo(tf);
				if (m == 1) {
					int remain = goodsInfoService.selectRemainingById(sale.getGoodsId());
					// 修改库存量
					int ramining = remain - sale.getSaleNumber();
					GoodsInfo g = new GoodsInfo();
					g.setGoodsId(sale.getGoodsId());
					g.setRemainingNumber(ramining);
					int a = goodsInfoService.updateRemainByGoods(g);

				}
			}

		}else {
			List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
			model.addAttribute("saleInfoList", saleInfoList);
			// 2.跳转页面
			return "view/saleInfo/saleInfoList";
		}
		
		// 重新返回主页
		List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
		model.addAttribute("saleInfoList", saleInfoList);
		// 2.跳转页面
		return "view/saleInfo/saleInfoList";
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

				List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfoBySaleId(sale_id);
				model.addAttribute("saleInfoList", saleInfoList);
				return "view/saleInfo/saleInfoList";
			} else {
				String goods_name = "%" + goods_search + "%";
				List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfoByName(goods_name);
				model.addAttribute("saleInfoList", saleInfoList);
				return "view/saleInfo/saleInfoList";
			}
		} else {
			// 重新返回主页

			List<SaleInfo> saleInfoList = saleInfoService.queryAllSaleInfo();
			model.addAttribute("saleInfoList", saleInfoList);
			// 2.跳转页面
			return "view/saleInfo/saleInfoList";

		}

	}

}