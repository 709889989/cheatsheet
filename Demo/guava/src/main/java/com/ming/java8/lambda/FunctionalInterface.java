package com.ming.java8.lambda;

/**
 * 函数式接口
 * 函数式接口也是接口，但只能有一个抽象方法
 *
 * 方法入参是函数式接口类型的，可以使用lambda表达式代替
 *
 * 参考：HelloWorld.hello(() -> System.out.println("hello world"));
 *
 * @FunctionalInterface Java 定义注解，用例标示这是一个函数式接口
 */

@java.lang.FunctionalInterface
public interface FunctionalInterface {
    void say();


    /**
     * Java8 新特性，运行在接口中添加默认方法和静态方法
     */
    static void staticMethod(){
        System.out.println("接口静态方法");
    }

    default void defaultMethod(){
        System.out.println("接口默认方法");
    }
}
