package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.LinkedList;

/**
 * leetcode938二叉搜索树的范围和（非递归实现）
 */
public class E05Leetcode938_1 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        TreeNode p = root;
        int sum = 0;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (pop.val > high) {//当遍历到>high了就不用继续遍历了（因为二叉搜索树的中序遍历是升序的）
                    break;
                }
                if (pop.val >= low) {
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;

    }
}
