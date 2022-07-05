package com.wyr.leetcode.step1.tree;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明:叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * 返回它的最大深度 3 。
 *
 * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 */
public class MaxDepthTest {

    /**
     * 会有额外空间
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        //向左树要信息
        int left=maxDepth(root.left);
        //向有树要信息
        int right=maxDepth(root.right);
        return 1+Math.max(left,right);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

}
