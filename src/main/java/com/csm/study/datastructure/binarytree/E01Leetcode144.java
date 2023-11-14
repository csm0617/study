package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * leetcode144进阶（使用非递归的方法，前中后序遍历二叉树）
 */
public class E01Leetcode144 {

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
                    去的时候： 一直遍历到左子树的最左的叶子节点，打印的是走过的每一个节点（完成了对左子树和根的遍历）   栈底【1 2 4】栈顶
                    回的时候： 弹栈[4,2],当pop==2,因为2有右孩子7,curr = pop.right; if (curr != null)不成立，7会被压栈【1 7】
                             因为7没有左孩子了，7被栈弹出，栈【1】，此时1被栈弹出,有右孩子3，curr = pop.right，3被压栈【3】
                             3有左孩子5，栈【3 5】，5没有左右孩子了，5出栈，3出栈，有右孩子6，curr = pop.right，被压栈【6】，
                             6没有左右孩子了，3被弹栈
                    下面是输出和栈的变化
                    1.【1】       去：[1]               回：[]
                    2.【1 2】     去：[1 2]             回：[]
                    3.【1 2 4】   去：[1 2 4]           回：[]
                    4.【1】       去：[1 2 4]           回：[4 2]
                    5.【1 7】     去：[1 2 4 7]         回：[4 2]
                    6.【1】       去：[1 2 4 7]         回：[4 2 7]
                    7.【3】       去：[1 2 4 7 3]       回：[4 2 7 1]
                    8.【3 5】     去：[1 2 4 7 3 5]     回：[4 2 7 1]
                    9.【3】       去：[1 2 4 7 3 5]     回：[4 2 7 1 5]
                    10.【6】      去：[1 2 4 7 3 6]     回：[4 2 7 1 5 3]
                    11.【】       去：[1 2 4 7 3 6]     回：[4 2 7 1 5 3 6]
                    可以看到 去的 输出是前序遍历，而 回来 的输出是中序遍历，
                    分析为什么会产生这样的输出呢？
                    1.单独考虑“去”的输出(“去全部是压栈才会输出”)curr!=null,curr.left让遍历一直到左子树的最左输出结果 [1 2 4]此时curr==null了，
                    就会一直弹栈，直到弹出的元素是有右孩子的，curr==pop.right,被更新成了右孩子7，同时进行压栈和输出[1 2 4 7]，
                    继续寻找他的左孩子，有左孩子，就压栈输出，没有就会弹栈，最终会来到记录初始根节点，对根节点的右孩子3进行遍历
                    经过分析发现，输出的情况是 和前序遍历的逻辑一样，先输出自己节点再去看左孩子，没有左孩子了，再来到右孩子，循环往复

                    2.单独考虑“回”的输出，可以发现弹栈才会输出，因为压栈的时候是压到左子树的最左，弹栈的时候是从子树最左叶子节点开始弹的[4]，
                    下一个弹的是父节点2，如果父节点有右孩子7，就会把右孩子7的左子树先遍历一遍（假设7有左子树），再把右孩子7弹出，再去遍历右孩子的右子树，
                    和中序遍历的逻辑是一样的 有左孩子，就一直到最左的左孩子，没有左孩子了就输出自己，然后从右子树里找最左

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
        while (curr != null || !stack.isEmpty()) {//走完当前节点的左子树,当curr为null走完了左子树，此时!stack.isEmpty()来返回走过的记录，回到最初的起点
            if (curr != null) {
                colorPrintln("去：" + curr.val, 31);//31:red   //只注释这行就是就是中序遍历
                stack.push(curr);//记录路径
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();//弹栈开始往回走
                colorPrintln("回：" + pop.val, 34);//34:blue   //只注释掉这行就是前序遍历
                curr = pop.right;//如果有右孩子，就进到if 值左右 ，没有右孩子就弹栈（弹出的是当前的父节点）
            }
        }
    }

    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }


}
