package com.ming.testcase;

import com.ming.service.Service;
import org.junit.Test;
import org.junit.Assert;

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
