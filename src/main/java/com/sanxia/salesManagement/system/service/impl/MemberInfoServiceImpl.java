package com.sanxia.salesManagement.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanxia.salesManagement.system.dao.MemberInfoMapper;
import com.sanxia.salesManagement.system.model.MemberInfo;
import com.sanxia.salesManagement.system.service.MemberInfoService;
@Service
public class MemberInfoServiceImpl implements MemberInfoService{
	@Autowired
	private MemberInfoMapper memberinfoMapper;

	@Override
	public List<MemberInfo> queryAllMemberInfo() {
		// TODO Auto-generated method stub
		return memberinfoMapper.queryAllMemberInfo();
	}

	@Override
	public List<MemberInfo> selectMemberInfoByMemberId(int member_id) {
		// TODO Auto-generated method stub
		return memberinfoMapper.selectMemberInfoByMemberId(member_id);
	}

	@Override
	public List<MemberInfo> selectMemberInfoByMemberName(String member_name) {
		// TODO Auto-generated method stub
		return memberinfoMapper.selectMemberInfoByMemberName(member_name);
	}

	@Override
	public String queryMemberNameByMemberId(int vip_id) {
		// TODO Auto-generated method stub
		return memberinfoMapper.queryMemberNameByMemberId(vip_id);
	}

	@Override
	public int addMemberByMember(MemberInfo m) {
		// TODO Auto-generated method stub
		return memberinfoMapper.addMemberByMember(m);
	}
}
