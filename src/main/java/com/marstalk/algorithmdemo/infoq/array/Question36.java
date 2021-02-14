package com.marstalk.algorithmdemo.infoq.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 问题 36：在整数数组中查找第一个重复元素。
 * 找到整数数组中的第一个重复元素。
 * https://java2blog.com/find-first-repeating-element-array-integers/
 */
public class Question36 {
    public static void main(String[] args) {
        int[] array = {10, 7, 8, 1, 8, 7, 6};
        find(array);
        System.out.println("found2: " + find2(array));
        System.out.println("found3: " + find3(array));
    }

    /**
     * 题目中需要找第一个，那么如果是HashSet的话，怎么搞呢，
     * 很简单的从右往左开始，如果set中有该元素，那么则记录下当前index；否则放入set中。
     * 【直呼内行啊，简直了！！！！！！！！】
     *
     * @param array
     * @return
     */
    private static int find3(int[] array) {
        Set<Integer> set = new HashSet<>();
        int index = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (set.contains(array[i])) {
                index = i;
            } else {
                set.add(array[i]);
            }
        }
        return index == -1 ? 0 : array[index];
    }

    /**
     * 双层循环，事件复杂度是n*n
     *
     * @param array
     */
    private static int find2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    return array[i];
                }
            }
        }
        System.out.println("没有找到");
        return 0;
    }

    /**
     * 我想到的第一种办法是使用hash（key=element， value=count），不断地放元素进去，count++，第一个count==2的元素即为
     * 事件复杂度是N，空间复杂度也是N
     * <p>
     * 不，更简单是使用HashSet，如果contains，即命中。
     *
     * @param array
     * @deprecated 我理解错题目了。应该是这么理解的：
     * 第一个元素是10，那么10是不是重复的？不是
     * 第二个元素是7，那么7是不是重复的，是，命中，结束。
     */
    private static void find(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            int e = array[i];
            if (set.contains(e)) {
                System.out.println("found wrong: " + e);
                break;
            } else {
                set.add(e);
            }
        }
    }
}
