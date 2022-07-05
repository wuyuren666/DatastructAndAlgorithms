package com.wyr.zuoshen.zuoshen2.p44;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Code1 {
    /**
     * 给你一个链表（可能是奇数个节点，也可能是偶数个节点）1:奇数时返回中点，偶数时的下中点/上中点 ；
     * 2:奇数返回中点前一个，偶数返回上中点前一个/下中点前一个
     */
    //面试的时候，为了空间复杂度考虑这样说
    //奇数时返回中点，偶数时返回上中点
    public static Node getMidOrTopMid1(Node head){
        if(head==null||head.next==null||head.next==null){
            return head;
        }
        //节点个数>=3才会来到这
        Node slow=head;
        Node fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    //笔试的时候为了快可以这样写
    //奇数时返回中点，偶数时返回上中点
    public static Node getMidOrTopMid2(Node head){
        if(head==null||head.next==null||head.next==null){
            return head;
        }
        //节点个数>=3才会来到这
        List<Node> list=new ArrayList<>();
        Node cur=head;
        while (head!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get((list.size()-1)/2);
    }
    //面试的时候，为了空间复杂度考虑这样说
    //奇数时返回中点，偶数时返回下中点
    public static Node getMidOrDownMid1(Node head){
        if(head==null||head.next==null){
            return head;
        }
        //节点个数>=2才会来到这
        Node slow=head.next;
        Node fast=head.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    //笔试的时候为了快可以这样写
    //奇数时返回中点，偶数时返回上中点
    public static Node getMidOrDownMid2(Node head){
        if(head==null||head.next==null){
            return head;
        }
        //节点个数>=2才会来到这
        List<Node> list=new ArrayList<>();
        Node cur=head;
        while (head!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get((list.size())/2);
    }

    //面试的时候，为了空间复杂度考虑这样说
    //奇数时返回中点前一个，偶数时返回上中点前一个
    public static Node getBeforeMidOrBeforeTopMid(Node head){
        if(head==null||head.next==null||head.next==null){
            return null;
        }
        //节点个数>=3才会来到这
        Node slow=head;
        Node fast=head.next.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    //面试的时候，为了空间复杂度考虑这样说
    //奇数时返回中点前一个，偶数时返回下中点前一个
    public static Node getBeforeMidOrBeforeDownMid(Node head){
        if(head==null||head.next==null){
            return null;
        }
        //节点个数>=2才会来到这
        Node slow=head;
        Node fast=head.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }



    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
