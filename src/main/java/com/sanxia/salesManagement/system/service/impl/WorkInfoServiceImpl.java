package com.sanxia.salesManagement.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.WorkInfoMapper;
import com.sanxia.salesManagement.system.model.WorkInfo;
import com.sanxia.salesManagement.system.service.WorkInfoService;

@Service
public class WorkInfoServiceImpl implements WorkInfoService {
	@Autowired
	private WorkInfoMapper workInfoMapper;

	@Override
	public List<WorkInfo> queryAllWorkInfo() {
		// TODO Auto-generated method stub
		return workInfoMapper.queryAllWorkInfo();
	}

	@Override
	public int addWorkInfoByWorkInfo(WorkInfo w) {
		// TODO Auto-generated method stub
		return workInfoMapper.addWorkInfoByWorkInfo(w);
	}

	@Override
	public WorkInfo queryWorkInfoById(int id) {
		// TODO Auto-generated method stub
		return workInfoMapper.queryWorkInfoById(id);
	}

	@Override
	public int updateWorkInfoByWorkInfo(WorkInfo w) {
		// TODO Auto-generated method stub
		return workInfoMapper.updateWorkInfoByWorkInfo(w);
	}

	@Override
	public int deleteWorkInfoById(int id) {
		// TODO Auto-generated method stub
		return workInfoMapper.deleteWorkInfoById(id);
	}

	@Override
	public List<WorkInfo> selectWorkInfoById(int salesmanId) {
		// TODO Auto-generated method stub
		return  workInfoMapper.selectWorkInfoById(salesmanId);
	}

	@Override
	public List<WorkInfo> selectWorkInfoByIdAndTime(WorkInfo workInfo) {
		// TODO Auto-generated method stub
		return workInfoMapper.selectWorkInfoByIdAndTime(workInfo);
	}

	
	@Override
	public int queryTotalByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return workInfoMapper.queryTotalByIdAndTime(map);
	}

	@Override
	public int queryLateByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return workInfoMapper.queryLateByIdAndTime(map);
	}

	@Override
	public int queryEarlyByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return workInfoMapper.queryEarlyByIdAndTime(map);
	}

	@Override
	public List<WorkInfo> selectWorkInfoByName(String salesman_name) {
		// TODO Auto-generated method stub
		return workInfoMapper.selectWorkInfoByName(salesman_name);
	}

	 

}
