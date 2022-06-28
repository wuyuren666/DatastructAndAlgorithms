package com.wyr.leetcode.step1.dp;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class ClimbStairsTest {

    public static int climbStairs(int n){
        if(n==1)
            return 1;
        int [] dp=new int[n+2];//缓存数组
        for(int i=0;i<dp.length;i++){
            dp[i]=-1;
        }
        return process(0,n,dp);
    }

    public static int process(int cur,int aim ,int[] dp){
        if(dp[cur]!=-1) //查看缓存
            return dp[cur];

        int result;
        if(cur==aim){ //当前位置==目标位置，得到一种解决方法
            result=1;
        }else if(cur>aim){ //当前位置>目标位置，没有解决方法
            result=0;
        }else { //当前位置，还没有来到目标位置
            result=process(cur+1,aim,dp)+process(cur+2,aim,dp);
        }
        dp[cur]=result;
        return result;
    }


    //动态规划版本
    public static int dp(int n){
        if(n==1)
            return 1;
        int [] dp=new int[n+2];//缓存数组
        dp[n]=1;
        dp[n+1]=0;
        for(int i=n-1;i>=0;i--){
            dp[i]=dp[i+1]+dp[i+2];
        }
        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(climbStairs(5));
        System.out.println(dp(5));
    }
}
