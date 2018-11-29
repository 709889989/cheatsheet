package com.ming.concurrent;


import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.*;

/**
 * ThreadPool 和 ThreadLocal 一起使用时候会存在获取同一个对象的错误情况
 * 解决方案：
 *  重写线程池，重置ThreadLocal 的beforeExecute() 方法
 *
 *  参考：https://mp.weixin.qq.com/s?__biz=MzIxOTI1NTk5Nw==&mid=2650047503&idx=1&sn=5529c44789eea2ae585b38b150867bbd&chksm=8fde21ddb8a9a8cb2c485ccfbbabce336f5fed39ea6a26b1793b01085b8e46b17cc9e732bf3a&scene=21#wechat_redirect
 */
public class ThreadPoolWithThreadLocalTest {
    private static ThreadLocal<Integer> badThreadLocal = ThreadLocal.withInitial(() -> 1);

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        badThreadLocal();
        goodThreadLocal();
    }

    /**
     * 自定义线程池，使ThreadPool 和 ThreadLocal 可以共用
     * 原理：重写ThreadPool 的beforeExecute() 方法，重置threadLocals的存储map
     */
    private static void goodThreadLocal(){
        MyThreadPool myThreadPool = new MyThreadPool(2, 2, 0,
                TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        System.out.println("--------good threadPool------");
        myThreadPool.submit(new BadThread());
        myThreadPool.submit(new BadThread());
        myThreadPool.submit(new BadThread());
        myThreadPool.submit(new BadThread());
        myThreadPool.submit(new BadThread());
        myThreadPool.submit(new BadThread());
        myThreadPool.submit(new BadThread());
    }
    /**
     * 线程池与 ThreadLocal 同时使用，获取得到同一对象的错误情况
     */
    private static void badThreadLocal(){
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
        executorService.submit(new BadThread());
    }
    private static class BadThread implements Runnable{

        @Override
        public void run() {
            System.out.println("init value: " + badThreadLocal.get());
            badThreadLocal.set(2);
        }
    }

    private static class MyThreadPool extends ThreadPoolExecutor{
        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            /**
             * 获取thread 中threadLocals 的存储map,并设置为null
             */
            Field field = ReflectionUtils.findField(t.getClass(), "threadLocals");
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, t, null);
            super.beforeExecute(t, r);
        }
    }
}
