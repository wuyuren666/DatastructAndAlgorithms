package com.wyr.zuoshen.zuoshen2.p32;

public class Code3 {
    /**
     * 中等
     * 给定两个 非空链表 l1和 l2来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/lMSNwu
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
     * 输出：[7,8,0,7]
     */

    //其实就是加法的竖式运算
    //先将链表反转（就是从个位开始加，有进位就进位）
    //主要就看较长的那个数
    //最终结果的位数要么就是较长的数的位数，比如7274+564=7807，即最终结果仍然是4位
    //要么就是结果的位数比较长的数的位数多一位，比如：999+200=1199，且最高位只会是1
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1=getListLength(l1); //第一个链表的长度
        int length2=getListLength(l2); //第二个链表的长度
        if(length1==1&&l1.val==0){ //特殊情况
            return l2;
        }
        if(length2==1&&l2.val==0){ //特殊情况
            return l1;
        }
        ListNode reverseL1Head=reverse(l1);//将第一个链表反转
        ListNode reverseL2Head=reverse(l2);//将第二个链表反转
        ListNode shortHead=null; //指向较短的链表
        ListNode longHead=null;  //指向较短的链表
        if(length1<=length2){
            shortHead=reverseL1Head;
            longHead=reverseL2Head;
        }else if(length1>length2){
            shortHead=reverseL2Head;
            longHead=reverseL1Head;
        }


        ListNode temp1=longHead; //辅助指针，用来找到链表的最后一个节点
        ListNode temp2=longHead; //辅助指针，用来保存头节点
        int info=0;//进位信息
        int value=0;//按位相加的信息
        //第一阶段，同时移动，直到短链表越界
        while(shortHead!=null){
            value=shortHead.val+longHead.val+info;
            if(value<10){
                longHead.val=value;
                info=0;
            }else{
                longHead.val=value%10;
                info=1;
            }
            shortHead=shortHead.next;
            longHead=longHead.next;
        }

        //第二阶段，长链表还剩节点的逻辑
        while(longHead!=null){
            value=longHead.val+info;
            if(value<10){
                longHead.val=value;
                info=0;
            }else{
                longHead.val=value%10;
                info=1;
            }
            longHead=longHead.next;
        }

        //使temp1指向长链表的最后一个节点
        while(temp1.next!=null){
            temp1=temp1.next;
        }

        //第三阶段，长链表都遍历完了，还有进位信息，则补节点
        if(info==1){
            temp1.next=new ListNode(1);//最高位只会是1
            temp1.next.next=null;
            return reverse(temp2);
        }else{
            return reverse(temp2);
        }
    }


    //定义一个方法，返回每个链表的长度
    public int getListLength(ListNode head){
        int count=0;
        while(head!=null){
            head=head.next;
            count++;
        }
        return count;
    }


    //反转链表的方法
    public ListNode reverse(ListNode head){
        ListNode cur=head;
        ListNode temp=null;
        ListNode pre=null;
        while(cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
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

    public static void main(String[] args) {

    }


}
