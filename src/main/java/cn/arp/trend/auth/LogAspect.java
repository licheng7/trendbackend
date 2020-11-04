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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.arp.trend.entity.AuditLog;
import cn.arp.trend.service.AuditLogService;

@Aspect
@Component
public class LogAspect {
	private static Logger logger;
	static {
		logger = LoggerFactory.getLogger(LogAspect.class);
	}

	@Pointcut("execution(public * cn.arp.trend.web..*.*(..))")
	public void auditLog() {
	};

	@Autowired
	private AuditLogService service;

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
		} else {
			result = pjd.proceed();
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
				} else if (arg instanceof BindingResult){
					returnArgs.add("<BindResult>");
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

	private void addIPAndPort(HttpServletRequest request, AuditLog log) {
		if (request.getHeader("ClientIP") != null) {
			log.setRemoteIp(request.getHeader("ClientIP"));
		} else if (request.getHeader("X-Real-IP") != null) {
			log.setRemoteIp(request.getHeader("X-Real-IP"));
			log.setInternalIp(request.getHeader("X-Real-IP"));
		} else {
			log.setRemoteIp(request.getRemoteAddr());
			log.setInternalIp(request.getRemoteAddr());
		}

		if (request.getHeader("X-Real-PORT") != null) {
			log.setRemotePort(Integer.parseInt(request.getHeader("X-Real-PORT")));
		} else {
			log.setRemotePort(0);
		}
	}

	private void addUserInfo(AuditLog auditLog) {
		if (CurrentSession.getSubject() != null && CurrentSession.getSubject().getUser() != null) {
			auditLog.setUserId(CurrentSession.getUserId());
			auditLog.setUserName(CurrentSession.getSubject().getUser().getName());
		} else {
			auditLog.setUserId("Unknown");
			auditLog.setUserName("Unknown");
		}
	}

	private void log(Audit audit, String argValues, Throwable e, ServletRequestAttributes sra) {
		AuditLog auditLog = new AuditLog();
		addUserInfo(auditLog);
		auditLog.setOperationName(audit.desc());
		auditLog.setArgs(argValues);
		auditLog.setHttpStatus(sra.getResponse().getStatus());

		HttpServletRequest request = sra.getRequest();
		addIPAndPort(request, auditLog);
		auditLog.setSessionId(request.getSession().getId());
		if (e == null) {
			logger.info(auditLog.toString());
		} else {
			logger.error(auditLog.toString(), e);
		}
		service.asyncInsert(auditLog);
	}

	private <T> Stream<T> streamOf(T[] array) {
		return (null == array || array.length == 0) ? Stream.empty() : Arrays.asList(array).stream();
	}
}
