package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.ChangeInfo;

public interface ChangeInfoMapper {
    int deleteByPrimaryKey(Integer changeId);

    int insert(ChangeInfo record);

    int insertSelective(ChangeInfo record);

    ChangeInfo selectByPrimaryKey(Integer changeId);

    int updateByPrimaryKeySelective(ChangeInfo record);

    int updateByPrimaryKey(ChangeInfo record);

	List<ChangeInfo> queryAllChangeInfo();

	int addChangeInfoByInfo(ChangeInfo c);

	ChangeInfo selectChangeInfoById(int changeId);

	int updateStatusByChange(ChangeInfo c);

	 

	List<ChangeInfo> queryAllChangeInfoByName(String goods_name);

	List<ChangeInfo> queryAllChangeInfoByChangeId(int change_id);
}