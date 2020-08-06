package cn.arp.trend.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.arp.trend.error.RestError;
@RestController
public abstract class BaseController {
	@ExceptionHandler(RestError.class)
	public JSONObject handleException(RestError error, HttpServletResponse response) {
		response.setStatus(error.getCode());
		return error.toJSON();	
	}
}
