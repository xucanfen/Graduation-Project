package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.PayinInfo;

public interface PayinInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayinInfo record);

    int insertSelective(PayinInfo record);

    PayinInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayinInfo record);

    int updateByPrimaryKey(PayinInfo record);

	List<PayinInfo> queryAllPayinInfo();

	int addPayinInfoByInfo(PayinInfo p);

	PayinInfo queryPayinInfoById(int id);

	int updatePayinInfoByInfo(PayinInfo p);

	int deletePayinInfoById(int id);

	List<PayinInfo> selectPayinInfoById(int id);
}