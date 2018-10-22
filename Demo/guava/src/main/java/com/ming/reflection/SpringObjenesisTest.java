package com.ming.reflection;

import org.springframework.objenesis.SpringObjenesis;

/**
 * Spring core 包中的SpringObjenesis工具使用示例
 * 动态创建实例
 * SpringObjenesis 对ObjectInstantiator 进行了缓存
 */
public class SpringObjenesisTest {
    public static void main(String[] args) {
        SpringObjenesis springObjenesis = new SpringObjenesis();

        // SpringObjenesis 已经对Test.class 的ObjectInstantiator 进行了缓存
        // SpringObjenesis 对不同的Class进行缓存，方便使用。
        // Objenesis 内部会对同一个Class 的ObjectInstantiator 进行了缓存，提高执行效率
        Test test = springObjenesis.newInstance(Test.class);

        test.print();
    }
}
