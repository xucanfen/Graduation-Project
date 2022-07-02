package com.sanxia.salesManagement.system.dao;

import com.sanxia.salesManagement.system.model.SupplierInfo;

public interface SupplierInfoMapper {
    int deleteByPrimaryKey(Integer supplierId);

    int insert(SupplierInfo record);

    int insertSelective(SupplierInfo record);

    SupplierInfo selectByPrimaryKey(Integer supplierId);

    int updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);

	SupplierInfo querySupplierInfoBy_id(int supplier_id);
}