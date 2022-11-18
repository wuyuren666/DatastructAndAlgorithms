package com.wyr.leetcode.step1.array;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class SingleNumberTest {

    public static int singleNumber(int[] nums) {
        // 任何数和0异或都是本身
        // 自己异或自己 是 0
        //a^a==0,  0^a==a
        int eor=0;
        for(int i:nums){
            eor=eor^i;
        }
        return eor;
    }
}
