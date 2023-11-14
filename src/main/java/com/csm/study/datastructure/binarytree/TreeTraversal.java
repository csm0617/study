package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

public class TreeTraversal {
    public static void main(String[] args) {
        /*
                        1
                      /   \
                     2     3
                    /     / \
                   4     5   6
         */
        //构建以上二叉树
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
        System.out.println("前序遍历:先打印自己，再打印左子树，后打印右子树");
        preOrder(root);
        System.out.println("\n中序遍历:先打印左子树，再打印自己，最后打印右子树");
        inOrder(root);
        System.out.println("\n后序遍历:先打印左子树，在大约右子树，最后打印自己");
        postOrder(root);


    }

    /**
     * 前序遍历（递归）
     * 先打印自己，再递归调用打印左孩子，然后右孩子
     *
     * @param treeNode 节点
     */
    static void preOrder(TreeNode treeNode) {
        //先序，重点是先打印自己
        if (treeNode != null) {
            System.out.print(treeNode.val + "\t");
        }
        //再递归调用打印左孩子
        if (treeNode.left != null) {
            preOrder(treeNode.left);
        }
        //再递归调用打印右孩子
        if (treeNode.right != null) {
            preOrder(treeNode.right);
        }
    }

    /**
     * 中序遍历（递归）
     * 先递归调用打印左孩子，再打印自己，最后递归调用打印右孩子
     *
     * @param treeNode 节点
     */
    static void inOrder(TreeNode treeNode) {
        //中序，重点是先递归调用左孩子
        if (treeNode != null) {
            if (treeNode.left != null) {
                inOrder(treeNode.left);
            }
            //左孩子结束了就打印自己
            System.out.print(treeNode.val + "\t");
            //再递归调用右孩子
            if (treeNode.right != null) {
                inOrder(treeNode.right);
            }
        }
    }

    /**
     * 后序遍历（递归）
     * 先递归调用打印完左孩子，再递归调用打印有孩子，左右都结束了最后打印自己
     *
     * @param treeNode 节点
     */
    static void postOrder(TreeNode treeNode) {
        //后序，重要的是最后打印自己
        if (treeNode != null) {
            //先递归调用左孩子
            if (treeNode.left != null) {
                postOrder(treeNode.left);
            }
            //再递归调用右孩子
            if (treeNode.right != null) {
                postOrder(treeNode.right);
            }
            //当左右孩子都没有了，就打印自己
            System.out.print(treeNode.val + "\t");
        }
    }
}
