package com.marstalk.algorithmdemo.binarytree;

public class TreeNode {
    public static TreeNode sample;

    static {

        final TreeNode one = new TreeNode(1, null, null);
        final TreeNode two = new TreeNode(2, one, null);
        final TreeNode four = new TreeNode(4, null, null);
        final TreeNode ten = new TreeNode(10, null, null);
        final TreeNode five = new TreeNode(5, four, ten);
        sample = new TreeNode(3, two, five);
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int val;

    public TreeNode left;
    public TreeNode right;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreeNode)) {
            return false;
        }
        TreeNode target = (TreeNode) obj;
        return checkEqual(this, target);
    }

    private boolean checkEqual(TreeNode n1, TreeNode n2) {
        // base case
        if (n1 == null && n2 == null) {
            return true;
        }

        // 如果只有其中一个是null，那么熔断返回false。
        if (n1 == null || n2 == null) {
            return false;
        }

        if (n1.val == n2.val) {
            // 如果父节点equals，那么继续判断左右子树。
            return checkEqual(n1.left, n2.left) && checkEqual(n1.right, n2.right);
        } else {
            // 如果父节点not equals，那么则熔断返回false。
            return false;
        }
    }

    public static void main(String[] args) {
        final TreeNode one = new TreeNode(1, null, null);
        final TreeNode two = new TreeNode(2, one, null);
        final TreeNode four = new TreeNode(4, null, null);
        final TreeNode eleven = new TreeNode(11, null, null);
        final TreeNode five = new TreeNode(5, four, eleven);
        TreeNode target = new TreeNode(3, two, five);
        System.out.println(TreeNode.sample.equals(target));
        System.out.println(TreeNode.sample.equals(TreeNode.sample));

    }
}
