package com.marstalk.algorithmdemo.infoq.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 查找数组中和等于给定数字的所有元素对
 */
public class Question25 {
    public static void main(String[] args) {
        int array[] = {-40, -5, 1, 3, 6, 7, 8, 20};
//        findPairsWithSumEqualsToXBruteForce(array, 15);
//        findPairsEqualsToX(array, 15);
        findPairsEqualsToXUsingHashing(array, 15);
    }

    /**
     * 全部放到hashMap中，key=element， value=index，为了避免使用同一个元素两次
     * 空间复杂度是N
     * 时间复杂度是N
     * @param array
     * @param target
     */
    private static void findPairsEqualsToXUsingHashing(int[] array, int target) {
        Map<Integer, Integer> hash = new HashMap<>(array.length * 3/4);
        for (int i = 0; i < array.length; i++) {
            hash.put(array[i], array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            if (hash.get(target - array[i]) != null && hash.get(target - array[i]) != i) {
                //将已经配对的数据从hash中删除。
                hash.remove(array[i]);
                hash.remove(target - array[i]);
                System.out.println(array[i] + " " + (target - array[i]));
            }
        }
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
            } else if (sum < target) {
                l++;
            } else {
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
