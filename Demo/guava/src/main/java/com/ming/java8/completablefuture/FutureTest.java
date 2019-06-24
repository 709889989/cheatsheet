package com.ming.java8.completablefuture;

import java.util.concurrent.*;

/**
 * @author mingming.xu
 * @description: Future 示例
 * @date 2019/6/24 10:20
 * @Version 1.0
 */

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        callable();
        futureTask();
    }

    public static void callable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = ()->{
            TimeUnit.SECONDS.sleep(3);
            return "Callable result";
        };

        Future<String> result = executorService.submit(callable);
        System.out.println(result.get());
    }

    /**
     * Future 与执行线程分离
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void futureTask() throws ExecutionException, InterruptedException {
        Callable<String> callable = ()->{
            TimeUnit.SECONDS.sleep(3);
            return "FutureTask result";
        };
        FutureTask future = new FutureTask(callable);
        Thread thread = new Thread(()->{
            future.run();
        });
        thread.start();

        System.out.println(future.get());
    }
}
