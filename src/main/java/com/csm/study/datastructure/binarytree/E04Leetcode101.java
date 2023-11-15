package com.csm.study.datastructure.binarytree;

import com.csm.study.datastructure.binarytree.structure.TreeNode;

/**
 * leetcode101判断是不是对称二叉树（递归）
 */
public class E04Leetcode101 {

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    /*
        如果一个树的左子树与右子树镜像对称，那么这个树是对称的。
        因此，该问题可以转化为：两个树在什么情况下互为镜像？
        如果同时满足下面的条件，两个树互为镜像：
        它们的两个根结点具有相同的值
        每个树的右子树都与另一个树的左子树镜像对称
        我们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，
        left和right指针，一开始从树的根出发，将树分为左子树和右子树
            1.先判断left和right是不是都为空，如果都为空，显然成立
            2.left和right有一个为空，显然不是对称二叉树
            3.对称二叉树，直观上来看就是镜像的，把left的左子树（left.left），和right的右子树(right.right)，进行递归比较，并且把
            left的右子树(left.right)和right的左子树（right.left）进行递归比较。
            如果满足  && 都为true说明是对称二叉树。

     */
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {//1.先判断left和right是不是都为空，如果都为空，显然成立
            return true;
        }
        if (left == null || right == null) {//2.left和right有一个为空，显然不是对称二叉树
            return false;
        }

        if (left.val != right.val) { //left和right不相等肯定不是
            return false;
        }
        //3.对称二叉树，直观上来看就是镜像的，把left的左子树（left.left），和right的右子树(right.right)，进行递归比较，并且把
        //left的右子树(left.right)和right的左子树（right.left）进行递归比较。
        return check(left.left,right.right)&&check(left.right,right.left);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(8,
                            3, 7),
                        2,
                        new TreeNode(6,
                                4,5)),
                1,
                new TreeNode(
                        new TreeNode(5,
                                4,6),
                        2,
                        new TreeNode(7,
                                3, 8))
        );
        System.out.println(new E04Leetcode101().isSymmetric(root));
    }
}
