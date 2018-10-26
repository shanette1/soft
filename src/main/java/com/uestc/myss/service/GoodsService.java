package com.uestc.myss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uestc.myss.dao.GoodsDao;
import com.uestc.myss.domain.MiaoshaGoods;
import com.uestc.myss.loginVo.GoodsVo;

@Service
public class GoodsService {
     @Autowired    
	GoodsDao goodsdao;
     public List<GoodsVo> listGoodsVo(){
    	 return goodsdao.listGoodsVo();
     }
	public GoodsVo getGoodsVoByGoodsId(Long id) {
		// TODO Auto-generated method stub
		return goodsdao.getGoodsVoByGoodsId(id);
	}
	public void reduceStock(GoodsVo goods) {
			MiaoshaGoods g = new MiaoshaGoods();
			g.setGoodsId(goods.getId());
			goodsdao.reduceStock(g.getGoodsId());
		
	}
	public List<GoodsVo> listGoodsVoByPage(int i, int pageSize) {
		// TODO Auto-generated method stub
		return goodsdao.listGoodsVoByPage(i,pageSize);
	}
         
}
