package com.uestc.myss.exeption;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import com.uestc.myss.result.CodeMsg;
import com.uestc.myss.result.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
		e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;
			return Result.Error(ex.getcMsg());
		}else if(e instanceof BindException) {
			BindException ex = (BindException)e;
			List<ObjectError> errors = ex.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.Error(CodeMsg.bind_erro.fillArgs(msg));
		}else {
			return Result.Error(CodeMsg.SERVER_ERROR);
		}
	}
}