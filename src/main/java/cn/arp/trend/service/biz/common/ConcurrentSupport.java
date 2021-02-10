package cn.arp.trend.service.biz.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * TODO 这个类是对并发查询的支持，后面可以借助这个类处理多个sql并发查询的情况，会提升效率，对小规模并发查询接口查询时间会缩减
 *
 * TODO 使用姿势：
 *      0、service类继承ConcurrentSupport
 *      1、调用initConcurrentSupportContext()初始化并发处理上下文；
 *      2、调用context.registerTask(Task task)将待执行的任务列表注册到上下文
 *      3、调用context.taskExecute()提交并执行任务
 *      4、调用context.await()阻塞主线程，直到所有task执行结束
 *      5、调用context.getResult()获取异步执行结果，用于主线程后续处理
 *
 * Created with IDEA
 * author:licheng
 * Date:2020/10/14
 * Time:上午10:32
 **/
public class ConcurrentSupport extends AbstructServiceHelper {

    private Logger log = LoggerFactory.getLogger(ConcurrentSupport.class);

    @Resource
    private ExecutorService fixedThreadPool;

    @Resource
    private ListeningExecutorService listeningExecutorService;

    public ConcurrentSupportContext initConcurrentSupportContext() {
        return new ConcurrentSupportContext();
    }



    public <T> void doAddCallback(ListenableFuture<T> feture, Consumer<T> consumer,
                                  ConcurrentSupportContext context) {
        Futures.addCallback(feture, new FutureCallback<T>() {
            @Override
            public void onSuccess(@Nullable T t) {
                doSuccess((p) -> {
                    consumer.accept(p);
                }, t, context);
            }
            @Override
            public void onFailure(Throwable throwable) {
                doFailure(throwable, context);
            }
        }, fixedThreadPool);
    }

    public void doFailure(Throwable tr, ConcurrentSupportContext context) {
        // 尝试取消本次任务其他线程执行，试图节省算力
        try {
            for(ListenableFuture feture : context.getFetureList()) {
                if(!feture.isCancelled() && !feture.isDone()) {
                    // TODO 这么写不能保证一定取消掉其他线程执行，严谨一点需要用信号量告知，这点并发问题不大
                    feture.cancel(true);
                }
            }
        } finally {
            context.setSuccess(false);
            context.setError(tr);
            context.getLatch().countDown();
            throw new RuntimeException("执行analyzeServiceImpl.query()异常", tr);
        }
    }

    public <T> void doSuccess(Consumer<T> consumer, T t, ConcurrentSupportContext context) {
        try {
            consumer.accept(t);
            context.setSuccess(true);
        } catch (Throwable tr) {
            context.setSuccess(false);
            context.setError(tr);
        } finally {
            context.getLatch().countDown();
        }
    }


    public class Task<T>{
        private String taskName;

        private Callable<T> executable;

        private Consumer<T> callback;

        private ListenableFuture<List> lf;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        public Task(String taskName, Callable<T> executable) {
            this.taskName = taskName;
            this.executable = executable;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public Callable<T> getExecutable() {
            return executable;
        }

        public void setExecutable(Callable<T> executable) {
            this.executable = executable;
        }

        public Consumer<T> getCallback() {
            return callback;
        }

        public void setCallback(Consumer<T> callback) {
            this.callback = callback;
        }

        public ListenableFuture<List> getLf() {
            return lf;
        }

        public void setLf(ListenableFuture<List> lf) {
            this.lf = lf;
        }

    }

    public class ConcurrentSupportContext{
        private Map<String, Task> taskMap;

        private List<ListenableFuture> fetureList;

        private CountDownLatch latch;

        private Map<String, List> result;

        private boolean success;

        private Throwable error;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public Throwable getError() {
            return error;
        }

        public void setError(Throwable error) {
            this.error = error;
        }

        public Map<String, Task> getTaskMap() {
            return taskMap;
        }

        public void setTaskMap(Map<String, Task> taskMap) {
            this.taskMap = taskMap;
        }

        public List<ListenableFuture> getFetureList() {
            return fetureList;
        }

        public void setFetureList(List<ListenableFuture> fetureList) {
            this.fetureList = fetureList;
        }

        public CountDownLatch getLatch() {
            return latch;
        }

        public void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        public Map<String, List> getResult() {
            return result;
        }

        public void setResult(Map<String, List> result) {
            this.result = result;
        }

        private ConcurrentSupportContext() {
            taskMap = Maps.newHashMap();
            fetureList = Lists.newArrayList();
            result = Maps.newHashMap();
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
                //doAddCallback(task.getLf(), task.getCallback(), fetureList, latch);
                doAddCallback(task.getLf(), list -> result.put(task.getTaskName(), (List) list),this);
            });
        }

        public void await() {
            try {
                latch.await(15, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("执行ConcurrentSupport.await()异常", e);
            }
        }
    }
}


