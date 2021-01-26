package com.marstalk.algorithmdemo.infoq.array;

import java.util.Arrays;

/**
 * 问题 28：给定一个只有 0、1 和 2 的数组。编写一个函数，以 O(n)的时间复杂度排序给定数组。
 * @author shanzhonglaosou
 */
public class Question28TODO {
    public static void main(String[] args) {
        int[]a = {1,0,2,0,0,1,2,2,1};
        sortOne(a);
        Arrays.stream(a).forEach(System.out::println);

        a = new int[]{1, 0, 2, 0, 0, 1, 2, 2, 1};
        sortTwo(a);

    }

    /**
     * //TODO
     * @param a
     */
    private static void sortTwo(int[] a) {

    }

    /**
     * 第一种方法，计数法，编译一次，计算每个元素的个数，然后再遍历一次，将值修改。
     * @param arr
     */
    private static void sortOne(int[] arr) {
        int[] count = new int[3];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]] = count[arr[i]] + 1;
        }
        int pointer = 0;
        for (int v = 0; v < count.length; v++) {
            int j = count[v];
            while (j-- > 0){
                arr[pointer] = v;
                pointer++;
            }
        }
    }

}
