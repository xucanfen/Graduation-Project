package com.sanxia.salesManagement.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.OverInfoMapper;
import com.sanxia.salesManagement.system.model.OverInfo;
import com.sanxia.salesManagement.system.service.OverInfoService;
@Service
public class OverInfoServiceImpl implements OverInfoService {
	@Autowired
	private OverInfoMapper overInfoMapper;

	@Override
	public List<OverInfo> queryAllOverInfo() {
		// TODO Auto-generated method stub
		return overInfoMapper.queryAllOverInfo();
	}

	@Override
	public int addOverInfoByOverInfo(OverInfo over) {
		// TODO Auto-generated method stub
		return overInfoMapper.addOverInfoByOverInfo(over);
	}

	@Override
	public OverInfo queryOverInfoById(int id) {
		// TODO Auto-generated method stub
		return overInfoMapper.queryOverInfoById(id);
	}

	@Override
	public int updateOverInfoByOverInfo(OverInfo over) {
		// TODO Auto-generated method stub
		return overInfoMapper.updateOverInfoByOverInfo(over);
	}

	@Override
	public int deleteOverInfoById(int id) {
		// TODO Auto-generated method stub
		return overInfoMapper.deleteOverInfoById(id);
	}

	@Override
	public BigDecimal queryOverByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return overInfoMapper.queryOverByIdAndTime(map);
	}

	@Override
	public List<OverInfo> selectOverBySalesmansId(int salesman_id) {
		// TODO Auto-generated method stub
		return overInfoMapper.selectOverBySalesmansId(salesman_id);
	}

	@Override
	public List<OverInfo> selectOBySalesmansName(String salesman_name) {
		// TODO Auto-generated method stub
		return overInfoMapper.selectOBySalesmansName(salesman_name);
	}
	 

}
