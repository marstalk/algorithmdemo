package com.marstalk.algorithmdemo.binarysearchtree;

/**
 * @author liujiacheng
 */
public class BstFindKSmall {
    private static int cnt;
    public static void main(String[] args) {
        findKsmall(Bst.sample, 2);
    }

    private static void findKsmall(Bst sample, int target) {
        traverse(sample, target);
    }

    private static void traverse(Bst sample, int target) {
        if (sample == null) {
            return;
        }
        traverse(sample.left, target);
        cnt++;
        if (cnt == target) {
            System.out.println(sample.val);
        }
        traverse(sample.right, target);
    }
}
