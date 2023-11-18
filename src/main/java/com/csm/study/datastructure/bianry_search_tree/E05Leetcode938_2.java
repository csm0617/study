package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode938二叉搜索树的范围和（上下限递归）
 */
public class E05Leetcode938_2 {
    /*
        思路：上下限法递归
     */
    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        if (node.val < low) {//如果节点的val已经小于low，节点左侧都是小于low,不在范围内
            //剪枝--将节点的右侧进行递归结果返回
            return rangeSumBST(node.right, low, high);
        }
        if (node.val > high) {//如果节点的val已经大于high，节点右侧都是大于high,不在范围内
            //剪枝--将节点的左侧进行递归结果返回
            return rangeSumBST(node.left, low, high);
        }
        //节点的val在范围把自己的值和左右孩子递归的结果累加
        return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
    }
}
