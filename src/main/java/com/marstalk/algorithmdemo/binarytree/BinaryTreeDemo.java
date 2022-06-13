package com.marstalk.algorithmdemo.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树的多种遍历方式。
 *
 * @author liujiacheng
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        traverseAsc(TreeNode.sample);
        System.out.println("");
        traverseDesc(TreeNode.sample);
        System.out.println("");
        traverseBfs(TreeNode.sample);
        System.out.println("");
        traverseBfs2(TreeNode.sample);
        System.out.println("");

    }




    static void traverseBfs2(TreeNode h){
        final LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(h);
        int depth = 0;
        while (!queue.isEmpty()) {
            final int sz = queue.size();
            depth++;
            for (int i = 0; i < sz; i++) {
                final TreeNode cur = queue.poll();

                System.out.println("当前是第" + depth + "层" + "这一层有" + sz + "个节点");
                System.out.println(cur.val);

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
    }



    static void traverseBfs(TreeNode h){
        final LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(h);
        while (!queue.isEmpty()) {
            final TreeNode cur = queue.poll();
            System.out.println(cur.val);
            if (null != cur.left) {
                queue.offer(cur.left);
            }
            if (null != cur.right) {
                queue.offer(cur.right);
            }
        }
    }

    static void traverseDesc(TreeNode h){
        if (null == h) {
            return;
        }
        traverseDesc(h.right);
        System.out.println(h.val);
        traverseDesc(h.left);
    }


    static void traverseAsc(TreeNode h){
        if (null == h) {
            return;
        }
        traverseAsc(h.left);
        System.out.println(h.val);
        traverseAsc(h.right);
    }

}
