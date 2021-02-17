package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 43：和最大的连续子数组。
 * 在一维数字数组内找到和最大的连续子数组。
 *
 * 分析：
 * 动态规划？？
 * 计算每个坐标最大的值，该值要么是它本身，要么是上一个位置的maxSum+本身。
 */
public class Question43 {
    public static void main(String[] args) {
        int arr[] = { 1, 8, -3, -7, 2, 7, -1, -9 };
        System.out.println("Largest sum continuous subarray is " + dynamicProgramForMaxSubArray(arr));
    }

    /**
     * 动态规划，要记录每一步的计算结果，以减少计算的次数。
     * 从左到右，一次计算每个位置上的最大值，并且记录下来。
     * 每个位置的最大值是Math.max(maxSum(i-1) + a[i], a[i])
     *
     * @param arr
     * @return
     */
    private static int dynamicProgramForMaxSubArray(int[] arr) {
        int[] maxSums = new int[arr.length];
        maxSums[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxSums[i] = Math.max(maxSums[i - 1] + arr[i], arr[i]);
        }
        int maxSum = maxSums[0];
        for (int i = 1; i < maxSums.length; i++) {
            if (maxSums[i] > maxSum) {
                maxSum = maxSums[i];
            }
        }
        return maxSum;
    }
}
