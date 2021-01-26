package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 32：在数组中查找等于给定和的子数组。
 * 给定一个非负整数数组和一个数字。需要打印和等于给定整数的子数组的所有起始和结束索引。
 */
public class Question32 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 4 ,9 ,0 ,11};
        int target = 9;
        solveEfficient(arr, target);
    }

    /**
     * 因为要找的是子数组，所以呢，我们可以用start和end指针来找到子数组，
     * start和end都是从0开始，
     * 如果start和end相等，且该元素大于目标，则双双往前移动。break
     * 如果start和end相等，且该元素等于目标，则start和end是要找的。break
     * 如果start和end相等，且该元素小于目标，则end往前一步。
     * 如果start比end小：
     *      如果所有元素
     * @param arr
     * @param target
     */
    private static void solveEfficient(int[] arr, int target) {

    }
}
