package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.PayinInfoMapper;
import com.sanxia.salesManagement.system.model.PayinInfo;
import com.sanxia.salesManagement.system.service.PayinInfoService;

@Service
public class PayinInfoServiceImpl implements PayinInfoService{
	@Autowired
	private PayinInfoMapper payinInfoMapper;

	@Override
	public List<PayinInfo> queryAllPayinInfo() {
		// TODO Auto-generated method stub
		return payinInfoMapper.queryAllPayinInfo();
	}

	@Override
	public int addPayinInfoByInfo(PayinInfo p) {
		// TODO Auto-generated method stub
		return payinInfoMapper.addPayinInfoByInfo(p);
	}

	@Override
	public PayinInfo queryPayinInfoById(int id) {
		// TODO Auto-generated method stub
		return payinInfoMapper.queryPayinInfoById(id);
	}

	@Override
	public int updatePayinInfoByInfo(PayinInfo p) {
		// TODO Auto-generated method stub
		return payinInfoMapper.updatePayinInfoByInfo(p);
	}

	@Override
	public int deletePayinInfoById(int id) {
		// TODO Auto-generated method stub
		return payinInfoMapper.deletePayinInfoById(id);
	}

	@Override
	public List<PayinInfo> selectPayinInfoById(int id) {
		// TODO Auto-generated method stub
		return payinInfoMapper.selectPayinInfoById(id);
	}

	

}
