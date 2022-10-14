package com.wyr.leetcode.step2.dp;

import java.util.Arrays;

public class CoinChangeTest {
    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
     *
     * 你可以认为每种硬币的数量是无限的。
     *
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     * 这个问题不也可以转换成爬楼梯问题吗？
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/coin-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //动态规划
    public static int coinChange(int[] coins, int amount) {
        //dp[i]，代表我兑换i零钱所需要的最小硬币数
        //dp[i]=min(1枚当前面值的硬币+dp[i-当前面值])

        //意思就是如果我兑换的零钱是3元，那么最少硬币数的组成可能是一下这些可能性中的最小的
        // 1(我拿一枚1元硬币)+dp[2] ; 1(我拿一枚2元硬币)+dp[1] 这两种可能性中的最小值
        int [] dp=new int [amount+1];
        // 数组大小为 amount + 1，初始值也为 amount + 1 ，为了防止溢出
        Arrays.fill(dp, amount + 1);
        dp[0]=0;
        //填表
        for(int i=1;i<amount+1;i++){
            for(int j=0;j<coins.length;j++){
                if(i-coins[j]>=0){
                    dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
                }
            }
        }
        return (dp[amount]==amount + 1)?-1:dp[amount];
    }


    // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
    int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp(coins, amount - coin);
            // 子问题无解则跳过
            if (subProblem == -1) continue;
            // 在子问题中选择最优解，然后加一
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }



    public static void main(String[] args) {
        int [] coins={1,5,2};
        System.out.println(coinChange(coins, 11));
    }
}
