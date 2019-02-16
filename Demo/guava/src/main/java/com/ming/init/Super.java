package com.ming.init;

/**
 * @author xu.mingming
 */
public class Super {
    private static String s = "s";

    static {
        System.out.println("super static");
    }

    public Super(){
        System.out.println("super constructor");
    }
}
