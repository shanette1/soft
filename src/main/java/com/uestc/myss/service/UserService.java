package com.uestc.myss.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.uestc.myss.dao.UserDao;
import com.uestc.myss.domain.MiaoshaUser;
import com.uestc.myss.domain.User;
@Service
public class UserService{
	@Autowired
   UserDao userDao;
	public User getById(int id) {
		return  userDao.getById(id);
	}
	public static MiaoshaUser getByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}