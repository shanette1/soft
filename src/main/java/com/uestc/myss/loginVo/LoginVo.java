package com.uestc.myss.loginVo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.uestc.myss.validatot.IsMobile;

public class LoginVo {
	public void setPassword(String password) {
		this.password = password;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@NotNull
	@Length(min=32)
	public String password;
	@NotNull
	@IsMobile
	public String mobile;
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getMobile() {
		// TODO Auto-generated method stub
		return mobile;
	}

}
