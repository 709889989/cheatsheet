package com.ming.concurrent;


import java.util.concurrent.ThreadLocalRandom;

/**
 * 线程本地变量 --- 每个线程都有同一个变量的独有拷贝
 *
 * ThreadLocal 与线程池一起使用时候，会存在获取同一个对象的情况发生，需要特殊处理。
 *
 */
public class ThreadLocalTest {
    //ThreadLocal对象一般都定义为static，以便于引用
    static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 1);
    /**
     * Random 的 ThreadLocal 实现版本
     */
    static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
    /**
     * 访问的虽然是同一个变量local，但每个线程都有自己的独立的值，互相不影响，
     * 这就是线程本地变量的含义。
     */
    public static void main(String[] args) throws InterruptedException {
        Thread child = new Thread(() -> {
            System.out.println("child thread initial: " + local.get());
            local.set(200);
            System.out.println("child thread final: " + local.get());
        });

        local.set(100);
        child.start();
        child.join();
        System.out.println("main thread final: " + local.get());

        local.remove();
        System.out.println("will get init value: " + local.get());

        System.out.println(threadLocalRandom.nextInt(12));
    }
}
