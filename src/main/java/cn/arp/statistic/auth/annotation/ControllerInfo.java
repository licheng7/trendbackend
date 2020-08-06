package cn.arp.statistic.auth.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.springframework.core.annotation.AnnotationUtils;

import cn.arp.statistic.auth.OnForbidden;
import cn.arp.statistic.auth.OnUnAuthroized;
import cn.arp.statistic.auth.RequirePermission;

public class ControllerInfo {
	private RequirePermission clazzSecuredAnnotation;
	private Method onForbidden;
	private Method onUnAuthroized;
	private Class<?> clazz;
	private HashMap<Method, RequirePermission> securedAnnotations;

	public ControllerInfo(Class<?> clazz) {
		this.clazz = clazz;
		this.clazzSecuredAnnotation = AnnotationUtils.findAnnotation(clazz, RequirePermission.class);
		this.securedAnnotations = new HashMap<Method, RequirePermission>();

		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (Modifier.PUBLIC == method.getModifiers()) {

				if (AnnotationUtils.findAnnotation(method, OnForbidden.class) != null) {
					if (this.onForbidden != null) {
						throw new IllegalStateException("Method " + method + " can't be annotated by OnForbidden. "
								+ this.onForbidden + " has already been annotated with OnForbidden");
					}
					this.onForbidden = method;
				}

				if (AnnotationUtils.findAnnotation(method, OnUnAuthroized.class) != null) {
					if (this.onUnAuthroized != null) {
						throw new IllegalStateException("Method " + method + " can't be annotated by OnUnAuthroized. "
								+ this.onForbidden + " has already been annotated with OnUnAuthroized");
					}
					this.onUnAuthroized = method;

				}

				RequirePermission methodSecured = AnnotationUtils.findAnnotation(method, RequirePermission.class);
				if (methodSecured != null) {
					securedAnnotations.put(method, methodSecured);
				}
			}
		}
	}

	public Method findOnForbidden() {
		return onForbidden;
	}

	public Method findOnUnAuthorized() {
		return onUnAuthroized;
	}

	public RequirePermission findPermission(Method method) {
		RequirePermission RequirePermission = securedAnnotations.get(method);
		if (RequirePermission == null) {
			RequirePermission = this.clazzSecuredAnnotation;
		}
		return RequirePermission;
	}

	public Class<?> getHandlerType() {
		return this.clazz;
	}

	public RequirePermission getClassSecuredAnnotation() {
		return this.clazzSecuredAnnotation;
	}
}
