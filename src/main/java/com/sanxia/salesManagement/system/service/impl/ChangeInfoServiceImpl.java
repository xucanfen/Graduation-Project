package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.ChangeInfoMapper;
import com.sanxia.salesManagement.system.model.ChangeInfo;
import com.sanxia.salesManagement.system.service.ChangeInfoService;

@Service
public class ChangeInfoServiceImpl implements ChangeInfoService {
	@Autowired
	private ChangeInfoMapper changeInfoMapper;

	@Override
	public List<ChangeInfo> queryAllChangeInfo() {
		// TODO Auto-generated method stub
		return changeInfoMapper.queryAllChangeInfo();
	}

	@Override
	public int addChangeInfoByInfo(ChangeInfo c) {
		// TODO Auto-generated method stub
		return changeInfoMapper.addChangeInfoByInfo(c);
	}

	@Override
	public ChangeInfo selectChangeInfoById(int changeId) {
		// TODO Auto-generated method stub
		return changeInfoMapper.selectChangeInfoById(changeId);
	}

	@Override
	public int updateStatusByChange(ChangeInfo c) {
		// TODO Auto-generated method stub
		return changeInfoMapper.updateStatusByChange( c);
	}

	@Override
	public List<ChangeInfo> queryAllChangeInfoByChangeId(int change_id) {
		// TODO Auto-generated method stub
		return changeInfoMapper.queryAllChangeInfoByChangeId(change_id);
	}

	@Override
	public List<ChangeInfo> queryAllChangeInfoByName(String goods_name) {
		// TODO Auto-generated method stub
		return changeInfoMapper.queryAllChangeInfoByName(goods_name);
	}

}
