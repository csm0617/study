package com.csm.study.binary_search_tree;

import com.csm.study.datastructure.bianry_search_tree.structure.BSTTree1;
import org.junit.jupiter.api.Test;

public class TestBSTTree1 {
    public BSTTree1 create() {
        /*
                                4
                              /   \
                             2     6
                            / \   / \
                           1   3 5   7
         */

        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, "张无忌");
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, "宋青书");
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, "周芷若", n1, n3);

        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, "说不得");
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, "殷离");
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, "赵敏", n5, n7);
        BSTTree1.BSTNode root = new BSTTree1.BSTNode(4, "小昭", n2, n6);
        BSTTree1 tree = new BSTTree1();
        tree.root = root;
        return tree;

    }
    @Test
    public void get(){
        BSTTree1 tree = create();
        System.out.println(tree.get(1));
        System.out.println(tree.get(2));
        System.out.println(tree.get(3));
        System.out.println(tree.get(4));
        System.out.println(tree.get(5));
        System.out.println(tree.get(6));
        System.out.println(tree.get(7));
        System.out.println(tree.get(8));
        System.out.println(tree.get(9));
    }
    @Test
    public void get1(){
        BSTTree1 tree = create();
        System.out.println(tree.get1(1));
        System.out.println(tree.get1(2));
        System.out.println(tree.get1(3));
        System.out.println(tree.get1(4));
        System.out.println(tree.get1(5));
        System.out.println(tree.get1(6));
        System.out.println(tree.get1(7));
        System.out.println(tree.get1(8));
        System.out.println(tree.get1(9));
    }

    @Test
    public void minOrMax(){
        BSTTree1 tree = create();
        System.out.println(tree.min());
        System.out.println(tree.min1());
        System.out.println(tree.max1());
        System.out.println(tree.max());
    }
}
