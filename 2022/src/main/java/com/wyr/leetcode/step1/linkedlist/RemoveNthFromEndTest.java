package com.wyr.leetcode.step1.linkedlist;

/**
 *    给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class RemoveNthFromEndTest {
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
        Node node = removeNthFromEnd2(node1, 5);
        node.print(node);
    }



    //这个方法比较容易想到，先计算出链表总节点数count，然后在找到需要删除节点的前一个节点
    public static Node removeNthFromEnd1(Node head, int n){
        if(head==null)
            return null;

        int count=0;//计数
        Node temp=head;

        while(temp!=null){
            count++;
            temp=temp.next;
        }

        if(n>count)
            return null; //越界

        if(count==n){
            return head.next;  //删除的是第一个节点
        }

        temp=head;
        for(int i=1;i<count-n;i++){
            temp=temp.next; //让temp移动到需要删除的节点的前一个节点
        }
        temp.next=temp.next.next; //删除这个节点
        return head;
    }


    //也可以不用计算出链表的总节点数，通过两个指针来实现
    public static Node removeNthFromEnd2(Node head, int n){
        if(head==null)
            return null;
        Node slow=head;
        Node fast=head;
        //让快指针移动n位
        for (int i=1;i<=n;i++){
            fast=fast.next;
        }

        //如果fast为空，表示删除的是头结点
        if (fast == null)
            return head.next;


        while (fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        //上面的while循环结束时，slow已经指向了这个要删除节点的前一个节点
        slow.next=slow.next.next;
        return head;
    }
}
