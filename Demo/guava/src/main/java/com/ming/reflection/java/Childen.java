package com.ming.reflection.java;

public class Childen extends Parent {
    private String cPri;
    public String cPub;

    private String childenPrivateMethod(){
        String s = "childen private method";
        System.out.println(s);
        return s;
    }

    public String childerPublicMethod(){
        String s = "childen public method";
        System.out.println(s);
        return s;
    }
}
