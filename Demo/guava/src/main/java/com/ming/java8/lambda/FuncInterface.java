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
 *
 * Predicate 测试是否满足条件
 * Function 函数转换，输入类型T,输出类型R
 * Consumer 直接修改原对象
 */

@FunctionalInterface
public interface FuncInterface {
    void say();


    /**
     * Java8 新特性，运行在接口中添加默认方法和静态方法
     */
    static void staticMethod(){
        System.out.println("接口静态方法");
    }

    /**
     * 方便给现有接口添加新的功能，原先的代码不需要实现，也能在新SDK下运行
     */
    default void defaultMethod(){
        System.out.println("接口默认方法");
    }
}
