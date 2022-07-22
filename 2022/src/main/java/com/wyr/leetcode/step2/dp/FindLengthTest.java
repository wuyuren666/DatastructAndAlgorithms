package com.wyr.leetcode.step2.dp;

public class FindLengthTest {
    /**
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *
     *
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     *
     * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
     * 输出：5
     *
     * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
     */


    public int findLength(int[] nums1, int[] nums2) {
        int N1=nums1.length;
        int N2=nums2.length;
        //dp[i][j]代表在nums1[0...i]范围上和nums2[0...j]范围上的最长公共子数组的长度
        int [][] dp=new int [N1][N2];
        //先填上边和左边
        int ans=Integer.MIN_VALUE;
        for(int j=0;j<N2;j++){
            dp[0][j]=nums1[0]==nums2[j]?1:0;
            ans=Math.max(ans,dp[0][j]);
        }
        for(int i=0;i<N1;i++){
            dp[i][0]=nums1[i]==nums2[0]?1:0;
            ans=Math.max(ans,dp[i][0]);
        }

        for(int i=1;i<N1;i++){
            for(int j=1;j<N2;j++){
                dp[i][j]=nums1[i]==nums2[j]?1+dp[i-1][j-1]:0;
                ans=Math.max(ans,dp[i][j]);
            }
        }
        return ans;
    }
}
