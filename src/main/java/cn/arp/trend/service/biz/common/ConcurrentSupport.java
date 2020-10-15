package cn.arp.trend.service.biz.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * TODO 这个类是对并发查询的支持，后面可以借助这个类处理多个sql并发查询的情况，会提升效率
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/10/14
 * Time:上午10:32
 **/
public class ConcurrentSupport extends ApplicationObjectSupport {

    private Logger log = LoggerFactory.getLogger(ConcurrentSupport.class);

    private ExecutorService fixedThreadPool;

    private ListeningExecutorService listeningExecutorService;

    private static ConcurrentSupport instance = new ConcurrentSupport();

    private ConcurrentSupport(){
        fixedThreadPool = getApplicationContext().getBean("fixedThreadPool", ExecutorService
                .class);
        listeningExecutorService = getApplicationContext().getBean("listeningExecutorService",
                ListeningExecutorService.class);
    }

    public ConcurrentSupport getInstance() {
        return instance;
    }

    public ConcurrentSupportContext initConcurrentSupportContext() {
        return new ConcurrentSupportContext();
    }



    public <T> void doAddCallback(ListenableFuture<T> feture, Consumer<T> consumer,
                                   List<ListenableFuture> fetureList, CountDownLatch latch) {
        Futures.addCallback(feture, new FutureCallback<T>() {
            @Override
            public void onSuccess(@Nullable T t) {
                doSuccess((p) -> {
                    consumer.accept(p);
                }, t, latch);
            }
            @Override
            public void onFailure(Throwable throwable) {
                doFailure(fetureList, throwable);
            }
        }, fixedThreadPool);
    }

    public void doFailure(List<ListenableFuture> fetureList, Throwable throwable) {
        // 尝试取消本次任务其他线程执行，试图节省算力
        for(ListenableFuture feture : fetureList) {
            if(!feture.isCancelled() && !feture.isDone()) {
                // TODO 这么写不能保证一定取消掉其他线程执行，严谨一点需要用信号量告知，这点并发问题不大
                feture.cancel(true);
            }
        }
        throw new RuntimeException("执行analyzeServiceImpl.query()异常", throwable);
    }

    public <T> void doSuccess(Consumer<T> consumer, T t, CountDownLatch latch) {
        consumer.accept(t);
        latch.countDown();
    }


    @Data
    public class Task<T>{
        private String taskName;
        private Callable<T> executable;
        private Consumer<T> callback;
        private ListenableFuture<List> lf;
    }

    @Data
    public class ConcurrentSupportContext{
        private Map<String, Task> taskMap;
        private List<ListenableFuture> fetureList;
        private CountDownLatch latch;

        private ConcurrentSupportContext() {
            taskMap = Maps.newHashMap();
            fetureList = Lists.newArrayList();
        }

        public void registerTask(Task task) {
            if(taskMap.containsKey(task.getTaskName())) {
                throw new RuntimeException("taskName["+task.getTaskName()+"]重复");
            } else {
                taskMap.put(task.getTaskName(), task);
            }
        }

        public void taskExecute() {
            taskMap.entrySet().stream().forEach(map -> {
                log.info("-并发任务{}开始执行-", map.getKey());
                Task task = map.getValue();
                ListenableFuture<List> lf = listeningExecutorService.submit(task.getExecutable());
                task.setLf(lf);
                fetureList.add(lf);
            });
            latch = new CountDownLatch(fetureList.size());
            taskMap.entrySet().stream().forEach(map -> {
                log.info("-为并发任务{}注册回调-", map.getKey());
                Task task = map.getValue();
                doAddCallback(task.getLf(), task.getCallback(), fetureList, latch);
            });
        }

        public void await() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException("执行analyzeServiceImpl.query()异常", e);
            }
        }
    }
}


