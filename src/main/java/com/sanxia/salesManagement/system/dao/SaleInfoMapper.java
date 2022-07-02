package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.SaleInfo;

public interface SaleInfoMapper {
    int deleteByPrimaryKey(Integer saleId);

    int insert(SaleInfo record);

    int insertSelective(SaleInfo record);

    SaleInfo selectByPrimaryKey(Integer saleId);

    int updateByPrimaryKeySelective(SaleInfo record);

    int updateByPrimaryKey(SaleInfo record);
    
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