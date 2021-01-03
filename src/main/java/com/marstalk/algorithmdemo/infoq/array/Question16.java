package com.marstalk.algorithmdemo.infoq.array;

/**
 * 16,编写 Java 程序以查找数组中最小和最大的元素
 * https://java2blog.com/java-program-to-find-smallest-and-largest-number-in-array/
 * @author shanzhonglaosou
 */
public class Question16 {
    public static void main(String[] args) {
        int arr[] = new int[]{12,56,76,89,100,343,21,234};
        System.out.println(findSmallest(arr));
    }

    /**
     * 因为数组尚未排序，所以最小的复杂度是N，即每个元素至少要遍历一次。
     * 这个问题可以扩展为找最大值，最小值。
     * 入参不管是数组、链表都是一样的效率。
     * @param arr
     * @return
     */
    private static int findSmallest(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int smallest = arr[0];
        int biggest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            } else if (arr[i] > biggest) {
                biggest = arr[i];
            }
        }
        return smallest;
    }
}
