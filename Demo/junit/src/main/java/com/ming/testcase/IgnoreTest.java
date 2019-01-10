package com.ming.testcase;

import com.ming.service.Service;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Ignore 注解用来标识测试用例忽略不执行
 * 可以用在具体的@Test 用例或测试用例类上
 * @author xu.mingming
 */
@Ignore // 忽略类中全部的测试用例
public class IgnoreTest {

    @Test   // 忽略hello() 这一个测试用例
    public void hello(){
        Service s = new Service();
        Assert.assertEquals("hello world", s.hello());
    }
}
