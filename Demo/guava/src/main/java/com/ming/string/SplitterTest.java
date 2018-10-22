package com.ming.string;

import com.google.common.base.Splitter;

import java.util.Map;

/**
 * 字符串分隔工具类Spliter
 * 从输入的字符串中抽取不重复的子串，通常是分析给定的分割序列；
 * 这个分割符可以是单个的字符（on(char separator)）、字符串（on(final String separator)）、
 * 正则表达式（on(final Pattern separatorPattern)）或者是一个CharMatcher实例（on(final CharMatcher separatorMatcher)）。
 * 当然，也可以不传入分隔符（fixedLength(final int length)），从而将给定的字符串分割为长度为length的子字符串。
 * 接口文档参考：https://blog.csdn.net/xuaomo/article/details/79158800
 */
public class SplitterTest {
    public static void main(String[] args) {
        /**
         * 分隔并去除空格,忽略空格
         */
        String s = "the ,quick, , brown         , fox, , ,              jumps, over, the, lazy, little dog.";
        Iterable<String> result = Splitter.on(',').omitEmptyStrings().trimResults().split(s);
        System.out.println(result);

        System.out.println(Splitter.on('|').split("we | are | happy"));     // 特定字符分隔
        System.out.println(Splitter.onPattern("\\s").split("we are happy"));    //正则表达式分隔
        System.out.println(Splitter.fixedLength(2).split("we are happy"));  // 固定长度分隔

        /**
         * 分隔字符串为Map
         */
        String mapString = "userName=Nimo phone=123 address=浙江省杭州市滨江区XXXX";
        Map<String,String> map = Splitter.on(" ").withKeyValueSeparator("=").split(mapString);
        System.out.println(map.toString());
//        打印输出
//        [the, quick, , brown, fox, jumps, over, the, lazy, little dog.]
//        [we ,  are ,  happy]
//        [we, are, happy]
//        [we,  a, re,  h, ap, py]
//        {userName=Nimo, phone=123, address=浙江省杭州市滨江区XXXX}
    }

}
