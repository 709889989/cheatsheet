package com.ming.testcase;
import java.util.Arrays;
import java.util.Collection;

import com.ming.service.Checker;
import org.junit.Test;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * 参数化测试
 */
@RunWith(Parameterized.class)
public class CheckerTest {
    private Integer inputNumber;
    private Boolean expectedResult;
    private Checker checker;

    /**
     * 准备测试数据
     * @return
     */
    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { 2, true },
                { 6, false },
                { 19, true },
                { 22, false },
                { 23, true }
        });
    }

    /**
     * 编写构造函数
     * 入参和测试数据相同
     * @param inputNumber
     * @param expectedResult
     */
    public CheckerTest(Integer inputNumber, Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }



    @Before
    public void initialize() {
        checker = new Checker();
    }
    // 测试用例
    @Test
    public void testPrimeNumberChecker() {
        System.out.println("Parameterized Number is : " + inputNumber);
        assertEquals(expectedResult, checker.validate(inputNumber));
    }
}
