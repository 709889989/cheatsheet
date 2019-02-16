package com.ming;

/**
 * @author xu.mingming
 */
public class Test1<T>{

    public  void testMethod(T t){
        System.out.println(t.getClass().getName());
    }
    public  <T> T testMethod1(T t){
        return t;
    }
}
