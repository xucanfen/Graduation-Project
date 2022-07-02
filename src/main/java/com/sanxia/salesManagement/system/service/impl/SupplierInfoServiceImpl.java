package com.sanxia.salesManagement.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.SupplierInfoMapper;
import com.sanxia.salesManagement.system.model.SupplierInfo;
import com.sanxia.salesManagement.system.service.SupplierInfoService;
@Service
public class SupplierInfoServiceImpl implements SupplierInfoService {
@Autowired
private SupplierInfoMapper supplierInfoMapper;

@Override
public SupplierInfo querySupplierInfoBy_id(int supplier_id) {
	// TODO Auto-generated method stub
	return supplierInfoMapper.querySupplierInfoBy_id(supplier_id);
}
}
