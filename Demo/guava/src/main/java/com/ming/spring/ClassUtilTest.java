package com.ming.spring;

import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public class ClassUtilTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class app = ClassUtils.forName("com.ming.algorithm.PayCode", null);


    }
}
