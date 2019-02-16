package com.ming.init;

/**
 * @author xu.mingming
 */
public class Test {

    static {
        i = 0;  //  给变量复制可以正常编译通过
//        System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }
    static int i = 1;

    static int j = 1;

    static{
        System.out.println(j);
        j = 2;
        System.out.println(j);
    }

    public static void main(String[] args){
        System.out.println(Test.i);  //1
        System.out.println(Test.j); //2
    }
}
