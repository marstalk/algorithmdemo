package com.marstalk.algorithmdemo.infoq.array;

/**
 * 在未排序的数组中寻找第二大的元素。 【未排序的话，时间复杂度最小是logN】
 */
public class Question20 {
    
    public static void main(String[] args) {
        int[] arr1 = {7, 5, 6, 1, 4, 2};
        int secondHighest = findSecondLargestNumberInTheArray(arr1);
        System.out.println("Second largest element in the array : " + secondHighest);
    }
    
    /**
     * 定义两个变量分别存储最大值和第二大值（初始化是最小值） 遍历整个数组。 如果被遍历元素比最大值大，那么将最大值赋值给第二大值，当前元素赋值给最大值。继续遍历 如果被便立志比第二大元素大，那么当前元素复制给第二大值。继续遍历。
     *
     * @param arr1
     * @return
     */
    private static int findSecondLargestNumberInTheArray(int[] arr1) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] > max) {
                secondMax = max;
                max = arr1[i];
            } else if (arr1[i] > secondMax) {
                secondMax = arr1[i];
            }
        }
        return secondMax;
    }
}
