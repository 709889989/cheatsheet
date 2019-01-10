package com.ming.testcase;
import java.util.Arrays;
import java.util.Collection;

import com.ming.service.Service;
import org.junit.Test;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * 参数化测试
 * 动态组装测试使用的数据，并一次性执行一组测试用例
 * @author xu.mingming
 */
@RunWith(Parameterized.class)
public class ParameterTest {
    private Integer inputNumber;
    private Boolean expectedResult;
    private Service checker;

    /**
     * 第一步：准备测试数据
     * 可以从外部文件、数据库读取数据信息
     * @return
     */
    @Parameterized.Parameters
    public static Collection prepareParameters() {
        return Arrays.asList(new Object[][] {
                { 2, true },
                { 6, false },
                { 19, true },
                { 22, false },
                { 23, true }
        });
    }
    /**
     * 第二步：编写构造函数
     * 入参和准备的测试数据相同
     * @param inputNumber
     * @param expectedResult
     */
    public ParameterTest(Integer inputNumber, Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    /**
     * 第三步：编写测试用例
     */
    // 测试用例前的准备
    @Before
    public void initialize() {
        checker = new Service();
    }
    // 测试用例
    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertEquals(expectedResult, checker.validate(inputNumber));
    }
}
