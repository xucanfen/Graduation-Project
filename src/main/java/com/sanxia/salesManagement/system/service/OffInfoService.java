package com.sanxia.salesManagement.system.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.OffInfo;

public interface OffInfoService {

	List<OffInfo> queryAllOffInfo();

	int addOffInfoByOffInfo(OffInfo off);

	OffInfo queryOffInfoById(int id);

	int updateOffInfoByOffInfo(OffInfo off);

	int deleteOffInfoById(int id);

	BigDecimal queryOffByIdAndTime(HashMap<String, Object> map);

	List<OffInfo> selectOffBySalesmansId(int salesman_id);

	List<OffInfo> selectOffBySalesmansName(String salesman_name);

}
