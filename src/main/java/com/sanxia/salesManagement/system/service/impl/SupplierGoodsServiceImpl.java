package com.sanxia.salesManagement.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.SupplierGoodsMapper;
import com.sanxia.salesManagement.system.model.SupplierGoods;
import com.sanxia.salesManagement.system.service.SupplierGoodsService;
@Service
public class SupplierGoodsServiceImpl implements SupplierGoodsService {
	@Autowired
	private SupplierGoodsMapper supplierGoodsMapper;

	@Override
	public BigDecimal selectPriceByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.selectPriceByIdAndTime(map);
	}

	@Override
	public int selectNumberByIdAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.selectNumberByIdAndTime(map);
	}
	
	@Override
	public List<SupplierGoods> queryAllSupplierGoodsInfo() {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.queryAllSupplierGoodsInfo();
	}
	//���ӹ�Ӧ��Ϣ��
	@Override
	public int addSupplierGoodsBySupplierGoods(SupplierGoods s) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.addSupplierGoodsBySupplierGoods(s);
	}
	//ɾ����Ӧ��
	@Override
	public int deleteSupplierInfoById(int id) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.deleteSupplierInfoById(id);
	}
	//����id��ѯ��Ӧ��
	@Override
	public SupplierGoods querySupplierGoodsInfoBy_id(int id) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.querySupplierGoodsInfoBy_id(id);
	}
	@Override
	public int UpdateSupplierGoodsIndoBy_id(SupplierGoods s) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.UpdateSupplierGoodsIndoBy_id(s);
	}
	//���ݹ�Ӧ��ID��ѯ�����Ϣ
	@Override
	public List<SupplierGoods> selectSupplierGoodsBySupplierId(int supplier_id) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.selectSupplierGoodsBySupplierId(supplier_id);
	}
	//������Ʒ����ѯ��Ӧ����Ϣ
	@Override
	public List<SupplierGoods> selectSupplierGoodsByGoodsName(String goods_name) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.selectSupplierGoodsByGoodsName(goods_name);
	}

	@Override
	public int selectSupplierIdById(int id) {
		// TODO Auto-generated method stub
		return supplierGoodsMapper.selectSupplierIdById(id);
	}

}
