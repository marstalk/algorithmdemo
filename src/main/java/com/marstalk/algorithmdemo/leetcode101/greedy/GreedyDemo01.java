package com.marstalk.algorithmdemo.leetcode101.greedy;

import java.util.Arrays;

/**
 * 【最简单的贪心问题】
 * 有一群孩子和一堆饼干（两个数组），每个孩子有不同的饥饿度（非排序数组，可重复），<br/>
 * 每个饼干有不同的大小（非排序数组，可重复）。每个孩子只能吃一个饼干（1对1），<br/>
 * 且只有当饼干大于或者等于饥饿度的情况下才能吃饱（arr[i] >= arr[j])，问最多多少孩子能够吃饱？<br/>
 * <p>
 * 因为饥饿度最小的孩子是最容易满足的，所以应该对孩子进行升序排序，从饥饿度最小的孩子开始分配。<br/>
 * 为了使得剩下的饼干可以满足饥饿度更大的孩子，所以我们要把大于或者等于这个孩子饥饿度的，且大小是最小的饼干给这个孩子。<br/>
 * 将饼干升序排序，找到最小的符合条件的饼干。有一群孩子和一堆饼干（两个数组），每个孩子有不同的饥饿度（非排序数组，可重复），<br/>
 * 每个饼干有不同的大小（非排序数组，可重复）。每个孩子只能吃一个饼干（1对1），且只有当饼干大于或者等于饥饿度的情况下才能吃饱（arr[i] >= arr[j])，
 * 问最多多少孩子能够吃饱？
 */
public class GreedyDemo01 {

    public static void main(String[] args) {
        int[] boys = new int[]{1, 3, 6, 2, 9, 5};
        int[] cookies = new int[]{3, 8, 4, 2, 5, 9};
        findByGreedy(boys, cookies);
    }

    /**
     * 总的下来，时间复杂度是NlogN
     *
     * @param g
     * @param s
     */
    private static void findByGreedy(int[] g, int[] s) {
        //NlogN
        Arrays.sort(g);
        //NlogN
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            //只要还剩下小孩和饼干，就继续。
            if (s[j] >= g[i]) {
                System.out.println(g[i] + " 吃饼干： " + s[j]);
                i++;
                j++;
            } else {
                j++;
            }
        }
        System.out.println("最多能喂饱：" + i + " 个小孩");
    }
}
