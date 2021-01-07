package com.marstalk.algorithmdemo.infoq.array;

import java.util.Arrays;

/**
 * 查找数组中和等于给定数字的所有元素对
 */
public class Question25 {
    public static void main(String[] args) {
        int array[] = {-40, -5, 1, 3, 6, 7, 8, 20};
        findPairsWithSumEqualsToXBruteForce(array, 15);
        findPairsEqualsToX(array, 15);
//        findPairsEqualsToXUsingHashing(array, 15);
    }

    private static void findPairsEqualsToX(int[] array, int target) {
        if (null == array || array.length < 2) {
            return;
        }
        //NlogN
        Arrays.sort(array);
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int sum = array[l] + array[r];
            if (sum == target) {
                System.out.println(array[l] + " " + array[r]);
                l++;
                r--;
            }else if (sum < target) {
                l++;
            }else{
                r--;
            }
        }
    }

    /**
     * 列出所有的组合，时间复杂度是N*N，时间复杂度是1
     *
     * @param array
     * @param target
     */
    private static void findPairsWithSumEqualsToXBruteForce(int[] array, int target) {
        if (null == array || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    System.out.println(array[i] + " " + array[j]);
                }
            }
        }
    }

}
