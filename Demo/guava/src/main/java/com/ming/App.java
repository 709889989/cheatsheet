package com.ming;

import org.springframework.util.StringUtils;

import java.lang.reflect.Array;

public class App {
    public static void main(String[] args) {
        String s = "12345";
        System.out.println(getLastFourBankAcct(s));
        test1();
    }

    public static String getLastFourBankAcct(String bankAcct){
        if(StringUtils.isEmpty(bankAcct) || bankAcct.length()<5){
            return bankAcct;
        }
        int len = bankAcct.length();
        return bankAcct.substring(len-4, len);
    }

    public static void test1(){
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
