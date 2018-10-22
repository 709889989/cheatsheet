package com.ming.reflection;

import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

/**
 * Objenesis动态实例化Java类 多线程安全
 * http://objenesis.org/index.html
 * https://blog.csdn.net/top_code/article/details/52964854
 *
 */
public class ObjenesisTest {
    public static void main(String[] args) {
        Objenesis objenesis = new ObjenesisStd(); // or ObjenesisSerializer
        Test thingy1 = (Test) objenesis.newInstance(Test.class);
        thingy1.print();

// or (a little bit more efficient if you need to create many objects)

        Objenesis objenesis2 = new ObjenesisStd(); // or ObjenesisSerializer
        ObjectInstantiator thingyInstantiator = objenesis2.getInstantiatorOf(Test.class);

        Test thingy2 = (Test)thingyInstantiator.newInstance();
        Test thingy3 = (Test)thingyInstantiator.newInstance();
        thingy2.print();
    }
}
