package com.csm.study.datastructure.queue;

import com.csm.study.datastructure.queue.structure.TreeNode;

/**
 * 二叉树的层序遍历（也不一定非要是二叉的）
 */
public class E01Leetcode102 {

    public static void main(String[] args) {
        //构造一个二叉树
        /*
                        1
                     /     \
                    2       3
                  /   \   /   \
                 4     5 6     7
         */
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4),
                        2,
                        new TreeNode(5)),
                1,
                new TreeNode(
                        new TreeNode(6),
                        3,
                        new TreeNode(7))
        );
        //构造一个存放节点的链式队列
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        //先将根节点加入到队列中
        queue.offer(root);      //[ 1 ]
        //遍历队列，直到队列为空
        while (!queue.isEmpty()) {
            //队头元素出队
            TreeNode n = queue.poll();
            System.out.println(n);//TreeNode重写了toString
            //判断  队头的左右孩子是不是为空，不为空则依次将左右孩子加入队列
            if (n.left != null) {
                queue.offer(n.left);  //[ 2 ]
            }
            if (n.right != null) {
                queue.offer(n.right);// [ 2 3 ]
            }
        }
    }
}
