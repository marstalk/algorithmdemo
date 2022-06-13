package com.marstalk.algorithmdemo.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * @author liujiacheng
 */
public class BinaryTreeSerializer {
    private static final String SEP = ",";
    private static final String NULL = "#";


    public static void main(String[] args) {
        //前序
        final String serialized = serializePreorder(TreeNode.sample);
        System.out.println("前序序列化" + serialized);
        TreeNode root = deserializedPreorder(serialized);
        System.out.println("前序反序列化" + root.equals(TreeNode.sample));

        // 中序：不支持
        System.out.println();
        String inorderString = serializedInorder(TreeNode.sample);
        System.out.println("中序序列化" + inorderString);
        TreeNode root2 = deserializedInorder(inorderString);

        // 后序
        System.out.println();
        String serializedPostorderString = serializedPostorder(TreeNode.sample);
        System.out.println("后序序列化" + serializedPostorderString);
        TreeNode rtn = deserializedPostorder(serializedPostorderString);
        System.out.println("后序反序列化" + rtn.equals(TreeNode.sample));

        // 层序
        System.out.println();
        String serializedLevelOrderString = serializedLevelOrder(TreeNode.sample);
        System.out.println("层序遍历序列化" + serializedLevelOrderString);
        System.out.println("同层遍历反序列化" + deserializedLevelOrder(serializedLevelOrderString).equals(TreeNode.sample));

    }

    /**
     * 层序遍历反序列哈
     * @param data
     * @return
     */
    private static TreeNode deserializedLevelOrder(String data){
        // 这里可以多联系几次。
        if (data == null) {
            return null;
        }

        final String[] nodes = data.split(SEP);

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for(int i = 1; i< nodes.length;){
            TreeNode parent = queue.poll();
            if (parent == null) {
                continue;
            }
            final String left = nodes[i++];
            if (!left.equals(NULL)) {
                parent.left = new TreeNode(Integer.parseInt(left));
                queue.offer(parent.left);
            }

            final String right = nodes[i++];
            if (!right.equals(NULL)) {
                parent.right = new TreeNode(Integer.parseInt(right));
                queue.offer(parent.right);
            }
        }
        return root;
    }

    /**
     * 层序遍历序列化主函数
     * @param root
     */
    private static String serializedLevelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode poll = queue.poll();
            if (poll == null) {
                // 如果null节点，拼接就好了，不需要offer子节点了。
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(poll.val).append(SEP);
            // 跟普通层序遍历不需要遍历null子节点，但是序列化需要拼接null子节点
            queue.offer(poll.left);
            queue.offer(poll.right);
        }
        return sb.toString();
    }




    private static TreeNode deserializedPostorder(String serializedPostorderString) {
        if (serializedPostorderString == null) {
            return null;
        }
        final String[] split = serializedPostorderString.split(SEP);
        final LinkedList<String> nodes = new LinkedList<>(Arrays.asList(split));
        return deserializedPostorder(nodes);
    }

    private static TreeNode deserializedPostorder(LinkedList<String> nodes) {
        // base case
        if (nodes.isEmpty()) {
            return null;
        }
        final String s = nodes.removeLast();
        if (s.equals(NULL)) {
            return null;
        }
        int val = Integer.parseInt(s);

        TreeNode root = new TreeNode(val);
        root.right = deserializedPostorder(nodes);
        root.left = deserializedPostorder(nodes);

        return root;
    }

    /**
     * 后序序列化主函数
     * @param sample
     * @return
     */
    private static String serializedPostorder(TreeNode sample) {
        StringBuilder sb = new StringBuilder();
        traversePostOrder(sample, sb);
        return sb.toString();
    }

    /**
     *  后序序列化辅助函数
     * @param root
     * @param sb
     */
    private static void traversePostOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        traversePostOrder(root.left, sb);
        traversePostOrder(root.right, sb);

        // 后序遍历
        sb.append(root.val).append(SEP);
    }

    /**
     * 无法判断根节点是哪个，所以无法进行发序列化。
     * @param inorderString
     * @return
     */
    private static TreeNode deserializedInorder(String inorderString) {
        System.out.println("Game over");
        return null;
    }


    private static String serializedInorder(TreeNode root) {
        StringBuilder inorderStringBuilder = new StringBuilder();
        traverseInorder(root, inorderStringBuilder);
        return inorderStringBuilder.toString();
    }

    private static void traverseInorder(TreeNode root, StringBuilder inorderStringBuilder) {
        if (root == null) {
            inorderStringBuilder.append(NULL);
            inorderStringBuilder.append(SEP);
            return;
        }
        traverseInorder(root.left, inorderStringBuilder);
        inorderStringBuilder.append(root.val);
        inorderStringBuilder.append(SEP);
        traverseInorder(root.right, inorderStringBuilder);
    }


    /**
     * 前序遍历反序列化主函数。
     * @param serialized
     * @return
     */
    private static TreeNode deserializedPreorder(String serialized) {
        // 反序列化主函数
        if (serialized == null) {
            return null;
        }
        final String[] split = serialized.split(SEP);
        final LinkedList<String> nodes = new LinkedList<>(Arrays.asList(split));
        return deserializedPreorder(nodes);
    }

    /**
     * 前序遍历反序列化辅助函数
     * 为什么递归入参不用数组呢？（String[] arr, int lo, int hi）因为数组需要告诉左右子节点的开始和结束位置，计算起来有点儿麻烦。
     * 这里使用了一个可以removeFirst的LinkedList来刚好对应的SpringBuilder.append，优雅。
     * @param nodes
     * @return
     */
    private static TreeNode deserializedPreorder(LinkedList<String> nodes) {
        // base case
        if (nodes.isEmpty()) {
            return null;
        }

        final String s = nodes.removeFirst();

        // root
        if (s.equals(NULL)) {
            return null;
        }

        int val = Integer.parseInt(s);
        final TreeNode root = new TreeNode(val);
        root.left = deserializedPreorder(nodes);
        root.right = deserializedPreorder(nodes);
        return root;
    }

    /**
     * 前序遍历序列化主函数
     * @param root
     * @return
     */
    static String serializePreorder(TreeNode root){
        StringBuilder sb = new StringBuilder();
        serializedPreorder(root, sb);
        return sb.toString();
    }

    /**
     * 前序遍历序列化辅助函数
     * @param root
     * @param sb
     */
    static void serializedPreorder(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            sb.append(NULL);
            sb.append(SEP);
            return;
        }

        // 前序遍历
        sb.append(root.val);
        sb.append(SEP);

        // 递归处理左右子树
        sb.append(serializePreorder(root.left));
        sb.append(serializePreorder(root.right));
    }
}
