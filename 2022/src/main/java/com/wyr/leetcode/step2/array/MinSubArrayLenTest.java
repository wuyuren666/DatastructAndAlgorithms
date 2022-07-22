package com.wyr.leetcode.step2.array;

public class MinSubArrayLenTest {
    /**
     * 给定一个含有n个正整数的数组和一个正整数 target 。
     *
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int N=nums.length;
        int L=0;
        int R=0;
        int tempSum=0;
        int resLen=Integer.MAX_VALUE;
        while (R<N){
            tempSum+=nums[R];
            while (tempSum>=target){
                resLen=Math.min(resLen,R-L+1);
                tempSum-=nums[L++];
            }
            R++;
        }
        return (resLen==Integer.MAX_VALUE?0:resLen);
    }
}


