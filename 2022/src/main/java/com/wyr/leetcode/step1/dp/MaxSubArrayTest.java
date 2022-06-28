package com.wyr.leetcode.step1.dp;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素）
 * 返回其最大和子数组 是数组中的一个连续部分。
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 */
public class MaxSubArrayTest {


    //最好
    public static int maxSubArray3(int[] nums) {
        int n=nums.length;
        // dp[i]的含义：以nums[i]结尾的连续子数组的最大和
        int [] dp=new int [n];
        dp[0]=nums[0];
        int maxSum=dp[0];
        for(int i=1;i<n;i++){
            //前面的以nums[i-1]结尾的连续子数组的和如果为正数，就加入
            if(dp[i-1]>0)
                dp[i]=dp[i-1]+nums[i];
            else //以nums[i-1]结尾的连续子数组的和为负数
                dp[i]=nums[i];
            maxSum=dp[i]>maxSum?dp[i]:maxSum;
        }
        return maxSum;
    }

    //暴力递归版本
    public static int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) //数组为空或长度为0
            return 0;
        if(nums.length==1) //数组长度为1
            return nums[0];
        int maxResult=process(nums,0); //maxResult初始化为从0位置开始所求得的最大和子数组
        for(int i=1;i<nums.length;i++) { //从1位置开始遍历
            maxResult=Math.max(process(nums,i),maxResult);
        }
        return maxResult;
    }
    //代表从index位置开始，可以选择不走，也可以选择走一步
    public static int process(int [] arr, int index){
        if(index==arr.length) //index越界返回0
            return 0;
        //选择不走直接结束
        int p1=arr[index];
        //选择走1步
        int p2=arr[index]+process(arr,index+1);
        return Math.max(p1,p2);
    }

    //动态规划版本，不太好（时间复杂度高）
    public static int dp(int []nums){
        if(nums==null||nums.length==0) //数组为空或长度为0
            return 0;
        if(nums.length==1) //数组长度为1
            return nums[0];
        int N=nums.length;
        int[]dp=new int[N+1];
        int maxResult=0;
        for(int i=0;i<=N-1;i++){
            for(int j=N-1;j>=i;j--){
                //选择不走直接结束
                int p1=nums[j];
                //选择走1步
                int p2=nums[j]+dp[j+1];

                dp[j]=Math.max(p1,p2);
            }
            maxResult=i==0?dp[0]:Math.max(maxResult,dp[i]);
        }
        return maxResult;
    }

    //次优(空间复杂度高)
    public static int maxSubArray2(int[] nums) {
        int n=nums.length;
        int [][] dp=new int [n][n];

        int maxSum=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            dp[i][i]=nums[i];
            if(dp[i][i]>maxSum){
                maxSum=dp[i][i];
            }
        }
        for(int i=0;i<n-1;i++){ //行
            for(int j=i+1;j<n;j++){
                dp[i][j]=dp[i][j-1]+nums[j];
                if(dp[i][j]>maxSum){
                    maxSum=dp[i][j];
                }
            }
        }
        return maxSum;
    }



    public static void main(String[] args) {
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray2(nums);
        /*System.out.println(dp(nums));
        System.out.println(maxSubArray(nums));
        System.out.println(Math.max(Integer.MAX_VALUE,Math.pow(10,9)));*/
    }
}
