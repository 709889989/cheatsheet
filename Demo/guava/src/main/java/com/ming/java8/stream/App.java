package com.ming.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xu.mingming
 * @Data 2018-11-21 14:11
 */
public class App {
    public static void main(String[] args) {
        List<String> s = Arrays.asList("1", "2", "3");

        System.out.println(s.stream().filter((a) -> a.equals("1")).collect(Collectors.toList()));
    }
}
