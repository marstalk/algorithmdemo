package com.marstalk.algorithmdemo.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liujiacheng
 */
public class BstConvert {
    public static void main(String[] args) {
        Bst root = convert(Bst.sample);
        System.out.println(serializedLevelOrder(root));
    }

    private static int sum = 0;
    private static Bst convert(Bst root) {
        if (root == null) {
            return null;
        }

        root.right = convert(root.right);

        sum += root.val;
        root.val = sum;

        root.left = convert(root.left);
        return root;
    }






    private static String serializedLevelOrder(Bst root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<Bst> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final Bst poll = queue.poll();
            if (poll == null) {
                // 如果null节点，拼接就好了，不需要offer子节点了。
                sb.append("#").append(",");
                continue;
            }
            sb.append(poll.val).append(",");
            // 跟普通层序遍历不需要遍历null子节点，但是序列化需要拼接null子节点
            queue.offer(poll.left);
            queue.offer(poll.right);
        }
        return sb.toString();
    }
}
