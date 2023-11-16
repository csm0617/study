package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode226反转二叉树（递归）
 */
public class E07Leetcode226 {
    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    public void fn(TreeNode root) {
        //递归出口
        if (root == null) {
            return;
        }
        //交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //递归调用，将左子树的左右子树交换
        fn(root.left);
        //递归调用，将右子树的左右子树交换
        fn(root.right);
    }


}
