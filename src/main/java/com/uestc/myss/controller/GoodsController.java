package com.uestc.myss.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.uestc.myss.domain.Goods;
import com.uestc.myss.domain.MiaoshaUser;
import com.uestc.myss.domain.PageBean;
import com.uestc.myss.domain.User;
import com.uestc.myss.loginVo.GoodsVo;
import com.uestc.myss.redis.MiaoshaUserKey;
import com.uestc.myss.redis.RedisService;
import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.result.Result;
import com.uestc.myss.service.GoodsService;
import com.uestc.myss.service.MiaoshaoUserService;
import com.uestc.myss.service.UserService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	GoodsService Service;
	@Autowired
	PageBean page;
	@RequestMapping("/to_list")
    public String hello2(Model model,MiaoshaUser user,@RequestParam("p")int p) {
		page.setCurrentPage(p);
		page.setPageSize(5);
		page.setTotalUsers(Service.listGoodsVo().size());
		model.addAttribute("user", user);
		List<GoodsVo> list=Service.listGoodsVoByPage((p-1)*page.getPageSize(),page.getPageSize());
		System.out.println(list);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "goods_list";
    }
	@RequestMapping("/to_detail/{goodsId}")
    public String hello3(Model model,MiaoshaUser user,@PathVariable("goodsId") Long id) {
		model.addAttribute("user", user);
		GoodsVo goodsVo=Service.getGoodsVoByGoodsId(id);
		model.addAttribute("goods", goodsVo);
    	long startAt = goodsVo.getStartDate().getTime();
    	long endAt = goodsVo.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("miaoshaStatus", miaoshaStatus);
    	model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";		
    }
}
