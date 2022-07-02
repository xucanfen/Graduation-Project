package com.sanxia.salesManagement.system.service;

import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.StatisticsInfo;

public interface StatisticsInfoService {

	int insertStatisticsInfo(StatisticsInfo s);

	List<StatisticsInfo> queryStatisticsInfo(Integer id);

	List<StatisticsInfo> queryAllStatisticsInfo();

	List<StatisticsInfo> queryStatisticsInfoByYear(HashMap<String, Object> map);

}
