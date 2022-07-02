package com.sanxia.salesManagement.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.InventoryInfoMapper;
import com.sanxia.salesManagement.system.model.InventoryInfo;
import com.sanxia.salesManagement.system.service.InventoryInfoService;
@Service
public class InventoryInfoServiceImpl implements InventoryInfoService {
	@Autowired
	private InventoryInfoMapper inventoryInfoMapper;


	@Override
	public int insertInventoryInfo(InventoryInfo i) {
		// TODO Auto-generated method stub
		return inventoryInfoMapper.insertInventoryInfo(i);
	}


	@Override
	public List<InventoryInfo> queryAllInventoryInfo() {
		// TODO Auto-generated method stub
		return inventoryInfoMapper.queryAllInventoryInfo();
	}


	@Override
	public List<InventoryInfo> queryInventoryInfoByYear(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return inventoryInfoMapper.queryInventoryInfoByYear(map);
	}

}
