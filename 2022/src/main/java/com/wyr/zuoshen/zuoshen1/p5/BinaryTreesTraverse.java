package com.wyr.zuoshen.zuoshen1.p5;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 二叉树的深度优先遍历，递归和非递归双版本的实现
 */
public class BinaryTreesTraverse {



    //递归前序遍历二叉树
    public static void preOrder(BinaryTreesNode head){
        if(head==null)
            return;
        System.out.println(head.value);
        preOrder(head.left);
        preOrder(head.right);
    }
    //递归中遍历二叉树
    public static void inOrder(BinaryTreesNode head){
        if(head==null)
            return;
        inOrder(head.left);
        System.out.println(head.value);
        inOrder(head.right);
    }
    //递归后序遍历二叉树
    public static void posOrder(BinaryTreesNode head){
        if(head==null)
            return;
        posOrder(head.left);
        posOrder(head.right);
        System.out.println(head.value);
    }


    //非递归实现先序遍历 5.10练习
    public static void preOrder510(BinaryTreesNode head){
        //头，右，左
        LinkedList<BinaryTreesNode> stack=new LinkedList<>();
        stack.push(head);
        BinaryTreesNode cur;
        while (stack.size()!=0){
            System.out.println(cur = stack.pop());
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }
    }


    //非递归实现后序遍历 5.10练习
    public static void posOrder510(BinaryTreesNode head){
        //头，左，右 ；两个栈
        LinkedList<BinaryTreesNode> stack1=new LinkedList<>();
        LinkedList<BinaryTreesNode> stack2=new LinkedList<>();
        BinaryTreesNode cur;
        stack1.push(head);
        while (stack1.size()!=0){
            stack2.push(cur=stack1.pop());
            if(cur.left!=null){
                stack1.push(cur.left);
            }

            if(cur.right!=null){
                stack1.push(cur.right);
            }
        }


        while (stack2.size()!=0){
            System.out.println(stack2.pop());
        }

    }



    //非递归实现中序遍历 5.10
    public static void inOrder510(BinaryTreesNode head){
        //左侧边界先进站
        //弹出时，有右孩子，就将右孩子的左侧边界进站
        LinkedList<BinaryTreesNode> stack=new LinkedList<>();
        BinaryTreesNode cur=head;
        BinaryTreesNode right;

        while (cur!=null){ //将左边界压入栈
            stack.push(cur);
            cur=cur.left;
        }
        while (stack.size()!=0){
            System.out.println(cur=stack.pop());
            right=cur.right;
            while (right!=null){
                stack.push(right);
                right=right.left;
            }
        }

    }






    //非递归实现先序遍历
    public static void preOrder2(BinaryTreesNode head){
        //口诀：头右左
        //1:先把头节点压入栈
        //2：弹出当前节点cur，并打印
        //3：先右孩子压栈，后左孩子压栈
        //4：重复以上步骤
        if(head==null)
            return;

        Stack<BinaryTreesNode> stack=new Stack<>();
        BinaryTreesNode cur;
        stack.push(head); //头
        while (!stack.empty()){
            System.out.println(cur=stack.pop());
            if(cur.right!=null){ //右
                stack.push(cur.right);  //右孩子不等于空先压右孩子
            }
            if(cur.left!=null){  //左
                stack.push(cur.left);  //左孩子如果也不为空，压左孩子
            }
        }
    }

    //非递归实现后序遍历
    public static void posOrder2(BinaryTreesNode head){
        //要点：头左右；多准备一个栈，用来存放第一个栈中弹出的元素

        if(head==null)
            return;

        Stack<BinaryTreesNode> stack1 = new Stack<>();
        Stack<BinaryTreesNode> stack2 = new Stack<>();
        BinaryTreesNode cur;
        stack1.push(head); //头
        while (!stack1.isEmpty()){
            stack2.push(cur=stack1.pop());
            if(cur.left!=null){  //左
                stack1.push(cur.left);
            }
            if(cur.right!=null){  //右
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()){
            System.out.println(stack2.pop());
        }

    }


    //非递归实现中序遍历 ,稍微麻烦一点
    public static void inOrder2(BinaryTreesNode head){
        //每棵子树整个树左边界进栈
        //依次弹出的过程中打印
        //对弹出节点右树，重复以上步骤
        if(head==null)
            return;
        Stack<BinaryTreesNode> stack = new Stack<>();
        BinaryTreesNode cur=head;

        while (cur!=null){ //将开始的完整树的左边界压入栈，在我们给出的二叉树中，是将1，2，4按顺序压入栈
            stack.push(cur);
            cur=cur.left;
        }
        while (!stack.isEmpty()){
            System.out.println(cur=stack.pop());
            cur=cur.right; //有没有右孩子
            if(cur!=null){ //如果有右孩子，将右孩子的左边界压入栈
                stack.push(cur);
                while (cur.left!=null){
                    stack.push(cur.left);
                    cur=cur.left;
                }
            }
        }
    }


    //Morris遍历，时间复杂度O(N),额外空间复杂度O(1)
    public static void morrisTravel(BinaryTreesNode root){
        //有左树的节点会来到两次
        BinaryTreesNode mostRight=null;
        BinaryTreesNode cur=root;
        while(cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){//当前节点有左树
                while(mostRight.right!=null&&mostRight.right!=cur){//找到左数上标准的最右节点
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){//第一次来到自己
                    mostRight.right=cur;
                    cur=cur.left;
                }else{//第二次来到自己
                    mostRight.right=null;
                    print(cur.left);
                    cur=cur.right;
                }
            }else {//当前节点没有左树
                cur=cur.right;
            }
        }
        //打印整棵树的右边界
        print(root);
    }

    public static void print(BinaryTreesNode root){
        //先逆序，获得逆序后的头节点
        BinaryTreesNode reverseHead = reverse(root);
        BinaryTreesNode temp=reverseHead;
        while (temp!=null){
            System.out.println(temp.value);
            temp=temp.right;
        }
        //将逆序还原
        reverse(reverseHead);
    }


    public static BinaryTreesNode reverse(BinaryTreesNode root){
        BinaryTreesNode cur=root;
        BinaryTreesNode pre=null;
        BinaryTreesNode temp=null;
        while(cur!=null){
            temp=cur.right;
            cur.right=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }







    public static void main(String[] args) {
        //构造一颗二叉树
        BinaryTreesNode node1=new BinaryTreesNode(1,null,null);
        BinaryTreesNode node2=new BinaryTreesNode(2,null,null);
        BinaryTreesNode node3=new BinaryTreesNode(3,null,null);
        BinaryTreesNode node4=new BinaryTreesNode(4,null,null);
        BinaryTreesNode node5=new BinaryTreesNode(5,null,null);
        BinaryTreesNode node6=new BinaryTreesNode(6,null,null);
        BinaryTreesNode node7=new BinaryTreesNode(7,null,null);
        BinaryTreesNode node8=new BinaryTreesNode(8,null,null);
        BinaryTreesNode node9=new BinaryTreesNode(9,null,null);
        BinaryTreesNode node10=new BinaryTreesNode(10,null,null);
        BinaryTreesNode node11=new BinaryTreesNode(11,null,null);

        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node8;
        node3.right=node9;
        node5.left=node6;
        node5.right=null;
        node6.left=node7;
        node6.right=null;
        node8.left=null;
        node8.right=node10;
        node10.left=node11;

        inOrder510(node1);

        //preOrder(node1);
        //inOrder(node1);
       // inOrder2(node1);
    }
}

class BinaryTreesNode{
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
