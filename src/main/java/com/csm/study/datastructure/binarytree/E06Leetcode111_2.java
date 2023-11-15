package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * leetcode111求二叉树的最小深度（非递归）
 */
public class E06Leetcode111_2 {
    /*
      思路：使用层序遍历求最小深度最巧妙的地方在于，只要出现了叶子节点的层数就是所求的最小深度
                                 1
                                / \
                               2   3
                                  / \
                                 5   6
    */
    public int minDepth(TreeNode root) {
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
            depth++;//结束了一行depth就++
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.pop();
//                System.out.printf("%-4d", poll.val);
                if (poll.left == null && poll.right == null) {
                    return depth;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
//            System.out.println();//一等结束了就换行

        }
        return depth;
    }
}
