package com.marstalk.algorithmdemo.binarytree;

import java.util.Arrays;

/**
 * 使用二叉树思想来实现快速排序。
 * 前序遍历，取中间数x，使得左边的数都小于x，右边的数都大于x，
 * @author liujiacheng
 */
public class QuickSort {
    private static int[] help;
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 4, 9};
        quickSort(nums);
        Arrays.stream(nums).forEach(System.out::print);
    }

    private static void quickSort(int[] nums) {
        help = new int[nums.length];
        traverse(nums, 0, nums.length - 1);
    }

    private static void traverse(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // 使得nums[lo, sep-1] <= nums[sep] <= nums[sep +1, hi]
        int sep = partition(nums, lo, hi);

        traverse(nums, lo, sep -1);
        traverse(nums, sep  + 1, hi);
    }

    private static int partition(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        for (int i = lo; i <= hi; i++) {
            help[i] = nums[i];
        }
        // 使用第一个数作为基准
        int x = help[lo];

        for (int j = lo+1; j <= hi; j++) {
            if (help[j] <= x) {
                nums[lo++] = help[j];
            } else  {
                nums[hi--] = help[j];
            }
        }
        // TODO 这个返回值有待确定。
        return 0;
    }
}
