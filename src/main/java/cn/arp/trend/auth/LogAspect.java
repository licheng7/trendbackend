package cn.arp.trend.auth;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Aspect
@Component
public class LogAspect {
	private static Logger logger;
	static {
		logger = LoggerFactory.getLogger(LogAspect.class);
	}
	@Pointcut("execution(public * cn.arp.trend.web.*.*(..))")
	public void auditLog(){};
	
	@Around("auditLog()")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
		Object result = null;

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		if (ra != null && ra instanceof ServletRequestAttributes) {
			result = processHttpRequest(pjd, ra);
		} else {
			String msg = String.format("Non http servlet request (%s) is caught by LogAspect",
					pjd.getSignature().toString());
			logger.error(msg);
			result = pjd.proceed();
		}
		return result;
	}

	private Object processHttpRequest(ProceedingJoinPoint pjd, RequestAttributes ra) throws Throwable {
		Object result = null;
		Audit audit = findAudit(pjd);
		if (audit != null) {
			String argValues = figureoutArgValues(pjd);
			Throwable error = null;
			try {
				result = pjd.proceed();
			} catch (Throwable e) {
				error = e;
				throw e;
			} finally {
				log(audit, argValues, error, (ServletRequestAttributes) ra);
			}
		}
		return result;
	}

	private String figureoutArgValues(ProceedingJoinPoint pjd) {
		Object[] args = pjd.getArgs();
		List<Object> logArgs = streamOf(args).collect(Collectors.toList());
		MethodSignature signature = (MethodSignature) pjd.getSignature();
		// 获取参数数组
		Parameter[] parameters = signature.getMethod().getParameters();
		List<Object> returnArgs = new ArrayList<>();
		for (int i = 0; i < logArgs.size(); i++) {
			// 判断参数是否添加了注解
			if (!isParamIgnored(parameters[i])) {
				Object arg = logArgs.get(i);
				if (arg instanceof HttpServletRequest) {
					returnArgs.add("<request>");
				} else if (arg instanceof HttpServletResponse) {
					returnArgs.add("<response>");
				} else if (arg instanceof MultipartFile) {
					returnArgs.add("<file>");
				} else {
					returnArgs.add(arg);
				}
			}
		}

		return JSONObject.toJSONString(returnArgs, SerializerFeature.WriteMapNullValue);
	}

	private Audit findAudit(ProceedingJoinPoint pjd) {
		MethodSignature signature = (MethodSignature) pjd.getSignature();
		return signature.getMethod().getAnnotation(Audit.class);
	}

	private boolean isParamIgnored(Parameter param) {
		return param.getAnnotation(IgnoreAudit.class) != null;
	}

	private void addIPAndPort(HttpServletRequest request, List<String> parts) {
		if (request.getHeader("ClientIP") != null) {
			parts.add(request.getHeader("ClientIP"));
		} else if (request.getHeader("X-Real-IP") != null) {
			parts.add(request.getHeader("X-Real-IP"));
		} else {
			parts.add(request.getRemoteAddr());
		}

		if (request.getHeader("X-Real-PORT") != null) {
			parts.add(request.getHeader("X-Real-PORT"));
		} else {
			parts.add("0");
		}
	}

	private void addUserInfo(List<String> parts) {
		if (CurrentSession.getSubject() != null && CurrentSession.getSubject().getUser() != null) {
			parts.add(CurrentSession.getUserId());
			parts.add(CurrentSession.getSubject().getUser().getName());
		} else {
			parts.add("Unknown");
			parts.add("Unknown");
		}
	}

	private void log(Audit audit, String argValues, Throwable e, ServletRequestAttributes sra) {
		HttpServletRequest request = sra.getRequest();
		List<String> parts = new ArrayList<String>();
		addUserInfo(parts);
		parts.add(audit.desc());
		parts.add(argValues);
		parts.add(Integer.toString(sra.getResponse().getStatus()));
		addIPAndPort(request, parts);
		if (e == null) {
			logger.info(String.join(" ", parts));
		} else {
			logger.error(String.join(" ", parts), e);
		}
	}

	private <T> Stream<T> streamOf(T[] array) {
		return (null == array || array.length == 0) ? Stream.empty() : Arrays.asList(array).stream();
	}
}
