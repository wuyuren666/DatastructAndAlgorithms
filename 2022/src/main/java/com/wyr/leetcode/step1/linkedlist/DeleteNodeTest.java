package com.wyr.leetcode.step1.linkedlist;

/**
 * 请编写一个函数，用于 删除单链表中某个特定节点 。
 * 在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问要被删除的节点 。
 */
public class DeleteNodeTest {
    public static void main(String[] args)  {
        com.wyr.leetcode.step1.linkedlist.ListNode listNode1 = new com.wyr.leetcode.step1.linkedlist.ListNode(3);
        com.wyr.leetcode.step1.linkedlist.ListNode listNode2 = new com.wyr.leetcode.step1.linkedlist.ListNode(1);
        com.wyr.leetcode.step1.linkedlist.ListNode listNode3 = new com.wyr.leetcode.step1.linkedlist.ListNode(5);
        com.wyr.leetcode.step1.linkedlist.ListNode listNode4 = new com.wyr.leetcode.step1.linkedlist.ListNode(0);
        com.wyr.leetcode.step1.linkedlist.ListNode listNode5 = new com.wyr.leetcode.step1.linkedlist.ListNode(4);
        listNode1.next= listNode2;
        listNode2.next= listNode3;
        listNode3.next= listNode4;
        listNode4.next= listNode5;
        listNode5.next=null;
        deleteNode(listNode2);
        listNode1.print(listNode1);

    }

    /**
     *  请编写一个函数，用于 删除单链表中某个特定节点 。
     *  在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问要被删除的节点 。
     */
    public static void deleteNode(com.wyr.leetcode.step1.linkedlist.ListNode delListNode)  {

        //3-->1-->5-->0-->4-->null
        //假如，我们要删除节点1。
        //首先，我们让节点5的值赋值给节点1
        //3-->5-->5-->0-->4-->null
        //让第一个值为5的节点的next指针指向值为0的节点
        delListNode.id= delListNode.next.id;
        delListNode.next= delListNode.next.next;
    }





    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     *
     * 返回删除后的链表的头节点。
     *
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public ListNode deleteNode(ListNode head, int val) {
        if(head.val==val){
            return head.next;
        }
        ListNode temp=head.next; //目标指针
        ListNode preTemp=head; //目标指针前一个
        while(temp!=null){
            if(temp.val==val){
                break;
            }
            preTemp=temp;
            temp=temp.next;
        }
        preTemp.next=temp.next;
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


class ListNode {
    public int id;
    public ListNode previous;
    public ListNode next;
    public ListNode ramdom;

    public ListNode() {
    }

    public ListNode(int id) {
        this.id = id;
    }

    public ListNode(int id, ListNode previous, ListNode next) {
        this.id = id;
        this.previous = previous;
        this.next = next;
    }

    public ListNode(int id, ListNode next) {
        this.id = id;
        this.previous = null;
        this.next = next;
    }


    //打印链表的方法
    public void print(ListNode head){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        System.out.println(head);
        while (true) {
            ListNode temp = head.next;
            if (temp == null) {
                break;
            }else {
                System.out.println(temp);
            }
            head=temp;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
