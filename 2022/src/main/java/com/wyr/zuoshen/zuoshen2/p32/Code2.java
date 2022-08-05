package com.wyr.zuoshen.zuoshen2.p32;

import java.util.LinkedList;

public class Code2 {
    /**
     * 困难题
     * 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     *
     * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //链表中的节点数目为 n
    //1 <= k <= n <= 5000
    public static ListNode reverseKGroup(ListNode head, int k) {
        //一个节点为1组，相当于不用反转
        if(k==1){
            return head;
        }else if(getKthNode(head,k).next==null) {
            //k==n，即走k步到达最后一个节点
            return reverse(head);
        }else {
            //每次走k步，如果返回值不为null
            ListNode result=null;
            ListNode passKthNode=null;
            ListNode passKthNodeNext=null;
            boolean flag=true;
            int tempk=k;
            while((passKthNode=getKthNode(head,k))!=null){
                //先将下一组的第一个节点保存下来
                passKthNodeNext=passKthNode.next;
                passKthNode.next=null;
                if(flag){ //只会做一次
                    result=reverse(head);
                    //获得第一组的最后一个节点
                    getLastNode(result).next=passKthNodeNext;
                    head=result;
                    flag=false;
                }else{
                    getKthNode(head,k-tempk).next=reverse(getKthNode(head,k-tempk).next);
                    getLastNode(passKthNode).next=passKthNodeNext;
                }
                k=k+tempk;
            }
            return result;
        }
    }


    //给头节点和走k步，返回走k步到达的节点
    public static ListNode getKthNode(ListNode head, int k){
        if(k==1){
            return head;
        }else{
            while(k>1){
                head=head.next;
                if(head==null){
                    break;
                }
                k--;
            }
            return head;
        }
    }

    public static ListNode reverse(ListNode head){
        ListNode cur=head;
        ListNode temp=null;
        ListNode pre=null;
        while(cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }

    public static ListNode getLastNode(ListNode head){
        while(head.next!=null){
            head=head.next;
        }
        return head;
    }

    public static class ListNode {
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


    public static void main(String[] args) {
        ListNode n1=new ListNode(1);ListNode n2=new ListNode(2);ListNode n3=new ListNode(3);
        ListNode n4=new ListNode(4);ListNode n5=new ListNode(5);ListNode n6=new ListNode(6);
        n1.next=n2;n2.next=n3;n3.next=n4;n4.next=n5;n5.next=n6;n6.next=null;

        reverseKGroup(n1,2);
    }
}
