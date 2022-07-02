package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.MemberInfo;

public interface MemberInfoService {

	List<MemberInfo> queryAllMemberInfo();

	List<MemberInfo> selectMemberInfoByMemberId(int member_id);

	List<MemberInfo> selectMemberInfoByMemberName(String member_name);


	String queryMemberNameByMemberId(int vip_id);

	int addMemberByMember(MemberInfo m);

}
