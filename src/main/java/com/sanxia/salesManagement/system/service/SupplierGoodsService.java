package com.sanxia.salesManagement.system.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.SupplierGoods;

public interface SupplierGoodsService {

	BigDecimal selectPriceByIdAndTime(HashMap<String, Object> map);

	int selectNumberByIdAndTime(HashMap<String, Object> map);
	
	List<SupplierGoods> queryAllSupplierGoodsInfo();

	int addSupplierGoodsBySupplierGoods(SupplierGoods s);

	int deleteSupplierInfoById(int id);

	SupplierGoods querySupplierGoodsInfoBy_id(int id);

	int UpdateSupplierGoodsIndoBy_id(SupplierGoods s);

	List<SupplierGoods> selectSupplierGoodsBySupplierId(int supplier_id);

	List<SupplierGoods> selectSupplierGoodsByGoodsName(String goods_name);

	int selectSupplierIdById(int id);

}
