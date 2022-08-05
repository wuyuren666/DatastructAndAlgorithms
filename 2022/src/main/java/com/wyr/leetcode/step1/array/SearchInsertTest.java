package com.wyr.leetcode.step1.array;

/**
 * https://leetcode.cn/problems/search-insert-position/
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 */
public class SearchInsertTest {

    public int searchInsert(int[] nums, int target) {
        //使用二分法，就是找到数组中>=target的最左边的下标（就是第一个>=target的下标）
        int left=0;
        int right=nums.length-1;
        int res=-1;
        while(left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]>=target){
                right=mid-1;
                res=mid;
            }else{
                left=mid+1;
            }
        }
        return res==-1?nums.length:res;
    }
}
