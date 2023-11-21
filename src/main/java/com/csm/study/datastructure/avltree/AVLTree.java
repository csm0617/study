package com.csm.study.datastructure.avltree;

/**
 * AVL树
 *
 * @二叉搜索树在插入和删除时，节点可能失衡，
 * @如果在插入和删除时通过旋转,始终让二叉树保持平横，称为自平衡的二叉搜索树
 * @AVL是自平衡二叉搜索树的实现之一
 */
public class AVLTree {
    static class AVLNode {
        int key;
        Object val;
        AVLNode left;
        AVLNode right;
        int height = 1;//高度

        public AVLNode(int key, Object val) {
            this.key = key;
            this.val = val;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object val, AVLNode left, AVLNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //求当前节点的高度
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    //更新节点高度（新增，删除，旋转）
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right));
    }

    //平衡因子(balance factor) = 左子树高度 - 右子树高度

    /**
     * 当bf==0 ,1 ,-1时，节点是平衡的，当bf>1时左子树高，当bf<-1时右子树高
     *
     * @param node 节点
     * @return 节点的平衡因子
     */
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }


    /**
     * 节点右旋
     *
     * @param red 要旋转的节点
     * @return 新的根节点（子树）
     */
    private AVLNode rightRotate(AVLNode red) {
        /*
            red:左右不平衡的节点，将要右旋成新根的右孩子  yellow:将要右旋上去成为新根的节点  green:是yellow节点的右孩子，将成为red节点的左孩子
         */
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;
        yellow.right = red;
        red.left = green;
        //调整后更新高度，red节点的高度一定会变， yellow节点的高度可能会变，其他节点的高度不会发生改变
        //更新高度的代码顺序不能调整位置，因为red是yellow的孩子节点，只有red先是正确的高度了yellow才会改变
        return yellow;
    }

    /**
     * @param red 要旋转的节点
     * @return 新的根节点（子树）
     */
    private AVLNode leftRotate(AVLNode red) {
        /*
            red:左右不平衡的节点，将要左旋成新根的左孩子  yellow:将要左旋上去成为新根的节点  green:是yellow节点的左孩子，将成为red节点的右孩子
         */
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;
        red.right = green;
        //调整后更新高度，red节点的高度一定会变， yellow节点的高度可能会变，其他节点的高度不会发生改变
        //更新高度的代码顺序不能调整位置，因为red是yellow的孩子节点，只有red先是正确的高度了yellow才会改变
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * LR失衡（左右旋）
     * 节点的左孩子更高，左孩子的右孩子比左孩子的左孩子更高
     * 左右旋，先左旋节点的左子树变成（LL失衡），再右旋根节点
     *
     * @param node
     * @return
     */
    private AVLNode leftRightRotate(AVLNode node) {
        //调整高度的的操作已经包含在leftRotate和rightRotate的调用中了
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    /**
     * RL失衡（右左旋）
     * 节点的右孩子更高，右孩子的左孩子比左孩子的右孩子更高
     * 右左旋，先右旋节点的左子树（变成RR失衡），再左旋根节点
     *
     * @param node
     * @return
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        //调整高度的的操作已经包含在leftRotate和rightRotate的调用中了
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }
}
