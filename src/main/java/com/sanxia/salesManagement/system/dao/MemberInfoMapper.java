package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.MemberInfo;

public interface MemberInfoMapper {
    int deleteByPrimaryKey(Integer vipId);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    MemberInfo selectByPrimaryKey(Integer vipId);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);
    
    List<MemberInfo> queryAllMemberInfo();

	List<MemberInfo> selectMemberInfoByMemberId(int member_id);

	List<MemberInfo> selectMemberInfoByMemberName(String member_name);


	String queryMemberNameByMemberId(int vip_id);

	int addMemberByMember(MemberInfo m);
}