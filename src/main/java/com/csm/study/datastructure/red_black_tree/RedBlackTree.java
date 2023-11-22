package com.csm.study.datastructure.red_black_tree;

import static com.csm.study.datastructure.red_black_tree.RedBlackTree.Color.BLACK;
import static com.csm.study.datastructure.red_black_tree.RedBlackTree.Color.RED;

/**
 * 红黑树
 */
public class RedBlackTree {
    /*
        红黑树特性：
            1.所有节点都有两种颜色：红与黑
            2.所有null都视为黑色
            3.红色节点不能相邻
            4.根节点是黑色
            5.从根到任意一个叶子节点，路径中的黑色节点数一样（黑色完美平衡）

        ps:当叶子节点是黑色时，没有兄弟节点，应该把null作为黑色节点补全才好判断是不是红黑树（黑色节点不应该作为单独的叶子节点出现，红色的可以）
     */
    enum Color {
        RED, BLACK;
    }

    Node root;//根节点

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;//父节点
        Color color = RED;//新节点默认都为红色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        //3个常用的工具方法
        //1.是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        //2.叔叔
        Node uncle() {
            if (parent == null || parent.parent == null) {//如果没有父亲或者没有爷爷，那么肯定没有叔叔，返回null
                return null;
            }
            //有爷爷
            if (parent.isLeftChild()) {//如果父亲是爷爷的左孩子，那么叔叔是爷爷的右孩子
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        //兄弟
        Node sibling() {
            if (parent == null) {//如果没有父亲
                return null;
            }
            //有父亲
            if (this.isLeftChild()) {//如果自己是左孩子
                //那么兄弟是父亲的右孩子
                return parent.right;
            } else {//否则兄弟是否则的左孩子
                return parent.left;
            }
        }
    }

    //判断节点的红、黑
    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    boolean isBlack(Node node) {
//        return !isRed(node);
        return node == null || node.color == BLACK;
    }


    //右旋 1.处理parent 2.旋转后新根的父子关系
    private void rightRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.left;//yellow右旋上位节点
        Node green = yellow.right;//yellow右孩子需要交给pink处理
        if (green != null) {
            green.parent = pink;
        }
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;
        //如果pink本来就是根节点（根节点的parent为null）
        if (parent == null) {
            root = yellow;//上位的yellow成为新的根节点
        } else if (parent.left == pink) {//如果pink不是根节点，那么建立父子关系。
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    //左旋
    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;//yellow左旋上位节点
        Node green = yellow.left;//yellow左孩子需要交给pink处理
        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        //如果pink本来就是根节点（根节点的parent为null）
        if (parent == null) {
            root = yellow;//上位的yellow成为新的根节点
        } else if (parent.left == pink) {//如果pink不是根节点，那么建立父子关系。
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    /**
     * 新增或更新
     * 正常增、遇到红红不平衡进行调整
     *
     * @param key   键
     * @param value 值
     */
    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        if (p != null) {
            parent = p;//每进行下一轮循环前记录新增节点的parent
            if (key < p.key) {//往左找
                p = p.left;
            } else if (key > p.key) {//往右找
                p = p.right;
            } else {//找到了就更新
                p.value = value;
            }
        }
        //p==null找到空的位置了
        Node inserted = new Node(key, value);
        if (parent == null) {//如果parent还是为null说明root是null，那么让新来的节点成为根节点
            root = inserted;
            return;
        }
        if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;//别忘记把新增节点的父亲属性建立关系
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);

    }

    /**
     * 如果新增后出现红红的相邻的情况，那么修复
     *
     * @param x 节点
     */
    void fixRedRed(Node x) {

        //新增节点后颜色可能出现的情况：
        //case1：如果新增的节点是根节点，修改为黑色（因为默认新增节点的颜色都是红色）
        if (x == root) {
            x.color = BLACK;
            return;
        }
        //case2：如果新增节点的父亲是黑色，那么不需要做任何调整，既不会违反红红相邻，也不会违反根到任意一个叶子节点，路径中的黑色节点数一样（红黑树特性5）
        if (isBlack(x.parent)) {
            return;
        }
        //新增节点的父亲是红色，会触发红红相邻，细分为case3和case4
        //case3：叔叔为红色
            /*
                解决方案：变色
                1.父亲变为黑色，为了保证黑色的平衡，连带叔叔也变为黑色
                2.祖父是黑色如果不变，就会造成当前这颗子树的黑色过多，因此祖父节点变为红色
                3.祖父如果变成红色，可能会接着触发红红相邻，因此需要对祖父进行递归调整
            */
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {//叔叔是红色
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
        }
        //新增节点的父亲是红色，会触发红红相邻，细分为case3和case4
        //case4:叔叔是黑色
        if (parent.isLeftChild() && x.isLeftChild()) {//4.1父亲为左孩子，插入节点也是左孩子，此时即LL不平衡
            /*
                1.把父亲先变为黑色，祖父变为红色
                2.祖父变红会造成，到叔叔路径的黑色节点数变少
                3.祖父右旋（此时父亲（黑色）上位，黑色节点数恢复）
             */
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild() && !x.isLeftChild()) {//4.2父亲为左孩子，插入节点是右孩子，此时即LR不平衡
            /*
                1.先左旋父节点（变成LL,新增节点上位取代父节点）
                2.此时x成为了新的父节点，变成黑色
                3.祖父变成红色
                4.祖父右旋
                结果是父节点成了新增节点的左孩子，祖父成了新增节点的右孩子
                            黑
                           /  \
                          红   红
             */
            leftRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (!parent.isLeftChild() && !x.isLeftChild()) {//4.3父亲为右孩子，插入节点也是右孩子，此时即RR不平衡
            /*
                1.把父亲先变为黑色，祖父变为红色
                2.祖父变红会造成，到叔叔路径的黑色节点数变少
                3.祖父左旋（此时父亲（黑色）上位，黑色节点数恢复）
             */
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        } else {//4.4父亲为右孩子，插入节点是左孩子，此时即RL不平衡
            /*
                1.先右旋父节点（变成RR,新增节点上位取代父节点）
                2.此时x成为了新的父节点，变成黑色
                3.祖父变成红色
                4.祖父左旋
                结果是父节点成了新增节点的左孩子，祖父成了新增节点的右孩子
                            黑
                           /  \
                          红   红
             */
            rightRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    /**
     * 查找节点
     *
     * @param key 键
     * @return 找到返回节点，找不到返回null
     */
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 根据被删除节点，查找剩下的节点
     *
     * @param deleted 被删除节点
     * @return 删剩下的节点
     */
    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {//删除的delete是叶子节点
            return null;
        }
        if (deleted.left == null) {//左孩子为空
            return deleted.right;//返回右孩子
        }
        if (deleted.right == null) {//右孩子为空
            return deleted.left;//返回左孩子
        }
        //deleted左右孩子都有
        //找deleted的后继s
        Node s = deleted.right;//寻找的起点
        while (s.left != null) {
            s = s.left;
        }
        return s;//s肯定只有右孩子
    }

    /**
     * 正常删、会用到李代桃僵技巧、遇到黑黑不平衡进行调整
     *
     * @param key 键
     */
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    private void doRemove(Node deleted) {
        /*
            两件事情分别做，不要既在处理删除的问题的时候，又想着怎么处理颜色的问题
            先处理删除节点的情况，再在考虑处理颜色问题
            //被删除的是根节点


            被删除的节点是叶子节点
                1.如果是黑色的，那肯定要处理颜色问题，删了一个黑色叶子节点，到这个黑色叶子节点的路径就会少1（null也可被视为黑，双黑）

                    这种情况下还有一种简单的case，如果兄弟节点也是黑色，那么就把父节点变黑，兄弟节点变红

                2.如果是红色的，不会造成颜色失衡，不做处理
            被删除的节点不是叶子节点,且被删除的节点只有一个孩子
                1.如果被删除的节点是黑色，孩子（也就是剩下的节点replaced）也是黑色，需要进行复杂处理（双黑）
                2.case2如果被删除的节点是黑色，孩子（也就是剩下的节点replaced）是红色，那么李代桃僵
         */

        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;//被删节点的父节点
        //deleted没有孩子
        if (replaced == null) {
            //case1:删除的是根节点
            //如果deleted的是根节点，且没有孩子
            if (deleted == root) {
                root = null;
            } else {//不是根节点（因为没有孩子，只能是叶子节点）
                if (isBlack(deleted)) {//是黑色的叶子节点,触发双黑
                    //复杂调整,注意调用实际先调整deleted了，再删除deleted
                    fixDoubleBlack(deleted);
                } else {
                    //红色叶子不会失衡，不用任何处理
                }
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;//help gc
            }
            return;
        }
        //deleted有一个孩子
        if (deleted.left == null || deleted.right == null) {
            //case1:删除的是根节点
            //如果deleted的是根节点，且只有一个孩子（说明这个孩子只能是红色）
            if (deleted == root) {
                //让孩子的k,v替换掉root的k,v
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;//这里也不讨论root是只有左孩子还是右孩子，统统置null
            } else {//不是根节点，有一个孩子，就建立deleted的父亲parent和孩子replaced的双向关系
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.parent = deleted.left = deleted.right = null;//help gc
                if (isBlack(deleted) && isBlack(replaced)) {//如果被删除的节点是黑色，另外一个孩子也是黑色
                    //复杂处理,注意调用实际，先删除了再调整，处理的是replaced
                    fixDoubleBlack(replaced);
                } else {//删的是黑 剩下的 是红
                    replaced.color = BLACK;//变成黑色
                }

            }
            return;
        }
        //case0 有两个孩子  转化为 ===> 有一个孩子 或者 没有孩子
        // 因为当deleted有两个孩子的时候，findReplaced返回的是他的后继s,s肯定没有左孩子，可能会有右孩子
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }

    /**
     * 处理双黑的情况（双黑），因为双黑删除了一个黑色节点，就会导致性质5路径上黑色节点少1
     *
     * @param x 待调整节点
     */
    //case3、case4、case5
    private void fixDoubleBlack(Node x) {
        if (x == root) {//调整到树的顶部了，停止递归
            return;
        }
        Node parent = x.parent;//父亲
        Node sibling = x.sibling();//兄弟
        //case3:被调整的节点的兄弟为红，那么两个侄子定为黑
        //要处理case3要先把case3转换成case4或者case5处理，最终处理的是case4或case5
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = RED;//父节点换色
            sibling.color = BLACK;//兄弟换色
            //---------case3转换成了case4或者case5----------------
            fixDoubleBlack(x);//处理case4或者case5的情况
        }
        //case4:被调整节点的兄弟为黑，两个侄子都为黑
        if (sibling != null) {
            /*
                4.1 将兄弟变为红，目的是将删除节点和兄弟那边的黑色高度同时-1
                4.2 如果父亲也是红色，则需要将父亲变黑，避免出现红红，此时路径黑色节点数目不变
                4.3 如果父亲是黑，说明这条路径还是少黑，再次让父节点触发双黑，将上一层的高度-1达到平衡（可能存在递归）
             */
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;
                if (isRed(parent)) {
                    parent.color = BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            } else {//case5:被调整节点的兄弟为黑，至少一个红侄子
                //LL
                if (sibling.isLeftChild() && isBlack(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                //LR => LL
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                //RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                } else {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }
                parent.color = BLACK;

            }
        } else {
            fixDoubleBlack(parent);
        }


    }
}
