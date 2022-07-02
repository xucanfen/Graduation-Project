package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.PayoutInfo;

public interface PayoutInfoService {

	List<PayoutInfo> queryAllPayoutInfo();

	int addPayoutInfoByInfo(PayoutInfo p);

	PayoutInfo queryPayoutInfoById(int id);

	int updatePayoutInfoByInfo(PayoutInfo p);

	int deletePayoutInfoById(int id);

	List<PayoutInfo> selectPayoutInfoById(int id);

}
