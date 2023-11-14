package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * leetcode145进阶（使用非递归的方法，前中后序遍历二叉树）
 */
public class E02Leetcode145 {

    public static void main(String[] args) {
                        /*
                                                       1
                                                     /   \
                                                    2     3
                                                   / \   / \
                                                  4   7 5   6

                    前序：1 2 4 7 3 5 6
                    中序：4 2 7 1 5 3 6
                    后序：4 7 2 5 6 3 1
                    栈底 【 】 栈顶
                    下面是输出和栈的变化(从处理完左子树开始)
                    1.【1 2 4】        回：[]
                    2.【1 2】          回：[4]
                    3.【1 2 7】        回：[4]
                    4.【1 2】          回：[4 7]
                    5.【1】            回：[4 7 2]
                    6.【1 3】          回：[4 7 2]
                    7.【1 3 5】        回：[4 7 2]
                    8.【1 3】          回：[4 7 2 5]
                    9.【1 3 6】        回：[4 7 2 5]
                    10.【1 3】         回：[4 7 2 5 6]
                    11.【1】           回：[4 7 2 5 6 3]
                    12.【】            回：[4 7 2 5 6 3 1]
                    后序遍历分析：
                    【1 2 4】此时peek出4，4没有右孩子，可以直接弹出，【1 2】,现在弹出2，因为2有右孩子7，比较上次弹出的元素不是7，
                    说明2的右孩子7没有处理完，把7压栈【1 2 7】，因为7没有左孩子peek出7，但是7也没有右孩子，所以7出栈，【1 2】，
                    再peek出2，peek.right == pop，2的右孩子7和上次弹出的元素7相同，所以2可以出栈，【1】peek出1，
                    因为1的右孩子3!=上次出栈的2，所以要处理1的右孩子3，【1 3】有左孩子5，【1 3 5】，5没有左右孩子出栈，处理3的
                    右孩子6，【1 3 6】，6没有左右孩子出栈，【1 3】peek出3，3的右孩子和6相等，3可以弹出，【1】再peek出1，1的右孩子和3相等
                    1可以弹出【】

                 */
        //构建以上二叉树

        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
        /**
         * 实现的思路，不论是前中后哪种遍历的方式，遍历所走的路径都是相同的，使用栈来记录走过的路径
         */
        TreeNode curr = root;//curr记录当前节点的指针
        LinkedList<TreeNode> stack = new LinkedList<>();//用来记录走过路径的栈
        TreeNode pop = null;//!!!用来记录上一次弹栈的元素（后续遍历，关键点在于要把左右孩子都处理完了才能把自己弹出）
        while (curr != null || !stack.isEmpty()) {//走完当前节点的左子树,当curr为null走完了左子树，此时!stack.isEmpty()来返回走过的记录，回到最初的起点
            if (curr != null) {
                stack.push(curr);//记录路径
                curr = curr.left;
            } else {//走到else分支，左子树肯定处理完了，能不能把自己弹出要看右子树
                TreeNode peek = stack.peek();//peek出当前的栈顶元素
                if (peek.right == null || peek.right == pop) {//1.如果没有叶子节点，自己肯定就能弹出，2.如果上一次弹出元素是右孩子，那么右子树肯定处理完了，就把自己弹出
                    pop = stack.pop();//弹栈开始往回走
                    colorPrintln("回：" + pop.val, 34);
                } else {//如果右子树没处理完，那就先处理右子树走if逻辑
                    curr = peek.right;
                }

            }
        }
    }

    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }


}
