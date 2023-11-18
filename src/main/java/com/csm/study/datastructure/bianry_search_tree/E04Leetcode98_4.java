package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;
import org.apache.tomcat.util.modeler.NoDescriptorRegistry;

import java.util.concurrent.atomic.AtomicLong;

public class E04Leetcode98_4 {
    /*
        思路：上下限递归                   给每个节点的的取值范围找出来，出现不合理的取值就是false
                         4              4： [-∞,+∞]
                        / \
                       2   6            2： [-∞,4)    6：(4,+∞]
                          / \
                         3   7          3： (4,6)     7：(6,+∞)


     */
    public boolean isValidBST(TreeNode root) {
        return doValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doValid(TreeNode node, long min, long max) {
        //递归到节点为空返回null
        if (node == null) {
            return true;
        }
        //如果节点的值不在取值范围内，直接返回false
        if (node.val <= min || node.val >= max) {
            return false;
        }
        //递归判断是否左子树满足定义
        boolean a = doValid(node.left, min, node.val);
        //出现左子树不满足的情况就直接返回false（二叉树减枝）减少无意义递归
        if (!a){
            return false;
        }
        //左子树符合，那么就判断右子树的递归调用结果
        return doValid(node.right, node.val, max);
    }
}
