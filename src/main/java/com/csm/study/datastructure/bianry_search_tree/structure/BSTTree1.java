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
        return min(root);
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
    public Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
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
        return max(root);
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
    public Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }


    /**
     * 存储关键字和对应值（非递归） 有就修改，没有就新增
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {
        //2.查找这个关键字
        BSTNode node = root;
        BSTNode parent = null;//记录如果需要新增节点的父节点
        while (node != null) {
            parent = node;
            if (node.key < key) {
                node = node.right;
            } else if (node.key > key) {
                node = node.left;
            } else {
                //2.1找到了就修改
                node.value = value;
                return;
            }
        }
        //2.2找不到就新增
        if (parent == null) {//parent为null说明传进来的树的是空的
            root = new BSTNode(key, value);
            return;
        }
        //比父节点关键字小，就加到左孩子
        if (parent.key > key) {
            parent.left = new BSTNode(key, value);
            //比父节点关键字大，就加到右孩子
        } else {
            parent.right = new BSTNode(key, value);
        }

    }

    /**
     * 查找关键字的前驱值
     *
     * @param key 关键字
     * @return 前驱值
     */
    public Object successor(int key) {
        //1.要找key的前驱，先找key
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (p.key < key) {
                //当要右拐了，说明p来自左，记录ancestorFromLeft
                ancestorFromLeft = p;
                p = p.right;
            } else if (p.key > key) {
                p = p.left;

            } else {
                break;//找到了就退出循环
            }
        }
        //2.判断此时的key是否找到
        if (p == null) {
            return null;//没找到返回null
        }
        /*
            找到了,分为两种情况
         */
        //情况1:如果p的左子树不为空，那么p的左子树的最大key就是p的前驱
        if (p.left != null) {
            return max(p.left);
        }
        //情况2:如果p的左子树为空，那么距离p最近的一个来自左边的祖先节点就是p的前驱
        /*
            这里很巧妙的运用了在找key的时候发生右拐，那么说明右拐之前的节点就是来自左的祖先节点
         */
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 查找关键字的后继值
     *
     * @param key 关键字
     * @return 后继值
     */
    public Object predecessor(int key) {
        //1.要找key的前驱，先找key
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (p.key < key) {
                p = p.right;
            } else if (p.key > key) {
                //当要左拐了，说明p来自右，记录ancestorFromLeft
                ancestorFromRight = p;
                p = p.left;

            } else {
                break;//找到了就退出循环
            }
        }
        //2.判断此时的key是否找到
        if (p == null) {
            return null;//没找到返回null
        }
        /*
            找到了,分为两种情况
         */
        //情况1:如果p的右子树不为空，那么p的右子树的最小key就是p的前驱
        if (p.right != null) {
            return min(p.right);
        }
        //情况2:如果p的右子树为空，那么距离p最近的一个来自右边的祖先节点就是p的前驱
        /*
            这里很巧妙的运用了在找key的时候发生左拐，那么说明左拐之前的节点就是来自右的祖先节点
         */
        return ancestorFromRight != null ? ancestorFromRight.value : null;
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
