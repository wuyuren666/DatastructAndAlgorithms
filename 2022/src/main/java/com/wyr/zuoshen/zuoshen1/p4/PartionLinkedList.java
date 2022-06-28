package com.wyr.zuoshen.zuoshen1.p4;

public class PartionLinkedList {
    public static void main(String[] args) {
        Node node1 = new Node(4);
        Node node2 = new Node(6);
        Node node3 = new Node(3);
        Node node4 = new Node(5);
        Node node5 = new Node(8);
        Node node6 = new Node(5);
        Node node7 = new Node(2);
        Node node8 = new Node(5);


        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        node7.next=node8;
        node8.next=null;

        Node head = partion2(node1, 5);
        head.print(head);
    }


    /**
     * 将单向链表按某值划分为左边小，中间相等，右边大的形式
     * 使用快排的思想，但是保证不了稳定性
     * @param head
     * @param x
     * @return
     */
    private static Node partion1(Node head, int x) {
        if(head==null){
            return null;
        }
        //首先，将这个链表保存在数组中
        Node cur=head;
        int countNode=0;
        //统计节点个数
        while (cur!=null){
            countNode++;
            cur=cur.next;
        }
        cur=head;
        int countX=0;
        Node [] nodeArr=new Node[countNode];
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i]=cur;
            if(nodeArr[i].id==x)
                countX++;
            cur=cur.next;
        }
        //统计x在nodeArr中出现的次数,将其出现的下标保存在数组中
        int[] xIndex=new int[countX];
        for (int i = 0,j=0; i < nodeArr.length; i++) {
            if(nodeArr[i].id==x){
                xIndex[j++]=i;
            }
        }

        for (int i = 0; i < xIndex.length; i++) {
            swap(nodeArr,0,xIndex[i]);
            int left=0;
            int right=nodeArr.length-1;
            Node temp=nodeArr[0];
            while (left<right){
                while (left<right&&nodeArr[right].id>=temp.id){
                    right--;
                }
                if(left<right){
                    nodeArr[left++]=nodeArr[right];
                }
                while (left<right&&nodeArr[left].id<=temp.id){
                    left++;
                }
                if(left<right){
                    nodeArr[right--]=nodeArr[left];
                }
            }
            nodeArr[left]=temp;
        }
        for (int i = 0; i < nodeArr.length; i++) {
            if(i+1<nodeArr.length)
            nodeArr[i].next=nodeArr[i+1];
            else
                nodeArr[i].next=null;
        }
        return nodeArr[0];
    }


    /**
     * 将单向链表按某值划分为左边小，中间相等，右边大的形式
     * 假设我们分为3快区域，小于区域，等于区域，大于区域
     * 并且每个区域我们有两个指针分别指向每个区域的头尾
     */
    public static Node partion2(Node head,int x) {
        Node sH=null; //small head
        Node sT=null;//small tail
        Node eH=null;//equal head
        Node eT=null;//equal head
        Node mH=null;//big head
        Node mT=null;//big head

        Node next=null;//save next node

        while (head!=null){
            next=head.next;
            head.next=null;
            if(head.id<x){
                if(sH==null){
                    sH=head;
                    sT=head;
                }else {
                    sT.next=head;
                    sT=head;
                }
            }
            if(head.id==x){
                if(eH==null){
                    eH=head;
                    eT=head;
                }else {
                    eT.next=head;
                    eT=head;
                }
            }
            if(head.id>x){
                if(mH==null){
                    mH=head;
                    mT=head;
                }else {
                    mT.next=head;
                    mT=head;
                }
            }
            head=next;
        }
        //最后，我们只需要将这些区域连起来，但是要考虑，这些区域是否存在的问题
        if(sT!=null){//如果有小于区域
            sT.next=eH;
            eT=eT==null?sT:eT;//下一步，谁去连大于区域的头，谁就编程eT
        }
        if (eT!=null){
            eT.next=mH;
        }
        return sH!=null?sH:(eH!=null?eH:mH);
    }


    public static void swap(Node [] nodeArr,int x, int y){
        Node temp=nodeArr[x];
        nodeArr[x]=nodeArr[y];
        nodeArr[y]=temp;
    }

}
