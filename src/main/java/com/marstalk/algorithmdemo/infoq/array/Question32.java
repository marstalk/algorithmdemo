package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 32：在数组中查找等于给定和的子数组。
 * 给定一个【非负】整数数组和一个数字。需要打印和等于给定整数的子数组的所有起始和结束索引。
 */
public class Question32 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 4, 9, 0, 11};
        int target = 9;
        solveEfficient(arr, target);
    }

    /**
     * 因为要找的是子数组，所以呢，我们可以用start和end指针来找到子数组，
     * start和end都是从0开始，tem sum = arr[0]
     * 如果 tem sum == target，那么打印，并且end+1，判断是否越界，如果没有越界，则加上新加入的元素。
     * 如果tem sum > target，说明我们窗口的太大了，需要减小，所以先减去start位置的元素，再让start左移。
     * 如果tem sum < target，说明我们的窗口太小了，需要增大，所以end++，如果没有越界，则加上新加入的元素。
     * 【重点】这个算法成立的前提是元素是【非负】的。
     *
     * @param arr
     * @param target
     */
    private static void solveEfficient(int[] arr, int target) {
        int start = 0;
        int end = 0;
        int temSum = arr[0];
        while (start < arr.length && end < arr.length) {
            if (temSum == target) {
                System.out.println("start index: " + start + " end index: " + end);
                end++;
                if (end < arr.length) {
                    //如果没有越界，则加上新加入的元素。
                    temSum += arr[end];
                }
            } else if (temSum < target) {
                end++;
                if (end < arr.length) {
                    //如果没有越界，则加上新加入的元素。
                    temSum += arr[end];
                }
            } else {
                //先减再移动。
                temSum -= arr[start];
                start++;
            }
        }
    }
}
