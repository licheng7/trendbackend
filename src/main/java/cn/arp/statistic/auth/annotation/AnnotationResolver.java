package cn.arp.statistic.auth.annotation;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cn.arp.statistic.auth.RequirePermission;

public class AnnotationResolver {
	private Map<Class<?>, ControllerInfo> map;

	public AnnotationResolver() {
		this.map = Collections.synchronizedMap(new HashMap<Class<?>, ControllerInfo>());
	}

	private ControllerInfo findHandlerCache(Class<?> beanType) {
		ControllerInfo info = map.get(beanType);
		if (info == null) {
			info = new ControllerInfo(beanType);
			map.put(beanType, info);
		}
		return info;
	}

	public RequirePermission findPermission(Method method) {
		ControllerInfo info = findHandlerCache(method.getDeclaringClass());
		return info.findPermission(method);
	}

	public Method findOnForbidden(Method method) {
		Class<?> beanType = method.getDeclaringClass();
		ControllerInfo info = findHandlerCache(beanType);
		return info.findOnForbidden();
	}

	public Method findOnUnAuthorized(Method method) {
		Class<?> beanType = method.getDeclaringClass();
		ControllerInfo info = findHandlerCache(beanType);
		return info.findOnUnAuthorized();
	}
}
