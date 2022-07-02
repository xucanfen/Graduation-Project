package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.SaleInfo;

public interface SaleInfoService {

	List<SaleInfo> queryAllSaleInfo();

	int deleteSaleInfoBySale_id(int sale_id);

	int addSaleInfoBySaleInfo(SaleInfo s);

	SaleInfo querySaleBySale_id(int saleId);

	int updateSaleInfoBySaleInfo(SaleInfo s);

	int deleteSaleInfoBySaleId(int sale_id);

	int updateSaleStatusBySaleInfo(SaleInfo s);

	List<SaleInfo> queryAllSaleInfoByDeli(String deli_search);

	List<SaleInfo> queryAllSaleInfoByStatus(String status_search);

	List<SaleInfo> queryAllSaleInfoBySaleId(int sale_id);

	List<SaleInfo> queryAllSaleInfoByName(String goods_name);

	String selectStatusBySaleId(int saleId);

	

}
