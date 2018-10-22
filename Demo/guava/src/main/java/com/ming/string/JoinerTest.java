package com.ming.string;

import com.google.common.base.Joiner;

import java.io.IOException;
import java.util.*;

/**
 * 字符串拼接工具类Joiner
 *
 */
public class JoinerTest {
    public static void main(String[] args) throws IOException {
        /**
         * on() 设置字符串分隔符
         * skipNulls() 跳过null
         */
        String s = Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,null,4,5)); //1,2,3,4,5
        System.out.println("result=" + s);
        /**
         * useForNull() 使用特点字符替换null值
         */
        String s2 = Joiner.on(",").useForNull("null").join(Arrays.asList(1,2,3,null,4,5)); //1,2,3,4,5
        System.out.println("result=" + s2);

        /**
         * appendTo() StringBuffer 等类的扩展
         */
        String[] sArray = new String[]{"a", "b", "c"};
        StringBuffer sb = new StringBuffer("s");
        sb = Joiner.on(",").appendTo(sb, sArray);
        System.out.println(sb.toString());

        /**
         * withKeyValueSeparator() 键值对分隔
         */
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "a");
        map.put(2, "b");
        Joiner.MapJoiner joiner = Joiner.on(";").withKeyValueSeparator("=");
        String mapString = joiner.join(map);
        System.out.println("map键值对：" + mapString);
    }
}
