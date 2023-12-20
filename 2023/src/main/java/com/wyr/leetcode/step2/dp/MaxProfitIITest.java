package com.wyr.leetcode.step2.dp;

public class MaxProfitIITest {
    /**
     * 不限制股票买卖的次数，但是含有冷冻期
     *
     *     * 不限制买卖次数，也就是k为无穷
     *      * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
     *      * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *      * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *      *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     *      *
     *      * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
     *      * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *      * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     *
     * 因为含有冷冻期，也就是卖出股票后无法在第二天买股票
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     *
     * 还需要注意的是初始化的时候，要多考虑一下。
     *
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     * */

    public int maxProfit_with_cool(int [] prices){
        int n=prices.length;
        int[][]dp=new int[n][2];
        if(prices.length==1||prices==null){
            return 0;
        }
        //step1:初始化
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        //   dp[i][1]
        // = max(dp[i-1][1], dp[-1][0] - prices[i])
        // = max(dp[i-1][1], 0 - prices[i])
        // = max(dp[i-1][1], -prices[i])
        dp[1][0]=Math.max(dp[0][0],dp[0][1]+prices[1]);
        dp[1][1]=Math.max(dp[0][1],0-prices[1]);
        //step2:填剩下的格子
        for(int i=2;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);//我今天没有持有股票，可能1：前一天我也没有持有股票，今天无操作；可能2：前一天我持有股票，今天我选择卖出。
            dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]); //我今天持有股票，可能1：前一天我也持有股票，今天无操作；可能2：前前一天我没有持有股票（前前一天我将手里股票卖了），今天我选择买入。
        }
        return dp[n-1][0];
    }
}
