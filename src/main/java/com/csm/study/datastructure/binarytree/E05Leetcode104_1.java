package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode104 求二叉树的最大深度（考察的是递归和后续遍历）
 */
public class E05Leetcode104_1 {
    /*
        总结之前二叉树的题型：
            1.但凡涉及到二叉树的广度的，基本上都需要配合队列
            2.涉及到二叉树的深度的，基本上都是递归，有时候需要配合栈来完成
     */

    /*
        思路：
            1.得到左子树深度，得到右子树深度，二者最大者+1，就是本节点的深度
            2.因为需要先得到左右子树的深度，很明显是后序遍历的典型应用
            3.关于节点最大深度的定义：是指从根节点到最远叶子节点的最长路径上的节点数。（和教材不一样，教材根节点定义为0）
     */
    public int maxDepth(TreeNode node) {
        //如果是空节点深度为0
        if (node == null) {
            return 0;
        }
        //如果是叶子节点
        if (node.left == null && node.right == null) {//深度为1
            return 1;
        }
        //不是叶子节点和空节点
        return Integer.max(maxDepth(node.left), maxDepth(node.right)) + 1;//递归调用返回左，右子树中深度大的那个再+1（因为算上自己，深度要+1）
    }

}
