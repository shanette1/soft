package com.uestc.myss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uestc.myss.domain.MiaoshaUser;
import com.uestc.myss.domain.OrderInfo;
import com.uestc.myss.loginVo.GoodsVo;
import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.service.GoodsService;
import com.uestc.myss.service.MiaoshaService;
import com.uestc.myss.service.OrderService;


@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
	@Autowired
	GoodsService GoodsService;
	@Autowired
	OrderService order;
	@Autowired
	MiaoshaService MiaoshaService;
	  @RequestMapping("/do_miaosha")
	    public String toLogin(Model model,MiaoshaUser user,@RequestParam("goodsId")long id) {
	        model.addAttribute("user", user);
	        if(user==null) {
	        	return "login";
	        }
	        System.out.println("1");
	     GoodsVo goods= GoodsService.getGoodsVoByGoodsId(id);
	    int stock= goods.getStockCount();
	    if(stock<=0) {
	    	model.addAttribute("eromsg", CodeMsg.stock_erro.getmsg());
	    	return "miaosha_fail";
	    }
	    else if(order.getMiaoshaOrderByUserIdGoodsId(user.getId(), id)!=null){
	    	model.addAttribute("eromsg", CodeMsg.stock_erro.getmsg());
	    	return "miaosha_fail";
		}
	        
	    else {
	    	System.out.println("2");
			OrderInfo info=MiaoshaService.miaosha(goods,user);
			model.addAttribute("orderInfo", info);
			model.addAttribute("goods", goods);
			return "order_detail";
		}    
    
	  }	      
}