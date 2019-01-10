package com.ming.testcase;

import com.ming.service.Service;
import org.junit.Test;
import org.junit.Assert;

/**
 * 测试用例
 * Service 服务的测试用例
 */
public class ServiceTest {

    @Test
    public void hello(){
        Service s = new Service();
        Assert.assertEquals("hello world", s.hello());
    }
    @Test
    public void error(){
        Service s = new Service();
        Assert.assertEquals("hello", s.hello());
    }
}
