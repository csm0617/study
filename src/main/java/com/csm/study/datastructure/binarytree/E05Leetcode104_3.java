package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * leetcode104 求二叉树的最大深度（层序遍历）
 */
public class E05Leetcode104_3 {
    /*
       思路：使用层序遍历，层数就是最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            /*
                二叉树的层序遍历难度在于：如何对每一层的元素进行分层？
                这里很巧妙利用了队列
                一开始把根节点加入到队列中 size==1
                当经过size.for()循环时把队列里的元素出队，同时把下一层的元素又全部都加入到了队列中，size==下一层元素的个数
                然后又经过size.for把队列里的元素出队，把下一层元素全部入队，size始终表示着下一层元素的个数，当个数为0就结束循环了，此时也遍历完了
             */
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.pop();
                System.out.printf("%-4d", poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            System.out.println();//一等结束了就换行
            depth++;//结束了一行depth就++
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(8,
                        3, 7),
                        2,
                        new TreeNode(6,
                                4, 5)),
                1,
                new TreeNode(
                        new TreeNode(5,
                                4, 6),
                        2,
                        new TreeNode(7,
                                3, 8))
        );
        E05Leetcode104_3 e05Leetcode1043 = new E05Leetcode104_3();
        System.out.println(e05Leetcode1043.maxDepth(root));
    }
}
