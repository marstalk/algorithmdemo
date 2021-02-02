package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 35：在已排序的二进制数组中找出数字 1 的数量。
 * 在给定的已排序二进制数组中打印数字 1 的数量。
 */
public class Question35 {
    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 1, 1, 1, 1, 1};
        count(arr);
    }

    /**
     * 二分法找到那个左边是0，本身是1的元素
     *
     * @param arr
     */
    private static void count(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (arr.length == 1) {
            System.out.println(arr[0] == 0 ? 0 : 1);
            return;
        }
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] == 1 && arr[mid - 1] == 0) {
                //{0, 0, 0, 1, 1, 1, 1, 1, 1} mid=3, length=9, count(1) = 6
                System.out.println(arr.length - mid);
                return;
            }
            if (arr[mid - 1] == 1) {
                end = mid;
            } else if (arr[mid] == 0) {
                start = mid;
            }
        }
    }
}
