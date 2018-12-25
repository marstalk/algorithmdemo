package com.marstalk.knapsack.knapsackof01;

/**
 * @author Mars
 * Created on 2018/10/12
 */
public class Demo {
    private static int[] ws = {4, 6, 5, 7, 3};
    private static int[] vs = {12, 10, 8, 11, 14};
    private static int itemCount = 4;

    public static void main(String[] args) {
        int maxValue = b(itemCount, 18);
        System.out.println(maxValue);
    }

    private static int b(int k, int w) {
        if (k < 0) {
            return 0;
        }

        if (w <= 0) {
            return 0;
        }
        int stealValue = b(k - 1, w - ws[k]) + vs[k];
        int notStealValue = b(k - 1, w);

        if (stealValue > notStealValue) {
            System.out.println(stealValue);
            return stealValue;
        } else {
            System.out.println(notStealValue);
            return notStealValue;
        }
    }
}
