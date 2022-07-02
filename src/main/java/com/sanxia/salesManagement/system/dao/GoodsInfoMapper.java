package com.sanxia.salesManagement.system.dao;

import java.math.BigDecimal;
import java.util.List;

import com.sanxia.salesManagement.system.model.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
    
    List<GoodsInfo> queryAllGoodsInfo();

	int addGoodsByGoods(GoodsInfo g);

	int deleteGoodsByGoods_id(int goodsId);

	List<GoodsInfo> selectGoodsByGoodsName(String goods_name);

	List<GoodsInfo> selectGoodsByGoodsId(int goods_id);

	int updateGoodsInfoById(GoodsInfo g);

	GoodsInfo queryGoodsByGoods_id(int id);

	String selectGoodsNameById(int goods_id);

	int selectRemainingById(int goods_id);

	BigDecimal selectPriceByGoodsId(int goods_id);

	int updateRemainByGoods(GoodsInfo g);

	String selectGoodsTypeById(int goodsid);

	int updateNumByGoods(GoodsInfo goods);
}