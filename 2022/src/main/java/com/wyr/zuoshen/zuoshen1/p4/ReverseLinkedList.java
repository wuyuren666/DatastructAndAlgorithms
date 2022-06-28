package com.wyr.zuoshen.zuoshen1.p4;

public class ReverseLinkedList {
    public static void main(String[] args) {
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
        Node node = reverseSingleLinkedList(node1);
        node.print(node);


    }



    //单向链表反转(此迭代的方法需要定义三个变量 pre保存当前节点cur的前一个节点；cur代表当前节点；temp保存当前节点的下一个节点)
    public static Node reverseSingleLinkedList(Node head){
        if(head==null)
            return null;
        Node pre=null;//指向当前节点的前一个节点
        Node cur=head;//指向当前节点
        Node temp;//临时变量
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }
}

class Node{
    public int id;
    public Node previous;
    public Node next;
    public Node ramdom;

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
