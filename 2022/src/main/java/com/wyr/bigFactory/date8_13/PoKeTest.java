package com.wyr.bigFactory.date8_13;

import java.util.LinkedList;
import java.util.Scanner;

public class PoKeTest {

    /**
     * 两个人玩扑克，有 n 张牌，两个人依次从牌顶拿牌放到牌底，然后拿出牌顶的牌，记下数字；
     * 如此反复，直到所有牌都被拿走，记下了 n 个数字，现在需要根据这 n 个数字输出原来牌的顺序（从牌顶到牌底）。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = in.nextInt();
        }
        if (n < 3) {
            for (int i = 0; i < n; ++i) {
                System.out.print(nums[i] + " ");
            }
            return;
        }
        int[] res = new int[n];

        LinkedList<Integer> linkedList = new LinkedList<>();

        //这个逻辑是和下面那个反着推
        for (int i = n - 1; i >= 0; --i) {
            linkedList.addFirst(nums[i]);
            linkedList.addFirst(linkedList.removeLast());
            linkedList.addFirst(linkedList.removeLast());
        }

        //这个逻辑是正着推
        //for (int i = 0; i < n; ++i) {
        //	linkedList.addLast(linkedList.removeFirst());
        //	linkedList.addLast(linkedList.removeFirst());
        //	res[i] = linkedList.removeFirst();
        //	System.out.print(res[i] + " ");
        //}

        for (Integer integer : linkedList) {
            System.out.print(integer + " ");
        }
    }

}
