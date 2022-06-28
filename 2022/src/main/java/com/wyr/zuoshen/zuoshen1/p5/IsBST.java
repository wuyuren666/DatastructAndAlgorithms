package com.wyr.zuoshen.zuoshen1.p5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 判断一棵树是否是搜索二叉树(BST：Binary Search Tree)
 * 搜索二叉树的特点
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 思想：使用中序遍历，只要遍历出来的结果是严格的升序，那么就是一颗二叉搜索树
 */
public class IsBST {

    /**
     * 第一种方法：傻白甜的方法
     */
    public static boolean isBST(BinaryTreesNode root){
        List<BinaryTreesNode> bstNode=new ArrayList<>();
        process1(root,bstNode);
        int temp=0;
        for (int i = 0; i < bstNode.size(); i++) { //检查中序遍历的结果是否是升序
            if(i==0)
                temp=bstNode.get(i).value;
            else {
                if(bstNode.get(i).value<=temp){
                    return false;
                }else {
                    temp=bstNode.get(i).value;
                }
            }
        }
        return true;
    }
    //中序遍历的递归方法
    public static void process1(BinaryTreesNode root,List<BinaryTreesNode> list){
        if(root==null)
            return;
        process1(root.left,list);
        list.add(root);
        process1(root.right,list);
    }



    /**
     *第二种方法，不使用集合工具类，使用递归
     */
    public static int temp;
    public static boolean flag;
    public static boolean isBST2(BinaryTreesNode root){
        if(root.left==null&&root.right==null)//一个节点的情况
            return true;
        return process2(root);
    }
    //中序遍历的递归方法
    public static boolean process2(BinaryTreesNode root){
        if(root==null)
            return true;
        boolean isLeftBst = process2(root.left);
        if (!isLeftBst)
            return false;
        if(!flag){  //temp初始化为左边界最下面的一个节点的值，初始化一次
            temp=root.value;
            flag=true;
        }else {
            if ((root.value <= temp)) {
                return false;
            } else {
                temp = root.value;
            }
        }
        return process2(root.right);
    }


    /**
     *第三种方法：使用非递归实现中序递归
     */
    public static boolean isBst3(BinaryTreesNode root){
        boolean flag=false;
        int temp=0;
        LinkedList<BinaryTreesNode> stack=new LinkedList<>();//栈
        BinaryTreesNode cur=root; //辅助变量
        while (cur!=null){  //左边界先入栈
            stack.push(cur);
            cur=cur.left;
        }
        while (stack.size()!=0){
            //弹栈,做我们想做的操作
            if(!flag){
                cur=stack.pop();
                temp=cur.value;
                flag=true;
            }else {
                cur=stack.pop();
                if(cur.value<=temp){
                    return false;
                }else {
                    temp=cur.value;
                }
            }
            if(cur.right!=null){ //如果有右孩子
                //先将右孩子压入栈
                stack.push(cur=cur.right);
                while (cur.left!=null){ //再将右孩子的左边界压入栈
                    stack.push(cur.left);
                    cur=cur.left;
                }
            }
        }
        return true;
    }


    /**
     *第四种方法：递归套路
     * 以x为根的二叉树
     *
     * 左树得是搜索二叉树
     * 右树得是搜索二叉是
     * 左树上的最大值<x
     * 右树上的最小值>x
     *
     * 以上四个条件都成立，才能是搜索二叉树
     */

    public static boolean isBst4(BinaryTreesNode root){
            return process(root).isBst;
    }

    public static class ResultType{
        public boolean isBst;
        public int max;
        public int min;

        public ResultType(boolean isBst, int max, int min) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        }
    }

    public static ResultType process(BinaryTreesNode root){
        if(root==null)
            return null;

        //向左树要信息
        ResultType leftResult=process(root.left);

        //向右树要信息
        ResultType rightResult=process(root.right);

        int max=root.value;
        int min=root.value;

        if(leftResult!=null){ //如果有左树，min为左树和当前root值中的最小值，max为左树和当前root值中的最大值
            min=Math.min(min,leftResult.min);
            max=Math.max(min,leftResult.max);
        }

        if(rightResult!=null){//如果有左树，min为root和他的左右树中的最小值，max为root和他的左右树中的最大值
            min=Math.min(min,rightResult.min);
            max=Math.max(min,rightResult.max);
        }


        boolean isBST=true;
        //如果有左子树，且左子树不为二叉搜索树，或者左子树的最大值不满足小于根结点的值
        if(leftResult!=null&&(!leftResult.isBst || leftResult.max>=root.value)){
                isBST=false;
        }
        //如果有右子树，且右子树不为二叉搜索树，或者右子树的最小值不满足大于根结点的值
        if(rightResult!=null&&(!rightResult.isBst || rightResult.min<=root.value)){
            isBST=false;
        }
        return new ResultType(isBST,max,min);
    }




    public static void main(String[] args) {
        BinaryTreesNode node1=new BinaryTreesNode(5,null,null);
        BinaryTreesNode node2=new BinaryTreesNode(3,null,null);
        BinaryTreesNode node3=new BinaryTreesNode(7,null,null);
        BinaryTreesNode node4=new BinaryTreesNode(2,null,null);
        BinaryTreesNode node5=new BinaryTreesNode(4,null,null);
        BinaryTreesNode node6=new BinaryTreesNode(6,null,null);
        BinaryTreesNode node7=new BinaryTreesNode(8,null,null);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        System.out.println(isBST2(node1));
        System.out.println(isBst3(node1));
    }
}
