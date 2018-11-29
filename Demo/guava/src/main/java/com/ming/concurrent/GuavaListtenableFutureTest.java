package com.ming.concurrent;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaListtenableFutureTest {
    private static ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService();
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "111";
            }
        });
        task.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        }, MoreExecutors.directExecutor());
        executorService.submit(task);
        System.out.println(task.get());
    }
}
