package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.DiscountInfo;

public interface DiscountInfoService {

	List<DiscountInfo> queryAllDiscountInfo();

	int addDiscountInfoByInfo(DiscountInfo d);

	DiscountInfo queryDiscountInfoById(int id);

	int updateDistributionInfoByDistribution(DiscountInfo d);

	int deleteDiscountInfoById(int id);

	List<DiscountInfo> selectDiscountByGoodsId(int goods_id);

	List<DiscountInfo> selectDiscountByGoodsName(String goods_name);

	DiscountInfo selectDiscountInfoByGoodsId(int goods_id);

}
