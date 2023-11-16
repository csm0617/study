package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.Arrays;

/**
 * leetcode106根据中后遍历结果建树（递归）
 */
public class E10Leetcode106 {
        /*
        inOrder   [4 2 1 6 3 7]
        postOrder [4 2 6 7 3 1]
        !!!主要解题思想是，一直递归分割postOrder和inOrder得到新的数组，根据新数组的postOder得到根节点，根据根节点建树
          根：1
              in                   post
          左  4,2                   4,2
          右  6,3,7                 6,7,3

          根：2
          左  4

          根：3
          左  6
          右  7
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //结束递归的条件：当数组被分割完了（长度为0）的时候就结束递归
        if (inorder.length == 0) {//(又因为postorder和inorder的长度始终保持一样，选一个作为判断条件即可)
            return null;
        }
        //1.从后序遍历中拿到根节点，后序遍历数组最后一个位置就是根节点
        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);
        //2.根据根节点分割中序遍历，拿到左右子树的中序遍历数组
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                //拿到左子树的中序遍历数组
                int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);
                //拿到右子树的中序遍历数组
                int[] rightInorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                //3.分割后序遍历的数组，拿到左右子树的后序遍历数组（根据中序数组分割的长度）
                int[] leftPostorder = Arrays.copyOfRange(postorder, 0, i);
                int[] rightPostorder = Arrays.copyOfRange(postorder, i, postorder.length - 1);
                //4.递归调用，根据左右子树的根节点建树
                root.left = buildTree(leftInorder, leftPostorder);
                root.right = buildTree(rightInorder, rightPostorder);
                //break跳出for循环，找到根节点了就没必要继续for循环了
                break;
            }
        }
        return root;
    }
}
