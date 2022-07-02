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
import com.sanxia.salesManagement.system.model.CodeInfo;
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.model.MemberInfo;
import com.sanxia.salesManagement.system.model.SaleInfo;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.service.ChangeInfoService;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
import com.sanxia.salesManagement.system.service.TradeFinishService;

@Controller
@RequestMapping(value = "/changeInfoController")
public class ChangeInfoController {
	@Autowired
	private ChangeInfoService changeInfoService;

	@Autowired
	private TradeFinishService tradeFinishService;

	// 显示条形码信息列表
	@RequestMapping(value = "changeInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
		model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

		return "view/changeInfo/changeInfoList";
	}

	// 增加信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/changeInfo/changeInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String sale_id_str = req.getParameter("sale_id");
		int sale_id = Integer.parseInt(sale_id_str);
		TradeFinish tf = tradeFinishService.selectTradeBySaleId(sale_id);
		if (tf != null) {

			int new_goods_id = tf.getGoodsId();
			// 查询商品名字
			String new_name = tf.getGoodsName();
			String change_reason = req.getParameter("change_reason");
			Date time = new Date();
			// 判断更换时间是否逾期(七天)
			// 查询订单购买时间
			Date sale_time = tf.getTime();
			Calendar ca = Calendar.getInstance();
			ca.setTime(sale_time);
			ca.add(Calendar.DATE, 7);
			Date end_time = ca.getTime();
			String apply_status;
			if (time.after(end_time)) {
				apply_status = "已逾期";
			} else {
				apply_status = "处理中";
			}

			ChangeInfo c = new ChangeInfo();
			c.setApplyStatus(apply_status);
			c.setChangeReason(change_reason);
			c.setChangeTime(time);
			c.setNewGoodsId(new_goods_id);
			c.setNewGoodsName(new_name);
			c.setSaleId(sale_id);

			int n = changeInfoService.addChangeInfoByInfo(c);

		} else {
			List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
			model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

			return "view/changeInfo/changeInfoList";
		}

		List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
		model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

		return "view/changeInfo/changeInfoList";

	}

	@RequestMapping(value = "updateStatus.do")
	public String updateStatus(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {
		String changeIdStr = req.getParameter("changeId");
		int changeId = Integer.parseInt(changeIdStr);
		// 判断转态是否逾期
		ChangeInfo ci = changeInfoService.selectChangeInfoById(changeId);
		String status = ci.getApplyStatus();

		if (status.equals("处理中")) {

			ChangeInfo c = new ChangeInfo();
			c.setChangeId(changeId);
			c.setApplyStatus("已处理");
			int i = changeInfoService.updateStatusByChange(c);

			if (i == 1) {
				// 修改交易数据
				TradeFinish tf = new TradeFinish();
				tf.setFinishType("已换货");
				tf.setSaleId(ci.getSaleId());
				int m = tradeFinishService.updateFinishType(tf);
			} else {

				List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
				model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

				return "view/changeInfo/changeInfoList";
			}
		} else {

			List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
			model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

			return "view/changeInfo/changeInfoList";
		}

		List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
		model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

		return "view/changeInfo/changeInfoList";
	}

	// 搜索指定的换货信息
	@RequestMapping(value = "searchInfo.do")
	public String searchInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			GoodsInfo goodsInfo) throws ServletException, IOException {

		String Info_search = req.getParameter("Info_search");

		if (Info_search != "") {
			// 判断传入的是编号还是名字
			char first = Info_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9') {
				int change_id = Integer.parseInt(Info_search);

				List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfoByChangeId(change_id); // 查询所有的商品信息数据
				model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

				return "view/changeInfo/changeInfoList";
			} else {
				String goods_name = "%" + Info_search + "%";
				List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfoByName(goods_name); // 查询所有的商品信息数据
				model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

				return "view/changeInfo/changeInfoList";
			}
		} else {
			// 重新返回主页
			List<ChangeInfo> changeInfoList = changeInfoService.queryAllChangeInfo(); // 查询所有的商品信息数据
			model.addAttribute("changeInfoList", changeInfoList); // 数据返回前端

			return "view/changeInfo/changeInfoList";

		}

	}

}
