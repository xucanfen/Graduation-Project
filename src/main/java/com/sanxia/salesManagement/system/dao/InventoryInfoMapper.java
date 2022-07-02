package com.sanxia.salesManagement.system.dao;

import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.InventoryInfo;

public interface InventoryInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryInfo record);

    int insertSelective(InventoryInfo record);

    InventoryInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryInfo record);

    int updateByPrimaryKey(InventoryInfo record);

	int insertInventoryInfo(InventoryInfo i);

	List<InventoryInfo> queryAllInventoryInfo();

	List<InventoryInfo> queryInventoryInfoByYear(HashMap<String, Object> map);
}