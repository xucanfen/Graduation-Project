package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.DistributionInfoMapper;
import com.sanxia.salesManagement.system.model.DistributionInfo;
import com.sanxia.salesManagement.system.service.DistributionInfoService;


@Service
public class DistributionInfoServiceImpl implements DistributionInfoService{
	
	@Autowired
	private DistributionInfoMapper distributionInfoMapper;

	@Override
	public List<DistributionInfo> queryAllDistributionInfo() {
		// TODO Auto-generated method stub
		return distributionInfoMapper.queryAllDistributionInfo();
	}

	@Override
	public int addDistributionInfoByInfo(DistributionInfo d) {
		// TODO Auto-generated method stub
		return distributionInfoMapper.addDistributionInfoByInfo(d);
	}

	@Override
	public DistributionInfo queryDistributionInfoById(int distribution_id) {
		// TODO Auto-generated method stub
		return distributionInfoMapper.queryDistributionInfoById(distribution_id);
	}

	@Override
	public int updateDistributionInfoByDistribution(DistributionInfo d) {
		// TODO Auto-generated method stub
		return distributionInfoMapper.updateDistributionInfoByDistribution(d);
	}

	@Override
	public int deleteDistributionInfoById(int distributionId) {
		// TODO Auto-generated method stub
		return distributionInfoMapper.deleteDistributionInfoById(distributionId);
	}

	@Override
	public List<DistributionInfo> selectDistributionById(int id) {
		// TODO Auto-generated method stub
		return distributionInfoMapper.selectDistributionById(id);
	}

}
