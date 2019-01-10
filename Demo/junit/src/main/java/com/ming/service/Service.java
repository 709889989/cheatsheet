package com.ming.service;

import java.util.concurrent.TimeUnit;

public class Service {
    public String hello(){
        return "hello world";
    }

    public void timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exception() throws Exception {
        throw new Exception();
    }

    public Boolean validate(Integer primeNumber) {
        for (int i = 2; i < (primeNumber / 2); i++) {
            if (primeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}