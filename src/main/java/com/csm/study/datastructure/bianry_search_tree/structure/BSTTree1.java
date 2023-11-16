package com.csm.study.datastructure.bianry_search_tree.structure;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

public class BSTTree1 {
    public BSTNode root;//根节点

    public static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 根据关键字查找对应值(递归)
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(int key) {
        return doGet(root, key);
    }

    private Object doGet(BSTNode node, int key) {
        //结束递归
        if (node == null) {
            return null;
        }
        if (key < node.key) {//往左找
            return doGet(node.left, key);
        } else if (node.key < key) {//往右找
            return doGet(node.right, key);
        } else {//找到了
            return node.value;
        }
    }

    /**
     * 根据关键字查找对应值（非递归）
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get1(int key) {
        BSTNode node = root;
        while (node != null) {
            if (node.key < key) {
                node = node.right;
            } else if (node.key > key) {
                node = node.left;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 查找最小关键字对应值（递归）
     *
     * @return 关键字对应的值
     */
    public Object min() {
        return doMin(root);
    }

    private Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    /**
     * 查找最小关键字对应值（非递归）
     *
     * @return 关键字对应的值
     */
    public Object min1() {
        if (root == null) {
            return null;
        }
        BSTNode p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     * 查找最大关键字对应值（递归）
     *
     * @return 关键字对应的值
     */
    public Object max() {
        return doMax(root);
    }

    private Object doMax(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMax(node.right);
    }

    /**
     * 查找最大关键字对应值（非递归）
     *
     * @return 关键字对应的值
     */
    public Object max1() {
        if (root == null) {
            return null;
        }
        BSTNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }


    /**
     * 存储关键字和对应值
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {

    }

    /**
     * 查找关键字的前驱值
     *
     * @param key 关键字
     * @return 前驱值
     */
    public Object successor(int key) {
        return null;
    }

    /**
     * 查找关键字的后继值
     *
     * @param key 关键字
     * @return 后继值
     */
    public Object predecessor(int key) {
        return null;
    }

    /**
     * 根据关键字删除
     *
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public Object delete(int key) {
        return null;
    }
}
