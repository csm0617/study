package com.csm.study.datastructure.binarytree.structure;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int left, int val, int right) {
        this.val = val;
        this.left= new TreeNode(null,left,null);
        this.right = new TreeNode(null,right,null);
    }

    public TreeNode(TreeNode left, int val, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
