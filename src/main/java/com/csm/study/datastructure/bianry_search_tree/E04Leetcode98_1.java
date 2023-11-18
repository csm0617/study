package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * 验证是不是二叉搜索树
 */
public class E04Leetcode98_1 {
    /*
        思路：对二叉搜索树中序遍历会得到一个升序序列
        [1 2 3 4 5 6] 对一棵树进行中序遍历，对比上一个数的值 如果出现比前一个小，则不是二叉搜索树
        假设第一个数的前一个值为Long.MIN_VALUE
     */
    public boolean isValidBST(TreeNode root) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;//记录上个节点的值,初始值设定最小
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {//左边处理完了
                TreeNode pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                //处理值
                p = pop.right;
            }

        }
        return true;
    }
}

