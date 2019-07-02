package com.ming.algorithm;

/**
 * @author mingming.xu
 * @description: 二分查找算法
 * @date 2019/7/2 10:31
 * @Version 1.0
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] list = {0, 1, 2, 3, 4, 5};
        int index = binarySearch(list, 3);

        System.out.println("3 index = " + index);
    }

    /**
     * 二分查找算法
     * @param list 待搜索列表,有序
     * @param key
     * @return 返回key所在位置索引，未找到返回-1
     */
    public static int binarySearch(int[] list, int key){
        int li = 0;
        int hi = list.length-1;
        while(li<=hi){
            int mid = li + (hi-li)/2;
            if(key>list[mid]){
                li = mid+1;
            }else if(key < list[mid]){
                hi = mid -1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
