package com.ming.concurrent.collection;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class CopyOnWriteArrayLIstTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        list.add("hello");
        list.add("a");
        list.add("v");
        list.addIfAbsent("hello");

        System.out.println(list.toString());
        Collections.sort(list);
        System.out.println(list);
        /**
         * Java 没有CurrentHashSet 可以使用Collections.newSetFromMap() 创建
         */
        Set concurrentSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }
}
