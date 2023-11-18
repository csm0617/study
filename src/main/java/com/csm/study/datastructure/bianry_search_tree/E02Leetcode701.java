package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode701二叉搜索树的插入（输入数据保证新值和原始二叉搜索树中的任意节点值都不同）
 * 也就是说插入只做新增不做修改
 */
public class E02Leetcode701 {
    public TreeNode insertIntoBST(TreeNode node, int val) {
        //一直找到为空的位置插入
        if (node == null) {
            return new TreeNode(val);
        }
        if (node.val > val) {
            //建立父子关系
            node.left = insertIntoBST(node.left, val);
        } else {
            //建立父子关系
            node.right = insertIntoBST(node.right, val);
        }
        return node;//因为给的函数里面要求有返回，node.left = insertIntoBST(node.left, val)这一步在递归返回的时候会沿着路径建立重复的父子关系
    }
}
