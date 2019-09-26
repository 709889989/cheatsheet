package com.ming;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mingming.xu
 * @description: 手动创建dump文件
 * @date 2019/7/30 11:23
 * @Version 1.0
 */

public class Dump {
    public static void main(String[] args) throws InterruptedException {
        List<String> l = new ArrayList<>();
        int i = 1;
        while (true){
            System.out.println(i);
            i++;
            String s = "======================";
            l.add(s);
            TimeUnit.MILLISECONDS.sleep(100);
            System.gc();
        }
    }

}
