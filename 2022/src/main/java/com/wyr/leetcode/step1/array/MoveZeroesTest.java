package com.wyr.leetcode.step1.array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。\
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 输入: nums = [0]
 * 输出: [0]
 */
public class MoveZeroesTest {
    public static void moveZeroes(int[] nums) {
        //双指针，指针1，指针2开始的时候都指向0位置
        //指针1每次都向右移动，如果指向的数==0不做任何事，否则nums[指针2++]=nums[i]
        int p1=0,p2=0;
        for(;p1<nums.length;p1++){
            if(nums[p1]!=0){
                nums[p2++]=nums[p1];
            }
        }
        //后面补零
        for(;p2<nums.length;p2++){
            nums[p2]=0;
        }
    }
}
