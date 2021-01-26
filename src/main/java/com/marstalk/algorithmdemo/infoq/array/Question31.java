package com.marstalk.algorithmdemo.infoq.array;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 问题 31：计算已排序数组中每个元素的出现次数（或频率）
 * 给定包含重复项的整数排序数组。找出数组中存在的每个元素的频率。
 * 频率定义为数组中元素的出现次数。
 */
public class Question31 {
    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        count(arr);
        count2(arr);
        count3(arr);
    }

    /**
     * 利用它已经排序的特点来更加高效的计算。
     * 但是它的事件复杂度是NLogN,还没有第一种方式来的高效。
     * @param arr
     */
    private static void count3(int[] arr) {
    }

    /**
     * 使用HashMap,
     * 事件复杂度M，
     * 空间复杂度是M
     * @param arr
     */
    private static void count2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];
            if (map.containsKey(element)) {
                map.put(element, map.get(element) + 1);
            }else{
                map.put(element, 1);
            }
        }
        map.entrySet().stream().forEach(entry -> System.out.println("element: " + entry.getKey() + " count: " + entry.getValue()));
    }

    /**
     * 办法1，遍历整个数组，加1
     * 事件复杂度是M
     * @param arr
     */
    private static void count(int[] arr) {
        int ele = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == ele) {
                count++;
            } else {
                System.out.println("element: " + ele + " count: " + count);
                ele = arr[i];
                count = 1;
            }
        }
        //输出最后一组。
        System.out.println("element: " + ele + " count: " + count);
    }
}
