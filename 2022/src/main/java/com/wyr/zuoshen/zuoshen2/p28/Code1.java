package com.wyr.zuoshen.zuoshen2.p28;

public class Code1 {


    public static class Node{
        public int value;
        public Node previous;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 双链表的逆序
     */
    public static Node reverseDoubleList(Node head){
        Node cur=head;
        Node temp=null;//保存cur节点的下一个节点
        Node pre=null;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            cur.previous=temp; //比反转单链表就多了这一步
            pre=cur;
            cur=temp;
        }
        return pre;
    }
}
