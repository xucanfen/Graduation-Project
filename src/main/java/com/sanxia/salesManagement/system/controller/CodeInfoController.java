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

import com.sanxia.salesManagement.system.model.CodeInfo;
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.service.CodeInfoService;
import com.sanxia.salesManagement.system.service.GoodsInfoService;

@Controller
@RequestMapping(value = "/codeInfoController")
public class CodeInfoController {
	@Autowired
	private CodeInfoService codeInfoService;
	@Autowired
	private GoodsInfoService goodsInfoService;

	// 显示条形码信息列表
	@RequestMapping(value = "codeInfolist.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {
		// 1.接受数据
		// 3.调用业务
		List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的商品信息数据
		model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

		return "view/codeInfo/codeInfoList";
	}

	// 增加条形码信息
	@RequestMapping(value = "addUI.do")
	public String addUI(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws ServletException, IOException {

		// 跳转到增加的页面
		return "view/codeInfo/codeInfoAdd";
	}

	@RequestMapping(value = "add.do")
	public String add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ServletException, IOException, ParseException {
		String goods_id_str = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_id_str);
		String goods_name = goodsInfoService.selectGoodsNameById(goods_id);

		if (goods_name != null) {
			BigDecimal price = goodsInfoService.selectPriceByGoodsId(goods_id);
			String goods_code = req.getParameter("goods_code");
			CodeInfo c = new CodeInfo();
			c.setGoodsId(goods_id);
			c.setGoodsName(goods_name);
			c.setGoodsPrice(price);
			c.setGoodsCode(goods_code);
			int n = codeInfoService.addCodeInfoByCodeInfo(c);

		} else {

			// 重新返回主页
			List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的条形码信息数据
			model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

			return "view/codeInfo/codeInfoList";

		}
		List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的条形码信息数据
		model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

		return "view/codeInfo/codeInfoList";

	}

	// 删除条形码信息
	@RequestMapping(value = "deleteCodeInfo.do")
	public String deleteCodeInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			CodeInfo codeInfo, @RequestParam(value = "id", required = false) String idStr)
			throws ServletException, IOException {

		int id = Integer.parseInt(idStr);

		int i = codeInfoService.deleteCodeInfoByid(id);

		// 重新返回主页
		List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的条形码信息数据
		model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

		return "view/codeInfo/codeInfoList";

	}

	// 搜索条形码/商品名
	@RequestMapping(value = "searchCodeInfo.do")
	public String searchCode(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model,
			CodeInfo codeInfo, @RequestParam(value = "codeInfo_search", required = false) String codeInfo_search)
			throws ServletException, IOException {
//判断传入的是编号还是名字
		if (codeInfo_search != "") {
			char first = codeInfo_search.trim().charAt(0);
			if (first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'
					|| first == '7' || first == '8' || first == '9') {

				List<CodeInfo> codeInfoList = codeInfoService.selectCodeInfoByGoodsId(codeInfo_search);

				model.addAttribute("codeInfoList", codeInfoList);
				return "view/codeInfo/codeInfoList";
			} else {
				String goods_name = "%" + codeInfo_search + "%";
				List<CodeInfo> codeInfoList = codeInfoService.selectCodeInfoByGoodsName(goods_name);

				model.addAttribute("codeInfoList", codeInfoList);
				return "view/codeInfo/codeInfoList";
			}
		} else {
			// 重新返回主页
			List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的条形码信息数据
			model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

			return "view/codeInfo/codeInfoList";

		}

	}

	// 修改指定商品信息
	// 跳转到编辑页面
	@RequestMapping(value = "updateUI.do")
	public String updateUI(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model) {
		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		CodeInfo codeInfo = codeInfoService.queryCodeById(id);

		req.setAttribute("codeInfo", codeInfo);
		return "view/codeInfo/codeInfoUpdate";

	}

	// 修改商品信息
	@RequestMapping(value = "update.do")
	public String update(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)
			throws ParseException {

		String idStr = req.getParameter("id");
		int id = Integer.parseInt(idStr);

		String goods_id_str = req.getParameter("goods_id");
		int goods_id = Integer.parseInt(goods_id_str);
		String goods_name = goodsInfoService.selectGoodsNameById(goods_id);
		String goods_code = req.getParameter("goods_code");

		if (goods_name != null) {
			BigDecimal price = goodsInfoService.selectPriceByGoodsId(goods_id);
			CodeInfo c = new CodeInfo();
			c.setGoodsId(goods_id);
			c.setGoodsName(goods_name);
			c.setGoodsPrice(price);
			c.setGoodsCode(goods_code);
			c.setId(id);
			int n = codeInfoService.updateCodeInfoByCodeInfo(c);

		} else {

			// 重新返回主页
			List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的条形码信息数据
			model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

			return "view/codeInfo/codeInfoList";

		}
		List<CodeInfo> codeInfoList = codeInfoService.queryAllCodeInfo(); // 查询所有的条形码信息数据
		model.addAttribute("codeInfoList", codeInfoList); // 数据返回前端

		return "view/codeInfo/codeInfoList";

	}
}
