package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 30：使用 Java 找到滑动窗口的最大值
 * 给定一个整数数组（长度是m）和一个整数 k，从所有大小为 K 的连续子数组中找出最大元素。
 */
public class Question30TODO {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, -1, 2, 4, 1, -6, 5};
        //6 6 4 4 4 5
        int windowSize = 3;
        solve(arr, windowSize);
        solve2(arr, windowSize);
    }

    /**
     * //TODO 单调双端队列的解法。
     * @param arr
     * @param windowSize
     */
    private static void solve2(int[] arr, int windowSize) {

    }

    /**
     * 双循环，外层循环锁住窗口大小。
     * 内循环找到最大值，并且打印出来
     * 但是事件复杂度是k*(m-k+1)约等于k*m，因为m>k
     * @param arr
     * @param windowSize
     */
    private static void solve(int[] arr, int windowSize) {
        for (int i = windowSize - 1; i < arr.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i - windowSize + 1; j <= i; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            System.out.println(max);
        }
    }
}
