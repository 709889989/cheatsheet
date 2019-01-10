package com.ming.testcase;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 套件测试
 * 套件测试，就是将多个单元测试用例一起执行
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JunitAnnotationTest.class,
        ServiceTest.class
})
public class TestSuite {
}
