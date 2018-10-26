package com.uestc.myss.redis;

public class MessageKey extends BasePrefix{

	public MessageKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
		// TODO Auto-generated constructor stub
	}
	public static MessageKey mKey=new MessageKey(3000, "miaosha");
			

}
