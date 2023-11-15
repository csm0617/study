package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

public class E06Leetcode111_1 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int d1 = minDepth(root.left);
        int d2 = minDepth(root.right);
        /*
                     1              1
                   /                  \
                  2                    2
            如果代码还是像求最大深度那样，当节点不为空时直接返回的是左右子树深度最小的那个+1就会出问题
            !!!像上面的例子，当出现只有最左子树的情况时return Integer.min(d1, d2) + 1就会==1，
            但是根据最小深度的定义，深度应该是2。只有右子树时同理
                                 1
                                / \
                               2   3
                              /   / \
                             4   5   6
            这个例子，如果按照错误的逻辑 1的左子树的最小深度就会被计算成2，右侧就会被计算成3，但是根据定义这个二叉树的最小高度就是3
         */
        //1.当只有右子树，当前节点的深度应该是右子树的深度+1（节点自己）
        if (d1 == 0) {
            return d2 + 1;
        }
        //2.当只有左子树，当前节点的深度应该是左子树的深度+1（节点自己）
        if (d2 == 0) {
            return d1 + 1;
        }
        //3.当左右子树都有深度时，才应该取两者最小的再+1（节点自己）
        return Integer.min(d1, d2) + 1;
    }
}
