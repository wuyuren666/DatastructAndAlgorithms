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
        if(head.next==null){ //只有一个节点
            return head;
        }
        //顺序保存所有节点
        List<ListNode> list=new ArrayList<>();
        ListNode res=head.next; //最终需要返回的结果
        ListNode cur=head;
        //list装入所有节点
        while(cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        //开始的时候pre为null
        ListNode pre=null;
        //两两交换
        for(int i=0;i<list.size();i+=2){
            if(i+1<list.size()){
                pre=swap(pre,list.get(i),list.get(i+1));
            }
        }
        return res;
    }

    //交换的逻辑，提供前一个节点和交换的两个节点，返回的是下一次交换所需要的前一个节点
    public ListNode swap(ListNode pre,ListNode o1,ListNode o2){
        ListNode temp=o2.next;
        if(pre==null){
            o2.next=o1;
            o1.next=temp;
        }else{
            o2.next=o1;
            o1.next=temp;
            pre.next=o2;
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
