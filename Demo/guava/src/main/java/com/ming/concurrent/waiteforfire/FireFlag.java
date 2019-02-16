package com.ming.concurrent.waiteforfire;

/**
 * @author xu.mingming
 */
public class FireFlag {

    private volatile boolean fire = false;

    public synchronized void waitForFire() throws InterruptedException {
        if(!fire){
            wait();
        }
    }

    public synchronized void fire(){
        fire = true;

        notifyAll();
    }
}
