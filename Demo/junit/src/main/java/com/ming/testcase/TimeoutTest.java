package com.ming.testcase;

import com.ming.service.Service;
import org.junit.Test;

/**
 * 超时测试
 * @author xu.mingming
 */
public class TimeoutTest {

    @Test(timeout = 100) // 运行时间超过100毫秒就认为是失败的
    public void timeoutTest(){
        Service service = new Service();
        service.timeout();
    }
}
