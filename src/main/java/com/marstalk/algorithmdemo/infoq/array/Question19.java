package com.marstalk.algorithmdemo.infoq.array;

/**
 * 在排序和旋转数组中寻找最小值。 问题18中，已经找了断点，那么断点下一个元素就是最小值。
 */
public class Question19 {
    
    public static void main(String[] args) {
        int arr[] = {16, 19, 21, 25, 3, 5, 8, 10};
        System.out.println(
                "Minimum element in the array : " + findMinimumElementRotatedSortedArray(arr, 0, arr.length - 1));
    }
    
    /**
     * 直接通过二分法来找到断点。
     * 然后最小值在断点的下一个位置上。
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int findMinimumElementRotatedSortedArray(int[] arr, int l, int r) {
        while (r - l > 1) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > arr[l]) {
                l = mid;
            }else{
                r = mid;
            }
        }
        return arr[l + 1];
    }
}
