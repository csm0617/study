package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

public class E04Leetcode98_2 {
    long prev = Long.MIN_VALUE;//记录中序遍历的前一个值

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        //递归判断左子树
        boolean a = isValidBST(root.left);
        /*
         *  --优化--剪枝
         *                            5
         *                          /   \
         *                         1     4
         *                              / \
         *                             3   6
         *   影响性能的背景：
         *   基于上一版代码分析：
         *   当递归来到3的位置时，那么4的左边验证结果是false,这时候就应该返回最终的结果false,但是4的右孩子6还会继续执行递归，
         *   直到6的结果返回给了4，4才能把自己的结果返回给5。
         *   所以当左边的分支出现了false的时候，
         *   右边就没必要继续执行递归代码了
         *
         */
        if (!a){//当递归时，如果左侧已经不满足了，就应该直接返回当前节点的结果
            return false;
        }
        //值
        if (root.val <= prev) {
            return false;
        }
        prev = root.val;//比较后，立刻更新prev
        //递归判断右子树
//        boolean b = isValidBST(root.right);
//        return a && b;
        return isValidBST(root.right);
    }
}
