package com.marstalk.algorithmdemo.infoq.array;

/**
 * 本题会给定一个包含 1 到 n 的整数数组，但少了 1 到 n 中的某个数字。需要提供找到丢失数字的最佳方案。数组中的数字不能重复。
 * 数组没有说明是排好序的，那么就是未排序的。
 *
 */
public class Question17 {
    public static void main(String[] args) {
        int[] arr1={7,5,6,1,4,2};
        System.out.println("Missing number from array arr1: "+missingNumber(arr1));
        int[] arr2={5,3,1,2};
        System.out.println("Missing number from array arr2: "+missingNumber(arr2));

        System.out.println(missingNumberAdvance());
    }

    /**
     * 模拟溢出的情况
     * integer.MAX = 21亿左右，
     * n*(1+n)/2 = 21亿，求解得：30,000左右。
     * @return
     */
    private static boolean missingNumberAdvance() {
        //1 31 thousand length array
        int length = 46_000;
        int[] arr = new int[length];
        System.out.println(length*(1+length)/2);
        return false;
    }

    /**
     * 1到N是等差数列，可以快速计算出数列之和。
     * 然后将题目中的元素加起来，相减即可。
     * 时间复杂是N。在未排序的数组中，复杂度一般都是N。
     * //TODO【但是在大数据量的情况下要考虑溢出的情况。】
     *
     * @param arr
     * @return
     */
    private static int missingNumber(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        //数组长度+1才是完整的等差数列的长度。
        int n = arr.length + 1;
        int sum = n * (1 + n) / 2;

        int sum2 = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum2 += arr[i];
        }
        return sum - sum2;
    }
}
