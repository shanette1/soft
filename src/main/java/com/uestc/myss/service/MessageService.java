package com.uestc.myss.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uestc.myss.util.MD5util;
@Service
public class MessageService {
	private static final String path="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	private static final String accountSid="fcd62eb633a44138ab460b7a7b447774";
	private static final String authToken="71d9ec5bb07f4f67952e610b709a4f4a";
	public String getMessage(String phone) {
		// TODO Auto-generated method stub
			String sendCode=smsCode();
			String content=getContent(sendCode);
			String timestamp=getStamp();
			String sig=MD5util.md5(accountSid+authToken+timestamp);
			OutputStreamWriter out=null;
			BufferedReader br=null;
			StringBuilder sBuilder=new StringBuilder();
			try {
				URL url=new URL(path);
				HttpURLConnection connection=(HttpURLConnection)url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(10000);
				connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
				out=new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				String args=getArgs(content,phone,sig,timestamp);
				out.write(args);
				out.flush();
				br=new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				String temp="";
				while((temp=br.readLine())!=null) {
					sBuilder.append(temp);
				}
			}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				JSONObject jsonObject=(JSONObject)JSON.parse(sBuilder.toString());
				String code=jsonObject.getString("respCode");
				String defaut="00000";
				if(defaut.equals(code)) {
					return sendCode;
				}
				else {
					return code;
				}
			}
				
		
		private static String getContent(String sendCode) {
			// TODO Auto-generated method stub
			return "【宇鑫科技】登录验证码："+sendCode+"，如非本人操作，请忽略此短信。";
		}
		private static String getArgs(String content,String phone,String sig,String timestamp) {
			 return "accountSid="+accountSid+"&smsContent="+content+"&to="+phone+"&timestamp="+timestamp+"&sig="+sig+"&respDataType="+"JSON";
		}
		private static String getStamp() {
			// TODO Auto-generated method stub
			 return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}
		private static String smsCode() {
			// TODO Auto-generated method stub
			String random=new Random().nextInt(1000000)+"";
			return random;
		}
	

}
