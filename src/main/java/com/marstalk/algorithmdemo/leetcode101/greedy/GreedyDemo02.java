package com.marstalk.algorithmdemo.leetcode101.greedy;

import java.util.Arrays;

/**
 * //TODO 一群孩子站成一排，每一个孩子有自己的评分。现在需要给这些孩子发糖果，规则是<br/>
 * 如果一个孩子的评分比自己身旁的一个孩子要高，那么这个孩子就必须得到比身旁孩子更多的糖果；<br/>
 * 所有孩子至少要有一个糖果。求解最少需要多少个糖果。<br/>
 * 【重点】数组元素顺序不能变。
 */
public class GreedyDemo02 {
    public static void main(String[] args) {
        int[] ratings = new int[]{1, 2, 2};
        calculateMinimum(ratings);
        ratings = new int[]{1, 0, 2};
        calculateMinimum(ratings);
    }


    private static void calculateMinimum(int[] ratings) {
        int[] apples = new int[ratings.length];
        for (int i = 0; i < apples.length; i++) {
            apples[i] = 1;
        }

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                apples[i] = apples[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i > 0; i--) {
            if (ratings[i] > ratings[i + 1] && apples[i] < apples[i + 1]) {
                apples[i] = apples[i + 1] + 1;
            }
        }
        int sum = Arrays.stream(apples).sum();
        System.out.println(sum);
    }
}
