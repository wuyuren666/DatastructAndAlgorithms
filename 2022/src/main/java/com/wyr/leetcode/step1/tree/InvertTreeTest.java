package com.wyr.leetcode.step1.tree;

public class InvertTreeTest {

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     *
     * https://leetcode.cn/problems/invert-binary-tree/
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        //先交换root的左右两个子树的位置
        TreeNode temp=root.right;
        root.right=root.left;
        root.left=temp;
        //下面递归交换子树
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
