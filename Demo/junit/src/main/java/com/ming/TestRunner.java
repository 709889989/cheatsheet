package com.ming;

import com.ming.testcase.ParameterTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 测试运行器 Test Runner
 * Junit 测试用例运行入口
 */
public class TestRunner {
    public static void main( String[] args )
    {
//        Result result = JUnitCore.runClasses(ServiceTest.class);
//        Result result = JUnitCore.runClasses(JunitAnnotationTest.class);
//        Result result = JUnitCore.runClasses(TestSuite.class);

        /**
         * 运行测试用例
         */
        Result result = JUnitCore.runClasses(ParameterTest.class);

        /**
         * 获取测试结果信息
         */
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
