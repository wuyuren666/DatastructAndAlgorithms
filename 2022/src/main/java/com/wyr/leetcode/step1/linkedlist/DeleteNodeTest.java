package com.wyr.leetcode.step1.linkedlist;

import com.wyr.zuoshen.zuoshen2.p32.Code3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * 请编写一个函数，用于 删除单链表中某个特定节点 。
 * 在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问要被删除的节点 。
 */
public class DeleteNodeTest {
    public static void main(String[] args)  {
        Node node1 = new Node(3);
        Node node2 = new Node(1);
        Node node3 = new Node(5);
        Node node4 = new Node(0);
        Node node5 = new Node(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;
        deleteNode(node2);
        node1.print(node1);

    }

    /**
     *  请编写一个函数，用于 删除单链表中某个特定节点 。
     *  在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问要被删除的节点 。
     */
    public static void deleteNode(Node delNode)  {

        //3-->1-->5-->0-->4-->null
        //假如，我们要删除节点1。
        //首先，我们让节点5的值赋值给节点1
        //3-->5-->5-->0-->4-->null
        //让第一个值为5的节点的next指针指向值为0的节点
        delNode.id=delNode.next.id;
        delNode.next=delNode.next.next;
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
        if(val==head.val){
            return head.next;
        }
        ListNode cur=head.next;
        ListNode curNext=null;
        ListNode curPre=head;
        while(cur!=null){
            curNext=cur.next;
            if(cur.val==val){
                curPre.next=curNext;
                break;
            }
            curPre=cur;
            cur=curNext;
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


class Node{
    public int id;
    public Node previous;
    public Node next;
    public Node ramdom;

    public Node() {
    }

    public Node(int id) {
        this.id = id;
    }

    public Node(int id, Node previous, Node next) {
        this.id = id;
        this.previous = previous;
        this.next = next;
    }

    public Node(int id, Node next) {
        this.id = id;
        this.previous = null;
        this.next = next;
    }


    //打印链表的方法
    public void print(Node head){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        System.out.println(head);
        while (true) {
            Node temp = head.next;
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
