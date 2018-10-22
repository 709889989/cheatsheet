package com.ming.limiter;


import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * 单机限流算法主要有：漏桶算法，令牌桶算法
 * 单机限流使用令牌桶算法(可以应对突然暴增的流量)，使用Guava RateLimiter 工具类
 *
 */
public class SingleLimiter {

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(0.5) ; // 1s内添加的令牌数量
        //批量调用
        long startTime = System.currentTimeMillis();
        for (int i = 0 ;i< 10 ; i++){
            double acquire = limiter.acquire();
            System.out.println("获取令牌成功!,消耗=" + acquire);
            System.out.println("执行业务逻辑");
        }
        long middleTime = System.currentTimeMillis();
        System.out.println("total cost time=" + (middleTime - startTime));

//        double acquire = limiter.acquire();
        //判断能否在1秒内得到令牌，如果不能则立即返回false，不会阻塞程序
        //可以获得则等待，获得令牌
        boolean lock = limiter.tryAcquire(5000, TimeUnit.MILLISECONDS);
        System.out.println(lock + ":" + (System.currentTimeMillis()-middleTime));
    }
}
