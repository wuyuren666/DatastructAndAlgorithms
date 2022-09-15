package com.wyr.bigFactory.date9_1;

import java.util.LinkedList;
import java.util.Scanner;

public class BaoShiTest {
    /**
     * 小美有一个精致的珠宝链子。初始这个链子上有n个宝石，从左到右
     * 分别编号1～n（宝石上的编号不会因为交换位置而改变编号）。接
     * 着，小美为了美观对这个项链进行微调，有m次错做，每一次选择一个
     * 编号x，将编号x的宝石放在最左边（不改变其他宝石的相对位置）。
     * 小美想知道，所有操作完成后最终链子从左到右编号是多少
     *
     * 输入描述：
     * 第一行两个正整数n和m，分别表示链子上的宝石数和操作的次数
     * 接下来的一行m个数，依次表示每次操作选择的编号x值。
     * 数字之间两两有空格隔开
     *
     * 输出描述：
     * 最终宝石编号位置
     *
     *
     * 样例输入
     * 5 3
     * 2 3 4
     *
     * 样例输出
     * 4 3 2 1 5
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();//宝石数
        int m=scanner.nextInt();//操作数
        scanner.nextLine();
        int [] index=new int[m];
        //index：放的是需要交换的宝石的编号
        for(int i=0;i<m;i++){
            index[i]=scanner.nextInt();
        }
        //头节点
        Node head=new Node(1);
        Node temp=head;
        for(int i=2;i<=n;i++){
            temp.next=new Node(i);
            temp=temp.next;
        }
        temp.next=null;//构造成一个单链表
        for(int i=0;i<m;i++){
            Node pre=getPreNode(head, index[i]);
            //返回的是null，即交换的编号的宝石就在第一个
            if(pre==null){
                continue;
            }
            temp = pre.next;//temp先保存当前节点
            pre.next=pre.next.next; //断开
            temp.next=(pre==head?pre:head);
            head=temp;
        }
        while(head!=null){
            System.out.print(head.index+" ");
            head=head.next;
        }
        scanner.close();
    }
    //给一个链表头节点，和编号值，返回这个编号节点的前一个节点
    private static Node getPreNode(Node head, int index) {
        Node temp=head;
        if(temp.index==index){
            return null;
        }
        while (temp.next.index!=index){
            temp=temp.next;
        }
        return temp;
    }

    public static class Node{
        public int index;
        public Node next;

        public Node(int index) {
            this.index = index;
        }

        public Node() {
        }

        public Node(int index, Node next) {
            this.index = index;
            this.next = next;
        }
    }
}
