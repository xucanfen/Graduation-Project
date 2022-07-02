package com.sanxia.salesManagement.system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sanxia.salesManagement.system.model.WorkInfo;

public interface WorkInfoService {

	List<WorkInfo> queryAllWorkInfo();

	int addWorkInfoByWorkInfo(WorkInfo w);

	WorkInfo queryWorkInfoById(int id);

	int updateWorkInfoByWorkInfo(WorkInfo w);

	int deleteWorkInfoById(int id);

	List<WorkInfo> selectWorkInfoById(int salesmanId);

	List<WorkInfo> selectWorkInfoByIdAndTime(WorkInfo workInfo);


	int queryTotalByIdAndTime(HashMap<String, Object> map);

	int queryLateByIdAndTime(HashMap<String, Object> map);

	int queryEarlyByIdAndTime(HashMap<String, Object> map);

	List<WorkInfo> selectWorkInfoByName(String salesman_name);



 

}
