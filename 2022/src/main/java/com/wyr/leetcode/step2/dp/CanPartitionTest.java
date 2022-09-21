package com.wyr.leetcode.step2.dp;

import java.util.Objects;

public class CanPartitionTest {

    /**
     * 给你一个 只包含正整数 的 非空 数组 nums 。
     * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     *
     * https://leetcode.cn/problems/partition-equal-subset-sum/
     */
    public static boolean canPartition(int[] nums) {
        //转换成背包问题，我有一个背包容量为sum/2的背包
        //然后我是否可以选num.length/2个数刚好装满这个背包？
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if((sum&1)!=0)
            return false;
        return process(nums,sum/2,0,0);
    }


    //改成动态规划
    public static boolean canPartitionDP(int[] nums) {
        //转换成背包问题，我有一个背包容量为sum/2的背包
        //然后我是否可以选num.length/2个数刚好装满这个背包？
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if((sum&1)!=0)
            return false;
        int M=nums.length+1;
        int N=sum/2+1;
        boolean [][] dp=new boolean[M][N];

        dp[nums.length][sum/2]=true;
        for(int i=M-2;i>=0;i--){
            for(int j=0;j<N;j++){
                boolean b1=dp[i+1][j];
                boolean b2=false;
                if(j+nums[i]<N){
                    b2=dp[i+1][j+nums[i]];
                }
                dp[i][j]=b1||b2;
            }
        }
        return dp[0][0];
    }

    public static boolean process(int [] nums, int sum, int index, int tempSum){
        if(index==nums.length){
            if(sum==tempSum){
                return true;
            }
            return false;
        }
        return process(nums,sum,index+1,tempSum)
                || process(nums,sum,index+1,tempSum+nums[index]);
    }

    public static void main(String[] args) {



    }
}
