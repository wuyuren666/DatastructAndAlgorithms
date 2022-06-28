package com.wyr.zuoshen.zuoshen1.p4;


/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点，如果不相交请返回null
 */
@SuppressWarnings("all")
public class TwoListCrossed {

    public static Node isTwoListCrossed(Node head1, Node head2) {
        if (head1 == null || head2 == null)
            return null;
        if (isSingleListHasCycle(head1) == null && isSingleListHasCycle(head2) == null) { //两个都是无环链表

            Node cur1 = head1;
            Node cur2 = head2;
            int length1 = 0;
            int length2 = 0;
            while (cur1.next != null) {
                length1++;
                cur1 = cur1.next;
            }
            while (cur2.next != null) {
                length2++;
                cur2 = cur2.next;
            }
            /**
             * 情况一：两个链表都无环，调用isSingleListHasCycle都返回null，
             *          分别遍历这两个链表，遍历到最后一个节点，得到end1和end2
             *          如果end1和end2指向的不是同一个堆内对象，那么不相交
             */
            if (cur1 != cur2)
                return null;
            /**
             * 情况二：两个链表都无环，调用isSingleListHasCycle都返回null，
             *          分别遍历这两个链表，遍历到最后一个节点，分别得到end1和length1；end2和length2
             *          如果end1和end2指向的是同一个堆内对象，那么会相交
             *          用大的链表长度减去小的链表长度，得到长度差值
             *          长链表走头走差值步，短链表开始走，那么他们会在第一个相交的节点处相遇
             */
            if (length1 == length2) { //两个链表长度相等
                cur1 = head1; //cur1指向第一个链表头节点
                cur2 = head2; //cur2指向第二个链表头节点
                while (cur1 != cur2) {
                    cur1 = cur1.next;
                    cur2 = cur2.next;
                }
                //while循环退出时，cur1和cur2指向这两个无环链表的相交的第一个节点
                return cur1;
            } else {//两个链表长度不想等
                cur1 = length1 > length2 ? head1 : head2;//让cur1指向长度较长的链表
                cur2 = cur1 == head1 ? head2 : head1;//cur2指向长度较短的链表
                int n = Math.abs(length1 - length2);
                while (n != 0) { //长链表先走差值步
                    n--;
                    cur1 = cur1.next;
                }
                while (cur1 != cur2) { //两个链表同时走
                    cur1 = cur1.next;
                    cur2 = cur2.next;
                }
                //上面的while循环退出后，cur1和cur2都指向两个无环链表的相交的入口处
                return cur1;
            }
        } else if (isSingleListHasCycle(head1) == null && isSingleListHasCycle(head2) != null || //单链表，一个链表有环一个链表无环，肯定不会相交
                isSingleListHasCycle(head1) != null && isSingleListHasCycle(head2) == null) {

            return null;

        } else {//两个单链表都是有环的
            Node loop1 = isSingleListHasCycle(head1);
            Node loop2 = isSingleListHasCycle(head2);
            Node cur1 = head1;
            Node cur2 = head2;
            /**
             * 情况一：
             *  假设返回两个链表的入口节点分别为loop1和loop2
             *  如果loop1==loop2，即这两个环状单链表在同一个位置入环
             *  这个时候，我们做法和第一个if中的情况二相似，不再赘述
             */
            if (loop1 == loop2) {
                int length1 = 0;
                int length2 = 0;
                while (cur1 != loop1) {
                    length1++;
                    cur1 = cur1.next;
                }
                while (cur2 != loop1) {
                    length2++;
                    cur2 = cur2.next;
                }
                if (length1 == length2) {
                    cur1 = head1;
                    cur2 = head2;
                    while (cur1 != cur2) {
                        cur1 = cur1.next;
                        cur2 = cur2.next;
                    }
                    return cur1;
                } else {
                    cur1 = length1 > length2 ? head1 : head2;//cur1指向较长的
                    cur2 = cur1 == head1 ? head2 : head1;
                    int n = Math.abs(length1 - length2);
                    while (n != 0) {
                        n--;
                        cur1 = cur1.next;
                    }
                    //同时走
                    while (cur1 != cur2) {
                        cur1 = cur1.next;
                        cur2 = cur2.next;
                    }
                    return cur1;
                }
            } else {//loop1!=loop2
                /**
                 * 情况二：
                 *  假设返回两个链表的入口节点分别为loop1和loop2
                 *  如果loop1!=loop2，可能这两个链表不想干，各自在那自环，不相交
                 *  如果loop让他走一圈，没有碰到loop2，那么这两个成环单链表就是各玩各的
                 */
                Node temp1 = loop1;
                boolean flag = false;
                while (temp1.next != loop1) {
                    temp1 = temp1.next;
                    if (temp1 == loop2) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return null;
                } else {
                    /**
                     * 情况三 如果loop1!=loop2，可能这两个链表有共同的一个环
                     * 如果loop让他走一圈，碰到了loop2，这两个链表有共同的一个环
                     */
                    //返回loop1或者loop2都行
                    return loop2;
                }
            }

        }

    }



    /**
     * 一个单链表，判断是否有环？
     * 如果有环，返回入环的第一个节点
     * 如果没有环返回null
     *
     * 思路：快慢指针，第一次循环如果 head!=null&&head.next!=null代表有环，快慢指针注定会在环上相遇
     * 相遇后，快指针回到头节点
     * 接着，快慢指针都同时一次走一步，直到他们再次相遇，相遇的肯定是环的入口
     */
    public static Node isSingleListHasCycle(Node head){
         if(head==null||head.next==null){ //链表为空，或者链表只有一个节点，下一个节点指向null，返回空
             return null;
         }
         if(head.next==head){ //链表只有一个节点，且这个节点的next指向自己，返回自己
             return head;
         }
         Node slow=head,fast=head;//定义快慢指针都指向头节点

         while(fast!=null&&fast.next!=null){
             slow=slow.next;//慢指针一次走一步
             fast=fast.next.next;//快指针一次走两步
             if(fast==slow){ //如果相遇，代表这个单链表肯定有环，此时break
                 break;
             }
         }
         if(fast==null||fast.next==null)
             return null;
        //让快指针移到head，慢指针不动
        fast = head;
        //接下来，慢指针和快指针都每次同时走一步，
        // 如果他们再次相遇，那么相遇的那么节点就是环的入口
        while(fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);Node node2 = new Node(2);Node node3 = new Node(3);Node node4 = new Node(4);
        Node node5 = new Node(5);Node node6 = new Node(6);Node node7 = new Node(7);Node node8 = new Node(8);
        Node node9 = new Node(9);Node node10 = new Node(10);Node node11= new Node(11);Node node12= new Node(12);
        Node node13= new Node(13);Node node14= new Node(14);Node node15= new Node(15);Node node16= new Node(16);

        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        node7.next=node8;
        node8.next=node9;
        node9.next= node6;

        node10.next=node11;
        node11.next=node12;
        node12.next=node13;
        node13.next=node14;
        node14.next=node15;
        node15.next=node6;


        System.out.println(isTwoListCrossed(node2, node10));
    }
}
