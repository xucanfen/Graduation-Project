package com.sanxia.salesManagement.system.dao;

import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.StatisticsInfo;

public interface StatisticsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatisticsInfo record);

    int insertSelective(StatisticsInfo record);

    StatisticsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StatisticsInfo record);

    int updateByPrimaryKey(StatisticsInfo record);

	int insertStatisticsInfo(StatisticsInfo s);

	List<StatisticsInfo> queryStatisticsInfo(Integer id);

	List<StatisticsInfo> queryAllStatisticsInfo();

	List<StatisticsInfo> queryStatisticsInfoByYear(HashMap<String, Object> map);
}