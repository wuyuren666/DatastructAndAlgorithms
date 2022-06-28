package com.wyr.leetcode.step1.linkedlist;


/**
 *   给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class ReverseListTest {
    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(1);
        Node node3 = new Node(5);
        Node node4 = new Node(0);
        Node node5 = new Node(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;
        Node node = reverseList(node1);
        node.print(node);

    }

    //给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
    //使用3个指针完成，pre，cur，temp
    public static Node reverseList(Node head){
        if(head==null)
            return null;
        if(head.next==null)
            return head;

        Node pre=null;
        Node cur=head;
        Node temp;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }


}
