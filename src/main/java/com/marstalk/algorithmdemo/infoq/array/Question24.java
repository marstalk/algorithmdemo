package com.marstalk.algorithmdemo.infoq.array;

/**
 * 给定一个已排序的数组和一个数字 x，找到数组中和最接近 x 的元素对
 * 这个会比Question23简单一些，因为已经排好序了。
 */
public class Question24 {
    public static void main(String[] args) {
        int array[] = {-40, -5, 1, 3, 6, 7, 8, 20};
        //findPairWithClosestToXBruteForce(array,5);
        findPairWithClosestToX(array, 5);
    }

    /**
     * 时间复杂度是N
     *
     * @param array
     * @param x
     */
    private static void findPairWithClosestToX(int[] array, int x) {
        if (null == array) {
            return;
        }
        if (array.length < 2) {
            return;
        }

        int index1 = 0;
        int index2 = 0;
        int l = 0;
        int r = array.length - 1;
        int diff = Integer.MAX_VALUE;
        while (l < r) {
            int temDif = array[l] + array[r] - x;
            if (Math.abs(temDif) < Math.abs(diff)) {
                index1 = l;
                index2 = r;
                diff = temDif;
            }
            if (temDif > 0) {
                r--;
            }else{
                l++;
            }
        }
        System.out.println(array[index1] + " " + array[index2]);
    }
}
