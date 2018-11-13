package com.uestc.myss.result;

import java.time.chrono.ThaiBuddhistEra;

public class CodeMsg {
    public static final CodeMsg VFalse = new CodeMsg(500500,"验证码错误");
	public static final CodeMsg ORDER_NOT_EXIST = new CodeMsg(500100, "订单不存在");
	public static final CodeMsg SESSION_ERROR = new CodeMsg(50120, "sesion错误");
    public static CodeMsg SUCCESS=new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务端异常");
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500211,"密码不能为空");
    public static CodeMsg mobile_EMPTY=new CodeMsg(500221,"手机号不能为空");
    public static CodeMsg mobile_Eror=new CodeMsg(500213,"手机号格式错误");
    public static CodeMsg mobile_notexist=new CodeMsg(500214,"手机号bucunzai");
    public static CodeMsg password_erro=new CodeMsg(500215,"密码错误");
    public static CodeMsg bind_erro=new CodeMsg(500216,"绑定参数异常");
    public static CodeMsg stock_erro=new CodeMsg(500516,"库存异常");
    public static CodeMsg check_erro=new CodeMsg(500516,"验证码获取失败");
	private int code;
    private String mString;
    public CodeMsg(int code,String mString) {
    	 this.code=code;
    	 this.mString=mString;
     }

     
     public  CodeMsg fillArgs(Object...args) {
    	 int code=this.code;
    	 String meString=String.format(this.mString, args);
    	 return new CodeMsg(code, meString);
     }
     
     
     public int getcode() {
		// TODO Auto-generated method stub
		return code;
	}
	public String getmsg() {
		// TODO Auto-generated method stub
		return mString;
	}
}
