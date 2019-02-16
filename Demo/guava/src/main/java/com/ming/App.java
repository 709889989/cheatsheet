package com.ming;

import java.lang.reflect.Array;

public class App {
    public static void main(String[] args) {
        Test1<String> t = new Test1();
        t.testMethod("generic");
        Integer i = t.testMethod1(new Integer(1));
        System.out.println(i);

        try{
            return;
        }finally {
            System.out.println("finally!");
        }
    }
}
