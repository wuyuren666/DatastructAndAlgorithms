package com.wyr.leetcode.step2.dp;

public class MaxProfitVTest {

    /**
     * 在MaxProfitIVTest中，max_k为2，这里我们的k是由题目输入的，思想大致一致，但是如果直接拿代码来用，内存可能会不够
     *
     * 有了上一题 k = 2 的铺垫，这题应该和上一题的第一个解法没啥区别，你把上一题的 k = 2 换成题目输入的 k 就行了。
     *
     * 但试一下发现会出一个内存超限的错误，原来是传入的 k 值会非常大，dp 数组太大了。那么现在想想，交易次数 k 最多有多大呢？
     *
     * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k 没有限制的情况，而这种情况是之前解决过的。
     *
     * 所以我们可以直接把之前的代码重用：
     */

    public int maxProfit_k_any(int k, int [] prices){
        int n = prices.length;
        if (n <= 0) {
            return 0;
        }

        if (k > n / 2) {
            // 复用之前交易次数 k 没有限制的情况
            return maxProfit_k_inf(prices);
        }

        int [][][] dp=new int[n][k+1][2];
        for(int i=0;i<n;i++){
            for(int tempK=1;tempK<=k;tempK++){ //穷举k
                if(i==0){ //baseCase
                    dp[i][tempK][0]=0;
                    dp[i][tempK][1]=-prices[i];
                    continue;
                }
                dp[i][tempK][0]=Math.max(dp[i-1][tempK][0],dp[i-1][tempK][1]+prices[i]);
                dp[i][tempK][1]=Math.max(dp[i-1][tempK][1],dp[i-1][tempK-1][0]-prices[i]);
            }
        }
        return dp[n-1][k][0];
    }

    public int maxProfit_k_inf(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        //初始化
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        //填剩下的位置
        for (int i = 1; i < n; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]); //我今天没有持有股票，可能1：前一天我也没有持有股票，今天无操作；可能2：前一天我持有股票，今天我选择卖出。
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]); //我今天持有股票，可能1：前一天我也持有股票，今天无操作；可能2：前一天我没有持有股票，今天我选择买入。
        }
        return dp[n - 1][0]; //在第n-1天，我将手里的股票卖掉的情况下能获得的最大利润
    }
}
