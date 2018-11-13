package com.uestc.myss.controller;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uestc.myss.loginVo.LoginVo;
import com.uestc.myss.loginVo.RegisterVo;
import com.uestc.myss.result.Result;
import com.uestc.myss.service.RegisterService;

@Controller
@RequestMapping("/user")
public class RegisterController {
	@Autowired
	RegisterService register;
	@RequestMapping("/doregister")
	public Result<String> doRegister(@Valid RegisterVo vo){
		System.out.println(vo);
		return register.service(vo);
	}
}
