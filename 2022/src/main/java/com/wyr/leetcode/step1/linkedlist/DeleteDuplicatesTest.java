package com.wyr.leetcode.step1.linkedlist;

public class DeleteDuplicatesTest {

    /**
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     *
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     *
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head;
        }
        if(head.next.next==null&&head.next.val==head.val){
            return head.next;
        }
        ListNode p1=head;
        ListNode p2=head.next;
        while(p2!=null){
            if(p2.val==p1.val){
                p2=p2.next;
            }else{
                p1.next=p2;
                p1=p2;
                p2=p2.next;
            }
        }
        // [1,1,2,3,3,3] 类似这种情况p1 来不到最后一个节点
        if(p1.next!=null){
            p1.next=null;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
