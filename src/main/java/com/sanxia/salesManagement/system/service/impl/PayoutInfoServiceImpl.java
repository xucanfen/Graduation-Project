package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.PayoutInfoMapper;
import com.sanxia.salesManagement.system.model.PayoutInfo;
import com.sanxia.salesManagement.system.service.PayoutInfoService;

@Service
public class PayoutInfoServiceImpl implements PayoutInfoService{
	@Autowired
	private PayoutInfoMapper payoutInfoMapper;

	@Override
	public List<PayoutInfo> queryAllPayoutInfo() {
		// TODO Auto-generated method stub
		return payoutInfoMapper.queryAllPayoutInfo();
	}

	@Override
	public int addPayoutInfoByInfo(PayoutInfo p) {
		// TODO Auto-generated method stub
		return payoutInfoMapper.addPayoutInfoByInfo(p);
	}

	@Override
	public PayoutInfo queryPayoutInfoById(int id) {
		// TODO Auto-generated method stub
		return payoutInfoMapper.queryPayoutInfoById(id);
	}

	@Override
	public int updatePayoutInfoByInfo(PayoutInfo p) {
		// TODO Auto-generated method stub
		return payoutInfoMapper.updatePayoutInfoByInfo(p);
	}

	@Override
	public int deletePayoutInfoById(int id) {
		// TODO Auto-generated method stub
		return payoutInfoMapper.deletePayoutInfoById(id);
	}

	@Override
	public List<PayoutInfo> selectPayoutInfoById(int id) {
		// TODO Auto-generated method stub
		return payoutInfoMapper.selectPayoutInfoById(id);
	}

}
