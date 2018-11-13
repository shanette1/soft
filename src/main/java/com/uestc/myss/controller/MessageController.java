package com.uestc.myss.controller;

import java.awt.Checkbox;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.text.AbstractDocument.Content;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.net.AprEndpoint.Sendfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uestc.myss.exeption.GlobalException;
import com.uestc.myss.redis.MessageKey;
import com.uestc.myss.redis.RedisService;
import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.result.Result;
import com.uestc.myss.service.MessageService;
import com.uestc.myss.util.CheckUtil;


@Controller
@RequestMapping("/user")
public class MessageController {
		@Autowired
	   MessageService messageSerice;
		@Autowired
		RedisService redisService;
		@RequestMapping("/getMessage")
		@ResponseBody
		public Result<String> getMessage(@RequestParam("mobile")String phone) {
			System.out.println(phone);
			String code= messageSerice.getMessage(phone);
			CheckUtil.check(code);
			System.out.println(code);
			redisService.set(MessageKey.mKey, phone, code);
			return Result.success("true");
		}
	
}
