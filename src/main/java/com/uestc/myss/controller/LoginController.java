package com.uestc.myss.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uestc.myss.domain.User;
import com.uestc.myss.loginVo.LoginVo;
import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.result.Result;
import com.uestc.myss.service.MiaoshaoUserService;
import com.uestc.myss.service.UserService;
import com.uestc.myss.util.ValidatorUtil;

import ch.qos.logback.classic.spi.LoggingEventVO;
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	MiaoshaoUserService miaoshaoUserService;
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/to_register")
    public String toRegister() {
        return "register";
    }

	@RequestMapping("/do_login")
	@ResponseBody
    public Result<Object> DoLogin(@Valid LoginVo vo,HttpServletResponse response) {
   	   miaoshaoUserService.login(response,vo);
   	   return Result.success(true);
}
}
