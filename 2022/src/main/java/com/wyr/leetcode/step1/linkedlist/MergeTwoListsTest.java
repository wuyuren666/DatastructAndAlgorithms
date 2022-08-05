package com.wyr.leetcode.step1.linkedlist;

/**
 *     将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *     输入：l1 = [1,2,4], l2 = [1,3,4]
 *     输出：[1,1,2,3,4,4]
 */
public class MergeTwoListsTest {
    public static void main(String[] args) {

    }

    public  static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead=new ListNode(-1);
        ListNode cur=newHead;
        //谁小就连谁，然后小的移动
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                cur.next=l1;
                cur=cur.next;
                l1=l1.next;
            }else{
                cur.next=l2;
                cur=cur.next;
                l2=l2.next;
            }
        }
        //l1还剩
        if(l1!=null){
            cur.next=l1;
        }
        //l2还剩
        if(l2!=null){
            cur.next=l2;
        }
        return newHead.next;
    }






    /**
     * 给定一个链表数组，每个链表都已经按升序排列。
     *
     * 请将所有链表合并到一个升序链表中，返回合并后的链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0)
            return null;
        if(lists.length==1){
            return lists[0];
        }
        if(lists.length==2){
            return merge(lists[0],lists[1]);
        }
        ListNode result=merge(lists[0],lists[1]);
        //两两合并
        for(int i=2;i<=lists.length-1;i++){
            result=merge(result,lists[i]);
        }
        return result;
    }
    //合并两个链表，返回新链表的节点
    public ListNode merge(ListNode n1, ListNode n2){
        ListNode p1=n1;
        ListNode p2=n2;
        ListNode result=new ListNode();//需要返回的新链表的头
        ListNode cur=result; //辅助指针
        while(p1!=null&&p2!=null){
            if(p1.val<=p2.val){
                cur.next=p1;
                p1=p1.next;
                cur=cur.next;
            }else{
                cur.next=p2;
                p2=p2.next;
                cur=cur.next;
            }
        }
        while(p1!=null){
            cur.next=p1;
            p1=p1.next;
            cur=cur.next;
        }
        while(p2!=null){
            cur.next=p2;
            p2=p2.next;
            cur=cur.next;
        }
        cur.next=null;
        return result.next;
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
