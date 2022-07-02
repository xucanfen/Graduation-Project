package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.PayinInfo;

public interface PayinInfoService {

	List<PayinInfo> queryAllPayinInfo();

	int addPayinInfoByInfo(PayinInfo p);

	PayinInfo queryPayinInfoById(int id);
	
	int updatePayinInfoByInfo(PayinInfo p);

	int deletePayinInfoById(int id);

	List<PayinInfo> selectPayinInfoById(int id);

 


}
