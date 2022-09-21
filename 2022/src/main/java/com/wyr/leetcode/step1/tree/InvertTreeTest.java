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
        TreeNode node=new TreeNode(root.val);
        node.left=invertTree(root.right);
        node.right=invertTree(root.left);
        return node;
    }
}
