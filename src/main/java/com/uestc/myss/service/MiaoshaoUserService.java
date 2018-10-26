package com.uestc.myss.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uestc.myss.dao.MiaoshaUserDao;
import com.uestc.myss.domain.MiaoshaUser;
import com.uestc.myss.domain.User;
import com.uestc.myss.exeption.GlobalException;
import com.uestc.myss.loginVo.LoginVo;
import com.uestc.myss.redis.MiaoshaUserKey;
import com.uestc.myss.redis.RedisService;
import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.util.MD5util;
import com.uestc.myss.util.UUIDUtil;

@Service
public class MiaoshaoUserService {
	public static final String COOKIE_NAME_TOKEN="token";
	@Autowired
    MiaoshaUserDao miaoshaUserDao;
	@Autowired
	RedisService redisservice;
	public MiaoshaUser getById(Long  id) {
		return miaoshaUserDao.getById(id);
	}
	public boolean login(HttpServletResponse response,LoginVo vo) {
		// TODO Auto-generated method stub
		if(vo==null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String mobile=vo.getMobile();
		String form=vo.getPassword();
		MiaoshaUser miaoshaUser=getById(Long.parseLong(mobile));
		if(miaoshaUser==null) {
			throw new GlobalException(CodeMsg.mobile_notexist);
		}
		String dbpass=miaoshaUser.getPassword();
		String dbsalt=miaoshaUser.getSalt();
		String calpass=MD5util.formPassDBPass(form, dbsalt);
		if(!calpass.equals(dbpass)) {
			throw new GlobalException(CodeMsg.password_erro);
		}
		//生成cookie
		String token=UUIDUtil.uuid();
		addCookie(token,response, miaoshaUser);
		return true;
		
	}
	private void addCookie(String token,HttpServletResponse response,MiaoshaUser miaoshaUser) {
		System.out.println(miaoshaUser);
		redisservice.set(MiaoshaUserKey.token, token, miaoshaUser);
		Cookie cookie=new Cookie(COOKIE_NAME_TOKEN, token);
		cookie.setMaxAge(MiaoshaUserKey.TOKEN_EXPIRE);
		cookie.setPath("/");
		response.addCookie(cookie);		
	}
	public MiaoshaUser getByToken(HttpServletResponse response,String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		
		MiaoshaUser user= redisservice.get(MiaoshaUserKey.token,token, MiaoshaUser.class);
		addCookie(token,response, user);
		return user;
	}
}
