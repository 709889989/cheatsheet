package com.ming.lock;


/**
 * 分布式锁实现(防重服务)
 *
 * 分布式实现主要有三种方式：数据库、Redis、Zookeeper。主流方式为Redis
 *
 * 文章参考：http://crossoverjie.top/2018/03/29/distributed-lock/distributed-lock-redis/
 *
 * 实现原理：往Redis中放key(set nx ex) , key原来不存在并设置超时时间，获得锁
 * 删除key，释放锁
 *
 */
public class DistributedLock {
}
