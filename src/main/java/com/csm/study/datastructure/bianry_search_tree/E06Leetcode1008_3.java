package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.Arrays;

public class E06Leetcode1008_3 {
    public TreeNode bstFromPreorder(int[] preorder) {
        return partition(preorder, 0, preorder.length - 1);
    }

    /*
        分治思路：[8 , 5 , 1 , 7 , 10 , 12]
            根：8
            左：[5 1 7]
            右：[10 12]
            根：5
            左：[1]
            右：[7]
            根：10
            左：null
            右：[12]
     */
    private TreeNode partition(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);//先序遍历的第一个就是根节点
        int index = start + 1;
        while (index <= end) {//寻找划分左右子树的index
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        root.left = partition(preorder, start + 1, index - 1);//递归调用建立左子树
        root.right = partition(preorder, index, end);//递归调用建立右子树
        return root;

    }
}
