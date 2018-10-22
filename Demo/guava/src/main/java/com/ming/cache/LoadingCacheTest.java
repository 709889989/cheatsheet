package com.ming.cache;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.*;

/**
 * Guava LoadingCache 是一个本地缓存工具类
 *
 * 本地缓存工具类，主要功能：
 * 缓存定时刷新，缓存写入后x失效，缓存读取后x失效
 *
 * 主要方法：
 * V getIfPresent(Object key) 获取缓存中key对应的value，如果缓存没命中，返回null。return value if cached, otherwise return null.
 * V get(K key) throws ExecutionException 获取key对应的value，若缓存中没有，则调用LocalCache的load方法，从数据源中加载，并缓存。 return value if cached, otherwise load, cache and return.
 * void put(K key, V value) if cached, return; otherwise create, cache , and return.
 * void invalidate(Object key); 删除缓存
 * void invalidateAll(); 清楚所有的缓存，相当远map的clear操作。
 * long size(); 获取缓存中元素的大概个数。为什么是大概呢？元素失效之时，并不会实时的更新size，所以这里的size可能会包含失效元素。
 * CacheStats stats(); 缓存的状态数据，包括(未)命中个数，加载成功/失败个数，总共加载时间，删除个数等。
 * ConcurrentMap<K, V> asMap(); 将缓存存储到一个线程安全的map中。
 *
 *
 */
public class LoadingCacheTest {
    // 缓存刷新线程池
    private static ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LoadingCache<Integer, String> testCache = buildCache();

        // getIfPresent()获取缓存中值，没有返回null
        // get() 获取缓存中值，没有CacheLoader.load() 加载并缓存
        System.out.println("getIfPresent()" + testCache.getIfPresent(1));

        for (int i=0;i<20;i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            String cache = testCache.get(1);
            System.out.println("read from cache:" + cache);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

        //最后打印缓存的命中率等 情况
        System.out.println("cache stats:" + testCache.stats().toString());

    }

    /**
     * 创建缓存池
     * @return
     */
    public static LoadingCache<Integer, String> buildCache(){
        LoadingCache<Integer, String> testCache
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(10, TimeUnit.SECONDS)    // 写入后8s过期
//                .expireAfterAccess(8, TimeUnit.SECONDS) // 访问后8s过期
                // 设置缓存刷新时间
                .refreshAfterWrite(8, TimeUnit.SECONDS)  // 在缓存写入10s 后刷新缓存
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<Integer, String>() {
                            //缓存加载方法
                            @Override
                            public String load(Integer key) throws Exception {
                                System.out.println("load Cache! key=" + key);
                                // 定义数据读取逻辑
                                return getFromDataSource(key);
                            }
                            //缓存刷新方法
                            @Override
                            public ListenableFuture<String> reload(final Integer key, String oldValue) throws Exception {
                                return executorService.submit(new Callable<String>() {
                                    @Override
                                    public String call() throws Exception {
                                        System.out.println("reload Cache! key=" + key);
                                        return getFromDataSource(key);
                                    }
                                });
                            }
                        }
                );

        return testCache;
    }

    private static String getFromDataSource(Integer key){
        System.out.println("load from SourceData! key=" + key);
        return key.toString();
    }
}
