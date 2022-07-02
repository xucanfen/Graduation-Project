package com.sanxia.salesManagement.system.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.OverInfo;

public interface OverInfoService {

	List<OverInfo> queryAllOverInfo();

	int addOverInfoByOverInfo(OverInfo over);

	OverInfo queryOverInfoById(int id);

	int updateOverInfoByOverInfo(OverInfo over);

	int deleteOverInfoById(int id);

	BigDecimal queryOverByIdAndTime(HashMap<String, Object> map);

	List<OverInfo> selectOverBySalesmansId(int salesman_id);

	List<OverInfo> selectOBySalesmansName(String salesman_name);

}
