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
        //1.要找key的前驱，先找key
        BSTNode p = root;
        BSTNode parent = null;//记录key的父节点
        while (p != null) {
            if (p.key < key) {
                parent = p;
                p = p.right;
            } else if (p.key > key) {
                parent = p;
                p = p.left;

            } else {
                break;//找到了就退出循环
            }
        }
        //2.判断此时的key是否找到
        if (p == null) {
            return null;//没找到返回null
        }
        //找到了执行删除操作
        //情况3，左右孩子都没有（包含在情况1和2里了）
        if (p.left == null) {//情况1，没有左孩子
            shift(parent, p, p.right);
        } else if (p.right == null) {//情况2，没有右孩子
            shift(parent, p, p.left);
        } else {//情况4：左右孩子都有
            /*
             那就让被删除节点的 后继 去顶替被删除节点的位置
                被删除节点的后继有两种情况
                a.和被删除节点相邻（也就是是被删除节点的右孩子，可以直接托孤）
                b.和被删除节点不相邻（需要处理后继节点的后事）
                      7                          7
                     /                          /
                    4                          5
                  /  \         -->           /   \
                 2    5                     2     6
                / \    \                   / \
               1   3    6                 1   3
                                情况a

             */
            //4.1找到被删除节点的后继节点(因为左右子树都右，找后继只需要到右子树找最小值就可以了)
            BSTNode s = p.right;
            BSTNode sParent = p;//sParent来记录后继节点的父节点
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            //后继节点即为s
            if (sParent != p) {//不相邻
                //4.2当删除节点和后继节点不相邻的时候，处理后继节点的后事（此时s必然只有右孩子，没有左孩子，因为s是p的后继节点（后继节点必然是右子树中最左的节点））
                shift(sParent, s, s.right);//把后继节点s的右孩子,托孤给它的父节点sParent;
                s.right = p.right;//把被删除节点p的右孩子，给后继s

            }
            //4.3后继节点顶替被删除节点
            shift(parent, p, s);
            s.left = p.left;//把p的左子树给s
        }
        return p.value;
    }

    /**
     * 托孤方法
     *
     * @param parent  被删除的节点的父节点
     * @param deleted 被删除的节点
     * @param child   顶上去的那个节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {//父节点是null，说明被删除的是根节点
            root = child;
            return;
        }
        if (parent.left == deleted) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }
}
