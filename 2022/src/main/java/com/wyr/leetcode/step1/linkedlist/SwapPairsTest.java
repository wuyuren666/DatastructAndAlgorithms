package com.wyr.leetcode.step1.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */
public class SwapPairsTest {

    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head;
        }
        List<ListNode> list=new ArrayList<>();
        ListNode cur=head;
        ListNode result=head.next;
        while(cur!=null){ //先将节点的按序保存到list集合中
            list.add(cur);
            cur=cur.next;
        }

        ListNode pre=null; //第一次交换是没有前一个节点的
        for(int i=0;i+1<list.size();i+=2){
            if(pre==null){ //第一次交换
                pre=swap(null,list.get(i),list.get(i+1));
            }else{ //接下来的交换
                pre=swap(pre,list.get(i),list.get(i+1));
            }
        }
        return result;
    }

    //两两交换节点，提供前一个节点，和交换的两个节点
    public ListNode swap(ListNode pre,ListNode o1,ListNode o2){
        ListNode temp=o2.next;
        if(pre==null){
            o1.next=temp;
            o2.next=o1;
        }else{
            pre.next=o2;
            o1.next=temp;
            o2.next=o1;
        }
        return o1;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
