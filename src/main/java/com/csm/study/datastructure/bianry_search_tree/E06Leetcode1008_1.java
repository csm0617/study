package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode1008 根据前序遍历结果建树（逐个插入）
 * @题目说明：
 * @preoder的长度>=1
 * @preoder中没有重复元素
 *
 */
public class E06Leetcode1008_1 {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {//找到为空的位置了，就插入
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);//在左侧找到空位就在左侧建立节点关系
        }
        if (val > node.val) {
            node.right = insert(node.right, val);//在右侧找到空位就在右侧建立节点关系
        }
        return node;//最终返回node
    }
}
