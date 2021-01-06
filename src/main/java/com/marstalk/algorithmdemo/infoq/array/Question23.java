package com.marstalk.algorithmdemo.infoq.array;

import java.util.Arrays;

/**
 * 给定一个包含正负整数的数组，需要找出数组中和最接近零的整数对。
 * 0既不是正数也不是负数。
 * 如果通过NlogN的复杂度把数组排好序。
 * 维护两个指针，l=0，r=N-1
 * 如果两个数之和是负数，则说明负数过大了，l要先前走一步（指向更小的负数）
 *
 */
public class Question23 {
    public static void main(String[] args) {
        int array[]={1,30,-5,70,-8,20,-40,60};
        findPairWithMinSumBruteForce(array);
        findPairWithMinSum(array);
    }

    /**
     * n*n的复杂度。
     * 将所有的组合都列出来，相加取绝对值。
     * 【改进】
     *  通过NlogN的排序之后，再加上N的复杂度，约等于NlogN的即可
     * @param array
     */
    private static void findPairWithMinSumBruteForce(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length <= 2) {
            return;
        }
        int x=0, y = 1;
        int minimum = Math.abs(array[x] + array[y]);
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                int abs = Math.abs(array[i] + array[j]);
                if (abs < minimum) {
                    minimum = abs;
                    x = array[i];
                    y = array[j];
                }
            }
        }
        System.out.println(x + " " + y);
    }

    /**
     * 通过NlogN的排序之后，再加上N的复杂度，约等于NlogN的即可
     * @param array
     * @return
     */
    private static void findPairWithMinSum(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length <= 2) {
            return;
        }
        Arrays.sort(array);
        int l = 0;
        int r = array.length - 1;

        int x = 0;
        int y = 0;
        int minimum = Integer.MAX_VALUE;
        while (l<r) {
            int sum = array[l] + array[r];
            if (Math.abs(sum) < Math.abs(minimum)) {
                minimum = sum;
                x = l;
                y = r;
            }
            if (sum < 0) {
                l++;
            }else{
                r--;
            }
        }
        System.out.println(array[x] + " " + array[y]);
    }
}
