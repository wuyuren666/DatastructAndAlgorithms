package com.wyr.bigFactory.date9_20;

public class NumberOfSameSubTree {

    /**
     * 如果一个节点X，它的左树结构和右树结构完全一样
     * 那么我们说以X为头的树是相等树
     * 给定一棵二叉树的头节点head
     * 返回head整棵树上有多少相等子树
     */
    //时间复杂度O(N*logN)
    public static int sameNumber1(Node head){
        if(head==null){
            return 0;
        }
        return sameNumber1(head.left)+sameNumber1(head.right)+(same(head.left,head.right)?1:0);
    }

    public static boolean same(Node h1,Node h2){
        if(h1==null&&h2!=null||h1!=null&&h2==null){
            return false;
        }
        if(h1==null&&h2==null){
            return true;
        }
        //h1和h2都不为空
        return h1.value==h2.value&&same(h1.left,h2.left)&&same(h1.right,h2.right);
    }




}


class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
