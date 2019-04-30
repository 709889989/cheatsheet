package com.ming.unsafe;

/**
 * @author mingming.xu
 * @description: 测试准备
 * @date 2019/4/29 17:03
 * @Version 1.0
 */
public class TestObject {
    // 实例域值
    private int objField =10;
    public int pObjField =10;
    // 静态域值
    private static int clsField=10;
    // 实例域数组值
    public int[] array={10,20,30,40,50};
    static{
        System.out.println("TestObject init");
    }

}
