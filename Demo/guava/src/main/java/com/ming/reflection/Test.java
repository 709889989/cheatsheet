package com.ming.reflection;

public class Test {
    private String name;
    public Test(String name){
        this.name = name;
    }
    public void print(){
        System.out.println("==============");
        System.out.println("实例已经构建完成");
        System.out.println("==============");
    }
}
