package com.csm.study.binary_search_tree;

import com.csm.study.datastructure.bianry_search_tree.structure.BSTTree2;
import org.junit.jupiter.api.Test;

public class TestBSTTree2 {
    public BSTTree2 create() {
        /*
                                4
                              /   \
                             2     6
                            / \   / \
                           1   3 5   7
         */

        BSTTree2.BSTNode<String,String> n1 = new BSTTree2.BSTNode("a", "张无忌");
        BSTTree2.BSTNode<String,String> n3 = new BSTTree2.BSTNode("c", "宋青书");
        BSTTree2.BSTNode<String,String> n2 = new BSTTree2.BSTNode("b", "周芷若", n1, n3);

        BSTTree2.BSTNode<String,String> n5 = new BSTTree2.BSTNode("e", "说不得");
        BSTTree2.BSTNode<String,String> n7 = new BSTTree2.BSTNode("f", "殷离");
        BSTTree2.BSTNode<String,String> n6 = new BSTTree2.BSTNode("g", "赵敏", n5, n7);
        BSTTree2.BSTNode<String,String> root = new BSTTree2.BSTNode("d", "小昭", n2, n6);
        BSTTree2<String,String> tree = new BSTTree2();
        tree.root = root;
        return tree;

    }
    @Test
    public void get(){
        BSTTree2 tree = create();
        System.out.println(tree.get("a"));
        System.out.println(tree.get("b"));
        System.out.println(tree.get("c"));
        System.out.println(tree.get("d"));
        System.out.println(tree.get("e"));
        System.out.println(tree.get("f"));
        System.out.println(tree.get("g"));
        System.out.println(tree.get("h"));
        System.out.println(tree.get("i"));
    }
    @Test
    public void get1(){
        BSTTree2 tree = create();
        System.out.println(tree.get1("a"));
        System.out.println(tree.get1("b"));
        System.out.println(tree.get1("c"));
        System.out.println(tree.get1("d"));
        System.out.println(tree.get1("e"));
        System.out.println(tree.get1("f"));
        System.out.println(tree.get1("g"));
        System.out.println(tree.get1("h"));
        System.out.println(tree.get1("i"));
    }
}
