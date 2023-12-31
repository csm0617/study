package com.csm.study.binary_search_tree;

import com.csm.study.datastructure.bianry_search_tree.structure.BSTTree1;
import com.csm.study.datastructure.binarytree.structure.TreeNode;
import com.fasterxml.jackson.core.sym.Name3;
import jdk.nashorn.internal.objects.NativeFloat64Array;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.LinkedList;

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
    public void get() {
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
    public void get1() {
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
    public void minOrMax() {
        BSTTree1 tree = create();
        System.out.println(tree.min());
        System.out.println(tree.max());
    }

    @Test
    public void put() {
        BSTTree1 tree = new BSTTree1();
        tree.put(4, new Object());
        tree.put(2, new Object());
        tree.put(6, new Object());
        tree.put(1, "张无忌");
        System.out.println(tree.get(1));
        tree.put(3, new Object());
        tree.put(7, new Object());
        tree.put(5, new Object());
        tree.put(1, "教主张无忌");
        System.out.println(tree.get(1));
        System.out.println(tree.get(4));
    }

    @Test
    public void successor() {
        BSTTree1 tree = create();
        System.out.println(tree.successor(3));
        System.out.println(tree.successor(2));
        System.out.println(tree.predecessor(2));
        System.out.println(tree.predecessor(3));
    }

    @Test
    public void delete() {
        /*
                            4
                          /   \
                         2     7
                        / \   /
                       1   3 6
                            /
                           5
         */
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, null);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);
        BSTTree1 tree = new BSTTree1();
        tree.root = root1;
        System.out.println(tree.delete(7));
        System.out.println(tree.get(7));
    }

    @Test
    @DisplayName("删除叶子节点")
    public void delete1() {
        /*
                            4
                          /   \
                         2     7
                        / \   /
                       1   3 6
                            /
                           5
         */
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, null);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);
        BSTTree1 tree = new BSTTree1();
        tree.root = root1;
        System.out.println(tree.delete(1));
        System.out.println(tree);
        System.out.println(tree.delete(2));
        System.out.println(tree);
        System.out.println(tree.delete1(5));//递归打印
        System.out.println(tree);
    }

    @Test
    @DisplayName("删除只有左孩子或者右孩子的")
    public void delete2() {
        /*
                            4
                          /   \
                         2     7
                          \   /
                           3 6
                            /
                           5
         */
//        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1,1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, null, n3);
//        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2,2,n1, n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, null);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);
        BSTTree1 tree = new BSTTree1();
        tree.root = root1;
        System.out.println(tree.delete(7));
        System.out.println(tree);
        System.out.println(tree.delete1(2));
        System.out.println(tree);
    }

    @Test
    @DisplayName("删除有左右孩子且后继相邻的")
    public void delete3() {
        /*
                            4
                          /   \
                         2     7
                        / \   / \
                       1   3 6   9
                            /   / \
                           5   8   10
                                     \
                                     11
         */
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n8 = new BSTTree1.BSTNode(8, 8);
        BSTTree1.BSTNode n11 = new BSTTree1.BSTNode(11, 11);
        BSTTree1.BSTNode n10 = new BSTTree1.BSTNode(10, 10, null, n11);
        BSTTree1.BSTNode n9 = new BSTTree1.BSTNode(9, 9, n8, n10);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, n9);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);
        BSTTree1 tree = new BSTTree1();
        tree.root = root1;
        System.out.println(tree.delete(9));
        System.out.println(tree);
        System.out.println(tree.delete1(2));//递归删除
        System.out.println(tree);
    }

    @Test
    @DisplayName("删除有左右孩子且后继不相邻的")
    public void delete4() {
        /*
                            4
                          /   \
                         2     7
                        / \   / \
                       1   3 6   9
                            /   / \
                           5   8   10
                                     \
                                     11
         */
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n8 = new BSTTree1.BSTNode(8, 8);
        BSTTree1.BSTNode n11 = new BSTTree1.BSTNode(11, 11);
        BSTTree1.BSTNode n10 = new BSTTree1.BSTNode(10, 10, null, n11);
        BSTTree1.BSTNode n9 = new BSTTree1.BSTNode(9, 9, n8, n10);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, n9);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);
        BSTTree1 tree = new BSTTree1();
        tree.root = root1;
        System.out.println(tree.delete(7));
        System.out.println(tree);
        System.out.println(tree.delete1(2));//递归删除
        System.out.println(tree);
    }

    @Test
    public void less_greater_between() {
         /*
                            4
                          /   \
                         2     7
                        / \   / \
                       1   3 6   9
                            /   / \
                           5   8   10
                                     \
                                     11
         */
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, 1);
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, 3);
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, 2, n1, n3);
        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, 5);
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, 6, n5, null);
        BSTTree1.BSTNode n8 = new BSTTree1.BSTNode(8, 8);
        BSTTree1.BSTNode n11 = new BSTTree1.BSTNode(11, 11);
        BSTTree1.BSTNode n10 = new BSTTree1.BSTNode(10, 10, null, n11);
        BSTTree1.BSTNode n9 = new BSTTree1.BSTNode(9, 9, n8, n10);
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, 7, n6, n9);
        BSTTree1.BSTNode root1 = new BSTTree1.BSTNode(4, 4, n2, n7);
        BSTTree1 tree = new BSTTree1();
        tree.root = root1;
        System.out.println(Arrays.toString(tree.between(2, 9).toArray()));
        System.out.println(Arrays.toString(tree.between(8, 5).toArray()));
        System.out.println(Arrays.toString(tree.greater(5).toArray()));
        System.out.println(Arrays.toString(tree.greater(12).toArray()));
        System.out.println(Arrays.toString(tree.less(6).toArray()));
        System.out.println(Arrays.toString(tree.less(0).toArray()));
    }

}
