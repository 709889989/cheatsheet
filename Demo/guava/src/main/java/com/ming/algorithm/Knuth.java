package com.ming.algorithm;

import java.util.Arrays;

/**
 * @author mingming.xu
 * @description: 随机排序算法 The Knuth (or Fisher-Yates) shuffling algorithm
 * @date 2019/7/2 11:08
 * @Version 1.0
 */

public class Knuth {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("input:" + Arrays.toString(input));
        int[] result = shuffle(input);
        System.out.println("result:" + Arrays.toString(result));
    }
    /**
     * 高纳德（ Knuth）随机置乱算法
     * @param input
     * @return
     */
    public static int[] shuffle(int[] input){
        int n = input.length-1;

        for(int i = n; i > 0; i--){
            int random = (int)(Math.random()*i);
            int randomItem = input[random];
            input[random] = input[i];
            input[i] = randomItem;
        }
        return input;
    }
}
