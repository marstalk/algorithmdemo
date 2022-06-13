package com.marstalk.algorithmdemo.binarytree;

import java.util.Arrays;

/**
 * 使用后序遍历的思想来理解归并排序。
 *
 * @author liujiacheng
 */
public class MergeSort {
    private static int[] tmp;

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 4, 10};
        mergeSort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    /**
     * 归并排序主函数
     *
     * @param nums
     */
    private static void mergeSort(int[] nums) {
        tmp = new int[nums.length];
        traverse(nums, 0, nums.length - 1);
    }

    /**
     * nums[lo:hi]是一棵树，本方法保证nums[lo:hi]这个区间的元素是有序的。
     *
     * @param nums
     * @param lo
     * @param hi
     */
    static void traverse(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = (lo + hi) / 2;
        traverse(nums, lo, mid);
        traverse(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    /**
     * 辅助方法，将nums[lo:mid]和nums[mid+1: hi]的元素进行排序。需要借助一个辅助数组
     * 辅助数组用来记录旧的数组的顺序。
     * 三个指针：
     * p指向原数组的开始的位置，放置一个数（其实是修改）就往前走一步，范围是[lo:hi]
     * left指针是tmp的左半部分，范围是[lo:mid]
     * right指针tmp是右半部分，范围是[mid+1; hi]
     * 要么从左边取一个数：p+1,left+1
     * 要么从右边取一个数：p+1,right+1
     * @param nums
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }

        int left = lo;
        int right = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (left == mid + 1) {
                nums[p] = tmp[right++];
            } else if (right == hi + 1) {
                nums[p] = tmp[left];
            } else if (tmp[left] < tmp[right]) {
                nums[p] = tmp[left++];
            } else {
                nums[p] = tmp[right++];
            }
        }
    }
}
