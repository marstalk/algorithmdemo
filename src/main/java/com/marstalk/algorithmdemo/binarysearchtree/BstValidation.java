package com.marstalk.algorithmdemo.binarysearchtree;

/**
 * 判断是否是合法二叉树。
 *
 * @author liujiacheng
 */
public class BstValidation {
    public static void main(String[] args) {

        final Bst one = new Bst(1, null, null);
        final Bst two = new Bst(2, one, null);
        final Bst four = new Bst(4, null, null);
        final Bst ten = new Bst(10, null, null);
        final Bst five = new Bst(5, ten, four);
        Bst fault = new Bst(3, two, five);
        System.out.println("利用中序遍历形成上下边界：");
        System.out.println(preOrder(Bst.sample));
        System.out.println(preOrder(fault));

        System.out.println("利用中序遍历（升序）特性：");
        System.out.println(inorder(Bst.sample));
        System.out.println(inorder(fault));
    }

    /**
     * 前序遍历（上下边界）
     * @param root
     * @return
     */
    static boolean preOrder(Bst root) {
        return preOrder(root, null, null);
    }
    private static boolean preOrder(Bst root, Bst min, Bst max) {
        if (root == null) {
            return true;
        }

        // 前序遍历，当前值作为左子树的最大节点，同时也是右子树的最大节点
        // 违反任意值，则中断。
        if (min != null && root.val < min.val) {
            return false;
        }
        if (max != null && root.val > max.val) {
            return false;
        }

        return preOrder(root.left, min, root) && preOrder(root.right, root, max);
    }


    /**
     * 中序遍历（升序）特性
     */
    static boolean res = true;
    static Bst pre;
    static boolean inorder(Bst root){
        traverse(root);
        return res;
    }

    private static void traverse(Bst root) {
        if (root == null) {
            return;
        }
        traverse(root.left);

        if (pre != null && root.val <= pre.val) {
            res = false;
        }
        pre = root;

        traverse(root.right);
    }
}
