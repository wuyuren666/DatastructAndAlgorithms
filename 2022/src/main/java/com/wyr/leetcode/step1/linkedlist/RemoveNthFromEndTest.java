package com.wyr.leetcode.step1.linkedlist;

/**
 *    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *    https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/submissions/
 */
public class RemoveNthFromEndTest {
    public static void main(String[] args) {
    }


    //这个方法比较容易想到，先计算出链表总节点数count，然后在找到需要删除节点的前一个节点
    public static ListNode removeNthFromEnd1(ListNode head, int n){
        if(head==null)
            return null;
        ListNode temp=head;
        int count=0;//计数
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        temp=head;
        for(int i=1;i<=count-n;i++){
            temp=temp.next;
        }
        return temp;
    }


    //也可以不用计算出链表的总节点数，通过两个指针来实现
    public static ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode slow=head;
        ListNode fast=head;

        for(int i=1;i<=n;i++){
            fast=fast.next;
        }
        if(fast==null){
            return head;
        }
        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
}
