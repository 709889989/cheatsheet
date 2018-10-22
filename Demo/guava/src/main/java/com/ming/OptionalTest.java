package com.ming;


import com.google.common.base.Optional;

/**
 * Guava Optional 是一个null处理工具类
 * 包裹处理一个值为null或非null的各种情况
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer a = null;
        Integer b = new Integer(10);

        // 实例化获取Optional<T>

        /**
         * 获取一个空的Optional
         */
        Optional<Integer> absent = Optional.absent();
        System.out.println("获取一个空的Optional:" + absent.isPresent());
        /**
         * Optional.fromNUllable() 入参可以为null
         */
        Optional<Integer> nullAble = Optional.fromNullable(a);
        /**
         * Optional.of() 入参不能为null，为null报NullPointException
         */
        Optional<Integer> canNotNull = Optional.of(b);



        /**
         *  检查Optional<T> 是否为空
         *  isPresent() 返回true，如果包含一个(非空)的实例
         */
        boolean isPresent = nullAble.isPresent();
        System.out.println("检查Optional<T> 是否为空:" + isPresent);

        /**
         * or() 如果Optional 为空返回，默认值
         */
        Integer orT = nullAble.or(11);    // 为空返回11
        Optional<Integer> orOptional = nullAble.or(canNotNull); // 为空返回canNotNull
        System.out.println("返回默认值：" + orT + ";" + orOptional.get());

        /**
         * orNull() 如果Optional 为空返回null
         */
        Integer isNull = nullAble.orNull();
        System.out.println("返回null：" + isNull);
        /**
         * 获取包含的T值，非absent(非空)，空，抛出IllegalStateException
         */
        Integer ten = canNotNull.get();
        System.out.println("获取Optional包含值：" + ten);
        Integer i = nullAble.get();
    }


}
