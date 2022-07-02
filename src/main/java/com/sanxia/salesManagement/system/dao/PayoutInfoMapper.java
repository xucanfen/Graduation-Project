package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.PayoutInfo;

public interface PayoutInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayoutInfo record);

    int insertSelective(PayoutInfo record);

    PayoutInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayoutInfo record);

    int updateByPrimaryKey(PayoutInfo record);

	List<PayoutInfo> queryAllPayoutInfo();

	int addPayoutInfoByInfo(PayoutInfo p);

	PayoutInfo queryPayoutInfoById(int id);

	int updatePayoutInfoByInfo(PayoutInfo p);

	int deletePayoutInfoById(int id);

	List<PayoutInfo> selectPayoutInfoById(int id);
}