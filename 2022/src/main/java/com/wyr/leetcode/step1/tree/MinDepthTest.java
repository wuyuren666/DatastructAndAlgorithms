package com.wyr.leetcode.step1.tree;

public class MinDepthTest {
    /**
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明：叶子节点是指没有子节点的节点。
     *
     *
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     *
     * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
     */


    /**
     * 会有额外空间
     */
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){ //左右子树都没有
            return 1;
        }
        if(root.left!=null&&root.right==null){//只有左树
            return 1+minDepth(root.left);//自己的高度+左树的最小高度
        }
        if(root.left==null&&root.right!=null){//只有右树
            return 1+minDepth(root.right);//自己的高度+右树的最小高度
        }
        //左右子树都有
        int left=minDepth(root.left);
        int right=minDepth(root.right);

        return 1+Math.min(left,right);
    }

   public static class TreeNode{
       int val;
       TreeNode left;
       TreeNode right;

       public TreeNode(int x) {
           val = x;
       }
   }

}
