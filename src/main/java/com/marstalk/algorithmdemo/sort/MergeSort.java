package com.marstalk.algorithmdemo.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author liujiacheng
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 1, 2, 8, 9, 7, 0};
        mergeSort(arr, 0, arr.length - 1);
//        swap(arr, 0, 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (r - l < 2) {
            return;
        }
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        // 合并两边的结果即可。【后序】
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        while (l < r) {
            if (arr[l] > arr[m + 1]) {
                swap(arr, l, m + 1);
            }else{
                l++;
            }
        }
    }

    private static void swap(int[] arr, int l, int i) {
        arr[l] = arr[l] ^ arr[i];
        arr[i] = arr[l] ^ arr[i];
        arr[l] = arr[l] ^ arr[i];
    }
}
