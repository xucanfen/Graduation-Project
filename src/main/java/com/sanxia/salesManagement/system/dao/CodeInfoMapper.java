package com.sanxia.salesManagement.system.dao;

import java.util.List;

import com.sanxia.salesManagement.system.model.CodeInfo;

public interface CodeInfoMapper {
	  int deleteByPrimaryKey(Integer id);

	  int insert(CodeInfo record);

	  int insertSelective(CodeInfo record);

	  CodeInfo selectByPrimaryKey(Integer id);

	  int updateByPrimaryKeySelective(CodeInfo record);

	  int updateByPrimaryKey(CodeInfo record);

      List<CodeInfo> queryAllCodeInfo();

	  int addCodeInfoByCodeInfo(CodeInfo c);

	  int deleteCodeInfoByid(int id);
 

	  List<CodeInfo> selectCodeInfoByGoodsName(String goods_name);

	  CodeInfo queryCodeById(int id);

	  int updateCodeInfoByCodeInfo(CodeInfo c);

	  List<CodeInfo> selectCodeInfoByGoodsId(String code);
}