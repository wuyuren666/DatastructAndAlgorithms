package com.wyr.leetcode.step2.dp;

public class MaxProfitIIITest {
    /**
     * 买卖股票不限制交易次数，但是含有手续费
     *
     * dp[i][0]=max{dp[i-1][0],dp[i-1][1]+prices[i]};
     * dp[i][1]=max{dp[i-1][1],dp[i-1][0]-prices[i]-fee} //相当于买的时候股票价格上涨
     * 或者
     * dp[i][0]=max{dp[i-1][0],dp[i-1][1]+prices[i]-fee}; //相当于卖出的时候股票价格下跌
     * dp[i][1]=max{dp[i-1][1],dp[i-1][0]-prices[i]}
     *
     *
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     */
    public int maxProfit_with_fee(int[] prices,int fee){
        int n=prices.length;
        int [][] dp=new int[n][2];
        //初始化：
        dp[0][0]=0;
        dp[0][1]=-prices[0]-fee;
        //填表
        for(int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]-fee);
        }
        return dp[n-1][0];
    }
}
