package com.wyr.zuoshen.zuoshen1.p5;

import java.util.LinkedList;
import java.util.Queue;

/**
 *判断一棵树是否为完全二叉树(CBT：completed Binary Tree)
 *思想：
 * 二叉树的层序遍历
 * 条件一：当前节点只要有右孩子，没有左孩子，false
 * 条件二：在条件一不违规的情况下，如果遇到了了第一个左右孩子不全的节点，后续皆为叶子节点
 */
public class IsCBT {

    //采用广度优先遍历
    //条件一：节点有右孩子而没有左孩子，则肯定不是完全二叉树
    //条件二：满足条件一的情况下，如果遇到第一个非全节点，剩下的节点应该都是叶子节点
    public boolean isCompleteTree66 (BinaryTreesNode root){
        //队列
        Queue<BinaryTreesNode> queue=new LinkedList<>();
        queue.add(root);
        BinaryTreesNode cur=null;
        boolean isMeet=false;
        while(queue.size()!=0){
            cur=queue.poll();
            //当前节点存在右孩子，而没有左孩子，返回false
            if(cur.right!=null&&cur.left==null){
                return false;
            }

            //if满足代表遇到了第一个非双全节点
            if(isMeet){
                //之后的节点必须都是叶子节点
                if(cur.right!=null||cur.left!=null){
                    return false;
                }
            }

            if(cur.left==null||cur.right==null){
                isMeet=true;
            }

            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
        return true;
    }




    //采用广度优先遍历，只要当前遍历到的节点有右孩子，没有左孩子，那么肯定不是完全二叉树
    //在第一个条件满足的情况下，当碰到第一个非全节点之后，后序节点应该都是叶子节点
    public boolean isCompleteTree520 (BinaryTreesNode root) {
        LinkedList<BinaryTreesNode> queue=new LinkedList<>();//队列
        queue.add(root);//头节点入队
        BinaryTreesNode cur=null;
        BinaryTreesNode left=null;
        BinaryTreesNode right=null;
        boolean meetFirstNode=false;//是否遇到了第一个非全的节点
        while(queue.size()!=0){
            cur=queue.removeFirst();
            left=cur.left;
            right=cur.right;
            if(right!=null&&left==null){ //当前节点只有右孩子没有左孩子
                return false;
            }

            if(meetFirstNode){ //已经遇到了第一个非全的节点
                if(left!=null||right!=null){ //判断是否是叶子节点
                    return false;
                }
            }

            if(left==null||right==null){ //第一次遇到了非全节点，反正就设置为true
                meetFirstNode=true;
            }

            if(left!=null){
                queue.add(left);
            }
            if(right!=null){
                queue.add(right);
            }

        }
        return true;
    }




    //思想，使用广度优先遍历，如果当前节点有右孩子，没有左孩子，那么肯定不是完全二叉树；
    //满足上一个条件的情况下，如果遇到了第一个非全节点，那么之后的所有节点都应该是叶子节点
    public static boolean isCbt(BinaryTreesNode root){
        boolean meetFirstNode=false;//是否遇到了第一个非双全节点
        LinkedList<BinaryTreesNode> queue=new LinkedList<>();
        BinaryTreesNode left;
        BinaryTreesNode right;
        BinaryTreesNode cur;
        queue.add(root);//根节点先入队
        while (queue.size()!=0){
            cur=queue.removeFirst();//先进先出
            left=cur.left;
            right=cur.right;
            //当前节点只要有右无左，则false
            if(left==null&&right!=null){
                return false;
            }

            //后序节点，是否是叶子节点
            if(meetFirstNode){
                if(left!=null||right!=null)
                    return false;
            }

            //不违规，且遇到第一个非双全节点了
            if((left==null||right==null)){
                meetFirstNode=true;
            }


            if(left!=null){
                queue.add(left);
            }
            if(right!=null){
                queue.add(right);
            }
        }
        return true;
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
        System.out.println(isCbt(node1));
    }

}
