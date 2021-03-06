package cn.arp.trend.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.arp.icas.rest.RestException;
import cn.arp.trend.error.RestError;
import cn.arp.trend.service.AuthenticateService;
import cn.arp.trend.service.impl.DispatchClient;
import cn.vlabs.umt.oauth.AccessToken;
import cn.vlabs.umt.oauth.UserInfo;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private DispatchClient client;
	@Autowired
	private AuthenticateService auth;
	
	@Value("${login.local}")
	private boolean localLogin;
	@Value("${login.home}")
	private String homeUri;
	@Value("${login.globalhome}")
	private String globalHome;
	@Value("${login.domainhome}")
	private String domainHome;
	
	@GetMapping("/global")
	public void globalLogin(HttpServletRequest request, HttpServletResponse response) throws RestError{
		jumpToDispatch("global", request, response);
	}
	@GetMapping("/domain")
	public void domainLogin(HttpServletRequest request, HttpServletResponse response) throws RestError{
		jumpToDispatch("domain", request, response);
	}
	
	@GetMapping("/manage")
	public void manageLogin(HttpServletRequest request, HttpServletResponse response) throws RestError{
		jumpToDispatch("manage", request, response);
	}
	private void jumpToDispatch(String loginFrom, HttpServletRequest request, HttpServletResponse response)  throws RestError{
		try {
			request.getSession().setAttribute("loginFrom", loginFrom);
			response.sendRedirect(client.buildUrl());
		} catch (IOException e) {
			throw RestError.internalError("??????????????????:" + e.getMessage());
		}
	}
	
	@GetMapping("/logout")
	public void logout(@RequestParam(value="from", required=false) String from, HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		request.getSession().setAttribute("loginFrom", from);
		response.sendRedirect(client.buildLogoutUrl());
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
			log.error("??????????????????????????????????????????????????????");
			throw RestError.internalError("???????????????????????????");
		}
	}
	
	@GetMapping("/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code)
			throws RestError, IOException {
		try {
			AccessToken token = client.retrieveTokenInfo(code);
			if (token!=null){
				auth.saveSession(request, token.getUserInfo());
				String loginFrom =(String) request.getSession().getAttribute("loginFrom");
				switch (loginFrom){
				case "global":
					response.sendRedirect(globalHome);
					break;
				case "domain":
					response.sendRedirect(domainHome);
					break;
				default:
					response.sendRedirect(homeUri);
					break;
				}
			}else{
				throw RestError.internalError(String.format("?????????????????????????????????????????????(code=%s)", code));
			}
		} catch (RestException e) {
			log.error("??????????????????????????????", e);
			throw RestError.internalError("??????????????????????????????????????????????????????");
		}
	}
	
}
