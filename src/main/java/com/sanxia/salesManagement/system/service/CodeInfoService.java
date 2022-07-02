package com.sanxia.salesManagement.system.service;

import java.util.List;

import com.sanxia.salesManagement.system.model.CodeInfo;
import com.sanxia.salesManagement.system.model.GoodsInfo;

public interface CodeInfoService {


	List<CodeInfo> queryAllCodeInfo();

	int addCodeInfoByCodeInfo(CodeInfo c);

	int deleteCodeInfoByid(int id);



	List<CodeInfo> selectCodeInfoByGoodsName(String goods_name);

	 

	CodeInfo queryCodeById(int id);

	int updateCodeInfoByCodeInfo(CodeInfo c);

	List<CodeInfo> selectCodeInfoByGoodsId(String codeInfo_search);

}
