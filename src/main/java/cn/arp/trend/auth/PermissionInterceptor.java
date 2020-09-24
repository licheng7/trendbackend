package cn.arp.trend.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.arp.trend.auth.annotation.AnnotationResolver;
import cn.arp.trend.service.AuthenticateService;
import cn.arp.trend.service.DataSetAuthService;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
	private AnnotationResolver resolver = new AnnotationResolver();
	private static final String ACL_KEY = "ACL";
	@Autowired
	private AuthenticateService authService;

	@Autowired
	private DataSetAuthService dataSetAuthService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			CurrentSession.populate(loadSubject(request));
			final HandlerMethod handlerMethod = (HandlerMethod) handler;
			RequirePermission annotation = resolver.findPermission(handlerMethod.getMethod());
			if (annotation != null) {
				if (!authService.hasLogin(request)) {
					response.sendError(HttpStatus.SC_UNAUTHORIZED, "用户需要登录才能访问系统。");
					return false;
				} else {
					if (annotation.roles() != null && annotation.roles().length > 0) {
						return checkRole(request, response, annotation);
					} else if (annotation.dataset()) {
						return checkDataSet(response, handlerMethod);
					}
				}
			}
		}
		return true;
	}

	private boolean checkRole(HttpServletRequest request, HttpServletResponse response, RequirePermission annotation)
			throws IOException {
		if (!authService.containAtLeastOneRole(request, annotation.roles())) {
			response.sendError(HttpStatus.SC_FORBIDDEN, "用户没有对应的角色信息。");
			return false;
		} else {
			return true;
		}
	}

	// 通过数据集，对权限进行控制
	private boolean checkDataSet(HttpServletResponse response, final HandlerMethod handlerMethod) throws IOException {
		Audit auditAnnotation = handlerMethod.getMethodAnnotation(Audit.class);
		if (auditAnnotation == null || auditAnnotation.value() == null) {
			response.sendError(HttpStatus.SC_INTERNAL_SERVER_ERROR, "该请求配置了检查数据集权限，但并未配置相应的数据集");
		}
		if (dataSetAuthService.canAccess(CurrentSession.getRoleIds(), auditAnnotation.value())) {
			return true;
		} else {
			response.sendError(HttpStatus.SC_FORBIDDEN, "用户没有对应的角色信息。");
			return false;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		CurrentSession.clear();
	}

	private UserSubject loadSubject(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserSubject subject = (UserSubject) session.getAttribute(ACL_KEY);
		return subject;
	}
}
