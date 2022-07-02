package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.SalesmanInfo;

public interface SalesmanInfoMapper {
    int deleteByPrimaryKey(Integer salesmanId);

    int insert(SalesmanInfo record);

    int insertSelective(SalesmanInfo record);

    SalesmanInfo selectByPrimaryKey(Integer salesmanId);

    int updateByPrimaryKeySelective(SalesmanInfo record);

    int updateByPrimaryKey(SalesmanInfo record);
    //////////////////

	List<SalesmanInfo> queryAllSalesmanInfo();

	int selectMax_id();

	int addsalesmanInfoBysalesmanInfo(SalesmanInfo s);

	SalesmanInfo querySalesmanInfoBySalesmanId(int salesmanId);

	int updateSalesmanInfoById(SalesmanInfo s);

	int deleteSalesmanInfoById(int salesmanId);

	String selectNameById(int salesman_id);

	List<SalesmanInfo> querySalesmanInfoById(int salesman_id);

	List<SalesmanInfo> querySalesmanInfoByName(String salesman_name);
}