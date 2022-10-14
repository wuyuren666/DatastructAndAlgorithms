package com.wyr.leetcode.step2.list;

import java.util.ArrayList;
import java.util.List;

public class PartitionTest {
    /**
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     *
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     *
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/partition-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dual = new ListNode(-1); //虚拟头节点
        List<ListNode> lessList=new ArrayList<>(); //存节点值小于x的节点
        List<ListNode> moreAndEqList=new ArrayList<>(); //存节点值大于等于x的节点

        ListNode temp=dual; //辅助指针
        ListNode cur=head; //辅助指针
        while(cur!=null){
            if(cur.val<x){
                lessList.add(cur);
            }else {
                moreAndEqList.add(cur);
            }
            cur=cur.next;
        }//按先后顺序填充lessList和moreAndEqList

        //拼接节点
        for(int i=0;i<lessList.size();i++){
            temp.next=lessList.get(i);
            temp=temp.next;
        }

        for(int i=0;i<moreAndEqList.size();i++){
            temp.next=moreAndEqList.get(i);
            temp=temp.next;
        }
        temp.next=null; //最后别忘了补上null

        return dual.next;
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
