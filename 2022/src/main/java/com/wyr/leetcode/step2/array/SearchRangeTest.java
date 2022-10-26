package com.wyr.leetcode.step2.array;

public class SearchRangeTest {
    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     *
     * 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0){
            return new int[]{-1,-1};
        }

        int left=0;
        int right=nums.length-1;
        int ans1=-1; //记录结果
        int ans2=-1; //记录结
        while(left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]>=target){
                ans1=mid;
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        left=0;
        right=nums.length-1;
        while(left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]<=target){
                ans2=mid;
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        int [] res=new int[2];
        if(ans1==-1){
            res[0]=-1;
        }else{
            res[0]=nums[ans1]==target?ans1:-1;
        }
        if(ans2==-1){
            res[1]=-1;
        }else{
            res[1]=nums[ans2]==target?ans2:-1;
        }
        return res;
    }




}
