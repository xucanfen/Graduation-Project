package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.DistributionInfo;

public interface DistributionInfoService {

	List<DistributionInfo> queryAllDistributionInfo();

	int addDistributionInfoByInfo(DistributionInfo d);

	DistributionInfo queryDistributionInfoById(int distribution_id);

	int updateDistributionInfoByDistribution(DistributionInfo d);

	int deleteDistributionInfoById(int distributionId);

	List<DistributionInfo> selectDistributionById(int id);

}
