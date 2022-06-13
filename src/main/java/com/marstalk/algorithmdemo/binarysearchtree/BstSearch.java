package com.marstalk.algorithmdemo.binarysearchtree;

/**
 * 搜索
 * @author liujiacheng
 */
public class BstSearch {
    public static void main(String[] args) {
        System.out.println(find(Bst.sample, 1));
    }

    private static Bst find(Bst root, int target){
        if(root == null){
            return null;
        }

        if (root.val == target) {
            return root;
        } else if (target < root.val) {
            return find(root.left, target);
        } else {
            return find(root.right, target);
        }
    }
}
