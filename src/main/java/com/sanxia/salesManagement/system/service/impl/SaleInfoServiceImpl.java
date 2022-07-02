package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.SaleInfoMapper;
import com.sanxia.salesManagement.system.model.SaleInfo;
import com.sanxia.salesManagement.system.service.SaleInfoService;

@Service
public class SaleInfoServiceImpl implements SaleInfoService{
	@Autowired
	private SaleInfoMapper saleInfoMapper;

	@Override
	public List<SaleInfo> queryAllSaleInfo() {
		// TODO Auto-generated method stub
		return saleInfoMapper.queryAllSaleInfo();
	}
	//删除销售信息
	@Override
	public int deleteSaleInfoBySale_id(int sale_id) {
		// TODO Auto-generated method stub
		return saleInfoMapper.deleteSaleInfoBySale_id(sale_id);
	}
	@Override
	public int addSaleInfoBySaleInfo(SaleInfo s) {
		// TODO Auto-generated method stub
		return saleInfoMapper.addSaleInfoBySaleInfo(s);
	}
	@Override
	public SaleInfo querySaleBySale_id(int saleId) {
		// TODO Auto-generated method stub
		return saleInfoMapper.querySaleBySale_id(saleId);
	}
	@Override
	public int updateSaleInfoBySaleInfo(SaleInfo s) {
		// TODO Auto-generated method stub
		return saleInfoMapper.updateSaleInfoBySaleInfo(s);
	}
	@Override
	public int deleteSaleInfoBySaleId(int sale_id) {
		// TODO Auto-generated method stub
		return saleInfoMapper.deleteSaleInfoBySaleId(sale_id);
	}
	@Override
	public int updateSaleStatusBySaleInfo(SaleInfo s) {
		// TODO Auto-generated method stub
		return saleInfoMapper.updateSaleStatusBySaleInfo(s);
	}
	@Override
	public List<SaleInfo> queryAllSaleInfoByDeli(String deli_search) {
		// TODO Auto-generated method stub
		return saleInfoMapper.queryAllSaleInfoByDeli(deli_search);
	}
	@Override
	public List<SaleInfo> queryAllSaleInfoByStatus(String status_search) {
		// TODO Auto-generated method stub
		return saleInfoMapper.queryAllSaleInfoByStatus(status_search);
	}
	@Override
	public List<SaleInfo> queryAllSaleInfoBySaleId(int sale_id) {
		// TODO Auto-generated method stub
		return saleInfoMapper.queryAllSaleInfoBySaleId(sale_id);
	}
	@Override
	public List<SaleInfo> queryAllSaleInfoByName(String goods_name) {
		// TODO Auto-generated method stub
		return saleInfoMapper.queryAllSaleInfoByName(goods_name);
	}
	@Override
	public String selectStatusBySaleId(int saleId) {
		// TODO Auto-generated method stub
		return saleInfoMapper.selectStatusBySaleId(saleId);
	}

}
