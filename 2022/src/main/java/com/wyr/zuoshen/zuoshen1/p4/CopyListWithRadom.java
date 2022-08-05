package com.wyr.zuoshen.zuoshen1.p4;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRadom {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
     * 一个单向链表，但是这个单向链表中有一个random节点随机指向，可能为null，可能为单向链表中的某个节点
     * 需要我们克隆一份新的，很老链表的各个节点的指针指向的地方是一样的
     * @param head
     * @return
     */
    public static Node copyListWithRadom(Node head){
        Map<Node,Node> map=new HashMap<>();
        Node cur=head;
        while(cur!=null){
            map.put(cur,new Node(cur.id));
            cur=cur.next;
        }
        cur=head;
        while(cur!=null){
            //map.get(cur)当前cur对应的克隆节点，map.get(cur.next):cur的next对应的克隆节点
            map.get(cur).next=map.get(cur.next);
            map.get(cur).ramdom=map.get(cur.ramdom);
        }
        return map.get(head);
    }


    /**
     * 不使用hash表
     * @param head
     * @return
     */
    public static Node copyListWithRadom2(Node head){
        if(head==null){
            return null;
        }
        Node cur=head;
        Node next=null;
        //copy node and link to every node
        //1->2
        //1->1'->2->2'
        while (cur!=null){
            next=cur.next;
            cur.next=new Node(cur.id);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        Node curCopy=null;
        //set copy node random
        //1->1' ->2->2'
        while (cur!=null){//设置当前节点的copy节点的random和当前节点指向相同
            next=cur.next.next;
            curCopy=cur.next;
            curCopy.ramdom=cur.ramdom!=null?cur.ramdom.next:null;
            cur=next;
        }
        Node res=head.next;
        cur=head;
        //split
        while (cur!=null){
            next=cur.next.next;
            curCopy=cur.next;
            cur.next=next;
            curCopy.next=next!=null?next.next:null;
            cur=next;
        }
        return res;

    }
}
