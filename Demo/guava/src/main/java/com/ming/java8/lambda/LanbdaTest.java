package com.ming.java8.lambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanbdaTest {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executor.submit(() -> System.out.println("hello world"));

        HelloWorld.hello(() -> System.out.println("hello world"));
    }


}
