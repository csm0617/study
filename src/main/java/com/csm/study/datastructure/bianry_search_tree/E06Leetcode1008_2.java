package com.csm.study.datastructure.bianry_search_tree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;
import com.sun.org.glassfish.external.amx.AMX;

import java.util.Arrays;

public class E06Leetcode1008_2 {
    /*
        上限法：[8,5,1,7,10,12]
     */


    public TreeNode bstFromPreorder(int[] preorder) {
        return insert(preorder, Integer.MAX_VALUE);
    }


    /*
        依次处理preorder的每个值，返回创建好的节点或者null
            1.如果超过上限返回null作为孩子返回
            2.如果没超过上限，创建节点，并设置其左右孩子，左右孩子完整后返回
     */
    int i = 0;
    public TreeNode insert(int[] preorder, int max) {
        if (i == preorder.length) {//当i来到数组结束的时候时,返回null
            return null;
        }
        int val = preorder[i];
        if (val > max) {//当val超过上限时,返回null
            return null;
        }
        TreeNode node = new TreeNode(val);//没有超过上限，就可以创建这个节点
        i++;//当数组中的元素被成功创建了节点,i++
        node.left = insert(preorder, val);//递归调用，将node的左右节点创建完成后再返回
        node.right = insert(preorder, max);
        return node;
    }
}
