package com.xiaofeihai;

/**
 * hello world
 * @author mingming.xu
 * @description: 测试
 * @date 2021/1/4 14:40
 * @Version 1.0
 */
@RequestMapping("/hello")
@RestController
public class Test {
    /**
     * hello
     * @author mingming.xu
     * @param args
     * @return
     */
    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public HelloResponse hello(String hello){

    }
}
