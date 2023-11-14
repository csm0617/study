package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCodeTraversalSolution {
    public static void main(String[] args) {
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
