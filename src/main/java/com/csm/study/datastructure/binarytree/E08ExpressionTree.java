package com.csm.study.datastructure.binarytree;

import java.util.LinkedList;

/**
 * 根据后缀表达式构造表达式树
 */
public class E08ExpressionTree {
    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }
    /*
        中缀表达式    (2 - 1)*3
        后缀表达式    21-3*
        表达式树：
                        *
                       / \
                      -   3
                     / \
                    2   1
     */

    public TreeNode constructExpressionTree(String[] token) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String t : token) {
            switch (t) {
                case "+":
                case "-":
                case "*":
                case "/":
                    //遇到运算符就先从栈弹出两个元素（先弹出的是右孩子）
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    //将运算符作为父节点，建立父子关系
                    TreeNode parent = new TreeNode(t);
                    parent.right=right;
                    parent.left=left;
                    //再将运算符压栈
                    stack.push(parent);
                    break;
                default://数字就压栈
                    stack.push(new TreeNode(t));
                    break;
            }
        }
        //所有关系建立后，最后留在栈顶的只有一个运算符，就是根节点，返回
        return stack.peek();
    }
}
