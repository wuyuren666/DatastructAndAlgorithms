package com.wyr.leetcode.step1.linkedlist;


/**
 *   给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class ReverseListTest {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(5);
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(4);
        listNode1.next= listNode2;
        listNode2.next= listNode3;
        listNode3.next= listNode4;
        listNode4.next= listNode5;
        listNode5.next=null;
        ListNode listNode = reverseList(listNode1);
        listNode.print(listNode);

    }

    //给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
    //使用3个指针完成，pre，cur，temp
    public static ListNode reverseList(ListNode head){
        if(head==null)
            return null;
        if(head.next==null)
            return head;

        ListNode pre=null;
        ListNode cur=head;
        ListNode temp;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }

    public ListNode reverse214(ListNode head){
        ListNode cur=head;
        ListNode pre=null;
        ListNode next=null;

        while(cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }


}
