package com.ming.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 信号量，控制同时访问的数量
 * Semaphore限制对资源的并发访问数
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        semaphore.release();
        semaphore.acquire();

        System.out.println("acquired");

    }
}
