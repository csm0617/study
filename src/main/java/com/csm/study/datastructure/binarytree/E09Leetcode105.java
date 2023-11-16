package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

import java.util.Arrays;

/**
 * leetcode105根据前中遍历结果建树（递归）
 */
public class E09Leetcode105 {
    /*
        preOrder   [1 2 4 3 6 7]
        inOrder    [4 2 1 6 3 7]
        !!!主要解题思想是，一直递归分割preOrder和inOrder得到新的数组，根据新数组的preOder得到根节点，根据根节点建树
          根：1
              pre                   in
          左  2,4                   4,2
          右  3,6,7                 6,3,7

          根：2
          左  4

          根：3
          左  6
          右  7
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //结束递归的条件：当数组被分割为空的时候就结束递归
        if (preorder.length==0){//(又因为preorder和inorder的长度始终保持一样，选一个作为判断条件即可)
            return null;
        }
        //1.从前序遍历中拿到根节点，前序遍历结果数组0位置就是根节点
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        //2.根据根节点分割中序遍历，拿到左右子树的中序遍历数组
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                //拿到左子树的中序遍历数组
                int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);//Arrays.copyOfRange复制的区间是左闭右开[)
                //拿到右子树的中序遍历数组
                int[] rightInorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                //3.分割前序遍历的数组，拿到左右子树的前序遍历数组（根据中序数组分割的长度）
                int[] leftPreorder = Arrays.copyOfRange(preorder, 1, i + 1);
                int[] rightPreorder = Arrays.copyOfRange(preorder, i + 1, preorder.length);
                //4.递归调用，根据左右子树的根节点建树
                root.left = buildTree(leftPreorder, leftInorder);
                root.right = buildTree(rightPreorder, rightInorder);
                //break跳出for循环，找到根节点了就没必要继续for循环了
                break;
            }
        }
        return root;
    }
}
