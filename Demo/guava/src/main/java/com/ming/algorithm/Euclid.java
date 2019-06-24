package com.ming.algorithm;

/**
 * @author mingming.xu
 * @description:
 * @date 2019/6/18 14:29
 * @Version 1.0
 */

public class Euclid {
    public static void main(String[] args) {
        System.out.println(euclid(60,100));
    }

    /**
     * 欧几里得算法
     * 求最大公约数
     * @param p
     * @param q
     * @return
     * @throws Exception
     */
    public static int euclid(int p, int q) {
        if(q == 0) {
            return p;
        }
        int r = p%q;
        return euclid(q, r);
    }
}
