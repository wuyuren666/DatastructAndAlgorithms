package com.wyr.leetcode.step1.tree;

/**
 * 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 *
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTreeTest {
    //使用二叉树的递归套路去求
    public static int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        return process(root).maxDistance;
    }

    public static Info process(TreeNode root){
        if(root==null){
            return new Info(0,0);
        }

        //想左树要信息
        Info leftInfo=process(root.left);
        //向右树要信息
        Info rightInfo=process(root.right);

        //接下来返回自己的信息
        //最大高度就是左树和右树中的最大距离+1
        int height=Math.max(leftInfo.height,rightInfo.height)+1;
        //最大的距离，分两种情况
        //情况一：经过root，就是左子树的高度+右子树的高度
        //情况二：不经过root，就是左子树和右子树中的最大距离
        int p1=leftInfo.height+rightInfo.height;
        int p2=Math.max(leftInfo.maxDistance,rightInfo.maxDistance);

        int maxDistance=Math.max(p1,p2);

        return new Info(height,maxDistance);
    }


    //自定义我们需要的返回值类型
    public static class Info{
        public int height;
        public int maxDistance;
        public Info(int height, int maxDistance){
            this.height=height;
            this.maxDistance=maxDistance;
        }
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
