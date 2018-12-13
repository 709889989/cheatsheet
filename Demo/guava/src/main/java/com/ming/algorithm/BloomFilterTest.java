package com.ming.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author xu.mingming
 *
 * 布隆过滤:BloomFilter
 * 主要就是用于解决判断一个元素是否在一个集合中，但它的优势是只需要占用很小的内存空间以及有着高效的查询效率。
 * 如判断一个数字是否存在于一个1000w的数字集合中
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter filter = BloomFilter.create(Funnels.integerFunnel(), 100000000);
        for (int i = 0; i < 100000000; i++){
            filter.put(i);
        }

        long startTime = System.currentTimeMillis();

        boolean rs = filter.mightContain(1000000000);
        boolean rs2 = filter.mightContain(123);
        System.out.println("rs=" + rs + "; rs2=" + rs2);

        long cost = System.currentTimeMillis() - startTime;
        System.out.println(cost);
    }
}
