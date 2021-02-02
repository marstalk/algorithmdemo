package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 41：找出数组中后面较大元素与前面较小元素的最大差值
 * 给定整数数组，找出后面较大元素与前面较小元素的最大差值
 * <p>
 * 分析：
 * 同40
 */
public class Question41 {
    public static void main(String[] args) {
        int arr[] = {14, 12, 70, 15, 95, 65, 22, 30};
        findMaxProfit(arr);
    }

    /**
     * 只遍历一次：时间复杂度是N
     * 记录最大差值是Integer.MIN_VALUE;
     * 记录最小值。
     *
     * @param arr
     */
    private static void findMaxProfit(int[] arr) {
        int lowestPriceTillThatDay = arr[0];
        int maxProfit = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > lowestPriceTillThatDay) {
                //大过最小的价格，那么计算一下是不是最大的差值
                int tem = arr[i] - lowestPriceTillThatDay;
                if (tem > maxProfit) {
                    maxProfit = tem;
                    end = i;
                }
            } else {
                lowestPriceTillThatDay = arr[i];
                start = i;
            }
        }
        System.out.println("max profit " + maxProfit + " = " + arr[end] + "-" + arr[start]);
    }
}
