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
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(1);
        //Node node4 = new Node(1);
        listNode1.next= listNode2;
        listNode2.next= listNode3;
        listNode3.next=null;
       /* node3.next=node4;
        node4.next=null;*/
        System.out.println(isPalindrome2(listNode1));
    }


    //思路：使用栈去解决，但是时间复杂度比较高
    public static boolean isPalindrome1(ListNode head){
        if(head.next==null)//一个节点肯定是回文链表
            return true;
        //使用栈去解决
        LinkedList<ListNode> stack=new LinkedList<>();
        ListNode temp=head;
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
    public static boolean isPalindrome2(ListNode head){
        if(head==null||head.next==null){
            return true;
        }
        ListNode slow=head;
        ListNode fast=head;

        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //slow.next：如果是偶数时就是下中点，奇数时就是中点后一个
        slow=reverse(slow.next);
        ListNode temp=slow; //保存一下头节点，因为后面需要再次反转回来
        fast=head; //fast来到开头
        boolean result=true; //最终返回的结果

        while(slow!=null){
            if(slow.id!=fast.id){
                result=false;
                break;
            }
            slow=slow.next;
            fast=fast.next;
        }

        reverse(temp);//链表回归原状
        return result;
    }

    //反转链表，并返回新链表的头节点
    public static ListNode reverse(ListNode head){
        ListNode pre=null;
        ListNode temp=null;
        ListNode cur=head;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }
}
