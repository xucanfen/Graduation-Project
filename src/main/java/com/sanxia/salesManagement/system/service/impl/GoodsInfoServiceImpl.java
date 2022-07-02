package com.sanxia.salesManagement.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sanxia.salesManagement.system.dao.GoodsInfoMapper;
import com.sanxia.salesManagement.system.model.GoodsInfo;
import com.sanxia.salesManagement.system.service.GoodsInfoService;
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	@Override
	public String selectGoodsNameById(int goods_id) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.selectGoodsNameById(goods_id);
	}

	@Override
	public int selectRemainingById(int goods_id) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.selectRemainingById(goods_id);
	}
	
	@Override
	public List<GoodsInfo> queryAllGoodsInfo() {
		// TODO Auto-generated method stub
		return goodsInfoMapper.queryAllGoodsInfo(); //�����ڿɷŲ���
	}

	@Override
	public int addGoodsByGoods(GoodsInfo g) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.addGoodsByGoods(g);
	}
	//ɾ����Ʒ
		@Override
		public int deleteGoodsByGoods_id(int goodsId) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.deleteGoodsByGoods_id(goodsId);
		}
	//��ѯ��Ʒ����
		@Override
		public List<GoodsInfo> selectGoodsByGoodsId(int goods_id) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.selectGoodsByGoodsId(goods_id);
		}
		@Override
		public List<GoodsInfo> selectGoodsByGoodsName(String goods_name) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.selectGoodsByGoodsName(goods_name);
		}
	//������Ʒ��Ϣ
		@Override
		public int updateGoodsInfoById(GoodsInfo g) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.updateGoodsInfoById(g);
		}
		@Override
		public GoodsInfo queryGoodsByGoods_id(int id) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.queryGoodsByGoods_id(id);
		}

		@Override
		public BigDecimal selectPriceByGoodsId(int goods_id) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.selectPriceByGoodsId(goods_id);
		}

		@Override
		public int updateRemainByGoods(GoodsInfo g) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.updateRemainByGoods(g);
		}

		@Override
		public String selectGoodsTypeById(int goodsid) {
			// TODO Auto-generated method stub
			return  goodsInfoMapper.selectGoodsTypeById(goodsid);
		}

		@Override
		public int updateNumByGoods(GoodsInfo goods) {
			// TODO Auto-generated method stub
			return goodsInfoMapper.updateNumByGoods(goods);
		}
}
