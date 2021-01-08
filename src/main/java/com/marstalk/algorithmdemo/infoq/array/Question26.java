package com.marstalk.algorithmdemo.infoq.array;

import java.util.Arrays;

/**
 *Separate 0s and 1s in an array
 * 数组元素只有0和1，
 *
 */
public class Question26 {
    public static void main(String[] args) {
        int arr[] = {0, 1, 0, 0, 1, 1, 1, 0, 1};
//        System.out.println("Original Array: ");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        arr = separate0s1sSolution1(arr);
//        System.out.println("===========================");
//        System.out.println("Solution 1");
//        System.out.println("nArray after separating 0's and 1's : ");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
        System.out.println("===========================");
        System.out.println("Solution 2");
        arr = separate0s1sSolution2(arr);
        System.out.println("nArray after separating 0's and 1's : ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 双游标
     * 一个在左
     * 一个在右
     * 如果左元素是1，且右元素是0，则调换位置。
     * 否则：
     *  如果左元素是0，则右移一位
     *  如果右元素是1，则左移一位。
     * @param arr
     * @return
     */
    private static int[] separate0s1sSolution2(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (arr[l] == 1 && arr[r] == 0) {
                arr[l] = 0;
                arr[r] = 1;
                l++;
                r--;
            } else {
                //不符合对调条件。则要那么左游标右移
                if (arr[l] == 0) {
                    l++;
                }
                if (arr[r] == 1) {
                    //要么右游标左移
                    r--;
                }
            }
        }
        return arr;
    }

    /**
     * 计算0的个数，剩下的都是1.
     * 时间复杂度是N
     * 空间复杂度也是N
     *
     * @param arr
     * @return
     */
    private static int[] separate0s1sSolution1(int[] arr) {
        int count = (int) Arrays.stream(arr).filter(i -> i == 0).count();

        int[] ints = new int[arr.length];
        for (int i = count; i < ints.length; i++) {
            ints[i] = 1;
        }
        return ints;
    }
}
