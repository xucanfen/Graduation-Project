package com.sanxia.salesManagement.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.sanxia.salesManagement.system.model.GoodsInfo;

public interface GoodsInfoService {

	String selectGoodsNameById(int goods_id);

	int selectRemainingById(int goods_id);
	
	List<GoodsInfo> queryAllGoodsInfo();

	int addGoodsByGoods(GoodsInfo g);

	int deleteGoodsByGoods_id(int goodsId);

	List<GoodsInfo> selectGoodsByGoodsId(int goods_id);

	List<GoodsInfo> selectGoodsByGoodsName(String goods_name);

	int updateGoodsInfoById(GoodsInfo g);

	GoodsInfo queryGoodsByGoods_id(int id);

	BigDecimal selectPriceByGoodsId(int goods_id);

	int updateRemainByGoods(GoodsInfo g);

	String selectGoodsTypeById(int goodsid);

	int updateNumByGoods(GoodsInfo goods);

}
