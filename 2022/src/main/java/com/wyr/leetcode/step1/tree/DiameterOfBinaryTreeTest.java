package com.wyr.leetcode.step1.tree;

import java.util.Objects;

/**
 * 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 *
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */


public class DiameterOfBinaryTreeTest {
    int maxDiameter=Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        process(root);
        return maxDiameter;
    }

    //最重要的：每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和。
    public int process(TreeNode root){
        if(root==null){
            return 0;
        }

        int left=process(root.left);
        int right=process(root.right);
        // 后序位置，顺便计算最大直径
        maxDiameter=Math.max(maxDiameter,left+right);
        return 1+Math.max(left,right);
    }



    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
