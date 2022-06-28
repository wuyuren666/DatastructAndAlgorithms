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
 */
public class MaxDepthTest {

    public static int maxDepth(BinaryTreesNode root){
        if(root==null)
            return 0;
        if(root.left==null&&root.right==null)
            return 1;
        return 1+Math.max(process(root.left),process(root.right));
    }

    public static int process(BinaryTreesNode root){
        if(root==null)
            return 0;
        if(root.left==null&&root.right==null)
            return 1;
        return 1+Math.max(process(root.left),process(root.right));
    }

    public static void main(String[] args) {
        BinaryTreesNode node1=new BinaryTreesNode(1,null,null);
        BinaryTreesNode node2=new BinaryTreesNode(1,null,null);
        BinaryTreesNode node3=new BinaryTreesNode(1,null,null);
        BinaryTreesNode node4=new BinaryTreesNode(3,null,null);
        BinaryTreesNode node5=new BinaryTreesNode(2,null,null);
        BinaryTreesNode node6=new BinaryTreesNode(2,null,null);
        BinaryTreesNode node7=new BinaryTreesNode(3,null,null);

        node1.left=node2; node1.right=node3;
        node2.left=node4; node2.right=node5;
        node3.left=node6; node3.right=node7;
        System.out.println(maxDepth(node1));
    }
}
