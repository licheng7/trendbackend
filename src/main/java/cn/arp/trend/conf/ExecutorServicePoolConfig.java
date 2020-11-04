package cn.arp.trend.conf;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * Created with IDEA
 * author:licheng
 * Date:2020/10/5
 * Time:下午9:04
 **/
@Configuration
public class ExecutorServicePoolConfig {

    // 核心线程数
    private final int corePoolSize = 10;
    // 最大线程数
    private final int maximumPoolSize = 50;
    // 闲置线程存活时间
    private final long keepAliveTime = 600;
    // 时间单位
    private final TimeUnit unit = TimeUnit.SECONDS;
    // 线程队列
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque(500);


    @Bean
    public ExecutorService fixedThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        return executorService;
    }

    @Bean
    public ListeningExecutorService listeningExecutorService(ExecutorService executorService) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(
                executorService);
        return service;
    }
}
