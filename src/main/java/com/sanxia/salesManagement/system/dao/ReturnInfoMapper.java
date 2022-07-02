package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.ReturnInfo;

public interface ReturnInfoMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(ReturnInfo record);

    int insertSelective(ReturnInfo record);

    ReturnInfo selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(ReturnInfo record);

    int updateByPrimaryKey(ReturnInfo record);

	List<ReturnInfo> queryAllReturnInfo();

	int addReturnInfoByInfo(ReturnInfo r);

	ReturnInfo selectReturnInfoById(int returnId);

	int updateStatusByReturn(ReturnInfo r);

	List<ReturnInfo> queryAllReturnInfoByReturnId(int return_id);

	List<ReturnInfo> queryAllReturnInfoByName(String goods_name);
}