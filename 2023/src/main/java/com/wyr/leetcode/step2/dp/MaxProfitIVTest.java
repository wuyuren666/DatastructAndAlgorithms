package com.wyr.leetcode.step2.dp;

public class MaxProfitIVTest {
    /**
     * 买卖股票限制交易最大次数为2次
     *
     * k = 2 和前面题目的情况稍微不同，因为上面的情况都和 k 的关系不太大：要么 k 是正无穷，状态转移和 k 没关系了；要么 k = 1，跟 k = 0 这个 base case 挨得近，最后也没有存在感。
     *
     * 这道题 k = 2 和后面要讲的 k 是任意正整数的情况中，对 k 的处理就凸显出来了，我们直接写代码，边写边分析原因。
     *
     * 原始的状态转移方程，没有可化简的地方
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 按照之前的代码，我们可能想当然这样写代码（错误的）：
     */
    public int errorTest(int [] prices){
        int n=prices.length;
        int k = 2;
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // 处理 base case
                dp[i][k][0] = 0;
                dp[i][k][1] = -prices[i];
                continue;
            }
            dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
            dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
        }
        return dp[n - 1][k][0];
        /**
         * 为什么错误？我这不是照着状态转移方程写的吗？
         *
         * 还记得前面总结的「穷举框架」吗？就是说我们必须穷举所有状态。其实我们之前的解法，都在穷举所有状态，只是之前的题目中 k 都被化简掉了。
         *
         * 但当 k = 2 时，由于没有消掉 k 的影响，所以必须要对 k 进行穷举：
         */
    }

    public int rightTest(int [] prices){
        int n=prices.length;
        int max_k=2;
        int [][][] dp=new int[n][max_k+1][2];
        for(int i=0;i<n;i++){
            for(int k=1;k<=max_k;k++){ //穷举k
                if(i==0){ //baseCase
                    dp[i][k][0]=0;
                    dp[i][k][1]=-prices[i];
                    continue;
                }
                dp[i][k][0]=Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                dp[i][k][1]=Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);
            }
        }
        return dp[n-1][max_k][0];
    }
}
