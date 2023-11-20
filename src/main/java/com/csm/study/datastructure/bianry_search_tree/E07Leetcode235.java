package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode235 求二叉搜索树中两个指定节点的最近公共祖先
 */
public class E07Leetcode235 {
    /*
                            -- 6 --
                           /       \
                          2         8
                         / \       / \
                        0   4     7   9
                           / \
                          3   5
          待查找节点 p q 在某一结点的两侧，那么此节点就是最近的公共祖先
          !!!当p和q在同一侧，且相邻（例如4和5），那么p就是最近公共祖先
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode a = root;
        while (p.val < a.val && q.val < a.val || a.val < p.val && a.val < q.val) {//如果是在同一侧，p,q都小于当前节点，就往左找，都大于当前节点就往右找。
            if (p.val < a.val) {
                a = a.left;
            } else {
                a = a.right;
            }
        }
        //最终a来到p或者q的位置,或者a,在p,q的两侧了就退出循环了
        return a;//此时的a就是最近的公共祖先
    }
}
