package com.wyr.leetcode.step1.linkedlist;

import java.util.LinkedList;

/**
 * 回文链表
 *给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *输入：head = [1,2,2,1]
 *输出：true
 *输入：head = [1,2]
 *输出：false
 */
public class IsPalindromeTest {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(1);
        //Node node4 = new Node(1);
        node1.next=node2;
        node2.next=node3;
        node3.next=null;
       /* node3.next=node4;
        node4.next=null;*/
        System.out.println(isPalindrome2(node1));
    }


    //思路：使用栈去解决，但是时间复杂度比较高
    public static boolean isPalindrome1(Node head){
        if(head.next==null)//一个节点肯定是回文链表
            return true;
        //使用栈去解决
        LinkedList<Node> stack=new LinkedList<>();
        Node temp=head;
        while(temp!=null){
            stack.add(temp);
            temp=temp.next;
        }
        temp=head;
        while(temp!=null){
            if(temp.id!=stack.removeLast().id){
                return false;
            }
            temp=temp.next;
        }
        return true;
    }


    //思路：不使用栈，使用快慢指针
    public static boolean isPalindrome2(Node head){
        if(head.next==null)//一个节点肯定是回文链表
            return true;

        Node slow=head;//慢指针
        Node fast=head;//快指针
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //如果节点个数为奇数个时，slow刚好指向中间那个节点，fast指向最后一个节点 fast!=null
        //如果节点个数为偶数个是，slow指向n/2+1的那个节点，fast指向null fast==null

        //奇数个时，将slow往后移动一位，1->2->3->2->1->null，使slow指向2就行
        if(fast!=null){
            slow=slow.next;
        }

        //反转链表
        slow = reverse(slow);

        //让fast重新指向head
        fast=head;

        while (slow!=null){
            if(slow.id!=fast.id)
                return false;
            slow=slow.next;
            fast=fast.next;
        }
        return true;
    }


    public static Node reverse(Node head){
        Node pre=null;
        Node temp;
        Node cur=head;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }
}
