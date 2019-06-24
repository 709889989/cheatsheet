package com.ming.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author mingming.xu
 * @description: JDK8 CompletableFuture 示例
 * @date 2019/6/24 10:10
 * @Version 1.0
 */

public class CompletableFutureTest {
    public static void main(String[] args) {
//        1. CompletableFuture中有很多名称带有run, accept或apply的方法，它们一般与任务的类型相对应，
//          run与Runnable对应，accept与Consumer对应，apply与Function对应
//        2. 名称不带Async的方法由当前线程或前一个阶段的线程执行，
//          带Async但没有指定Executor的方法由默认Excecutor执行(ForkJoinPool.commonPool()或ThreadPerTaskExecutor)
        supplier();
        runable();
        extraThread();
        //回调函数
        whenComplete();
        handle();
        exceptionally();
        //任务流
        thenRun();
        thenApplyThenAccept();
        thenCompose();
        //2阶段都完成再执行下一个阶段任务
        bothFinished();
        //2阶段至少一个完成
        atLeastOneFinished();
        //多阶段任务
        multiStage();
    }

    public static void supplier(){
        Supplier<Integer> supplier = ()->{
            //任务
            System.out.println("Supplier ....");
            return 1;
        };
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(supplier);
        System.out.println(completableFuture.join());
    }

    public static void runable(){
        Runnable runnable = ()->{
            //任务
            System.out.println("Runnable ....");
        };
        CompletableFuture completableFuture = CompletableFuture.runAsync(runnable);
        System.out.println(completableFuture.join());
    }

    /**
     * CompletableFuture 与执行线程分离
     */
    public static void extraThread(){
        Supplier<Integer> supplier = ()->{
            //任务
            System.out.println("Supplier ....");
            return 1;
        };

        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread() {
            @Override
            public void run() {
                try {
                    future.complete(supplier.get());
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }
        }.start();

        System.out.println(future.join());
    }

    /**
     * 回调函数
     * whenComplete 只注册回调函数，不改变结果。CompletableFuture原样返回
     * handle 返回的CompletableFuture，是被handle函数修改后的
     * exceptionally 只处理异常结果，返回的CompletableFuture，是被exceptionally函数修改后的
     */
    public static void whenComplete(){
        Supplier<Integer> externalTask = ()->{
            //任务
            System.out.println("Supplier ....");
            return 1;
        };
        int rt = CompletableFuture.supplyAsync(externalTask).whenComplete((result, ex) -> {
            if (result != null) {
                System.out.println(result);
            }
            if (ex != null) {
                ex.printStackTrace();
            }
        }).join();
        System.out.println("result =" + rt);
    }
    public static void handle(){
        String ret =
                CompletableFuture.supplyAsync(()->{
                    throw new RuntimeException("test");
                }).handle((result, ex)->{
                    return "hello";
                }).join();
        System.out.println("handle:" + ret);
    }
    public static void exceptionally(){
        String ret = (String) CompletableFuture.supplyAsync(()->{
                    throw new RuntimeException("test");
                }).exceptionally((ex)->{
                    return "hello";
                }).join();
        System.out.println("exceptionally:" + ret);
    }

    /**
     * thenRun 任务流
     * thenRunAsync 异步
     * 只有前一个阶段没有异常结束，下一个阶段的任务才会执行
     * 如果前一个阶段发生了异常，所有后续阶段都不会运行
     */
    public static void thenRun(){
        Runnable taskA = () -> System.out.println("task A");
        Runnable taskB = () -> System.out.println("task B");
        Runnable taskC = () -> System.out.println("task C");

        CompletableFuture.runAsync(taskA)
                .thenRun(taskB)
                .thenRun(taskC)
                .join();
    }

    /**
     * thenApply 接收一个参数，返回一个参数
     * thenAccept 接收一个参数，无返回
     */
    public static void thenApplyThenAccept(){
        Supplier<String> taskA = () -> "hello";
        Function<String, String> taskB = (t) -> t.toUpperCase();
        Consumer<String> taskC = (t) -> System.out.println("consume: " + t);

        CompletableFuture.supplyAsync(taskA)
                .thenApply(taskB)
                .thenAccept(taskC)
                .join();
    }

    /**
     * thenCompose 接收一个Function,但该函数返回值类型是CompletionStage
     */
    public static void thenCompose(){
        Supplier<String> taskA = () -> "hello";
        Function<String, CompletableFuture<String>> taskB = (t) ->
                CompletableFuture.supplyAsync(() -> t.toUpperCase());
        Consumer<String> taskC = (t) -> System.out.println("consume: " + t);

        CompletableFuture.supplyAsync(taskA)
                .thenCompose(taskB)
                .thenAccept(taskC)
                .join();
    }

    /**
     * 两个阶段都完成后执行另一个任务
     * runAfterBoth
     * thenCombine  接受前两个阶段的结果作为参数，返回一个结果
     * thenAcceptBoth   接受前两个阶段的结果作为参数，但不返回结果
     */
    public static void bothFinished(){
        Supplier<String> taskA = () -> "taskA";
        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> "taskB");
        BiFunction<String, String, String> taskC = (a, b) -> a + "," + b;

        String ret = CompletableFuture.supplyAsync(taskA)
                .thenCombineAsync(taskB, taskC)
                .join();
        System.out.println(ret);
    }

    /**
     * 至少一个任务完成
     * runAfterEither
     * applyToEither
     * acceptEither
     */
    public static void atLeastOneFinished(){

    }

    /**
     * 多阶段任务
     * allOf 等待所有阶段结束，如果有多个阶段异常结束，新的CompletableFuture中保存的异常是最后一个的
     * anyOf 当第一个子CompletableFuture完成或异常结束时，它相应地完成或异常结束，结果与第一个结束的子CompletableFuture一样
     */
    public static void multiStage(){
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            return "helloA";
        });
        CompletableFuture<Void> taskB = CompletableFuture.runAsync(() -> {
        });
        CompletableFuture<Void> taskC = CompletableFuture.runAsync(() -> {
            throw new RuntimeException("task C exception");
        });

        CompletableFuture.allOf(taskA, taskB, taskC).whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println(ex.getMessage());
            }
            if (!taskA.isCompletedExceptionally()) {
                System.out.println("task A " + taskA.join());
            }
        });
    }
}
