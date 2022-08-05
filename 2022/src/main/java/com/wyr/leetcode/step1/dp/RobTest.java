package com.wyr.leetcode.step1.dp;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * https://leetcode.cn/problems/house-robber/
 */
public class RobTest {
    //暴力递归版本
    public static int rob(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        return process(nums,0);
    }

    public static int process(int[] nums, int index){
        if(index>=nums.length)
            return 0;
        //在index处选择偷
        int p1=nums[index]+process(nums,index+2);
        //在index处选择不偷
        int p2=process(nums,index+1);
        return Math.max(p1,p2);
    }


    //动态规划版本
    public static int robDb(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        int N=nums.length;
        int [] dp=new int[N+2];
        dp[N+1]=0;
        dp[N]=0;
        for(int i=N-1;i>=0;i--){
            dp[i]=Math.max(nums[i]+dp[i+2],dp[i+1]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int [] nums={1,2,3,1,2,4,3,5,2,5,1,0};
        System.out.println(rob(nums));
        System.out.println(robDb(nums));
    }


}
