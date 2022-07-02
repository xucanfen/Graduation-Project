package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.ReturnInfoMapper;
import com.sanxia.salesManagement.system.model.ReturnInfo;
import com.sanxia.salesManagement.system.service.ReturnInfoService;
@Service
public class ReturnInfoServiceImpl implements ReturnInfoService {
	@Autowired
	private ReturnInfoMapper returnInfoMapper;

	@Override
	public List<ReturnInfo> queryAllReturnInfo() {
		// TODO Auto-generated method stub
		return returnInfoMapper.queryAllReturnInfo();
	}

	@Override
	public int addReturnInfoByInfo(ReturnInfo r) {
		// TODO Auto-generated method stub
		return returnInfoMapper.addReturnInfoByInfo(r);
	}

	@Override
	public ReturnInfo selectReturnInfoById(int returnId) {
		// TODO Auto-generated method stub
		return returnInfoMapper.selectReturnInfoById(returnId);
	}

	@Override
	public int updateStatusByReturn(ReturnInfo r) {
		// TODO Auto-generated method stub
		return returnInfoMapper.updateStatusByReturn(r);
	}

	@Override
	public List<ReturnInfo> queryAllReturnInfoByReturnId(int return_id) {
		// TODO Auto-generated method stub
		return returnInfoMapper.queryAllReturnInfoByReturnId(return_id);
	}

	@Override
	public List<ReturnInfo> queryAllReturnInfoByName(String goods_name) {
		// TODO Auto-generated method stub
		return returnInfoMapper.queryAllReturnInfoByName(goods_name);
	}

}
