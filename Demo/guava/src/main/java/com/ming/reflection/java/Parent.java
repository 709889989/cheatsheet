package com.ming.reflection.java;

public class Parent {
    private String pri;
    public String pub;

    private String parentPriMethod(){
        String s = "parent private method";
        System.out.println(s);

        return s;
    }

    public String parentPublicMethod(){
        String s = "parent public method";
        System.out.println(s);

        return s;
    }
}
