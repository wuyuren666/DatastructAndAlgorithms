package com.wyr.zuoshen.zuoshen2.p32;

public class Code1 {
    /**
     * 中等题
     * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reverse-linked-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     *
     * 输入：head = [5], left = 1, right = 1
     * 输出：[5]
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode n1=getKthNode(head,left);
        ListNode n2=getKthNode(head,right);
        //四种情况
        if(head==n1){//代表n1是头节点
            if(n2.next==null){ //代表n1是头节点，n2是尾节点。
                return reverse(n1);
            }else{ //代表n1是头节点，n2不不是尾节点。
                ListNode n2Next=n2.next; //先将n2的下一个节点保存下来
                n2.next=null; //然后将n2的next置为空
                ListNode reverseHead=reverse(n1); //反转去吧，将反转后的头节点保存，即在这种情况下我们需要返回的结果
                ListNode temp1=reverseHead; //定义辅助指针，用来定位到反转后的链表的最后一个节点
                while(temp1.next!=null){
                    temp1=temp1.next;
                }
                temp1.next=n2Next; //拼接
                return reverseHead; //返回结果
            }
        }else{//n1不是头节点
            if(n2.next==null){//n1不是头节点，n2是尾节点
                ListNode temp2=head;
                while(temp2.next!=n1){ //让temp2到达原链表的n1的前一个节点
                    temp2=temp2.next;
                }
                temp2.next=reverse(n1);//拼接
                return head;
            }else{//n1不是头节点，n2不是尾节点
                ListNode temp3=head;
                while(temp3.next!=n1){ //让temp3到达原链表的n1的前一个节点
                    temp3=temp3.next;
                }
                ListNode temp4=n2.next; //将n2的后一个节点记录下来
                n2.next=null; //将n2的后一个节点置为null，便于反转
                ListNode temp5=reverse(n1); //temp5保存反转后的新链表的头节点
                ListNode temp6=temp5;
                while(temp6.next!=null){ //temp6用来寻找反转后的链表的最后一个节点
                    temp6=temp6.next;
                }
                //temp3->temp5；temp6->temp4
                temp6.next=temp4;
                temp3.next=temp5;
                return head;
            }
        }
    }


    //设计一个函数，给你一个头节点，你走k步到达某个节点，返回这个节点
    public ListNode getKthNode(ListNode head, int k){
        if(k==1){
            return head;
        }else{
            while(head!=null&&k>1){
                head=head.next;
                k--;
            }
            return head;
        }
    }

    //反转单链表的方法
    public ListNode reverse(ListNode head){
        ListNode cur=head;
        ListNode pre=null;
        ListNode temp=null;
        while(cur!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }



    //节点类
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





    /**
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
}
