package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.DiscountInfo;

public interface DiscountInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiscountInfo record);

    int insertSelective(DiscountInfo record);

    DiscountInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiscountInfo record);

    int updateByPrimaryKey(DiscountInfo record);

	List<DiscountInfo> queryAllDiscountInfo();

	int addDiscountInfoByInfo(DiscountInfo d);

	DiscountInfo queryDiscountInfoById(int id);

	int updateDistributionInfoByDistribution(DiscountInfo d);

	int deleteDiscountInfoById(int id);

	List<DiscountInfo> selectDiscountByGoodsId(int goods_id);

	List<DiscountInfo> selectDiscountByGoodsName(String goods_name);

	DiscountInfo selectDiscountInfoByGoodsId(int goods_id);
}