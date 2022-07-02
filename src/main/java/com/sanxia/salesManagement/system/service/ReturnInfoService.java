package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.ReturnInfo;

public interface ReturnInfoService {

	List<ReturnInfo> queryAllReturnInfo();

	int addReturnInfoByInfo(ReturnInfo r);

	ReturnInfo selectReturnInfoById(int returnId);

	int updateStatusByReturn(ReturnInfo r);

	List<ReturnInfo> queryAllReturnInfoByReturnId(int return_id);

	List<ReturnInfo> queryAllReturnInfoByName(String goods_name);

}
