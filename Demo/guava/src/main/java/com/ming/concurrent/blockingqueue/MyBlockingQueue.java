package com.ming.concurrent.blockingqueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 自定义阻塞队列
 * @author xu.mingming
 */
public class MyBlockingQueue {

    private Queue queue;
    private int capacity;

    public MyBlockingQueue(int size){
        this.capacity = size;
        queue = new ArrayDeque(size);
    }

    public synchronized void put(Object o) throws InterruptedException {
        if(queue.size() >= capacity){
            wait();
        }
        queue.add(o);
        /**
         * 因为添加获取方法的wait方法会将线程添加到同一个等候队列，所以不确定唤醒的是哪一个，需要全部唤醒
         */
        notifyAll();
    }

    public synchronized Object take() throws InterruptedException {
        if(queue.isEmpty()){
            wait();
        }
        Object o = queue.poll();
        notifyAll();
        return o;
    }

}
