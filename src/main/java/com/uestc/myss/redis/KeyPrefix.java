package com.uestc.myss.redis;

public interface KeyPrefix {
		
	public int getExpireSeconds();
	
	public String getPrefix();
	
}
