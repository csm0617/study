package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * leetcode104 求二叉树的最大深度（非递归）
 */
public class E05Leetcode104_2 {
    public int maxDepth(TreeNode root) {
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;
        int depth = 0;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                if (stack.size() > depth) {
                    depth = stack.size();
                }
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return depth;
    }

}
