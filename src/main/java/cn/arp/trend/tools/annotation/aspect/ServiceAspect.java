package cn.arp.trend.tools.annotation.aspect;

import cn.arp.trend.data.model.constant.ExceptionCode;
import cn.arp.trend.data.model.exception.TrendServerException;
import cn.arp.trend.data.model.response.common.DataResult;
import cn.arp.trend.tools.ResultUtils;
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

        DataResult dataResult = null;
        try {
            Object obj = joinPoint.proceed();
            if(null == obj) {
                dataResult = ResultUtils.wrapSuccess(new Object());
            } else {
                dataResult = (DataResult) obj;
            }
            log.info("访问{}结束，返回{}", methodDesc, dataResult.toString());
        } catch (TrendServerException tse) {
            dataResult = ResultUtils.wrapFailure(tse.getErrorCode(), tse.getErrorMsg());
            log.error("访问{}结束，返回{}，堆栈信息{}", methodDesc, dataResult.toString(), tse);
        } catch (Exception e) {
            dataResult = ResultUtils.wrapFailure(ExceptionCode.EXCEPTION.code, "访问[" + methodDesc
                    + "]服务异常");
            log.error("访问{}结束，返回{}，堆栈信息{}", methodDesc, dataResult.toString(), e);
        } catch (Throwable t) {
            dataResult = ResultUtils.wrapFailure(ExceptionCode.EXCEPTION.code, "访问[" + methodDesc
                    + "]服务异常");
            log.error("访问{}结束，返回{}，堆栈信息{}", methodDesc, dataResult.toString(), t);
        }

        return dataResult;
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
