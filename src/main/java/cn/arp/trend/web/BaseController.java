package cn.arp.trend.web;

import cn.arp.trend.error.RestError;
import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
@RestController
public abstract class BaseController {
	@ExceptionHandler(RestError.class)
	public JSONObject handleException(RestError error, HttpServletResponse response) {
		response.setStatus(error.getCode());
		return error.toJSON();	
	}

	public void validData(BindingResult bindingResult) throws RestError {
		if (bindingResult.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for (ObjectError error : bindingResult.getAllErrors()) {
				sb.append(error.getDefaultMessage());
				sb.append(";");
			}
			throw RestError.badArgument(sb.toString());
		}
	}
}
