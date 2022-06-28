package com.wyr.leetcode.step1.linkedlist;

/**
 *     将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *     输入：l1 = [1,2,4], l2 = [1,3,4]
 *     输出：[1,1,2,3,4,4]
 */
public class MergeTwoListsTest {
    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(5);
        Node node3 = new Node(7);
        Node node4 = new Node(10);
        Node node5 = new Node(11);
        Node node6 = new Node(2);
        Node node7 = new Node(5);
        Node node8 = new Node(6);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;
        node6.next=node7;
        node7.next=node8;
        node8.next=null;
        Node node = mergeTwoLists1(node1, node6);
        node.print(node);

    }


    //开辟一个新的链表空间
    public static Node mergeTwoLists1(Node list1,Node list2){
        if(list1==null && list2!=null)
            return list2;
        else if(list1!=null && list2==null)
            return list1;
        else if (list1==null && list2==null)
            return null;

        Node newHead=new Node(); //新链表的头节点
        Node cur=newHead; //新链表的辅助指针
        Node templeft=list1; //左边链表的辅助指针
        Node tempright=list2; //右边链表的辅助指针
        while(templeft!=null&&tempright!=null){
            if(templeft.id<=tempright.id)
            {
               cur.next=new Node(templeft.id);
               cur=cur.next;
               templeft=templeft.next;
            }else {
                cur.next=new Node(tempright.id);
                cur=cur.next;
                tempright=tempright.next;
            }
        }
        //上面的while循环结束，要么templeft指向了null，要么就是tempright指向了null
        //所以cur后面直接连上不为空的那个
        cur.next=templeft!=null?templeft:tempright;
        return newHead.next;
    }




    //当然也可以不用开辟新的空间,即不使用templeft和tempright
    public static Node mergeTwoLists2(Node list1,Node list2){
        if(list1==null && list2!=null)
            return list2;
        else if(list1!=null && list2==null)
            return list1;
        else if (list1==null && list2==null)
            return null;

        Node newHead=new Node(); //新链表的头节点
        Node cur=newHead; //新链表的辅助指针

        while (list1!=null&&list2!=null){
            if(list1.id<=list2.id){
                cur.next=list1;
                cur=cur.next;
                list1=list1.next;
            }
            else {
                cur.next=list2;
                cur=cur.next;
                list2=list2.next;
            }
        }

        cur.next=list1!=null?list1:list2;
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
    public ListNode merge(ListNode n1,ListNode n2){
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
