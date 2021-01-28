package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 34：找到数组中的 leader。
 * 我们需要打印数组中的所有 leader。Leader 元素大于它右侧的所有元素。
 * 分析：
 * 1，单指针，从右往左遍历。
 * 2，max = Integer.MIN_VALUE
 * 3，如果指针元素比max大
 */
public class Question34 {
    public static void main(String[] args) {
        int[] arr = {14, 12, 70, 15, 99, 65, 21, 90};
        findLeaders(arr);
    }

    private static void findLeaders(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i > -1; i--) {
            max = Math.max(arr[i], max);
            if (max == arr[i]) {
                System.out.println(arr[i]);
            }
        }
    }
}
