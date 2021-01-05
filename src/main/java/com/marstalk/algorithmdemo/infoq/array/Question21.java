package com.marstalk.algorithmdemo.infoq.array;

/**
 * 本题会给定一个整数数组，其中所有数字都会出现偶数次，只有一个例外。
 * 本题需要找到出现奇数次数的数字，方案限定在 o(n)的时间复杂度和 o(1)的空间复杂度内。
 */
public class Question21 {
    
    public static void main(String[] args) {
        int array[] = new int[] {20, 40, 50, 40, 50, 20, 30, 30, 50, 20, 40, 40, 20};
        System.out.println("Number which occurs odd number of times is : " + getOddTimesElement(array));
    }
    
    private static int getOddTimesElement(int[] array) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            result = result ^ array[i];
        }
        return result;
    }
    
}
