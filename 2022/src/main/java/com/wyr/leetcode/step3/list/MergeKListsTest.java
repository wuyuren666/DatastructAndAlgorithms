package com.wyr.leetcode.step3.list;

import java.util.PriorityQueue;

public class MergeKListsTest {
    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     *
     * 输入：lists = []
     * 输出：[]
     *
     * 输入：lists = [[]]
     * 输出：[]
     *
     * https://leetcode.cn/problems/merge-k-sorted-lists/
     */

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dual = new ListNode();//虚拟节点
        ListNode temp=dual;//辅助节点
        PriorityQueue<ListNode> pq=new PriorityQueue<>(
                (o1,o2)->o1.val-o2.val
        );//优先级队列

        //先将k条链表的k个头节点加入到优先级队列中
        for(ListNode node: lists){
            if(node!=null) //有特殊情况
                pq.add(node);
        }
        while(!pq.isEmpty()){
            //弹出值最小的节点
            ListNode cur=pq.poll();
            //拼接
            temp.next=cur;
            //当前值最小的节点是否还有后续节点
            if(cur.next!=null){
                //加入后续节点
                pq.add(cur.next);
            }
            //temp后移
            temp=temp.next;
        }
        //最后补null
        temp.next=null;
        return dual.next;
    }

    public static abstract interface A{

    }
}



class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode() {
    }
}
