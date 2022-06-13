package com.marstalk.algorithmdemo.binarytree;

/**
 * 寻找目标节点的父节点。
 *
 * @author liujiacheng
 */
public class BinaryTreeDemoFindParent {
    public static void main(String[] args) {
        final int targetVal = 5;
        TreeNode parent = findParent(TreeNode.sample, targetVal);
        System.out.println("found parent of " + targetVal + " is " + parent.val);
    }

    /**
     * 主函数
     *
     * @param root
     * @param targetVal
     * @return
     */
    private static TreeNode findParent(TreeNode root, int targetVal) {
        return traverseTreeNode(root, null, targetVal);
    }

    /**
     * 辅助函数
     *
     * @param root
     * @param parent
     * @param targetVal
     * @return
     */
    private static TreeNode traverseTreeNode(TreeNode root, TreeNode parent, int targetVal) {
        // base case
        if (root == null) {
            return null;
        }

        // 前序遍历
        if (root.val == targetVal) {
            return parent;
        }

        // 左子树，如果找到则熔断。
        final TreeNode rtnLeft = traverseTreeNode(root.left, root, targetVal);
        if (rtnLeft != null) {
            return rtnLeft;
        }

        // 右子树
        return traverseTreeNode(root.right, root, targetVal);
    }
}
