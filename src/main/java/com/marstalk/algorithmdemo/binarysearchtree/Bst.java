package com.marstalk.algorithmdemo.binarysearchtree;

/**
 * 二叉搜索树
 * @author liujiacheng
 */
public class Bst {
    public static Bst sample;

    static {

        final Bst one = new Bst(1, null, null);
        final Bst two = new Bst(2, one, null);
        final Bst four = new Bst(4, null, null);
        final Bst ten = new Bst(10, null, null);
        final Bst five = new Bst(5, four, ten);
        sample = new Bst(3, two, five);
    }

    public Bst(int val) {
        this.val = val;
    }

    public Bst(int val, Bst left, Bst right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int val;

    public Bst left;
    public Bst right;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bst)) {
            return false;
        }
        Bst target = (Bst) obj;
        return checkEqual(this, target);
    }

    private boolean checkEqual(Bst n1, Bst n2) {
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

    @Override
    public String toString() {
        return "value=" + this.val + ", left=" + this.left.val + ", right=" + this.right.val;
    }

    public static void main(String[] args) {
        final Bst one = new Bst(1, null, null);
        final Bst two = new Bst(2, one, null);
        final Bst four = new Bst(4, null, null);
        final Bst eleven = new Bst(11, null, null);
        final Bst five = new Bst(5, four, eleven);
        Bst target = new Bst(3, two, five);
        System.out.println(Bst.sample.equals(target));
        System.out.println(Bst.sample.equals(Bst.sample));

    }
}
