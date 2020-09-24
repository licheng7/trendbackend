package cn.arp.trend.auth;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface RequirePermission {
	// 需要的用户角色
	String[] roles() default {};
	// 使用访问数据集进行控制
	boolean dataset() default false;
}
