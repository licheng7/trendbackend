package cn.arp.statistic.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.icas.rest.RestException;
import cn.arp.statistic.auth.RequirePermission;
import cn.arp.statistic.auth.UserSubject;
import cn.arp.statistic.error.RestError;
import cn.arp.statistic.service.AuthenticateService;
import cn.arp.statistic.service.impl.DispatchClient;
import cn.vlabs.umt.oauth.AccessToken;
import cn.vlabs.umt.oauth.UserInfo;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	private Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private DispatchClient client;
	@Autowired
	private AuthenticateService auth;
	
	@Value("${login.local}")
	private boolean localLogin;
	@Value("${login.home}")
	private String homeUri;
	
	@GetMapping
	public void jumpToDispatch(HttpServletRequest request, HttpServletResponse response)  throws RestError{
		try {
			response.sendRedirect(client.buildUrl());
		} catch (IOException e) {
			throw RestError.internalError("启动登录失败:" + e.getMessage());
		}
	}
	
	@GetMapping("/mine")
	@RequirePermission
	public UserSubject mine(HttpServletRequest request){
		return auth.loadSubject(request);
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GetMapping("/echo")
	public String echo(@RequestParam("msg")String message){
		return message;
	}
	
	@PostMapping
	public void doLogin(HttpServletRequest request, @RequestBody LoginForm form) throws RestError{
		if (localLogin){
			UserInfo userInfo = new UserInfo();
			userInfo.setCstnetId(form.getUser());
			userInfo.setTrueName("Login by local");
			auth.saveSession(request, userInfo);
		}else{
			throw RestError.internalError("服务器禁止本地登录");
		}
	}
	
	@GetMapping("/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code)
			throws RestError, IOException {
		try {
			AccessToken token = client.retrieveTokenInfo(code);
			if (token!=null){
				auth.saveSession(request, token.getUserInfo());
				response.sendRedirect(homeUri);
			}else{
				throw RestError.internalError(String.format("无法登录系统，用户信息获取失败(code=%s)", code));
			}
		} catch (RestException e) {
			log.error("通过分派服务登录失败", e);
			throw RestError.internalError("通过分派服务登录失败。请联系管理员。");
		}
	}
	
}
