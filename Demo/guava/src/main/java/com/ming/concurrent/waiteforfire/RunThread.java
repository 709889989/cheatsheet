package com.ming.concurrent.waiteforfire;

/**
 * @author xu.mingming
 */
public class RunThread extends Thread{
    private FireFlag flag;

    public RunThread(FireFlag flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            flag.waitForFire();
            System.out.println("fire!" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FireFlag flag = new FireFlag();

        for (int i = 0; i < 10; i++) {
            RunThread runThread = new RunThread(flag);
            runThread.start();
        }
        flag.fire();
    }
}
