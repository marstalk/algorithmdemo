package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 35：在已排序的二进制数组中找出数字 1 的数量。
 * 在给定的已排序二进制数组中打印数字 1 的数量。
 */
public class Question35 {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 0, 1, 1, 1, 1};
        count(arr);
        count2(arr);
    }

    /**
     * 排好序的数组，都可以考虑下能不能使用二分法。
     * divide and conquer
     * @param arr
     */
    private static void count2(int[] arr) {
        int count = 0;
        int start = 0;
        int end = arr.length - 1;

        //检查边界。
        if (arr[start] == 1) {
            count = arr.length;
        } else if (arr[end] == 0) {
            count = 0;
        }

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid - 1] == 0 && arr[mid] == 1) {
                count = arr.length - mid;
                break;
            } else if (arr[mid] == 0 && arr[mid + 1] == 1) {
                count = arr.length - mid + 1;
                break;
            } else if (arr[mid - 1] == 1) {
                end = mid;
            }else {
                start = mid;
            }
        }
        System.out.println(count);
    }

    /**
     * 最简单的是遍历，知道遇到1的元素为止，时间复杂是N
     *
     * @param arr
     */
    private static void count(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                count = arr.length - i;
                break;
            }
        }
        System.out.println("1 count: " + count);
    }
}
