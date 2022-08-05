package com.wyr.leetcode.step1.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true。 否则，返回 false 。
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class HasCycleTest {
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
        System.out.println(hasCycle1(listNode1));

    }

    //使用快慢指针，快指针一次走两步，慢指针一次走一步
    //如果有环，那么快慢指针肯定会相遇
    public static boolean hasCycle1(ListNode head){
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast) //注意这是引用类型的==比较，比较的是，这两个指针指向的是否是同一个对象。
                return true;
        }
        return false;
    }


    //使用Set集合，利用Set集合不重复的原理
    public static boolean hasCycle2(ListNode head){
        Set<ListNode> set=new HashSet<>();
        while (head!=null){
            //如果重复出现说明有环
            if(set.contains(head))
                return true;
            //否则就把当前节点加入到集合中
            set.add(head);
            head=head.next;
        }
        return false;
    }
}
