package com.csm.study.datastructure.bianry_search_tree.structure;

/**
 * 二叉搜索树，泛型key版本
 */
public class BSTTree2<K extends Comparable<K>, V> {
    public BSTNode<K, V> root;//根节点

    public static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 根据关键字查找对应值
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public V get(K key) {
        return doGet(root, key);
    }

    private V doGet(BSTNode<K, V> node, K key) {
        //结束递归
        if (node == null) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result < 0) {//往左找
            return doGet(node.left, key);
        } else if (result > 0) {//往右找
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
    public V get1(K key) {
        BSTNode<K, V> p = root;
        while (p != null) {
            /*
                a.compareTo(b)
               -1       a<b
                0       a==b
                1       a>b
             */
            int result = key.compareTo(p.key);
            if (result > 0) {
                p = p.right;
            } else if (result < 0) {
                p = p.left;
            } else {
                return p.value;
            }
        }
        return null;
    }

    /**
     * 查找最小关键字对应值
     *
     * @return 关键字对应的值
     */
    public V min() {
        return null;
    }

    /**
     * 查找最大关键字对应值
     *
     * @return 关键字对应的值
     */
    public V max() {
        return null;
    }

    /**
     * 存储关键字和对应值
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(K key, V value) {

    }

    /**
     * 查找关键字的前驱值
     *
     * @param key 关键字
     * @return 前驱值
     */
    public V successor(K key) {
        return null;
    }

    /**
     * 查找关键字的后继值
     *
     * @param key 关键字
     * @return 后继值
     */
    public V predecessor(K key) {
        return null;
    }

    /**
     * 根据关键字删除
     *
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public V delete(K key) {
        return null;
    }
}
