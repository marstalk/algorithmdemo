package com.marstalk.algorithmdemo.leetcode300.最长递增子序列;

import java.util.Arrays;

/**
 * 重点是设计状态。
 * 这个有bug，需要改进。
 */
public class LeetCode300 {
    public static void main(String[] args) {
        final Solution solution = new Solution();
        //solution.lengthOfLIS(new int[]{0,1,0,3,2,3});
        solution.lengthOfLIS(new int[]{4,10,4,3,8,9});
    }
}
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++){
            int pre=-1;
            for(int j = i-1; j >-1; j--){
                if (nums[j] < nums[i]){
                    pre = j;
                    break;
                }
            }
            if(pre == -1){
                dp[i] = dp[i-1];
            }else{
                dp[i] = dp[i-1] > (dp[pre] + 1) ? dp[i-1] : (dp[pre] + 1);
            }
        }

        Arrays.stream(dp).forEach(System.out::println);
        return dp[nums.length-1];
    }
}