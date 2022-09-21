package com.wyr.leetcode.step1.tree;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * https://leetcode.cn/problems/NYBBNL/
 * 展平二叉搜索树
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 */
public class IncreasingBSTTest  {

    public static void main(String[] args) throws IOException {
        ConcurrentHashMap<Integer,Integer>map=new ConcurrentHashMap<>();
        map.put(null,null);


    }
    public BinaryTreesNode increasingBST(BinaryTreesNode root) {
        //root为null，或者没有左右孩子时，返回本身
        if(root==null||root.left==null&&root.right==null)
            return root;
        //list集合，用来存放中序遍历后的结果
        LinkedList<BinaryTreesNode> list=new LinkedList<>();
        process(root,list);
        //结果要返回的头节点
        BinaryTreesNode result=new BinaryTreesNode(list.removeFirst().value);
        //辅助指针
        BinaryTreesNode cur=result;
        while(list.size()!=0){
            cur.right=list.removeFirst();
            cur.left=null; //左孩子设置为null
            cur=cur.right;
        }
        cur.left=null; //最后一个节点的左孩子设置为null
        cur.right=null;  //最后一个节点的右孩子设置为null
        return result;
    }

    //中序遍历的同时，将节点保存在list集合中
    public void process(BinaryTreesNode root, List<BinaryTreesNode> list){
        if(root==null)
            return;
        process(root.left,list);
        list.add(root);
        process(root.right,list);
    }
}
