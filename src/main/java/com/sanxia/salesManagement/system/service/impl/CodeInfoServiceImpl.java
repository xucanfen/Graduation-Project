package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.CodeInfoMapper;
import com.sanxia.salesManagement.system.model.CodeInfo;
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.service.CodeInfoService;

@Service
public class CodeInfoServiceImpl implements CodeInfoService {
	
	@Autowired
	private CodeInfoMapper codeinfoMapper;

	@Override
	public List<CodeInfo> queryAllCodeInfo() {
		// TODO Auto-generated method stub
		return codeinfoMapper.queryAllCodeInfo();
	}

	@Override
	public int addCodeInfoByCodeInfo(CodeInfo c) {
		// TODO Auto-generated method stub
		return codeinfoMapper.addCodeInfoByCodeInfo(c);
	}

	@Override
	public int deleteCodeInfoByid(int id) {
		// TODO Auto-generated method stub
		return codeinfoMapper.deleteCodeInfoByid(id);
	}

	@Override
	public List<CodeInfo> selectCodeInfoByGoodsId(String code) {
		// TODO Auto-generated method stub
		return codeinfoMapper.selectCodeInfoByGoodsId(code);
	}

	@Override
	public List<CodeInfo> selectCodeInfoByGoodsName(String goods_name) {
		// TODO Auto-generated method stub
		return codeinfoMapper.selectCodeInfoByGoodsName(goods_name);
	}

	@Override
	public CodeInfo queryCodeById(int id) {
		// TODO Auto-generated method stub
		return codeinfoMapper.queryCodeById(id);
	}

	@Override
	public int updateCodeInfoByCodeInfo(CodeInfo c) {
		// TODO Auto-generated method stub
		return codeinfoMapper.updateCodeInfoByCodeInfo(c);
	}

	 

	 

}
