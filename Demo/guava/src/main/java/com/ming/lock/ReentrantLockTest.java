package com.ming.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Java 重入锁使用示例
 * ReentrantLock
 * ReentrantReadWriteLock
 *
 */
public class ReentrantLockTest {
    public static void main(String[] args) {


    }

    /**
     * 重入锁使用示例
     */
    public static void reentrantLock(){
        // 重入锁
        ReentrantLock reentrantLock = new ReentrantLock();

        try {
            /**
             * tryLock()方法只有在成功获取了锁的情况下才会返回true，如果别的线程当前正持有锁，则会立即返回false！
             * 如果为这个方法加上timeout参数，则会在等待timeout的时间才会返回false或者在获取到锁的时候返回true。
             */
            reentrantLock.lock(); // 获取锁
//            boolean result = reentrantLock.tryLock();
            boolean result = reentrantLock.tryLock(10, TimeUnit.SECONDS); // 获取锁
            if(result){
//                do some thing
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock(); //释放锁
        }
    }

    /**
     * 重入读写锁使用示例
     */
    public static void reentrantReadWriteLock(){
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        /**
         * 重入读写锁，写方法获取写锁，读方法获取读锁。
         * 当写线程访问时则其他所有锁都将阻塞，读线程访问时则不会。
         * 通过读写锁的分离可以很大程度的提高并发量和吞吐量。
         */

    }

    /**
     * 读方法
     * @param readWriteLock
     */
    public void read(ReentrantReadWriteLock readWriteLock){
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock(); // 获取读锁
        try {
            readLock.lock();  // 获取锁
//            do some things
        }finally {
            readLock.unlock(); // 释放锁
        }
    }

    /**
     * 写方法
     * @param readWriteLock
     */
    public void write(ReentrantReadWriteLock readWriteLock){
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock(); // 获取写锁
        try {
            writeLock.lock(); // 获取锁
        }finally {
            writeLock.unlock(); // 释放锁
        }
    }
}
