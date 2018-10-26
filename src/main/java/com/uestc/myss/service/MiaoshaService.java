package com.uestc.myss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uestc.myss.domain.MiaoshaUser;
import com.uestc.myss.domain.OrderInfo;
import com.uestc.myss.loginVo.GoodsVo;

@Service
public class MiaoshaService {
	@Autowired
	OrderService orderService;
	@Autowired
	GoodsService goodsService;
	@Transactional
	public OrderInfo miaosha(GoodsVo goods,MiaoshaUser user) {
		OrderInfo orderInfo=orderService.createOrder(user, goods);
		goodsService.reduceStock(goods);
		return orderInfo;
	}

}
