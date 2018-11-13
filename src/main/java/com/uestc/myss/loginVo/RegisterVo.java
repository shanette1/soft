package com.uestc.myss.loginVo;

public class RegisterVo {

	private String mobile;
	private String password;
	private String code;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		// TODO Auto-generated method stub
		return mobile;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}
	
	@Override
	public String toString() {
		return "RegisterVo [mobile=" + mobile + ", password=" + password + ", code=" + code + "]";
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
