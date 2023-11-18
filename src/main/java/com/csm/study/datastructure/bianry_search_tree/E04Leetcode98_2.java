package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

public class E04Leetcode98_2 {
    long prev = Long.MIN_VALUE;//记录中序遍历的前一个值

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        //递归判断左子树
        boolean a = isValidBST(root.left);
        //值
        if (root.val <= prev) {
            return false;
        }
        prev = root.val;//比较后，立刻更新prev
        //递归判断右子树
        boolean b = isValidBST(root.right);
        return a && b;
    }
}
