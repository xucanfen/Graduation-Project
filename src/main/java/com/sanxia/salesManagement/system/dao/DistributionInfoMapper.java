package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.DistributionInfo;

public interface DistributionInfoMapper {
    int deleteByPrimaryKey(Integer distributionId);

    int insert(DistributionInfo record);

    int insertSelective(DistributionInfo record);

    DistributionInfo selectByPrimaryKey(Integer distributionId);

    int updateByPrimaryKeySelective(DistributionInfo record);

    int updateByPrimaryKey(DistributionInfo record);

	List<DistributionInfo> queryAllDistributionInfo();

	int addDistributionInfoByInfo(DistributionInfo d);

	DistributionInfo queryDistributionInfoById(int distribution_id);

	int updateDistributionInfoByDistribution(DistributionInfo d);

	int deleteDistributionInfoById(int distributionId);

	List<DistributionInfo> selectDistributionById(int id);
}