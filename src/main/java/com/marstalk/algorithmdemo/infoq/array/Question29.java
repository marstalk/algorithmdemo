package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 29：在数组中查找局部最小元素
 * 局部最小元素比其旁边的元素都小
 * https://www.jianshu.com/p/92279e303991
 * https://java2blog.com/find-local-minima-array/
 */
public class Question29 {
    public static void main(String[] args) {
        int[] arr = {10, 5, 3, 6, 13, 16, 7};
        int index = findLocalMinima(arr);
        System.out.println("Local Minima is: " + arr[index]);
    }

    /**
     * 从中间元素开始找（可以随机任何一个元素），如果它比两边的元素都要小，那么则返回（命中）
     * 如果左边的元素比较小，则左边肯定有局部最小值。
     * 如果右边的元素比较小，那么右边肯定有局部最小值。
     * 【重点】如果数组不保证无重复，那么事件复杂度至少是O(N)。
     * 如果数组保证了无重复，那么则可以使用logN来找到其中某个局部最小值（或者局部最大值）
     * @param arr
     * @return
     */
    private static int findLocalMinima(int[] arr) {
        //临界判断
        if (arr == null || arr.length == 1) {
            return -1;
        }
        //1个元素，前两个元素
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        //尾部元素。
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        //其他元素，随机找一个元素（取中间元素）
        int start = 1;
        int end = arr.length - 2;
        while (start < end) {
            int mid = start + (end - start) / 2;//或者(start + end)/2
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                end = mid ;
            } else if (arr[mid] + 1 < arr[mid]) {
                start = mid;
            }
        }
        return -1;
    }
}
