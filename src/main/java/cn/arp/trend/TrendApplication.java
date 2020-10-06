package cn.arp.trend;

import cn.arp.trend.tools.SpringBeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("cn.arp.trend.repository")
@SpringBootApplication
@EnableCaching
@EnableAsync
public class TrendApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(TrendApplication.class, args);
		SpringBeanUtil.setApplicationContext(applicationContext);
	}

}
