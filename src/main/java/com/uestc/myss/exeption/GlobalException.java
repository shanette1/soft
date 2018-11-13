package com.uestc.myss.exeption;

import com.uestc.myss.result.CodeMsg;

public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CodeMsg cMsg;
	public GlobalException(CodeMsg cm) {
		super(cm.toString());
		this.cMsg=cm;
	}
	public CodeMsg getcMsg() {
		return cMsg;
	}
     
}
