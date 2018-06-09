package com.ming;

import com.ming.testcase.JunitAnnotation;
import com.ming.testcase.ServiceTest;
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
//        Result result = JUnitCore.runClasses(ServiceTest.class);
        Result result = JUnitCore.runClasses(JunitAnnotation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
