package com.ming.init;

/**
 * @author xu.mingming
 */
public class Sub extends Super{
    private static String s = "sub";

    static {
        System.out.println("Sub statuc");
    }

    public Sub(){
        System.out.println("Sub constructor");
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
    }
}
