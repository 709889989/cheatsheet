package com.ming.testcase;

import com.ming.service.Service;
import org.junit.Test;

/**
 * 异常测试
 * @author xu.mingming
 */
public class ExceptionTest {

    @Test(expected = Exception.class) // 预测测试用例抛出异常
    public void exceptionTest() throws Exception {
        Service service = new Service();
        service.exception();
    }
}
