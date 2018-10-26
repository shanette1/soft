package com.uestc.myss.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.uestc.myss.dao.MiaoshaUserDao;
import com.uestc.myss.domain.MiaoshaUser;
import com.uestc.myss.loginVo.LoginVo;
import com.uestc.myss.loginVo.RegisterVo;
import com.uestc.myss.redis.MessageKey;
import com.uestc.myss.redis.RedisService;
import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.result.Result;
import com.uestc.myss.util.UUIDUtil;
@Service
public class RegisterService {
	@Autowired
	RedisService redisService;
	@Autowired
	MiaoshaUserDao userDao;
	public Result<String> service(RegisterVo vo) {
		// TODO Auto-generated method stub
		String telephone=vo.getMobile();
		String password=vo.getPassword();
		String nickname=vo.getNickname();
		System.out.println(telephone);
		Date registerDate=new Date();
		MiaoshaUser newUser=new MiaoshaUser();
		newUser.setId(Long.parseLong(telephone));
		newUser.setRegisterDate(registerDate);
		newUser.setPassword(password);
		newUser.setSalt("1a2b3c");
		newUser.setNickname(nickname);
		String code=vo.getCode();
		if(code.equals(redisService.get(MessageKey.mKey, telephone, String.class))){
		//保存
		userDao.save(newUser);
		return Result.success("成功");
		}
		else {
			return Result.Error(CodeMsg.VFalse);
		}
	}
}

