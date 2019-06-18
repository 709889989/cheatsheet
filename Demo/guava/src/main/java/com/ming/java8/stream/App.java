package com.ming.java8.stream;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xu.mingming
 * @Data 2018-11-21 14:11
 */
public class App {
    public static void main(String[] args) {
        List<Integer> s = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            s.add(i);
        }
        long st = System.currentTimeMillis();
        s.stream().filter((e)->e>500).collect(Collectors.toList());
        long e1 = System.currentTimeMillis() - st;
        long st2 = System.currentTimeMillis();
        s.parallelStream().filter((e)->e>500).collect(Collectors.toList());
        long e2 = System.currentTimeMillis() - st2;
        System.out.println("stream() cost=" + e1);
        System.out.println("parallelStream() cost=" + e2);
        System.out.println(e1/e2);
    }
}
