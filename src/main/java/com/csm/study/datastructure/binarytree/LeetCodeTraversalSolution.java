package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCodeTraversalSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
        treeTraversal(root);
    }

    /**
     * 前中后统一的遍历模板
     *
     * @param root
     * @return
     * @注意打印的时机就行
     */
    public static void treeTraversal(TreeNode root) {
        TreeNode curr = root;//记录当前节点的指针
        LinkedList<TreeNode> stack = new LinkedList<>();//记录curr指针走过的路
        TreeNode pop = null;//记录上次弹出的节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                colorPrintln("前序:" + curr.val, 31);//前序遍历，先打印节点，再处理左子树，处理完左子树再处理右子树
                curr = curr.left;//处理左子树
            } else {//左子树处理完了
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {
                    colorPrintln("中序：" + peek.val, 36);//中序遍历和后序遍历，在没有右子树的情况下打印是相同的
                    pop = stack.pop();
                    colorPrintln("后序：" + pop.val, 34);

                }
                //右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后序：" + pop.val, 34);//处理完右子树再打印是后序遍历
                }
                //待处理右子树
                else {
                    colorPrintln("中序：" + peek.val, 36);//处理完左子树，右子树还没处理，此时打印是中序遍历
                    curr = peek.right;
                }
            }
        }
    }

    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    /**
     * 前序遍历（非递归）
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        TreeNode curr = root;//curr记录当前节点的指针
        LinkedList<TreeNode> stack = new LinkedList<>();//用来记录走过路径的栈
        while (curr != null || !stack.isEmpty()) {//走完当前节点的左子树,当curr为null走完了左子树，此时!stack.isEmpty()来返回走过的记录，回到最初的起点
            if (curr != null) {
                values.add(curr.val);
                stack.push(curr);//记录路径
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();//弹栈开始往回走
//                    colorPrintln("回：" + pop.val, 34);//34:blue   //只注释掉这行就是前序遍历
                curr = pop.right;//如果有右孩子，就进到if 值左右 ，没有右孩子就弹栈（弹出的是当前的父节点）
            }
        }
        return values;
    }

    /**
     * 后序遍历（非递归）
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
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
                    values.add(pop.val);
                } else {//如果右子树没处理完，那就先处理右子树走if逻辑
                    curr = peek.right;
                }
            }
        }
        return values;
    }

    /**
     * 中序遍历（非递归）
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        TreeNode curr = root;//curr记录当前节点的指针
        LinkedList<TreeNode> stack = new LinkedList<>();//用来记录走过路径的栈
        while (curr != null || !stack.isEmpty()) {//走完当前节点的左子树,当curr为null走完了左子树，此时!stack.isEmpty()来返回走过的记录，回到最初的起点
            if (curr != null) {
                stack.push(curr);//记录路径
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();//弹栈开始往回走
//                    colorPrintln("回：" + pop.val, 34);//34:blue   //只注释掉这行就是前序遍历
                values.add(pop.val);
                curr = pop.right;//如果有右孩子，就进到if 值左右 ，没有右孩子就弹栈（弹出的是当前的父节点）
            }
        }
        return values;
    }
}
