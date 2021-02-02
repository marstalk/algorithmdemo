package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 40：股票买卖策略。
 * 给定整数数组，元素代表某日股价，找出一次交易可获得的最大利润。
 * 所以需要找出（买入日，卖出日）的元素对，买入日值小于等于卖出日值，并最大化利润。
 * 分析：
 * 元素从左到右代表了连续的先后的股票买入价格。
 * 所以题目要求的是找一个元素对，第一个元素是买入价格，第二个元素是卖出价格。
 * 使得卖出-买入的差额最大。
 */
public class Question40Imba {
    public static void main(String[] args) {
        int arr[] = {14, 12, 70, 15, 99, 65, 21, 90};
        findPair(arr);
        findPair2(arr);
    }

    /**
     * 方法二，不能通过NlogN的时间复杂度先排序。
     * 因为排序之后就乱了。
     *
     * @param arr
     */
    private static void findPair2(int[] arr) {
        int lowestPriceTillThatDay = arr[0];
        int maxProfit = Integer.MIN_VALUE;
        //{14, 12, 70, 15, 99, 65, 21, 90}
        int buy = 0;
        int sell = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < lowestPriceTillThatDay) {
                //这个价格更加低。更新买入价格。
                lowestPriceTillThatDay = arr[i];
                buy = i;
            } else if (arr[i] > lowestPriceTillThatDay) {
                //大过当前价格，可以卖，但是要不要，要比较profit是不是比当前maxProfit大
                int profit = arr[i] - lowestPriceTillThatDay;
                if (profit > maxProfit) {
                    maxProfit = profit;
                    sell = i;
                }
            }
        }
        System.out.println("buy at: " + arr[buy] + " sell at: " + arr[sell] + " cat make the maximum profit: " + maxProfit);
    }

    /**
     * 第一种思路，双层循环，找到所有的组合。并计算出左右的最大差值，时间复杂度是N*N
     *
     * @param arr
     */
    private static void findPair(int[] arr) {
        int profit = Integer.MIN_VALUE;
        int buy = 0;
        int sell = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int tem = arr[j] - arr[i];
                if (tem > profit) {
                    profit = tem;
                    buy = i;
                    sell = j;
                }
            }
        }
        System.out.println("buy at: " + arr[buy] + " sell at: " + arr[sell] + " cat make the maximum profit: " + profit);
    }
}
