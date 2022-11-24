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
        if(nums==null||nums.length==0){
            return new int[]{-1,-1};
        }
        int p1=0;
        int p2=nums.length-1;
        int res=-1;
        while(p1<=p2){ //二分法寻找>=target的最左边的下标
            int mid=(p1+p2)/2;
            if(nums[mid]>=target){
                res=mid;
                p2=mid-1;
            }else{
                p1=mid+1;
            }
        }

        if(res==-1){ //没有>=target的最左边的下标，说明target比这个数组中的所有数都小
            return new int[]{-1,-1};
        }else{
            if(nums[res]!=target){ //nums中没有target这个数
                return new int[]{-1,-1};
            }else{ //nums中有target这个数
                //下面需要看是否多次出现
                int i=res+1;
                for(;i<nums.length;i++){
                    if(nums[i]!=target){
                        break;
                    }
                }
                return new int[]{res,i-1};
            }
        }
    }




}
