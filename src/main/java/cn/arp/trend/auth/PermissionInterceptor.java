package cn.arp.trend.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.arp.trend.auth.annotation.AnnotationResolver;
import cn.arp.trend.service.AuthenticateService;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private AnnotationResolver resolver = new AnnotationResolver();
	@Autowired
	private AuthenticateService authService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			final HandlerMethod handlerMethod = (HandlerMethod) handler;
			RequirePermission annotation = resolver.findPermission(handlerMethod.getMethod());
			if (annotation != null) {
				if (!authService.hasLogin(request)) {
					response.sendError(HttpStatus.SC_UNAUTHORIZED, "用户需要登录才能访问系统。");
					return false;
				}else{
					if (annotation.roles() != null && annotation.roles().length > 0) {
						if (!authService.containAtLeastOneRole(request, annotation.roles())) {
							response.sendError(HttpStatus.SC_FORBIDDEN, "用户没有对应的角色信息。");
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
