package com.ming.concurrent;

import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.concurrent.*;

public class ListenableFutureTest {
    private static ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListenableFutureTask<String> listenableFutureTask = new ListenableFutureTask<String>(new Callable() {
            @Override
            public String call() throws Exception {
                return "sss";
            }
        });

        ListenableFutureCallback callback = new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("fail !");
            }

            @Override
            public void onSuccess(String s) {
                System.out.println("success");
            }
        };

        /**
         * 回调函数
         */
        listenableFutureTask.addCallback(callback);

        executorService.submit(listenableFutureTask);
        String result = (String) listenableFutureTask.get();

        System.out.println(result);

        listenableFutureTask.addCallback(callback);
    }
}
