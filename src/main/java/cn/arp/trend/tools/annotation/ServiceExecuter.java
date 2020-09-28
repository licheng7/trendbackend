package cn.arp.trend.tools.annotation;

import java.lang.annotation.*;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/9/27
 * Time:下午11:57
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceExecuter {

    String description() default "";
}
