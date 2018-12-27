package com.ming.cache;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

/**
 * MapDb java嵌入式数据库
 * MapDb官网 http://www.mapdb.org/
 * 文档：https://jankotek.gitbooks.io/mapdb/content/db/
 * @author xu.mingming
 */
public class MapDbTest {
    public static void main(String[] args) {
        offHeap();
        file();
    }

    /**
     * 堆外内存存储
     */
    public static void offHeap(){
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        String key = "something";
        map.put(key, "here");

        System.out.println(map.get(key));
    }

    /**
     * 文件存储
     * 数据信息存储在文件中，不会丢失
     */
    public static void file(){
        DB db = DBMaker.fileDB("file.db").make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();

        String key = "something";
        System.out.println(map.get(key));
        map.put(key, "here");
        System.out.println(map.get(key));
        db.close();
    }
}
