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
        int L=0; //最左边的下标
        int R=nums.length-1; //最右边的下标
        int mid=0; //中间下标
        int res=-1; //返回结果
        while(L<=R){
            mid=L+((R-L)>>1);
            if(nums[mid]>=target){
                res=mid;
                R=mid-1;
            }else {
                L=mid+1;
            }
        }
        if(res==-1){
            return nums.length;
        }
        return res;
    }
}
