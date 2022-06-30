package com.wyr.leetcode.step2.dp;

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
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/coin-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int coinChange(int[] coins, int amount) {
        int M = coins.length + 2;
        int N = amount + 2;
        int[][] dp = new int[M][N];
        for (int j = 1; j < N; j++) {
            dp[0][j] = j - 1;
        }
        for (int j = 2; j < N; j++) {
            dp[1][j] = amount + 1;
        }
        for (int i = 2; i < M; i++) {
            dp[i][0] = coins[i - 2];
            dp[i][1] = 0;
        }
        for (int i = 2; i < M; i++) {
            for (int j = 2; j < N; j++) {
                //能找零钱
                if (dp[0][j] >= dp[i][0]) {
                    if (dp[i][dp[0][j] - dp[i][0] + 1] + 1 <= dp[i - 1][j]) {
                        dp[i][j] = dp[i][dp[0][j] - dp[i][0] + 1] + 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {//不能找零钱
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        if (dp[M - 1][N - 1] != amount + 1) { //最佳答案就是在表的右下角
            return dp[M - 1][N - 1];
        }
        return -1;
    }

    public static void main(String[] args) {
        int [] coins={1,5,2};
        System.out.println(coinChange(coins, 11));
    }
}
