package com.ming.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author mingming.xu
 * @description: 获取Java Unsafe 对象
 * @date 2019/4/29 16:25
 * @Version 1.0
 */
public class UnsafeHolder {

    public static Unsafe getUnsafe(){
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
