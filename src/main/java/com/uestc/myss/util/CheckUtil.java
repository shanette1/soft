package com.uestc.myss.util;

import com.uestc.myss.exeption.GlobalException;
import com.uestc.myss.result.CodeMsg;

public class CheckUtil {

	public static void check(String code) {
	 if(code.equals("00134"))
		throw new GlobalException(CodeMsg.check_erro);
	}


	
}
