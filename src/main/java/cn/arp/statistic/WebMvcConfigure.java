package cn.arp.statistic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import cn.arp.statistic.auth.PermissionInterceptor;


@Configuration
public class WebMvcConfigure extends WebMvcConfigurationSupport{
	 
    @Bean
    PermissionInterceptor permissionInterceptor() {
         return new PermissionInterceptor();
    }
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor());
    }
 
}
