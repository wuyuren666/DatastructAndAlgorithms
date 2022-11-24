package com.wyr.leetcode.step1.tree;



/**
 * 给你一个二叉树的根节点root，检查他是否轴对称
 * 轴对称包含节点的值也是一样的
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
 */
public class IsSymmetricTest {

    public static boolean isSymmetric(BinaryTreesNode root){
        if(root==null)//根节点为null，返回false
            return false;
        return process(root.left,root.right);
    }

    private static boolean process(BinaryTreesNode left, BinaryTreesNode right) {
        if(left==null&&right==null) //左右节点都为null，返回true
            return true;
        //左右节点一个存在一个不存在，或者左右节点都存在但是value不一样
        if(left==null&&right!=null||left!=null&&right==null||left.value!= right.value)
            return false;

        return process(left.left,right.right)&&process(left.right,right.left);
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
        System.out.println(isSymmetric(node1));

    }
}

class BinaryTreesNode {
    public int value;
    public BinaryTreesNode left;
    public BinaryTreesNode right;

    public BinaryTreesNode(int value, BinaryTreesNode left, BinaryTreesNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTreesNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BinaryTreesNode{" +
                "value=" + value +
                '}';
    }
}
