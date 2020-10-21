package cn.arp.trend.tools.annotation.aspect;

import cn.arp.trend.data.model.exception.TrendServerException;
import cn.arp.trend.error.RestError;
import cn.arp.trend.tools.annotation.ServiceExecuter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:58
 **/
@Aspect
@Component
public class ServiceAspect {

    private Logger log = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("@annotation(cn.arp.trend.tools.annotation.ServiceExecuter)")
    public void serviceExecuter() {
    }

    @Around("serviceExecuter()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Method currentMethod = this.getCurrentMethod(joinPoint);
        String methodDesc = this.getServiceExecuterDescription(joinPoint, currentMethod);

        log.info("访问{}开始", methodDesc);

        Object dataResult;
        try {
            dataResult = joinPoint.proceed();
            log.info("访问{}结束", methodDesc);
            log.debug("{}接口返回结果：{}", methodDesc, dataResult.toString());
            return dataResult;
        } catch (TrendServerException tse) {
            log.error("访问{}结束，堆栈信息{}", methodDesc, tse);
            tse.printStackTrace();
            throw RestError.internalError(tse.toString());
        } catch (Exception e) {
            log.error("访问{}结束，堆栈信息{}", methodDesc, e);
            e.printStackTrace();
            throw RestError.internalError(this.buildErrMsg(e));
        } catch (Throwable t) {
            log.error("访问{}结束，堆栈信息{}", methodDesc, t);
            t.printStackTrace();
            throw RestError.internalError(this.buildErrMsg(t));
        }
    }

    private String buildErrMsg(Throwable t) {
        StringBuilder sb = new StringBuilder("异常信息:");
        StackTraceElement[] trace = t.getStackTrace();
        for (StackTraceElement s : trace) {
            sb.append(s + "\\n");
        }
        sb.append("\\n\\t");
        sb.append("------------------------------------堆栈信息------------------------------------");
        sb.append("\\n\\t");
        sb.append(t.getMessage());
        return sb.toString();
    }

    private String getServiceExecuterDescription(ProceedingJoinPoint joinPoint, Method currentMethod) {
        String description = ((ServiceExecuter)currentMethod.getAnnotation(ServiceExecuter.class)).description();
        return StringUtils.isBlank(description) ? joinPoint.getTarget().getClass().getName() + "." + currentMethod.getName() : description;
    }

    private Method getCurrentMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature msig = (MethodSignature)joinPoint.getSignature();

        try {
            return joinPoint.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
        } catch (NoSuchMethodException var4) {
            throw new RuntimeException("system error !!!");
        }
    }
}
