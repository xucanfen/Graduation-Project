package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.SalesmanInfoMapper;
import com.sanxia.salesManagement.system.model.SalesmanInfo;
import com.sanxia.salesManagement.system.service.SalesmanInfoService;

@Service
public class SalesmanInfoServiceImpl implements SalesmanInfoService{
	@Autowired
	private SalesmanInfoMapper salesmanInfoMapper;

	@Override
	public List<SalesmanInfo> queryAllSalesmanInfo() {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.queryAllSalesmanInfo();
	}

	@Override
	public int selectMax_id() {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.selectMax_id();
	}

	@Override
	public int addsalesmanInfoBysalesmanInfo(SalesmanInfo s) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.addsalesmanInfoBysalesmanInfo(s);
	}

	@Override
	public SalesmanInfo querySalesmanInfoBySalesmanId(int salesmanId) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.querySalesmanInfoBySalesmanId(salesmanId);
	}

	@Override
	public int updateSalesmanInfoById(SalesmanInfo s) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.updateSalesmanInfoById(s);
	}

	@Override
	public int deleteSalesmanInfoById(int salesmanId) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.deleteSalesmanInfoById(salesmanId);
	}

	@Override
	public String selectNameById(int salesman_id) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.selectNameById(salesman_id);
	}

	@Override
	public List<SalesmanInfo> querySalesmanInfoById(int salesman_id) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.querySalesmanInfoById(salesman_id);
	}

	@Override
	public List<SalesmanInfo> querySalesmanInfoByName(String salesman_name) {
		// TODO Auto-generated method stub
		return salesmanInfoMapper.querySalesmanInfoByName(salesman_name);
	}

}
