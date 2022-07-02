package com.sanxia.salesManagement.system.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
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

import com.sanxia.salesManagement.system.model.ChangeInfo;
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.ReturnInfo;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
import com.sanxia.salesManagement.system.service.ReturnInfoService;
import com.sanxia.salesManagement.system.service.TradeFinishService;

@Controller
@RequestMapping(value = "/returnInfoController")
public class ReturnInfoController {
	@Autowired
	private ReturnInfoService returnInfoService;

	@Autowired
	private TradeFinishService tradeFinishService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	// 显示条形码信息列表
	@RequestMapping(value = "returnInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
		model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

		return "view/returnInfo/returnInfoList";
	}

	// 增加信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/returnInfo/returnInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String sale_id_str = req.getParameter("sale_id");
		int sale_id = Integer.parseInt(sale_id_str);
		TradeFinish tf = tradeFinishService.selectTradeBySaleId(sale_id);
		if (tf != null) {

			int goods_id = tf.getGoodsId();
			// 查询商品名字
			String goods_name = tf.getGoodsName();
			BigDecimal return_money = tf.getConsumeMoney();
			String return_reason = req.getParameter("return_reason");
			String return_way = req.getParameter("teturn_way");
			Date time = new Date();
			// 判断退换时间是否逾期(三天)
			// 查询订单购买时间
			Date sale_time = tf.getTime();
			Calendar ca = Calendar.getInstance();
			ca.setTime(sale_time);
			ca.add(Calendar.DATE, 3);
			Date end_time = ca.getTime();
			String apply_status;
			if (time.after(end_time)) {
				apply_status = "已逾期";
			} else {
				apply_status = "处理中";
			}

			ReturnInfo r = new ReturnInfo();
			r.setApplyStatus(apply_status);
			r.setReturnReason(return_reason);
			r.setReturnTime(time);
			r.setGoodsId(goods_id);
			r.setGoodsName(goods_name);
			r.setSaleId(sale_id);
			r.setReturnMoney(return_money);
			r.setTeturnWay(return_way);

			int n = returnInfoService.addReturnInfoByInfo(r);

		} else {
			List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
			model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

			return "view/returnInfo/returnInfoList";
		}

		List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
		model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

		return "view/returnInfo/returnInfoList";

	}

	@RequestMapping(value = "updateStatus.do")
	public String updateStatus(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String returnIdStr = req.getParameter("returnId");
		int returnId = Integer.parseInt(returnIdStr);
		// 判断转态是否逾期
		ReturnInfo ri = returnInfoService.selectReturnInfoById(returnId);
		String status = ri.getApplyStatus();
		int sale_id = ri.getSaleId();
		// 查询交易信息
		TradeFinish tf = tradeFinishService.selectTradeBySaleId(sale_id);
		int goods_id = tf.getGoodsId();
		int saleNumber = tf.getSaleNumber();

		if (status.equals("处理中")) {

			ReturnInfo r = new ReturnInfo();
			r.setReturnId(returnId);
			r.setApplyStatus("已处理");
			int i = returnInfoService.updateStatusByReturn(r);

			if (i == 1) {
				// 删除交易数据，因为徐灿芬功能需要统计存在的交易数据
				int n = tradeFinishService.deleteTradeFinishBySaleId(sale_id);
				// 修改库存数量
				if (n == 1) {

					// 查询库存数
					int remain_number = goodsInfoService.selectRemainingById(goods_id);
					// 加上退货的数量
					int remaining = remain_number + saleNumber;

					GoodsInfo g = new GoodsInfo();
					g.setRemainingNumber(remaining);
					g.setGoodsId(goods_id);

					int m = goodsInfoService.updateRemainByGoods(g);
				}
			} else {

				List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
				model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

				return "view/returnInfo/returnInfoList";
			}
		} else {

			List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
			model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

			return "view/returnInfo/returnInfoList";
		}

		List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
		model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

		return "view/returnInfo/returnInfoList";
	}

	// 搜索指定的退货信息
	@RequestMapping(value = "searchInfo.do")
	public String searchInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo) throws ServletException, IOException {

		String Info_search = req.getParameter("Info_search");

		if (Info_search != "") {
			// 判断传入的是编号还是名字
			char first = Info_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9') {
				int return_id = Integer.parseInt(Info_search);

				List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfoByReturnId(return_id); // 查询所有的商品信息数据
				model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

				return "view/returnInfo/returnInfoList";
			} else {
				String goods_name = "%" + Info_search + "%";
				List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfoByName(goods_name); // 查询所有的商品信息数据
				model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

				return "view/returnInfo/returnInfoList";
			}
		} else {
			// 重新返回主页
			List<ReturnInfo> returnInfoList = returnInfoService.queryAllReturnInfo(); // 查询所有的商品信息数据
			model.addAttribute("returnInfoList", returnInfoList); // 数据返回前端

			return "view/returnInfo/returnInfoList";

		}

	}

}
