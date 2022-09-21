package com.wyr.leetcode.step3.dp;

public class MinDistanceTest {
    /**
     * 给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数 。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     *
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/edit-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minDistanceDP(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i] 和 s2[0..j] 的最小编辑距离是 dp[i+1][j+1]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int p1=dp[i - 1][j] + 1;
                    int p2=dp[i][j - 1] + 1;
                    int p3=dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(p1,p2),p3);
                }
            }
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }


    //暴力递归
    public int minDistance(String word1, String word2) {
        return process(word1,word1.length()-1,word2,word2.length()-1);
    }

    // 定义：返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
    public int process(String s1, int i, String s2, int j){
        //baseCase
        if(i==-1){ //i 走完 s1 时 j 还没走完了 s2，那就只能用插入操作把 s2 剩下的字符全部插入 s1。
            return j+1;
        }
        if(j==-1){ //j 走完 s2 时 i 还没走完了 s1，那就只能用删除操作把 s1 缩短为 s2。
            return i+1;
        }

        if(s1.charAt(i)==s2.charAt(j)){// 啥都不做
            return process(s1,i-1,s2,j-1);
        }else{
            int p1=1+process(s1,i,s2,j-1); //插入
            int p2=1+process(s1,i-1,s2,j);//删除
            int p3=1+process(s1,i-1,s2,j-1);//替换
            return Math.min(Math.min(p1,p2),p3);
        }
    }
}
