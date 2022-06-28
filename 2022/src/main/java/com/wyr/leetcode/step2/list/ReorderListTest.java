package com.wyr.leetcode.step2.list;

import java.util.Stack;

public class ReorderListTest {
    /**
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     *
     * L0→ L1→ … → Ln-1→ Ln
     * 请将其重新排列后变为：
     *
     * L0→Ln→L1→Ln-1→L2→Ln-2→ …
     *
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 输入: head = [1,2,3,4]
     * 输出: [1,4,2,3]
     *
     * 输入: head = [1,2,3,4,5]
     * 输出: [1,5,2,4,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/LGjMqU
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //没有返回值的意思是，从head开始，往后遍历就能得到结果
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();//用栈保存节点,为了逆序弹出节点
        int count = getLength(head, stack); //获得链表长度，同时填栈
        if (count == 1 || count == 2) {
            return;
        }
        ListNode temp = head; //辅助指针
        ListNode tempNext = null; //保存temp的下一个节点

        for (int i = 0; i < (count >> 1); i++) { //coding问题，自己画图
            tempNext = temp.next;
            temp.next = stack.pop();
            temp = temp.next;
            temp.next = tempNext;
            temp = temp.next;
        }
        temp.next = null;
    }

    public int getLength(ListNode head, Stack<ListNode> stack) {
        int count = 0;
        while (head != null) {
            stack.push(head);
            count++;
            head = head.next;
        }
        return count;
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



