package com.sanxia.salesManagement.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.TradeFinishMapper;
import com.sanxia.salesManagement.system.model.SaleInfo;
import com.sanxia.salesManagement.system.model.TradeFinish;
import com.sanxia.salesManagement.system.service.TradeFinishService;
@Service
public class TradeFinishServiceImpl implements TradeFinishService {
	@Autowired
	private TradeFinishMapper tradeFinishMapper;

	@Override
	public int selectSaleNumByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.selectSaleNumByIdAndTime(map);
	}

	@Override
	public BigDecimal selectSalePriceByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.selectSalePriceByIdAndTime(map);
	}
	
	@Override
	public List<TradeFinish> queryAllTradeFinish() {
		// TODO Auto-generated method stub
		return tradeFinishMapper.queryAllTradeFinish();
	}

	 

	@Override
	public int addTradeFinishBySaleInfo(TradeFinish tf) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.addTradeFinishBySaleInfo(tf);
	}

	@Override
	public int deleteTradeFinishBySaleId(int sale_id) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.deleteTradeFinishBySaleId(sale_id);
	}

	@Override
	public TradeFinish selectTradeBySaleId(int sale_id) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.selectTradeBySaleId(sale_id);
	}

	@Override
	public int updateFinishType(TradeFinish tf) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.updateFinishType(tf);
	}

	@Override
	public List<TradeFinish> queryAllTradeFinishBySaleId(int sale_id) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.queryAllTradeFinishBySaleId(sale_id);
	}

	@Override
	public List<TradeFinish> queryAllTradeFinishByName(String goods_name) {
		// TODO Auto-generated method stub
		return tradeFinishMapper.queryAllTradeFinishByName(goods_name);
	}
	

}
