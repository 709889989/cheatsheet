package com.ming.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环栅栏 --- 适用同一种线程使用，可以重复利用
 *
 * 栅栏，所有线程在到达该栅栏后都需要等待其他线程，等所有线程都到达后再一起通过，
 * 它是循环的，可以用作重复的同步
 *
 * 在CyclicBarrier中，参与的线程是互相影响的，只要其中一个线程在调用await时被中断了，或者超时了，栅栏就会被破坏，
 * 此外，如果栅栏动作抛出了异常，栅栏也会被破坏，被破坏后，所有在调用await的线程就会退出，抛出BrokenBarrierException。
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        int num = 3;
        Tourist[] threads = new Tourist[num];
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override   // 最后一个到达的线程，就执行可选的命令
            public void run() {
                System.out.println("all arrived " + System.currentTimeMillis()
                        + " executed by " + Thread.currentThread().getName());
            }
        });
        for (int i = 0; i < num; i++) {
            threads[i] = new Tourist(barrier);
            threads[i].start();
        }

    }

    static class Tourist extends Thread {
        CyclicBarrier barrier;

        public Tourist(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // 模拟先各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合点A
                barrier.await();

                System.out.println(this.getName() + " arrived A "
                        + System.currentTimeMillis());

                // 集合后模拟再各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合点B
                barrier.await();
                System.out.println(this.getName() + " arrived B "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
    }


}
