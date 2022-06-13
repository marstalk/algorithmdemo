package com.marstalk.algorithmdemo.sort;

/**
 * 快速排序
 * @author liujiacheng
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4,6,1,2,8,9,33,7,0,3};
        quickSort(arr, 0, arr.length -1);
    }

    static void quickSort(int[] arr, int left, int right){
        int p = partition(arr, left, right);
    }

    private static int partition(int[] arr, int left, int right) {

        int[] tmp = new int[right - left + 1];

        return 0;
    }
}
