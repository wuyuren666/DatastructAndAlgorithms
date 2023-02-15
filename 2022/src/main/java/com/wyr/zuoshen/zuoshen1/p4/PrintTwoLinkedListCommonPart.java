package com.wyr.zuoshen.zuoshen1.p4;

import java.util.Stack;

public class PrintTwoLinkedListCommonPart {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(5);
        Node node4 = new Node(0);
        Node node5 = new Node(2);
        Node node6 = new Node(3);
        Node node7 = new Node(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=null;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        node7.next=null;
       // printTwoLinkedListCommonPart(node1,node4);
        Node node8 = new Node(1);
        Node node9 = new Node(3);
        Node node10 = new Node(10);
        Node node11 = new Node(4);
        Node node12 = new Node(1);
        node8.next=node9;
        node9.next=node10;
        node10.next=node11;
        node11.next=node12;
        node12.next=null;
        System.out.println(isHuiWen12(node8));
        node8.print(node8);

    }

    /**
     * 打印两个链表的公共部分
     * @param head1
     * @param head2
     */
    //谁小谁移动，相同就一起移动，并且打印
    public static void printTwoLinkedListCommonPart(Node head1,Node head2){
        Node p1=head1;
        Node p2=head2;
        while (p1!=null&&p2!=null){
            //谁小谁移动,相等就打印，一起移动
            if(p1.id<p2.id){
                p1=p1.next;
            }else if(p1.id>p2.id){
                p2=p2.next;
            }else {
                System.out.println(p1.id);
                p1=p1.next;
                p2=p2.next;
            }
        }
    }


    /**
     * 判断链表是否是回文链表
     */
    public static boolean isHuiWen1(Node head){
        /*if(head==null)
            return false;
        StringBuilder stringBuilder=new StringBuilder();
        StringBuilder reverse=new StringBuilder();
        while (head!=null){
            stringBuilder.append(head.id);
            reverse.append(head.id);
            head=head.next;
        }
        return reverse.reverse().toString().equals(stringBuilder.toString());*/

        if(head==null)
            return false;
        Stack<Node> list=new Stack<>();
        Node temp=head;
        if(temp.next==null)
            return true;
        while(temp!=null){
            list.push(temp);//压栈
            temp=temp.next;
        }
        temp=head;
        while(temp!=null){
            if(list.pop().id!=temp.id)
            {
                return false;
            }
            temp=temp.next;
        }
        return true;
    }


    /**
     * 判断链表是否是回文链表,额外空间复杂度为O(1)，使用快慢指针
     */
    public static boolean isHuiWen12(Node head){
        if(head==null||head.next==null)
            return true;
        Node slow=head;//慢指针
        Node fast=head;//快指针
        boolean result=true;

        while (fast!=null&&fast.next!=null){
           slow=slow.next;
           fast=fast.next.next;
        }
        //如果链表节点是偶数，fast指向null，slow指向n/2+1
        //如果链表节点是奇数，fast指向最后一个节点，slow刚好指向中间那个节点
        Node temp=slow;
        if(fast!=null){//节点为奇数时
            slow=slow.next;
        }
        temp.next=null;
        Node temp1=reverse(slow);
        slow=temp1;
        fast=head;
        while (slow!=null){
            if(slow.id!=fast.id){
                result=false;
                break;
            }
            slow=slow.next;
            fast=fast.next;
        }

        //将链表恢复成原状
        fast=head;
        Node temp2 = reverse(temp1);
        while (fast.next!=null){
            fast= fast.next;
        }
        fast.next=temp2;
        return result;
    }

    private static Node reverse(Node slow) {
        Node pre=null;
        Node cur=slow;
        Node temp;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }

}
