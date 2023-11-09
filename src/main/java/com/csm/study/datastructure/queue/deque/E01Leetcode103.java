package com.csm.study.datastructure.queue.deque;

import com.csm.study.datastructure.queue.LinkedListQueue;
import com.csm.study.datastructure.queue.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode103二叉树的锯齿形（z字型）遍历
 */
public class E01Leetcode103 {
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
        int c1 = 1;//当前层节点数
        while (!queue.isEmpty()) {
            int c2 = 0;//下一层节点数
            for (int i = 0; i < c1; i++) {
                //队头元素出队
                TreeNode n = queue.poll();
                System.out.print(n + "\t");//TreeNode重写了toString
                //判断  队头的左右孩子是不是为空，不为空则依次将左右孩子加入队列
                if (n.left != null) {
                    queue.offer(n.left);  //[ 2 ]
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);// [ 2 3 ]
                    c2++;
                }
            }
            //内层循环结束
            c1 = c2;
            System.out.println();
        }
    }


    //LeetCode103二叉树的z字形遍历，奇数层顺序，偶数层逆序
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        //传过来的是空节点
        if (root == null) {
            return result;
        }
        //构造一个存放节点的链式队列
//        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        //先将根节点加入到队列中
        queue.offer(root);      //[ 1 ]
        //遍历队列，直到队列为空
        int c1 = 1;//当前层节点数(第一层默认为1)
        boolean ood = true;//默认第一层是奇数层
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int c2 = 0;//下一层节点数
            for (int i = 0; i < c1; i++) {
                //队头元素出队
                TreeNode n = queue.poll();
                if (ood) {
                    level.offerLast(n.val);
                } else {
                    level.offerFirst(n.val);
                }

                //判断  队头的左右孩子是不是为空，不为空则依次将左右孩子加入队列
                if (n.left != null) {
                    queue.offer(n.left);  //[ 2 ]
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);// [ 2 3 ]
                    c2++;
                }
            }
            //内层循环结束
            ood=!ood;//奇数层过了，就取反变偶数层
            result.add(level);
            c1 = c2;
        }
        return result;
    }
}
