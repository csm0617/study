package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode700 查找值为val的节点
 */
public class E03Leetcode700 {
    public TreeNode searchBST(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val>val){
           return searchBST(node.left,val);
        }else if (node.val<val){
            return searchBST(node.right,val);
        }else {
            return node;
        }
    }
}