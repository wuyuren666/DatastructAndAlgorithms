package com.wyr.leetcode.step1.array;


/**
 *  给你一个升序排列的数组 nums ，请你原地删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长度。元素的相对顺序应该保持一致。
 *     输入：nums = [0,0,1,1,1,2,2,3,3,4]
 *     输出：5, nums = [0,1,2,3,4]
 *     解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *     https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesTest {
    public static void main(String[] args) {
        int [] nums={0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if(nums.length==1){
            return 1;
        }
        int counts=1;
        int p1=0,p2=1; //两个指针
        for(;p2<nums.length;){
            if(nums[p1]==nums[p2]){ //相等,p2++
                p2++;
            }else{ //不相等，1：赋值 2：计数+1 3：p2++
                nums[++p1]=nums[p2];
                counts++;
                p2++;
            }
        }
        return counts;
    }





}
