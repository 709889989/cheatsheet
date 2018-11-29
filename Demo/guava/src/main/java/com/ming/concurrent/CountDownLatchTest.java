package com.ming.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 倒计时门栓 --- 适用多个不同种线程使用
 *
 * 相当于是一个门栓，一开始是关闭的，所有希望通过该门的线程都需要等待，然后开始倒计时，
 * 倒计时变为0后，门栓打开，等待的所有线程都可以通过，它是一次性的，打开后就不能再关上了。
 *
 * 1. 初始化CountDownLatch 数量
 * 2. 需要等待的任务调用await()
 * 3. 计数倒计时任务调用countDown()
 * 4. countDown()调用够了初始化次数，所有等待任务执行
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        startAll();     // 所有线程同时开始
        waitAllFinish();    // 等待所有子线程结束
    }

    /**
     * 线程同时开始
     */
    public static void startAll() throws InterruptedException {
        int num = 10;
        CountDownLatch latch = new CountDownLatch(1);
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(latch);
            racers[i].start();
        }
        Thread.sleep(1000);
        latch.countDown();
    }

    /**
     * 等待所有子线程结束
     * @throws InterruptedException
     */
    public static void waitAllFinish() throws InterruptedException {
        int workerNum = 100;
        CountDownLatch latch = new CountDownLatch(workerNum);
        Worker[] workers = new Worker[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workers[i] = new Worker(latch);
            workers[i].start();
        }
        latch.await();
        System.out.println("collect worker results");
    }

    static class Racer extends Thread {
        CountDownLatch latch;

        public Racer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                this.latch.await();
                System.out.println(getName()
                        + " start run "+System.currentTimeMillis());
            } catch (InterruptedException e) {
            }
        }
    }

    static class Worker extends Thread {
        CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // simulate working on task
                Thread.sleep((int) (Math.random() * 1000));

                // simulate exception
                if (Math.random() < 0.02) {
                    throw new RuntimeException("bad luck");
                }
            } catch (InterruptedException e) {
            } finally {
                this.latch.countDown();
            }
        }
    }
}
