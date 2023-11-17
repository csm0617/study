package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode450 删除二叉搜索树的节点（递归）
 */
public class E01Leetcode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        return delete(root,key);
    }

    public TreeNode delete(TreeNode node,int key){
        if (node == null) {
            return null;
        }
        if (node.val > key) {
            node.left = deleteNode(node.left, key);
            return node;
        }
        if (node.val < key) {
            node.right = deleteNode(node.right, key);
            return node;
        }
        //找到了，分为3种情况
        // 情况1，只有右孩子
        if (node.left == null) {
            return node.right;//返回给上次的递归建立父子关系
        }
        // 情况2，只有左孩子
        if (node.right == null) {
            return node.left;//返回给上次的递归建立父子关系
        }
        //情况3，既有左孩子，又有右孩子,那么找他的后继
        TreeNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        //如果后继是不相邻的，先处理后继的后事
        s.right = deleteNode(node.right, s.val); //作为后继s的右孩子（因为这里是在做后继节点的善后，所以随便new 一个 new ArrayList<>()，不做任何记录）
        //同时把
        s.left = node.left;

        return s;
    }
}
