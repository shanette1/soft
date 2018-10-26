package com.uestc.myss.result;

import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportMessage;

public class Result<T> {
	private  int code;
	private  String msg;
	private T data;
	public Result(int i, String string, T data) {
		this.code=i;
		this.msg=string;
		this.data=data;
	}
	public Result(T data2) {
		this.code=0;
		this.msg="success";
		this.data=data2;
	}
	public Result(CodeMsg cm) {
		if(cm==null) {
			return;
		}
		this.code=cm.getcode();
		this.msg=cm.getmsg();
		
		// TODO Auto-generated constructor stub
	}
	public static<T> Result<T> success(T data){
		return new Result<T>( data);
	}
	public static<T> Result<T> Error(CodeMsg cm){
		return new Result<T>(cm);
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}
	
	
	
	

}
