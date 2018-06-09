package com.ming.testsuite;


import com.ming.testcase.JunitAnnotation;
import com.ming.testcase.ServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JunitAnnotation.class,
        ServiceTest.class
})
public class TestSuite {
}
