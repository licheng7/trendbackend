package cn.arp.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import cn.arp.statistic.tools.SpringBeanUtil;

@SpringBootApplication
@EnableCaching
public class TrendApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(TrendApplication.class, args);
		SpringBeanUtil.setApplicationContext(applicationContext);
	}

}
