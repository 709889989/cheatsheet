package com.ming.java8.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author mingming.xu
 * @description:
 * @date 2019/6/18 14:29
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception{
        CompletableFuture<String> completableFuture=new CompletableFuture();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟执行耗时任务
                    System.out.println("task doing...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("抛异常了");
                }catch (Exception e) {
                    //告诉completableFuture任务发生异常了
                    completableFuture.completeExceptionally(e);
                }
            }
        }).start();
        //获取任务结果，如果没有完成会一直阻塞等待
        String result=completableFuture.get();
        System.out.println("计算结果:"+result);
    }
}
