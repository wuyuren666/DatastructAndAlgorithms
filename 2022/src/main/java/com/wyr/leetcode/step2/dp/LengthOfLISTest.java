package com.wyr.leetcode.step2.dp;

import java.util.LinkedList;

public class LengthOfLISTest {
    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     *
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int lengthOfLIS(int[] nums) {
        int N=nums.length;
        int ans=1;
        //dp[i]代表以下标i位置的数结尾的最长严格递增子序列的长度。
        int [] dp=new int[N]; //初始化的时候全都是0
        dp[0]=1;
        for(int i=1;i<N;i++){
            //确实不能找到0...i-1下标上比num[i]小的数，dp[i]这个值还是0
            //寻找0...i-1下标上比num[i]小的数
            for(int j=0;j<i;j++){
                //找到比他小的数
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]);
                }
            }
            //如果dp[i]还是0，说明没找到小的数
            dp[i]=dp[i]==0?1:dp[i]+1;
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int [] nums={0,1,0,3,2,3};
        System.out.println(lengthOfLIS(nums));
    }
}
