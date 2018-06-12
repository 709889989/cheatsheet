package com.ming;

import com.ming.testcase.CheckerTest;
import com.ming.testcase.JunitAnnotation;
import com.ming.testcase.ServiceTest;
import com.ming.testsuite.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String data = "{\"appId\":\"121\",\"memberCode\":\"10012189149\",\"deviceId\":\"EC170542-6711-4A51-A4B4-DC6782B95E4B\"}";
//        Result result = JUnitCore.runClasses(ServiceTest.class);
//        Result result = JUnitCore.runClasses(JunitAnnotation.class);
//        Result result = JUnitCore.runClasses(TestSuite.class);
        Result result = JUnitCore.runClasses(CheckerTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
